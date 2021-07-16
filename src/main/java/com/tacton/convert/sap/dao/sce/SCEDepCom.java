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
 * Compilation of Dependency Nets
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scedepcom
 */
public class SCEDepCom extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public int netid;
    public int codeidx;
    public int termid;
    public int type;
    public int operator;
    public int arg1;
    public int arg2;

    public SCEDepCom() {
    }

    public static SCEDepCom of(String input) {
        SCEDepCom inst = new SCEDepCom();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.netid = parseInt(split, 1);
        inst.codeidx = parseInt(split, 2);
        inst.termid = parseInt(split, 3);
        inst.type = parseInt(split, 4);
        inst.operator = parseInt(split, 5);
        inst.arg1 = parseInt(split, 6);
        inst.arg2 = parseInt(split, 7);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEDepCom{" +
                "netid=" + netid +
                ", codeidx=" + codeidx +
                ", termid=" + termid +
                ", type=" + type +
                ", operator=" + operator +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                '}';
    }
}
