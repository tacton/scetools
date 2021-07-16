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

import java.text.ParseException;
import java.util.Date;

/**
 * Runtime Version of SCE Knowledge Base
 * <p></p>
 * https://dev-workbench.com/en/sap-dictionary/table/scekb
 */
public class SCEKb extends AbstractSCEClass {
    public String mandt;
    public int kbid;
    public String kbobjname;  //SCEKBOBJ
    public String version;
    public int build;
    public Date fromdate; // Format YYYYMMDD
    public String status; //TCUX
    public String authorize; //TCUB
    public String creator;
    public Date creadate; // Format YYYYMMDD
    public String changer;
    public Date changedate; // Format YYYYMMDD
    public String lang_down;   //T002
    public String plant;       //T001W
    public String bom_appl;    //TC04
    public String klart_down;  //TCLA
    public Boolean classicdep; // T / F / NULL
    public Boolean actionflat; // T / F / NULL
    public String kbschema;

    public SCEKb() {
    }

    public static SCEKb of(String input) {
        SCEKb inst = new SCEKb();
        String[] split = input.split("\t");
        inst.kbid = parseInt(split, 0);
        inst.kbobjname = parseString(split, 1);
        inst.version = parseString(split, 2);
        inst.build = parseInt(split, 3);
        try {
            inst.fromdate = parseDate(split, 4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inst.status = parseString(split, 5);
        //inst.authorize = parseString(split,6);
        inst.creator = parseString(split, 6);

        try {
            inst.creadate = parseDate(split, 7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inst.changer = parseString(split, 8);

        try {
            inst.changedate = parseDate(split, 9);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inst.lang_down = parseString(split, 10);
        inst.plant = parseString(split, 11);
        inst.bom_appl = parseString(split, 12);
        inst.klart_down = parseString(split, 13);

        inst.classicdep = parseBoolean(split, 14);
        inst.actionflat = parseBoolean(split, 15);
        inst.kbschema = parseString(split, 16);

        return inst;
    }

    @Override
    public String toString() {
        return "SCEKb{" +
                "kbobjname='" + kbobjname + '\'' +
                ", fromdate=" + fromdate +
                ", creadate=" + creadate +
                ", lang_down='" + lang_down + '\'' +
                ", plant='" + plant + '\'' +
                ", bom_appl='" + bom_appl + '\'' +
                '}';
    }
}
