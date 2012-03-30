/**
 * Licensed under the Apache License, Version 2.0 (the "License");
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


/**
 * @author "Jose Matias Rivero <jose.matias.rivero@gmail.com>"
 */
@implementation MockupDrawing : Drawing
{
}

- (id) widgetContainerOf: (Figure) aFigure
{
	var current = aFigure;
	while (current != nil 
 			//&& ![current isKindOfClass:[PanelContainerFigure class]] 
			//&& ![current isKindOfClass:[ListOfContainerFigure class]] 
			//&& ![current isKindOfClass:[InteractionFigure class]] 
			&& ![current isKindOfClass:[Drawing class]]) 
	{
		current = [current superview];
	}
	
	if ([current isKindOfClass:[Drawing class]]) {
		return nil;
	} else {
		return current;
	}
}

@end