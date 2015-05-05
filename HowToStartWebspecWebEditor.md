To start Webspec's Web editor follow these steps:
  1. Checkout trunk
  1. You need to have a MySql instance running locally. So please set up one if you don't have it.
  1. Setup the DB using a script (otherwise you can copy and paste the SQL):
```
cd modules/webspeclanguage-webdiagrameditor/config/schema/
./recreateSchema.sh
```
  1. From the ROOT directory run (if you are in the previous step run cd ../../../../) :
```
mvn clean install -DskipTests
cd modules/webspeclanguage-webdiagrameditor/
mvn jetty:run
```
  1. The editor will be available in http://localhost:9090/playground/webspec/index.html