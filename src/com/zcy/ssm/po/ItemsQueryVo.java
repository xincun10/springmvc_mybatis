package com.zcy.ssm.po;
/*
 * ��Ʒ��װ����
 */
public class ItemsQueryVo {

	//��Ʒ��Ϣ
	private Items items;
	//Ϊ��ϵͳ����չ�ԣ���ԭʼ���ɵ�po������չ
	private ItemsCustom itemsCustom;
	
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
}