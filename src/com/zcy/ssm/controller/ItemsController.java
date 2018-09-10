package com.zcy.ssm.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zcy.ssm.controller.validation.ValidGroup1;
import com.zcy.ssm.exception.CustomException;
import com.zcy.ssm.po.ItemsCustom;
import com.zcy.ssm.po.ItemsQueryVo;
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
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo)
			throws Exception
	{
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	//商品查询
//	@RequestMapping("/queryItems")
//	public ModelAndView queryItems() throws Exception
//	{
//		//调用service查找数据库，查询商品列表
//		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
//		//返回ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		//相当于request的setAttribute，在jsp页面中通过itemsList取数据
//		modelAndView.addObject("itemsList", itemsList);
//		
//		modelAndView.setViewName("items/itemsList");
//		return modelAndView;
//	}
	
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
		//判断商品是否为空，根据id没有查询到商品，抛出异常，提示用户商品信息不存在
//		if(itemsCustom == null)
//		{
//			throw new CustomException("修改的商品信息不存在！");
//		}
		//相当于modelAndView.addObject方法
		model.addAttribute("itemsCustom", itemsCustom);
		return "items/editItems";
	}
	
	//商品信息修改提交
	//在需要校验的pojo前面添加@Validated，
	//在需要校验的pojo后边添加BindingResult bindingResult接收校验出错信息
	//注意：@Validated和BindingResult bindingResult是配对出现，顺序先后固定
	//value={ValidGroup1.class}指定使用ValidGroup1分组的校验
	//@ModelAttribute指定pojo回显到页面在request中的key
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(
			Model model, HttpServletRequest request, Integer id, 
			@ModelAttribute("itemsCustom")
			@Validated(value={ValidGroup1.class}) ItemsCustom itemsCustom, 
			BindingResult bindingResult,
			MultipartFile items_pic//接收商品图片
			) throws Exception
	{
		//获取校验错误信息
		if(bindingResult.hasErrors())
		{
			//输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError objectError:allErrors)
			{
				//输出错误信息
				System.out.println(objectError.getDefaultMessage());
			}
			//将错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			//出错重新到商品修改页面
			return "items/editItems";
		}
		//获取图片原始名称
		String originalFilename = items_pic.getOriginalFilename();
		//上传图片
		if(items_pic!=null && originalFilename!=null && originalFilename.length()>0)
		{
			//存储图片的物理路径
			String pic_path = "I:\\pictures\\老田\\";
			
			//新的图片名称
			String newFilename = UUID.randomUUID() + 
					originalFilename.substring(originalFilename.lastIndexOf('.'));
			//新图片
			File newPic = new File(pic_path+newFilename);
			//将内存中的数据写入磁盘
			items_pic.transferTo(newPic);
			//将新图片名称写到itemsCustom中
			itemsCustom.setPic(newFilename);
		}
		//调用service更新商品信息，页面需要将商品信息传到此方法
		itemsService.updateItems(id, itemsCustom);
		//重定向
//		return "redirect:queryItems.action";
		//页面转发
		return "forward:queryItems.action";
	}
	
	//批量删除商品信息
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] itemsId) throws Exception
	{
		//调用service批量删除商品
		itemsService.deleteItems(itemsId);
		return "success";
	}
	
	//批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo)
			throws Exception
	{
		//调用service查找数据库，查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/editItemsQuery");
		return modelAndView;
	}
	
	//批量修改商品提交
	//通过ItemsQueryVo接受批量提交的商品信息，
	//将商品信息存储到itemsQueryVo中itemsList属性中
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception
	{
		return "success";
	}
	
	//使用RESTful格式查询商品信息
	///itemsView/{id}里面的{id}表示将这个位置的参数传到@PathVariable指定的名称中
	//例如itemsView/{id}/{type},参数可以写成(@PathVariable("id") Integer id,
	//@PathVariable("type") String abc)
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id)
			throws Exception
	{
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
}
