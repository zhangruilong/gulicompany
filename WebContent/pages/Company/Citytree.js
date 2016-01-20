var treepanel;
Ext.onReady(function() {
	var Cityaction = "CityAction.do";
	var root = new Ext.tree.AsyncTreeNode({
		text : '城市管理',
		expanded : true,
		icon : '../../sysimages/home.gif',
		id : 'root'
	});
	treepanel = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
			baseAttrs : {},
			dataUrl : basePath + Cityaction + "?method=selTree"
		}),
		root : root,
		lines : true,
		title : '城市管理',
		autoScroll : true,
		height : document.documentElement.clientHeight - 4,
		width : 180,
		containerScroll : true,
		animate : false,
		useArrows : false,
		enableDD : true, // 拖拽节点
	    listeners: {
            'click': function(node, checked){
            	node.getUI().getAnchor().href = "javascript:void(0);"
            	var queryValue = node.attributes['code'];
            	if(node.id=='root'){
        			queryValue = "";
        		}
            	Companystore.load({
            		params : {
            			query : node.id
            		}
            	});
            }
        }
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ {
	        region: 'west',
	        floatable: false,
	        width: 180,
	        items: treepanel
	    }, Companygrid]
	});
})
