//package web.bag.dao.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import web.bag.dao.BagListDao;
//import web.bag.vo.BagList;
//public class BagDaoImpl implements BagListDao {
//    private DataSource ds;  // 資料來源，透過容器或自定義配置進行設置
//
//    public BagDaoImpl(DataSource ds) {
//        this.ds = ds;  // 設定資料源
//    }
//
//    @Override
//    public List<BagList> findByMemAndSchedule(int memNo, int schNo) {
//        List<BagList> bagLists = new ArrayList<>();
//        String sql = "SELECT * FROM bag_list  WHERE bl_memno = ? AND bl_schno = ?";
//
//        try (Connection conn = ds.getConnection(); 
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, memNo);
//            pstmt.setInt(2, schNo);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    BagList bag = new BagList();
//                    bag.setBlMemno(rs.getInt("bl_memno"));
//                    bag.setBlSchno(rs.getInt("bl_schno"));
//                    bag.setBlItemno(rs.getInt("bl_itemno"));
//                    bag.setBlReady(rs.getBoolean("bl_ready"));
//                    bagLists.add(bag);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bagLists;
//    }
//}