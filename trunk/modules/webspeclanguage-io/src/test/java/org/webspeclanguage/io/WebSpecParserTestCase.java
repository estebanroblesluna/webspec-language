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
package org.webspeclanguage.io;

import org.webspeclanguage.action.ActionParser;
import org.webspeclanguage.base.WebSpecDiagram;
import org.webspeclanguage.base.WebSpecInteraction;
import org.webspeclanguage.base.WebSpecNavigation;
import org.webspeclanguage.base.WebSpecRichBehavior;
import org.webspeclanguage.io.WebSpecParser;
import org.webspeclanguage.widget.ListOfContainer;

import junit.framework.TestCase;

/**
 * @author Esteban Robles Luna
 */
public class WebSpecParserTestCase extends TestCase {

  public void testParsing() {
    WebSpecParser parser = new WebSpecParser();
    WebSpecDiagram diagram = (WebSpecDiagram) parser.parse("webspecexample.xml");

    assertEquals("example", diagram.getName());
    assertEquals(2, diagram.getInteractions().size());

    WebSpecInteraction home = diagram.getInteractionNamed("Home");
    assertNotNull(home.getWidget("search"));
    assertNotNull(home.getWidget("searchTF"));

    WebSpecInteraction searchResults = diagram.getInteractionNamed("SearchResults");
    assertNotNull(searchResults.getWidget("searchTitle"));

    ListOfContainer listOfContainer = (ListOfContainer) searchResults.getWidget("books");
    assertNotNull(listOfContainer.getWidgetNamed("price"));

    assertEquals(2, home.getForwardTransitions().size());

    WebSpecNavigation navigation = home.navigationsTo(searchResults).get(0);
    assertEquals(
            ActionParser.getActions("productName := $products$; type(Home.searchTF, ${productName}); click(Home.search)", diagram), 
            navigation.getActions());

    WebSpecRichBehavior richBehavior = home.richBehaviorsTo(searchResults).get(0);
    assertEquals(
            ActionParser.getActions("click(Home.search)", diagram), 
            richBehavior.getActions());
  }
}
