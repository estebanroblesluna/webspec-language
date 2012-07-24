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
package org.webspeclanguage.webtest.generator.watir.java;

/**
 * Class Builder for Ruby Language 
 * 
 * @author Gonzalo G. Testa
 */
public class ClassBuilder {
  private StringBuffer classCodeBuffer;

  private void setClassCodeBuffer(StringBuffer classCodeBuffer) {
    this.classCodeBuffer = classCodeBuffer;
  }

  private StringBuffer getClassCodeBuffer() {
    return classCodeBuffer;
  }

  public ClassBuilder() {
    this.setClassCodeBuffer(new StringBuffer());
  }

  public String getClassCode(){
    return this.getClassCodeBuffer().toString();
  }

  public void newLine(){
    this.getClassCodeBuffer().append("\n");
  }

  public void newLine(int n) {
    for(int i=0;i<n;i++)
      this.newLine();
  }

  public void newTab(){
    this.getClassCodeBuffer().append("\t");
  }

  public void newTab(int n){
    for(int i=0;i<n;i++)
      this.newTab();
  }

  // class definition

  // start the class
  public void setRequired(String required){
    if (!required.isEmpty()){
      this.getClassCodeBuffer().append("require \"" + required + "\"");
      this.newLine();
    }
  }

  public void startClass(String require, String className,
          String extendsImplements){

    if (require != null){     
      String[] requires = require.split(",");


      for (String r : requires){
        this.setRequired(r.trim());
      }
      this.newLine();
    }

    this.getClassCodeBuffer().append("class ");
    this.getClassCodeBuffer().append(className.replace(" ", "_"));

    if (extendsImplements != null){

      if (!extendsImplements.isEmpty()){
        this.getClassCodeBuffer().append(" < ");
        this.getClassCodeBuffer().append(extendsImplements);

      }
    }

    this.newLine(2);

  }

  // end class
  public void endClass() {

    this.getClassCodeBuffer().append("end");
    this.newLine();
  }

  ////////////////////////////////////////////

  // method definition


  //start method
  public void startMethod(String methodName) {
    this.newTab();
    this.getClassCodeBuffer().append("def ");
    this.getClassCodeBuffer().append(methodName.replace(" ", "_"));
    this.newLine();
  }

  // complete method
  public void setMethodStatement(String statement) {
    this.newTab(2);
    this.classCodeBuffer.append(statement);
    this.newLine();
  }

  //end method
  public void endMethod() {
    this.newTab();
    this.getClassCodeBuffer().append("end");
    this.newLine(2);
  }

  public void addStatementAndNewLine(String statement) {
    if (!statement.equals("")){
      this.addStatement(statement);
      this.newLine();
    }
  }

  public void addStatement(String statement) {
    this.getClassCodeBuffer().append("\t\t");
    this.getClassCodeBuffer().append(statement);
  }
}
