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
 * Assignment of Decomposition to OO Class
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scehasdec
 */
public class SCEHasDec extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public int objid;  // check SCEOCLASS
    public String decompid; // check SCEDECOMP

    public SCEHasDec() {
    }

    public static SCEHasDec of(String input) {
        SCEHasDec inst = new SCEHasDec();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.objid = parseInt(split, 1);
        inst.decompid = parseString(split, 2);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEHasDec{" +
                "objid=" + objid +
                ", decompid='" + decompid + '\'' +
                '}';
    }
}
