package model;

import java.sql.*;
import java.util.ArrayList;

import model.rec.OrderVO;

public class OrderDAO {
	// DB연결
	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.84:1521:air3";
	private String id = "soft";
	private String pwd = "soft";

	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	
	OrderVO vo;
	ArrayList list;

	public OrderDAO() throws Exception {
		// TODO Auto-generated constructor stub
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pwd);
	}

	// 기구 주문번호 생성하기
	public int mNum() throws Exception {
		String sql = "select morder_seq.nextval from dual";
		int mNum = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			mNum = rs.getInt(1);
		}
		String sql2 = "insert into machineorder (mocode)values(?)";
		ps = conn.prepareStatement(sql2);
		ps.setInt(1, mNum);
		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return mNum;
	}

	// 소모품 주문번호 생성하기
	public int aNum() throws Exception {
		String sql = "select somo_seq.nextval from dual";
		int aNum = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			aNum = rs.getInt(1);
		}
		String sql2 = "insert into somo (scode)values(?)";
		ps = conn.prepareStatement(sql2);
		ps.setInt(1, aNum);
		ps.executeUpdate();
		stmt.close();
		rs.close();
		ps.close();
		return aNum;
	}

	// 기구 주문에 insert
	public void mOrder(int memcode, int mocode, int paynum, int total, int totPrice) throws Exception {
		String totalQuantity = String.valueOf(total);
		String totalPrice = String.valueOf(totPrice);
		String sql = "update machineorder set modate = sysdate, mototalprice = ?, modeliver = 'N',"
				+ "memcode = ?, paynum =?, mototal=? where mocode = " + mocode;

		ps = conn.prepareStatement(sql);
		ps.setString(1, totalPrice);
		ps.setInt(2, memcode);
		ps.setInt(3, paynum);
		ps.setString(4, totalQuantity);

		ps.executeUpdate();
		ps.close();
	}

	// 소모품 주문에 insert
	public void aOrder(int memcode, int socode, int paynum, int total, int totPrice) throws Exception {
		String totalQuantity = String.valueOf(total);
		String totalPrice = String.valueOf(totPrice);
		String sql = "update somo set sodate = sysdate, sototalprice = ?, sodeliver = 'N', memcode = ?, "
				+ "paynum=?, sototal=? where scode = " + socode;

		ps = conn.prepareStatement(sql);
		ps.setString(1, totalPrice);
		ps.setInt(2, memcode);
		ps.setInt(3, paynum);
		ps.setString(4, totalQuantity);

		ps.executeUpdate();
		ps.close();
	}

	// 기구 주문 상세에 insert
	public void mOrderDesc(OrderVO vo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into shoppinglist (shopcode, shopquantity, amscode, mocode) "
				+ "values (modesc_seq.nextval, ?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getShopquantity());
		ps.setInt(2, vo.getAmscode());
		ps.setInt(3, vo.getMocode());

		ps.executeUpdate();
		ps.close();
	}

	// 소모품 주문 상세 insert
	public void aOrderDesc(OrderVO vo) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into smdesc (sodcode, sodquantity, acscode, scode) " + "values (smdesc_seq.nextval, ?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getShopquantity());
		ps.setInt(2, vo.getAmscode());
		ps.setInt(3, vo.getMocode());

		ps.executeUpdate();
		ps.close();
	}

	// 기구 리스트 출력
	public ArrayList machineSearch() throws Exception {
		String sql = "select a.amscode, m.machinename, a.amsnation, a.ascom, a.amsprice, a.amssignificant from am_seller a, machine m where a.machinecode = m.machinecode";

		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();

			temp.add(rs.getInt("amscode"));
			temp.add(rs.getString("machinename"));
			temp.add(rs.getString("amsnation"));
			temp.add(rs.getString("ascom"));
			temp.add(rs.getString("amsprice"));
			temp.add(rs.getString("amssignificant"));

			list.add(temp);
		}
		stmt.close();
		rs.close();
		return list;
	}

	// 소모품 리스트 출력
	public ArrayList somoSearch() throws Exception {
		String sql = "select a.acscode, ac.acname, a.acsnation, a.acscom, a.acsprice, a.acsexpirationdate from accstore a, accesories ac where a.accode = ac.accode";

		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();

			temp.add(rs.getInt("acscode"));
			temp.add(rs.getString("acname"));
			temp.add(rs.getString("acsnation"));
			temp.add(rs.getString("acscom"));
			temp.add(rs.getString("acsprice"));
			temp.add(rs.getString("acsexpirationdate"));

			list.add(temp);
		}

		return list;
	}

	// 기구 검색
	public ArrayList machineFind(int col, String txt) throws Exception {
		String[] columnName = { "m.machinename", "d.dicode", "d.diname" };
		String sql = "select distinct a.amscode, m.machinename, a.amsnation, a.ascom, a.amsprice, a.amssignificant "
				+ "from am_seller a, machine m, disease d, disease_aids da where a.machinecode = m.machinecode "
				+ "and da.dicode = d.dicode and m.machinecode = da.machinecode and " + columnName[col] + " like '%"
				+ txt + "%' order by 1";

		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
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

	// 소모품 검색
	public ArrayList somoFind(String txt) throws Exception {
		String sql = "select a.acscode, ac.acname, a.acsnation, a.acscom, a.acsprice, a.acsexpirationdate "
				+ "from accstore a, accesories ac " + "where a.accode = ac.accode " + "and ac.acname like '%" + txt
				+ "%'";

		list = new ArrayList();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
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

	// 기구 상세 정보 가져오기
	public OrderVO searchMachineAll(int code, String text) throws Exception {
		OrderVO vo = new OrderVO();
		String sql = "select m.machinename, m.method, m.machinecycle, m.machinemanagement, a.amssignificant, a.amsprice, a.mimage "
				+ "from am_seller a, machine m, disease d " + "where a.machinecode = m.machinecode and amscode = "
				+ code + " and machinename = '" + text + "'";

		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setName(rs.getString(1));
			vo.setMethod(rs.getString(2));
			vo.setMachinecycle(rs.getString(3));
			vo.setManagement(rs.getString(4));
			vo.setAmssignificant(rs.getString(5));
			vo.setAmsprice(rs.getString(6));
			vo.setImage(rs.getString(7));
		}
		return vo;
	}

	// 소모품 상세 정보 가져오기
	public OrderVO searchSomoAll(int code, String text) throws Exception {
		OrderVO vo = new OrderVO();
		String sql = "select a.acname, a.acwarranty, ac.acsexpirationdate, ac.acsprice, ac.simage "
				+ "from accstore ac, accesories a " + "where ac.accode = a.accode and acscode = " + code
				+ " and acname = '" + text + "'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setAcname(rs.getString(1));
			vo.setAcwarranty(rs.getString(2));
			vo.setAcexp(rs.getString(3));
			vo.setAmsprice(rs.getString(4));
			vo.setImage(rs.getString(5));
		}
		return vo;
	}
}
