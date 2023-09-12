package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.rec.ReturnServiceVO;
import model.rec.ReturnVO;
import model.rec.ReturnnowVO;

public class ReturnDAO {
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";

	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	ReturnServiceVO resvo;
	ReturnVO revo;
	ReturnnowVO renowvo;
	
	ArrayList list, renlist;

	// DB 연결
	public ReturnDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}

	// 수거 신청
	public int reappInsert(ReturnServiceVO resvo) throws Exception {

		String sql2 = "select returns_seq.nextval from dual ";

		int num = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql2);

		if (rs.next()) {
			num = rs.getInt(1);
		}

		String sql = "insert into returnservice(recode,redate, resch, memcode, reitem) values(?,sysdate,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setString(2, resvo.getResch());
		ps.setInt(3, resvo.getMemcode());
		ps.setString(4, resvo.getReitem().toString());

		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return num;
	}

	// 수거 신청 현황
	public void renow(int ccscode, int colcode, String ccsdate, String ccsname, String ccstel) throws Exception {

		String sql = "insert into returnnow values (returns_seq.nextval,?,?,?,'N',?,?)";

		ps = conn.prepareStatement(sql);

		ps.setInt(1, ccscode);
		ps.setInt(2, colcode);
		ps.setString(3, ccsdate);
		ps.setString(4, ccsname);
		ps.setString(5, ccstel);

		ps.executeUpdate();

		ps.close();

	}

	// 지역별 수거업체
	public ArrayList RecomSearch(String rc) throws Exception {

		list = new ArrayList();

		String sql = " select colcode, colinfo, coltel, colloc, url, opentime, colprice from return where colloc = '"
				+ rc + "'";

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ReturnVO revo = new ReturnVO();
		while (rs.next()) {
			ArrayList tem = new ArrayList();
			tem.add(rs.getInt("colcode"));
			tem.add(rs.getString("colinfo"));
			tem.add(rs.getString("coltel"));
			tem.add(rs.getString("colloc"));
			tem.add(rs.getString("url"));
			tem.add(rs.getString("opentime"));
			tem.add(rs.getString("colprice"));

			list.add(tem);
		}
		rs.close();
		stmt.close();
		return list;
	}

	// 회원 수거 신청 리스트
	public ArrayList renList(int rnNum) throws Exception {

		renlist = new ArrayList();

		String sql = " select a.ccscode, a.recode, a.colcode, to_char(a.ccsdate,'yy/MM/dd'), a.ccsconfirm, a.ccsname, a.ccstel from returnnow a, member b, returnservice c where b.memcode = c.memcode and c.recode = a.recode and c.memcode  = '"
				+ rnNum + "'";

		renlist = new ArrayList();

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ReturnnowVO renvo = new ReturnnowVO();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("ccscode"));
			temp.add(rs.getString("recode"));
			temp.add(rs.getString("colcode"));
			temp.add(rs.getString(4));
			temp.add(rs.getString("ccsconfirm"));
			temp.add(rs.getString("ccsname"));
			temp.add(rs.getString("ccstel"));

			renlist.add(temp);
		}
		rs.close();
		stmt.close();
		return renlist;
	}
	// 수거업체 정보
	public ReturnVO findRelist(int reNum) throws Exception {

		String sql = " select colcode, colinfo, coltel, colloc, url, opentime, colprice from return where colcode = '"
				+ reNum + "'";

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ReturnVO revo = new ReturnVO();
		if (rs.next()) {
			revo.setColcode(rs.getInt("colcode"));
			revo.setColinfo(rs.getString("colinfo"));
			revo.setColtel(rs.getString("coltel"));
			revo.setColloc(rs.getString("colloc"));
			revo.setURL(rs.getString("url"));
			revo.setOpentime(rs.getString("opentime"));
			revo.setColprice(rs.getString("colprice"));
		}
		return revo;

	}
	// 수거 신청 현황에서 삭제
	public void rtNowCancel(int recode) throws Exception {
		String sql = "delete from returnnow where recode = " + recode;
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
	}
	// 수거 신청에서 삭제
	public void rtCancel(int recode) throws Exception {
		String sql = "delete from returnservice where recode = " + recode;
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
	}
	//취소 가능 여부
	public boolean isPossible(int recode) throws Exception {
		boolean possible = true;
		String sql = "select to_char(ccsdate) from returnnow where ccscode = " + recode;
		Date todayfm = new Date(System.currentTimeMillis());
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		String laststr = null;
		if (rs.next()) {
			laststr = rs.getString(1);
		}
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
		try {
			Date last = format.parse(laststr);
			
		
		if (last.before(todayfm)) {
			possible = false;
		} else if (last.after(todayfm)) {
			possible = true;
		} else if(last.equals(todayfm)) {
			possible=false;
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return possible;
	}
}