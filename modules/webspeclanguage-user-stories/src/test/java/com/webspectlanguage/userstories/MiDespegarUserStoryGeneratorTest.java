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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;
import org.webspeclanguage.userstories.cropping.CroppingInfo;
import org.webspeclanguage.userstories.impl.HTMLUserStoryGenerationParameters;
import org.webspeclanguage.userstories.impl.HtmlUserStoryGeneratorStrategy;
import org.webspeclanguage.userstories.impl.WordEnumerationUserStoryGeneratorStrategy;
import org.webspeclanguage.userstories.impl.WordTabularUserStoryGeneratorStrategy;
import org.webspeclanguage.userstories.impl.WordUserStoryGenerationParameters;
import org.webspeclanguage.userstories.response.HtmlUserStoryGenerationResponse;

public class MiDespegarUserStoryGeneratorTest {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"classpath*:userStoriesGenerationAppContex.xml");

	private Map<String, CroppingInfo> getCroppingMap() {
		Map<String, CroppingInfo> map = new HashMap<String, CroppingInfo>();
		map.put("PagDeGracias", new CroppingInfo(9, 12, 155, 107));
		map.put("Login", new CroppingInfo(456, 10, 197, 114));
		map.put("Reservas", new CroppingInfo(432, 215, 243, 138));
		map.put("Reserva", new CroppingInfo(12, 193, 219, 181));
		map.put("MiDespegar", new CroppingInfo(168, 31, 207, 52));
		map.put("EntrarConFacebook", new CroppingInfo(469, 130, 203, 57));
		map.put("AgregarEmail", new CroppingInfo(676, 216, 327, 121));
		map.put("VerReserva", new CroppingInfo(231, 262, 200, 52));
		return map;
	}

	@Test
	public void htmlGenerationTest() throws Exception {
		HtmlUserStoryGeneratorStrategy htmlUserStoryGenerator = new HtmlUserStoryGeneratorStrategy();
		htmlUserStoryGenerator.setMessageSource(ctx);
		Diagram diagram = WebSpecFactory.getMiDespegarExample();
		HtmlUserStoryGenerationResponse response = (HtmlUserStoryGenerationResponse) htmlUserStoryGenerator.generate(
				this.getParametersForHTMLGeneration(diagram, this.getCroppingMap(), new Locale("es", "ES")));
		response.generateResourcesIn(System.getProperty("user.dir")
				+ "/userStories-example-folder/", "MiDespegar");
	}
  
	@Test
  public void wordTabularGenerationStrategy() throws Exception {
    WordTabularUserStoryGeneratorStrategy wordTabularUserStoryGenerator = new WordTabularUserStoryGeneratorStrategy();
    wordTabularUserStoryGenerator.setApplicationContext(ctx);
    Diagram diagram = WebSpecFactory.getMiDespegarExample();
    File file = new File(this.getClass().getResource("MyBookingListDiagram.png").getFile());
    UserStoryGenerationResponse response = 
      wordTabularUserStoryGenerator.generate(
      		this.getParametersForWORDGeneration(diagram, this.getCroppingMap(), file, new Locale("en", "US")));
    response.generateResourcesIn(System.getProperty("user.dir") + "/userStories-example-folder/", "MiDespegarWordTabularStrategy");
  }

	@Test
  public void wordEnumerationGenerationStrategy() throws Exception {
    WordEnumerationUserStoryGeneratorStrategy wordEnumerationUserStoryGenerator = new WordEnumerationUserStoryGeneratorStrategy();
    wordEnumerationUserStoryGenerator.setApplicationContext(ctx);
    Diagram diagram = WebSpecFactory.getMiDespegarExample();
    File file = new File(this.getClass().getResource("MyBookingListDiagram.png").getFile());
    UserStoryGenerationResponse response = 
      wordEnumerationUserStoryGenerator.generate(
      		this.getParametersForWORDGeneration(diagram, this.getCroppingMap(), file, new Locale("es", "ES")));
    response.generateResourcesIn(System.getProperty("user.dir") + "/userStories-example-folder/", "MiDespegarWordEnumerationStrategy");
  }

	private UserStoryGenerationParameters getParametersForWORDGeneration(Diagram diagram, Map<String, CroppingInfo> croppingInfoMap,
			File diagramFile, Locale outputLocale) {
		return new WordUserStoryGenerationParameters(diagram, croppingInfoMap, diagramFile, outputLocale);
	}

	private UserStoryGenerationParameters getParametersForHTMLGeneration(Diagram diagram, Map<String, CroppingInfo> croppingInfoMap,
			Locale outputLocale) {
		return new HTMLUserStoryGenerationParameters(diagram, croppingInfoMap, outputLocale,
				"http://localhost:8080/service/projects/diagram/{diagramId}/image",
				"http://localhost:8080/service/projects/diagram/{diagramId}/image/{x}/{y}/{width}/{height}",
				"555", Arrays.asList("css/common.css", "css/userstories.css"),
				Arrays.asList("http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js", "js/fancyzoom.js"),
				"/home/sony/Development/repositorio/webspec-language/modules/webspeclanguage-user-stories/userStories-example-folder/images");
	}

}
