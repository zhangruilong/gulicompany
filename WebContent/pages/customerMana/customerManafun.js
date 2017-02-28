var Empfields = ['empid','empcompany','empcode','empdetail'];// 全部字段
var Empkeycolumn = [ 'empid' ];// 主键
var Empstore = dataStore(Empfields, basePath + "CPEmpAction.do?method=selAll&wheresql=empcompany='"+comid+"' and empstatue='启用' and empcode!='隐藏'");// 定义Empstore


















