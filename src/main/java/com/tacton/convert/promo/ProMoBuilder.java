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

package com.tacton.convert.promo;

import com.tacton.convert.promo.assembly.Assembly;
import com.tacton.convert.promo.assembly.AssemblyAttribute;
import com.tacton.convert.promo.assembly.AssemblyPosition;
import com.tacton.convert.promo.domains.DomainElement;
import com.tacton.convert.promo.module.Module;
import com.tacton.convert.promo.module.ModuleFeature;
import com.tacton.convert.promo.module.ModuleVariant;
import com.tacton.convert.promo.module.ModuleVariantValue;
import com.tacton.convert.sap.SCEModel;
import com.tacton.convert.sap.dao.model.ConfigurableMaterial;
import com.tacton.convert.sap.dao.model.Domain;
import com.tacton.convert.sap.dao.sce.VariantTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProMoBuilder {
    public List<com.tacton.convert.promo.domains.Domain> domainList = new ArrayList<>();
    public List<Module> moduleList = new ArrayList<>();
    public List<Assembly> assemblyList = new ArrayList<>();
    public String defaultLanguageCode = "E";

    public ProMoBuilder(SCEModel model) {

        // domains for all characteristics
        model.characteristics.values().forEach(ch -> {
            Domain dom = ch.domain;
            com.tacton.convert.promo.domains.Domain domain = new com.tacton.convert.promo.domains.Domain(dom.name, dom.name);
            if (dom.elementList.isEmpty()) {
                if (dom.isString) {
                    domain.withType("String");
                }
                if (dom.isNumeric) {
                    domain.withType("Float");
                }
            } else {
                domain.elementList.addAll(dom.elementList.stream().map(el -> new DomainElement(el.id, el.name.getValue(defaultLanguageCode))).collect(Collectors.toList()));
            }
            domainList.add(domain);
        });

        // add all assemblies
        Assembly rootAssembly = createAssembly(model.configurableMaterial);
        assemblyList.add(rootAssembly);

        // add variant tables
        model.variantTables.forEach(ct -> {
            moduleList.add(createCombinationTableModule(ct, rootAssembly));
        });
    }

    public Module createCombinationTableModule(VariantTable variantTable, Assembly rootAssembly) {
        Module module = new Module(variantTable.tableName + "_vtab", "Variant Table " + variantTable.tableName);
        variantTable.columns.forEach(col ->
                module.featureList.add(new ModuleFeature(col.charname, col.charname)));

        int id = 100000;
        for (int i = 0; i < variantTable.data.size() - 1; i++) {
            ModuleVariant var = new ModuleVariant(module.name + "_" + (id + i), "unspecified");
            List<String> entries = variantTable.data.get(i);
            for (int ent = 0; ent < entries.size(); ent++) {
                var.values.add(new ModuleVariantValue(variantTable.columns.get(ent).charname, entries.get(ent)));
            }
            module.variantList.add(var);
        }

        rootAssembly.withPosition(new AssemblyPosition(variantTable.tableName + "_vtab", "Variant Table " + variantTable.tableName, variantTable.tableName + "_vtab"));
        return module;
    }

    private Assembly createAssembly(ConfigurableMaterial kmat) {
        Assembly assembly = new Assembly(kmat.id, kmat.name.getValue(defaultLanguageCode));
        kmat.name.translations.forEach(assembly::withTranslation);
        kmat.characteristics.forEach(chapos -> {
            AssemblyAttribute attr = new AssemblyAttribute(chapos.name.toString(), chapos.name.getValue(defaultLanguageCode)).withDomainName(chapos.id).withIO(false);
            chapos.name.translations.forEach(attr::withTranslation);
            assembly.withAttribute(attr);
        });

        kmat.billOfMaterials.forEach(bomItem -> {
            ConfigurableMaterial sub = bomItem.configurableMaterial;
            AssemblyPosition rootAssemblyPosition = new AssemblyPosition(sub.id, sub.name.getValue(defaultLanguageCode));
            rootAssemblyPosition.positionName = sub.id;
            rootAssemblyPosition.positionType = AssemblyPosition.POS_TYPE.ASSEMBLY;
            assembly.withPosition(rootAssemblyPosition);

            assemblyList.add(createAssembly(sub));
        });
        return assembly;
    }
}
