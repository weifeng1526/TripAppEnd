package web.bag.vo;

import lombok.Data;

@Data
public class BagList {
    private int Memno; // 成員編號 (主鍵)
    private int Schno; // 行程編號 (主鍵)
    private int Itemno; // 物品編號 (主鍵)
    private boolean Ready; // 是否準備好
}

