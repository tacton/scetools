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

package com.tacton.convert.sap.dao.model;

import com.tacton.convert.Translatable;
import com.tacton.convert.sap.SCEImport;
import com.tacton.convert.sap.dao.sce.SCECharac;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Representation of a domain i.e the possible values for a Characteristic.
 */
public class Domain {

    public String name;
    public List<DomainElement> elementList = new ArrayList<>();

    public boolean isNumeric = false;
    public boolean isString = false;

    public Domain(String name) {
        this.name = name;
    }

    @NotNull
    public static Domain parseDomain(SCEImport sceImport, SCECharac e) {
        Domain domain = new Domain(e.charname);

        // determine if string or number
        if (sceImport.string.stream().filter(s -> s.charid == e.charid).findFirst().orElse(null) != null) {
            domain.isString = true;
        } else if (sceImport.num.stream().anyMatch(s -> s.charid == e.charid)) {
            domain.isNumeric = true;
        }

        Set<String> seenElements = new HashSet<>();

        // gather string domain elements
        sceImport.clsdom.stream().filter(c -> c.charid == e.charid).forEach(el -> {
            String value = sceImport.valsym.stream().filter(vs -> vs.symbolid == el.symbolid).findFirst().map(a -> a.charvalue).orElseThrow(RuntimeException::new);

            Map<String, String> translations = sceImport.valtx.stream().filter(vs -> vs.symbolid == el.symbolid).filter(vs -> vs.charid == e.charid)
                    .collect(Collectors.toMap(v -> v.languageid, v -> v.descript, (a, b) -> b));

            Translatable t = new Translatable(value, translations);

            domain.elementList.add(new DomainElement(value, t));
            seenElements.add(value);
        });

        // gather number domain elements
        sceImport.clndom.stream().filter(c -> c.charid == e.charid).forEach(el -> {
            String valueFrom = sceImport.valsym.stream().filter(vs -> vs.symbolid == el.symbolfrom).findFirst().map(a -> a.charvalue).orElseThrow(RuntimeException::new);

            // values can be closed or open intervals, based on the clndom.valuecode
            //String valueTo = sceImport.valsym.stream().filter(vs -> vs.symbolid == el.symbolto).findFirst().map(a->a.charvalue).orElseThrow(RuntimeException::new);

            Translatable t = new Translatable(valueFrom, Collections.emptyMap());

            domain.elementList.add(new DomainElement(valueFrom, t));
            seenElements.add(valueFrom);
        });


        // Add missing values from the variant tables
        Set<String> missingValues = new TreeSet<>();
        sceImport.variantTables.forEach(table -> {
            List<SCECharac> columns = table.columns;
            for (int i = 0; i < columns.size(); i++) {
                if (columns.get(i).charid == e.charid) {
                    final int j = i;
                    table.data.forEach(row -> missingValues.add(row.get(j)));
                }
            }
        });
        // But don't create duplicates
        missingValues.removeAll(seenElements);
        missingValues.forEach(value ->
                domain.elementList.add(new DomainElement(value, new Translatable(value, Collections.emptyMap()))));

        return domain;
    }

    @Override
    public String toString() {
        return name + " elementList=" + elementList.size();
    }
}
