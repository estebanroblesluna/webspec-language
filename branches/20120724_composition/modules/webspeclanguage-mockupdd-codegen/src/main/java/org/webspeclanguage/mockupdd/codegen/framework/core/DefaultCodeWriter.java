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
package org.webspeclanguage.mockupdd.codegen.framework.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author Jose Matias Rivero
 */
public class DefaultCodeWriter extends AbstractCodeWriterImpl {

  private OutputStreamWriter outputStreamWriter;
  private IndentationStrategy indentationStrategy;

  public DefaultCodeWriter(OutputStream outputStream) {
    this(outputStream, new TabIndentationStrategy());
  }
  
  public DefaultCodeWriter() {
    this.setIndentationStrategy(new TabIndentationStrategy());
  }

  public DefaultCodeWriter(OutputStream outputStream, IndentationStrategy indentationStrategy) {
    super();
    this.setOutputStream(outputStream);
    this.setIndentationStrategy(indentationStrategy);
  }

  public final void setOutputStream(OutputStream outputStream) {
    if (this.outputStreamWriter != null) {
      try {
        this.outputStreamWriter.close();
      } catch (IOException e) {
      }
    }

    this.outputStreamWriter = new OutputStreamWriter(outputStream);
  }

  private OutputStreamWriter getOutputStreamWriter() {
    return outputStreamWriter;
  }

  @Override
  protected void writeIndent() {
    for (Integer i = 0; i < this.getIndent(); i++) {
      this.getIndentationStrategy().writeIndentation(this.getOutputStreamWriter());
    }
  }

  @Override
  protected void writeLine() {
    try {
      this.getOutputStreamWriter().append('\n');
    } catch (IOException e) {
    }
  }

  @Override
  protected void writeText(String text) {
    try {
      this.getOutputStreamWriter().append(text);
    } catch (IOException e) {
    }
  }

  public void close() {
    try {
      this.getOutputStreamWriter().close();
    } catch (IOException e) {
    }
  }

  private void setIndentationStrategy(IndentationStrategy indentationStrategy) {
    this.indentationStrategy = indentationStrategy;
  }

  private IndentationStrategy getIndentationStrategy() {
    return indentationStrategy;
  }

  public void flush() {
    try {
      this.getOutputStreamWriter().close();
    } catch (IOException e) { }
  }

}
