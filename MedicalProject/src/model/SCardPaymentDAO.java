package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import model.rec.CardPaymentVO;

public class SCardPaymentDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";
	
	PreparedStatement ps;
	Statement stmt;

	// DB연결
	public SCardPaymentDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}

	// 소모품 카드 결제
	public int regist(CardPaymentVO vo) throws Exception {
		String sql = "insert into acard values (acard_seq.nextval,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);

		ps.setString(1, vo.getCardnum());
		ps.setString(2, vo.getCardwarranty());
		ps.setString(3, vo.getCvc());
		ps.setString(4, vo.getCardcom());
		ps.setString(5, vo.getCardpwdf2());
		ps.setInt(6, vo.getScode());

		int cnt = ps.executeUpdate();
		ps.close();
		return cnt;
	}

}
