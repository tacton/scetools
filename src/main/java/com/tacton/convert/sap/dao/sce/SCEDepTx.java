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
 * Language-Dependent Descriptions for Dependencies
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scedeptx
 */
public class SCEDepTx extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public int depid;
    public String languageid;
    public String descript;

    public SCEDepTx() {
    }

    public static SCEDepTx of(String input) {
        SCEDepTx inst = new SCEDepTx();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.depid = parseInt(split, 1);
        inst.languageid = parseString(split, 2);
        inst.descript = parseString(split, 3);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEDepTx{" +
                "depid=" + depid +
                ", languageid='" + languageid + '\'' +
                ", descript='" + descript + '\'' +
                '}';
    }
}
