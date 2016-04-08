function selectEmp(companyid) {
	var Empclassify = "业务员";
	var Emptitle = "当前位置:业务管理》" + Empclassify;
	var Empaction = "EmpAction.do";
	var Empfields = ['empid'
	        			    ,'empcompany' 
	        			    ,'empcode' 
	        			    ,'loginname' 
	        			    ,'password' 
	        			    ,'empdetail' 
	        			    ,'empstatue' 
	        			    ,'createtime' 
	        			    ,'updtime' 
	        			      ];// 全部字段
	var Empkeycolumn = [ 'empid' ];// 主键
	var Empstore = dataStore(Empfields, basePath + Empaction + "?method=selQuery");// 定义Empstore
	var Empsm = new Ext.grid.CheckboxSelectionModel();// grid复选框模式
	var Empcm = new Ext.grid.ColumnModel({// 定义columnModel
		columns : [ new Ext.grid.RowNumberer(), Empsm, {// 改
			header : '业务员ID',
			dataIndex : 'empid',
			hidden : true
		}
		, {
			header : '经销商ID',
			dataIndex : 'empcompany',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '编码',
			dataIndex : 'empcode',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '账号',
			dataIndex : 'loginname',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '密码',
			dataIndex : 'password',
			align : 'center',
			width : 80,
			hidden : true
		}
		, {
			header : '描述',
			dataIndex : 'empdetail',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '状态',
			dataIndex : 'empstatue',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '创建时间',
			dataIndex : 'createtime',
			align : 'center',
			width : 80,
			sortable : true
		}
		, {
			header : '修改时间',
			dataIndex : 'updtime',
			align : 'center',
			width : 80,
			sortable : true
		}
		]
	});
	var EmpdataForm = new Ext.form.FormPanel({// 定义新增和修改的FormPanel
		id:'EmpdataForm',
		labelAlign : 'right',
		frame : true,
		layout : 'column',
		items : [ {
			items : [ {
				xtype : 'textfield',
				id : 'Empempid',
				name : 'empid',
				hidden : true
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'hidden',
				fieldLabel : '经销商ID',
				id : 'Empempcompany',
				name : 'empcompany',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '编码',
				id : 'Empempcode',
				name : 'empcode',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '账号',
				id : 'Emploginname',
				name : 'loginname',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		, {
			columnWidth : 1,
			layout : 'form',
			items : [ {
				xtype : 'textfield',
				fieldLabel : '密码',
				id : 'Emppassword',
				name : 'password',
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
				id : 'Empempdetail',
				name : 'empdetail',
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
				hiddenName : 'empstatue',
				fieldLabel : '状态',
				id : 'Empempstatue',
				name : 'empstatue',
				maxLength : 100,
				anchor : '95%'
			} ]
		}
		]
	});
	
	var Empbbar = pagesizebar(Empstore);//定义分页
	var Empgrid = new Ext.grid.GridPanel({
		height : document.documentElement.clientHeight - 4,
		width : '100%',
		store : Empstore,
		stripeRows : true,
		frame : true,
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		},
		cm : Empcm,
		sm : Empsm,
		bbar : Empbbar,
		tbar : [{
				text : "新增",
				iconCls : 'add',
				handler : function() {
					EmpdataForm.form.reset();
					createWindow(basePath + Empaction + "?method=insAll", "新增", EmpdataForm, Empstore);
					EmpdataForm.getForm().setValues({Empempcompany:companyid});
				}
			},'-',{
				text : "修改",
				iconCls : 'edit',
				handler : function() {
					var selections = Empgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条要修改的记录！', function() {
						});
						return;
					}
					createWindow(basePath + Empaction + "?method=updAll", "修改", EmpdataForm, Empstore);
					EmpdataForm.form.loadRecord(selections[0]);
				}
			},'-',{
				text : "删除",
				iconCls : 'delete',
				handler : function() {
					var selections = Empgrid.getSelectionModel().getSelections();
					if (Ext.isEmpty(selections)) {
						Ext.Msg.alert('提示', '请选择您要删除的数据！');
						return;
					}
					commonDelete(basePath + Empaction + "?method=delAll",selections,Empstore,Empkeycolumn);
				}
			},'-',{
				text : "导入",
				iconCls : 'imp',
				handler : function() {
					commonImp(basePath + Empaction + "?method=impAll","导入",Empstore);
				}
			},'-',{
				text : "后台导出",
				iconCls : 'exp',
				handler : function() {
					Ext.Msg.confirm('请确认', '<b>提示:</b>请确认要导出当前数据？', function(btn, text) {
						if (btn == 'yes') {
							window.location.href = basePath + Empaction + "?method=expAll"; 
						}
					});
				}
			},'-',{
				text : "前台导出",
				iconCls : 'exp',
				handler : function() {
					commonExp(Empgrid);
				}
			},'-',{
				text : "附件",
				iconCls : 'attach',
				handler : function() {
					var selections = Empgrid.getSelectionModel().getSelections();
					if (selections.length != 1) {
						Ext.Msg.alert('提示', '请选择一条您要上传附件的数据！', function() {
						});
						return;
					}
					var fid = '';
					for (var i=0;i<Empkeycolumn.length;i++){
						fid += selections[0].data[Empkeycolumn[i]] + ","
					}
					commonAttach(fid, Empclassify);
				}
			}
		]
	});
	Empgrid.region = 'center';
	Empstore.on("beforeload",function(){ 
		Empstore.baseParams = {
				wheresql : "empcompany='"+companyid+"'"
		}; 
	});
	Empstore.load();//加载数据
	var selectgridWindow = new Ext.Window({
		title : Emptitle,
		layout : 'fit', // 设置窗口布局模式
		width : 620, // 窗口宽度
		height : document.body.clientHeight -4, // 窗口高度
		modal : true,
		closeAction: 'hide',
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		animateTarget : Ext.getBody(),
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 620 / 2, // 页面定位X坐标
		items : Empgrid
	});
	selectgridWindow.show();
}
