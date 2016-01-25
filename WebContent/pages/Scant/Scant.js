Ext.onReady(function() {
	var Scantclassify = "标品库";
	var Scanttitle = "当前位置:业务管理》" + Scantclassify;
	var Scantaction = "ScantAction.do";
	var Scantfields = ['scantid'
       			    ,'scantcode' 
    			    ,'scantname' 
    			    ,'scantdetail' 
    			    ,'scantunits' 
    			    ,'scantclass' 
    			    ,'scantimage' 
    			    ,'scantstatue' 
    			    ,'goodsclassid' 
    			    ,'goodsclasscode' 
    			    ,'goodsclassname' 
    			    ,'goodsclassparent' 
    			    ,'goodsclassdetail' 
    			    ,'goodsclassstatue' 
    			      ];// 全部字段
	var Scantkeycolumn = [ 'scantid' ];// 主键
	var Scantstore = dataStore(Scantfields, basePath + "ScantviewAction.do" + "?method=selQuery");// 定义Scantstore
	var Scantsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Scantcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Scantsm, {// 改
			header : '标品ID',
			dataIndex : 'scantid',
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'scantcode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '名称',
			dataIndex : 'scantname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '描述',
			dataIndex : 'scantdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '规格',
			dataIndex : 'scantunits',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '小类ID',
			dataIndex : 'scantclass',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '小类',
			dataIndex : 'goodsclassname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '图片',
			dataIndex : 'scantimage',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'scantstatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var ScantdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'ScantdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Scantscantid',
				name : 'scantid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Scantscantcode',
				name : 'scantcode',
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
				id : 'Scantscantname',
				name : 'scantname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '描述',
				id : 'Scantscantdetail',
				name : 'scantdetail',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '规格',
				id : 'Scantscantunits',
				name : 'scantunits',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '小类ID',
				id : 'Scantscantclass',
				name : 'scantclass',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : .9,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '小类',
				id : 'Scantscantclassname',
				name : 'goodclassname',
				readOnly:true,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : .1,
			layout : 'form',
			items : [ {
				xtype : 'button',
				iconCls : 'select',
				maxLength : 100,
				handler : selectGoodsclass.createCallback(),
				scope : this,
				anchor : '25%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '图片',
				id : 'Scantscantimage',
				name : 'scantimage',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'combo',
				emptyText : '请选择',
				store : statueStore,
				mode : 'local',
				triggerAction : 'all',
				editable : false,
				allowBlank : false,
				displayField : 'name',
				valueField : 'name',
				hiddenName : 'scantstatue',
				fieldLabel : '状态',
				id : 'Scantscantstatue',
				name : 'scantstatue',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Scantbbar = pagesizebar(Scantstore);//定义分页
	var Scantgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		title : Scanttitle,
		store : Scantstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Scantcm,
		sm : Scantsm,
		bbar : Scantbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					ScantdataForm.form.reset();
					createWindow(basePath + Scantaction + "?method=insAll", "新增", ScantdataForm, Scantstore);
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Scantgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Scantaction + "?method=updAll", "修改", ScantdataForm, Scantstore);
					ScantdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Scantgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Scantaction + "?method=delAll",selections,Scantstore,Scantkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Scantaction + "?method=impAll","导入",Scantstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Scantaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Scantgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Scantgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Scantkeycolumn.length;i++){
						fid += selections[0].data[Scantkeycolumn[i]] + ","
					}
					commonAttach(fid, Scantclassify);
				}
			},'->',{
				xtype : 'textfield',
				id : 'query'+Scantaction,
				name : 'query',
				emptyText : '模糊匹配',
				width : 100,
				enableKeyEvents : true,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							if ("" == Ext.getCmp("query"+Scantaction).getValue()) {
								Scantstore.load();
							} else {
								Scantstore.load({
									params : {
										query : Ext.getCmp("query"+Scantaction).getValue()
									}
								});
							}
						}
					}
				}
			}
		]
	});
	Scantgrid.region = 'center';
	Scantstore.load();//加载数据
	Scantstore.on("beforeload",function(){ 
		Scantstore.baseParams = {
				query : Ext.getCmp("query"+Scantaction).getValue()
		}; 
	});
	var win = new Ext.Viewport({//只能有一个viewport
		resizable : true,
		layout : 'border',
		bodyStyle : 'padding:0px;',
		items : [ Scantgrid ]
	});
})
