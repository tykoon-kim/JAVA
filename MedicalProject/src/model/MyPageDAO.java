package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MyPageDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";

	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	
	ArrayList list;
	
	public MyPageDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}
	// 회원 기구 구매 내역
	public ArrayList myShoppinglist(int memcode) throws Exception{
		String sql = " select s.shopcode, m.machinename, s.shopquantity, s.shopquantity*a.amsprice, to_char(mo.modate,'yy/MM/dd'), mo.modeliver"
				+ " from machine m, shoppinglist s, am_seller a, machineorder mo"
				+ " where m.machinecode = a.machinecode and a.amscode = s.amscode"
				+ " and s.mocode = mo.mocode and mo.memcode = "+memcode
				+" order by s.shopcode";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			temp.add(rs.getString(6));
			list.add(temp);
		}
		
		return list;
	}
	// 소모품 구매 내역
	public ArrayList mySomoList(int memcode) throws Exception{
		String sql = "select smd.sodcode, acc.acname, smd.sodquantity, smd.sodquantity*acs.acsprice, to_char(sm.sodate,'yy/MM/dd'), sm.sodeliver "
				+ "from somo sm, smdesc smd, accstore acs, accesories acc "
				+ "where acc.accode = acs.accode and acs.acscode = smd.acscode and "
				+ "smd.scode = sm.scode and sm.memcode = "+memcode + " order by smd.sodcode";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt(1));
			temp.add(rs.getString(2));
			temp.add(rs.getString(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			temp.add(rs.getString(6));
			list.add(temp);
		}
		
		return list;
	}
}
