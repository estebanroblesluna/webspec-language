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
package com.webspectlanguage.userstories;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.impl.HtmlUserStoryGeneratorStrategy;
import org.webspeclanguage.userstories.impl.WordEnumerationUserStoryGeneratorStrategy;
import org.webspeclanguage.userstories.impl.WordTabularUserStoryGeneratorStrategy;

/**
 * Test for word's document generator strategies.
 * 
 * @author cristian.cianfagna
 */
public class UserStoryGeneratorTest {

  private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:userStoriesGenerationAppContex.xml");

  private Map<String, CroppingInfo> getCroppingMap() {
    Map<String, CroppingInfo> map = new HashMap<String, CroppingInfo>();
    map.put("Home", new CroppingInfo(9, 13, 142, 92));
    map.put("SearchResults", new CroppingInfo(503, 9, 309, 85));
    map.put("ShoppingCart", new CroppingInfo(503, 196, 285, 92));
    map.put("ProductDetail", new CroppingInfo(33, 192, 232, 91));
    map.put("search", new CroppingInfo(153, 5, 328, 54));
    map.put("view product details1", new CroppingInfo(249, 94, 262, 50));
    map.put("add to cart1", new CroppingInfo(559, 111, 245, 54));
    map.put("add to cart2", new CroppingInfo(292, 195, 189, 53));
    map.put("view product details2", new CroppingInfo(270, 322, 266, 50));
    return map;
  }

  @Test
  public void wordTabularGenerationStrategy() throws Exception {
    WordTabularUserStoryGeneratorStrategy wordTabularUserStoryGenerator = new WordTabularUserStoryGeneratorStrategy();
    wordTabularUserStoryGenerator.setApplicationContext(ctx);
    Diagram diagram = WebSpecFactory.getShoppingCartExample();
    File file = new File(this.getClass().getResource("shoppingCartDiagram.png").getFile());
    UserStoryGenerationResponse response = 
      wordTabularUserStoryGenerator.generate(diagram, this.getCroppingMap(), file, new Locale("en", "US"));
    response.generateResourcesIn(System.getProperty("user.dir") + "/userStories-example-folder/", "WordTabularStrategy");
  }

  @Test
  public void wordEnumerationGenerationStrategy() throws Exception {
    WordEnumerationUserStoryGeneratorStrategy wordEnumerationUserStoryGenerator = new WordEnumerationUserStoryGeneratorStrategy();
    wordEnumerationUserStoryGenerator.setApplicationContext(ctx);
    Diagram diagram = WebSpecFactory.getShoppingCartExample();
    File file = new File(this.getClass().getResource("shoppingCartDiagram.png").getFile());
    UserStoryGenerationResponse response = 
      wordEnumerationUserStoryGenerator.generate(diagram, this.getCroppingMap(), file, new Locale("es", "ES"));
    response.generateResourcesIn(System.getProperty("user.dir") + "/userStories-example-folder/", "WordEnumerationStrategy");
  }

  @Test
  public void htmlGenerationStrategy() throws Exception {
    HtmlUserStoryGeneratorStrategy htmlUserStoryGenerator = new HtmlUserStoryGeneratorStrategy();
    htmlUserStoryGenerator.setMessageSource(ctx);
    Diagram diagram = WebSpecFactory.getShoppingCartExample();
    File diagramImageFile = new File(this.getClass().getResource("shoppingCartDiagram.png").getFile());
    UserStoryGenerationResponse response = htmlUserStoryGenerator.generate(diagram, this.getCroppingMap(), diagramImageFile, new Locale("es", "ES"));
    response.generateResourcesIn(System.getProperty("user.dir") + "/userStories-example-folder/", "HtmlStrategy");
  }
}
