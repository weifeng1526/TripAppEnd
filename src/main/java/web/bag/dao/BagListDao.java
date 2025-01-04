package web.bag.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import web.bag.vo.BagList;
import web.bag.vo.Item;

public class BagListDao {
    private DataSource dataSource;

    public BagListDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<BagList> findByMemAndSchedule(int memNo, int schNo) {
        List<BagList> bagLists = new ArrayList<>();
        String sql = "SELECT bl.bl_memno, bl.bl_schno, bl.bl_itemno, bl.bl_ready, "
                   + "i.item_name, i.item_type "
                   + "FROM bag_list bl "
                   + "JOIN item i ON bl.bl_itemno = i.item_no "
                   + "WHERE bl.bl_memno = ? AND bl.bl_schno = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memNo);
            stmt.setInt(2, schNo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BagList bag = new BagList();
                    bag.setBlMemno(rs.getInt("bl_memno"));
                    bag.setBlSchno(rs.getInt("bl_schno"));
                    bag.setBlItemno(rs.getInt("bl_itemno"));
                    bag.setBlReady(rs.getBoolean("bl_ready"));

                    Item item = new Item();
                    item.setItemNo(rs.getInt("bl_itemno"));
                    item.setItemName(rs.getString("item_name"));
                    item.setItemType(rs.getInt("item_type"));

                    bag.setItem(item);
                    bagLists.add(bag);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bagLists;
    }
}
