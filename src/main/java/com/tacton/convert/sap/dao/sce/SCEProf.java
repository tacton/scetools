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
 * Profile of Knowledge Base (Starting Point of Configuration)
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/sceprof
 */
public class SCEProf extends AbstractSCEClass {
    public String mandt;
    public int kbid;            //SCEKB
    public String profname;
    public int objid;            //SCEOCLASS
    public String uiname;

    public SCEProf() {
    }

    public static SCEProf of(String input) {
        SCEProf inst = new SCEProf();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.profname = parseString(split, 1);
        inst.objid = parseInt(split, 2);
        inst.uiname = parseString(split, 3);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEProf{" +
                "profname='" + profname + '\'' +
                ", objid=" + objid +
                ", uiname='" + uiname + '\'' +
                '}';
    }
}
