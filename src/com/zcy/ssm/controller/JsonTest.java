package com.zcy.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcy.ssm.po.ItemsCustom;

/**
 * json交互测试
 * @author Administrator
 *
 */
@Controller
public class JsonTest {

	//请求json，返回json
	//@RequestBody将请求的商品信息的json串转成itemsCustom对象
	//@ResponseBody将itemsCustom转成json输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom)
	{
		return itemsCustom;
	}
	
	//请求key/value，返回json
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom)
	{
		return itemsCustom;
	}
}
