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
package org.webspeclanguage.userstories.visitor;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.webspeclanguage.api.PathItemVisitor;

/**
 * @author cristian.cianfagna
 */
public abstract class AbstractVisitor implements PathItemVisitor {

  private MessageSource messageSource;
  private Locale locale;
  
  public AbstractVisitor(MessageSource messageSource, Locale locale) {
    this.setMessageSource(messageSource);
    this.setLocale(locale);
  }

  protected String getMessage(String bundleKey) {
    return this.getMessageSource().getMessage(bundleKey, new Object[] {}, this.getLocale());
  }

  protected String getMessage(String bundleKey, Object[] args) {
    return this.getMessageSource().getMessage(bundleKey, args, this.getLocale());
  }
  
  protected MessageSource getMessageSource() {
    return messageSource;
  }
  
  private void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }
  
  protected Locale getLocale() {
    return locale;
  }
  
  private void setLocale(Locale locale) {
    this.locale = locale;
  }

}
