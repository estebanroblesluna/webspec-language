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
 * @author "Esteban Robles Luna <esteban.roblesluna@gmail.com>"
 */
@implementation TransitionIconLabelFigure : IconLabelFigure 
{ 
} 

- (id) value
{
	var model = [self model];
	var value = 
		@"#" 
		+ [model propertyValue: @"Name"]
		+ @"\n"
		+ @"{"
		+ [model propertyValue: @"Precondition"]
		+ @"}"
		+ @"\n"
		+ [model propertyValue: @"Actions"];
		
	//CPLog.info(value);
	return value;
}

- (void) value: (id) aValue
{
	var model = [self model];

	var indexNumberal = aValue.indexOf("#");
	var indexLeftBrace = aValue.indexOf("{");
	var indexRightBrace = aValue.indexOf("}");
	
	var name = aValue.substr(indexNumberal + 1, indexLeftBrace - (indexNumberal + 1));
	var precondition = aValue.substr(indexLeftBrace + 1, indexRightBrace - (indexLeftBrace + 1));
	var actions = aValue.substr(indexRightBrace + 1);
	
	name = [name stringByTrimmingWhitespace];
	precondition = [precondition stringByTrimmingWhitespace];
	actions = [actions stringByTrimmingWhitespace];
	
	[model propertyValue: @"Name" be: name];	
	[model propertyValue: @"Precondition" be: precondition];	
	[model propertyValue: @"Actions" be: actions];	
}
@end