package core.util;

import com.zaxxer.hikari.HikariDataSource;
import static core.util.JdbcConstant.PASSWORD;
import static core.util.JdbcConstant.URL;
import static core.util.JdbcConstant.USER;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class DsConstant {	
	private HikariDataSource ds;
	//Jdbc引擎連接資料庫
	public HikariDataSource DsConfig() {
		//MySQL引擎，要有jar檔
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ds = new HikariDataSource();
		ds.setJdbcUrl(URL);
		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
		// 最小空閒連線數
		ds.setMinimumIdle(5);
		 // 最大連線數
		ds.setMaximumPoolSize(10);
		 // 啟⽤預編譯敘述快取
		ds.addDataSourceProperty("cachePrepStmts", true);
		 // 設定最多可保存的預編譯敘述數量
		ds.addDataSourceProperty("prepStmtCacheSize", 250);
		 // 設定預編譯敘述⻑度上限
		ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		return ds;
	}
}
