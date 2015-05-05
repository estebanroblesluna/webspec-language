# WebDriver Tests #

This wiki page describes how to execute the WebDriver Tests.


## What's needed? ##

To execute WebDriver test cases, it's needed: WebDriver library, it can be found: http://code.google.com/p/selenium/downloads/list

The file to download is selenium-server-2.0a7.zip.

## Executing Test Cases from Maven ##

To execute WebDriver test cases from Maven is needed to add the following lines to **pom.xml** file.

Into the repository section:
```
<repository>
  <id>selenium-repository</id>
  <url>http://selenium.googlecode.com/svn/repository/</url>
</repository>
```

Into the dependencies section:
```
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium</artifactId>
  <version>2.0a4</version>
</dependency>
```

After adding these lines to the pom, the test cases can be executed.

The following test case executes a WebDriver instance, it goes to Google page and executes a search:
_(it makes use of JUnit too)_
```
package org.webspeclanguage.tests;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleTest extends TestCase {

  WebDriver driver;

  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.get("http://www.google.com");
  }

  public void testGoogleHome() throws Exception {
    driver.findElement(By.name("q")).sendKeys("Webdriver");
    driver.findElement(By.name("btnG")).click();
    assertTrue(driver.findElement(By.xpath("//Body")).getText().contains("WebDriver"));
  }

}
```

The file must be saved under ./src/test with the name GoogleTest.java

The following **pom.xml** allows to execute the test cases with this command: "_**mvn test**_":
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

  <parent>
    <artifactId>main</artifactId>
    <groupId>org.webspeclanguage</groupId>
    <version>0.1-SNAPSHOT</version>
  </parent>

  <modelVersion>4.0.0</modelVersion>
  <groupId>org.webspeclanguage</groupId>
  <artifactId>webspeclanguage-test-webdriver</artifactId>
  <packaging>jar</packaging>

  <version>0.1-SNAPSHOT</version>
  <name>WebSpec Language - Webdriver test generation</name>
  <url>http://maven.apache.org</url>

  <repositories>
    <repository>
      <id>selenium-repository</id>
      <url>http://selenium.googlecode.com/svn/repository/</url>
    </repository>
  </repositories>

  

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium</artifactId>
      <version>2.0a4</version>
    </dependency>
  </dependencies>

  <build>
    <finalName>${project.artifactId}</finalName>
    <sourceDirectory>src/main</sourceDirectory>
    <testSourceDirectory>src/test</testSourceDirectory>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>2.0</version>
        <configuration>
          <source>1.5</source>
          <target>1.5</target>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```

This pom compiles the tests and execute them.