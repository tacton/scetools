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

import java.util.stream.Collectors;

/**
 * Representation of a Characteristic.
 */
public class Characteristic {

    public final String id;
    public final Translatable name;

    // input requirements
    public int maxLength = -1;
    public String template;
    public String defaultValue;
    public boolean multiValued;

    public Domain domain;

    public Characteristic(SCEImport sceImport, SCECharac sceCharac) {
        int cid = sceCharac.charid;

        this.id = sceCharac.charname;
        this.name = new Translatable(sceCharac.charname,
                sceImport.chatx.stream().filter(c -> c.charid == cid).collect(Collectors.toMap(a -> a.languageid, a -> a.descript, (a, b) -> b)));

        this.defaultValue = sceImport.clval.stream().filter(c -> c.charid == cid).findFirst().flatMap(val ->
                sceImport.valsym.stream().filter(s -> s.symbolid == val.symbolid).findFirst().map(a -> a.charvalue)).orElse(null);

        // restrictions for numeric values
        sceImport.num.stream().filter(c -> c.charid == cid).findFirst().ifPresent(sceNum -> {
            this.maxLength = sceNum.length;
            this.template = sceNum.template;
        });

        // restrictions for string values
        sceImport.string.stream().filter(c -> c.charid == cid).findFirst().ifPresent(sceString -> {
            this.maxLength = sceString.length;
            this.template = sceString.template;
        });

        this.multiValued = (sceCharac.charkind == SCECharac.KIND_MULTI_VALUES);

        this.domain = Domain.parseDomain(sceImport, sceCharac);
    }
}