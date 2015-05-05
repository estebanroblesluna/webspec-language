# Watir Tests #

Watir steps to configure and execute Ruby Watir Test Cases.


## What's needed? ##

Ruby (http://www.ruby-lang.org/), it's needed the version 1.8.6 or 1.8.7, the 1.9.x version is not compatible with Watir.

Watir (http://www.watir.com), after installing Ruby in the system it's needed to install Watir library, the following code installs Watir:

```
gem update --system
gem install watir
```

The next step is install the addon for Firefox, the different version for each operating system can be found in the following link: http://watir.com/installation/


## How to execute Ruby scripts ##

The Ruby scripts can be executed with the following sentence:
```
ruby script.rb
```

The Ruby files must have the .rb extension.


## Extra libraries ##

Watir can be complemented with the Test/Unit library, it allows to have test and suites more organized.
To implement a Test is required to extends the TestCase class, so the code could looks like this:

```
require "rubygems"
require "test/unit"

class TC_Example < Test::Unit::TestCase

  def setup
    # setup method
  end

  def testStep
  end

end
```

All test methods should start with the name "test", if the beginning of the name is different the method is ignored by Test/Unit.

This scripts can be executed in the same way than any Ruby script, e.g.: ruby test.rb


## Running Scripts from Maven ##

There's no plugin for Ruby language, so, the only way to execute ruby scripts is using **Exec Maven** plugin.

An example of a pom for executing test.rb:

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

  <parent>
    <artifactId>main</artifactId>
    <groupId>org.webspeclanguage</groupId>
    <version>0.1-SNAPSHOT</version>
  </parent>

  <modelVersion>4.0.0</modelVersion>
  <groupId>org.webspeclanguage</groupId>
  <artifactId>webspeclanguage-test-watir</artifactId>
  <packaging>test</packaging>
  <version>0.1-SNAPSHOT</version>
  <name>WebSpec Language - Watir test running</name>
  <url>http://maven.apache.org</url>

  <build>
    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.1</version>
        <executions>
          <execution>
            <goals>
              <goal>exec</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <executable>ruby</executable>
          <arguments>
            <argument>test.rb</argument>
          </arguments>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
 
```


From command line:
```
mvn exec:exec
```


---


## Ruby using Ant ##

The ruby scripts can be executed using Ant too. Ant allows to execute the Java Version of Ruby language (JRuby), this implementation also has the a libreary with the same API of Watir, this library is _Celerity_, implemented using HtmlUnit.

An example of an Ant task for Ruby:

```
<project name="RunRubyTestExample">
  <target name="test" description="Run Ruby test">
    <exec executable="ruby" failonerror="true">
      <arg value="test.rb" />
    </exec>
  </target>
</project>
```

An example of an Ant task for JRuby:

```
<project name="RunRubyTestExample">

  <target name="test" description="Run Ruby test">

    <property environment="env"/>

    <java classname="org.jruby.Main" fork="true" failonerror="true">

      <classpath>
        <fileset dir="${env.JRUBY_HOME}/lib">
          <include name="*.jar"/>
        </fileset>
        <pathelement path="${env.JRUBY_HOME}/lib"/>
      </classpath>

      <jvmarg value="-Djruby.home=${env.JRUBY_HOME}"/>
      <arg value="-S"/>
      <arg value="test.rb"/>

    </java>
  </target>
</project>
```

In both cases to run the tasks, the following command must be executed:
```
ant test
```