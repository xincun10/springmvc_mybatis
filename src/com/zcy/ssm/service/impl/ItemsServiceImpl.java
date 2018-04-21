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
 * ��Ʒ����
 */
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		// ͨ��ItemsMapperCustom��ѯ���ݿ�
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(int id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//�м����Ʒ��Ϣ����ҵ����
		//����ItemsCustom
		ItemsCustom itemsCustom = new ItemsCustom();
		//��items�����ݿ�����itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) 
			throws Exception {
		//���ҵ��У�飬ͨ����service�ӿڶԹؼ���������У��
		//У��id�Ƿ�Ϊ�գ����Ϊ���׳��쳣
		//������Ʒ��Ϣ,ʹ��updateByPrimaryKeyWithBLOBs
		//����id����items���������ֶΣ��������ı������ֶ�
		//ע��updateByPrimaryKeyWithBLOBsҪ����봫��id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
