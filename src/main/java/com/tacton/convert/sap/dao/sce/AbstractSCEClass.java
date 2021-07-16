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
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractSCEClass {

    public static Boolean parseBoolean(String[] str, int col) {
        if (str.length >= col) {
            if ("t".equals(str[col]) || "T".equals(str[col])) {
                return Boolean.TRUE;
            } else if ("f".equals(str[col]) || "F".equals(str[col])) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public static String parseString(String[] str, int col) {
        if (str.length > col) {
            return str[col];
        }
        return null;
    }

    public static int parseInt(String[] str, int col) {
        if (str.length >= col) {
            return Integer.parseInt(str[col]);
        }
        return -99999;
    }

    public static Date parseDate(String[] str, int col) throws ParseException {
        if (str.length >= col) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.parse(str[col]);
        }
        return null;
    }
}
