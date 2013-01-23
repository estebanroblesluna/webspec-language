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
package org.webspeclanguage.userstories.response;

import java.io.File;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;

/**
 * @author cristian.cianfagna
 */
public class WordUserStoryGenerationResponse implements UserStoryGenerationResponse {

  private WordprocessingMLPackage wordprocessingMLPackage;

  public WordUserStoryGenerationResponse(WordprocessingMLPackage wordprocessingMLPackage) {
    this.setWordprocessingMLPackage(wordprocessingMLPackage);
  }

  public void generateResourcesIn(String fullDirectory, String fileNameWithoutExtension) throws Exception {
    StringBuilder sb = new StringBuilder(fullDirectory);
    sb.append("/").append(fileNameWithoutExtension).append(".docx");
    this.getWordprocessingMLPackage().save(new File(sb.toString()));
  }
  
  private WordprocessingMLPackage getWordprocessingMLPackage() {
    return wordprocessingMLPackage;
  }
  
  private void setWordprocessingMLPackage(WordprocessingMLPackage wordprocessingMLPackage) {
    this.wordprocessingMLPackage = wordprocessingMLPackage;
  }

}
