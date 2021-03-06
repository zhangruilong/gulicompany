package com.server.action.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dao.mapper.CityMapper;
import com.server.dao.mapper.CustomerMapper;
import com.server.dao.mapper.FeedbackMapper;
import com.server.dao.mapper.System_attachMapper;
import com.server.pojo.entity.City;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.Feedback;
import com.server.pojo.entity.System_attach;
import com.system.tools.util.CommonUtil;

/**
 * 我的
 * @author taolichao
 *
 */
@Controller
public class MineController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private FeedbackMapper feedbackMapper;
	@Autowired
	private System_attachMapper attachMapper;
	//我的
	@RequestMapping("/guliwang/minePage")
	@ResponseBody
	public System_attach minePage(System_attach attach){
		List<System_attach> attaList = attachMapper.selectByClassifyAndFid(attach);
		if(attaList != null && attaList.size() >0){
			attach = attachMapper.selectByClassifyAndFid(attach).get(0);
		}
		return attach;
	}
	//到修改页
	@RequestMapping("/guliwang/doEditCus")
	public String doEditCus(Model model,Customer customer){
		List<City> cityList = cityMapper.selectAllParent();						//查询所有地区的父类
		customer = customerMapper.selectByPrimaryKey(customer.getCustomerid());	
		model.addAttribute("customer", customer);
		model.addAttribute("cityList", cityList);
		return "myshop.jsp";
	}
	//修改客户信息
	@RequestMapping("/guliwang/editCus")
	public String editCus(HttpSession session,Customer customer){
		customerMapper.updateByPrimaryKeySelective(customer);
		session.setAttribute("customer", customer);
		return "mine.jsp";
	}
	//添加客户反馈意见
	@RequestMapping("/guliwang/feedbackof")
	public String feedbackof(Feedback feedback){
		feedback.setFeedbackid(CommonUtil.getNewId());		//id
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		feedback.setFeedbacktime(sdf.format(new Date()).toString());	//反馈时间
		
		feedbackMapper.insertSelective(feedback);
		return "mine.jsp";
	}
}
