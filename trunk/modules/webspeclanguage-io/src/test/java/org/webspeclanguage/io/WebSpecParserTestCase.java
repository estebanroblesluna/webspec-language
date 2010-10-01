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

import junit.framework.TestCase;

import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.api.RichBehavior;
import org.webspeclanguage.impl.action.ActionParser;
import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.RichBehaviorImpl;
import org.webspeclanguage.impl.widget.ListOfContainer;

/**
 * @author Esteban Robles Luna
 */
public class WebSpecParserTestCase extends TestCase {

  public void testParsing() {
    WebSpecParser parser = new WebSpecParser();
    DiagramImpl diagram = (DiagramImpl) parser.parse("webspecexample.xml");

    assertEquals("example", diagram.getName());
    assertEquals(2, diagram.getInteractions().size());

    Interaction home = diagram.getInteractionNamed("Home");
    assertNotNull(home.getWidget("search"));
    assertNotNull(home.getWidget("searchTF"));

    Interaction searchResults = diagram.getInteractionNamed("SearchResults");
    assertNotNull(searchResults.getWidget("searchTitle"));

    ListOfContainer listOfContainer = (ListOfContainer) searchResults.getWidget("books");
    assertNotNull(listOfContainer.getWidgetNamed("price"));

    assertEquals(2, home.getForwardTransitions().size());

    Navigation navigation = home.getForwardTransitionsTo(searchResults, Navigation.class).get(0);
    assertEquals(
            ActionParser.getActions("productName := $products$; type(Home.searchTF, ${productName}); click(Home.search)", diagram), 
            navigation.getActions());

    RichBehavior richBehavior = home.getForwardTransitionsTo(searchResults, RichBehavior.class).get(0);
    assertEquals(
            ActionParser.getActions("click(Home.search)", diagram), 
            richBehavior.getActions());
  }
}
