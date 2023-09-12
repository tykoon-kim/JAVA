package model;

import model.rec.LoginVO;
import model.rec.MemlogVO;
import view.LoginVIEW;
import view.MemlogVIEW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemlogDAO {
	int memcode;
	String ID, PW, memtel, memaddr, mememr, memname, memjuban;
	Object memobstacle;

	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";

	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	LoginVO vo;
	
	public MemlogDAO() throws Exception {
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

	// 아이디 중복확인
	public String checkid(String id) throws Exception {
		String douId = null;
		String sql = "select id from member where ID = '" + id + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			douId = rs.getString("ID");
		}

		rs.close();

		return douId;
	}

	// 비밀번호 중복확인
	public String checkpw(String pw) throws Exception {
		String doupw = null;
		String sql = "select pw from member where PW = '" + pw + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			doupw = rs.getString("PW");
		}

		rs.close();

		return doupw;
	}

	// 장애등급
	public String checkob(String ob) throws Exception {
		String chob = null;
		String sql = "select memobstacle from member where memobstacle = '" + ob + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			chob = rs.getString("memobstacle");
		}

		rs.close();

		return chob;
	}

	// 회원가입
	public void ijoin(MemlogVO vo) throws Exception {
		LoginVO res = null;
		String sql = "insert into member(memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle) "
				+ "values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getID());
		ps.setString(2, vo.getPW());
		ps.setString(3, vo.getMemtel());
		ps.setString(4, vo.getMemaddr());
		ps.setString(5, vo.getMememr());
		ps.setString(6, vo.getMemname());
		ps.setString(7, vo.getMemjuban());
		ps.setObject(8, vo.getMemobstacle());

		ps.executeUpdate();
		res = new LoginVO(memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle);

		ps.close();
	}

	// 회원정보 저장
	public MemlogVO info(int code) throws Exception {
		MemlogVO res = null;
		String sql = "select memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle from member where memcode ="
				+ code;

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {

			int memcode = rs.getInt("memcode");
			String ID = rs.getString("ID");
			String PW = rs.getString("PW");
			String memtel = rs.getString("memtel");
			String memaddr = rs.getString("memaddr");
			String mememr = rs.getString("mememr");
			String memname = rs.getString("memname");
			String memjuban = rs.getString("memjuban");
			String memobstacle = rs.getString("memobstacle");

			// MemberVO 타입으로 모든 정보 담아주기 - [JIN]
			res = new MemlogVO(memcode, ID, PW, memtel, memaddr, mememr, memname, memjuban, memobstacle);
		}
		stmt.close();
		rs.close();

		return res;
	}

	// 회원정보 수정
	public int updateInfo(MemlogVO vo) throws Exception {
		String sql = "update member set PW=?, memaddr=?, memtel=?, mememr=?, memname=?, memobstacle=? where memcode = "
				+ vo.getMemcode();

		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getPW());
		ps.setString(2, vo.getMemaddr());
		ps.setString(3, vo.getMemtel());
		ps.setString(4, vo.getMememr());
		ps.setString(5, vo.getMemname());
		ps.setObject(6, vo.getMemobstacle());

		int ui = ps.executeUpdate();

		ps.close();
		return ui;
	}

}