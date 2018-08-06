package com.zcy.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcy.ssm.mapper.ItemsMapper;
import com.zcy.ssm.mapper.ItemsMapperCustom;
import com.zcy.ssm.po.Items;
import com.zcy.ssm.po.ItemsCustom;
import com.zcy.ssm.po.ItemsQueryVo;
import com.zcy.ssm.service.ItemsService;
/*
 * 商品管理
 */
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		// 通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//中间对商品信息进行业务处理
		//返回ItemsCustom
		ItemsCustom itemsCustom = new ItemsCustom();
		//将items的内容拷贝到itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) 
			throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验id是否为空，如果为空抛出异常
		//更新商品信息,使用updateByPrimaryKeyWithBLOBs
		//根据id更新items表中所有字段，包括大文本类型字段
		//注意updateByPrimaryKeyWithBLOBs要求必须传入id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

	@Override
	public void deleteItems(Integer[] itemsId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
