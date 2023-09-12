package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import model.rec.LoginVO;
import model.rec.MemlogVO;
import view.LoginVIEW;

public class LoginDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";
	
	LoginVIEW lv;
	LoginDAO dao;
	LoginVO vo;
	
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	public LoginDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}
	// 로그인
	public int Login(String ID, String PW) throws Exception {
		int res = 0;
		String sql = "select memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle from member where ID = ? and PW = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, ID);
		ps.setString(2, PW);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			res = rs.getInt("memcode");
		}

		rs.close();
		ps.close();

		return res;
	}
	// 회원 정보 출력
	public LoginVO info(int code) throws Exception {
		LoginVO res = null;
		String sql = "select memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle from member where memcode =" + code;

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {

			Number memcode = rs.getInt("memcode");
			String ID = rs.getString("ID");
			String PW = rs.getString("PW");
			String memtel = rs.getString("memtel");
			String memaddr = rs.getString("memaddr");
			String mememr = rs.getString("mememr");
			String memname = rs.getString("memname");
			String memjuban = rs.getString("memjuban");
			String memobstacle = rs.getString("memobstacle");

			// MemberVO 타입으로 모든 정보 담아주기 - [JIN]
			res = new LoginVO(memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle);
		}

		stmt.close();
		rs.close();

		return res;
	}

	}

