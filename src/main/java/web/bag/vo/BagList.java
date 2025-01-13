package web.bag.vo;

import lombok.Data;

@Data
public class BagList {
    private int memNo; // 成員編號 (主鍵)
    private int schNo; // 行程編號 (主鍵)
    private int itemNo; // 物品編號 (主鍵)
    private boolean ready; // 是否準備好
	private String itemName; // 物品名稱
}

