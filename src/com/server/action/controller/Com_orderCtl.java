package com.server.action.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CompanyMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.OrderdMapper;
import com.server.dao.mapper.OrdermMapper;
import com.server.pojo.entity.Company;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.Orderd;
import com.server.pojo.entity.OrderdStatistics;
import com.server.pojo.entity.Orderm;
import com.server.pojo.entity.Ordermview;
import com.system.tools.util.FileUtil;

/**
 * 供应商后台管理系统-订单管理
 * @author tao
 *
 */
@Controller
public class Com_orderCtl {
	@Autowired
	private OrdermMapper ordermMapper;
	@Autowired
	private OrderdMapper orderdMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CustomerMapper customerMapper;
	//全部订单
	@RequestMapping("/companySys/allOrder")
	public String allOrder(Model model,String staTime,String endTime,Orderm order,Integer pagenow){
		if(staTime != null && !staTime.equals("") && staTime.length() <19){
			staTime = staTime + " 00:00:00";
		}
		if(endTime != null && !endTime.equals("") && endTime.length() <19){
			endTime = endTime + " 23:59:59";
		}
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = ordermMapper.selectByCompanyCount(staTime, endTime,order);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Ordermview> ordermList = ordermMapper.selectByPage(staTime, endTime,order,pagenow,10);
		model.addAttribute("allOrder", ordermList);
		model.addAttribute("order", order);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "forward:/companySys/orderMana.jsp";
	}
	//删除订单
	@RequestMapping("/companySys/editOrder")
	public String editOrder(Model model,Orderm order){
		order.setOrdermstatue("已删除");
		ordermMapper.updateByPrimaryKeySelective(order);
		return "forward:allOrder.action";
	}
	//修改订单状态
	@RequestMapping("/companySys/deliveryGoods")
	public String deliveryGoods(Model model,Orderm order){
		ordermMapper.updateByPrimaryKeySelective(order);
		model.addAttribute("order", order);
		return "forward:allOrder.action";
	}
	//修改订单状态2
	@RequestMapping("/companySys/updateStatue")
	public @ResponseBody void updateStatue( Orderm order){
		ordermMapper.updateByPrimaryKeySelective(order);
	}
/*--------------------------订单详情-----------------------------*/
	//订单详情
	@RequestMapping("/companySys/orderDetail")
	public String orderDetail(Model model,Orderm order){
		order = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		model.addAttribute("order", order);
		return "forward:/companySys/orderDetail.jsp";
	}
	//删除订单详情
	@RequestMapping("/companySys/deleOrderd")
	public String deleOrderd(Model model,String[] orderdids,Orderm order){
		Float money = Float.parseFloat(order.getOrdermmoney());
		Float rightmoney = Float.parseFloat(order.getOrdermrightmoney());
		Integer ordermnum = order.getOrdermnum();
		for (String orderdid : orderdids) {
			Orderd orderd = orderdMapper.selectByPrimaryKey(orderdid);
			money -= Float.parseFloat(orderd.getOrderdmoney());
			rightmoney -= Float.parseFloat(orderd.getOrderdrightmoney());
			ordermnum--;
			orderdMapper.deleteByPrimaryKey(orderdid);
		}
		order.setOrdermmoney(money.toString());
		order.setOrdermrightmoney(rightmoney.toString());
		order.setOrdermnum(ordermnum);
		ordermMapper.updateByPrimaryKeySelective(order);
		return "forward:orderDetail.action";
	}
	//到'订单详情'修改页
	@RequestMapping("/companySys/doeditOrder")
	public String doeditOrder(Model model,String orderdid,Orderm order){
		Orderd orderd = orderdMapper.selectByPrimaryKey(orderdid);
		model.addAttribute("orderd", orderd);
		model.addAttribute("order", order);
		return "forward:/companySys/orderdDeta.jsp";
	}
	//修改订单详情
	@RequestMapping("/companySys/editOrderd")
	public String editOrderd(Model model,Orderd orderd,Orderm order,Float diffOrderdmoney,Float diffOrderdrightmoney){
		Orderm updateOrderm = ordermMapper.selectByPrimaryKey(order.getOrdermid());					//查询要修改的订单
		Float nowOrdermmoney = Float.parseFloat(updateOrderm.getOrdermmoney()) - diffOrderdmoney;	//得到计算后的下单金额
		Float nowOrderdrightmoney = Float.parseFloat(updateOrderm.getOrdermrightmoney()) - diffOrderdrightmoney;	//计算后的实际金额
		updateOrderm.setOrdermmoney(nowOrdermmoney.toString());				
		updateOrderm.setOrdermrightmoney(nowOrderdrightmoney.toString());
		ordermMapper.updateByPrimaryKeySelective(updateOrderm);										//修改下单金额和实际金额
		orderdMapper.updateByPrimaryKeySelective(orderd);
		model.addAttribute("orderd", orderd);
		model.addAttribute("order", order);
		return "forward:orderDetail.action";
	}
	//打印订单
	@RequestMapping("/companySys/printOrder")
	public String printOrder(Model model,Orderm order){
		order = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		Company company = companyMapper.selectByPrimaryKey(order.getOrdermcompany());		//要打印的订单
		Customer customer = customerMapper.selectByPrimaryKey(order.getOrdermcustomer());
		model.addAttribute("order", order);
		model.addAttribute("printCompany", company);
		model.addAttribute("printCustomer", customer);
		return "forward:/companySys/print.jsp";
	}
	//订单商品统计
	@RequestMapping("/companySys/orderStatistics")
	public String orderStatistics(Model model,String staTime,String endTime,String companyid,String condition,Integer pagenow) throws Exception{
		if(staTime != null && !staTime.equals("") && staTime.length() <19){
			staTime = staTime + " 00:00:00";
		}
		if(endTime != null && !endTime.equals("") && endTime.length() <19){
			endTime = endTime + " 23:59:59";
		}
		if(pagenow == null){
			pagenow = 1;
		}
		Integer count = orderdMapper.selectByTimeCount(staTime, endTime, companyid,condition);	//总信息条数
		Integer pageCount;		//总页数
		if(count % 10 ==0){
			pageCount = count / 10;
		} else {
			pageCount = (count / 10) +1;
		}
		List<Orderd> list = orderdMapper.selectByPage(staTime, endTime, companyid,condition,pagenow,10);					//查询数据
		OrderdStatistics total = orderdMapper.selectOrderdStatistics(staTime, endTime, companyid, condition);	//数据统计
		model.addAttribute("total", total);
		model.addAttribute("orderdList", list);
		model.addAttribute("companyid", companyid);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("condition", condition);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagenow", pagenow);
		model.addAttribute("count", count);
		return "orderStatistics.jsp";
	}
	//导出报表
	@RequestMapping("/companySys/exportReport")
	@ResponseBody
	public void exportReport(HttpServletResponse response,String staTime,String endTime,String companyid,String condition) throws Exception{
		/*if(staTime != null && !staTime.equals("") && staTime.length() <19){
			staTime = staTime + " 00:00:00";
		}
		if(endTime != null && !endTime.equals("") && endTime.length() <19){
			endTime = endTime + " 23:59:59";
		}*/
		ArrayList<Orderd> list = (ArrayList<Orderd>) orderdMapper.selectByTime(staTime, endTime, companyid,condition);
		String[] heads = {"商品编码","商品名称","规格","商品单价","单位","数量","商品总价","实际金额"};				//表头
		String[] discard = {"orderdid","orderdorderm","orderddetail","orderdclass","orderdtype","orderm"};			//要忽略的字段名
		String name = "订单商品统计报表";							//文件名称
		if(!staTime.equals("") && !endTime.equals("")){
			name = staTime + "日至" + endTime + "日的" + name;
		} else if(staTime.equals("") && !endTime.equals("")){
			name = endTime + "日之前的" + name;
		} else if(endTime.equals("") && !staTime.equals("")){
			name = staTime + "日之后的" + name;
		}
		FileUtil.expExcel(response, list, heads, discard, name);
	}
	//导出订单报表
	@RequestMapping("/companySys/exportOrderReport")
	@ResponseBody
	public void exportOrderReport(HttpServletResponse response,String staTime,String endTime,Orderm order) throws Exception{
		/*if(staTime != null && !staTime.equals("") && staTime.length() <19){
			staTime = staTime + " 00:00:00";
		}
		if(endTime != null && !endTime.equals("") && endTime.length() <19){
			endTime = endTime + " 23:59:59";
		}*/
		ArrayList<Ordermview> ordermList = (ArrayList<Ordermview>)ordermMapper.selectByCompany(staTime, endTime,order);
		String[] heads = {"订单编号","种类数","下单金额","实际金额","支付方式","订单状态","下单时间","联系人","手机","地址","修改时间","客户名称"};				//表头
		String[] discard = {"ordermid","ordermcustomer","ordermcompany","ordermdetail","updor","ordermemp","orderdList","orderdCustomer"};			//要忽略的字段名
		String name = "订单统计报表";							//文件名称
		if(!staTime.equals("") && !endTime.equals("")){
			name = staTime + "日至" + endTime + "日的" + name;
		} else if(staTime.equals("") && !endTime.equals("")){
			name = endTime + "日之前的" + name;
		} else if(endTime.equals("") && !staTime.equals("")){
			name = staTime + "日之后的" + name;
		}
		FileUtil.expExcel(response, ordermList, heads, discard, name);
	}
}
