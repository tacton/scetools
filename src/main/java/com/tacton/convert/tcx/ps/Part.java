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

package com.tacton.convert.tcx.ps;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Part {
    public String name;
    public String realizedBy;
    public int numberOfInstances = 1;
    public List<PartAttribute> attributes = new LinkedList<>();
    public List<Part> subParts = new LinkedList<>();
    public List<Constraint> constraints = new LinkedList<>();
    private boolean isSubPart = false;

    public Part(String name) {
        this.name = name;
    }

    public Part(String name, String realizedBy) {
        this.name = name;
        this.realizedBy = realizedBy;
    }

    public Part createSubPart(String partName, String realizedBy) {
        Part sp = new Part(partName, realizedBy);
        sp.isSubPart = true;
        subParts.add(sp);
        return sp;
    }

    public ConstraintGroup createConstraintGroup(String name) {
        ConstraintGroup cg = new ConstraintGroup(name);
        constraints.add(cg);
        return cg;
    }

    public String toXML() {
        String ret = "<part>" +
                "<name>" + name + "</name>" +
                "<attributes>" + attributes.stream().map(PartAttribute::toXML).collect(Collectors.joining()) + "</attributes>" +
                "<subparts>" + subParts.stream().map(Part::toXML).collect(Collectors.joining()) + "</subparts>";
        if (realizedBy != null) {
            ret += "<realized-by><component-class-ref>" + realizedBy + "</component-class-ref></realized-by>";
        }
        ret += "<constraints>" + constraints.stream().map(Constraint::toXML).collect(Collectors.joining()) + "</constraints>" +
                "</part>";
        if (isSubPart) { //Wrap in Sub-Part tag.
            ret = "<subpart>" +
                    "<number-of-instances>" +
                    "<integer>" + numberOfInstances + "</integer>" +
                    "</number-of-instances>" +
                    ret
                    + "</subpart>";
        }
        return ret;
    }
}
