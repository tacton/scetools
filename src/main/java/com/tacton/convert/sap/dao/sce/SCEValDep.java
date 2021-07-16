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
 * Assignment of Dependencies to Char. Values of Classes
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scevaldep
 */
public class SCEValDep extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public int objid;           //SCEOCLASS
    public int charid;          //SCECHARAC
    public int symbolid;        //SCEVALSYM
    public int depid;           //SCEDEP

    public SCEValDep() {
    }

    public static SCEValDep of(String input) {
        SCEValDep inst = new SCEValDep();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.objid = parseInt(split, 1);
        inst.charid = parseInt(split, 2);
        inst.symbolid = parseInt(split, 3);
        inst.depid = parseInt(split, 4);
        return inst;
    }
}
