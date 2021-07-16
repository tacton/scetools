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

package com.tacton.convert.promo.assembly;

public class AssemblyPosition {
    public enum POS_TYPE {MODULE, ASSEMBLY, CATALOG}
    public POS_TYPE positionType = POS_TYPE.MODULE;

    public boolean defaultView = true;
    public boolean dynamic = false;
    public String description;
    public String name;
    public int qty = 1;
    public String qtyMax = null;
    public String qtyMin = null;
    public String qtyType = "Static";
    public String positionName;

    public AssemblyPosition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AssemblyPosition(String name, String description, String moduleName) {
        this.name = name;
        this.description = description;
        this.positionName = moduleName;
    }

    public AssemblyPosition(String name, String description, String moduleName, POS_TYPE pos_type) {
        this.name = name;
        this.description = description;
        this.positionName = moduleName;
        this.positionType = pos_type;
    }

    @Override
    public String toString() {
        return name;
    }
}
