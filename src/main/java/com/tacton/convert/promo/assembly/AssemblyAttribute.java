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

public class AssemblyAttribute {
    public enum AGGREGATE_STRATEGY {EQUAL, SUM, MIN, MAX, NONE}

    //public String parentAssemblyName;
    public String attributeCategoryNamedReference;
    public String domainNamedReference;

    public String name;
    public String description;
    public Map<String, String> descriptionTranslation = new HashMap<>();
    public boolean io = true;
    public boolean defaultView = true;
    public AGGREGATE_STRATEGY aggregateStrategy = AGGREGATE_STRATEGY.NONE;
    public List<AssemblyAttributeAggregate> aggregateList = new ArrayList<>();

    public AssemblyAttribute(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AssemblyAttribute withTranslation(String languageCode, String description) {
        descriptionTranslation.put(languageCode, description);
        return this;
    }

    public AssemblyAttribute withAggregate(AssemblyAttributeAggregate aggregate) {
        this.aggregateList.add(aggregate);
        return this;
    }

    public AssemblyAttribute withStrategy(AGGREGATE_STRATEGY str) {
        this.aggregateStrategy = str;
        return this;
    }

    public AssemblyAttribute withIO(boolean io) {
        this.io = io;
        return this;
    }

    public AssemblyAttribute withDefaultView(boolean dv) {
        this.defaultView = dv;
        return this;
    }

    public AssemblyAttribute withDomainName(String name) {
        this.domainNamedReference = name;
        return this;
    }

    public AssemblyAttribute withCategoryName(String name) {
        this.attributeCategoryNamedReference = name;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
