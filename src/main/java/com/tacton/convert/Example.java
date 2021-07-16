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

package com.tacton.convert;

import com.tacton.convert.promo.ProMoBuilder;
import com.tacton.convert.promo.ProMoUploader;
import com.tacton.convert.sap.SCEImport;
import com.tacton.convert.sap.SCEModel;
import com.tacton.convert.tcx.TCXBuilder;

import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {

        // read the dump from a directory
        String dumpDirectory = "c:\\temp";
        SCEImport sceImport = SCEImport.readFiles(dumpDirectory);

        // interpret the dump into configurable materials and characteristics etc.
        SCEModel sceModel = new SCEModel(sceImport);

        // build a tcx model for TCstudio
        TCXBuilder tcxBuilder = new TCXBuilder(sceModel);
        String tcxData = tcxBuilder.generateTCX();
        // todo: write tcx file

        // alternatively, build a model for Tacton CPQ Product Modeling
        ProMoBuilder proMoBuilder = new ProMoBuilder(sceModel);

        // upload everything to CPQ
        String baseurl = "https://MY-DOMAIN-NAME";
        String username = "USERNAME";
        String password = "PASSWORD";
        String ticketId = "TICKETID";
        ProMoUploader uploader = new ProMoUploader(baseurl, username, password, ticketId);
        ProMoUploader.postDomains(proMoBuilder, uploader);
        ProMoUploader.postModules(proMoBuilder, uploader);
        ProMoUploader.postAssemblies(proMoBuilder, uploader);
    }
}
