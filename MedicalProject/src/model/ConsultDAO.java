package model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.rec.ConsultVO;

public class ConsultDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";

	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	
	ConsultVO vo;
	ArrayList list;

	public ConsultDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}

	// 상담 신청
	public void consultInsert(ConsultVO vo) throws Exception {
		String sql = "insert into interview values(in_seq.nextval, ?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);

		ps.setString(1, vo.getivDate());
		ps.setString(2, vo.getIvcon());
		ps.setInt(3, vo.getMemcode());
		ps.setInt(4, vo.getRepcode());

		ps.executeUpdate();

		ps.close();
	}

	// 회원별 상담내역
	public ArrayList findByIvcode(int vnum) throws Exception {

		String sql = "select ivcode, to_char(ivDate,'yy/MM/dd'), ivcon, memcode, repcode from interview" + " where memcode = " + vnum;
		list = new ArrayList();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList al = new ArrayList();

			al.add(rs.getInt("ivcode"));
			al.add(rs.getString(2));
			al.add(rs.getString("ivcon"));
			al.add(rs.getInt("memcode"));
			al.add(rs.getInt("repcode"));

			list.add(al);
		}
		rs.close();
		stmt.close();
		return list;
	}

	// 카테고리별 상담원 출력
	public ArrayList ConSearch(int repdept) throws Exception {
		String index[] = { "'A/S'", "'수거'", "'구매'", "'돌봄'" };
		String sql = "select repcode, repname, repdept from reporter" + " where repdept = " + index[repdept];
		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList tmp = new ArrayList();
			tmp.add(rs.getInt("repcode"));
			tmp.add(rs.getString("repname"));
			tmp.add(rs.getString("repdept"));

			list.add(tmp);
		}
		stmt.close();
		rs.close();
		return list;

	}

	// 회원 비상연락처 검색
	public String findMemTel(int memCode) throws Exception {
		String sql = "select mememr from member where memcode = " + memCode;

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		String emTel = "";
		if (rs.next()) {
			emTel = rs.getString(1);
		}
		return emTel;
	}

	// 상담 취소
	public void consultDelete(int ivcode, ConsultVO vo) throws Exception {
		String sql = "delete interview where ivcode = " + ivcode;
		stmt = conn.createStatement();
		stmt.executeQuery(sql);
		stmt.close();
	}

	// 취소 가능 여부
	public boolean isPossible(int recode) throws Exception {
		boolean possible = true;
		String sql = "select ivdate from interview where ivcode = " + recode;
		Date last = null;
		Date todayfm = new Date(System.currentTimeMillis());
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			last = rs.getDate(1);
		}

		if (last.before(todayfm)) {
			possible = false;
		} else if (last.after(todayfm)) {
			possible = true;
		} else if(last.equals(todayfm)) {
			possible=false;
		}

		return possible;
	}
}
