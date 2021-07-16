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
 * Interface Design - Sequence of Characteristics in a Group
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scecuco
 */
public class SCECuco extends AbstractSCEClass {
    public String mandt;
    public int kbid;     //SCEKB
    public int dsint;
    public int grint;    //SCECUSD
    public int charid;   //SCECHARAC
    public int counter;

    public SCECuco() {
    }

    public static SCECuco of(String input) {
        SCECuco inst = new SCECuco();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.dsint = parseInt(split, 1);
        inst.grint = parseInt(split, 2);
        inst.charid = parseInt(split, 3);
        inst.counter = parseInt(split, 4);
        return inst;
    }

    @Override
    public String toString() {
        return "SCECuco{" +
                "dsint=" + dsint +
                ", grint=" + grint +
                ", charid=" + charid +
                ", counter=" + counter +
                '}';
    }
}
