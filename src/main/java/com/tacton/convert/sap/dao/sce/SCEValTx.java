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
 * Language-Dependent Descriptions of Values of Alphanum. Chars
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scevaltx
 */
public class SCEValTx extends AbstractSCEClass {
    public String mandt;
    public int kbid;            //SCEKB
    public int charid;          //SCESTRDOM
    public int symbolid;        //SCEVALSYM
    public String languageid;   //T002
    public String descript;

    public SCEValTx() {
    }

    public static SCEValTx of(String input) {
        SCEValTx inst = new SCEValTx();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.charid = parseInt(split, 1);
        inst.symbolid = parseInt(split, 2);
        inst.languageid = parseString(split, 3);
        inst.descript = parseString(split, 4);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEValTx{" +
                "charid=" + charid +
                ", symbolid=" + symbolid +
                ", languageid='" + languageid + '\'' +
                ", descript='" + descript + '\'' +
                '}';
    }
}
