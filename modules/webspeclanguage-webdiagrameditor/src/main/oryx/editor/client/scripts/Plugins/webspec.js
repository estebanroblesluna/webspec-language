/**
 * Copyright (c) 2010
 * Jan-Felix Schwarz
 * Sven Wagner-Boysen
 **/

/**
 * @namespace Oryx name space for plugins
 * @name ORYX.Plugins
*/
 if(!ORYX.Plugins)
	ORYX.Plugins = new Object();

/**
 * The Webspec plugin provides layout methods primarily referring to the webspec stencilset. 
 * 
 * @class ORYX.Plugins.Webspec
 * @extends Clazz
 * @param {Object} facade The facade of the editor
 */
ORYX.Plugins.Webspec = 
/** @lends ORYX.Plugins.Webspec.prototype */
{
	/**
	 * Creates a new instance of the Webspec plugin and registers it on the
	 * layout events listed in the Webspec stencil set.
	 * 
	 * @constructor
	 * @param {Object} facade The facade of the editor
	 */
	construct: function(facade) {
		this.facade = facade;
		
		this.facade.registerOnEvent('layout.webspec.addWidget', this.handleLayoutAddWidget.bind(this));
		this.facade.registerOnEvent('layout.webspec.handleChanges', this.handleLayoutChanges.bind(this));
	},
	
	
	/**
	 *	Manages the layout for all the widgets inside an interaction 
	 *
	 * @param {Object} event
	 */
	handleLayoutAddWidget : function(event) {
		var shape = event.shape;
		var eheight = event.height;
		var swidth = shape.bounds.width() - 1;
		var sheight = shape.bounds.height();
		var widgets = shape.getChildNodes(false).sortBy(function(widget) {
			return widget.bounds.upperLeft().y;
		});
		
		widgets = widgets.findAll(function(node) {
			var roles = node.getStencil().roles();
			var rolesL = roles.length;
			for(var i=0; i<rolesL; i++){
				if(roles[i] == "http://code.google.com/p/webspec-language/#widget"){
					return true;
				}
			}
		});
		
		var counter = 0;
		var last = null;
			var header = shape._labels[shape.getId()+ "nameInteractionText"];
		if(widgets.length > 0) {
			widgets.each(function(widget) {
				var ul = widget.bounds.upperLeft();
				ul.x = 2;
				ul.y = 26 + counter++ * eheight;
				var lr = widget.bounds.lowerRight();
				lr.x = swidth;
				lr.y = ul.y + eheight;
				
				//making it elastic
				if(lr.y + (eheight/2) > sheight){
					var sul = shape.bounds.upperLeft();
					var slr = shape.bounds.lowerRight();
					slr.y = slr.y + eheight;
					shape.bounds.set(sul, slr);
				}
				last = widget;
				widget.bounds.set(ul, lr);
			});
			if(shape.properties["oryx-widgets"] == null){
				shape.properties["oryx-widgets"] = 0;
			}
			// Adjusting ths size when a widget is deleted
			shape.properties["oryx-oldWidgets"] = shape.properties["oryx-widgets"];
			shape.properties["oryx-widgets"] = counter;
			if(shape.properties["oryx-oldWidgets"] > shape.properties["oryx-widgets"]){
				var sul = shape.bounds.upperLeft();
				var slr = shape.bounds.lowerRight();
				slr.y = slr.y - eheight;
				shape.bounds.set(sul, slr);
			}
			
		}
	},
	
	/**
	 *	Manages the layout all the layour changes 
	 *
	 * @param {Object} event
	 */
	handleLayoutChanges : function(event) {
		var shape = event.shape;
		var id = shape.id;
		
		shape.isResizable = false;
		var swidth = shape.bounds.width();
		
		// Fixing the header line
		var svg_headerBorder = document.getElementById(id+"headerBorder");
		svg_headerBorder.setAttribute("x1",0);
		svg_headerBorder.setAttribute("y1",25);
		svg_headerBorder.setAttribute("x2",swidth);
		svg_headerBorder.setAttribute("y2",25);
		
		// Fixing the header text
		var header = shape._labels[shape.getId()+ "nameInteractionText"];
		if(header){
			header.y = 19;
		}
		
		// Dashed border if shape marked as "initial"
		var svg_rect = document.getElementById(id+"border");
		if(shape.properties["oryx-initial"] != "undefined" && shape.properties["oryx-initial"]){
			svg_rect.setAttribute("stroke-dasharray","5, 2");
		}else{
			svg_rect.setAttribute("stroke-dasharray","");
		}
	}
};

// Registering the plugin
ORYX.Plugins.Webspec = Clazz.extend(ORYX.Plugins.Webspec);
