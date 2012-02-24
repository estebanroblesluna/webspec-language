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
@implementation Widget : Figure 
{ 
	CPTextField _label;
} 

- (id)initWithFrame:(CGRect)aFrame 
{ 
	self = [super initWithFrame:aFrame];
	if (self) {
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"middleLeft"]];
		handles = [handles arrayByAddingObject: [[Handle alloc] initWithTarget: self selector: @"middleRight"]];

		//DRAW INTERACTION NAME
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: @"Widget"];
		[label setTextColor:[CPColor blackColor]];
		[label sizeToFit];
		[label setFrameOrigin:CGPointMake(22, 4)];
		[self addSubview: label];
		_label = label;

		//DRAW ICON
		var icon = [[CPImage alloc]
		            initWithContentsOfFile: @"Resources/Label.gif" 
		            size:CGSizeMake(16, 16)];
		
		var iconView = [[CPImageView alloc] initWithFrame:CGRectMake(4, 4, 16, 160)];
		[iconView setHasShadow:NO];
		[iconView setImageScaling:CPScaleNone];
		
		var iconSize = [icon size];
		[iconView setFrameSize: iconSize];
		[iconView setImage: icon];
		[self addSubview: iconView];
		
		return self;
	}
} 

- (bool) isSelectable
{ 
	return true;
}

- (bool) isMoveable
{ 
	return true;
}

- (bool) isEditable
{ 
	return true;
}

- (void) switchToEditMode
{
	var editorDelegate = [[EditorDelegate alloc] 
		initWithWidget: _label 
		value: [_label objectValue]
		window: [self window]
		figureContainer: self
		drawing: [self drawing]];
}

- (void) setEditionResult: (String) aValue
{
	[_label setObjectValue: aValue];
	[_label sizeToFit];
	
	var currentFrameSize = [self frameSize];
	currentFrameSize.width = [_label frameOrigin].x + [_label frameSize].width;
	[self setFrameSize: currentFrameSize];
}
@end