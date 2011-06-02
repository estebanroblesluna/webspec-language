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
package org.webspeclanguage.metamock.io;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.webspeclanguage.metamock.codegen.xml.MockupXmlGenerator;
import org.webspeclanguage.metamock.model.MetaMockModel;

/**
 * @author Jose Matias Rivero
 */
public class MetaMockXmlSerializer implements MetaMockSerializer {

  public String serialize(MetaMockModel model) {
    XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
    return xo.outputString(
      new MockupXmlGenerator().generateInSingleDocument(model));
  }

  public MetaMockModel desrialize(String serializedModel) throws Exception {
    throw new Exception("MustBeImplemented");
  }

}
