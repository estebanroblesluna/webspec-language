/*
 * AppController.j
 * NewApplication
 *
 * Created by You on November 16, 2011.
 * Copyright 2011, Your Company All rights reserved.
 */
@import <Foundation/CPObject.j>
@import <Foundation/Foundation.j>
@import "Hotdraw/Drawing.j"
@import "Hotdraw/Figure.j"
@import "Hotdraw/Handle.j"
@import "Hotdraw/Connection.j"
@import "Hotdraw/Polyline.j"
@import "Hotdraw/Connection.j"
@import "Hotdraw/HandleMagnet.j"
@import "Interaction.j"
@import "Widget.j"
@import "ProjectFigure.j"
@import "LPMultiLineTextField.j"

@implementation AppController : CPObject
{
	CPTextField svnChangesTextField;
	CPTextField bwTextField;
	Drawing drawing;
	id anotherContentView;
}

- (void)applicationDidFinishLaunching:(CPNotification)aNotification
{
    CPLogRegister(CPLogPopup);
	var theWindow = [[CPWindow alloc] initWithContentRect:CGRectMakeZero() styleMask:CPBorderlessBridgeWindowMask],
        contentView = [theWindow contentView];


	[self initializeDrawing: contentView];
    [theWindow orderFront: self];
	[theWindow makeFirstResponder: drawing];
/*
	var windowWidth = 500;
    svnChangesTextField = [LPMultiLineTextField textFieldWithStringValue:@"" placeholder:@"Insert your svn changes here!" width:250];
	[svnChangesTextField setEditable: YES];
	[svnChangesTextField setBordered: YES];
	[svnChangesTextField setFrame: CPRectMake(0, 0, windowWidth, 160)];
	[svnChangesTextField setAutoresizingMask:CPViewMinXMargin | CPViewMaxXMargin | CPViewMinYMargin | CPViewMaxYMargin];

    bwTextField = [CPTextField textFieldWithStringValue:@"" placeholder:@"Computed BW" width:250];
	[bwTextField setEditable: NO];
	[bwTextField setBordered: YES];
	[bwTextField setFrame: CPRectMake(0, 170, windowWidth, 30)];
	[bwTextField setAutoresizingMask:CPViewMinXMargin | CPViewMaxXMargin | CPViewMinYMargin | CPViewMaxYMargin];

	var updateButton = [[CPButton alloc] initWithFrame: CGRectMake(4, 200, 120, 25)];
	[updateButton setAutoresizingMask:CPViewMinXMargin | 
	                            CPViewMaxXMargin | 
	                            CPViewMinYMargin | 
	                            CPViewMaxYMargin];

	[updateButton setTitle:"Update"];
	[updateButton setCenter: CPPointMake(windowWidth / 2, 240 - (25))];

	[updateButton setTarget:self];
	[updateButton setAction:@selector(update)];
	
	var anotherWindow = [[CPWindow alloc] initWithContentRect:CGRectMake(700, 40, windowWidth, 232) styleMask: CPTitledWindowMask];
	[anotherWindow setTitle: @"SVN changes"];
	anotherContentView = [anotherWindow contentView];

	[anotherWindow setBackgroundColor: [CPColor blackColor]];
	[anotherContentView addSubview: svnChangesTextField];
	[anotherContentView addSubview: bwTextField];
	[anotherContentView addSubview: updateButton];
	//[contentView addSubview: anotherContentView];
    [anotherWindow orderFront: self];
*/
}

-(void) update
{
	var body = [svnChangesTextField stringValue];
	//CPLog.debug(body);
	
	var request = [[CPURLRequest alloc] initWithURL:@"services/projectService"];
	[request setHTTPMethod:@"POST"];
	[request setHTTPBody: body];
	[request setValue:"application/x-www-form-urlencoded;charset=UTF-8" forHTTPHeaderField:@"Content-Type"];
	
	var urlConnection = [CPURLConnection connectionWithRequest:request delegate:self];
	[urlConnection start];
}

