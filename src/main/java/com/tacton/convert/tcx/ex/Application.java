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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public String name;
    public String comment = "";
    public List<ApplicationStep> steps = new LinkedList<>();
    public Map<String, String> properties = new HashMap<>();

    public Application(String name) {
        this.name = name;
    }

    public Application withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ApplicationStep createStep(String name, String topPart) {
        ApplicationStep step = new ApplicationStep(name).withTopPartRef(topPart);
        steps.add(step);
        return step;
    }

    public String toXML() {
        return "<application>" +
                "<name>" + name + "</name>" +
                "<steps>" + steps.stream().map(ApplicationStep::toXML).collect(Collectors.joining()) + "</steps>" +
                "<properties>" + properties.entrySet().stream().map(this::map2xml).collect(Collectors.joining()) + "</properties>" +
                "<comment>" + comment + "</comment>" +
                "</application>";
    }

    private String map2xml(Map.Entry<String, String> ent) {
        return "<property>" +
                "<name>" + ent.getKey() + "</name>" +
                "<value>" + ent.getValue() + "</value>" +
                "</property>";
    }
}
