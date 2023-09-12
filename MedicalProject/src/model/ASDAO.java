package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.rec.AsVO;
import model.rec.AscurrentVO;
import model.rec.AsnowVO;

public class ASDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";

	AsVO asVO;
	AscurrentVO acvo;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	
	ArrayList list, asnlist;

	public ASDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}

	// as 신청
	public int asappInsert(AscurrentVO acvo) throws Exception {
		// 시퀀스번호 생성
		String sql2 = "select as_seq.nextval from dual";

		int num = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql2);

		if (rs.next()) {
			num = rs.getInt(1);
		}

		// 생성한 시퀀스 번호로 인서트
		String sql = "insert into afterserveice values(?, sysdate,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setString(2, acvo.getAssch());
		ps.setInt(3, acvo.getMemcode());
		ps.setString(4, acvo.getMachinename());

		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return num;
	}

	// 서비스 신청 현황 insert
	public void asnow(int afsccode, int asapcode, String ascdate, String ascsname, String ascstel) throws Exception {

		String sql = "insert into afterservicenow values(asnow_seq.nextval,?,?,?,'N',?,?)";

		ps = conn.prepareStatement(sql);

		ps.setInt(1, afsccode);
		ps.setInt(2, asapcode);
		ps.setString(3, ascdate);
		ps.setString(4, ascsname);
		ps.setString(5, ascstel);

		ps.executeUpdate();

		ps.close();
	}

	//
	public AsVO findAslist(int asNum) throws Exception {

		String sql = "select afsccode, afscname, afscloc, afsctel, url, afsctime, afscprice from afscom where afsccode = "
				+ asNum;

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		AsVO asVO = new AsVO();
		if (rs.next()) {
			asVO.setAfsccode(rs.getInt("afsccode"));
			asVO.setAfscname(rs.getString("afscname"));
			asVO.setAfscloc(rs.getString("afscloc"));
			asVO.setAfsctel(rs.getString("afsctel"));
			asVO.setURL(rs.getString("url"));
			asVO.setAfsctime(rs.getString("afsctime"));
			asVO.setAfscprice(rs.getString("afscprice"));
		}
		return asVO;

	}

	// 지역별 업체 리스트
	public ArrayList AscomSearch(String str) throws Exception {

		list = new ArrayList();

		String sql = " select afsccode , afscname, afscloc, afsctel, url, afsctime, afscprice from afscom where afscloc = '"
				+ str + "'";

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList tem = new ArrayList();
			tem.add(rs.getInt("afsccode"));
			tem.add(rs.getString("afscname"));
			tem.add(rs.getString("afscloc"));
			tem.add(rs.getString("afsctel"));
			tem.add(rs.getString("url"));
			tem.add(rs.getString("afsctime"));
			tem.add(rs.getString("afscprice"));

			list.add(tem);
		}
		rs.close();
		stmt.close();
		return list;
	}

	// 회원별 as 신청 현황
	public ArrayList asnList(int anNum) throws Exception {

		asnlist = new ArrayList();
		String sql = " select a.ascscode, a.afsccode, a.asapcode, to_char(a.ascsdate,'yy/MM/dd'), a.ascsconfirm, a.ascsname, a.ascstel from afterservicenow a, member b, afterserveice c where b.memcode = c.memcode and c.asapcode = a.asapcode and c.memcode = '"
				+ anNum + "'";
		asnlist = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("ascscode"));
			temp.add(rs.getInt("afsccode"));
			temp.add(rs.getInt("asapcode"));
			temp.add(rs.getString(4));
			temp.add(rs.getString("ascsconfirm"));
			temp.add(rs.getString("ascsname"));
			temp.add(rs.getString("ascstel"));

			asnlist.add(temp);

		}
		rs.close();
		stmt.close();
		return asnlist;

	}

	// as 신청 현황에서 삭제
	public void asNowCancel(int ascode) throws Exception {
		String sql = "delete from afterservicenow where ASCSCODE = " + ascode;
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
	}

	// as 신청에서 삭제
	public void asCancel(int asapcode) throws Exception {
		String sql = "delete from afterserveice where asapcode = " + asapcode;
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
	}

	// 취소 가능 여부
	public boolean isPossible(int ascode) throws Exception {
		boolean possible = true;
		String sql = "select to_char(ascsdate) from afterservicenow where ASCSCODE =" + ascode;

		Date todayfm = new Date(System.currentTimeMillis());

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		String lastStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");

		if (rs.next()) {
			lastStr = rs.getString(1);
		}
		try {
			Date last = format.parse(lastStr);

			if (last.before(todayfm)) {
				possible = false;
			} else if (last.after(todayfm)) {
				possible = true;
			} else if (last.equals(todayfm)) {
				possible = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return possible;
	}
}
