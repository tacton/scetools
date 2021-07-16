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
 * Allowed Values for Numeric Characteristic of Class
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/sceclndom
 */
public class SCEClnDom extends AbstractSCEClass {

    // values from valuecode
    public static final int VALUE_CODE_EQ = 1;
    public static final int VALUE_CODE_GE_LT = 2;
    public static final int VALUE_CODE_GE_LE = 3;
    public static final int VALUE_CODE_GT_LT = 4;
    public static final int VALUE_CODE_GT_LE = 5;
    public static final int VALUE_CODE_LT = 6;
    public static final int VALUE_CODE_LE = 7;
    public static final int VALUE_CODE_GT = 8;
    public static final int VALUE_CODE_GE = 9;

    public String mandt;
    public int kbid;
    public int objid;
    public int charid;
    public int symbolfrom;
    public int valuecode;
    public int symbolto;

    public SCEClnDom() {
    }

    public static SCEClnDom of(String input) {
        SCEClnDom inst = new SCEClnDom();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.objid = parseInt(split, 1);
        inst.charid = parseInt(split, 2);
        inst.symbolfrom = parseInt(split, 3);
        inst.valuecode = parseInt(split, 4);
        inst.symbolto = parseInt(split, 5);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEClnDom{" +
                "objid=" + objid +
                ", charid=" + charid +
                ", symbolfrom=" + symbolfrom +
                ", valuecode='" + valuecode + '\'' +
                ", symbolto=" + symbolto +
                '}';
    }
}
