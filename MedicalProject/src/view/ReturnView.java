package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.ReturnDAO;
import model.rec.ReturnServiceVO;
import model.rec.ReturnVO;
import model.rec.ReturnnowVO;
import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ReturnView extends JPanel {

	JComboBox reloccomboBox;
	JPanel panel;
	JLabel lbMemCode, lbReturnName, lbDate, lbComCode, lbComName, lbComTel, lbComSearch, lbReturnNow, lbBackground,
			lbTitle;
	JTextField tfRecode, memid, tfcolcode, tfcolname, tfcoltel;
	JTable retable, renowtable_1;
	JButton appbtn_1, btnCancel;
	JScrollPane scrollPane, scrollPane_1;
	JDateChooser dateChooser;
	SimpleDateFormat simpleFormat;
	RecomtableModel tmRecom;
	apprenowtableModel tmarncom;

	ReturnDAO redao;
	ReturnVO revo;
	ReturnServiceVO resvo;
	ReturnnowVO renowvo;

	int memCode;
	String id, todayFm;
	ArrayList list, renlist;

	public ReturnView(int memCode, String id) {
		setBackground(new Color(255, 255, 255));
		this.memCode = memCode;
		this.id = id;

		newObject();
		addLayout(memCode, id);
		event(memCode, id);

		try {
			redao = new ReturnDAO();
			myReturnList(memCode);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "수거 DB연결 실패 : " + e.getMessage());
		}
	}

	void newObject() {
		panel = new JPanel();
		
		lbMemCode = new JLabel("회원 번호");
		lbMemCode.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		memid = new JTextField();
		memid.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbReturnName = new JLabel("수거 품목");
		lbReturnName.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		tfRecode = new JTextField();
		tfRecode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbDate = new JLabel("희망 날짜");
		lbDate.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("HY중고딕", Font.PLAIN, 12));
		lbComCode = new JLabel("업체 코드");
		lbComCode.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		tfcolcode = new JTextField(10);
		tfcolcode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbComName = new JLabel("업체명");
		lbComName.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		tfcolname = new JTextField(10);
		tfcolname.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfcoltel = new JTextField(10);
		tfcoltel.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbComTel = new JLabel("업체 연락처");
		lbComTel.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbComSearch = new JLabel("수거 업체 검색");
		lbComSearch.setFont(new Font("HY중고딕", Font.BOLD, 15));
		
		retable = new JTable();
		retable.setFont(new Font("돋움", Font.PLAIN, 12));
		appbtn_1 = new JButton("신청 하기");
		appbtn_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		renowtable_1 = new JTable();
		renowtable_1.setFont(new Font("돋움", Font.PLAIN, 12));
		lbReturnNow = new JLabel("수거 신청 현황");
		lbReturnNow.setFont(new Font("HY중고딕", Font.BOLD, 15));
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon("src/Image/144.png"));
		lbTitle = new JLabel("수거");
		lbTitle.setFont(new Font("HY중고딕", Font.BOLD, 26));
	}

	void addLayout(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;

		renlist = new ArrayList();
		simpleFormat = new SimpleDateFormat("yy/MM/dd");
		setVisible(true);
		setLayout(null);

		lbMemCode.setBounds(12, 56, 100, 16);
		add(lbMemCode);

		memid.setBounds(12, 81, 100, 20);
		add(memid);

		memid.setText(String.valueOf(memCode));
		memid.setEditable(false);
		memid.setColumns(10);

		lbReturnName.setBounds(12, 122, 86, 16);
		add(lbReturnName);

		tfRecode.setBounds(12, 147, 100, 20);
		add(tfRecode);
		tfRecode.setColumns(10);

		lbDate.setBounds(12, 190, 89, 16);
		add(lbDate);

		dateChooser.setBounds(12, 214, 100, 20);
		add(dateChooser);

		lbComCode.setBounds(12, 244, 89, 16);
		add(lbComCode);

		tfcolcode.setEditable(false);
		tfcolcode.setBounds(12, 270, 100, 20);
		add(tfcolcode);

		lbComName.setBounds(12, 300, 89, 16);
		add(lbComName);

		tfcolname.setEditable(false);
		tfcolname.setBounds(12, 326, 100, 20);
		add(tfcolname);

		tfcoltel.setEditable(false);
		tfcoltel.setBounds(12, 382, 100, 20);
		add(tfcoltel);

		lbComTel.setBounds(12, 356, 100, 16);
		add(lbComTel);

		lbComSearch.setBounds(164, 23, 132, 16);
		add(lbComSearch);

		reloccomboBox = new JComboBox();
		reloccomboBox.setBounds(731, 19, 63, 20);
		add(reloccomboBox);
		reloccomboBox.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		reloccomboBox.setModel(new DefaultComboBoxModel(new String[] { "서울", "대전", "부산", "대구", "경기도" }));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 44, 630, 328);
		add(scrollPane);

		tmRecom = new RecomtableModel();
		tmarncom = new apprenowtableModel();
		
		retable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "업체 코드", "업체명", "업체 연락처", "지역", " URL ", "영업 시간", "비용" }));
		retable.getColumnModel().getColumn(0).setResizable(false);
		retable.getColumnModel().getColumn(0).setPreferredWidth(62);
		retable.getColumnModel().getColumn(1).setResizable(false);
		retable.getColumnModel().getColumn(1).setPreferredWidth(79);
		retable.getColumnModel().getColumn(2).setResizable(false);
		retable.getColumnModel().getColumn(2).setPreferredWidth(50);
		retable.getColumnModel().getColumn(3).setResizable(false);
		retable.getColumnModel().getColumn(3).setPreferredWidth(100);
		retable.getColumnModel().getColumn(4).setResizable(false);
		retable.getColumnModel().getColumn(4).setPreferredWidth(175);
		retable.getColumnModel().getColumn(5).setResizable(false);
		retable.getColumnModel().getColumn(5).setPreferredWidth(80);
		retable.getColumnModel().getColumn(6).setResizable(false);
		retable.getColumnModel().getColumn(6).setPreferredWidth(70);
		scrollPane.setViewportView(retable);

		appbtn_1.setBounds(704, 381, 90, 22);
		add(appbtn_1);

		

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(164, 424, 630, 176);
		add(scrollPane_1);

		renowtable_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "수거 현황 코드", "수거 코드", "수거 업체 코드", "수거 날짜", " 수거 여부 ", "수거 업체명", "수거 연락처" }));
		renowtable_1.getColumnModel().getColumn(0).setResizable(false);
		renowtable_1.getColumnModel().getColumn(0).setPreferredWidth(85);
		renowtable_1.getColumnModel().getColumn(1).setResizable(false);
		renowtable_1.getColumnModel().getColumn(1).setPreferredWidth(85);
		renowtable_1.getColumnModel().getColumn(2).setResizable(false);
		renowtable_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		renowtable_1.getColumnModel().getColumn(3).setResizable(false);
		renowtable_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		renowtable_1.getColumnModel().getColumn(4).setResizable(false);
		renowtable_1.getColumnModel().getColumn(4).setPreferredWidth(63);
		renowtable_1.getColumnModel().getColumn(5).setResizable(false);
		renowtable_1.getColumnModel().getColumn(5).setPreferredWidth(79);
		renowtable_1.getColumnModel().getColumn(6).setResizable(false);
		renowtable_1.getColumnModel().getColumn(6).setPreferredWidth(108);
		scrollPane_1.setViewportView(renowtable_1);

		lbReturnNow.setBounds(164, 398, 123, 16);
		add(lbReturnNow);

		btnCancel.setBounds(704, 612, 90, 22);
		add(btnCancel);

		lbBackground.setBounds(0, 44, 144, 611);
		add(lbBackground);

		lbTitle.setBounds(12, 10, 85, 26);
		add(lbTitle);

		
	}
	void selectrenTable() {
		try {
			renlist = redao.renList(memCode);
			renowtable_1.setModel(tmarncom);
			tmarncom.data = renlist;
			renowtable_1.getColumnModel().getColumn(0).setResizable(false);
			renowtable_1.getColumnModel().getColumn(0).setPreferredWidth(85);
			renowtable_1.getColumnModel().getColumn(1).setResizable(false);
			renowtable_1.getColumnModel().getColumn(1).setPreferredWidth(85);
			renowtable_1.getColumnModel().getColumn(2).setResizable(false);
			renowtable_1.getColumnModel().getColumn(2).setPreferredWidth(100);
			renowtable_1.getColumnModel().getColumn(3).setResizable(false);
			renowtable_1.getColumnModel().getColumn(3).setPreferredWidth(70);
			renowtable_1.getColumnModel().getColumn(4).setResizable(false);
			renowtable_1.getColumnModel().getColumn(4).setPreferredWidth(63);
			renowtable_1.getColumnModel().getColumn(5).setResizable(false);
			renowtable_1.getColumnModel().getColumn(5).setPreferredWidth(79);
			renowtable_1.getColumnModel().getColumn(6).setResizable(false);
			renowtable_1.getColumnModel().getColumn(6).setPreferredWidth(108);
			tmarncom.fireTableDataChanged();
		} catch (Exception w) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "신청 현황 검색 실패 :" + w.getMessage());
		}
	}

	void event(int memCode, String id) {
		// 수거 신청
				appbtn_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 오늘 날짜
						todayFm = simpleFormat.format(new Date(System.currentTimeMillis()));

						// 희망 날짜
						Date date = dateChooser.getDate();
						String resch = simpleFormat.format(date);

						// tf값 가져오기
						int memcode = Integer.parseInt(memid.getText());
						String reitem = tfRecode.getText();
						resvo = new ReturnServiceVO(reitem, resch, memcode);
						try {
							// 오늘 날짜 +3부터 수거 신청 가능
							Date today = new Date(simpleFormat.parse(todayFm).getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(today);
							cal.add(Calendar.DATE, 3);// 3일 추가
							Date available = cal.getTime();

							int compare = date.compareTo(available);
							if (compare > 0) {
								try {

									int renow = redao.reappInsert(resvo);
									int recode = Integer.parseInt(tfcolcode.getText());
									String ccsdate = simpleFormat.format(date);
									String ccsname = tfcolname.getText();
									String ccstel = tfcoltel.getText();
									redao.renow(renow, recode, ccsdate, ccsname, ccstel);

									selectrenTable();
									tfRecode.setText("");
									tfcolcode.setText("");
									tfcolname.setText("");
									tfcoltel.setText("");
								} catch (Exception e1) {
									// TODO: handle exception
									JOptionPane.showMessageDialog(null, "신청 실패 :" + e1.getMessage());
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null, "🙏오늘 기준 3일 이후부터 접수 가능하세요🙏");
							}

						} catch (Exception e2) {
							// TODO: handle exception
						}

					}
				});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = renowtable_1.getSelectedRow();
				int recode = Integer.parseInt(String.valueOf(renowtable_1.getValueAt(row, 1)));
				try {
					if (redao.isPossible(recode) == false) {
						JOptionPane.showMessageDialog(null, "이미 수거가 완료됐습니다.");
					} else {
						redao.rtNowCancel(recode);
						redao.rtCancel(recode);
						selectrenTable();
						JOptionPane.showMessageDialog(null, "취소됐습니다.");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		reloccomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == reloccomboBox) {
					String reloc = (String) reloccomboBox.getSelectedItem();
					ArrayList all;
					try {
						String reloca = (String) reloccomboBox.getSelectedItem();
						selectTable(reloc);
					} catch (Exception t) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "업체검색 실패 :" + t.getMessage());
					}
				}
			}
		});

		retable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = retable.getSelectedRow();
				int reNum = (Integer) retable.getValueAt(row, col);
				try {
					revo = redao.findRelist(reNum);
				} catch (Exception q) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "업체검색 실패 :" + q.getMessage());
				}
				tfcolname.setText(revo.getColinfo());
				tfcoltel.setText(revo.getColtel());
				tfcolcode.setText(String.valueOf(revo.getColcode()));
			}
		});
	}

	void selectTable(String reloc) {
		try {
			list = new ArrayList();
			list = redao.RecomSearch(reloc);
			retable.setModel(tmRecom);
			tmRecom.data = list;
			retable.getColumnModel().getColumn(0).setResizable(false);
			retable.getColumnModel().getColumn(0).setPreferredWidth(62);
			retable.getColumnModel().getColumn(1).setResizable(false);
			retable.getColumnModel().getColumn(1).setPreferredWidth(79);
			retable.getColumnModel().getColumn(2).setResizable(false);
			retable.getColumnModel().getColumn(2).setPreferredWidth(50);
			retable.getColumnModel().getColumn(3).setResizable(false);
			retable.getColumnModel().getColumn(3).setPreferredWidth(100);
			retable.getColumnModel().getColumn(4).setResizable(false);
			retable.getColumnModel().getColumn(4).setPreferredWidth(175);
			retable.getColumnModel().getColumn(5).setResizable(false);
			retable.getColumnModel().getColumn(5).setPreferredWidth(80);
			retable.getColumnModel().getColumn(6).setResizable(false);
			retable.getColumnModel().getColumn(6).setPreferredWidth(70);
			tmRecom.fireTableDataChanged();
		} catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "업체검색 실패 :" + e1.getMessage());

		}

	}
	void myReturnList(int memCode) {
		try {
			renlist = new ArrayList();
			renlist = redao.renList(memCode);
			tmarncom.data = renlist;
			renowtable_1.setModel(tmarncom);
			renowtable_1.getColumnModel().getColumn(0).setResizable(false);
			renowtable_1.getColumnModel().getColumn(0).setPreferredWidth(85);
			renowtable_1.getColumnModel().getColumn(1).setResizable(false);
			renowtable_1.getColumnModel().getColumn(1).setPreferredWidth(85);
			renowtable_1.getColumnModel().getColumn(2).setResizable(false);
			renowtable_1.getColumnModel().getColumn(2).setPreferredWidth(100);
			renowtable_1.getColumnModel().getColumn(3).setResizable(false);
			renowtable_1.getColumnModel().getColumn(3).setPreferredWidth(100);
			renowtable_1.getColumnModel().getColumn(4).setResizable(false);
			renowtable_1.getColumnModel().getColumn(4).setPreferredWidth(63);
			renowtable_1.getColumnModel().getColumn(5).setResizable(false);
			renowtable_1.getColumnModel().getColumn(5).setPreferredWidth(79);
			renowtable_1.getColumnModel().getColumn(6).setResizable(false);
			renowtable_1.getColumnModel().getColumn(6).setPreferredWidth(108);
			tmarncom.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "수거 신청 리스트 출력 실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}
	class RecomtableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "업체 코드", "업체명", "업체 연락처", "지역", " URL ", "영업 시간", "비용" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList tem = (ArrayList) data.get(row);
			return tem.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];

		}
	}

	class apprenowtableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "수거 현황 코드", "수거 코드", "수거 업체 코드", "수거 날짜", " 수거 여부 ", "수거 업체명", "수거 연락처" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];

		}

	}
}
