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

package com.tacton.convert.tcx.ex;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Field extends ExecutionEntry {
    private String attRef;
    private String path;
    private String widget;

    public Field(String name, String attRef, String widget) {
        super(name);
        if (attRef.contains(".")) {
            String[] split = attRef.split("\\.");
            this.attRef = split[split.length - 1];
            String[] pathSplit = Arrays.copyOf(split, split.length - 1);
            this.path = String.join(".", pathSplit);
        } else {
            this.attRef = attRef;
        }
        this.widget = widget;
    }

    @Override
    public String toXML() {
        String ret = "<field>" +
                "<name>" + name + "</name>" +
                "<description>" + description + "</description>" +
                "<attribute-ref>" +
                "<ref>" + attRef + "</ref>";
        if (path != null) {
            ret += "<ref-path>\n" +
                    "<part-ref>" + path + "</part-ref>\n" +
                    "</ref-path>";
        }
        ret += "</attribute-ref>" +
                "<widget>" + widget + "</widget>" +
                "</field>";
        return ret;
    }
}
