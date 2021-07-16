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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assembly {
    public String name;
    public String description;
    public Map<String, String> descriptionTranslation = new HashMap<>();
    public String ref;
    public List<AssemblyPosition> positionList = new ArrayList<>();
    public List<AssemblyAttribute> attributeList = new ArrayList<>();

    public Assembly(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Assembly withTranslation(String languageCode, String description) {
        descriptionTranslation.put(languageCode, description);
        return this;
    }

    public Assembly withPosition(AssemblyPosition position) {
        positionList.add(position);
        return this;
    }

    public Assembly withAttribute(AssemblyAttribute attribute) {
        attributeList.add(attribute);
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
