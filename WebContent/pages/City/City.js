	var Cityclassify = "城市和县及街道";
	var Citytitle = "当前位置:业务管理》" + Cityclassify;
	var Cityaction = "CityAction.do";
	var Cityfields = ['cityid'
	        			    ,'citycode' 
	        			    ,'cityname' 
	        			    ,'cityparent' 
	        			    ,'citydetail' 
	        			    ,'citystatue' 
	        			      ];// 全部字段
	var Citykeycolumn = [ 'cityid' ];// 主键
	var Citystore = dataStore(Cityfields, basePath + Cityaction + "?method=selQuery");// 定义Citystore
	var Citysm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Citycm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Citysm, {// 改
			header : '城市ID',
			dataIndex : 'cityid',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'citycode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'cityname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '父类',
			dataIndex : 'cityparent',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '描述',
			dataIndex : 'citydetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'citystatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var CitydataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'CitydataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Citycityid',
				name : 'cityid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Citycitycode',
				name : 'citycode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '名称',
				id : 'Citycityname',
				name : 'cityname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '父类',
				id : 'Citycityparent',
				name : 'cityparent',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '父节点',
				id : 'Citycityparentname',
				name : 'cityparentname',
				readOnly:true,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Citycitydetail',
				name : 'citydetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				fieldLabel : '状态',
				id : 'Citycitystatue',
				name : 'citystatue',
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',
				displayField : 'name',
				valueField : 'name',
				hiddenName : 'hreftarget',
				triggerAction : 'all',
				editable : false,
				maxLength : 100,
				allowBlank : false,
				fieldLabel : '状态',
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Citybbar = pagesizebar(Citystore);//定义分页
	var Citygrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Citytitle,
		store : Citystore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Citycm,
		sm : Citysm,
		bbar : Citybbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					CitydataForm.form.reset();
					createWindow(basePath + Cityaction + "?method=insAll", "新增", CitydataForm, Citystore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Citygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Cityaction + "?method=updAll", "修改", CitydataForm, Citystore);
					CitydataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Citygrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Cityaction + "?method=delAll",selections,Citystore,Citykeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Cityaction + "?method=impAll","导入",Citystore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Cityaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Citygrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Citygrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Citykeycolumn.length;i++){
						fid += selections[0].data[Citykeycolumn[i]] + ","
					}
					commonAttach(fid, Cityclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Cityaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Cityaction).getValue()) {
								Citystore.load();
							} else {
								Citystore.load({
									params : {
										query : Ext.getCmp("query"+Cityaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Citygrid.region = 'center';
	Citystore.load();//加载数据
	Citystore.on("beforeload",function(){ 
		Citystore.baseParams = {
				query : Ext.getCmp("query"+Cityaction).getValue()
		}; 
	});
