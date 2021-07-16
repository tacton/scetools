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

package com.tacton.convert.promo;

import com.tacton.convert.Languages;
import com.tacton.convert.promo.assembly.Assembly;
import com.tacton.convert.promo.assembly.AssemblyPosition;
import com.tacton.convert.promo.domains.Domain;
import com.tacton.convert.promo.domains.DomainElement;
import com.tacton.convert.promo.module.Module;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Functionality for making API calls to Tacton Administration API for uploading Assemblies, Domains and Modules.
 */
public class ProMoUploader {
    public String baseurl;
    public String username;
    public String password;
    public String ticketid;

    public ProMoUploader(String baseurl, String username, String password, String ticketid) {
        this.baseurl = baseurl;
        this.username = username;
        this.password = password;
        this.ticketid = ticketid;
    }

    private static OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .callTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build();

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public String createDomainJSON(List<Domain> domainEntries) {
        JSONObject domains = new JSONObject();
        JSONArray domainList = new JSONArray();
        domains.put("domainList", domainList);

        domainEntries.forEach(dom -> {
            JSONObject domain = new JSONObject();
            domain.put("name", dom.name);
            domain.put("description", dom.description);
            domain.put("type", dom.type);
            JSONArray elements = new JSONArray();
            domain.put("enumElementList", elements);
            Map<String, DomainElement> deduped = dom.elementList.stream().collect(Collectors.toMap(c -> c.name, c -> c, (a, b) -> a));
            deduped.values().forEach(el -> {
                JSONObject ent = new JSONObject();
                ent.put("name", el.name);
                ent.put("description", el.description);
                elements.put(ent);
            });
            domainList.put(domain);
        });
        return domains.toString();
    }

    public String createModulesJSON(List<Module> modules) {
        JSONObject root = new JSONObject();
        JSONArray moduleList = new JSONArray();
        root.put("moduleList", moduleList);
        JSONArray featureList = new JSONArray();
        root.put("featureList", featureList);
        JSONArray variantList = new JSONArray();
        root.put("variantList", variantList);

        modules.stream().filter(Objects::nonNull).forEach(module -> {
            // Module List
            JSONObject m = new JSONObject();
            m.put("name", module.name);
            m.put("description", module.description);
            moduleList.put(m);

            //Feature list
            module.featureList.forEach(feat -> {
                JSONObject feature = new JSONObject();
                JSONObject parentModuleNamedReference = new JSONObject();
                parentModuleNamedReference.put("name", module.name);
                feature.put("parentModuleNamedReference", parentModuleNamedReference);

                JSONObject domainNamedReference = new JSONObject();
                domainNamedReference.put("name", feat.domainReference);
                feature.put("domainNamedReference", domainNamedReference);

                feature.put("name", feat.name);

                featureList.put(feature);
            });

            AtomicInteger cnt = new AtomicInteger(100000);
            // VariantList
            module.variantList.forEach(var -> {
                JSONObject variant = new JSONObject();
                JSONObject parentModuleNamedReference = new JSONObject();
                variant.put("parentModuleNamedReference", parentModuleNamedReference);
                parentModuleNamedReference.put("name", module.name);
                cnt.getAndIncrement();
                variant.put("name", obf(module.name) + "_" + cnt);
                variant.put("description", var.name);
                variant.put("status", var.status);
                variant.put("variantPartList", new JSONArray());

                JSONArray valueList = new JSONArray();
                variant.put("variantValueList", valueList);
                var.values.forEach(val -> {
                    JSONObject jval = new JSONObject();
                    JSONObject featureNamedReference = new JSONObject();
                    featureNamedReference.put("name", val.featNamedRef);
                    jval.put("featureNamedReference", featureNamedReference);
                    jval.put("value", val.value);
                    valueList.put(jval);
                });
                variantList.put(variant);
            });
        });
        return root.toString();
    }

