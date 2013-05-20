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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.webspeclanguage.userstories.UserStoryGenerationResponse;

/**
 * @author cristian.cianfagna
 */
public class HtmlUserStoryGenerationResponse implements
		UserStoryGenerationResponse {

	private String html;

	public HtmlUserStoryGenerationResponse(String html) {
		super();
		this.setHtml(html);
	}

	@Override
	public void generateResourcesIn(String fullDirectory,
			String fileNameWithoutExtension) throws Exception {
		StringBuilder sb = new StringBuilder(fullDirectory).append("/")
				.append(fileNameWithoutExtension).append(".html");
		FileOutputStream fos = new FileOutputStream(sb.toString());
		OutputStreamWriter osw = new OutputStreamWriter(fos,
				Charset.forName("UTF8"));
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(this.getHtml());
		bw.close();
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
