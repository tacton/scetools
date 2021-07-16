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
 * Components of Decomposition (BOM and so on)
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scehasprt
 */
public class SCEHasPrt extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public String decompid; // check SCEDECOMP
    public int posid;
    public int objid;  // check SCEOCLASS
    public int posmin;
    public int posmax;
    public int altgroupid;
    public int congroupid;
    public String posnr;
    public Boolean salesrelev; // T / F / NULL

    public SCEHasPrt() {
    }

    public static SCEHasPrt of(String input) {
        SCEHasPrt inst = new SCEHasPrt();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.decompid = parseString(split, 1);
        inst.posid = parseInt(split, 2);
        inst.objid = parseInt(split, 3);

        inst.posmin = parseInt(split, 4);
        inst.posmax = parseInt(split, 5);
        inst.altgroupid = parseInt(split, 6);
        inst.congroupid = parseInt(split, 7);
        inst.posnr = parseString(split, 8);
        inst.salesrelev = parseBoolean(split, 9);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEHasPrt{" +
                "decompid='" + decompid + '\'' +
                ", posid=" + posid +
                ", objid=" + objid +
                ", posmin=" + posmin +
                ", posmax=" + posmax +
                '}';
    }
}
