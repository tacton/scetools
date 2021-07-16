/*
 * Copyright 2021 Tacton Systems
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.tacton.convert.tcx;

import com.tacton.convert.sap.SCEModel;
import com.tacton.convert.sap.dao.model.ConfigurableMaterial;
import com.tacton.convert.sap.dao.model.Domain;
import com.tacton.convert.tcx.cc.Component;
import com.tacton.convert.tcx.cc.ComponentClass;
import com.tacton.convert.tcx.dom.NamedDomain;
import com.tacton.convert.tcx.dom.NamedDomainElement;
import com.tacton.convert.tcx.ex.Application;
import com.tacton.convert.tcx.ex.ApplicationStep;
import com.tacton.convert.tcx.ex.SubGroup;
import com.tacton.convert.tcx.ps.ConstraintGroup;
import com.tacton.convert.tcx.ps.Part;
import com.tacton.convert.tcx.ps.PartAttribute;
import com.tacton.convert.tcx.ps.SelectAllConstraint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TCXBuilder {
    SCEModel model;
    public String defaultLanguageCode = "E";
    public List<NamedDomain> domainList = new LinkedList<>();
    public List<ComponentClass> componentClasses = new LinkedList<>();
    public List<Application> applications = new LinkedList<>();
    public List<Part> rootParts = new LinkedList<>();
    public Part rootPart;
    public ConfigurableMaterial oClass;
    public Application theApp;
    public ApplicationStep rootStep;


    public TCXBuilder(SCEModel model) {
        this.model = model;

        rootPart = new Part(model.configurableMaterial.id);
        rootParts.add(rootPart);

        oClass = model.configurableMaterial;
        theApp = new Application(oClass.id).withComment(oClass.name.getValue(defaultLanguageCode));
        applications.add(theApp);

        buildApplication();
        buildDomains();
        buildPartTree(oClass, rootPart);

        buildVariantTables();
    }

    public String generateTCX() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        StringBuilder tcx = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<model-data>" +
                "<identification>" +
                "<created-by>SCEImporter</created-by>" +
                "<edited-with>TCStudio4.11.3</edited-with>" +
                "<date>" + sdf.format(new Date()) + "</date>" +
                "<xml-version>4.11</xml-version>" +
                "</identification>" +
                "<model>");
        tcx.append(domainList.stream().map(NamedDomain::toXML).collect(Collectors.joining("", "<named-domains>", "</named-domains>")));
        tcx.append(componentClasses.stream().map(ComponentClass::toXML).collect(Collectors.joining("", "<component-classes>", "</component-classes>")));
        tcx.append(rootParts.stream().map(Part::toXML).collect(Collectors.joining("", "<root-parts>", "</root-parts>")));

        tcx.append("<collections/>");
        tcx.append(applications.stream().map(Application::toXML).collect(Collectors.joining("", "<applications>", "</applications>")));
        tcx.append("<includes/>");

        tcx.append("</model></model-data>");
        return tcx.toString();
    }

    private void buildApplication() {
        theApp.properties.put("&amp;max_runtime", "30000");
        rootStep = theApp.createStep("Step", rootPart.name).withAttrRef("none").withDescription(oClass.name.getValue(defaultLanguageCode));
        SubGroup rootStepGroup = rootStep.createGroup("root");

        SubGroup mainGroup = rootStepGroup.createSubGroup(oClass.id).withDescription(oClass.name.getValue(defaultLanguageCode));
        oClass.characteristics.forEach(c -> {
            mainGroup.createField(c.name.toString(), c.id, "menu").withDescription(c.name.getValue(defaultLanguageCode));
        });

        model.configurableMaterial.billOfMaterials.forEach(boMItem -> {
            ConfigurableMaterial kmat = boMItem.configurableMaterial;
            SubGroup subGroup = rootStepGroup.createSubGroup(kmat.id).withDescription(kmat.name.getValue(defaultLanguageCode));
            kmat.characteristics.forEach(c -> {
                subGroup.createField(kmat.id + " - " + c.name, kmat.name + "." + c.id, "menu").withDescription(c.name.toString());
            });
        });
    }

    private void buildPartTree(ConfigurableMaterial oClass, Part rootPart) {
        ConstraintGroup defaultConstraints = rootPart.createConstraintGroup("Default Value Assignments");
        ConstraintGroup selectConstraints = rootPart.createConstraintGroup("Equalize All Shared Characteristics");
        selectConstraints.enabled = false;

        // create attributes for all characteristics
        oClass.characteristics.forEach(c -> {
            String dom = c.id;
            if (c.domain.elementList.isEmpty()) {
                if (c.domain.isString) {
                    dom = "function";
                    String defaultValue = c.defaultValue == null ? "" : c.defaultValue;
                    // function attributes require a default value
                    defaultConstraints.createDefaultConstraint(c.id, defaultValue);
                } else {
                    dom = "int";
                }
            } else {
                selectConstraints.constraints.add(new SelectAllConstraint(c.id));
                if (c.defaultValue != null) {
                    defaultConstraints.createDefaultConstraint(c.id, c.defaultValue);
                }
            }
            rootPart.attributes.add(new PartAttribute(c.id, dom));
        });

        oClass.billOfMaterials.forEach(boMItem -> {
            ConfigurableMaterial kmat = boMItem.configurableMaterial;
            Part part = rootPart.createSubPart(kmat.id, null);
            buildPartTree(kmat, part);
        });
    }

    private void buildDomains() {
        NamedDomain booleanDomain = new NamedDomain("bool")
                .withElement(new NamedDomainElement("No", "No", "0"))
                .withElement(new NamedDomainElement("Yes", "Yes", "1"));
        domainList.add(booleanDomain);

        // create a named domain for every characteristic domain with any values
        model.characteristics.values().forEach(ch -> {
            Domain dom = ch.domain;
            if (!dom.elementList.isEmpty()) {
                NamedDomain domain = new NamedDomain(dom.name);
                dom.elementList.stream().distinct().forEach(de -> {
                    domain.withElement(new NamedDomainElement(de.id, de.name.getValue(defaultLanguageCode)));
                });
                domainList.add(domain);
            }
        });
    }

    private void buildVariantTables() {
        ConstraintGroup variantTables = rootPart.createConstraintGroup("Variant Table Constraints");
        model.variantTables.forEach(ct -> {
            ConstraintGroup subgroup = variantTables.createConstraintGroup(ct.tableName);
            subgroup.enabled = false;

            String tableName = ct.tableName + "_vtab";
            ComponentClass cc = new ComponentClass(tableName, ct.tableName);
            ct.columns.forEach(col -> {
                cc.createFeatureDomain(col.charname, col.charname);
                // this constraint is only valid if the root part has all characteristics that the table does
                // else the constraint will be invalid
                subgroup.createVTabConstraint(tableName + "." + col.charname, col.charname);
            });
            AtomicInteger cnt = new AtomicInteger();
            ct.data.forEach(d -> {
                cnt.getAndIncrement();
                String name = String.format("%s_%04d", ct.tableName, cnt.get());
                Component comp = cc.createComponent(name, name);
                for (int i = 0; i < ct.columns.size(); i++) {
                    comp.createFeatureValue(ct.columns.get(i).charname, d.get(i));
                }
            });
            componentClasses.add(cc);
            this.rootPart.createSubPart(tableName, tableName);
        });
    }
}
