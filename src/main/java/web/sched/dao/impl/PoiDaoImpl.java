package web.sched.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.sched.dao.PoiDao;
import web.sched.vo.Poi;

public class PoiDaoImpl implements PoiDao {
	private DataSource ds;

    public PoiDaoImpl() throws NamingException {
        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/tripapp");
    }
    @Override
    public List<Poi> selectAll() {
        String sql = "SELECT * FROM poi";
        List<Poi> list = new ArrayList<>();
        try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                Poi poi = new Poi();
                poi.setPoiNo(rs.getInt("poi_no"));
                poi.setPoiAdd(rs.getString("poi_add"));
                poi.setPoiName(rs.getString("poi_name"));
                poi.setPoiLng(rs.getBigDecimal("poi_lng"));
                poi.setPoiLat(rs.getBigDecimal("poi_lat"));
                poi.setPoiLab(rs.getString("poi_lab"));
                poi.setPoiPic(rs.getString("poi_pic"));
                poi.setPoiRat(rs.getDouble("poi_rat"));
                poi.setPoiHno(rs.getString("poi_hno"));
                poi.setPoiPhon(rs.getString("poi_phon"));
                poi.setPoiBs(rs.getTime("poi_bs"));
                poi.setPoiNbs(rs.getTime("poi_nbs"));
                poi.setPoiBd(rs.getString("poi_bd"));
                poi.setPoiLike(rs.getInt("poi_like"));
                list.add(poi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
