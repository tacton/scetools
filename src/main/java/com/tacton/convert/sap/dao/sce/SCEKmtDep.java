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
 * Assignment: Dependencies for Configurable Materials
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scekmtdep
 */
public class SCEKmtDep extends AbstractSCEClass {
    public String mandt;
    public int kbid;     //SCEKB
    public int objid;   //SCEMAT
    public int depid;   //SCEDEP
    public int posnr;
    public int deppos;
    public int proc_event;

    public SCEKmtDep() {
    }

    public static SCEKmtDep of(String input) {
        SCEKmtDep inst = new SCEKmtDep();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.objid = parseInt(split, 1);
        inst.depid = parseInt(split, 2);
        inst.posnr = parseInt(split, 3);
        inst.deppos = parseInt(split, 4);
        inst.proc_event = parseInt(split, 5);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEKmtDep{" +
                "objid=" + objid +
                ", depid=" + depid +
                ", posnr=" + posnr +
                ", deppos=" + deppos +
                ", proc_event=" + proc_event +
                '}';
    }
}
