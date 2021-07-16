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

package com.tacton.convert;

import java.util.Map;
import java.util.TreeMap;

/**
 * SAP Language code translations
 * <p></p>
 * https://help.sap.com/doc/saphelp_nw74/7.4.16/en-us/c1/ae563cd2ad4f0ce10000000a11402f/frameset.htm
 */
public class Languages {

    private static Map<String, String> isoToSap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Map<String, String> sapToIso = new TreeMap<>();
    private static Map<String, String> sapToName = new TreeMap<>();

    public static String getSAPLanguageFromIso(String iso) {
        return isoToSap.get(iso);
    }

    public static String getISOLanguageFromSap(String sap) {
        return sapToIso.get(sap);
    }

    public static String getLanguageNameFromSap(String sap) {
        return sapToName.get(sap);
    }

    static {
        addLanguage("Afrikaans", "AF", "a");
        addLanguage("Arabic", "AR", "A");
        addLanguage("Bulgarian", "BG", "W");
        addLanguage("Catalan", "CA", "c");
        addLanguage("Chinese", "ZH", "1");
        addLanguage("Chinese traditional", "ZF", "M");
        addLanguage("Croatian", "HR", "6");
        addLanguage("Czech", "CS", "C");
        addLanguage("Danish", "DA", "K");
        addLanguage("Dutch", "NL", "N");
        addLanguage("English", "EN", "E");
        addLanguage("Estonian", "ET", "9");
        addLanguage("Finnish", "FI", "U");
        addLanguage("French", "FR", "F");
        addLanguage("German", "DE", "D");
        addLanguage("Greek", "EL", "G");
        addLanguage("Hebrew", "HE", "B");
        addLanguage("Hungarian", "HU", "H");
        addLanguage("Icelandic", "IS", "b");
        addLanguage("Indonesian", "ID", "i");
        addLanguage("Italian", "IT", "I");
        addLanguage("Japanese", "JA", "J");
        addLanguage("Korean", "KO", "3");
        addLanguage("Latvian", "LV", "Y");
        addLanguage("Lithuanian", "LT", "X");
        addLanguage("Malay", "MS", "7");
        addLanguage("Norwegian", "NO", "O");
        addLanguage("Polish", "PL", "L");
        addLanguage("Portuguese", "PT", "P");
        addLanguage("Reserved- cust†.", "Z1", "Z");
        addLanguage("Romanian", "RO", "4");
        addLanguage("Russian", "RU", "R");
        addLanguage("Serbian", "SR", "0");
        addLanguage("Serbo-Croatian", "SH", "d");
        addLanguage("Slovakian", "SK", "Q");
        addLanguage("Slovene", "SL", "5");
        addLanguage("Spanish", "ES", "S");
        addLanguage("Swedish", "SV", "V");
        addLanguage("Thai", "TH", "2");
        addLanguage("Turkish", "TR", "T");
        addLanguage("Ukrainian", "UK", "8");
    }

    private static void addLanguage(String name, String iso, String sap) {
        sapToIso.put(sap, iso);
        sapToName.put(sap, name);
        isoToSap.put(iso, sap);
    }
}
