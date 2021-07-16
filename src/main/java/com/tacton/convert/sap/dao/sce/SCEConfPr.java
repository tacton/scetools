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
 * Configuration Profiles
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/sceconfpr
 */
public class SCEConfPr extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public int objid;
    public String confprname;
    public String bom_appl;
    public int scenario;
    public String decomp_man;
    public String value_man;
    public int dsint;

    public SCEConfPr() {
    }

    public static SCEConfPr of(String input) {
        SCEConfPr inst = new SCEConfPr();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.objid = parseInt(split, 1);
        inst.confprname = parseString(split, 2);
        inst.bom_appl = parseString(split, 3);
        inst.scenario = parseInt(split, 4);
        inst.decomp_man = parseString(split, 5);
        inst.value_man = parseString(split, 6);
        inst.dsint = parseInt(split, 7);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEConfPr{" +
                "objid=" + objid +
                ", confprname='" + confprname + '\'' +
                ", bom_appl='" + bom_appl + '\'' +
                ", scenario=" + scenario +
                ", decomp_man='" + decomp_man + '\'' +
                ", value_man='" + value_man + '\'' +
                ", dsint=" + dsint +
                '}';
    }
}
