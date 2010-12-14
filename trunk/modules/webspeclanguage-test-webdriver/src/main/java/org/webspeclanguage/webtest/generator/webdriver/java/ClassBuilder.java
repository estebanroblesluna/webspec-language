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
package org.webspeclanguage.webtest.generator.webdriver.java;

/**
 * A Java class builder
 * 
 * @author Esteban Robles Luna
 */
public class ClassBuilder {

  private StringBuffer classCodeBuffer;

  public ClassBuilder() {
    this.setClassCodeBuffer(new StringBuffer());
  }

  public String getClassCode() {
    return this.getClassCodeBuffer().toString();
  }

  public void startClass(String packageName, String imports, String className,
      String extendsImplements) {
    if (packageName != null && !packageName.isEmpty()) {
      this.getClassCodeBuffer().append("package ");
      this.getClassCodeBuffer().append(packageName);
      this.getClassCodeBuffer().append(";\n\n");
    }

    this.getClassCodeBuffer().append(imports);
    this.getClassCodeBuffer().append("\n");

    this.getClassCodeBuffer().append("public class ");
    this.getClassCodeBuffer().append(className.replace(" ", "_"));
    this.getClassCodeBuffer().append(" " + extendsImplements);
    this.getClassCodeBuffer().append(" {\n\n");
  }

  public void startMethod(String methodName, String exceptions) {
    this.getClassCodeBuffer().append("\tpublic void ");
    this.getClassCodeBuffer().append(methodName);
    this.getClassCodeBuffer().append("()");
    if (exceptions != null) {
      this.getClassCodeBuffer().append(" throws " + exceptions);
    }
    this.getClassCodeBuffer().append(" {\n");
  }

  public void addStatement(String statement) {
    this.getClassCodeBuffer().append("\t\t");
    this.getClassCodeBuffer().append(statement);
  }

  public void addStatementAndNewLine(String statement) {
    this.addStatement(statement);
    this.newLine();
  }

  public void newLine() {
    this.getClassCodeBuffer().append("\n");
  }

  public void endClass() {
    this.getClassCodeBuffer().append("}");
  }

  public void endMethod() {
    this.getClassCodeBuffer().append("\t}\n\n");
  }

  private StringBuffer getClassCodeBuffer() {
    return classCodeBuffer;
  }

  private void setClassCodeBuffer(StringBuffer classCodeBuffer) {
    this.classCodeBuffer = classCodeBuffer;
  }
}
