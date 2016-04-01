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
import com.server.pojo.entity.Orderm;
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
	public String allOrder(Model model,Orderm order){
		List<Orderm> ordermList = ordermMapper.selectByCompany(order);
		model.addAttribute("allOrder", ordermList);
		model.addAttribute("order", order);
		return "forward:/companySys/orderMana.jsp";
	}
	//删除订单
	@RequestMapping("/companySys/editOrder")
	public String editOrder(Model model,Orderm order){
		Orderm orderm = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		if(orderm.getOrderdList() != null && orderm.getOrderdList().size() != 0){
			for (Orderd orderd : orderm.getOrderdList()) {
				orderdMapper.deleteByPrimaryKey(orderd.getOrderdid());
			}
		}
		ordermMapper.deleteByPrimaryKey(orderm.getOrdermid());
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
		for (String orderdid : orderdids) {
			orderdMapper.deleteByPrimaryKey(orderdid);
		}
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
	public String editOrderd(Model model,Orderd orderd,Orderm order){
		System.out.println(order.getOrdermid());
		orderdMapper.updateByPrimaryKeySelective(orderd);
		model.addAttribute("orderd", orderd);
		model.addAttribute("order", order);
		return "forward:orderDetail.action";
	}
	//打印订单
	@RequestMapping("/companySys/printOrder")
	public String printOrder(Model model,Orderm order){
		order = ordermMapper.selectByPrimaryKey(order.getOrdermid());
		Company company = companyMapper.selectByPrimaryKey(order.getOrdermcompany());
		Customer customer = customerMapper.selectByPrimaryKey(order.getOrdermcustomer());
		model.addAttribute("order", order);
		model.addAttribute("printCompany", company);
		model.addAttribute("printCustomer", customer);
		return "forward:/companySys/print.jsp";
	}
	//订单商品统计
	@RequestMapping("/companySys/orderStatistics")
	public String orderStatistics(Model model,String staTime,String endTime,String companyid,String condition) throws Exception{
		List<Orderd> list = orderdMapper.selectByTime(staTime, endTime, companyid,condition);
		model.addAttribute("orderdList", list);
		model.addAttribute("companyid", companyid);
		model.addAttribute("staTime", staTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("condition", condition);
		return "orderStatistics.jsp";
	}
	//导出报表
	@RequestMapping("/companySys/exportReport")
	@ResponseBody
	public void exportReport(HttpServletResponse response,String staTime,String endTime,String companyid,String condition) throws Exception{
		ArrayList<Orderd> list = (ArrayList<Orderd>) orderdMapper.selectByTime(staTime, endTime, companyid,condition);
		String[] heads = {"商品编码","商品名称","规格","单位","数量","商品单价","商品总价","实际金额"};				//表头
		String[] discard = {"orderdid","orderdorderm","orderddetail","orderdclass","orderdtype","orderm"};			//要忽略的字段名
		String name = "订单商品统计报表";									//文件名称
		FileUtil.expExcel(response, list, heads, discard, name);
	}
}
