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
 * DNNs of Dependency
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scedepdnn
 */
public class SCEDepDnn extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public int netid;
    public int dnnid;
    public int dnntype;
    public int l_predn;
    public int r_predn;
    public int codeidx;

    public SCEDepDnn() {
    }

    public static SCEDepDnn of(String input) {
        SCEDepDnn inst = new SCEDepDnn();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.netid = parseInt(split, 1);
        inst.dnnid = parseInt(split, 2);
        inst.dnntype = parseInt(split, 3);
        inst.l_predn = parseInt(split, 4);
        inst.r_predn = parseInt(split, 5);
        inst.codeidx = parseInt(split, 6);
        return inst;
    }

    @Override
    public String toString() {
        return "SCEDepDnn{" +
                "netid=" + netid +
                ", dnnid=" + dnnid +
                ", dnntype=" + dnntype +
                ", l_predn=" + l_predn +
                ", r_predn=" + r_predn +
                ", codeidx=" + codeidx +
                '}';
    }
}
