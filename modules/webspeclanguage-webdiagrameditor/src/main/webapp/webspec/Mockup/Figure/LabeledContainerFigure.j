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
@implementation LabeledContainerFigure : MockupFigure 
{ 
	CPTextField _label;
} 

+ (LabeledFigure) newAt: (CGPoint) aPoint
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 100, 25);
	var widget = [[self new] initWithFrame: frame];
	return widget;
}

- (id) initWithFrame: (CGRect) aFrame model: aModel modelFeature: aModelFeature labelValue: aLabelValue labelLocation: aPoint
{ 
	self = [super initWithFrame: aFrame model: aModel modelFeature: aModelFeature];
	if (self) {
		
		//DRAW WIDGET NAME
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: aLabelValue];
		[label setTextColor:[CPColor blackColor]];
		[label sizeToFit];
		[label setFrameOrigin: aPoint];
		[self addSubview: label];
		_label = label;
	
		return self;
	}
}

- (CPTextField) getEditableLabel
{
	return _label;	
}

- (CPTextField) label {
    return _label;   
}


- (void) addWidget: (Widget) aWidget
{
	[self addSubview: aWidget];
}

- (void) addFigure: (Widget) aWidget
{
	[self addWidget: aWidget];
}

@end