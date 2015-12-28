Ext.onReady(function(){
	var System_passwordForm = new Ext.form.FormPanel({
		defaultType : 'textfield', // 表单元素默认类型
		labelWidth : 150, // 标签宽度
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:5px', // 表单元素和表单面板的边距
		layout : 'form',
		frame : true,
		items : [{
					inputType : 'password',
					fieldLabel : '请输入旧密码',
					id : 'oldpassword',
					name : 'oldpassword',
					maxLength : 100,
					allowBlank : false
				},{
					inputType : 'password',
					fieldLabel : '请输入新密码',
					id : 'newpassword',
					name : 'newpassword',
					maxLength : 100,
					allowBlank : false
				},{
					inputType : 'password',
					fieldLabel : '请再输入新密码',
					id : 'newpassword2',
					name : 'newpassword2',
					maxLength : 100,
					allowBlank : false
				}]
	});
	var System_passwordWindow = new Ext.Window({
		title : '修改密码', // 窗口标题
		layout : 'fit', // 设置窗口布局模式
		width : 420, // 窗口宽度
		height : 250, // 窗口高度
		modal : true,
		closable : true, // 是否可关闭
		collapsible : true, // 是否可收缩
		maximizable : true, // 设置是否可以最大化
		border : false, // 边框线设置
		constrain : true, // 设置窗口是否可以溢出父容器
		pageY : 50, // 页面定位Y坐标
		pageX : document.body.clientWidth / 2 - 420 / 2, // 页面定位X坐标
		items : [System_passwordForm], // 嵌入的表单面板
		buttons : [{
			text : '提交',
			iconCls : 'ok',
			handler : function() {
					if(Ext.getCmp("newpassword").getValue()==Ext.getCmp("newpassword2").getValue()){
						System_passwordForm.form.submit({
							url : basePath+"System_userAction.do?method=setpassword",
							waitTitle : '提示',
							method : 'POST',
							waitMsg : '正在处理数据,请稍候...',
							params : {
								password : Ext.getCmp("newpassword").getValue(),
								oldpassword : Ext.getCmp("oldpassword").getValue()
							},
							success : function(form, action) {
								Ext.Msg.alert('提示','操作成功');
		
							},
							failure : function(form, action) {
								Ext.Msg.alert('提示','旧密码错误');
							}
						});
					}else{
						alert("新密码输入不一致！");
					}
			}
		}]
	});
	System_passwordWindow.show(); // 显示窗口
});

