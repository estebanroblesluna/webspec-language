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
@implementation MockupFigure : Figure 
{ 
	id _modelFeature;
} 

- (void) initWithFrame: aFrame andModelFeature: aModelFeature
{
	[super initWithFrame: aFrame];
	[self checkModelFeature: aModelFeature];
	return self;
}

- (void) addAllHandles
{
	[handles addObject: [Handle target: self selector: @"topLeft"]];
	[handles addObject: [Handle target: self selector: @"topMiddle"]];
	[handles addObject: [Handle target: self selector: @"topRight"]];

	[handles addObject: [Handle target: self selector: @"middleLeft"]];
	[handles addObject: [Handle target: self selector: @"middleRight"]];

	[handles addObject: [Handle target: self selector: @"bottomLeft"]];
	[handles addObject: [Handle target: self selector: @"bottomMiddle"]];
	[handles addObject: [Handle target: self selector: @"bottomRight"]];	
}

- (void) addLeftRightHandles;
{
	[handles addObject: [Handle target: self selector: @"middleLeft"]];
	[handles addObject: [Handle target: self selector: @"middleRight"]];	
}

- (void) switchToEditMode
{
	if ([self isEditable]) {
		var editorDelegate = [[EditorDelegate alloc] 
			initWithWidget: [self getEditableLabel] 
			label: [self getEditableLabel]
			window: [self window]
			figureContainer: self
			drawing: [self drawing]];
	}
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
			
		[self propertyChanged];
	}
}

- (void) propertyChanged
{
	var value = [self value];
	[self setLabelValue: value];
}

- (void) setLabelValue: (String) aValue
{
	var label = [self getEditableLabel];
	
	if (label == nil) {
		return;	
	}
	
	if (aValue == nil) {
		aValue = @"";
	}
	
	[[self getEditableLabel] setObjectValue: aValue];
	[[self getEditableLabel] sizeToFit];
	
	//var currentFrameSize = [self frameSize];
	//currentFrameSize.width = [_label frameOrigin].x + [_label frameSize].width;
	//currentFrameSize.height = [_label frameOrigin].y + [_label frameSize].height;
	//[self setFrameSize: currentFrameSize];
}



- (void) setEditionResult: (String) aValue
{
	if (_modelFeature != nil && ([self model] != nil)) {
		[self value: aValue];
	} else {
		[self setLabelValue: aValue];
	}
	[self setNeedsDisplay: YES];
}


- (id) value
{
	return [[self model] propertyValue: _modelFeature];
}

- (void) value: (id) aValue
{
	[[self model] propertyValue: _modelFeature be: aValue];	
}

- (CPTextField) getEditableLabel
{
	return nil;	
}

- (BOOL) idEditable
{ 
	return true;
}


