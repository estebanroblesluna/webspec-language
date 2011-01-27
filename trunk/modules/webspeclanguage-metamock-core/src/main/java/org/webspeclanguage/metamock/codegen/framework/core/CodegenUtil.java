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
package org.webspeclanguage.metamock.codegen.framework.core;

/**
 * @author Jose Matias Rivero
 */
public final class CodegenUtil {
  
  private CodegenUtil() {
  }

	public static String convertToIdentifier(String text) {
		StringBuffer sb = new StringBuffer();
		for (String s : text.split("\\s+")) {
			sb.append(CodegenUtil.firstToUpper(s.toLowerCase()));
		}
		return 
			CodegenUtil.firstToLower(
					CodegenUtil.escape(
							CodegenUtil.removeNonIdentChars(sb.toString())));
	}

	private static String removeNonIdentChars(String string) {
		return string;
	}

	private static String firstToLower(String s) {
		if (s.length() == 0) {
			return "";
		}
		char[] ca = s.toCharArray();
		ca[0] = Character.toLowerCase(ca[0]);
		return String.valueOf(ca);
	}

	public static String firstToUpper(String s) {
		if (s.length() == 0) {
			return "";
		}
		char[] ca = s.toCharArray();
		ca[0] = Character.toUpperCase(ca[0]);
		return String.valueOf(ca);
	}
	
	public static String escape(String s) {
		return s
			.replace("\'", "\\'")
			.replace("\"", "\\\"")
			.replaceAll("\\W", "");
	}
	
	public static String escapeExcludingBlanks(String s) {
		return s
			.replace("\'", "\\'")
			.replace("\"", "\\\"");
	}

  public static String camelCaseToSpaces(String name) {
    StringBuffer sb = new StringBuffer();
    for (Integer iChar = 0; iChar < name.length(); iChar++) {
      char c = name.charAt(iChar);
      if (Character.isLowerCase(c) && iChar > 0) {
        sb.append(c);
      } else {
        if (iChar > 0) {
          sb.append(" ");
        }
        sb.append(Character.toUpperCase(c));
      }
    }
    return sb.toString();
  }

}
