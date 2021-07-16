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

package com.tacton.convert.sap;

import com.tacton.convert.sap.dao.sce.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Representation of a Knowledge Base dump as produced by the CU36 transaction code.
 */
public class SCEImport {
    public List<SCEChaDep> chadep;
    public List<SCEChaInp> chainp;
    public List<SCEChaPos> chapos;
    public List<SCECharac> charac;
    public List<SCECharef> charef;
    public List<SCEChaTx> chatx;
    public List<SCEChavis> chavis;
    public List<SCEClaCom> clacom;
    public List<SCEClnDom> clndom;
    public List<SCEClprec> clprec;
    public List<SCEClsDom> clsdom;
    public List<SCEClval> clval;
    public List<SCECnt> cnt;
    public List<SCECntDep> cntdep;
    public List<SCECnttx> cnttx;
    public List<SCEConfPr> confpr;
    public List<SCECuco> cuco;
    public List<SCECuhd> cuhd;
    public List<SCECusd> cusd;
    public List<SCECusdt> cusdt;
    public List<SCEDecomp> decomp;
    public List<SCEDep> dep;
    public List<SCEDepCom> depcom;
    public List<SCEDepDnn> depdnn;
    public List<SCEDepDoc> depdoc;
    public List<SCEDepPat> deppat;
    public List<SCEDepSpn> depspn;
    public List<SCEDepSrc> depsrc;
    public List<SCEDepTx> deptx;
    public List<SCEDepVar> depvar;
    public List<SCEDocObj> docobj;
    public List<SCEHasCha> hascha;
    public List<SCEHasDec> hasdec;
    public List<SCEHasPrt> hasprt;
    public List<SCEInView> inview;
    public List<SCEKb> kb;
    public List<SCEKbTx> kbtx;
    public List<SCEKmtDep> kmtdep;
    public List<SCELngTxt> lngtxt;
    public List<SCEMat> mat;
    public List<SCENum> num;
    public List<SCENumDom> numdom;
    public List<SCEOClass> oclass;
    public List<SCEOclTx> ocltx;
    public List<SCEProf> prof;
    public List<SCEProfTx> proftx;
    public List<SCEPrtDep> prtdep;
    public List<SCEStrDom> strdom;
    public List<SCEString> string;
    public List<SCESuper> zuper;
    public List<SCETabFun> tabfun;
    public List<SCEVaCond> vacond;
    public List<SCEValDep> valdep;
    public List<SCEValSym> valsym;
    public List<SCEValTx> valtx;

    public List<VariantTable> variantTables;

