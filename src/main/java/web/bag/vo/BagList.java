package web.bag.vo;

import lombok.Data;

@Data
public class BagList {
    private int blMemno; // 成員編號 (主鍵)
    private int blSchno; // 行程編號 (主鍵)
    private int blItemno; // 物品編號 (主鍵)
    private boolean blReady; // 是否準備好
    private Item item; // 物品資料
}

