function getTabPanel(roleid){
	var root = new Ext.tree.AsyncTreeNode({
		text : '应用菜单',
		expanded : true,
		icon : '../../../sysimages/home.gif',
		id : 'root'
	});
	function mytoggleChecked(node){
        //迭代复选=>父节点影响子节点选择,子节点不影响父节点
        if(node.hasChildNodes())
        {
           //eachChild(fn),遍历函数
           node.eachChild(function(child){
                     child.getUI().toggleCheck(node.attributes.checked);   
                     child.attributes.checked = node.attributes.checked; 
                     //child.getUI().checkbox.checked=node.getUI().checkbox.checked;//有其父必有其子
                     child.on("checkchange",function(sub){
                         mytoggleChecked(sub);//传递子节点
                     });
                     mytoggleChecked(child);//处理子节点(第三级,有点晕阿)
           })
        }
	}
	var dree = new Ext.tree.TreePanel({
		loader : new Ext.tree.TreeLoader({
			baseAttrs : {},
			dataUrl : basePath+"System_rolepowerviewAction.do?method=selRolepowertree&roleid="+roleid
		}),
		root : root,
		lines : true,
		autoScroll : true,
		height : document.documentElement.clientHeight - 32,
		containerScroll : true,
		animate : false,
		singleExpand : false,
		useArrows : false,
	    listeners: {
            'checkchange':function(node,checked){
            	mytoggleChecked(node);
            }
        },
        tbar:[{
        	 iconCls : 'ok',
        	 text: '保存',
        	 handler:function(){
        		var ids = '[';
        		var selNodes = dree.getChecked();
        		Ext.each(selNodes, function(node){
        			 ids += "{";
        			 ids += "'roleid':'" + roleid + "','powerid':'"+node.id+"'";
        			 ids = ids.substr(0, ids.length - 1) + "'},";
                });
				Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要保存当前操作吗？', function(btn,text) {
					if (btn == 'yes') {
						Ext.Ajax.request({
							url : basePath+"System_rolepowerAction.do?method=insRolepowertree",
							method : 'POST',
							params : {
								json : ids.substr(0, ids.length - 1) + "]"
							},
							success : function(response) {
								var resp = Ext.decode(response.responseText); 
								Ext.Msg.alert('提示', resp.msg, function(){
									store.reload();
								});
							},
							failure : function(response) {
								Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
							}
						});
					}
				});
        	 }
        }],
		border : true
	});
	dree.expandAll();
	//dree.collapseAll();
	var tabs = new Ext.TabPanel({
	    margins:'3 3 3 0', 
	    activeTab: 0,
	    id:"tabs",
	    frame : true,
	    defaults:{autoScroll:true},
	    items:[{
	        title: '功能权限',
	        items: dree
	    },{
	        title: '权限分配',
	        items: roleUserGrid(roleid)
	    }]
	});
	return tabs;
}
function editeInfo(roleid){
	var editPanel = Ext.getCmp("editPanel");
	if(Ext.getCmp("tabs")){
		editPanel.remove(Ext.getCmp("tabs"),true);
	}
	editPanel.add(getTabPanel(roleid));
	editPanel.doLayout();
}
function selQueryRemoveRoleuser(roleid,System_roleuserstore) {
	var System_userclassify = "用户";
	var System_usertitle = null;
	var System_useraction = "System_userAction.do";
	var System_userfields = ['id'
	        			    ,'loginname' 
	        			    ,'password' 
	        			    ,'username' 
	        			    ,'statue' 
	        			      ];// 全部字段
	var System_userkeycolumn = [ 'id' ];// 主键
	var System_userstore = dataStore(System_userfields, basePath + System_useraction + "?method=selQueryRemoveRoleuser&roleid=" + roleid);// 定义System_userstore
	var System_usersm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_usercm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_usersm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : '登录名',
			dataIndex : 'loginname',
			sortable : true
		}
		, {
			header : '用户名',
			dataIndex : 'username',
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'statue',
			sortable : true
		}
		]
	});
	
	var System_userbbar = pagesizebar(System_userstore);//定义分页
	var System_usergrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_usertitle,
		store : System_userstore,
		stripeRows : true,
		autoScroll : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_usercm,
		sm : System_usersm,
		bbar : System_userbbar,
		tbar : [{
				xtype : 'textfield',
				id : 'query'+System_useraction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_useraction).getValue()) {
								System_userstore.load();
							} else {
								System_userstore.load({
									params : {
										query : Ext.getCmp("query"+System_useraction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_userstore.load();//加载数据
	System_userstore.on("beforeload",function(){ 
		System_userstore.baseParams = {
			query : Ext.getCmp("query"+System_useraction).getValue()
		}; 
	});
	var System_userwin = new Ext.Window({
		title : '操作员列表',
		layout : 'form',
		width : 620,
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,//背景失效
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		plain : true,
		items : System_usergrid,
		buttons : [{
						text : '提交',
						iconCls : 'ok',
						handler : function() {
							var selections = System_usergrid.getSelectionModel().getSelections();
							if (Ext.isEmpty(selections)) {
								Ext.Msg.alert('提示', '请选择用户');
								return;
							}
							Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要保存当前操作吗？', function(btn,text) {
								if (btn == 'yes') {
									var jsonvalue = '[';
									for (var i=0;i<selections.length;i++) {
										jsonvalue = jsonvalue+'{"roleid":"'+roleid
												+'","userid":"'+selections[i].get("id")
												+'"},'
									};
									Ext.Ajax.request({
										url : basePath+"System_roleuserAction.do?method=insAll",
										waitTitle : '提示',
										method : 'POST',
										waitMsg : '正在处理数据,请稍候...',
										params : {// 改
											json : jsonvalue.substr(0,jsonvalue.length-1)+']'
										},
										success : function(response) {
											var resp = Ext.decode(response.responseText); 
											Ext.Msg.alert('提示', resp.msg, function(){
												System_roleuserstore.reload();
												System_userwin.close();
											});
										},
										failure : function(response) {
											Ext.Msg.alert('提示', '网络出现问题，请稍后再试');
										}
									});
								}
							});
						}
					}, '-', {
						text : '关闭',
						iconCls : 'close',
						handler : function() {
							System_userwin.close();
						}
					}]
			});
	System_userwin.show();
};
function roleUserGrid(roleid) {
	var System_roleuserclassify = "角色人员";
	var System_roleusertitle = null;
	var System_roleuseraction = "System_roleuserAction.do";
	var System_roleuserviewaction = "System_roleuserviewAction.do";
	var System_roleuserfields = ['id'
	        			    ,'roleid' 
	        			    ,'userid' 
	        			    ,'type' 
	        			    ,'rolecode' 
	        			    ,'rolename' 
	        			    ,'roledetail' 
	        			    ,'fcode' 
	        			    ,'fname'
	        			    ,'fdetail'
	        			      ];// 全部字段
	var System_roleuserkeycolumn = [ 'id' ];// 主键
	var System_roleuserstore = dataStore(System_roleuserfields, basePath + System_roleuserviewaction + "?method=selRoleuser&roleid="+roleid);// 定义System_roleuserstore
	var System_roleusersm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var System_roleusercm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), System_roleusersm, {// 改
			header : 'ID',
			dataIndex : 'id',
			hidden : true
		}
		, {
			header : 'ROLEID',
			dataIndex : 'roleid',
			sortable : true,
			hidden : true
		}
		, {
			header : 'USERID',
			dataIndex : 'userid',
			sortable : true,
			hidden : true
		}
		, {
			header : '登录名',
			dataIndex : 'fcode',
			sortable : true
		}
		, {
			header : '用户名',
			dataIndex : 'fname',
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'fdetail',
			sortable : true
		}
		]
	});
	var System_roleuserdataForm = null;

	var System_roleuserbbar = pagesizebar(System_roleuserstore);//定义分页
	var System_roleusergrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : System_roleusertitle,
		store : System_roleuserstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : System_roleusercm,
		sm : System_roleusersm,
		bbar : System_roleuserbbar,
		tbar : [{
			iconCls : 'add',
			text : '新增',
			handler : selQueryRemoveRoleuser.createCallback(roleid,System_roleuserstore)
		},'-',{
			text : '移除',
			iconCls : 'delete',
			handler : function() {
				var selections = System_roleusergrid.getSelectionModel().getSelections();
				if (Ext.isEmpty(selections)) {
					Ext.Msg.alert('提示', '请选择您要删除的数据！');
					return;
				}
				commonDelete(basePath + System_roleuseraction + "?method=delAll",selections,System_roleuserstore,System_roleuserkeycolumn);
			}
		},'->',{
				xtype : 'textfield',
				id : 'query'+System_roleuseraction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+System_roleuseraction).getValue()) {
								System_roleuserstore.load();
							} else {
								System_roleuserstore.load({
									params : {
										query : Ext.getCmp("query"+System_roleuseraction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	System_roleusergrid.height = System_roleusergrid.height - 28;
	System_roleuserstore.load();//加载数据
	System_roleuserstore.on("beforeload",function(){ 
		System_roleuserstore.baseParams = {
				query : Ext.getCmp("query"+System_roleuseraction).getValue()
		}; 
	});
	return System_roleusergrid;
}
