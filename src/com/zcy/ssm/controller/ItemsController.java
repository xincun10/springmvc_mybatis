package com.zcy.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zcy.ssm.po.ItemsCustom;
import com.zcy.ssm.service.ItemsService;

/*
 * 商品的controller
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;

	//商品查询
	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception
	{
		//调用service查找数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribute，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
	
	//商品信息修改页面显示
//	@RequestMapping("/editItems")
//	public ModelAndView editItems() throws Exception
//	{
//		//调用service查询商品信息
//		ItemsCustom itemsCustom = itemsService.findItemsById(1);
//			
//		//返回ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		//将商品信息放到modelAndView
//		modelAndView.addObject("itemsCustom", itemsCustom);
//		//商品修改页面
//		modelAndView.setViewName("items/editItems");
//		return modelAndView;
//	}
	
	@RequestMapping(value="/editItems", method= {RequestMethod.POST,RequestMethod.GET})
	//@RequestParam里面指定request传入参数名称和形参进行绑定
	public String editItems(Model model,@RequestParam(value="id") Integer items_id) throws Exception
	{
		//调用service查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		//相当于modelAndView.addObject方法
		model.addAttribute("itemsCustom", itemsCustom);
		return "items/editItems";
	}
	
	//商品信息修改提交
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request, Integer id, ItemsCustom itemsCustom) throws Exception
	{
		//调用service更新商品信息，页面需要将商品信息传到此方法
		itemsService.updateItems(id, itemsCustom);
		//重定向
//		return "redirect:queryItems.action";
		//页面转发
		return "forward:queryItems.action";
	}
}
