package org.webspeclanguage.metamock.codegen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;

import org.webspeclanguage.metamock.codegen.artifacts.Code;
import org.webspeclanguage.metamock.codegen.framework.core.DefaultCodeWriter;
import org.webspeclanguage.metamock.codegen.framework.core.IndentationStrategy;
import org.webspeclanguage.metamock.codegen.framework.core.SpaceIndentationStrategy;
import org.webspeclanguage.metamock.codegen.framework.core.TabIndentationStrategy;

/**
 * @author Jose Matias Rivero
 */
public class CodegenTestCase extends TestCase {

  private ByteArrayOutputStream outputStream;
  private DefaultCodeWriter codeWriter;

  public void testLine() {
    this.initializeWriter();
    Code.line().writeOn(codeWriter);
    Code.line("line").writeOn(codeWriter);
    this.assertResultingStream(outputStream, "\nline\n");
  }
  
  public void testText() {
    this.initializeWriter();
    Code.text("abc").writeOn(codeWriter);
    Code.text("def").writeOn(codeWriter);
    this.assertResultingStream(outputStream, "abcdef");
  }
  
  public void testCodeBlock() {
    this.initializeWriter();
    Code.block(
      Code.text("abc"),
      Code.text("def")
    ).writeOn(codeWriter);
    this.assertResultingStream(outputStream, "abcdef");
  }
  
  public void testMixedBlock() {
    this.initializeWriter();
    Code.mixedBlock(
      "abc",
      Code.text("def"),
      Arrays.asList(Code.text("g"), Code.text("h"))
    ).writeOn(codeWriter);
    this.assertResultingStream(outputStream, "abc\ndefgh");
  }
  
  public void testTabIndent() {
    this.initializeWriter(new TabIndentationStrategy());
    Code.mixedBlock(
      "abc",
      Code.indent(Code.block(
        Code.line("d"),
        Code.line("e")
      ), 1),
      "f"
    ).writeOn(codeWriter);
    this.assertResultingStream(outputStream, "abc\n\td\n\te\nf\n");
  }
  
  public void testSpaceIndent() {
    this.initializeWriter(new SpaceIndentationStrategy(2));
    Code.mixedBlock(
      "abc",
      Code.indent(Code.block(
        Code.line("d"),
        Code.line("e")
      ), 1),
      "f"
    ).writeOn(codeWriter);
    this.assertResultingStream(outputStream, "abc\n  d\n  e\nf\n");
  }

  private void initializeWriter() {
    outputStream = new ByteArrayOutputStream();
    codeWriter = new DefaultCodeWriter(outputStream);
  }
  
  private void initializeWriter(IndentationStrategy indentationStrategy) {
    outputStream = new ByteArrayOutputStream();
    codeWriter = new DefaultCodeWriter(outputStream, indentationStrategy);
  }

  private void assertResultingStream(ByteArrayOutputStream os, String resultingString) {
    try {
      codeWriter.close();
      os.close();
      byte[] bytes = os.toByteArray();
      assertEquals(resultingString.length(), bytes.length);
      for (int i = 0; i < os.size(); i++) {
        assertEquals(resultingString.charAt(i), bytes[i]);
      }
    } catch (IOException e) {
      fail();
    }
  }
  
  
}
