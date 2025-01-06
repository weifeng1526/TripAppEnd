package web.bag.vo;

import lombok.Data;

@Data
public class Item {
	private int itemNo; // 物品編號 (自增主鍵)
	private String itemName; // 物品名稱
	private int itemType; // 物品類型
}