    public String createAssemblyJSON(boolean removeNotReplaced, List<Assembly> assemblies) {
        JSONObject root = new JSONObject();
        JSONArray assemblyList = new JSONArray();
        root.put("assemblyList", assemblyList);
        JSONArray attributeList = new JSONArray();
        root.put("attributeList", attributeList);
        JSONArray positionList = new JSONArray();
        root.put("positionList", positionList);
        JSONArray variantList = new JSONArray();
        root.put("variantList", variantList);
        root.put("removeNotReplaced", removeNotReplaced);

        assemblies.stream().filter(Objects::nonNull).forEach(assembly -> {
            // Module List
            JSONObject m = new JSONObject();
            m.put("name", assembly.name);
            m.put("description", assembly.description);
            if (assembly.descriptionTranslation.size() > 0) {
                JSONObject translations = new JSONObject();
                assembly.descriptionTranslation.forEach((l, d) -> translations.put(Languages.getISOLanguageFromSap(l).toLowerCase(), d));
                m.put("descriptionTranslations", translations);
            }
            assemblyList.put(m);

            //Feature list
            assembly.positionList.forEach(pos -> {
                JSONObject position = new JSONObject();
                JSONObject moduleNamedReference = null;
                if (pos.positionType == AssemblyPosition.POS_TYPE.MODULE) {
                    moduleNamedReference = new JSONObject();
                    moduleNamedReference.put("name", pos.positionName);
                }
                position.put("moduleNamedReference", moduleNamedReference);

                JSONObject assemblyNamedReference = null;
                if (pos.positionType == AssemblyPosition.POS_TYPE.ASSEMBLY) {
                    assemblyNamedReference = new JSONObject();
                    assemblyNamedReference.put("name", pos.name);
                }
                position.put("assemblyNamedReference", assemblyNamedReference);

                JSONObject parentAssemblyNamedReference = new JSONObject();
                parentAssemblyNamedReference.put("name", assembly.name);
                position.put("parentAssemblyNamedReference", parentAssemblyNamedReference);

                position.put("name", pos.name);
                position.put("description", pos.description);

                position.put("defaultView", pos.defaultView);
                position.put("dynamic", pos.dynamic);
                position.put("qty", pos.qty);
                position.put("qtyMax", pos.qtyMax);
                position.put("qtyMin", pos.qtyMin);
                position.put("qtyType", pos.qtyType);
                positionList.put(position);
            });

            // Assembly List
            assembly.attributeList.forEach(attr -> {
                JSONObject attribute = new JSONObject();
                JSONObject parentAssemblyNamedReference = new JSONObject();
                parentAssemblyNamedReference.put("name", assembly.name);
                attribute.put("parentAssemblyNamedReference", parentAssemblyNamedReference);

                if (attr.attributeCategoryNamedReference != null) {
                    JSONObject attributeCategoryNamedReference = new JSONObject();
                    attributeCategoryNamedReference.put("name", attr.attributeCategoryNamedReference);
                    attribute.put("attributeCategoryNamedReference", attributeCategoryNamedReference);
                }

                if (attr.domainNamedReference != null) {
                    JSONObject domainNamedReference = new JSONObject();
                    domainNamedReference.put("name", attr.domainNamedReference);
                    attribute.put("domainNamedReference", domainNamedReference);
                }
                attribute.put("name", attr.name);
                attribute.put("description", attr.description);
                attribute.put("io", attr.io);
                attribute.put("defaultView", attr.io);
                if (attr.descriptionTranslation.size() > 0) {
                    JSONObject translations = new JSONObject();
                    attr.descriptionTranslation.forEach((l, d) -> translations.put(Languages.getISOLanguageFromSap(l).toLowerCase(), d));
                    attribute.put("descriptionTranslations", translations);
                }

                switch (attr.aggregateStrategy) {
                    case EQUAL:
                        attribute.put("aggregationStrategy", "Equal");
                        break;
                    case SUM:
                        attribute.put("aggregationStrategy", "Sum");
                        break;
                    case MIN:
                        attribute.put("aggregationStrategy", "Min");
                        break;
                    case MAX:
                        attribute.put("aggregationStrategy", "Max");
                        break;
                    default:
                        attribute.put("aggregationStrategy", "None");
                }

                JSONArray aggregateList = new JSONArray();
                attribute.put("aggregateList", aggregateList);

                attr.aggregateList.forEach(agg -> {
                    JSONObject aggr = new JSONObject();
                    if (agg.attributeName != null) {
                        JSONObject attributeNamedReference = new JSONObject();
                        attributeNamedReference.put("name", agg.attributeName);
                        aggr.put("attributeNamedReference", attributeNamedReference);
                    }
                    if (agg.featureName != null) {
                        JSONObject featureNamedReference = new JSONObject();
                        featureNamedReference.put("name", agg.featureName);
                        aggr.put("featureNamedReference", featureNamedReference);
                    }
                    if (agg.positionName != null) {
                        JSONObject positionNamedReference = new JSONObject();
                        positionNamedReference.put("name", agg.positionName);
                        aggr.put("positionNamedReference", positionNamedReference);
                    }
                    aggregateList.put(aggr);
                });
                attributeList.put(attribute);
            });
        });
        return root.toString();
    }

    public static String postAssemblies(ProMoBuilder proMoBuilder, ProMoUploader helper) throws IOException {
        return helper.post("assembly/list", helper.createAssemblyJSON(false, proMoBuilder.assemblyList));
    }

    public static String postModules(ProMoBuilder proMoBuilder, ProMoUploader helper) throws IOException {
        return helper.post("module/list", helper.createModulesJSON(proMoBuilder.moduleList));
    }

    public static String postDomains(ProMoBuilder proMoBuilder, ProMoUploader helper) throws IOException {
        return helper.post("domain/list", helper.createDomainJSON(proMoBuilder.domainList));
    }

    public String post(String uri, String json) throws IOException {
        Request request = new Request.Builder()
                .url(getV2Url(uri))
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Basic " + getAuth())
                .post(RequestBody.create(json.getBytes(StandardCharsets.UTF_8), JSON))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String getV2Url(String uri) {
        return baseurl + "/api/product-modeling/v2/" + ticketid + "/" + uri;
    }

    private String getAuth() {
        return new String(Base64.getEncoder().encode((username + ":" + password).getBytes(StandardCharsets.UTF_8)));
    }

    public static String obf(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "_");
    }
}
