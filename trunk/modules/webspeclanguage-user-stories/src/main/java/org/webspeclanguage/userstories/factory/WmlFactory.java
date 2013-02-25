/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.webspeclanguage.userstories.factory;

/**
 * Factory class that wrap docx4j's library word factory.
 * @author cristian.cianfagna
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTVerticalJc;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.ParaRPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblGrid;
import org.docx4j.wml.TblGridCol;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.Tr;
import org.docx4j.wml.PPrBase.Ind;
import org.docx4j.wml.PPrBase.NumPr;
import org.docx4j.wml.PPrBase.PStyle;
import org.docx4j.wml.PPrBase.NumPr.Ilvl;
import org.docx4j.wml.PPrBase.NumPr.NumId;

public class WmlFactory {

  private static final WmlFactory instance;

  private final static String TWIPS_UNIT_TYPE = "dxa";
  private final static String LIST_OF_PARAGRAPH = "ListParagraph";
  private final static String AUTO_COLOR = "auto";
  private ObjectFactory wmlObjectFactory;

  static {
    instance = new WmlFactory(Context.getWmlObjectFactory());
  }

  private WmlFactory(ObjectFactory wmlObjectFactory) {
    this.setWmlObjectFactory(wmlObjectFactory);
  }

  public static WmlFactory getInstance() {
    return instance;
  }

  public P createP() {
    return this.getWmlObjectFactory().createP();
  }
  public P createP(String text, boolean bold, long fontSize) {
    P p = this.getWmlObjectFactory().createP();
    org.docx4j.wml.Text t = this.getWmlObjectFactory().createText();
    t.setValue(text);
    org.docx4j.wml.R run = this.getWmlObjectFactory().createR();
    run.getContent().add(t);
    p.getContent().add(run);

    HpsMeasure hpsMeasure = new HpsMeasure();
    hpsMeasure.setVal(BigInteger.valueOf(fontSize));
    RPr rpr = new RPr();
    rpr.setSz(hpsMeasure);
    if (bold) {
      rpr.setB(new BooleanDefaultTrue());
    }
    run.setRPr(rpr);
    return p;
  }

  public P createP(String text) {
    P p = this.getWmlObjectFactory().createP();
    org.docx4j.wml.Text t = this.getWmlObjectFactory().createText();
    t.setValue(text);
    org.docx4j.wml.R run = this.getWmlObjectFactory().createR();
    run.getContent().add(t);
    p.getContent().add(run);
    return p;
  }

  public P createP(String text, long paddingLeft) {
    P p = this.createP(text);
    PPr pPr = getWmlObjectFactory().createPPr();
    Ind ind = new Ind();
    ind.setLeft(BigInteger.valueOf(paddingLeft));
    pPr.setInd(ind);
    p.setPPr(pPr);
    return p;
  }

  public Tc createTC(String cellTextContent, int twips) {
    P p = this.createP(cellTextContent);
    return this.createTC(p, twips);
  }

  public Tc createTC(P p, int twips) {
    Tc tc = this.getWmlObjectFactory().createTc();
    TcPr tcPr = this.getWmlObjectFactory().createTcPr();
    tc.setTcPr(tcPr);
    TblWidth cellWidth = this.getWmlObjectFactory().createTblWidth();
    tcPr.setTcW(cellWidth);
    cellWidth.setType(TWIPS_UNIT_TYPE);
    cellWidth.setW(BigInteger.valueOf(twips));
    tc.getContent().add(p);
    return tc;
  }

  public Tc createTC(P p, JcEnumeration jcHorizontalAlignmentContent, STVerticalJc stVerticalJcAlignmentContent, int twips) {
    PPr ppr = this.getWmlObjectFactory().createPPr();
    Jc jc = new Jc();
    jc.setVal(jcHorizontalAlignmentContent);
    ppr.setJc(jc);
    p.setPPr(ppr);
    return this.createTC(p, stVerticalJcAlignmentContent, twips);
  }

  public Tc createTC(String text, JcEnumeration jcHorizontalAlignmentContent, STVerticalJc stVerticalJcAlignmentContent, int twips) {
    P p = this.createP(text);
    return this.createTC(p, jcHorizontalAlignmentContent, stVerticalJcAlignmentContent, twips);
  }

  public Tc createTC(String text, JcEnumeration jcHorizontalAlignmentContent, STVerticalJc stVerticalJcAlignmentContent, int twips, boolean bold, long fontsize) {
    P p = this.createP(text, bold, fontsize);
    return this.createTC(p, jcHorizontalAlignmentContent, stVerticalJcAlignmentContent, twips);
  }

  public P getImage(WordprocessingMLPackage wordprocessingMLPackage, File file, JcEnumeration jcHorizontalAlignmentContent) throws Exception {
    P pimage = this.getImage(wordprocessingMLPackage, file, 0);
    Jc jc = new Jc();
    jc.setVal(JcEnumeration.CENTER);
    pimage.getPPr().setJc(jc);
    return pimage;
  }

  public P getImage(WordprocessingMLPackage wordprocessingMLPackage, ByteArrayOutputStream byteArrayOutputStream, long widthInTwips) throws Exception {
    return this.getBaseImage(wordprocessingMLPackage, byteArrayOutputStream.toByteArray(), 0, widthInTwips);
  }

  private P getBaseImage(WordprocessingMLPackage wordprocessingMLPackage, byte[] bytes, long paddingLeft, long widthInTwips) throws Exception {
    BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordprocessingMLPackage, bytes);
    Inline inline;
    if (widthInTwips == 0) {
    	inline = imagePart.createImageInline(null, null, 1, 2, false);
    } else {
    	inline = imagePart.createImageInline(null, null, 1, 2, widthInTwips, false);
    }

    PPr pPr = getWmlObjectFactory().createPPr();
    PStyle pStyle = new PStyle();
    pStyle.setVal("ListParagraph");
    pPr.setPStyle(pStyle);
    Ind ind = new Ind();
    ind.setLeft(BigInteger.valueOf(paddingLeft));
    pPr.setInd(ind);

    ParaRPr rPr = getWmlObjectFactory().createParaRPr();
    rPr.setB(new BooleanDefaultTrue());
    pPr.setRPr(rPr);

    P p = getWmlObjectFactory().createP();
    p.setPPr(pPr);
    R run = getWmlObjectFactory().createR();
    p.getContent().add(run);
    Drawing drawing = getWmlObjectFactory().createDrawing();
    run.getContent().add(drawing);
    drawing.getAnchorOrInline().add(inline);

    return p;
  }

  public P getImage(WordprocessingMLPackage wordprocessingMLPackage, File file, long paddingLeft) throws Exception {
	  return getImage(wordprocessingMLPackage, file, paddingLeft, 0);
  }
  
  public P getImage(WordprocessingMLPackage wordprocessingMLPackage, File file, long paddingLeft, long widthInTwips) throws Exception {
    FileInputStream fis = new FileInputStream(file);
    byte diagramBytes[] = new byte[(int) file.length()];
    fis.read(diagramBytes);
    return this.getBaseImage(wordprocessingMLPackage, diagramBytes, paddingLeft, widthInTwips);
  }

  public P getImage(WordprocessingMLPackage wordprocessingMLPackage, ByteArrayOutputStream byteArrayOutputStream, long paddingLeft, long widthInTwips) throws Exception {
    return this.getBaseImage(wordprocessingMLPackage, byteArrayOutputStream.toByteArray(), paddingLeft, widthInTwips);
  }

  public Tc createTC(P p, STVerticalJc stVerticalJcAlignmentContent, int twips) {
    CTVerticalJc cTVerticalJc = new CTVerticalJc();
    cTVerticalJc.setVal(stVerticalJcAlignmentContent);
    Tc tc = createTC(p, twips);
    tc.getTcPr().setVAlign(cTVerticalJc);
    return tc;
  }

  public Tbl createTbl() {
    Tbl tbl = this.getWmlObjectFactory().createTbl();
    TblPr tblPr = this.getWmlObjectFactory().createTblPr();
    TblBorders tblBorders = this.getWmlObjectFactory().createTblBorders();
    CTBorder cTBorder = new CTBorder();
    cTBorder.setColor(AUTO_COLOR);
    cTBorder.setSpace(BigInteger.ZERO);
    cTBorder.setSz(BigInteger.valueOf(2));
    cTBorder.setVal(STBorder.SINGLE);

    CTBorder insidecTBorder = new org.docx4j.wml.CTBorder();
    insidecTBorder.setColor(AUTO_COLOR);
    insidecTBorder.setSpace(BigInteger.ZERO);
    insidecTBorder.setSz(BigInteger.valueOf(3));
    insidecTBorder.setVal(STBorder.SINGLE);

    tblBorders.setTop(cTBorder);
    tblBorders.setBottom(cTBorder);
    tblBorders.setLeft(cTBorder);
    tblBorders.setRight(cTBorder);
    tblBorders.setInsideH(insidecTBorder);
    tblBorders.setInsideV(insidecTBorder);

    tblPr.setTblBorders(tblBorders);

    Jc jc = new Jc();
    jc.setVal(JcEnumeration.CENTER);
    tblPr.setJc(jc);

    tbl.setTblPr(tblPr);
    return tbl;
  }

  public TblGrid createTblGrid() {
    return this.getWmlObjectFactory().createTblGrid();
  }

  public TblGridCol createTblGridCol() {
    return this.getWmlObjectFactory().createTblGridCol();
  }

  public Tr createTr() {
    return this.getWmlObjectFactory().createTr();
  }

  public PPr createPPr() {
    return this.getWmlObjectFactory().createPPr();
  }

  public P createNumberingP(long actionNumber, String text) {
    P p = this.createP(text);
    return this.createNumberingP(actionNumber, p);
  }

  public P createNumberingP(long numberingId, long level, String text) {
    return this.createNumberingP(numberingId, level, this.createP(text));
  }

  public P createNumberingP(long numberingId, long level, String text, boolean bold, long numberingSize) {
    P numberingP = this.createNumberingP(numberingId, level, this.createP(text, bold, numberingSize));
    PPr pPr = numberingP.getPPr();
    ParaRPr paraRPr = new ParaRPr();
    if (bold) {
      paraRPr.setB(new BooleanDefaultTrue());
    }
    HpsMeasure hpsMeasure = new HpsMeasure();
    hpsMeasure.setVal(BigInteger.valueOf(numberingSize));
    paraRPr.setSz(hpsMeasure);
    pPr.setRPr(paraRPr);
    return numberingP;
  }

  private P createNumberingP(long numberingId, P p) {
    return this.createNumberingP(numberingId, 0, p);
  }

  public P createNumberingP(long numberingId, long level, P paragraph) {
    org.docx4j.wml.PPr ppr = this.getWmlObjectFactory().createPPr();
    paragraph.setPPr(ppr);

    PStyle pStyle = this.getWmlObjectFactory().createPPrBasePStyle();
    pStyle.setVal(LIST_OF_PARAGRAPH);
    ppr.setPStyle(pStyle);

    NumPr numPr = this.getWmlObjectFactory().createPPrBaseNumPr();
    ppr.setNumPr(numPr);

    Ilvl ilvlElement = this.getWmlObjectFactory().createPPrBaseNumPrIlvl();
    numPr.setIlvl(ilvlElement);
    ilvlElement.setVal(BigInteger.valueOf(level));

    NumId numIdElement = this.getWmlObjectFactory().createPPrBaseNumPrNumId();
    numPr.setNumId(numIdElement);
    numIdElement.setVal(BigInteger.valueOf(numberingId));

    return paragraph;
  }

  public P getNewPage() {
    P p = this.getWmlObjectFactory().createP();
    R r = this.getWmlObjectFactory().createR();
    Br br = this.getWmlObjectFactory().createBr();
    br.setType(STBrType.PAGE);
    r.getContent().add(br);
    p.getContent().add(r);
    return p;
  }

  private ObjectFactory getWmlObjectFactory() {
    return wmlObjectFactory;
  }

  private void setWmlObjectFactory(ObjectFactory wmlObjectFactory) {
    this.wmlObjectFactory = wmlObjectFactory;
  }

}
