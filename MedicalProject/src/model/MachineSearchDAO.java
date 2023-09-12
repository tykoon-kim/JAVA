package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.OrderVO;

public class MachineSearchDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";
	ArrayList list;
	
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	OrderVO vo;
	
	// DB연결
	public MachineSearchDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			System.out.println("디비연결실패");
			e.printStackTrace();
		}
	}

	// 기구검색하기
	public ArrayList searchMachine(int combo, String text) throws Exception {
		String[] searchCol = { "d.dicode", "d.diname" };
		String sql = "select d.dicode, d.diname, m.machinecode, m.machinename, m.machinecycle, m.machinemanagement "
				+ "from machine m, disease_aids da, disease d "
				+ "where m.machinecode = da.machinecode and da.dicode = d.dicode " + "and " + searchCol[combo]
				+ " like '%" + text + "%'";

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();

			temp.add(rs.getString(1));
			temp.add(rs.getString(2));
			temp.add(rs.getInt(3));
			temp.add(rs.getString(4));
			temp.add(rs.getString(5));
			temp.add(rs.getString(6));

			list.add(temp);
		}

		return list;
	}
	
}
