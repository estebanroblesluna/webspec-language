## 1 Maven ##
The first time is to setup Maven in your box.

### 1.1 Download ###
So please download Maven 2 from http://maven.apache.org/. You can use this link which is the latest Maven 2 version today: http://apache.dattatec.com/maven/maven-2/2.2.1/binaries/apache-maven-2.2.1-bin.zip

### 1.2 Unzip ###
Unzip the zip file and move it to a directory you are going to use. For example, in linux I would move it to /usr/local/maven

### 1.3 Add path variable ###
Add /usr/local/maven/bin to the PATH variable.

In Linux/Mac you can export do it by running
```
export PATH=$PATH:/usr/local/maven/bin
```
I recommend to add it to your ~/.bashrc

In Windows you can do something similar by running from the command line

```
set PATH=%PATH%;C:\maven\bin
```
I also recommend you to add it to your user path variables through My computer

### 1.4 Test ###
To test that Maven is working run from the command line:

```
mvn -version
```
and you will obtain something like this:

```
> Apache Maven 2.2.1 (r801777; 2009-08-06 16:16:01-0300)
> Java version: 1.7.0_21
> Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_21.jdk/Contents/Home/jre
```

## 2. MySql ##
Configure a Mysql instance if you don't have one

### 2.1 Download ###
Download Mysql 5.5.27 for your OS.

### 2.2 Configuration ###
Configure a user with name "root" and password "".

### 2.3 Schema creation ###
If you are in linux you can run the schema creation script that will create the DB for you. The script is located in: /modules/webspeclanguage-webdiagrameditor/config/schema/recreateSchema.sh

If you are in Windows, you can help use creating that script or run the sqls located in /modules/webspeclanguage-webdiagrameditor/config/schema/ in the following order:
  * common.sql
  * sampleData.sql
  * playgroundSchema.sql

### 2.4 Test ###
Verify that the schema playground has been created

## 3. Checkout the SVN ##

You can checkout the code in read only mode by running the following command from the command line
```
svn checkout http://webspec-language.googlecode.com/svn/trunk/ webspec-language-read-only
```

## 4. Test everything is fine from the command line ##

Run the following command from the trunk directory

```
mvn clean install -Pwebspec
```

It should build successfully

## 5. Setup Eclipse IDE ##

Run the following command from the trunk directory

```
mvn eclipse:eclipse -Pwebspec
```

It should build successfully

Then in Eclipse go to the menu and:
  1. Import -> Existing projects into workspace
  1. Select the /modules directory
  1. Import

## 6. Run the editor in debug mode (Eclipse) ##

Once you import all the projects from step 5 you can run the editor in debug mode by
  1. Search for the app.properties file and make sure env=DEV
  1. In the menu Run -> External tools -> External tools configuration
    1. Program ->  New (Right click)
    1. In the main tab set the location to your mvn location (in linux it could be /usr/local/maven/bin/mvn)
    1. Working directory: ${workspace\_loc:/webspeclanguage-webdiagrameditor}
    1. Arguments jetty:run
    1. In the environment tab click in the button new for a new variable
    1. Name: MAVEN\_OPTS
    1. Value: -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt\_socket,address=4000,server=y,suspend=n
    1. Then click in Apply and then Run
  1. In the menu Run -> Debug configuration
    1. Remote Java application ->  New (Right click)
    1. Project: webspeclanguage-webdiagrameditor
    1. Port: 4000
    1. Then click in Apply and then Debug

The editor should be running in http://localhost:9090/playground/index.html and requires Facebook or twitter authentication