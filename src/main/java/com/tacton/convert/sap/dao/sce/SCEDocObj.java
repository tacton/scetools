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
 * SAP SCEDOCOBJ (Link Between Documents and Objects) Table & Fields
 * <p></p>
 * https://sapstack.com/tables/tabledetails.php?table=SCEDOCOBJ
 */
public class SCEDocObj extends AbstractSCEClass {
    public String mandt;
    public int kbid;            //SCEKB
    public int docuid;          //SCEID
    public int objid;        //SCEID
    public int objtype;        //SCEOBJTYPE

    public SCEDocObj() {
    }

    public static SCEDocObj of(String input) {
        SCEDocObj inst = new SCEDocObj();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.docuid = parseInt(split, 1);
        inst.objid = parseInt(split, 2);
        inst.objtype = parseInt(split, 3);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEDocObj{" +
                "docuid=" + docuid +
                ", objid=" + objid +
                ", objtype=" + objtype +
                '}';
    }
}
