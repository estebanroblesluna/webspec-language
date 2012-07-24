Selenium.prototype.doLoadSimulation = function() {
    var _doc = selenium.browserbot.getCurrentWindow().document;
	
	var fileref = _doc.createElement('link');
  	fileref.setAttribute("type","text/css");
  	fileref.setAttribute("rel","stylesheet");
  	fileref.setAttribute("href", "file:///Users/estebanroblesluna/Documents/workspaceWebSpecAll2/trunk4/modules/webspeclanguage-simulation/src/main/resources/selenium/tip.css");
  	
	var fileref2 = _doc.createElement('script');
  	fileref2.setAttribute("type","text/javascript");
  	fileref2.setAttribute("src", "http://jqueryjs.googlecode.com/files/jquery-1.3.2.js");
  	
  	var fileref3 = _doc.createElement('script');
  	fileref3.setAttribute("type","text/javascript");
  	fileref3.setAttribute("src", "http://flowplayer.org/js/tools/tools.tooltip-1.1.3.js?download=true");
	
	var fileref4 = _doc.createElement('script');
  	fileref4.setAttribute("type","text/javascript");
  	fileref4.setAttribute("src", "http://flowplayer.org/js/tools/tools.tooltip.slide-1.0.0.js?download=true");
	
	var fileref5 = _doc.createElement('script');
  	fileref5.setAttribute("type","text/javascript");
  	fileref5.setAttribute("src", "http://flowplayer.org/js/tools/tools.tooltip.dynamic-1.0.1.js?download=true");
  	
  	_doc.body.appendChild(fileref);
  	_doc.body.appendChild(fileref2);
  	_doc.body.appendChild(fileref3);
  	_doc.body.appendChild(fileref4);
  	_doc.body.appendChild(fileref5);

	var _demotip = _doc.createElement('div');
    _demotip.setAttribute("id", "webSpecSimulationTip");
    _demotip.setAttribute("class", "webSpecSimulationTip");
	_doc.body.appendChild(_demotip);

	var _demotop = _doc.createElement('div');
    _demotop.setAttribute("id", "webSpecSimulationTop");
	_doc.body.appendChild(_demotop);
}

Selenium.prototype.doShowMessage = function(message, ms) {
	this.doShowMessageWithLocator("id=webSpecSimulationTop", message);
}

Selenium.prototype.doShowMessageWithLocator = function(locator, message) {
    var _doc = selenium.browserbot.getCurrentWindow().document;
	
    var _elem = this.page().findElement(locator);
	var _oldID = _elem.getAttribute("id");
	var _newID = _oldID ? _oldID : "webSpecSimulationTarget";
	
    _elem.setAttribute("title", message);
    _elem.setAttribute("id", _newID);
    
    //write js
    var _scr = _doc.createElement('script');
    
    var script = "";
    script += "var vvv = $(\"#"+_newID +"\").tooltip({\n"
	script += "	tip:'#webSpecSimulationTip',\n"
	script += "	offset: [10, 2],\n"
	script += "	effect: 'slide',\n"
	script += "	opacity: 0.9, \n"
	script += "	events: { \n"
    script += "		def: \"showDescriptionEvent,mouseout\",\n" 
    script += "		input:  \"showDescriptionEvent,mouseout\",\n" 
    script += "		widget: \"showDescriptionEvent,mouseout\",\n" 
    script += "		tooltip: \"showDescriptionEvent,mouseout\",\n" 
	script += "	} \n"
	script += "});\n"
	script += "if (vvv.dynamic) {\n"
	script += "vvv.dynamic({bottom: {direction: 'down', bounce: true }});\n"
	script += "}\n"
    script += "$(\"#webSpecSimulationTip\").hide();\n";
    script += "$(\"#webSpecSimulationTip\").removeClass('webSpecSimulationTip bottom');\n";
    script += "$(\"#"+_newID +"\").trigger('showDescriptionEvent');\n";
    script += "$(\"#webSpecSimulationTip\").html(\""+message+"\");\n";
    
    var _text = _doc.createTextNode(script);
    _scr.appendChild(_text);
  	_doc.body.appendChild(_scr);
    
    //restore
    _elem.setAttribute("id", _oldID);
    
    //delete js
    //_doc.body.removeChild(_scr);
}