@STATIC;1.0;p;9;CupDraw.jt;2013;@STATIC;1.0;i;23;CPCancelableTextField.ji;22;LPMultiLineTextField.ji;15;GeometryUtils.ji;14;HandleMagnet.ji;8;Figure.ji;17;CompositeFigure.ji;7;Model.ji;10;Property.ji;6;Grid.ji;9;Drawing.ji;14;DrawingModel.ji;8;Handle.ji;17;CompositeFigure.ji;10;Polyline.ji;12;Connection.ji;13;ImageFigure.ji;15;ToolboxFigure.ji;13;LabelFigure.ji;18;PropertiesFigure.ji;17;IconLabelFigure.ji;17;RectangleFigure.ji;11;ToolState.ji;27;SelectionToolInitialState.ji;15;SelectedState.ji;18;MoveFiguresState.ji;17;MoveHandleState.ji;6;Tool.ji;18;StateMachineTool.ji;15;SelectionTool.ji;26;AbstractCreateFigureTool.ji;17;CreateImageTool.ji;17;CreateLabelTool.ji;16;EditorDelegate.jt;1347;
objj_executeFile("CPCancelableTextField.j",YES);
objj_executeFile("LPMultiLineTextField.j",YES);
objj_executeFile("GeometryUtils.j",YES);
objj_executeFile("HandleMagnet.j",YES);
objj_executeFile("Figure.j",YES);
objj_executeFile("CompositeFigure.j",YES);
objj_executeFile("Model.j",YES);
objj_executeFile("Property.j",YES);
objj_executeFile("Grid.j",YES);
objj_executeFile("Drawing.j",YES);
objj_executeFile("DrawingModel.j",YES);
objj_executeFile("Handle.j",YES);
objj_executeFile("CompositeFigure.j",YES);
objj_executeFile("Polyline.j",YES);
objj_executeFile("Connection.j",YES);
objj_executeFile("ImageFigure.j",YES);
objj_executeFile("ToolboxFigure.j",YES);
objj_executeFile("LabelFigure.j",YES);
objj_executeFile("PropertiesFigure.j",YES);
objj_executeFile("IconLabelFigure.j",YES);
objj_executeFile("RectangleFigure.j",YES);
objj_executeFile("ToolState.j",YES);
objj_executeFile("SelectionToolInitialState.j",YES);
objj_executeFile("SelectedState.j",YES);
objj_executeFile("MoveFiguresState.j",YES);
objj_executeFile("MoveHandleState.j",YES);
objj_executeFile("Tool.j",YES);
objj_executeFile("StateMachineTool.j",YES);
objj_executeFile("SelectionTool.j",YES);
objj_executeFile("AbstractCreateFigureTool.j",YES);
objj_executeFile("CreateImageTool.j",YES);
objj_executeFile("CreateLabelTool.j",YES);
objj_executeFile("EditorDelegate.j",YES);
p;9;Drawing.jt;5493;@STATIC;1.0;t;5474;
DrawingSelectionChangedNotification="DrawingSelectionChangedNotification";
var _1=objj_allocateClassPair(CompositeFigure,"Drawing"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_currentTool"),new objj_ivar("_selectedFigure"),new objj_ivar("_backgroundLayer"),new objj_ivar("_toolbox"),new objj_ivar("_properties")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("init"),function(_3,_4){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("Drawing").super_class},"init");
_currentTool=objj_msgSend(SelectionTool,"drawing:",_3);
_selectedFigure=nil;
_selectable=false;
_moveable=false;
_editable=false;
objj_msgSend(_3,"model:",objj_msgSend(DrawingModel,"new"));
return _3;
}
}),new objj_method(sel_getUid("initWithFrame:"),function(_5,_6,_7){
with(_5){
objj_msgSendSuper({receiver:_5,super_class:objj_getClass("Drawing").super_class},"initWithFrame:",_7);
_backgroundLayer=objj_msgSend(CompositeFigure,"frame:",_7);
objj_msgSend(_backgroundLayer,"selectable:",NO);
objj_msgSend(_backgroundLayer,"moveable:",NO);
objj_msgSend(_backgroundLayer,"setAutoresizingMask:",CPViewHeightSizable|CPViewWidthSizable);
objj_msgSend(_5,"addFigure:",_backgroundLayer);
objj_msgSend(_5,"computeBackgroundLayer");
return _5;
}
}),new objj_method(sel_getUid("toolbox:"),function(_8,_9,_a){
with(_8){
_toolbox=_a;
objj_msgSend(_8,"addFigure:",_toolbox);
}
}),new objj_method(sel_getUid("properties:"),function(_b,_c,_d){
with(_b){
_properties=_d;
objj_msgSend(_b,"addFigure:",_properties);
}
}),new objj_method(sel_getUid("computeBackgroundLayer"),function(_e,_f){
with(_e){
objj_msgSend(_backgroundLayer,"clearFigures");
var _10=CGRectMake(0,0,1600,1600);
var _11=objj_msgSend(Grid,"frame:showGrid:gridSize:",_10,objj_msgSend(_e,"showGrid"),objj_msgSend(_e,"gridSize"));
objj_msgSend(_backgroundLayer,"addFigure:",_11);
}
}),new objj_method(sel_getUid("select"),function(_12,_13){
with(_12){
objj_msgSendSuper({receiver:_12,super_class:objj_getClass("Drawing").super_class},"select");
objj_msgSend(objj_msgSend(_12,"window"),"makeFirstResponder:",_12);
}
}),new objj_method(sel_getUid("drawing"),function(_14,_15){
with(_14){
return _14;
}
}),new objj_method(sel_getUid("showGrid"),function(_16,_17){
with(_16){
return objj_msgSend(objj_msgSend(objj_msgSend(_16,"model"),"propertyValue:","Show grid?"),"boolValue");
}
}),new objj_method(sel_getUid("snapToGrid"),function(_18,_19){
with(_18){
return objj_msgSend(objj_msgSend(objj_msgSend(_18,"model"),"propertyValue:","Snap to grid?"),"boolValue");
}
}),new objj_method(sel_getUid("floatingToolboxes"),function(_1a,_1b){
with(_1a){
return objj_msgSend(objj_msgSend(objj_msgSend(_1a,"model"),"propertyValue:","Floating toolboxes?"),"boolValue");
}
}),new objj_method(sel_getUid("gridSize"),function(_1c,_1d){
with(_1c){
return objj_msgSend(objj_msgSend(objj_msgSend(_1c,"model"),"propertyValue:","Grid size"),"intValue");
}
}),new objj_method(sel_getUid("mouseDown:"),function(_1e,_1f,_20){
with(_1e){
objj_msgSend(_currentTool,"mouseDown:",_20);
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_21,_22,_23){
with(_21){
objj_msgSend(_currentTool,"mouseDragged:",_23);
}
}),new objj_method(sel_getUid("mouseUp:"),function(_24,_25,_26){
with(_24){
objj_msgSend(_currentTool,"mouseUp:",_26);
}
}),new objj_method(sel_getUid("acceptsFirstResponder"),function(_27,_28){
with(_27){
return YES;
}
}),new objj_method(sel_getUid("keyUp:"),function(_29,_2a,_2b){
with(_29){
objj_msgSend(_currentTool,"keyUp:",_2b);
}
}),new objj_method(sel_getUid("keyDown:"),function(_2c,_2d,_2e){
with(_2c){
objj_msgSend(_currentTool,"keyDown:",_2e);
}
}),new objj_method(sel_getUid("unselectAll"),function(_2f,_30){
with(_2f){
var _31=objj_msgSend(_2f,"subviews");
for(var i=objj_msgSend(_31,"count")-1;i>=0;i--){
var _32=objj_msgSend(_31,"objectAtIndex:",i);
objj_msgSend(_32,"unselect");
}
}
}),new objj_method(sel_getUid("tool:"),function(_33,_34,_35){
with(_33){
objj_msgSend(_currentTool,"release");
_currentTool=_35;
}
}),new objj_method(sel_getUid("selectedFigure"),function(_36,_37){
with(_36){
return _selectedFigure;
}
}),new objj_method(sel_getUid("selectedFigure:"),function(_38,_39,_3a){
with(_38){
_selectedFigure=_3a;
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"postNotificationName:object:",DrawingSelectionChangedNotification,_38);
}
}),new objj_method(sel_getUid("modelChanged"),function(_3b,_3c){
with(_3b){
objj_msgSend(_3b,"computeBackgroundLayer");
var _3d=objj_msgSend(_3b,"floatingToolboxes");
var _3e=objj_msgSend(_3b,"frame");
if(_toolbox!=nil){
objj_msgSend(_toolbox,"selectable:",_3d);
objj_msgSend(_toolbox,"moveable:",_3d);
if(!_3d){
var _3f=objj_msgSend(_toolbox,"frame");
var _40=CGRectMake(0,0,_3f.size.width,_3e.size.height);
objj_msgSend(_toolbox,"setFrame:",_40);
objj_msgSend(_toolbox,"setAutoresizingMask:",CPViewHeightSizable);
}else{
objj_msgSend(_toolbox,"sizeToFit");
}
}
if(_properties!=nil){
objj_msgSend(_properties,"selectable:",_3d);
objj_msgSend(_properties,"moveable:",_3d);
if(!_3d){
var _41;
if(_toolbox!=nil){
_41=objj_msgSend(_toolbox,"frame").size.width;
}else{
_41=0;
}
var _3f=objj_msgSend(_properties,"frame");
var _40=CGRectMake(_41,_3e.size.height-_3f.size.height,_3e.size.width,_3f.size.height);
objj_msgSend(_properties,"setFrame:",_40);
objj_msgSend(_properties,"setAutoresizingMask:",CPViewMinYMargin|CPViewWidthSizable);
}else{
objj_msgSend(_properties,"setFrame:",objj_msgSend(PropertiesFigure,"defaultFrame"));
}
}
}
})]);
p;14;DrawingModel.jt;589;@STATIC;1.0;t;571;
var _1=objj_allocateClassPair(Model,"DrawingModel"),_2=_1.isa;
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("init"),function(_3,_4){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("DrawingModel").super_class},"init");
objj_msgSend(_3,"addProperty:value:","Name","");
objj_msgSend(_3,"addProperty:value:","Show grid?",YES);
objj_msgSend(_3,"addProperty:value:","Grid size",20);
objj_msgSend(_3,"addProperty:value:","Snap to grid?",NO);
objj_msgSend(_3,"addProperty:value:","Floating toolboxes?",YES);
return _3;
}
})]);
p;6;Grid.jt;1485;@STATIC;1.0;t;1466;
var _1=objj_allocateClassPair(Figure,"Grid"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_showGrid"),new objj_ivar("_gridSize"),new objj_ivar("_gridColor")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithFrame:showGrid:gridSize:"),function(_3,_4,_5,_6,_7){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("Grid").super_class},"initWithFrame:",_5);
_showGrid=_6;
_gridSize=_7;
_gridColor=objj_msgSend(CPColor,"colorWithHexString:","F7F0F3");
return _3;
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_8,_9,_a,_b){
with(_8){
CGContextSetFillColor(_b,objj_msgSend(CPColor,"colorWithHexString:","FEFEFE"));
CGContextFillRect(_b,_a);
if(_showGrid){
CGContextSetLineWidth(_b,0.25);
for(var p=0;p<=_a.size.width;p=p+_gridSize){
objj_msgSend(_8,"drawGridLineX:y:x:y:context:",p,0,p,_a.size.height,_b);
}
for(var p=0;p<=_a.size.height;p=p+_gridSize){
objj_msgSend(_8,"drawGridLineX:y:x:y:context:",0,p,_a.size.width,p,_b);
}
}
}
}),new objj_method(sel_getUid("drawGridLineX:y:x:y:context:"),function(_c,_d,x1,y1,x2,y2,_e){
with(_c){
CGContextMoveToPoint(_e,x1,y1);
CGContextAddLineToPoint(_e,x2,y2);
CGContextSetStrokeColor(_e,_gridColor);
CGContextStrokePath(_e);
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("frame:showGrid:gridSize:"),function(_f,_10,_11,_12,_13){
with(_f){
var _14=objj_msgSend(objj_msgSend(_f,"alloc"),"initWithFrame:showGrid:gridSize:",_11,_12,_13);
return _14;
}
})]);
p;23;CPCancelableTextField.jt;589;@STATIC;1.0;t;571;
var _1=objj_allocateClassPair(CPTextField,"CPCancelableTextField"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_cancelator")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("cancelator:"),function(_3,_4,_5){
with(_3){
_cancelator=_5;
}
}),new objj_method(sel_getUid("keyDown:"),function(_6,_7,_8){
with(_6){
objj_msgSendSuper({receiver:_6,super_class:objj_getClass("CPCancelableTextField").super_class},"keyDown:",_8);
if(_cancelator!=nil&&objj_msgSend(_8,"keyCode")==CPKeyCodes.ESC){
objj_msgSend(_cancelator,"cancelEditing");
}
}
})]);
p;22;LPMultiLineTextField.jt;8046;@STATIC;1.0;I;20;AppKit/CPTextField.jt;8002;
objj_executeFile("AppKit/CPTextField.j",NO);
var _1=nil;
var _2=objj_allocateClassPair(CPTextField,"LPMultiLineTextField"),_3=_2.isa;
class_addIvars(_2,[new objj_ivar("_DOMTextareaElement"),new objj_ivar("_stringValue"),new objj_ivar("_hideOverflow")]);
objj_registerClassPair(_2);
class_addMethods(_2,[new objj_method(sel_getUid("_DOMTextareaElement"),function(_4,_5){
with(_4){
if(!_DOMTextareaElement){
_DOMTextareaElement=document.createElement("textarea");
_DOMTextareaElement.style.position="absolute";
_DOMTextareaElement.style.background="none";
_DOMTextareaElement.style.border="0";
_DOMTextareaElement.style.outline="0";
_DOMTextareaElement.style.zIndex="100";
_DOMTextareaElement.style.resize="none";
_DOMTextareaElement.style.padding="0";
_DOMTextareaElement.style.margin="0";
_DOMTextareaElement.onkeyup=function(_6){
objj_msgSend(_1,"keyUp:",nil);
};
_DOMTextareaElement.onblur=function(){
objj_msgSend(objj_msgSend(_1,"window"),"makeFirstResponder:",nil);
_1=nil;
};
_4._DOMElement.appendChild(_DOMTextareaElement);
}
return _DOMTextareaElement;
}
}),new objj_method(sel_getUid("initWithFrame:"),function(_7,_8,_9){
with(_7){
if(_7=objj_msgSendSuper({receiver:_7,super_class:objj_getClass("LPMultiLineTextField").super_class},"initWithFrame:",_9)){
}
return _7;
}
}),new objj_method(sel_getUid("isScrollable"),function(_a,_b){
with(_a){
return !_hideOverflow;
}
}),new objj_method(sel_getUid("setScrollable:"),function(_c,_d,_e){
with(_c){
_hideOverflow=!_e;
}
}),new objj_method(sel_getUid("setEditable:"),function(_f,_10,_11){
with(_f){
objj_msgSend(_f,"_DOMTextareaElement").style.cursor=_11?"cursor":"default";
objj_msgSendSuper({receiver:_f,super_class:objj_getClass("LPMultiLineTextField").super_class},"setEditable:",_11);
}
}),new objj_method(sel_getUid("selectText:"),function(_12,_13,_14){
with(_12){
objj_msgSend(_12,"_DOMTextareaElement").select();
}
}),new objj_method(sel_getUid("layoutSubviews"),function(_15,_16){
with(_15){
objj_msgSendSuper({receiver:_15,super_class:objj_getClass("LPMultiLineTextField").super_class},"layoutSubviews");
var _17=objj_msgSend(_15,"layoutEphemeralSubviewNamed:positioned:relativeToEphemeralSubviewNamed:","content-view",CPWindowAbove,"bezel-view");
objj_msgSend(_17,"setHidden:",YES);
var _18=objj_msgSend(_15,"_DOMTextareaElement"),_19=objj_msgSend(_15,"currentValueForThemeAttribute:","content-inset"),_1a=objj_msgSend(_15,"bounds");
_18.style.top=_19.top+"px";
_18.style.bottom=_19.bottom+"px";
_18.style.left=_19.left+"px";
_18.style.right=_19.right+"px";
_18.style.width=(CGRectGetWidth(_1a)-_19.left-_19.right)+"px";
_18.style.height=(CGRectGetHeight(_1a)-_19.top-_19.bottom)+"px";
_18.style.color=objj_msgSend(objj_msgSend(_15,"currentValueForThemeAttribute:","text-color"),"cssString");
_18.style.font=objj_msgSend(objj_msgSend(_15,"currentValueForThemeAttribute:","font"),"cssString");
switch(objj_msgSend(_15,"currentValueForThemeAttribute:","alignment")){
case CPLeftTextAlignment:
_18.style.textAlign="left";
break;
case CPJustifiedTextAlignment:
_18.style.textAlign="justify";
break;
case CPCenterTextAlignment:
_18.style.textAlign="center";
break;
case CPRightTextAlignment:
_18.style.textAlign="right";
break;
default:
_18.style.textAlign="left";
}
_18.value=_stringValue||"";
if(_hideOverflow){
_18.style.overflow="hidden";
}
}
}),new objj_method(sel_getUid("scrollWheel:"),function(_1b,_1c,_1d){
with(_1b){
var _1e=objj_msgSend(_1b,"_DOMTextareaElement");
_1e.scrollLeft+=_1d._deltaX;
_1e.scrollTop+=_1d._deltaY;
}
}),new objj_method(sel_getUid("mouseDown:"),function(_1f,_20,_21){
with(_1f){
if(objj_msgSend(_1f,"isEditable")&&objj_msgSend(_1f,"isEnabled")){
objj_msgSend(objj_msgSend(objj_msgSend(_1f,"window"),"platformWindow"),"_propagateCurrentDOMEvent:",YES);
}else{
objj_msgSendSuper({receiver:_1f,super_class:objj_getClass("LPMultiLineTextField").super_class},"mouseDown:",_21);
}
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_22,_23,_24){
with(_22){
return objj_msgSend(objj_msgSend(objj_msgSend(_24,"window"),"platformWindow"),"_propagateCurrentDOMEvent:",YES);
}
}),new objj_method(sel_getUid("keyDown:"),function(_25,_26,_27){
with(_25){
if(objj_msgSend(_27,"keyCode")===CPTabKeyCode){
if(objj_msgSend(_27,"modifierFlags")&CPShiftKeyMask){
objj_msgSend(objj_msgSend(_25,"window"),"selectPreviousKeyView:",_25);
}else{
objj_msgSend(objj_msgSend(_25,"window"),"selectNextKeyView:",_25);
}
if(objj_msgSend(objj_msgSend(objj_msgSend(_25,"window"),"firstResponder"),"respondsToSelector:",sel_getUid("selectText:"))){
objj_msgSend(objj_msgSend(objj_msgSend(_25,"window"),"firstResponder"),"selectText:",_25);
}
objj_msgSend(objj_msgSend(objj_msgSend(_25,"window"),"platformWindow"),"_propagateCurrentDOMEvent:",NO);
}else{
objj_msgSend(objj_msgSend(objj_msgSend(_25,"window"),"platformWindow"),"_propagateCurrentDOMEvent:",YES);
}
objj_msgSend(objj_msgSend(CPRunLoop,"currentRunLoop"),"limitDateForMode:",CPDefaultRunLoopMode);
}
}),new objj_method(sel_getUid("keyUp:"),function(_28,_29,_2a){
with(_28){
if(_stringValue!==objj_msgSend(_28,"stringValue")){
_stringValue=objj_msgSend(_28,"stringValue");
if(!_isEditing){
_isEditing=YES;
objj_msgSend(_28,"textDidBeginEditing:",objj_msgSend(CPNotification,"notificationWithName:object:userInfo:",CPControlTextDidBeginEditingNotification,_28,nil));
}
objj_msgSend(_28,"textDidChange:",objj_msgSend(CPNotification,"notificationWithName:object:userInfo:",CPControlTextDidChangeNotification,_28,nil));
}
objj_msgSend(objj_msgSend(objj_msgSend(_28,"window"),"platformWindow"),"_propagateCurrentDOMEvent:",YES);
}
}),new objj_method(sel_getUid("becomeFirstResponder"),function(_2b,_2c){
with(_2b){
_stringValue=objj_msgSend(_2b,"stringValue");
objj_msgSend(_2b,"setThemeState:",CPThemeStateEditing);
setTimeout(function(){
objj_msgSend(_2b,"_DOMTextareaElement").focus();
_1=_2b;
},0);
objj_msgSend(_2b,"textDidFocus:",objj_msgSend(CPNotification,"notificationWithName:object:userInfo:",CPTextFieldDidFocusNotification,_2b,nil));
return YES;
}
}),new objj_method(sel_getUid("resignFirstResponder"),function(_2d,_2e){
with(_2d){
objj_msgSend(_2d,"unsetThemeState:",CPThemeStateEditing);
objj_msgSend(_2d,"setStringValue:",objj_msgSend(_2d,"stringValue"));
objj_msgSend(_2d,"_DOMTextareaElement").blur();
if(_isEditing){
_isEditing=NO;
objj_msgSend(_2d,"textDidEndEditing:",objj_msgSend(CPNotification,"notificationWithName:object:userInfo:",CPControlTextDidEndEditingNotification,_2d,nil));
if(objj_msgSend(_2d,"sendsActionOnEndEditing")){
objj_msgSend(_2d,"sendAction:to:",objj_msgSend(_2d,"action"),objj_msgSend(_2d,"target"));
}
}
objj_msgSend(_2d,"textDidBlur:",objj_msgSend(CPNotification,"notificationWithName:object:userInfo:",CPTextFieldDidBlurNotification,_2d,nil));
return YES;
}
}),new objj_method(sel_getUid("stringValue"),function(_2f,_30){
with(_2f){
return (!!_DOMTextareaElement)?_DOMTextareaElement.value:"";
}
}),new objj_method(sel_getUid("setStringValue:"),function(_31,_32,_33){
with(_31){
_stringValue=_33;
objj_msgSend(_31,"setNeedsLayout");
}
})]);
var _34="LPMultiLineTextFieldStringValueKey",_35="LPMultiLineTextFieldScrollableKey";
var _2=objj_getClass("LPMultiLineTextField");
if(!_2){
throw new SyntaxError("*** Could not find definition for class \"LPMultiLineTextField\"");
}
var _3=_2.isa;
class_addMethods(_2,[new objj_method(sel_getUid("initWithCoder:"),function(_36,_37,_38){
with(_36){
if(_36=objj_msgSendSuper({receiver:_36,super_class:objj_getClass("LPMultiLineTextField").super_class},"initWithCoder:",_38)){
objj_msgSend(_36,"setStringValue:",objj_msgSend(_38,"decodeObjectForKey:",_34));
objj_msgSend(_36,"setScrollable:",objj_msgSend(_38,"decodeBoolForKey:",_35));
}
return _36;
}
}),new objj_method(sel_getUid("encodeWithCoder:"),function(_39,_3a,_3b){
with(_39){
objj_msgSendSuper({receiver:_39,super_class:objj_getClass("LPMultiLineTextField").super_class},"encodeWithCoder:",_3b);
objj_msgSend(_3b,"encodeObject:forKey:",_stringValue,_34);
objj_msgSend(_3b,"encodeBool:forKey:",(_hideOverflow?NO:YES),_35);
}
})]);
p;17;CompositeFigure.jt;628;@STATIC;1.0;t;610;
var _1=objj_allocateClassPair(Figure,"CompositeFigure"),_2=_1.isa;
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("addFigure:"),function(_3,_4,_5){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("CompositeFigure").super_class},"addSubview:",_5);
}
}),new objj_method(sel_getUid("addSubview:"),function(_6,_7,_8){
with(_6){
objj_msgSend(CPException,"raise:reason:","invalid method","Use addFigure instead");
}
}),new objj_method(sel_getUid("clearFigures"),function(_9,_a){
with(_9){
objj_msgSend(_9,"setSubviews:",objj_msgSend(CPMutableArray,"array"));
}
})]);
p;12;Connection.jt;3862;@STATIC;1.0;t;3843;
var _1=objj_allocateClassPair(Polyline,"Connection"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_sourceFigure"),new objj_ivar("_targetFigure"),new objj_ivar("_magnet1"),new objj_ivar("_magnet2"),new objj_ivar("_p1Arrow"),new objj_ivar("_p2Arrow")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithSource:target:"),function(_3,_4,_5,_6){
with(_3){
_sourceFigure=_5;
_targetFigure=_6;
var _7=objj_msgSend(CPMutableArray,"array");
objj_msgSend(_7,"addObject:",objj_msgSend(_sourceFigure,"center"));
if(_sourceFigure==_targetFigure){
var _8=objj_msgSend(_sourceFigure,"center");
objj_msgSend(_7,"addObject:",CGPointMake(_8.x+100,_8.y));
objj_msgSend(_7,"addObject:",CGPointMake(_8.x+100,_8.y-100));
objj_msgSend(_7,"addObject:",CGPointMake(_8.x,_8.y-100));
}
objj_msgSend(_7,"addObject:",objj_msgSend(_targetFigure,"center"));
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("Connection").super_class},"initWithPoints:",_7);
if(_3){
objj_msgSend(_3,"recomputeFrame");
_magnet1=objj_msgSend(objj_msgSend(HandleMagnet,"alloc"),"initWithHandle:source:target:",objj_msgSend(_3,"handleAt:",2),_sourceFigure,_targetFigure);
_magnet2=objj_msgSend(objj_msgSend(HandleMagnet,"alloc"),"initWithHandle:source:target:",objj_msgSend(_3,"handleAt:",0),_targetFigure,_sourceFigure);
objj_msgSend(_magnet1,"updateHandleLocation:",nil);
objj_msgSend(_magnet2,"updateHandleLocation:",nil);
objj_msgSend(_sourceFigure,"addOutConnection:",_3);
objj_msgSend(_targetFigure,"addInConnection:",_3);
return _3;
}
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_9,_a,_b,_c){
with(_9){
objj_msgSendSuper({receiver:_9,super_class:objj_getClass("Connection").super_class},"drawRect:on:",_b,_c);
var _d=objj_msgSend(_points,"objectAtIndex:",(objj_msgSend(_points,"count")-1));
var _e=objj_msgSend(_9,"frameOrigin");
CGContextBeginPath(_c);
CGContextMoveToPoint(_c,_p1Arrow.x-_e.x,_p1Arrow.y-_e.y);
CGContextAddLineToPoint(_c,_p2Arrow.x-_e.x,_p2Arrow.y-_e.y);
CGContextAddLineToPoint(_c,_d.x-_e.x,_d.y-_e.y);
CGContextClosePath(_c);
CGContextSetFillColor(_c,objj_msgSend(_9,"foregroundColor"));
CGContextFillPath(_c);
}
}),new objj_method(sel_getUid("computeArrowPoints"),function(_f,_10){
with(_f){
var _11=objj_msgSend(_points,"objectAtIndex:",(objj_msgSend(_points,"count")-2));
var _12=objj_msgSend(_points,"objectAtIndex:",(objj_msgSend(_points,"count")-1));
var p1=nil;
var p2=nil;
var _13=5;
var _14=7;
if(_11.x==_12.x){
if(_11.y<_12.y){
p1=CPPointMake(_12.x-_13,_12.y-_14);
p2=CPPointMake(_12.x+_13,_12.y-_14);
}else{
p1=CPPointMake(_12.x-_13,_12.y+_14);
p2=CPPointMake(_12.x+_13,_12.y+_14);
}
}else{
if(_11.y==_12.y){
if(_11.x<_12.x){
p1=CPPointMake(_12.x-_14,_12.y-_13);
p2=CPPointMake(_12.x-_14,_12.y+_13);
}else{
p1=CPPointMake(_12.x+_14,_12.y-_13);
p2=CPPointMake(_12.x+_14,_12.y+_13);
}
}else{
var _15=CPPointMake(_12.x-_11.x,_12.y-_11.y);
var _16=SQRT((_15.x*_15.x)+(_15.y*_15.y));
var pi=3.14159265;
var _17=10;
var _18=pi/8;
var _19=_17/(2*(TAN(_18)/2)*_16);
_19=_19/3;
var _1a=CPPointMake(_12.x+-_19*_15.x,_12.y+-_19*_15.y);
var _1b=CPPointMake(-_15.y,_15.x);
var _1c=_17/(2*_16);
var _1d=CPPointMake(_1a.x+_1c*_1b.x,_1a.y+_1c*_1b.y);
var _1e=CPPointMake(_1a.x+-_1c*_1b.x,_1a.y+-_1c*_1b.y);
p1=_1d;
p2=_1e;
}
}
_p1Arrow=p1;
_p2Arrow=p2;
}
}),new objj_method(sel_getUid("recomputeFrame"),function(_1f,_20){
with(_1f){
objj_msgSend(_1f,"computeArrowPoints");
var _21=objj_msgSend(CPMutableArray,"arrayWithArray:",_points);
objj_msgSend(_21,"addObject:",_p1Arrow);
objj_msgSend(_21,"addObject:",_p2Arrow);
var _22=objj_msgSend(GeometryUtils,"computeFrameFor:",_21);
objj_msgSend(_1f,"setFrame:",_22);
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("source:target:"),function(_23,_24,_25,_26){
with(_23){
return objj_msgSend(objj_msgSend(_23,"new"),"initWithSource:target:",_25,_26);
}
})]);
p;8;Figure.jt;9826;@STATIC;1.0;t;9807;
var _1=objj_allocateClassPair(CPView,"Figure"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("handles"),new objj_ivar("_inConnections"),new objj_ivar("_outConnections"),new objj_ivar("_backgroundColor"),new objj_ivar("_foregroundColor"),new objj_ivar("_selectable"),new objj_ivar("_moveable"),new objj_ivar("_editable"),new objj_ivar("_model"),new objj_ivar("_selected")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("init"),function(_3,_4){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("Figure").super_class},"init");
handles=objj_msgSend(CPMutableArray,"array");
_inConnections=objj_msgSend(CPMutableArray,"array");
_outConnections=objj_msgSend(CPMutableArray,"array");
_backgroundColor=objj_msgSend(CPColor,"blackColor");
_foregroundColor=_backgroundColor;
_selectable=true;
_moveable=true;
_editable=true;
_selected=false;
objj_msgSend(_3,"setPostsFrameChangedNotifications:",YES);
return _3;
}
}),new objj_method(sel_getUid("figureAt:"),function(_5,_6,_7){
with(_5){
var _8=objj_msgSend(_5,"subviews");
var _9=objj_msgSend(_5,"frameOrigin");
var _a=CGPointMake(_7.x-_9.x,_7.y-_9.y);
for(var i=objj_msgSend(_8,"count")-1;i>=0;i--){
var _b=objj_msgSend(_8,"objectAtIndex:",i);
var _c;
if(objj_msgSend(_b,"respondsToSelector:",sel_getUid("figureAt:"))){
_c=objj_msgSend(_b,"figureAt:",_a);
}else{
if(CPRectContainsPoint(objj_msgSend(_b,"frame"),_a)){
_c=_5;
}else{
_c=nil;
}
}
if(_c!=nil){
return _c;
}
}
return objj_msgSend(_5,"primFigureAt:",_7);
}
}),new objj_method(sel_getUid("primFigureAt:"),function(_d,_e,_f){
with(_d){
var _10=objj_msgSend(_d,"frame");
if(CPRectContainsPoint(_10,_f)){
return _d;
}else{
return nil;
}
}
}),new objj_method(sel_getUid("globalToLocal:"),function(_11,_12,_13){
with(_11){
var _14=_11;
var _15=CGPointMake(0,0);
while(_14!=nil&&!objj_msgSend(_14,"isKindOfClass:",objj_msgSend(Drawing,"class"))){
var _16=objj_msgSend(_14,"frameOrigin");
_15=CGPointMake(_15.x-_16.x,_15.y-_16.y);
_14=objj_msgSend(_14,"superview");
}
var _17=CGPointMake(_13.x+_15.x,_13.y+_15.y);
return _17;
}
}),new objj_method(sel_getUid("addInConnection:"),function(_18,_19,_1a){
with(_18){
objj_msgSend(_inConnections,"addObject:",_1a);
}
}),new objj_method(sel_getUid("addOutConnection:"),function(_1b,_1c,_1d){
with(_1b){
objj_msgSend(_outConnections,"addObject:",_1d);
}
}),new objj_method(sel_getUid("moveTo:"),function(_1e,_1f,_20){
with(_1e){
if(_moveable){
objj_msgSend(_1e,"setFrameOrigin:",_20);
}
}
}),new objj_method(sel_getUid("handleAt:"),function(_21,_22,_23){
with(_21){
return objj_msgSend(handles,"objectAtIndex:",_23);
}
}),new objj_method(sel_getUid("borderColor"),function(_24,_25){
with(_24){
return objj_msgSend(CPColor,"blackColor");
}
}),new objj_method(sel_getUid("handleColor"),function(_26,_27){
with(_26){
return objj_msgSend(_26,"borderColor");
}
}),new objj_method(sel_getUid("isSelectable"),function(_28,_29){
with(_28){
return _selectable;
}
}),new objj_method(sel_getUid("isMoveable"),function(_2a,_2b){
with(_2a){
return _moveable;
}
}),new objj_method(sel_getUid("isEditable"),function(_2c,_2d){
with(_2c){
return _editable;
}
}),new objj_method(sel_getUid("selectable:"),function(_2e,_2f,_30){
with(_2e){
_selectable=_30;
}
}),new objj_method(sel_getUid("moveable:"),function(_31,_32,_33){
with(_31){
_moveable=_33;
}
}),new objj_method(sel_getUid("editable:"),function(_34,_35,_36){
with(_34){
_editable=_36;
}
}),new objj_method(sel_getUid("isHandle"),function(_37,_38){
with(_37){
return false;
}
}),new objj_method(sel_getUid("invalidate"),function(_39,_3a){
with(_39){
objj_msgSend(_39,"setNeedsDisplay:",YES);
}
}),new objj_method(sel_getUid("switchToEditMode"),function(_3b,_3c){
with(_3b){
}
}),new objj_method(sel_getUid("select"),function(_3d,_3e){
with(_3d){
_selected=true;
var _3f=objj_msgSend(_3d,"superview");
for(var i=0;i<objj_msgSend(handles,"count");i++){
var _40=objj_msgSend(handles,"objectAtIndex:",i);
objj_msgSend(_3f,"addFigure:",_40);
}
}
}),new objj_method(sel_getUid("unselect"),function(_41,_42){
with(_41){
_selected=false;
for(var i=0;i<objj_msgSend(handles,"count");i++){
var _43=objj_msgSend(handles,"objectAtIndex:",i);
objj_msgSend(_43,"removeFromSuperview");
}
}
}),new objj_method(sel_getUid("drawing"),function(_44,_45){
with(_44){
return objj_msgSend(objj_msgSend(_44,"superview"),"drawing");
}
}),new objj_method(sel_getUid("handles"),function(_46,_47){
with(_46){
return handles;
}
}),new objj_method(sel_getUid("drawRect:"),function(_48,_49,_4a){
with(_48){
var _4b=objj_msgSend(objj_msgSend(CPGraphicsContext,"currentContext"),"graphicsPort");
objj_msgSend(_48,"drawRect:on:",_4a,_4b);
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_4c,_4d,_4e,_4f){
with(_4c){
}
}),new objj_method(sel_getUid("topLeft"),function(_50,_51){
with(_50){
return CGPointMake(objj_msgSend(_50,"frame").origin.x,objj_msgSend(_50,"frame").origin.y);
}
}),new objj_method(sel_getUid("topLeft:"),function(_52,_53,_54){
with(_52){
var _55=objj_msgSend(_52,"frame");
var _56=_55.origin.x-_54.x;
var _57=_55.origin.y-_54.y;
var _58=CGRectMake(_54.x,_54.y,_55.size.width+_56,_55.size.height+_57);
objj_msgSend(_52,"setFrame:",_58);
}
}),new objj_method(sel_getUid("topMiddle"),function(_59,_5a){
with(_59){
return CGPointMake(objj_msgSend(_59,"frame").origin.x+(objj_msgSend(_59,"frame").size.width/2),objj_msgSend(_59,"frame").origin.y);
}
}),new objj_method(sel_getUid("topMiddle:"),function(_5b,_5c,_5d){
with(_5b){
var _5e=objj_msgSend(_5b,"frame");
var _5f=0;
var _60=_5d.y-_5e.origin.y;
var _61=CGRectMake(_5e.origin.x,_5e.origin.y+_60,_5e.size.width+_5f,_5e.size.height-_60);
objj_msgSend(_5b,"setFrame:",_61);
}
}),new objj_method(sel_getUid("topRight"),function(_62,_63){
with(_62){
return CGPointMake(objj_msgSend(_62,"frame").origin.x+objj_msgSend(_62,"frame").size.width,objj_msgSend(_62,"frame").origin.y);
}
}),new objj_method(sel_getUid("topRight:"),function(_64,_65,_66){
with(_64){
var _67=objj_msgSend(_64,"frame");
var _68=_66.x-(_67.origin.x+_67.size.width);
var _69=_66.y-_67.origin.y;
var _6a=CGRectMake(_67.origin.x,_67.origin.y+_69,_67.size.width+_68,_67.size.height-_69);
objj_msgSend(_64,"setFrame:",_6a);
}
}),new objj_method(sel_getUid("middleLeft"),function(_6b,_6c){
with(_6b){
return CGPointMake(objj_msgSend(_6b,"frame").origin.x,objj_msgSend(_6b,"frame").origin.y+(objj_msgSend(_6b,"frame").size.height/2));
}
}),new objj_method(sel_getUid("middleLeft:"),function(_6d,_6e,_6f){
with(_6d){
var _70=objj_msgSend(_6d,"frame");
var _71=_70.origin.x-_6f.x;
var _72=0;
var _73=CGRectMake(_6f.x,_70.origin.y,_70.size.width+_71,_70.size.height+_72);
objj_msgSend(_6d,"setFrame:",_73);
}
}),new objj_method(sel_getUid("middleRight"),function(_74,_75){
with(_74){
return CGPointMake(objj_msgSend(_74,"frame").origin.x+objj_msgSend(_74,"frame").size.width,objj_msgSend(_74,"frame").origin.y+(objj_msgSend(_74,"frame").size.height/2));
}
}),new objj_method(sel_getUid("middleRight:"),function(_76,_77,_78){
with(_76){
var _79=objj_msgSend(_76,"frame");
var _7a=_78.x-(_79.origin.x+_79.size.width);
var _7b=0;
var _7c=CGRectMake(_79.origin.x,_79.origin.y,_79.size.width+_7a,_79.size.height+_7b);
objj_msgSend(_76,"setFrame:",_7c);
}
}),new objj_method(sel_getUid("bottomLeft"),function(_7d,_7e){
with(_7d){
return CGPointMake(objj_msgSend(_7d,"frame").origin.x,objj_msgSend(_7d,"frame").origin.y+objj_msgSend(_7d,"frame").size.height);
}
}),new objj_method(sel_getUid("bottomLeft:"),function(_7f,_80,_81){
with(_7f){
var _82=objj_msgSend(_7f,"frame");
var _83=_82.origin.x-_81.x;
var _84=_81.y-(_82.origin.y+_82.size.height);
var _85=CGRectMake(_81.x,_82.origin.y,_82.size.width+_83,_82.size.height+_84);
objj_msgSend(_7f,"setFrame:",_85);
}
}),new objj_method(sel_getUid("bottomMiddle"),function(_86,_87){
with(_86){
return CGPointMake(objj_msgSend(_86,"frame").origin.x+(objj_msgSend(_86,"frame").size.width/2),objj_msgSend(_86,"frame").origin.y+objj_msgSend(_86,"frame").size.height);
}
}),new objj_method(sel_getUid("bottomMiddle:"),function(_88,_89,_8a){
with(_88){
var _8b=objj_msgSend(_88,"frame");
var _8c=0;
var _8d=_8a.y-(_8b.origin.y+_8b.size.height);
var _8e=CGRectMake(_8b.origin.x,_8b.origin.y,_8b.size.width+_8c,_8b.size.height+_8d);
objj_msgSend(_88,"setFrame:",_8e);
}
}),new objj_method(sel_getUid("bottomRight"),function(_8f,_90){
with(_8f){
return CGPointMake(objj_msgSend(_8f,"frame").origin.x+objj_msgSend(_8f,"frame").size.width,objj_msgSend(_8f,"frame").origin.y+objj_msgSend(_8f,"frame").size.height);
}
}),new objj_method(sel_getUid("bottomRight:"),function(_91,_92,_93){
with(_91){
var _94=objj_msgSend(_91,"frame");
var _95=_93.x-(_94.origin.x+_94.size.width);
var _96=_93.y-(_94.origin.y+_94.size.height);
var _97=CGRectMake(_94.origin.x,_94.origin.y,_94.size.width+_95,_94.size.height+_96);
objj_msgSend(_91,"setFrame:",_97);
}
}),new objj_method(sel_getUid("backgroundColor"),function(_98,_99){
with(_98){
return _backgroundColor;
}
}),new objj_method(sel_getUid("foregroundColor"),function(_9a,_9b){
with(_9a){
return _foregroundColor;
}
}),new objj_method(sel_getUid("model"),function(_9c,_9d){
with(_9c){
return _model;
}
}),new objj_method(sel_getUid("model:"),function(_9e,_9f,_a0){
with(_9e){
if(_model!=nil){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_9e,ModelPropertyChangedNotification,_model);
}
_model=_a0;
if(_model!=nil){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_9e,sel_getUid("modelChanged"),ModelPropertyChangedNotification,_model);
}
}
}),new objj_method(sel_getUid("modelChanged"),function(_a1,_a2){
with(_a1){
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("frame:"),function(_a3,_a4,_a5){
with(_a3){
var _a6=objj_msgSend(_a3,"new");
objj_msgSend(_a6,"initWithFrame:",_a5);
return _a6;
}
})]);
p;8;Handle.jt;3675;@STATIC;1.0;t;3656;
var _1=objj_allocateClassPair(Figure,"Handle"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_targetFigure"),new objj_ivar("_getSelector"),new objj_ivar("_setSelector"),new objj_ivar("_extraArgument"),new objj_ivar("_displayColor")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithTarget:getSelector:setSelector:extraArgument:"),function(_3,_4,_5,_6,_7,_8){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("Handle").super_class},"init");
_targetFigure=_5;
_getSelector=_6;
_setSelector=_7;
_extraArgument=_8;
_displayColor=objj_msgSend(_targetFigure,"handleColor");
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("updateLocation:"),"CPViewFrameDidChangeNotification",_targetFigure);
var _9=objj_msgSend(_3,"getPoint");
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("Handle").super_class},"initWithFrame:",CGRectMake(_9.x-4,_9.y-4,8,8));
if(_3){
return _3;
}
}
}),new objj_method(sel_getUid("extraArgument"),function(_a,_b){
with(_a){
return _extraArgument;
}
}),new objj_method(sel_getUid("extraArgument:"),function(_c,_d,_e){
with(_c){
_extraArgument=_e;
}
}),new objj_method(sel_getUid("getSelector:setSelector:"),function(_f,_10,_11,_12){
with(_f){
_getSelector=_11;
_setSelector=_12;
}
}),new objj_method(sel_getUid("getPoint"),function(_13,_14){
with(_13){
var _15=nil;
if(_extraArgument==nil){
_15=objj_msgSend(_targetFigure,"performSelector:",_getSelector);
}else{
_15=objj_msgSend(_targetFigure,"performSelector:withObject:",_getSelector,_extraArgument);
}
return _15;
}
}),new objj_method(sel_getUid("updateLocation:"),function(_16,_17,_18){
with(_16){
var _19=objj_msgSend(_16,"getPoint");
var x=_19.x-4;
var y=_19.y-4;
var _1a=CGPointMake(x,y);
objj_msgSendSuper({receiver:_16,super_class:objj_getClass("Handle").super_class},"setFrameOrigin:",_1a);
}
}),new objj_method(sel_getUid("isHandle"),function(_1b,_1c){
with(_1b){
return true;
}
}),new objj_method(sel_getUid("isMoveable"),function(_1d,_1e){
with(_1d){
return true;
}
}),new objj_method(sel_getUid("targetFigure"),function(_1f,_20){
with(_1f){
return _targetFigure;
}
}),new objj_method(sel_getUid("setFrameOrigin:"),function(_21,_22,_23){
with(_21){
if(_extraArgument==nil){
objj_msgSend(_targetFigure,"performSelector:withObject:",_setSelector,_23);
}else{
objj_msgSend(_targetFigure,"performSelector:withObject:withObject:",_setSelector,_extraArgument,_23);
}
}
}),new objj_method(sel_getUid("displayColor:"),function(_24,_25,_26){
with(_24){
_displayColor=_26;
objj_msgSend(_24,"setNeedsDisplay:",YES);
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_27,_28,_29,_2a){
with(_27){
CGContextSetFillColor(_2a,_displayColor);
CGContextFillRect(_2a,objj_msgSend(_27,"bounds"));
CGContextSetFillColor(_2a,objj_msgSend(CPColor,"whiteColor"));
CGContextFillRect(_2a,CGRectMake(2,2,4,4));
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("target:selector:"),function(_2b,_2c,_2d,_2e){
with(_2b){
return objj_msgSend(_2b,"target:selector:extraArgument:",_2d,_2e,nil);
}
}),new objj_method(sel_getUid("target:selector:extraArgument:"),function(_2f,_30,_31,_32,_33){
with(_2f){
var _34=CPSelectorFromString(_32);
var _35=CPSelectorFromString(objj_msgSend(_32,"stringByAppendingString:",":"));
return objj_msgSend(_2f,"target:getSelector:setSelector:extraArgument:",_31,_34,_35,_33);
}
}),new objj_method(sel_getUid("target:getSelector:setSelector:extraArgument:"),function(_36,_37,_38,_39,_3a,_3b){
with(_36){
return objj_msgSend(objj_msgSend(_36,"alloc"),"initWithTarget:getSelector:setSelector:extraArgument:",_38,_39,_3a,_3b);
}
})]);
p;17;IconLabelFigure.jt;3749;@STATIC;1.0;t;3730;
var _1=objj_allocateClassPair(Figure,"IconLabelFigure"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_label"),new objj_ivar("_iconUrl"),new objj_ivar("_modelFeature"),new objj_ivar("_iconView")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithFrame:iconUrl:"),function(_3,_4,_5,_6){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("IconLabelFigure").super_class},"initWithFrame:",_5);
if(_3){
_iconUrl=_6;
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"middleLeft"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"middleRight"));
var _7=objj_msgSend(objj_msgSend(CPTextField,"alloc"),"initWithFrame:",CGRectMakeZero());
objj_msgSend(_7,"setStringValue:","");
objj_msgSend(_7,"setTextColor:",objj_msgSend(CPColor,"blackColor"));
objj_msgSend(_7,"sizeToFit");
objj_msgSend(_7,"setFrameOrigin:",CGPointMake(22,4));
objj_msgSend(_3,"addSubview:",_7);
_label=_7;
var _8=objj_msgSend(objj_msgSend(CPImage,"alloc"),"initWithContentsOfFile:size:",_iconUrl,CGSizeMake(16,16));
var _9=objj_msgSend(objj_msgSend(CPImageView,"alloc"),"initWithFrame:",CGRectMake(4,4,16,160));
objj_msgSend(_9,"setHasShadow:",NO);
objj_msgSend(_9,"setImageScaling:",CPScaleNone);
_iconView=_9;
var _a=objj_msgSend(_8,"size");
objj_msgSend(_9,"setFrameSize:",_a);
objj_msgSend(_9,"setImage:",_8);
objj_msgSend(_3,"addSubview:",_9);
return _3;
}
}
}),new objj_method(sel_getUid("switchToEditMode"),function(_b,_c){
with(_b){
if(objj_msgSend(_b,"isEditable")){
var _d=objj_msgSend(objj_msgSend(EditorDelegate,"alloc"),"initWithWidget:label:window:figureContainer:drawing:",_label,_label,objj_msgSend(_b,"window"),_b,objj_msgSend(_b,"drawing"));
}
}
}),new objj_method(sel_getUid("value"),function(_e,_f){
with(_e){
return objj_msgSend(objj_msgSend(_e,"model"),"propertyValue:",_modelFeature);
}
}),new objj_method(sel_getUid("value:"),function(_10,_11,_12){
with(_10){
objj_msgSend(objj_msgSend(_10,"model"),"propertyValue:be:",_modelFeature,_12);
}
}),new objj_method(sel_getUid("setEditionResult:"),function(_13,_14,_15){
with(_13){
if(_modelFeature!=nil&&(objj_msgSend(_13,"model")!=nil)){
objj_msgSend(_13,"value:",_15);
}else{
objj_msgSend(_13,"setLabelValue:",_15);
}
}
}),new objj_method(sel_getUid("setLabelValue:"),function(_16,_17,_18){
with(_16){
if(_18==nil){
_18="";
}
objj_msgSend(_label,"setObjectValue:",_18);
objj_msgSend(_label,"sizeToFit");
var _19=objj_msgSend(_16,"frameSize");
_19.width=objj_msgSend(_label,"frameOrigin").x+objj_msgSend(_label,"frameSize").width;
_19.height=objj_msgSend(_label,"frameOrigin").y+objj_msgSend(_label,"frameSize").height;
objj_msgSend(_16,"setFrameSize:",_19);
}
}),new objj_method(sel_getUid("propertyChanged"),function(_1a,_1b){
with(_1a){
var _1c=objj_msgSend(_1a,"value");
objj_msgSend(_1a,"setLabelValue:",_1c);
}
}),new objj_method(sel_getUid("checkModelFeature:"),function(_1d,_1e,_1f){
with(_1d){
if(_modelFeature!=nil&&(objj_msgSend(_1d,"model")!=nil)){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_1d,ModelPropertyChangedNotification,objj_msgSend(_1d,"model"));
}
_modelFeature=_1f;
if(_modelFeature!=nil&&(objj_msgSend(_1d,"model")!=nil)){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_1d,sel_getUid("propertyChanged"),ModelPropertyChangedNotification,objj_msgSend(_1d,"model"));
objj_msgSend(_1d,"propertyChanged");
}
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("newAt:iconUrl:"),function(_20,_21,_22,_23){
with(_20){
var _24=CGRectMake(_22.x,_22.y,100,25);
var _25=objj_msgSend(objj_msgSend(_20,"new"),"initWithFrame:iconUrl:",_24,_23);
return _25;
}
})]);
p;13;ImageFigure.jt;2267;@STATIC;1.0;t;2248;
var _1=objj_allocateClassPair(Figure,"ImageFigure"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_textField"),new objj_ivar("_offset"),new objj_ivar("_showBorder")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initializeWithImage:x:y:offset:"),function(_3,_4,_5,_6,_7,_8){
with(_3){
var _9=CGRectMake(_6,_7,0,0);
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("ImageFigure").super_class},"initWithFrame:",_9);
if(_3){
var _a=objj_msgSend(objj_msgSend(CPImage,"alloc"),"initWithContentsOfFile:",_5);
_offset=_8;
_showBorder=true;
objj_msgSend(_a,"setDelegate:",_3);
return _3;
}
}
}),new objj_method(sel_getUid("showBorder:"),function(_b,_c,_d){
with(_b){
_showBorder=_d;
}
}),new objj_method(sel_getUid("imageDidLoad:"),function(_e,_f,_10){
with(_e){
var _11=objj_msgSend(_10,"size");
var _12=objj_msgSend(objj_msgSend(CPImageView,"alloc"),"initWithFrame:",CGRectMake(_offset,_offset,_11.width,_11.height));
objj_msgSend(_12,"setHasShadow:",NO);
objj_msgSend(_12,"setImageScaling:",CPScaleNone);
var _13=CGSizeMake(_11.width+(_offset*2),_11.height+(_offset*2));
objj_msgSend(_12,"setImage:",_10);
objj_msgSend(_e,"addSubview:",_12);
objj_msgSend(_e,"setFrameSize:",_13);
objj_msgSend(_e,"invalidate");
}
}),new objj_method(sel_getUid("borderColor"),function(_14,_15){
with(_14){
return objj_msgSend(CPColor,"colorWithHexString:","#EAEAEA");
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_16,_17,_18,_19){
with(_16){
CGContextSetFillColor(_19,objj_msgSend(CPColor,"whiteColor"));
CGContextFillRect(_19,objj_msgSend(_16,"bounds"));
if(_showBorder){
CGContextSetLineWidth(_19,0.25);
CGContextSetFillColor(_19,objj_msgSend(_16,"borderColor"));
CGContextSetStrokeColor(_19,objj_msgSend(_16,"borderColor"));
CGContextStrokeRect(_19,objj_msgSend(_16,"bounds"));
}
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("initializeWithImage:x:y:"),function(_1a,_1b,_1c,anX,anY){
with(_1a){
return objj_msgSend(_1a,"initializeWithImage:x:y:offset:",_1c,anX,anY,0);
}
}),new objj_method(sel_getUid("initializeWithImage:x:y:offset:"),function(_1d,_1e,_1f,anX,anY,_20){
with(_1d){
var _21=objj_msgSend(objj_msgSend(_1d,"alloc"),"initializeWithImage:x:y:offset:",_1f,anX,anY,_20);
return _21;
}
})]);
p;13;LabelFigure.jt;1644;@STATIC;1.0;t;1625;
var _1=objj_allocateClassPair(Figure,"LabelFigure"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_textField")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithFrame:textField:"),function(_3,_4,_5,_6){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("LabelFigure").super_class},"initWithFrame:",_5);
if(_3){
_textField=_6;
objj_msgSend(_3,"addSubview:",_textField);
return _3;
}
}
}),new objj_method(sel_getUid("figureAt:"),function(_7,_8,_9){
with(_7){
var _a=objj_msgSend(_7,"frame");
if(CPRectContainsPoint(_a,_9)){
return _7;
}else{
return nil;
}
}
}),new objj_method(sel_getUid("isSelectable"),function(_b,_c){
with(_b){
return true;
}
}),new objj_method(sel_getUid("isMoveable"),function(_d,_e){
with(_d){
return true;
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("initializeWithText:at:"),function(_f,_10,_11,_12){
with(_f){
var _13=objj_msgSend(objj_msgSend(CPTextField,"alloc"),"initWithFrame:",CGRectMakeZero());
objj_msgSend(_13,"setStringValue:",_11);
objj_msgSend(_13,"setTextColor:",objj_msgSend(CPColor,"blackColor"));
objj_msgSend(_13,"setFrameOrigin:",CGPointMake(0,0));
objj_msgSend(_13,"sizeToFit");
objj_msgSend(_13,"setBordered:",NO);
objj_msgSend(_13,"setBezeled:",NO);
return objj_msgSend(_f,"initializeWithTextField:at:",_13,_12);
}
}),new objj_method(sel_getUid("initializeWithTextField:at:"),function(_14,_15,_16,_17){
with(_14){
var _18=objj_msgSend(_16,"frameSize");
var _19=CGRectMake(_17.x,_17.y,_18.width,_18.height);
var _1a=objj_msgSend(objj_msgSend(_14,"alloc"),"initWithFrame:textField:",_19,_16);
return _1a;
}
})]);
p;10;Polyline.jt;4607;@STATIC;1.0;t;4588;
var _1=nil;
var _2=objj_allocateClassPair(Figure,"Polyline"),_3=_2.isa;
class_addIvars(_2,[new objj_ivar("_points")]);
objj_registerClassPair(_2);
class_addMethods(_2,[new objj_method(sel_getUid("initWithPoints:"),function(_4,_5,_6){
with(_4){
_1=objj_msgSend(CPNotificationCenter,"defaultCenter");
var _7=objj_msgSend(GeometryUtils,"computeFrameFor:",_6);
_4=objj_msgSendSuper({receiver:_4,super_class:objj_getClass("Polyline").super_class},"initWithFrame:",_7);
_points=objj_msgSend(CPMutableArray,"arrayWithArray:",_6);
for(var i=0;i<objj_msgSend(_points,"count");i++){
var _8=objj_msgSend(_points,"objectAtIndex:",i);
objj_msgSend(handles,"addObject:",objj_msgSend(_4,"addNewHandle:",i));
if(i!=objj_msgSend(_points,"count")-1){
objj_msgSend(handles,"addObject:",objj_msgSend(_4,"addCreateHandle:",i));
}
}
if(_4){
return _4;
}
}
}),new objj_method(sel_getUid("addNewHandle:"),function(_9,_a,_b){
with(_9){
var _c=objj_msgSend(Handle,"target:getSelector:setSelector:extraArgument:",_9,sel_getUid("pointAt:"),sel_getUid("pointAt:put:"),_b);
return _c;
}
}),new objj_method(sel_getUid("addCreateHandle:"),function(_d,_e,_f){
with(_d){
var _10=objj_msgSend(Handle,"target:getSelector:setSelector:extraArgument:",_d,sel_getUid("insertionPointAt:"),sel_getUid("createPointAt:put:"),_f);
objj_msgSend(_10,"displayColor:",objj_msgSend(CPColor,"redColor"));
return _10;
}
}),new objj_method(sel_getUid("isSelectable"),function(_11,_12){
with(_11){
return true;
}
}),new objj_method(sel_getUid("pointAt:"),function(_13,_14,_15){
with(_13){
var _16=objj_msgSend(_points,"objectAtIndex:",_15);
return _16;
}
}),new objj_method(sel_getUid("pointAt:put:"),function(_17,_18,_19,_1a){
with(_17){
objj_msgSend(_points,"replaceObjectAtIndex:withObject:",_19,_1a);
objj_msgSend(_17,"recomputeFrame");
}
}),new objj_method(sel_getUid("insertionPointAt:"),function(_1b,_1c,_1d){
with(_1b){
var _1e=objj_msgSend(_points,"objectAtIndex:",_1d);
var _1f=objj_msgSend(_points,"objectAtIndex:",(_1d+1));
var x=(_1e.x+_1f.x)/2;
var y=(_1e.y+_1f.y)/2;
return CGPointMake(x,y);
}
}),new objj_method(sel_getUid("createPointAt:put:"),function(_20,_21,_22,_23){
with(_20){
var _24=_22+1;
objj_msgSend(_points,"insertObject:atIndex:",_23,_24);
var _25=(_22*2)+1;
var _26=objj_msgSend(handles,"objectAtIndex:",_25);
objj_msgSend(_26,"displayColor:",objj_msgSend(CPColor,"blackColor"));
objj_msgSend(_26,"getSelector:setSelector:",sel_getUid("pointAt:"),sel_getUid("pointAt:put:"));
objj_msgSend(_26,"extraArgument:",_24);
var _27=objj_msgSend(_20,"addCreateHandle:",_24-1);
var _28=objj_msgSend(_20,"addCreateHandle:",_24);
objj_msgSend(handles,"insertObject:atIndex:",_28,_25+1);
objj_msgSend(handles,"insertObject:atIndex:",_27,_25);
for(var i=_25;i<objj_msgSend(handles,"count");i++){
var _29=objj_msgSend(handles,"objectAtIndex:",i);
var _2a=FLOOR(i/2);
objj_msgSend(_29,"extraArgument:",_2a);
}
var _2b=objj_msgSend(_20,"superview");
objj_msgSend(_2b,"addFigure:",_27);
objj_msgSend(_2b,"addFigure:",_28);
objj_msgSend(_20,"recomputeFrame");
}
}),new objj_method(sel_getUid("figureAt:"),function(_2c,_2d,_2e){
with(_2c){
for(var i=0;i<objj_msgSend(_points,"count")-1;i++){
var a=objj_msgSend(_points,"objectAtIndex:",i);
var b=objj_msgSend(_points,"objectAtIndex:",(i+1));
if(objj_msgSend(GeometryUtils,"distanceFrom:and:to:",a,b,_2e)<5){
return _2c;
}
}
return nil;
}
}),new objj_method(sel_getUid("recomputeFrame"),function(_2f,_30){
with(_2f){
var _31=objj_msgSend(GeometryUtils,"computeFrameFor:",_points);
objj_msgSend(_2f,"setNeedsDisplay:",YES);
objj_msgSend(_2f,"setFrame:",_31);
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_32,_33,_34,_35){
with(_32){
var _36=objj_msgSend(_32,"frameOrigin");
var _37=objj_msgSend(_points,"objectAtIndex:",0);
CGContextBeginPath(_35);
CGContextMoveToPoint(_35,_37.x-_36.x,_37.y-_36.y);
for(var i=1;i<objj_msgSend(_points,"count");i++){
var _38=objj_msgSend(_points,"objectAtIndex:",i);
CGContextAddLineToPoint(_35,_38.x-_36.x,_38.y-_36.y);
}
CGContextSetStrokeColor(_35,objj_msgSend(_32,"foregroundColor"));
CGContextSetLineWidth(_35,0.5);
CGContextStrokePath(_35);
}
}),new objj_method(sel_getUid("translatedBy:"),function(_39,_3a,_3b){
with(_39){
var _3c=objj_msgSend(_39,"frame");
var _3d=_3b.x-_3c.origin.x;
var _3e=_3b.y-_3c.origin.y;
for(var i=0;i<objj_msgSend(_points,"count");i++){
var _3f=objj_msgSend(_points,"objectAtIndex:",i);
_3f=CGPointMake(_3f.x+_3d,_3f.y+_3e);
objj_msgSend(_points,"replaceObjectAtIndex:withObject:",i,_3f);
}
objj_msgSendSuper({receiver:_39,super_class:objj_getClass("Polyline").super_class},"translatedBy:",_3b);
}
})]);
p;18;PropertiesFigure.jt;4506;@STATIC;1.0;t;4487;
var _1=objj_allocateClassPair(Figure,"PropertiesFigure"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_drawing"),new objj_ivar("_selectedFigure"),new objj_ivar("_nameColumn"),new objj_ivar("_valueColumn"),new objj_ivar("_tableView")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithFrame:drawing:"),function(_3,_4,_5,_6){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("PropertiesFigure").super_class},"initWithFrame:",_5);
if(_3){
_drawing=_6;
_selectedFigure=nil;
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("selectionChanged"),DrawingSelectionChangedNotification,_drawing);
var _7=CGRectMake(5,0,695,100);
var _8=objj_msgSend(objj_msgSend(CPScrollView,"alloc"),"initWithFrame:",_7);
objj_msgSend(_8,"setAutohidesScrollers:",YES);
_tableView=objj_msgSend(objj_msgSend(CPTableView,"alloc"),"initWithFrame:",CGRectMakeZero());
objj_msgSend(_tableView,"setDoubleAction:",sel_getUid("doubleClick:"));
objj_msgSend(_tableView,"setUsesAlternatingRowBackgroundColors:",YES);
objj_msgSend(_tableView,"setAutoresizingMask:",CPViewWidthSizable|CPViewHeightSizable);
_nameColumn=objj_msgSend(objj_msgSend(CPTableColumn,"alloc"),"initWithIdentifier:","nameColumn");
objj_msgSend(objj_msgSend(_nameColumn,"headerView"),"setStringValue:","Name");
objj_msgSend(_nameColumn,"setMinWidth:",200);
objj_msgSend(_nameColumn,"setEditable:",NO);
objj_msgSend(_tableView,"addTableColumn:",_nameColumn);
_valueColumn=objj_msgSend(objj_msgSend(CPTableColumn,"alloc"),"initWithIdentifier:","valueColumn");
objj_msgSend(objj_msgSend(_valueColumn,"headerView"),"setStringValue:","Value");
objj_msgSend(_valueColumn,"setMinWidth:",400);
objj_msgSend(_valueColumn,"setEditable:",YES);
objj_msgSend(_tableView,"addTableColumn:",_valueColumn);
objj_msgSend(_8,"setDocumentView:",_tableView);
objj_msgSend(_tableView,"setDataSource:",_3);
objj_msgSend(_tableView,"setDelegate:",_3);
objj_msgSend(_3,"addSubview:",_8);
objj_msgSend(_8,"setAutoresizingMask:",CPViewWidthSizable);
_selectable=true;
_moveable=true;
return _3;
}
}
}),new objj_method(sel_getUid("doubleClick:"),function(_9,_a,_b){
with(_9){
var _c=1;
var _d=objj_msgSend(_tableView,"selectedRow");
objj_msgSend(_tableView,"editColumn:row:withEvent:select:",_c,_d,nil,YES);
}
}),new objj_method(sel_getUid("numberOfRowsInTableView:"),function(_e,_f,_10){
with(_e){
if(_selectedFigure==nil){
return 0;
}else{
var _11=objj_msgSend(_selectedFigure,"model");
if(_11!=nil){
var _12=objj_msgSend(_11,"propertiesSize");
return _12;
}else{
return 0;
}
}
}
}),new objj_method(sel_getUid("tableView:objectValueForTableColumn:row:"),function(_13,_14,_15,_16,_17){
with(_13){
var _18=objj_msgSend(_selectedFigure,"model");
if(_nameColumn==_16){
return objj_msgSend(_18,"propertyNameAt:",_17);
}else{
return objj_msgSend(_18,"propertyValueAt:",_17);
}
}
}),new objj_method(sel_getUid("tableView:setObjectValue:forTableColumn:row:"),function(_19,_1a,_1b,_1c,_1d,_1e){
with(_19){
var _1f=objj_msgSend(_selectedFigure,"model");
if(_1d==_valueColumn){
return objj_msgSend(_1f,"propertyValueAt:be:",_1e,_1c);
}
}
}),new objj_method(sel_getUid("selectionChanged"),function(_20,_21){
with(_20){
if(_selectedFigure!=nil){
var _22=objj_msgSend(_selectedFigure,"model");
if(_22!=nil){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_20,ModelPropertyChangedNotification,_22);
}
}
_selectedFigure=objj_msgSend(_drawing,"selectedFigure");
if(_selectedFigure!=nil){
var _22=objj_msgSend(_selectedFigure,"model");
if(_22!=nil){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_20,sel_getUid("reloadData"),ModelPropertyChangedNotification,_22);
}
}
objj_msgSend(_20,"reloadData");
}
}),new objj_method(sel_getUid("reloadData"),function(_23,_24){
with(_23){
objj_msgSend(_tableView,"reloadData");
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_25,_26,_27,_28){
with(_25){
CGContextSetFillColor(_28,objj_msgSend(CPColor,"lightGrayColor"));
CGContextFillRect(_28,objj_msgSend(_25,"bounds"));
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("defaultFrame"),function(_29,_2a){
with(_29){
return CGRectMake(10,450,700,100);
}
}),new objj_method(sel_getUid("newAt:drawing:"),function(_2b,_2c,_2d,_2e){
with(_2b){
var _2f=objj_msgSend(objj_msgSend(_2b,"new"),"initWithFrame:drawing:",objj_msgSend(_2b,"defaultFrame"),_2e);
return _2f;
}
})]);
p;17;RectangleFigure.jt;1634;@STATIC;1.0;t;1615;
var _1=objj_allocateClassPair(Figure,"RectangleFigure"),_2=_1.isa;
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithFrame:"),function(_3,_4,_5){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("RectangleFigure").super_class},"initWithFrame:",_5);
if(_3){
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"topLeft"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"topMiddle"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"topRight"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"middleLeft"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"middleRight"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"bottomLeft"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"bottomMiddle"));
objj_msgSend(handles,"addObject:",objj_msgSend(Handle,"target:selector:",_3,"bottomRight"));
return _3;
}
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_6,_7,_8,_9){
with(_6){
CGContextSetFillColor(_9,objj_msgSend(_6,"backgroundColor"));
CGContextFillRect(_9,objj_msgSend(_6,"bounds"));
CGContextSetLineWidth(_9,0.5);
CGContextSetStrokeColor(_9,objj_msgSend(_6,"foregroundColor"));
CGContextStrokeRect(_9,objj_msgSend(_6,"bounds"));
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("newAt:"),function(_a,_b,_c){
with(_a){
var _d=CGRectMake(_c.x,_c.y,50,50);
var _e=objj_msgSend(objj_msgSend(_a,"new"),"initWithFrame:",_d);
return _e;
}
})]);
p;15;ToolboxFigure.jt;3049;@STATIC;1.0;t;3030;
var _1=objj_allocateClassPair(Figure,"ToolboxFigure"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_drawing"),new objj_ivar("_buttonsMapping"),new objj_ivar("_firstColumn"),new objj_ivar("_currentY")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initializeWith:at:"),function(_3,_4,_5,_6){
with(_3){
var _7=CGRectMake(_6.x,_6.y,50,1);
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("ToolboxFigure").super_class},"initWithFrame:",_7);
if(_3){
_drawing=_5;
_firstColumn=true;
_currentY=15;
_selectable=true;
_moveable=true;
_buttonsMapping=objj_msgSend(CPDictionary,"dictionary");
objj_msgSend(_3,"addDefaultTools");
return _3;
}
}
}),new objj_method(sel_getUid("addDefaultTools"),function(_8,_9){
with(_8){
objj_msgSend(_8,"addTool:withTitle:image:",objj_msgSend(SelectionTool,"drawing:",_drawing),"Selection","");
objj_msgSend(_8,"addTool:withTitle:image:",objj_msgSend(CreateImageTool,"drawing:",_drawing),"Image","");
objj_msgSend(_8,"addTool:withTitle:image:",objj_msgSend(CreateLabelTool,"drawing:",_drawing),"Label","");
}
}),new objj_method(sel_getUid("addSeparator"),function(_a,_b){
with(_a){
if(!_firstColumn){
_currentY=_currentY+25;
}
_firstColumn=true;
}
}),new objj_method(sel_getUid("addTool:withTitle:image:"),function(_c,_d,_e,_f,url){
with(_c){
var _10=30;
var _11=25;
var _12=objj_msgSend(CPButton,"buttonWithTitle:","");
var y=_currentY;
var x=0;
if(!_firstColumn){
x=_10;
}
var _13=CGPointMake(x,y);
objj_msgSend(_12,"setFrameOrigin:",_13);
var _14=objj_msgSend(objj_msgSend(CPImage,"alloc"),"initWithContentsOfFile:",url);
objj_msgSend(_12,"setImage:",_14);
objj_msgSend(_12,"setButtonType:",CPOnOffButton);
objj_msgSend(_12,"setBordered:",YES);
objj_msgSend(_12,"setBezelStyle:",CPRegularSquareBezelStyle);
objj_msgSend(_12,"setFrameSize:",CGSizeMake(_10,_11));
objj_msgSend(_c,"addSubview:",_12);
objj_msgSend(_12,"setTarget:",_c);
objj_msgSend(_12,"setAction:",sel_getUid("selectTool:"));
objj_msgSend(_buttonsMapping,"setObject:forKey:",_e,_12);
if(!_firstColumn){
_currentY=_currentY+_11;
}
var _15=CGSizeMake(_10*2,_currentY+_11);
objj_msgSend(_c,"setFrameSize:",_15);
_firstColumn=!_firstColumn;
}
}),new objj_method(sel_getUid("selectTool:"),function(_16,_17,_18){
with(_16){
var _19=objj_msgSend(_buttonsMapping,"objectForKey:",_18);
objj_msgSend(_drawing,"tool:",_19);
}
}),new objj_method(sel_getUid("drawRect:on:"),function(_1a,_1b,_1c,_1d){
with(_1a){
CGContextSetFillColor(_1d,objj_msgSend(CPColor,"lightGrayColor"));
CGContextFillRect(_1d,objj_msgSend(_1a,"bounds"));
}
}),new objj_method(sel_getUid("sizeToFit"),function(_1e,_1f){
with(_1e){
var _20=objj_msgSend(GeometryUtils,"computeFrameForViews:",objj_msgSend(_1e,"subviews"));
_20.origin.y=_20.origin.y-15;
_20.size.height=_20.size.height+15;
objj_msgSend(_1e,"setFrame:",_20);
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("initializeWith:at:"),function(_21,_22,_23,_24){
with(_21){
var _25=objj_msgSend(objj_msgSend(_21,"new"),"initializeWith:at:",_23,_24);
return _25;
}
})]);
p;7;Model.jt;2132;@STATIC;1.0;t;2113;
ModelPropertyChangedNotification="ModelPropertyChangedNotification";
var _1=objj_allocateClassPair(CPObject,"Model"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_properties"),new objj_ivar("_propertiesByName")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("init"),function(_3,_4){
with(_3){
_properties=objj_msgSend(CPMutableArray,"array");
_propertiesByName=objj_msgSend(CPDictionary,"dictionary");
return _3;
}
}),new objj_method(sel_getUid("addProperty:"),function(_5,_6,_7){
with(_5){
CPLog.info(_7);
objj_msgSend(_5,"addProperty:value:",_7,nil);
}
}),new objj_method(sel_getUid("addProperty:value:"),function(_8,_9,_a,_b){
with(_8){
var _c=objj_msgSend(Property,"name:value:",_a,_b);
objj_msgSend(_properties,"addObject:",_c);
objj_msgSend(_propertiesByName,"setObject:forKey:",_c,_a);
}
}),new objj_method(sel_getUid("propertiesSize"),function(_d,_e){
with(_d){
return objj_msgSend(_properties,"count");
}
}),new objj_method(sel_getUid("propertyNameAt:"),function(_f,_10,_11){
with(_f){
var _12=objj_msgSend(_properties,"objectAtIndex:",_11);
return objj_msgSend(_12,"name");
}
}),new objj_method(sel_getUid("propertyValueAt:"),function(_13,_14,_15){
with(_13){
var _16=objj_msgSend(_properties,"objectAtIndex:",_15);
return objj_msgSend(_16,"value");
}
}),new objj_method(sel_getUid("propertyValue:"),function(_17,_18,_19){
with(_17){
var _1a=objj_msgSend(_propertiesByName,"objectForKey:",_19);
return objj_msgSend(_1a,"value");
}
}),new objj_method(sel_getUid("propertyValue:be:"),function(_1b,_1c,_1d,_1e){
with(_1b){
var _1f=objj_msgSend(_propertiesByName,"objectForKey:",_1d);
objj_msgSend(_1f,"value:",_1e);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"postNotificationName:object:",ModelPropertyChangedNotification,_1b);
}
}),new objj_method(sel_getUid("propertyValueAt:be:"),function(_20,_21,_22,_23){
with(_20){
var _24=objj_msgSend(_properties,"objectAtIndex:",_22);
objj_msgSend(_24,"value:",_23);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"postNotificationName:object:",ModelPropertyChangedNotification,_20);
}
})]);
p;10;Property.jt;752;@STATIC;1.0;t;734;
var _1=objj_allocateClassPair(CPObject,"Property"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_name"),new objj_ivar("_value")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithName:value:"),function(_3,_4,_5,_6){
with(_3){
_name=_5;
_value=_6;
return _3;
}
}),new objj_method(sel_getUid("name"),function(_7,_8){
with(_7){
return _name;
}
}),new objj_method(sel_getUid("value"),function(_9,_a){
with(_9){
return _value;
}
}),new objj_method(sel_getUid("value:"),function(_b,_c,_d){
with(_b){
_value=_d;
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("name:value:"),function(_e,_f,_10,_11){
with(_e){
return objj_msgSend(objj_msgSend(_e,"alloc"),"initWithName:value:",_10,_11);
}
})]);
p;26;AbstractCreateFigureTool.jt;421;@STATIC;1.0;t;403;
var _1=objj_allocateClassPair(Tool,"AbstractCreateFigureTool"),_2=_1.isa;
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("mouseDown:"),function(_3,_4,_5){
with(_3){
var _6=objj_msgSend(_5,"locationInWindow");
objj_msgSend(_3,"createFigureAt:on:",_6,objj_msgSend(_3,"drawing"));
}
}),new objj_method(sel_getUid("createFigureAt:on:"),function(_7,_8,_9,_a){
with(_7){
}
})]);
p;17;CreateImageTool.jt;1746;@STATIC;1.0;t;1727;
var _1=objj_allocateClassPair(AbstractCreateFigureTool,"CreateImageTool"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_editableLabel"),new objj_ivar("_point")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("createFigureAt:on:"),function(_3,_4,_5,_6){
with(_3){
_editableLabel=objj_msgSend(CPCancelableTextField,"textFieldWithStringValue:placeholder:width:","","Insert url",100);
_point=_5;
objj_msgSend(_editableLabel,"setEditable:",YES);
objj_msgSend(_editableLabel,"setBordered:",YES);
objj_msgSend(_editableLabel,"setFrameOrigin:",_5);
objj_msgSend(_editableLabel,"cancelator:",_3);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("controlTextDidEndEditing:"),CPControlTextDidEndEditingNotification,_editableLabel);
objj_msgSend(_6,"addFigure:",_editableLabel);
objj_msgSend(objj_msgSend(_6,"window"),"makeFirstResponder:",_editableLabel);
}
}),new objj_method(sel_getUid("cancelEditing"),function(_7,_8){
with(_7){
if(_editableLabel!=nil){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_7,CPControlTextDidEndEditingNotification,_editableLabel);
objj_msgSend(_editableLabel,"removeFromSuperview");
var _9=objj_msgSend(_7,"drawing");
objj_msgSend(objj_msgSend(_9,"window"),"makeFirstResponder:",_9);
}
}
}),new objj_method(sel_getUid("controlTextDidEndEditing:"),function(_a,_b,_c){
with(_a){
var _d=objj_msgSend(_editableLabel,"objectValue");
var _e=objj_msgSend(ImageFigure,"initializeWithImage:x:y:offset:",_d,_point.x,_point.y,3);
objj_msgSend(objj_msgSend(_a,"drawing"),"addFigure:",_e);
objj_msgSend(_a,"cancelEditing");
objj_msgSend(_a,"activateSelectionTool");
}
})]);
p;17;CreateLabelTool.jt;1724;@STATIC;1.0;t;1705;
var _1=objj_allocateClassPair(AbstractCreateFigureTool,"CreateLabelTool"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_editableLabel"),new objj_ivar("_point")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("createFigureAt:on:"),function(_3,_4,_5,_6){
with(_3){
_editableLabel=objj_msgSend(CPCancelableTextField,"textFieldWithStringValue:placeholder:width:","","Insert url",100);
_point=_5;
objj_msgSend(_editableLabel,"setEditable:",YES);
objj_msgSend(_editableLabel,"setBordered:",YES);
objj_msgSend(_editableLabel,"setFrameOrigin:",_5);
objj_msgSend(_editableLabel,"cancelator:",_3);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("controlTextDidEndEditing:"),CPControlTextDidEndEditingNotification,_editableLabel);
objj_msgSend(_6,"addFigure:",_editableLabel);
objj_msgSend(objj_msgSend(_6,"window"),"makeFirstResponder:",_editableLabel);
}
}),new objj_method(sel_getUid("cancelEditing"),function(_7,_8){
with(_7){
if(_editableLabel!=nil){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_7,CPControlTextDidEndEditingNotification,_editableLabel);
objj_msgSend(_editableLabel,"removeFromSuperview");
var _9=objj_msgSend(_7,"drawing");
objj_msgSend(objj_msgSend(_9,"window"),"makeFirstResponder:",_9);
}
}
}),new objj_method(sel_getUid("controlTextDidEndEditing:"),function(_a,_b,_c){
with(_a){
var _d=objj_msgSend(_editableLabel,"objectValue");
var _e=objj_msgSend(LabelFigure,"initializeWithText:at:",_d,_point);
objj_msgSend(objj_msgSend(_a,"drawing"),"addFigure:",_e);
objj_msgSend(_a,"cancelEditing");
objj_msgSend(_a,"activateSelectionTool");
}
})]);
p;18;MoveFiguresState.jt;1413;@STATIC;1.0;t;1394;
var _1=objj_allocateClassPair(ToolState,"MoveFiguresState"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_initialDragPoint")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithInitialDragPoint:"),function(_3,_4,_5){
with(_3){
_initialDragPoint=_5;
return _3;
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_6,_7,_8){
with(_6){
var _9=objj_msgSend(_8,"locationInWindow");
var _a=_9.x-_initialDragPoint.x;
var _b=_9.y-_initialDragPoint.y;
var _c=objj_msgSend(_tool,"selectedFigures");
var _d=objj_msgSend(objj_msgSend(_tool,"drawing"),"snapToGrid");
var _e=objj_msgSend(objj_msgSend(_tool,"drawing"),"gridSize");
for(var i=0;i<objj_msgSend(_c,"count");i++){
var _f=objj_msgSend(_c,"objectAtIndex:",i);
var _10=objj_msgSend(_tool,"initialPositionOf:",_f);
var _11=CGPointMake(_10.x+_a,_10.y+_b);
if(_d){
_11=CGPointMake(ROUND(_11.x/_e)*_e,ROUND(_11.y/_e)*_e);
}
objj_msgSend(_f,"moveTo:",_11);
}
}
}),new objj_method(sel_getUid("mouseUp:"),function(_12,_13,_14){
with(_12){
var _15=objj_msgSend(_14,"locationInWindow");
objj_msgSend(_12,"transitionTo:",objj_msgSend(SelectedState,"tool:initialDragPoint:",_tool,_15));
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("tool:initialDragPoint:"),function(_16,_17,_18,_19){
with(_16){
var _1a=objj_msgSend(_16,"tool:",_18);
objj_msgSend(_1a,"initWithInitialDragPoint:",_19);
return _1a;
}
})]);
p;17;MoveHandleState.jt;1351;@STATIC;1.0;t;1332;
var _1=objj_allocateClassPair(ToolState,"MoveHandleState"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_initialDragPoint"),new objj_ivar("_handle")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithInitialDragPoint:handle:"),function(_3,_4,_5,_6){
with(_3){
_initialDragPoint=_5;
_handle=_6;
return _3;
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_7,_8,_9){
with(_7){
var _a=objj_msgSend(_9,"locationInWindow");
var _b=_a.x-_initialDragPoint.x;
var _c=_a.y-_initialDragPoint.y;
var _d=objj_msgSend(objj_msgSend(_tool,"drawing"),"snapToGrid");
var _e=objj_msgSend(objj_msgSend(_tool,"drawing"),"gridSize");
var _f=objj_msgSend(_tool,"initialPositionOf:",_handle);
var _10=CGPointMake(_f.x+_b,_f.y+_c);
if(_d){
_10=CGPointMake(ROUND(_10.x/_e)*_e,ROUND(_10.y/_e)*_e);
}
objj_msgSend(_handle,"moveTo:",_10);
}
}),new objj_method(sel_getUid("mouseUp:"),function(_11,_12,_13){
with(_11){
var _14=objj_msgSend(_13,"locationInWindow");
objj_msgSend(_11,"transitionTo:",objj_msgSend(SelectedState,"tool:initialDragPoint:",_tool,_14));
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("tool:initialDragPoint:handle:"),function(_15,_16,_17,_18,_19){
with(_15){
var _1a=objj_msgSend(_15,"tool:",_17);
objj_msgSend(_1a,"initWithInitialDragPoint:handle:",_18,_19);
return _1a;
}
})]);
p;15;SelectedState.jt;1780;@STATIC;1.0;t;1761;
var _1=objj_allocateClassPair(ToolState,"SelectedState"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_initialDragPoint")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithInitialDragPoint:"),function(_3,_4,_5){
with(_3){
_initialDragPoint=_5;
return _3;
}
}),new objj_method(sel_getUid("mouseDown:"),function(_6,_7,_8){
with(_6){
var _9=objj_msgSend(_8,"locationInWindow");
var _a=objj_msgSend(_tool,"drawing");
var _b=objj_msgSend(_a,"figureAt:",_9);
_b=objj_msgSend(_tool,"selectableFigure:",_b);
_initialDragPoint=_9;
if(_b==nil||_b==_a){
objj_msgSend(_6,"transitionToInitialState");
}else{
if(!objj_msgSend(_b,"isHandle")){
var _c=objj_msgSend(_tool,"selectedFigures");
var _d=objj_msgSend(_c,"containsObject:",_b);
var _e=(objj_msgSend(_8,"modifierFlags")&(CPControlKeyMask|CPCommandKeyMask))==0;
if(!_d&&_e){
objj_msgSend(_tool,"clearSelection");
}
}
objj_msgSend(_tool,"select:",_b);
objj_msgSend(_tool,"updateInitialPoints");
}
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_f,_10,_11){
with(_f){
var _12=objj_msgSend(_11,"locationInWindow");
var _13=objj_msgSend(objj_msgSend(_tool,"drawing"),"figureAt:",_12);
if(objj_msgSend(_13,"isHandle")){
objj_msgSend(_f,"transitionTo:",objj_msgSend(MoveHandleState,"tool:initialDragPoint:handle:",_tool,_initialDragPoint,_13));
}else{
objj_msgSend(_f,"transitionTo:",objj_msgSend(MoveFiguresState,"tool:initialDragPoint:",_tool,_initialDragPoint));
}
}
}),new objj_method(sel_getUid("mouseUp:"),function(_14,_15,_16){
with(_14){
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("tool:initialDragPoint:"),function(_17,_18,_19,_1a){
with(_17){
var _1b=objj_msgSend(_17,"tool:",_19);
objj_msgSend(_1b,"initWithInitialDragPoint:",_1a);
return _1b;
}
})]);
p;15;SelectionTool.jt;2580;@STATIC;1.0;t;2561;
var _1=objj_allocateClassPair(StateMachineTool,"SelectionTool"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_selectedFigures"),new objj_ivar("_initialPositions"),new objj_ivar("_initialDragPoint")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("init"),function(_3,_4){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("SelectionTool").super_class},"init");
_selectedFigures=objj_msgSend(CPMutableArray,"array");
_initialPositions=objj_msgSend(CPDictionary,"dictionary");
return _3;
}
}),new objj_method(sel_getUid("initialState"),function(_5,_6){
with(_5){
return objj_msgSend(SelectionToolInitialState,"tool:",_5);
}
}),new objj_method(sel_getUid("selectedFigures"),function(_7,_8){
with(_7){
return _selectedFigures;
}
}),new objj_method(sel_getUid("select:"),function(_9,_a,_b){
with(_9){
objj_msgSend(_selectedFigures,"addObject:",_b);
objj_msgSend(_b,"select");
objj_msgSend(_drawing,"selectedFigure:",_b);
objj_msgSend(_initialPositions,"setObject:forKey:",objj_msgSend(_b,"frameOrigin"),_b);
}
}),new objj_method(sel_getUid("initialPositionOf:"),function(_c,_d,_e){
with(_c){
return objj_msgSend(_initialPositions,"objectForKey:",_e);
}
}),new objj_method(sel_getUid("updateInitialPoints"),function(_f,_10){
with(_f){
for(var i=0;i<objj_msgSend(_selectedFigures,"count");i++){
var _11=objj_msgSend(_selectedFigures,"objectAtIndex:",i);
objj_msgSend(_initialPositions,"setObject:forKey:",objj_msgSend(_11,"frameOrigin"),_11);
}
}
}),new objj_method(sel_getUid("clearSelection"),function(_12,_13){
with(_12){
for(var i=0;i<objj_msgSend(_selectedFigures,"count");i++){
var _14=objj_msgSend(_selectedFigures,"objectAtIndex:",i);
objj_msgSend(_14,"unselect");
}
objj_msgSend(_selectedFigures,"removeAllObjects");
objj_msgSend(_initialPositions,"removeAllObjects");
objj_msgSend(_drawing,"selectedFigure:",nil);
}
}),new objj_method(sel_getUid("release"),function(_15,_16){
with(_15){
objj_msgSend(_15,"clearSelection");
}
}),new objj_method(sel_getUid("selectableFigure:"),function(_17,_18,_19){
with(_17){
while(_19!=_drawing&&!objj_msgSend(_19,"isSelectable")){
_19=objj_msgSend(_19,"superview");
}
return _19;
}
}),new objj_method(sel_getUid("keyDown:"),function(_1a,_1b,_1c){
with(_1a){
}
}),new objj_method(sel_getUid("keyUp:"),function(_1d,_1e,_1f){
with(_1d){
if((objj_msgSend(_1f,"keyCode")==CPKeyCodes.F2)&&(objj_msgSend(_selectedFigures,"count")==1)){
var _20=objj_msgSend(_selectedFigures,"objectAtIndex:",0);
if(objj_msgSend(_20,"isEditable")){
objj_msgSend(_20,"switchToEditMode");
}
}
}
})]);
p;27;SelectionToolInitialState.jt;934;@STATIC;1.0;t;916;
var _1=objj_allocateClassPair(ToolState,"SelectionToolInitialState"),_2=_1.isa;
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithTool:"),function(_3,_4,_5){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("SelectionToolInitialState").super_class},"initWithTool:",_5);
objj_msgSend(_5,"clearSelection");
return _3;
}
}),new objj_method(sel_getUid("mouseDown:"),function(_6,_7,_8){
with(_6){
var _9=objj_msgSend(_8,"locationInWindow");
var _a=objj_msgSend(objj_msgSend(_tool,"drawing"),"figureAt:",_9);
_a=objj_msgSend(_tool,"selectableFigure:",_a);
if(_a!=nil){
objj_msgSend(_tool,"select:",_a);
objj_msgSend(_6,"transitionTo:",objj_msgSend(SelectedState,"tool:initialDragPoint:",_tool,_9));
}
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("tool:"),function(_b,_c,_d){
with(_b){
return objj_msgSend(objj_msgSend(_b,"new"),"initWithTool:",_d);
}
})]);
p;18;StateMachineTool.jt;1171;@STATIC;1.0;t;1152;
var _1=objj_allocateClassPair(Tool,"StateMachineTool"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_state")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("init"),function(_3,_4){
with(_3){
objj_msgSendSuper({receiver:_3,super_class:objj_getClass("StateMachineTool").super_class},"init");
objj_msgSend(_3,"setState:",objj_msgSend(_3,"initialState"));
return _3;
}
}),new objj_method(sel_getUid("initialState"),function(_5,_6){
with(_5){
return nil;
}
}),new objj_method(sel_getUid("setState:"),function(_7,_8,_9){
with(_7){
_state=_9;
}
}),new objj_method(sel_getUid("mouseDown:"),function(_a,_b,_c){
with(_a){
objj_msgSend(_state,"mouseDown:",_c);
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_d,_e,_f){
with(_d){
objj_msgSend(_state,"mouseDragged:",_f);
}
}),new objj_method(sel_getUid("mouseUp:"),function(_10,_11,_12){
with(_10){
objj_msgSend(_state,"mouseUp:",_12);
}
}),new objj_method(sel_getUid("keyUp:"),function(_13,_14,_15){
with(_13){
objj_msgSend(_state,"keyUp:",_15);
}
}),new objj_method(sel_getUid("keyDown:"),function(_16,_17,_18){
with(_16){
objj_msgSend(_state,"keyDown:",_18);
}
})]);
p;6;Tool.jt;1165;@STATIC;1.0;t;1146;
var _1=objj_allocateClassPair(CPObject,"Tool"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_drawing")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithDrawing:"),function(_3,_4,_5){
with(_3){
_drawing=_5;
return _3;
}
}),new objj_method(sel_getUid("drawing"),function(_6,_7){
with(_6){
return _drawing;
}
}),new objj_method(sel_getUid("activateSelectionTool"),function(_8,_9){
with(_8){
objj_msgSend(_drawing,"tool:",objj_msgSend(SelectionTool,"drawing:",_drawing));
}
}),new objj_method(sel_getUid("release"),function(_a,_b){
with(_a){
}
}),new objj_method(sel_getUid("mouseDown:"),function(_c,_d,_e){
with(_c){
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_f,_10,_11){
with(_f){
}
}),new objj_method(sel_getUid("mouseUp:"),function(_12,_13,_14){
with(_12){
}
}),new objj_method(sel_getUid("keyUp:"),function(_15,_16,_17){
with(_15){
}
}),new objj_method(sel_getUid("keyDown:"),function(_18,_19,_1a){
with(_18){
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("drawing:"),function(_1b,_1c,_1d){
with(_1b){
return objj_msgSend(objj_msgSend(_1b,"new"),"initWithDrawing:",_1d);
}
})]);
p;11;ToolState.jt;1606;@STATIC;1.0;t;1587;
var _1=objj_allocateClassPair(CPObject,"ToolState"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_tool")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithTool:"),function(_3,_4,_5){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("ToolState").super_class},"init");
if(_3){
_tool=_5;
return _3;
}
}
}),new objj_method(sel_getUid("transitionTo:"),function(_6,_7,_8){
with(_6){
objj_msgSend(_tool,"setState:",_8);
}
}),new objj_method(sel_getUid("activateSelectionTool"),function(_9,_a){
with(_9){
objj_msgSend(_tool,"activateSelectionTool");
}
}),new objj_method(sel_getUid("transitionToInitialState"),function(_b,_c){
with(_b){
objj_msgSend(_b,"transitionTo:",objj_msgSend(_tool,"initialState"));
}
}),new objj_method(sel_getUid("mouseDown:"),function(_d,_e,_f){
with(_d){
objj_msgSend(_d,"transitionToInitialState");
}
}),new objj_method(sel_getUid("mouseDragged:"),function(_10,_11,_12){
with(_10){
objj_msgSend(_10,"transitionToInitialState");
}
}),new objj_method(sel_getUid("mouseUp:"),function(_13,_14,_15){
with(_13){
objj_msgSend(_13,"transitionToInitialState");
}
}),new objj_method(sel_getUid("keyUp:"),function(_16,_17,_18){
with(_16){
CPLog.debug(_18);
objj_msgSend(_16,"transitionToInitialState");
}
}),new objj_method(sel_getUid("keyDown:"),function(_19,_1a,_1b){
with(_19){
CPLog.debug(_1b);
objj_msgSend(_19,"transitionToInitialState");
}
})]);
class_addMethods(_2,[new objj_method(sel_getUid("tool:"),function(_1c,_1d,_1e){
with(_1c){
return objj_msgSend(objj_msgSend(_1c,"new"),"initWithTool:",_1e);
}
})]);
p;16;EditorDelegate.jt;2630;@STATIC;1.0;t;2611;
var _1=objj_allocateClassPair(CPObject,"EditorDelegate"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_editableLabel"),new objj_ivar("_editableFigure"),new objj_ivar("_drawing"),new objj_ivar("_window")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithWidget:label:window:figureContainer:drawing:"),function(_3,_4,_5,_6,_7,_8,_9){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("EditorDelegate").super_class},"init");
if(_3){
var _a=objj_msgSend(_6,"frameSize").width+(6*2);
var _b=objj_msgSend(_6,"objectValue");
var _c=objj_msgSend(_9,"convertPoint:fromView:",CPPointMake(-5,-5),_5);
var _d=objj_msgSend(CPCancelableTextField,"textFieldWithStringValue:placeholder:width:",_b,"",_a);
objj_msgSend(_d,"setEditable:",YES);
objj_msgSend(_d,"setBordered:",NO);
objj_msgSend(_d,"sizeToFit");
objj_msgSend(_d,"setFrameOrigin:",_c);
objj_msgSend(_d,"cancelator:",_3);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("controlTextDidBlur:"),CPTextFieldDidBlurNotification,_d);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("controlTextDidEndEditing:"),CPControlTextDidEndEditingNotification,_d);
objj_msgSend(objj_msgSend(_7,"contentView"),"addSubview:",_d);
objj_msgSend(_7,"makeFirstResponder:",_d);
_editableLabel=_d;
_editableFigure=_8;
_drawing=_9;
_window=_7;
return _3;
}
}
}),new objj_method(sel_getUid("controlTextDidChange:"),function(_e,_f,_10){
with(_e){
var _11=objj_msgSend(objj_msgSend(_10,"userInfo"),"objectForKey:","CPFieldEditor");
CPLog.debug(_11);
}
}),new objj_method(sel_getUid("controlTextDidEndEditing:"),function(_12,_13,_14){
with(_12){
objj_msgSend(_editableFigure,"setEditionResult:",objj_msgSend(_editableLabel,"objectValue"));
objj_msgSend(_12,"cleanUp");
}
}),new objj_method(sel_getUid("controlTextDidBlur:"),function(_15,_16,_17){
with(_15){
objj_msgSend(_15,"controlTextDidEndEditing:",_17);
}
}),new objj_method(sel_getUid("cancelEditing"),function(_18,_19){
with(_18){
objj_msgSend(_18,"cleanUp");
}
}),new objj_method(sel_getUid("cleanUp"),function(_1a,_1b){
with(_1a){
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_1a,CPControlTextDidEndEditingNotification,_editableLabel);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"removeObserver:name:object:",_1a,CPTextFieldDidBlurNotification,_editableLabel);
objj_msgSend(_editableLabel,"removeFromSuperview");
objj_msgSend(_window,"makeFirstResponder:",_drawing);
}
})]);
p;15;GeometryUtils.jt;2799;@STATIC;1.0;t;2780;
var _1=objj_allocateClassPair(CPObject,"GeometryUtils"),_2=_1.isa;
objj_registerClassPair(_1);
class_addMethods(_2,[new objj_method(sel_getUid("computeFrameFor:"),function(_3,_4,_5){
with(_3){
var _6=objj_msgSend(_5,"objectAtIndex:",0);
var _7=_6.x;
var _8=_7;
var _9=_6.y;
var _a=_9;
for(var i=1;i<objj_msgSend(_5,"count");i++){
var _b=objj_msgSend(_5,"objectAtIndex:",i);
_7=MIN(_7,_b.x);
_8=MAX(_8,_b.x);
_9=MIN(_9,_b.y);
_a=MAX(_a,_b.y);
}
var _c=ABS(_8-_7);
var _d=ABS(_a-_9);
_c=MAX(_c,1);
_d=MAX(_d,1);
return CGRectMake(_7,_9,_c,_d);
}
}),new objj_method(sel_getUid("computeFrameForViews:"),function(_e,_f,_10){
with(_e){
var _11=objj_msgSend(objj_msgSend(_10,"objectAtIndex:",0),"frame").origin;
var _12=_11.x;
var _13=_12;
var _14=_11.y;
var _15=_14;
for(var i=1;i<objj_msgSend(_10,"count");i++){
var _16=objj_msgSend(objj_msgSend(_10,"objectAtIndex:",i),"frame").origin;
_12=MIN(_12,_16.x);
_13=MAX(_13,_16.x);
_14=MIN(_14,_16.y);
_15=MAX(_15,_16.y);
var _17=objj_msgSend(objj_msgSend(_10,"objectAtIndex:",i),"frame").size;
var _18=CGPointMake(_16.x+_17.width,_16.y+_17.height);
_12=MIN(_12,_18.x);
_13=MAX(_13,_18.x);
_14=MIN(_14,_18.y);
_15=MAX(_15,_18.y);
}
var _19=ABS(_13-_12);
var _1a=ABS(_15-_14);
_19=MAX(_19,1);
_1a=MAX(_1a,1);
return CGRectMake(_12,_14,_19,_1a);
}
}),new objj_method(sel_getUid("intersectionOf:with:with:with:"),function(_1b,_1c,p1,p2,p3,p4){
with(_1b){
var Ax=p1.x;
var Ay=p1.y;
var Bx=p2.x;
var By=p2.y;
var Cx=p3.x;
var Cy=p3.y;
var Dx=p4.x;
var Dy=p4.y;
var _1d,_1e,_1f,_20,_21;
if(Ax==Bx&&Ay==By||Cx==Dx&&Cy==Dy){
return nil;
}
if(Ax==Cx&&Ay==Cy||Bx==Cx&&By==Cy||Ax==Dx&&Ay==Dy||Bx==Dx&&By==Dy){
return nil;
}
Bx-=Ax;
By-=Ay;
Cx-=Ax;
Cy-=Ay;
Dx-=Ax;
Dy-=Ay;
_1d=objj_msgSend(CPPredicateUtilities,"sqrt:",(Bx*Bx+By*By));
_1e=Bx/_1d;
_1f=By/_1d;
_20=Cx*_1e+Cy*_1f;
Cy=Cy*_1e-Cx*_1f;
Cx=_20;
_20=Dx*_1e+Dy*_1f;
Dy=Dy*_1e-Dx*_1f;
Dx=_20;
if(Cy<0&&Dy<0||Cy>=0&&Dy>=0){
return nil;
}
_21=Dx+(Cx-Dx)*Dy/(Dy-Cy);
if(_21<0||_21>_1d){
return nil;
}
var _22=CGPointMake(ROUND(Ax+_21*_1e),ROUND(Ay+_21*_1f));
return _22;
}
}),new objj_method(sel_getUid("distanceFrom:to:"),function(_23,_24,p1,p2){
with(_23){
var _25=(p1.x-p2.x);
var _26=(p1.y-p2.y);
return objj_msgSend(CPPredicateUtilities,"sqrt:",(_25*_25+_26*_26));
}
}),new objj_method(sel_getUid("distanceFrom:and:to:"),function(_27,_28,a,b,p){
with(_27){
if(a==b){
return objj_msgSend(_27,"distanceFrom:to:",a,p);
}
var r=((p.x-a.x)*(b.x-a.x)+(p.y-a.y)*(b.y-a.y))/((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
if(r<=0){
return objj_msgSend(_27,"distanceFrom:to:",a,p);
}
if(r>=1){
return objj_msgSend(_27,"distanceFrom:to:",b,p);
}
var s=((a.y-p.y)*(b.x-a.x)-(a.x-p.x)*(b.y-a.y))/((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
return ABS(s)*SQRT(((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y)));
}
})]);
p;14;HandleMagnet.jt;2661;@STATIC;1.0;t;2642;
var _1=objj_allocateClassPair(CPObject,"HandleMagnet"),_2=_1.isa;
class_addIvars(_1,[new objj_ivar("_handle"),new objj_ivar("_sourceFigure"),new objj_ivar("_targetFigure")]);
objj_registerClassPair(_1);
class_addMethods(_1,[new objj_method(sel_getUid("initWithHandle:source:target:"),function(_3,_4,_5,_6,_7){
with(_3){
_3=objj_msgSendSuper({receiver:_3,super_class:objj_getClass("HandleMagnet").super_class},"init");
if(_3){
_handle=_5;
_sourceFigure=_6;
_targetFigure=_7;
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("updateHandleLocation:"),"CPViewFrameDidChangeNotification",_targetFigure);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("updateHandleLocation:"),"CPViewFrameDidChangeNotification",_handle);
objj_msgSend(objj_msgSend(CPNotificationCenter,"defaultCenter"),"addObserver:selector:name:object:",_3,sel_getUid("updateHandleLocation:"),"CPViewFrameDidChangeNotification",objj_msgSend(_handle,"targetFigure"));
return _3;
}
}
}),new objj_method(sel_getUid("updateHandleLocation:"),function(_8,_9,_a){
with(_8){
var _b=objj_msgSend(_sourceFigure,"center");
var p1=objj_msgSend(GeometryUtils,"intersectionOf:with:with:with:",objj_msgSend(_targetFigure,"topLeft"),objj_msgSend(_targetFigure,"topRight"),objj_msgSend(_targetFigure,"center"),_b);
var p2=objj_msgSend(GeometryUtils,"intersectionOf:with:with:with:",objj_msgSend(_targetFigure,"topRight"),objj_msgSend(_targetFigure,"bottomRight"),objj_msgSend(_targetFigure,"center"),_b);
var p3=objj_msgSend(GeometryUtils,"intersectionOf:with:with:with:",objj_msgSend(_targetFigure,"bottomRight"),objj_msgSend(_targetFigure,"bottomLeft"),objj_msgSend(_targetFigure,"center"),_b);
var p4=objj_msgSend(GeometryUtils,"intersectionOf:with:with:with:",objj_msgSend(_targetFigure,"bottomLeft"),objj_msgSend(_targetFigure,"topLeft"),objj_msgSend(_targetFigure,"center"),_b);
var _c=p1;
if(_c==nil||(p2!=nil&&(objj_msgSend(GeometryUtils,"distanceFrom:to:",p2,objj_msgSend(_handle,"center"))<objj_msgSend(GeometryUtils,"distanceFrom:to:",_c,objj_msgSend(_handle,"center"))))){
_c=p2;
}
if(_c==nil||(p3!=nil&&(objj_msgSend(GeometryUtils,"distanceFrom:to:",p3,objj_msgSend(_handle,"center"))<objj_msgSend(GeometryUtils,"distanceFrom:to:",_c,objj_msgSend(_handle,"center"))))){
_c=p3;
}
if(_c==nil||(p4!=nil&&(objj_msgSend(GeometryUtils,"distanceFrom:to:",p4,objj_msgSend(_handle,"center"))<objj_msgSend(GeometryUtils,"distanceFrom:to:",_c,objj_msgSend(_handle,"center"))))){
_c=p4;
}
if(_c!=nil){
objj_msgSend(_handle,"setFrameOrigin:",_c);
}else{
}
}
})]);
e;