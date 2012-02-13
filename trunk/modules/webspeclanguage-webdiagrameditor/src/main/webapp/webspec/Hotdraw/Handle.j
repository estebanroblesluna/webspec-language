@import <Foundation/Foundation.j>
@import <AppKit/CPView.j>
@import "Figure.j"

@implementation Handle : Figure 
{ 
	Figure _targetFigure;
	SEL _getSelector;
	SEL _setSelector;
	id _extraArgument;
	CPColor _displayColor;
} 

- (id)initWithTarget: (id) aTargetFigure selector: (CPString) aStringSelector
{
	 return [self initWithTarget: aTargetFigure selector: aStringSelector extraArgument: nil]
}

- (id)initWithTarget: (id) aTargetFigure selector: (CPString) aStringSelector extraArgument: (id) extraArgument
{ 
	var getSelector = CPSelectorFromString(aStringSelector);
	var setSelector = CPSelectorFromString([aStringSelector stringByAppendingString: @":"]);
	return [self initWithTarget: aTargetFigure getSelector: getSelector setSelector: setSelector extraArgument: extraArgument];
}

- (id)initWithTarget: (id) aTargetFigure getSelector: (SEL) getSelector setSelector: (SEL) setSelector extraArgument: (id) extraArgument
{ 
	_targetFigure = aTargetFigure;
	_getSelector = getSelector;
	_setSelector = setSelector;
	_extraArgument = extraArgument;
	_displayColor = [_targetFigure handleColor];

	[[CPNotificationCenter defaultCenter] 
		addObserver: self 
		selector: @selector(updateLocation:) 
		name: @"CPViewFrameDidChangeNotification" 
		object: _targetFigure];
	
	var point = [self getPoint];
	//CPLog.debug(point);
	self = [super initWithFrame: CGRectMake(point.x - 4, point.y - 4, 8, 8)];
	//CPLog.debug(self);
	
	if (self) {
		return self;
	}
}

- (id) extraArgument
{
	return _extraArgument;
}

- (void) extraArgument: (id) anExtraArgument
{
	_extraArgument = anExtraArgument;
}

- (void) getSelector: (id) aGetSelector setSelector: (id) aSetSelector
{
	_getSelector = aGetSelector;
	_setSelector = aSetSelector;
	//CPLog.info(@"getSelector");
}

- (CPPoint) getPoint 
{
	var point = nil;
	
	if (_extraArgument == nil) {
		point = [_targetFigure performSelector: _getSelector];
	} else {
		point = [_targetFigure performSelector: _getSelector withObject: _extraArgument];
	}
	
	return point;
}

- (void) updateLocation: (id) aNotification
{
	var point = [self getPoint];

	var x = point.x - 4;
	var y = point.y - 4;

	var newOrigin = CGPointMake(x, y);
	[super setFrameOrigin: newOrigin];
}

- (bool) isHandle
{ 
	return true;
}

- (bool) isMoveable
{ 
	return true;
}

- (Figure) targetFigure
{ 
	return _targetFigure;
}

- (void) setFrameOrigin: (CGPoint) aPoint
{
	if (_extraArgument == nil) {
		[_targetFigure performSelector: _setSelector withObject: aPoint];
	} else {
		[_targetFigure performSelector: _setSelector withObject: _extraArgument withObject: aPoint];
	}
}

- (void) displayColor: (CPColor) aDisplayColor
{
	_displayColor = aDisplayColor;
	[self setNeedsDisplay: YES];
}

- (void)drawRect:(CGRect)rect on: (id)context
{
	CGContextSetFillColor(context, _displayColor);
	CGContextFillRect(context, [self bounds]);
	CGContextSetFillColor(context, [CPColor whiteColor]);
	CGContextFillRect(context, CGRectMake(2, 2, 4, 4));
}

@end