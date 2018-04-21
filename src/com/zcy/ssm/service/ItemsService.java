package com.zcy.ssm.service;

import java.util.List;

import com.zcy.ssm.po.ItemsCustom;
import com.zcy.ssm.po.ItemsQueryVo;

/*
 * ��Ʒ����service
 */
public interface ItemsService {

	//��Ʒ��ѯ�б�
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception;
	//����id��ѯ��Ʒ��Ϣ
	public ItemsCustom findItemsById(int id) throws Exception;
	//�޸���Ʒ��Ϣ
	/**
	 * @param id �޸���Ʒ��id
	 * @param itemsCustom �޸ĵ���Ʒ��Ϣ
	 * @throws Exception
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) 
			throws Exception;
}
