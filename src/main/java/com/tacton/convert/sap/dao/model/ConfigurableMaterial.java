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
import com.tacton.convert.sap.SCEModel;
import com.tacton.convert.sap.dao.sce.SCEOClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representation of a configurable material.
 */
public class ConfigurableMaterial {

    public final String id;
    public final Translatable name;
    public final List<Characteristic> characteristics = new ArrayList<>();
    public List<BoMItem> billOfMaterials = Collections.emptyList();

    public ConfigurableMaterial(SCEModel model, int objId) {
        SCEImport sceImport = model.sceImport;

        SCEOClass sceoclass = sceImport.oclass.stream().filter(oc -> oc.objid == objId).findFirst()
                .orElseThrow(() -> new RuntimeException("Missing oclass " + objId));

        id = sceoclass.objname;
        name = new Translatable(sceoclass.objname,
                sceImport.ocltx.stream().filter(cl -> cl.objid == objId).collect(
                        Collectors.toMap(c -> c.languageid, c -> c.descript, (a, b) -> b)));

        collectCharacteristics(characteristics, model, objId);

        // do we have a BoM for this material?
        sceImport.hasdec.stream().filter(h -> h.objid == objId).findFirst().ifPresent(sceHasDec -> {
            billOfMaterials = sceImport.hasprt.stream()
                    .filter(hp -> hp.decompid.equals(sceHasDec.decompid))
                    .map(n -> new BoMItem(model, n))
                    .collect(Collectors.toList());
        });
    }

    private void collectCharacteristics(List<Characteristic> result, SCEModel model, int objId) {
        // all direct characteristics
        model.sceImport.hascha.stream().filter(pos -> pos.objid == objId)
                .map(pos -> model.characteristics.get(pos.charid))
                .forEach(result::add);

        // recursively collect from super classes
        model.sceImport.zuper.stream().filter(s -> s.subobjid == objId)
                .forEach(c -> collectCharacteristics(result, model, c.supobjid));
    }
}
