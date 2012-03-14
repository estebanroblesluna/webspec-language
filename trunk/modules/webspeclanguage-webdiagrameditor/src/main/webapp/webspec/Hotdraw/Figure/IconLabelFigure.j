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
@implementation IconLabelFigure : Figure 
{ 
	CPTextField _label;
	CPString _iconUrl;
	boolean _selectable;
	boolean _moveable;
	boolean _editable;
	id _modelFeature;
} 

+ (IconLabelFigure) newAt: (CGPoint) aPoint iconUrl: (id) iconUrl
{
	var frame = CGRectMake(aPoint.x, aPoint.y, 100, 25);
	var widget = [[self new] initWithFrame: frame iconUrl: iconUrl];
	return widget;
}

- (id) initWithFrame: (CGRect) aFrame iconUrl: (id) iconUrl
{ 
	self = [super initWithFrame:aFrame];
	if (self) {
		_iconUrl = iconUrl;
		_selectable = true;
		_moveable = true;
		_editable = true;
		
		[handles addObject: [[Handle alloc] initWithTarget: self selector: @"middleLeft"]];
		[handles addObject: [[Handle alloc] initWithTarget: self selector: @"middleRight"]];

		//DRAW WIDGET NAME
		var label = [[CPTextField alloc] initWithFrame:CGRectMakeZero()];
		[label setStringValue: @"Widget"];
		[label setTextColor:[CPColor blackColor]];
		[label sizeToFit];
		[label setFrameOrigin:CGPointMake(22, 4)];
		[self addSubview: label];
		_label = label;

		//DRAW ICON
		var icon = [[CPImage alloc]
		            initWithContentsOfFile: _iconUrl
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
	return _selectable;
}

- (bool) isMoveable
{ 
	return _moveable;
}

- (bool) isEditable
{ 
	return _editable;
}

- (void) selectable: (boolean) aValue
{
	_selectable = aValue;
}

- (void) moveable: (boolean) aValue
{
	_moveable = aValue;
}

- (void) editable: (boolean) aValue
{
	_editable = aValue;
}

- (void) switchToEditMode
{
	if (_editable) {
		var editorDelegate = [[EditorDelegate alloc] 
			initWithWidget: _label 
			value: [_label objectValue]
			window: [self window]
			figureContainer: self
			drawing: [self drawing]];
	}
}

- (void) setEditionResult: (String) aValue
{
	if (_modelFeature != nil && ([self model] != nil)) {
		[[self model] propertyValue: _modelFeature be: aValue];
	} else {
		[self setLabelValue: aValue];
	}
}

- (void) setLabelValue: (String) aValue
{
	[_label setObjectValue: aValue];
	[_label sizeToFit];
	
	var currentFrameSize = [self frameSize];
	currentFrameSize.width = [_label frameOrigin].x + [_label frameSize].width;
	[self setFrameSize: currentFrameSize];
}

- (void) propertyChanged
{
	var value = [[self model] propertyValue: _modelFeature];
	[self setLabelValue: value];
}

- (void) checkModelFeature: (id) aModelFeature
{
	if (_modelFeature != nil && ([self model] != nil)) {
		[[CPNotificationCenter defaultCenter] 
			removeObserver: self 
			name: ModelPropertyChangedNotification 
			object: [self model]];
		
	}

	_modelFeature = aModelFeature

	if (_modelFeature != nil && ([self model] != nil)) {
		[[CPNotificationCenter defaultCenter] 
			addObserver: self 
			selector: @selector(propertyChanged) 
			name: ModelPropertyChangedNotification 
			object: [self model]];
	}
}
@end