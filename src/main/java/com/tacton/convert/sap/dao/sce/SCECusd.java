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
 * Interface Design - Grouping Structure
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scecusd
 */
public class SCECusd extends AbstractSCEClass {
    public String mandt;
    public int kbid;     //SCEKB
    public int dsint;
    public int grint;
    public int counter;
    public Boolean comp;
    public Boolean s_sales;
    public Boolean s_engineering;
    public Boolean s_p_sales;
    public Boolean s_p_purchase;
    public Boolean s_p_engineer;

    public SCECusd() {
    }

    public static SCECusd of(String input) {
        SCECusd inst = new SCECusd();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.dsint = parseInt(split, 1);
        inst.grint = parseInt(split, 2);
        inst.counter = parseInt(split, 3);
        inst.comp = parseBoolean(split, 4);
        inst.s_sales = parseBoolean(split, 5);
        inst.s_engineering = parseBoolean(split, 6);
        inst.s_p_sales = parseBoolean(split, 7);
        inst.s_p_purchase = parseBoolean(split, 8);
        inst.s_p_engineer = parseBoolean(split, 9);
        return inst;
    }

    @Override
    public String toString() {
        return "SCECusd{" +
                "dsint=" + dsint +
                ", grint=" + grint +
                ", counter=" + counter +
                ", comp=" + comp +
                ", s_sales=" + s_sales +
                ", s_engineering=" + s_engineering +
                ", s_p_sales=" + s_p_sales +
                ", s_p_purchase=" + s_p_purchase +
                ", s_p_engineer=" + s_p_engineer +
                '}';
    }
}
