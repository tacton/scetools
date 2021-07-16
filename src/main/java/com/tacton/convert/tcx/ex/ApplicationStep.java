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

public class ApplicationStep {
    public String name;
    public String description;
    public String topPartRef;
    public String attributeRef;
    public SubGroup group;

    public ApplicationStep(String name) {
        this.name = name;
    }

    public ApplicationStep withDescription(String description) {
        this.description = description;
        return this;
    }

    public ApplicationStep withTopPartRef(String topPartRef) {
        this.topPartRef = topPartRef;
        return this;
    }

    public ApplicationStep withAttrRef(String attributeRef) {
        this.attributeRef = attributeRef;
        return this;
    }

    public SubGroup createGroup(String name) {
        SubGroup sg = new SubGroup(name);
        if (group == null) group = sg;
        return sg;
    }

    public String toXML() {
        String ret = "<step>" +
                "<name>" + name + "</name>" +
                "<description>" + description + "</description>" +
                "<step-top-part><ref-path><part-ref>" + topPartRef + "</part-ref></ref-path></step-top-part>" +
                "<opt-crit><attribute-ref><ref>" + attributeRef + "</ref></attribute-ref></opt-crit>";
        if (group == null) {
            ret += "<group/>";
        } else {
            ret += group.toXML();
        }
        ret += "</step>";
        return ret;
    }
}
