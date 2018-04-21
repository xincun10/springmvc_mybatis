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
 * ��Ʒ��controller
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;

	//��Ʒ��ѯ
	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception
	{
		//����service�������ݿ⣬��ѯ��Ʒ�б�
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//����ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//�൱��request��setAttribute����jspҳ����ͨ��itemsListȡ����
		modelAndView.addObject("itemsList", itemsList);
		
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
	
	//��Ʒ��Ϣ�޸�ҳ����ʾ
//	@RequestMapping("/editItems")
//	public ModelAndView editItems() throws Exception
//	{
//		//����service��ѯ��Ʒ��Ϣ
//		ItemsCustom itemsCustom = itemsService.findItemsById(1);
//			
//		//����ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		//����Ʒ��Ϣ�ŵ�modelAndView
//		modelAndView.addObject("itemsCustom", itemsCustom);
//		//��Ʒ�޸�ҳ��
//		modelAndView.setViewName("items/editItems");
//		return modelAndView;
//	}
	
	@RequestMapping(value="/editItems", method= {RequestMethod.POST,RequestMethod.GET})
	//@RequestParam����ָ��request����������ƺ��βν��а�
	public String editItems(Model model,@RequestParam(value="id") Integer items_id) throws Exception
	{
		//����service��ѯ��Ʒ��Ϣ
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		//�൱��modelAndView.addObject����
		model.addAttribute("itemsCustom", itemsCustom);
		return "items/editItems";
	}
	
	//��Ʒ��Ϣ�޸��ύ
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request, Integer id, ItemsCustom itemsCustom) throws Exception
	{
		//����service������Ʒ��Ϣ��ҳ����Ҫ����Ʒ��Ϣ�����˷���
		itemsService.updateItems(id, itemsCustom);
		//�ض���
//		return "redirect:queryItems.action";
		//ҳ��ת��
		return "forward:queryItems.action";
	}
}