- (void)connection:(CPURLConnection) connection didReceiveData:(CPString)data
{
	[drawing clearFigures];
	
	var warBackColor = [CPColor colorWithHexString: @"228B22"];
	var warForeColor = [CPColor whiteColor];
	var jarBackColor = [CPColor colorWithHexString: @"D3D3D3"];
	var jarForeColor = [CPColor blackColor];

	var projectMapping = [CPDictionary dictionary];
	
	//var fullSpec = @"0,4,news-common-scraping-cmpt,jar\n0,3,sharing-unwinder-cmpt,jar\n0,2,scrapertools-impl,jar\n0,1,scrapertools-cmpt,jar\n1,1,sharing-urlhandler-cmpt,jar\n0,0,scrapertools-war,jar\n1,0,sharing-unwinder-war,war\nscrapertools-war,scrapertools-impl\nscrapertools-war,scrapertools-cmpt\nsharing-unwinder-war,sharing-urlhandler-cmpt\nscrapertools-impl,sharing-unwinder-cmpt\nscrapertools-cmpt,news-common-scraping-cmpt\nscrapertools-cmpt,scrapertools-impl\nsharing-urlhandler-cmpt,sharing-unwinder-cmpt\nsharing-unwinder-cmpt,news-common-scraping-cmpt\nscrapertools-impl,sharing-unwinder-cmpt\nsharing-unwinder-cmpt,news-common-scraping-cmpt";
	
	var fullSpec = data;
	//CPLog.debug(fullSpec);
	var lines = [fullSpec componentsSeparatedByString: @"\n"];
	for (var i = 0; i < [lines count]; i++) { 
	    var line = [lines objectAtIndex:i];
		//CPLog.debug(line);
		var spec = [line componentsSeparatedByString: @","];
		if ([spec count] >= 1) {
			var firstElement = [spec objectAtIndex: 0];
			if ([firstElement hasPrefix: @"BW="]) {
				[bwTextField removeFromSuperview];
				[bwTextField setObjectValue: line];
				[anotherContentView addSubview: bwTextField];
			} else if ([spec count] == 4) {
				//create figure
				var xAsString = [spec objectAtIndex: 0];
				var yAsString = [spec objectAtIndex: 1];
				var name = [spec objectAtIndex: 2];
				var type = [spec objectAtIndex: 3];

				var x = [xAsString intValue];
				var y = [yAsString intValue];
				x = 20 + (160 * x);
				y = 20 + (100 * y);

				var backColor;
				var foreColor;

				if ([type isEqualToString: @"jar"]) {
					backColor = jarBackColor;
					foreColor = jarForeColor;
				} else {
					backColor = warBackColor;
					foreColor = warForeColor;
				}

				var project = [[ProjectFigure alloc] 
								initX: x 
								y: y 
								label: name
								backColor: backColor 
								foreColor: foreColor];
				[drawing addSubview: project];

				[projectMapping setObject: project forKey: name];

			} else if ([spec count] == 2){
				//create connection
				var fromAsString = [spec objectAtIndex: 0];
				var toAsString = [spec objectAtIndex: 1];

				var from = [projectMapping objectForKey: fromAsString];
				var to = [projectMapping objectForKey: toAsString];

				var connection = [[Connection alloc] 
								initWithSource: from 
								target: to];

				[drawing addSubview: connection];
			}
		}

	}
}

- (void)connection:(CPURLConnection)connection didFailWithError:(CPString)error
{
	var alertW = [CPAlert alertWithError: @"Mmm something went wrong, try with a different set of changes"];
	[alertW setTitle: @"Message"];
	[alertW runModal];
}

- (void)initializeDrawingServices:(id) contentView
{
	drawing = [[Drawing alloc] initWithFrame: [contentView bounds]];
	[drawing showGrid: YES];
	[drawing gridSize: 20];
	//[drawing snapToGrid: YES];
	[contentView addSubview: drawing];
}

- (void)initializeDrawing:(id) contentView
{
	var drawing = [[Drawing alloc] initWithFrame: [contentView bounds]];
	[drawing showGrid: YES];
	[drawing gridSize: 30];
	//[drawing snapToGrid: YES];
	var interaction1 = [[Interaction alloc] initWithFrame:CGRectMake(10, 10, 225, 125)];
	var interaction2 = [[Interaction alloc] initWithFrame:CGRectMake(400, 150, 225, 125)];
	var connection = [[Connection alloc] initWithSource: interaction1 target: interaction2];
	
	var points = [CPArray array];
	points = [points arrayByAddingObject: CGPointMake(100, 50)];
	points = [points arrayByAddingObject: CGPointMake(200, 380)];
	points = [points arrayByAddingObject: CGPointMake(60, 270)];
	var polyline = [[Polyline alloc] initWithPoints: points];
	var label = [[Widget alloc] initWithFrame:CGRectMake(0, 0, 100, 25)];

    [interaction1 addWidget: label];
	[contentView addSubview: drawing];
	[drawing addSubview: interaction1];
	[drawing addSubview: interaction2];
	[drawing addSubview: connection];
}
@end
