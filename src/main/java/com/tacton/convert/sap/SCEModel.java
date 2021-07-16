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

package com.tacton.convert.sap;

import com.tacton.convert.sap.dao.model.Characteristic;
import com.tacton.convert.sap.dao.model.ConfigurableMaterial;
import com.tacton.convert.sap.dao.sce.VariantTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A representation of the variant configurator "model" as represented in SAP
 */
public class SCEModel {

    public ConfigurableMaterial configurableMaterial;
    public List<VariantTable> variantTables;

    public final SCEImport sceImport;
    public final Map<Integer, Characteristic> characteristics = new HashMap<>();

    public SCEModel(SCEImport sceImport) {
        this.sceImport = sceImport;
        this.variantTables = sceImport.variantTables;

        // parse all characteristics first
        sceImport.charac.forEach(c ->
                characteristics.put(c.charid, new Characteristic(sceImport, c)));

        // recursively parse all configurable materials and their bom materials
        this.configurableMaterial = new ConfigurableMaterial(this, 1);
    }
}
