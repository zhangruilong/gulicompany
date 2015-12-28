package com.om.pojo;

import java.sql.Date;
/**
 * 人员 实体类
 *@author ZhangRuiLong
 */
public class Om_employee
{
   /**
    * 人员编号,主键
    */
   private String empid; 
   /**
    * 人员代码
    */
   private String empcode;   
   /**
    * 操作员编号
    */
   private String operatorid;   
   /**
    * 操作员登录号
    */
   private String loginname;   
   /**
    * 人员姓名
    */
   private String empname;   
   /**
    * 人员全名
    */
   private String realname;   
   /**
    * 性别
    */
   private String gender;   
   /**
    * 出生日期
    */
   private Date birthdate;   
   /**
    * 基本岗位
    */
   private String position;   
   /**
    * 状态
    */
   private String empstatus;   
   /**
    * 证件类型
    */
   private String cardtype;   
   /**
    * 证件号码
    */
   private String cardno;   
   /**
    * 入职日期
    */
   private Date indate;   
   /**
    * 离职日期
    */
   private Date outdate;   
   /**
    * 办公电话
    */
   private String otel;   
   /**
    * 办公地址
    */
   private String oaddress;   
   /**
    * 办公邮编
    */
   private String ozipcode;   
   /**
    * 办公邮件
    */
   private String oemail;   
   /**
    * 传真号码
    */
   private String faxno;   
   /**
    * 手机号码
    */
   private String mobileno;   
   /**
    * MSN号码
    */
   private String msn;   
   /**
    * 家庭电话
    */
   private String htel;   
   /**
    * 家庭地址
    */
   private String haddress;   
   /**
    * 家庭邮编
    */
   private String hzipcode;   
   /**
    * 私人电子邮箱
    */
   private String pemail;   
   /**
    * 政治面貌
    */
   private String party;   
   /**
    * 职级
    */
   private String degree;   
   /**
    * 直接主管
    */
   private String major;   
   /**
    * 可授权角色
    */
   private String specialty;   
   /**
    * 工作描述
    */
   private String workexp;   
   /**
    * 注册日期
    */
   private Date regdate;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 最新更新时间
    */
   private String lastmodytime;   
   /**
    * 可管理机构
    */
   private String orgidlist;   
   /**
    * 主机构编号
    */
   private String orgid;   
   /**
    * 备注
    */
   private String remark;   
    //属性方法	    
     /**
	 *设置主键"人员编号"属性
	 *@param empid 实体的Empid属性
	 */
	public void setEmpid(String empid)
	{
		this.empid = empid;
	}
	
	/**
	 *获取主键"人员编号"属性
	 */
	public String getEmpid()
	{
		return this.empid;
	}

	/**
	 *设置"人员代码"属性
	 *@param empcode 实体的Empcode属性
	 */
	public void setEmpcode(String empcode)
	{
		this.empcode = empcode;
	}
	
	/**
	 *获取"人员代码"属性
	 */
	public String getEmpcode()
	{
		return this.empcode;
	}	   

	/**
	 *设置"操作员编号"属性
	 *@param operatorid 实体的Operatorid属性
	 */
	public void setOperatorid(String operatorid)
	{
		this.operatorid = operatorid;
	}
	
	/**
	 *获取"操作员编号"属性
	 */
	public String getOperatorid()
	{
		return this.operatorid;
	}	   

