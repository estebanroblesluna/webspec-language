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
package org.selenium;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;

/**
 * An extended version of the Selenium driver that allows
 * to execute arbitrary function calls
 * 
 * @author Esteban Robles Luna
 */
public class ExtendedSelenium extends DefaultSelenium {

  private boolean hasStarted = false;;

  public ExtendedSelenium(String serverHost, int serverPort,
      String browserStartCommand, String browserURL) {
    super(serverHost, serverPort, browserStartCommand, browserURL);
  }

  public ExtendedSelenium(CommandProcessor processor) {
    super(processor);
  }

  public void execute(String command, String arg1, String arg2) {
    this.commandProcessor.doCommand(command, new String[] { arg1, arg2, });
  }

  public void execute(String command, String arg1) {
    this.commandProcessor.doCommand(command, new String[] { arg1, });
  }

  public void execute(String command, String[] args) {
    this.commandProcessor.doCommand(command, args);
  }

  public String getString(String command, String arg1, String arg2) {
    return this.commandProcessor.getString(command,
        new String[] { arg1, arg2, });
  }

  public String getString(String command, String arg1) {
    return this.commandProcessor.getString(command, new String[] { arg1, });
  }

  public void safeStart() {
    if (!this.hasStarted()) {
      this.start();
    }
  }

  @Override
  public void start() {
    super.start();
    this.setHasStarted(true);
  }

  @Override
  public void start(Object optionsObject) {
    super.start(optionsObject);
    this.setHasStarted(true);
  }

  @Override
  public void start(String optionsString) {
    super.start(optionsString);
    this.setHasStarted(true);
  }

  @Override
  public void stop() {
    super.stop();
    this.setHasStarted(false);
  }

  public void safeStop() {
    if (this.hasStarted()) {
      this.stop();
    }
  }

  private boolean hasStarted() {
    return hasStarted;
  }

  private void setHasStarted(boolean hasStarted) {
    this.hasStarted = hasStarted;
  }
}
