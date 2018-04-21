package com.zcy.ssm.service;

import java.util.List;

import com.zcy.ssm.po.ItemsCustom;
import com.zcy.ssm.po.ItemsQueryVo;

/*
 * 商品管理service
 */
public interface ItemsService {

	//商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception;
	//根据id查询商品信息
	public ItemsCustom findItemsById(int id) throws Exception;
	//修改商品信息
	/**
	 * @param id 修改商品的id
	 * @param itemsCustom 修改的商品信息
	 * @throws Exception
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) 
			throws Exception;
}
