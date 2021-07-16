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

package com.tacton.convert.sap.dao.sce;

/**
 * Characteristics
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scecharac
 */
public class SCECharac extends AbstractSCEClass {

    // values for charkind
    public static final int KIND_SINGLE_VALUE = 1;
    public static final int KIND_MULTI_VALUES = 2;
    public static final int KIND_RESTRICT_SINGLE_VALUE = 3;
    public static final int KIND_RESTRICT_MULTI_VALUE = 4;
    public static final int KIND_BALANCE_VALUE = 5;
    public static final int KIND_SIMPLE_AGGR_VALUE = 6;

    public String mandt;
    public int kbid;
    public int charid;
    public String charname;
    public int datatype;
    public int charkind;
    public Boolean additvalue;
    public String dispmode;

    public SCECharac() {
    }

    public static SCECharac of(String input) {
        SCECharac inst = new SCECharac();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.charid = parseInt(split, 1);
        inst.charname = parseString(split, 2);
        inst.datatype = parseInt(split, 3);
        inst.charkind = parseInt(split, 4);
        inst.additvalue = parseBoolean(split, 5);
        inst.dispmode = parseString(split, 6);
        return inst;
    }

    @Override
    public String toString() {
        return "SCECharac for " + charname + " (" + charkind + ")";
    }
}
