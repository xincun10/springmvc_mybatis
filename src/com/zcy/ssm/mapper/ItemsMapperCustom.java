package com.zcy.ssm.mapper;

import java.util.List;

import com.zcy.ssm.po.ItemsCustom;
import com.zcy.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {

	//��ѯ��Ʒ�б�
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) 
			throws Exception;
	
}