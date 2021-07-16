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

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SubGroup extends ExecutionEntry {
    public List<ExecutionEntry> members = new LinkedList<>();

    public SubGroup(String name) {
        super(name);
    }

    public SubGroup withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubGroup createSubGroup(String name) {
        SubGroup subGroup = new SubGroup(name);
        members.add(subGroup);
        return subGroup;
    }

    public Field createField(String name, String attRef, String widget) {
        Field field = new Field(name, attRef, widget);
        members.add(field);
        return field;
    }

    public String toXML() {
        return "<group>" +
                "<name>" + name + "</name>" +
                "<description>" + description + "</description>" +
                "<members>" +
                members.stream().map(ExecutionEntry::toXML).collect(Collectors.joining()) +
                "</members>" +
                "</group>";
    }
}
