package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.ASDAO;
import model.rec.AsVO;
import model.rec.AscurrentVO;
import model.rec.AsnowVO;

public class AsView extends JPanel {
	JTextField tfmachinename_1, textFieldmem, tfcomcode, tfcomname, tfcomtel;
	JComboBox asloccomboBox, reloccomboBox;
	JTable astable, asnowtable;
	JButton appbtn, btnCancel;
	JDateChooser dateChooser;
	AscomtableModel tmAscom;
	appnowtableModel tmasncom;

	ASDAO asdao;
	AsVO asvo;
	AscurrentVO acvo;
	AsnowVO anvo;
	SimpleDateFormat simpleDateFormat;

	int memCode;
	String id, todayFm;
	ArrayList list, asnlist;

	// constructor method
	public AsView(int memCode, String id) {
		setBackground(new Color(255, 255, 255));
		this.memCode = memCode;
		this.id = id;

		newObject();
		addLayout(memCode, id);
		event(memCode, id);
		anvo = new AsnowVO();

		try {
			asdao = new ASDAO();
			myASList(memCode);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "A/S DB연결 실패 : " + e.getMessage());
		}
	}

	void newObject() {
		tmAscom = new AscomtableModel();
		tmasncom = new appnowtableModel();
		reloccomboBox = new JComboBox();
		asnlist = new ArrayList();
		textFieldmem = new JTextField();
		textFieldmem.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfmachinename_1 = new JTextField();
		tfmachinename_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfcomcode = new JTextField(10);
		tfcomcode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfcomname = new JTextField(10);
		tfcomname.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfcomtel = new JTextField(10);
		tfcomtel.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		appbtn = new JButton("신청 하기");
		appbtn.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		asloccomboBox = new JComboBox();
		asloccomboBox.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		astable = new JTable();
		astable.setFont(new Font("돋움", Font.PLAIN, 12));
		asnowtable = new JTable();
		asnowtable.setFont(new Font("돋움", Font.PLAIN, 12));
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("HY견고딕", Font.PLAIN, 12));
	}

	// 멤버필드 객체 생성
	void addLayout(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;

		simpleDateFormat = new SimpleDateFormat("yy/MM/dd");

		ButtonGroup bg = new ButtonGroup();
		setVisible(true);
		setLayout(null);

		JLabel appname = new JLabel("회원 번호");
		appname.setBounds(12, 56, 100, 16);
		add(appname);
		appname.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		textFieldmem.setBounds(12, 81, 100, 20);
		textFieldmem.setText(String.valueOf(memCode));
		textFieldmem.setEditable(false);
		textFieldmem.setColumns(10);
		add(textFieldmem);

		JLabel machine = new JLabel("A/S 품목");
		machine.setHorizontalAlignment(SwingConstants.LEFT);
		machine.setBounds(12, 122, 86, 16);
		add(machine);
		machine.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		tfmachinename_1.setBounds(12, 147, 100, 20);
		tfmachinename_1.setColumns(10);
		add(tfmachinename_1);

		JLabel date_2 = new JLabel("희망 날짜");
		date_2.setBounds(12, 190, 89, 16);
		add(date_2);
		date_2.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		dateChooser.setBounds(12, 214, 100, 20);
		add(dateChooser);

		JLabel date_1 = new JLabel("업체코드");
		date_1.setHorizontalAlignment(SwingConstants.LEFT);
		date_1.setBounds(12, 244, 89, 16);
		add(date_1);
		date_1.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		tfcomcode.setEditable(false);
		tfcomcode.setBounds(12, 270, 100, 20);
		add(tfcomcode);

		JLabel date_1_1 = new JLabel("업체명");
		date_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		date_1_1.setBounds(12, 300, 100, 16);
		add(date_1_1);
		date_1_1.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		JLabel date_1_2 = new JLabel("업체 연락처");
		date_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		date_1_2.setBounds(12, 356, 100, 16);
		add(date_1_2);
		date_1_2.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		tfcomname.setEditable(false);
		tfcomname.setBounds(12, 326, 100, 20);
		add(tfcomname);

		tfcomtel.setEditable(false);
		tfcomtel.setBounds(12, 382, 100, 20);
		add(tfcomtel);

		appbtn.setBounds(704, 381, 90, 22);

		add(appbtn);

		JLabel loc = new JLabel("A/S 업체 검색");
		loc.setHorizontalAlignment(SwingConstants.LEFT);
		loc.setBounds(164, 23, 132, 16);
		loc.setFont(new Font("HY중고딕", Font.BOLD, 15));
		add(loc);

		asloccomboBox.setBounds(731, 19, 63, 20);
		add(asloccomboBox);

		asloccomboBox.setModel(
				new DefaultComboBoxModel(new String[] { "서울", "부산", "김해", "인천", "수원", "김포", "광주", "포항", "파주", "부천" }));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 44, 630, 328);
		add(scrollPane);

		astable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "업체 코드", "업체명", "지역", "업체 연락처", " URL ", "영업 시간", "비용" }));
		astable.getColumnModel().getColumn(0).setResizable(false);
		astable.getColumnModel().getColumn(0).setPreferredWidth(62);
		astable.getColumnModel().getColumn(1).setResizable(false);
		astable.getColumnModel().getColumn(1).setPreferredWidth(79);
		astable.getColumnModel().getColumn(2).setResizable(false);
		astable.getColumnModel().getColumn(2).setPreferredWidth(35);
		astable.getColumnModel().getColumn(3).setResizable(false);
		astable.getColumnModel().getColumn(3).setPreferredWidth(105);
		astable.getColumnModel().getColumn(4).setResizable(false);
		astable.getColumnModel().getColumn(4).setPreferredWidth(186);
		astable.getColumnModel().getColumn(5).setResizable(false);
		astable.getColumnModel().getColumn(5).setPreferredWidth(65);
		astable.getColumnModel().getColumn(6).setResizable(false);
		astable.getColumnModel().getColumn(6).setPreferredWidth(70);
		scrollPane.setViewportView(astable);

		JLabel ASname = new JLabel("A/S 신청 현황");
		ASname.setBounds(164, 398, 123, 16);
		add(ASname);
		ASname.setFont(new Font("HY중고딕", Font.BOLD, 15));

		JScrollPane sPASlist_1 = new JScrollPane();
		sPASlist_1.setBounds(164, 424, 630, 176);
		add(sPASlist_1);

		asnowtable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "A/S 현황 코드", "A/S 업체 코드", "A/S 코드", "A/S 날짜", "A/S 여부", "A/S 업체명", "A/S 연락처" }));
		asnowtable.getColumnModel().getColumn(0).setResizable(false);
		asnowtable.getColumnModel().getColumn(0).setPreferredWidth(85);
		asnowtable.getColumnModel().getColumn(1).setResizable(false);
		asnowtable.getColumnModel().getColumn(1).setPreferredWidth(85);
		asnowtable.getColumnModel().getColumn(2).setResizable(false);
		asnowtable.getColumnModel().getColumn(2).setPreferredWidth(62);
		asnowtable.getColumnModel().getColumn(3).setResizable(false);
		asnowtable.getColumnModel().getColumn(3).setPreferredWidth(122);
		asnowtable.getColumnModel().getColumn(4).setResizable(false);
		asnowtable.getColumnModel().getColumn(4).setPreferredWidth(63);
		asnowtable.getColumnModel().getColumn(5).setResizable(false);
		asnowtable.getColumnModel().getColumn(5).setPreferredWidth(79);
		asnowtable.getColumnModel().getColumn(6).setResizable(false);
		asnowtable.getColumnModel().getColumn(6).setPreferredWidth(108);
		sPASlist_1.setViewportView(asnowtable);

		JLabel lblAs = new JLabel("A/S");
		lblAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblAs.setFont(new Font("HY견고딕", Font.PLAIN, 26));
		lblAs.setBounds(12, 10, 85, 26);
		add(lblAs);

		btnCancel.setBounds(704, 612, 90, 22);
		add(btnCancel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/Image/pexels-codioful-(formerly-gradienta)-7130537.jpg"));
		lblNewLabel.setBounds(0, 44, 144, 611);
		add(lblNewLabel);
	}

	void event(int memCode, String id) {
		// AS 신청 버튼 이벤트
		appbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// AS 희망일
				Date date = dateChooser.getDate();
				String assch = simpleDateFormat.format(date);

				// tf에서 값 가져오기
				int memcode = Integer.parseInt(textFieldmem.getText());
				String machinename = tfmachinename_1.getText();
				acvo = new AscurrentVO(assch, machinename, memcode);

				// 오늘 날짜 yy/mm/dd형태로 생성
				todayFm = simpleDateFormat.format(new Date(System.currentTimeMillis()));

				try {
					// 오늘 날짜 +3일부터 AS가능
					Date today = new Date(simpleDateFormat.parse(todayFm).getTime());

					Calendar cal = Calendar.getInstance();
					cal.setTime(today);
					cal.add(Calendar.DATE, 3);
					Date available = cal.getTime();

					int compare = date.compareTo(available);
					if (compare > 0) {
						try {
							int nownum = asdao.asappInsert(acvo);
							int asapcode = Integer.parseInt(tfcomcode.getText());
							String ascdate = simpleDateFormat.format(date);
							String ascsname = tfcomname.getText();
							String ascstel = tfcomtel.getText();
							asdao.asnow(asapcode, nownum, ascdate, ascsname, ascstel);
							myASList(memCode);
						} catch (Exception e1) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "신청 실패 :" + e1.getMessage());
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "현재 날짜 기준 3일 뒤 부터 신청 가능합니다.");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		asloccomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == asloccomboBox) {
					String asloc = (String) asloccomboBox.getSelectedItem();
					ArrayList all;
					try {
						String asloca = (String) asloccomboBox.getSelectedItem();
						selectTable(asloc);

					} catch (Exception eq) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "업체검색 실패 :" + eq.getMessage());
					}
					// tfmachinename.setText(asvo.get

				}

			}
		});
		astable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = astable.getSelectedRow();
				int asNum = (Integer) astable.getValueAt(row, col);
				try {
					asvo = asdao.findAslist(asNum);

				} catch (Exception c) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, " 검색 실패 :" + c.getMessage());
				}
				tfcomname.setText(asvo.getAfscname());
				tfcomtel.setText(asvo.getAfsctel());
				tfcomcode.setText(String.valueOf(asvo.getAfsccode()));
				System.out.println("업체 검색 성공");
			}

		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = asnowtable.getSelectedRow();
				int ascode = Integer.parseInt(String.valueOf(asnowtable.getValueAt(row, 0)));
				int asapcode = Integer.parseInt(String.valueOf(asnowtable.getValueAt(row, 2)));
				try {
					if (asdao.isPossible(ascode) == false) {
						JOptionPane.showMessageDialog(null, "이미 as가 완료됐습니다.");
					} else if (asdao.isPossible(ascode) == true) {
						asdao.asNowCancel(ascode);
						asdao.asCancel(asapcode);
						myASList(memCode);
						JOptionPane.showMessageDialog(null, "취소됐습니다.");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		});
	}

	void selectTable(String str) {

		try {
			list = new ArrayList();
			list = asdao.AscomSearch(str);
			tmAscom.data = list;
			astable.setModel(tmAscom);
			astable.getColumnModel().getColumn(0).setResizable(false);
			astable.getColumnModel().getColumn(0).setPreferredWidth(62);
			astable.getColumnModel().getColumn(1).setResizable(false);
			astable.getColumnModel().getColumn(1).setPreferredWidth(79);
			astable.getColumnModel().getColumn(2).setResizable(false);
			astable.getColumnModel().getColumn(2).setPreferredWidth(35);
			astable.getColumnModel().getColumn(3).setResizable(false);
			astable.getColumnModel().getColumn(3).setPreferredWidth(105);
			astable.getColumnModel().getColumn(4).setResizable(false);
			astable.getColumnModel().getColumn(4).setPreferredWidth(186);
			astable.getColumnModel().getColumn(5).setResizable(false);
			astable.getColumnModel().getColumn(5).setPreferredWidth(65);
			astable.getColumnModel().getColumn(6).setResizable(false);
			astable.getColumnModel().getColumn(6).setPreferredWidth(70);
			tmAscom.fireTableDataChanged();

		} catch (Exception eq) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "업체검색 실패 :" + eq.getMessage());
		}

	}

	void myASList(int memCode) {
		try {
			asnlist = asdao.asnList(memCode);
			asnowtable.setModel(tmasncom);
			tmasncom.data = asnlist;
			asnowtable.getColumnModel().getColumn(0).setResizable(false);
			asnowtable.getColumnModel().getColumn(0).setPreferredWidth(85);
			asnowtable.getColumnModel().getColumn(1).setResizable(false);
			asnowtable.getColumnModel().getColumn(1).setPreferredWidth(85);
			asnowtable.getColumnModel().getColumn(2).setResizable(false);
			asnowtable.getColumnModel().getColumn(2).setPreferredWidth(62);
			asnowtable.getColumnModel().getColumn(3).setResizable(false);
			asnowtable.getColumnModel().getColumn(3).setPreferredWidth(140);
			asnowtable.getColumnModel().getColumn(4).setResizable(false);
			asnowtable.getColumnModel().getColumn(4).setPreferredWidth(63);
			asnowtable.getColumnModel().getColumn(5).setResizable(false);
			asnowtable.getColumnModel().getColumn(5).setPreferredWidth(79);
			asnowtable.getColumnModel().getColumn(6).setResizable(false);
			asnowtable.getColumnModel().getColumn(6).setPreferredWidth(105);
			tmasncom.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	class AscomtableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "업체 코드", "업체명", "지역", "업체 연락처", " URL ", "영업 시간", "비용" };

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

	class appnowtableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "A/S 현황 코드", "A/S 업체 코드", "A/S 코드", "A/S 날짜", "A/S 여부", "A/S 업체명", "A/S 연락처" };

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
