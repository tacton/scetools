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

package com.tacton.convert.tcx.cc;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Component {
    public String name;
    public String description;
    public List<FeatureValue> featureValues = new LinkedList<>();

    public Component(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FeatureValue createFeatureValue(String name, String domainRef) {
        FeatureValue fd = new FeatureValue(name, domainRef);
        featureValues.add(fd);
        return fd;
    }

    public String toXML() {
        return "<component>" +
                "<name>" + name + "</name>" +
                "<description>" + description + "</description>" +
                featureValues.stream().map(FeatureValue::toXML).collect(Collectors.joining("", "<feature-values>", "</feature-values>")) +
                "</component>";
    }
}