    public static SCEImport readFiles(String path) throws IOException {
        SCEImport dump = new SCEImport();
        dump.chadep = readType(SCEChaDep::of, path, "scechadep.txt");
        dump.chainp = readType(SCEChaInp::of, path, "scechainp.txt");
        dump.chapos = readType(SCEChaPos::of, path, "scechapos.txt");
        dump.charac = readType(SCECharac::of, path, "scecharac.txt");
        dump.charef = readType(SCECharef::of, path, "scecharef.txt");
        dump.chatx = readType(SCEChaTx::of, path, "scechatx.txt");
        dump.chavis = readType(SCEChavis::of, path, "scechavis.txt");
        dump.clacom = readType(SCEClaCom::of, path, "sceclacom.txt");
        dump.clndom = readType(SCEClnDom::of, path, "sceclndom.txt");
        dump.clprec = readType(SCEClprec::of, path, "sceclprec.txt");
        dump.clsdom = readType(SCEClsDom::of, path, "sceclsdom.txt");
        dump.clval = readType(SCEClval::of, path, "sceclval.txt");
        dump.cnt = readType(SCECnt::of, path, "scecnt.txt");
        dump.cntdep = readType(SCECntDep::of, path, "scecntdep.txt");
        dump.cnttx = readType(SCECnttx::of, path, "scecnttx.txt");
        dump.confpr = readType(SCEConfPr::of, path, "sceconfpr.txt");
        dump.cuco = readType(SCECuco::of, path, "scecuco.txt");
        dump.cuhd = readType(SCECuhd::of, path, "scecuhd.txt");
        dump.cusd = readType(SCECusd::of, path, "scecusd.txt");
        dump.cusdt = readType(SCECusdt::of, path, "scecusdt.txt");
        dump.decomp = readType(SCEDecomp::of, path, "scedecomp.txt");
        dump.dep = readType(SCEDep::of, path, "scedep.txt");
        dump.depcom = readType(SCEDepCom::of, path, "scedepcom.txt");
        dump.depdnn = readType(SCEDepDnn::of, path, "scedepdnn.txt");
        dump.depdoc = readType(SCEDepDoc::of, path, "scedepdoc.txt");
        dump.deppat = readType(SCEDepPat::of, path, "scedeppat.txt");
        dump.depspn = readType(SCEDepSpn::of, path, "scedepspn.txt");
        dump.depsrc = readType(SCEDepSrc::of, path, "scedepsrc.txt");
        dump.deptx = readType(SCEDepTx::of, path, "scedeptx.txt");
        dump.depvar = readType(SCEDepVar::of, path, "scedepvar.txt");
        dump.docobj = readType(SCEDocObj::of, path, "scedocobj.txt");
        dump.hascha = readType(SCEHasCha::of, path, "scehascha.txt");
        dump.hasdec = readType(SCEHasDec::of, path, "scehasdec.txt");
        dump.hasprt = readType(SCEHasPrt::of, path, "scehasprt.txt");
        dump.inview = readType(SCEInView::of, path, "sceinview.txt");
        dump.kb = readType(SCEKb::of, path, "scekb.txt");
        dump.kbtx = readType(SCEKbTx::of, path, "scekbtx.txt");
        dump.kmtdep = readType(SCEKmtDep::of, path, "scekmtdep.txt");
        dump.lngtxt = readType(SCELngTxt::of, path, "scelngtxt.txt");
        dump.mat = readType(SCEMat::of, path, "scemat.txt");
        dump.num = readType(SCENum::of, path, "scenum.txt");
        dump.numdom = readType(SCENumDom::of, path, "scenumdom.txt");
        dump.oclass = readType(SCEOClass::of, path, "sceoclass.txt");
        dump.ocltx = readType(SCEOclTx::of, path, "sceocltx.txt");
        dump.prof = readType(SCEProf::of, path, "sceprof.txt");
        dump.proftx = readType(SCEProfTx::of, path, "sceproftx.txt");
        dump.prtdep = readType(SCEPrtDep::of, path, "sceprtdep.txt");
        dump.strdom = readType(SCEStrDom::of, path, "scestrdom.txt");
        dump.string = readType(SCEString::of, path, "scestring.txt");
        dump.zuper = readType(SCESuper::of, path, "scesuper.txt");
        dump.tabfun = readType(SCETabFun::of, path, "scetabfun.txt");
        dump.vacond = readType(SCEVaCond::of, path, "scevacond.txt");
        dump.valdep = readType(SCEValDep::of, path, "scevaldep.txt");
        dump.valsym = readType(SCEValSym::of, path, "scevalsym.txt");
        dump.valtx = readType(SCEValTx::of, path, "scevaltx.txt");

        dump.variantTables = readVariantTables(dump, path);
        return dump;
    }

    private static <T> List<T> readType(Function<String, T> of, String path, String filename) throws IOException {
        return readFile(path, filename).stream().map(of).collect(Collectors.toList());
    }

    private static List<VariantTable> readVariantTables(SCEImport sceImport, String path) throws IOException {
        List<VariantTable> variantTables = new ArrayList<>();

        // OClasses of object type 3 are variant tables
        for (SCEOClass c : sceImport.oclass.stream().filter(c -> c.objtype == SCEOClass.OBJECT_TYPE_VARIANT_TABLE).collect(Collectors.toList())) {
            VariantTable variantTable = new VariantTable(c.objname);
            variantTables.add(variantTable);

            // Find the characteristics connected with this OClass (via chapos to get them in the right order),
            // these characteristics are columns in the table.
            sceImport.chapos.stream().filter(r -> r.objid == c.objid).sorted(Comparator.comparingInt(r -> r.posnr)).forEach(r -> {
                SCECharac chr = sceImport.charac.stream().filter(ch -> ch.charid == r.charid).findFirst().orElseThrow(() -> new RuntimeException("missing characteristic: " + r.charid));
                variantTable.columns.add(chr);
            });

            // Find the data file dump based on the naming pattern
            String dataFileName = c.objname + "_" + c.kbid + ".tmp";
            List<String> tableData = readFile(path, dataFileName);

            // Read all the rows, each "cell value" is on its own line in the file
            List<String> row = new ArrayList<>();
            for (String cell : tableData) {
                row.add(cell);
                // enough cells to switch to the next row?
                if (row.size() == variantTable.columns.size()) {
                    variantTable.data.add(row);
                    row = new ArrayList<>();
                }
            }
        }

        return variantTables;
    }

    private static List<String> readFile(String path, String filename) throws IOException {
        Path filepath = Paths.get(path, filename);
        if (!Files.exists(filepath)) return Collections.emptyList();
        return Files.readAllLines(filepath, StandardCharsets.ISO_8859_1);
    }
}
