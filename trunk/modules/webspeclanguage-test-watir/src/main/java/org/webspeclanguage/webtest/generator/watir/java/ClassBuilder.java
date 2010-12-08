package org.webspeclanguage.webtest.generator.watir.java;


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
//    this.startMethod("setup");
//    this.setMethodStatement("$browser = Watir::Browser.new()");
//    this.endMethod();
    
    
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
