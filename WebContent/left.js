Ext.onReady(function() {
	var root = new Ext.tree.AsyncTreeNode({
		text : '菜单管理',
		expanded : true,
		icon : 'sysimages/home.gif',
		id : 'root'
	});
	var deptTree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
			baseAttrs : {},
			dataUrl : basePath + "System_menuAction.do?method=selMenuChildren"
		}),
		root : root,
		lines : true,
		applyTo : 'tree',
		autoScroll : true,
		height : document.documentElement.clientHeight - 4,
		width : 170,
		containerScroll : true,
		animate : false,
		singleExpand : true,
		useArrows : false,
		border : true
	});
})
