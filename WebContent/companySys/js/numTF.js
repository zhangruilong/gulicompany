/** 
 * 数字转中文 
* @param dValue 
 * @returns 
 */ 
 function chineseNumber(dValue) { 
 var maxDec = 2; 
 // 验证输入金额数值或数值字符串： 
dValue = dValue.toString().replace(/,/g, ""); 
dValue = dValue.replace(/^0+/, ""); // 金额数值转字符、移除逗号、移除前导零 
if (dValue == "") { 
return "零元整"; 
 } // （错误：金额为空！） 
else if (isNaN(dValue)) { 
 return "错误：金额不是合法的数值！"; 
 } 
var minus = ""; // 负数的符号"-"的大写："负"字。可自定义字符，如"（负）"。 
var CN_SYMBOL = ""; // 币种名称（如"人民币"，默认空） 
if (dValue.length > 1) { 
 if (dValue.indexOf('-') == 0) { 
 dValue = dValue.replace("-", ""); 
minus = "负"; 
 } // 处理负数符号"-" 
if (dValue.indexOf('+') == 0) { 
 dValue = dValue.replace("+", ""); 
 } // 处理前导正数符号"+"（无实际意义） 
} 