	/**
	 *设置"操作员登录号"属性
	 *@param loginname 实体的Loginname属性
	 */
	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}
	
	/**
	 *获取"操作员登录号"属性
	 */
	public String getLoginname()
	{
		return this.loginname;
	}	   

	/**
	 *设置"人员姓名"属性
	 *@param empname 实体的Empname属性
	 */
	public void setEmpname(String empname)
	{
		this.empname = empname;
	}
	
	/**
	 *获取"人员姓名"属性
	 */
	public String getEmpname()
	{
		return this.empname;
	}	   

	/**
	 *设置"人员全名"属性
	 *@param realname 实体的Realname属性
	 */
	public void setRealname(String realname)
	{
		this.realname = realname;
	}
	
	/**
	 *获取"人员全名"属性
	 */
	public String getRealname()
	{
		return this.realname;
	}	   

	/**
	 *设置"性别"属性
	 *@param gender 实体的Gender属性
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	/**
	 *获取"性别"属性
	 */
	public String getGender()
	{
		return this.gender;
	}	   

	/**
	 *设置"出生日期"属性
	 *@param birthdate 实体的Birthdate属性
	 */
	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}
	
	/**
	 *获取"出生日期"属性
	 */
	public Date getBirthdate()
	{
		return this.birthdate;
	}	   

	/**
	 *设置"基本岗位"属性
	 *@param position 实体的Position属性
	 */
	public void setPosition(String position)
	{
		this.position = position;
	}
	
	/**
	 *获取"基本岗位"属性
	 */
	public String getPosition()
	{
		return this.position;
	}	   

	/**
	 *设置"状态"属性
	 *@param empstatus 实体的Empstatus属性
	 */
	public void setEmpstatus(String empstatus)
	{
		this.empstatus = empstatus;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getEmpstatus()
	{
		return this.empstatus;
	}	   

	/**
	 *设置"证件类型"属性
	 *@param cardtype 实体的Cardtype属性
	 */
	public void setCardtype(String cardtype)
	{
		this.cardtype = cardtype;
	}
	
	/**
	 *获取"证件类型"属性
	 */
	public String getCardtype()
	{
		return this.cardtype;
	}	   

	/**
	 *设置"证件号码"属性
	 *@param cardno 实体的Cardno属性
	 */
	public void setCardno(String cardno)
	{
		this.cardno = cardno;
	}
	
	/**
	 *获取"证件号码"属性
	 */
	public String getCardno()
	{
		return this.cardno;
	}	   

	/**
	 *设置"入职日期"属性
	 *@param indate 实体的Indate属性
	 */
	public void setIndate(Date indate)
	{
		this.indate = indate;
	}
	
	/**
	 *获取"入职日期"属性
	 */
	public Date getIndate()
	{
		return this.indate;
	}	   

	/**
	 *设置"离职日期"属性
	 *@param outdate 实体的Outdate属性
	 */
	public void setOutdate(Date outdate)
	{
		this.outdate = outdate;
	}
	
	/**
	 *获取"离职日期"属性
	 */
	public Date getOutdate()
	{
		return this.outdate;
	}	   

	/**
	 *设置"办公电话"属性
	 *@param otel 实体的Otel属性
	 */
	public void setOtel(String otel)
	{
		this.otel = otel;
	}
	
	/**
	 *获取"办公电话"属性
	 */
	public String getOtel()
	{
		return this.otel;
	}	   

	/**
	 *设置"办公地址"属性
	 *@param oaddress 实体的Oaddress属性
	 */
	public void setOaddress(String oaddress)
	{
		this.oaddress = oaddress;
	}
	
	/**
	 *获取"办公地址"属性
	 */
	public String getOaddress()
	{
		return this.oaddress;
	}	   

	/**
	 *设置"办公邮编"属性
	 *@param ozipcode 实体的Ozipcode属性
	 */
	public void setOzipcode(String ozipcode)
	{
		this.ozipcode = ozipcode;
	}
	
	/**
	 *获取"办公邮编"属性
	 */
	public String getOzipcode()
	{
		return this.ozipcode;
	}	   

	/**
	 *设置"办公邮件"属性
	 *@param oemail 实体的Oemail属性
	 */
	public void setOemail(String oemail)
	{
		this.oemail = oemail;
	}
	
	/**
	 *获取"办公邮件"属性
	 */
	public String getOemail()
	{
		return this.oemail;
	}	   

	/**
	 *设置"传真号码"属性
	 *@param faxno 实体的Faxno属性
	 */
	public void setFaxno(String faxno)
	{
		this.faxno = faxno;
	}
	
	/**
	 *获取"传真号码"属性
	 */
	public String getFaxno()
	{
		return this.faxno;
	}	   

	/**
	 *设置"手机号码"属性
	 *@param mobileno 实体的Mobileno属性
	 */
	public void setMobileno(String mobileno)
	{
		this.mobileno = mobileno;
	}
	
	/**
	 *获取"手机号码"属性
	 */
	public String getMobileno()
	{
		return this.mobileno;
	}	   

	/**
	 *设置"MSN号码"属性
	 *@param msn 实体的Msn属性
	 */
	public void setMsn(String msn)
	{
		this.msn = msn;
	}
	
	/**
	 *获取"MSN号码"属性
	 */
	public String getMsn()
	{
		return this.msn;
	}	   

	/**
	 *设置"家庭电话"属性
	 *@param htel 实体的Htel属性
	 */
	public void setHtel(String htel)
	{
		this.htel = htel;
	}
	
	/**
	 *获取"家庭电话"属性
	 */
	public String getHtel()
	{
		return this.htel;
	}	   

	/**
	 *设置"家庭地址"属性
	 *@param haddress 实体的Haddress属性
	 */
	public void setHaddress(String haddress)
	{
		this.haddress = haddress;
	}
	
	/**
	 *获取"家庭地址"属性
	 */
	public String getHaddress()
	{
		return this.haddress;
	}	   

	/**
	 *设置"家庭邮编"属性
	 *@param hzipcode 实体的Hzipcode属性
	 */
	public void setHzipcode(String hzipcode)
	{
		this.hzipcode = hzipcode;
	}
	
	/**
	 *获取"家庭邮编"属性
	 */
	public String getHzipcode()
	{
		return this.hzipcode;
	}	   

	/**
	 *设置"私人电子邮箱"属性
	 *@param pemail 实体的Pemail属性
	 */
	public void setPemail(String pemail)
	{
		this.pemail = pemail;
	}
	
	/**
	 *获取"私人电子邮箱"属性
	 */
	public String getPemail()
	{
		return this.pemail;
	}	   

	/**
	 *设置"政治面貌"属性
	 *@param party 实体的Party属性
	 */
	public void setParty(String party)
	{
		this.party = party;
	}
	
	/**
	 *获取"政治面貌"属性
	 */
	public String getParty()
	{
		return this.party;
	}	   

	/**
	 *设置"职级"属性
	 *@param degree 实体的Degree属性
	 */
	public void setDegree(String degree)
	{
		this.degree = degree;
	}
	
	/**
	 *获取"职级"属性
	 */
	public String getDegree()
	{
		return this.degree;
	}	   

	/**
	 *设置"直接主管"属性
	 *@param major 实体的Major属性
	 */
	public void setMajor(String major)
	{
		this.major = major;
	}
	
	/**
	 *获取"直接主管"属性
	 */
	public String getMajor()
	{
		return this.major;
	}	   

	/**
	 *设置"可授权角色"属性
	 *@param specialty 实体的Specialty属性
	 */
	public void setSpecialty(String specialty)
	{
		this.specialty = specialty;
	}
	
	/**
	 *获取"可授权角色"属性
	 */
	public String getSpecialty()
	{
		return this.specialty;
	}	   

	/**
	 *设置"工作描述"属性
	 *@param workexp 实体的Workexp属性
	 */
	public void setWorkexp(String workexp)
	{
		this.workexp = workexp;
	}
	
	/**
	 *获取"工作描述"属性
	 */
	public String getWorkexp()
	{
		return this.workexp;
	}	   

	/**
	 *设置"注册日期"属性
	 *@param regdate 实体的Regdate属性
	 */
	public void setRegdate(Date regdate)
	{
		this.regdate = regdate;
	}
	
	/**
	 *获取"注册日期"属性
	 */
	public Date getRegdate()
	{
		return this.regdate;
	}	   

	/**
	 *设置"创建时间"属性
	 *@param createtime 实体的Createtime属性
	 */
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}
	
	/**
	 *获取"创建时间"属性
	 */
	public String getCreatetime()
	{
		return this.createtime;
	}	   

	/**
	 *设置"最新更新时间"属性
	 *@param lastmodytime 实体的Lastmodytime属性
	 */
	public void setLastmodytime(String lastmodytime)
	{
		this.lastmodytime = lastmodytime;
	}
	
	/**
	 *获取"最新更新时间"属性
	 */
	public String getLastmodytime()
	{
		return this.lastmodytime;
	}	   

	/**
	 *设置"可管理机构"属性
	 *@param orgidlist 实体的Orgidlist属性
	 */
	public void setOrgidlist(String orgidlist)
	{
		this.orgidlist = orgidlist;
	}
	
	/**
	 *获取"可管理机构"属性
	 */
	public String getOrgidlist()
	{
		return this.orgidlist;
	}	   

	/**
	 *设置"主机构编号"属性
	 *@param orgid 实体的Orgid属性
	 */
	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}
	
	/**
	 *获取"主机构编号"属性
	 */
	public String getOrgid()
	{
		return this.orgid;
	}	   

	/**
	 *设置"备注"属性
	 *@param remark 实体的Remark属性
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	 *获取"备注"属性
	 */
	public String getRemark()
	{
		return this.remark;
	}	   
	public Om_employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_employee(
		String empid
	 	,String empcode
	 	,String operatorid
	 	,String loginname
	 	,String empname
	 	,String realname
	 	,String gender
	 	,Date birthdate
	 	,String position
	 	,String empstatus
	 	,String cardtype
	 	,String cardno
	 	,Date indate
	 	,Date outdate
	 	,String otel
	 	,String oaddress
	 	,String ozipcode
	 	,String oemail
	 	,String faxno
	 	,String mobileno
	 	,String msn
	 	,String htel
	 	,String haddress
	 	,String hzipcode
	 	,String pemail
	 	,String party
	 	,String degree
	 	,String major
	 	,String specialty
	 	,String workexp
	 	,Date regdate
	 	,String createtime
	 	,String lastmodytime
	 	,String orgidlist
	 	,String orgid
	 	,String remark
		 ){
		super();
		this.empid = empid;
	 	this.empcode = empcode;
	 	this.operatorid = operatorid;
	 	this.loginname = loginname;
	 	this.empname = empname;
	 	this.realname = realname;
	 	this.gender = gender;
	 	this.birthdate = birthdate;
	 	this.position = position;
	 	this.empstatus = empstatus;
	 	this.cardtype = cardtype;
	 	this.cardno = cardno;
	 	this.indate = indate;
	 	this.outdate = outdate;
	 	this.otel = otel;
	 	this.oaddress = oaddress;
	 	this.ozipcode = ozipcode;
	 	this.oemail = oemail;
	 	this.faxno = faxno;
	 	this.mobileno = mobileno;
	 	this.msn = msn;
	 	this.htel = htel;
	 	this.haddress = haddress;
	 	this.hzipcode = hzipcode;
	 	this.pemail = pemail;
	 	this.party = party;
	 	this.degree = degree;
	 	this.major = major;
	 	this.specialty = specialty;
	 	this.workexp = workexp;
	 	this.regdate = regdate;
	 	this.createtime = createtime;
	 	this.lastmodytime = lastmodytime;
	 	this.orgidlist = orgidlist;
	 	this.orgid = orgid;
	 	this.remark = remark;
	}
}

