package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.CareDAO;
import model.rec.CareVO;
import view.CareView.CareTableModel;
import view.CareView.CaregiverTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class CareView extends JPanel implements ActionListener {
	JTextField tfCareGiver, tfMemCode;
	JLabel lblcgLabel, lblcssdateLabel_2, lblcsldateLabel, lblcgcodeLabel, lblmemcodeLabel_1, lbCareLocation, lblNewLabel;
	JButton btnOk, btnCancel;
	JComboBox comboBox;
	JTable cgtable, apptable;
	JScrollPane SPdesc, scrollPane, scrollPane_1;
	JDateChooser startDate, endDate;
	
	CareTableModel tmCare;
	CaregiverTableModel tmgiver;
	CareVO vo;
	CareDAO dao;
	
	ArrayList list;
	int memCode;
	String id, todayFm;
	SimpleDateFormat simpleFormat;


	public CareView(int memCode, String id) {
		setBackground(new Color(255, 255, 255));
		this.memCode = memCode;
		this.id = id;

		newObject();
		addLayout();
		eventProc(memCode, id);
		
		// 데이터베이스 연결
		try {
			dao = new CareDAO();
			System.out.println("연결완료");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "연결못했어요 : " + e.getMessage());
		}
		System.out.println("본인 예약 리스트"+memCode);
		showBookList(memCode);
	}
	
	// 본인 예약 리스트 띄우기
	void showBookList(int memCode) {
		try {
			list = dao.findByCscode(memCode);
			tmgiver.data = list;
			apptable.setModel(tmgiver);
			tmgiver.fireTableDataChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 객체 생성
	void newObject() {
		btnOk = new JButton("신청");
		btnCancel = new JButton("취소");
		comboBox = new JComboBox();
		tfCareGiver = new JTextField();
		tfMemCode = new JTextField();
		lblcgLabel = new JLabel("간병인");
		lblcssdateLabel_2 = new JLabel("시작날짜");
		lblcsldateLabel = new JLabel("종료날짜");
		lblcgcodeLabel = new JLabel("간병인코드");
		tmCare = new CareTableModel();
		tmgiver = new CaregiverTableModel();
		scrollPane = new JScrollPane();
		cgtable = new JTable();
		scrollPane_1 = new JScrollPane();
		apptable = new JTable();
		lblmemcodeLabel_1 = new JLabel("회원코드");
		startDate = new JDateChooser();
		endDate = new JDateChooser();
		simpleFormat = new SimpleDateFormat("yy/MM/dd");
	}

	void addLayout() {
		setLayout(null);
		
		btnOk.setFont(new Font("HY견고딕", Font.PLAIN, 13));
		btnOk.setBounds(590, 604, 90, 22);
		add(btnOk);
		
		btnCancel.setFont(new Font("HY견고딕", Font.PLAIN, 13));
		btnCancel.setBounds(700, 604, 90, 22);
		add(btnCancel);
		
		comboBox.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		comboBox.setBounds(12, 147, 63, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "서울", "대전", "부산", "경상도", "전라도" }));
		add(comboBox);
		
		tfCareGiver.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfCareGiver.setEditable(false);
		tfCareGiver.setColumns(10);
		tfCareGiver.setBounds(12, 326, 100, 20);
		add(tfCareGiver);
		
		tfMemCode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfMemCode.setEditable(false);
		tfMemCode.setText(String.valueOf(memCode));
		tfMemCode.setBounds(12, 81, 100, 20);
		tfMemCode.setColumns(10);
		add(tfMemCode);
		
		lblcgLabel.setFont(new Font("HY중고딕", Font.BOLD, 26));
		lblcgLabel.setBounds(12, 10, 85, 26);
		add(lblcgLabel);
		
		lblcssdateLabel_2.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblcssdateLabel_2.setBounds(12, 190, 85, 15);
		add(lblcssdateLabel_2);
		
		lblcsldateLabel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblcsldateLabel.setBounds(12, 245, 100, 15);
		add(lblcsldateLabel);
		
		lblcgcodeLabel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblcgcodeLabel.setBounds(12, 300, 100, 15);
		add(lblcgcodeLabel);
		
		scrollPane.setBounds(164, 42, 630, 280);
		add(scrollPane);
		
		cgtable.setFont(new Font("돋움", Font.PLAIN, 12));
		cgtable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "간병인코드", "간병인명", "소속회사", "연락처", "급여코드", "지역"}));
		scrollPane.setViewportView(cgtable);
		
		scrollPane_1.setBounds(164, 348, 630, 246);
		add(scrollPane_1);
		apptable.setFont(new Font("돋움", Font.PLAIN, 12));
		apptable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "돌봄서비스코드", "시작일", "종료일", "회원코드", "간병인코드", "신청날짜" }));
		scrollPane_1.setViewportView(apptable);
		
		lblmemcodeLabel_1.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lblmemcodeLabel_1.setBounds(12, 56, 100, 15);
		add(lblmemcodeLabel_1);
		
		startDate.setBounds(12, 214, 120, 20);
		add(startDate);
		endDate.setBounds(12, 270, 120, 20);
		add(endDate);
		
		lbCareLocation = new JLabel("지역");
		lbCareLocation.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbCareLocation.setBounds(12, 122, 86, 15);
		add(lbCareLocation);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/Image/image437.png"));
		lblNewLabel.setBounds(0, 44, 144, 611);
		add(lblNewLabel);
	}

	// 이벤트 생성
	void eventProc(int memCode, String id) {

		this.memCode = memCode;
		this.id = id;
		
		// 서비스 신청
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 서비스 시작일, 종료일
				Date start = startDate.getDate();
				Date end = endDate.getDate();

				String csstartdate = simpleFormat.format(start);
				String cslastdate = simpleFormat.format(end);

				// tf값 불러오기
				int memcode = Integer.parseInt(tfMemCode.getText());
				int cgcode = Integer.parseInt(tfCareGiver.getText());

				// 오늘 날짜 yy/MM/dd 형태로 생성
				todayFm = simpleFormat.format(new Date(System.currentTimeMillis()));

				try {
					Date today = new Date(simpleFormat.parse(todayFm).getTime());
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 3);
					Date available = cal.getTime();

					int compare = start.compareTo(available);
					if (compare > 0) {
						// 오늘부터 3일 뒤니까 신청 가능
						int compare2 = end.compareTo(start);
						if (compare2 > 0) {
							try {
								vo = new CareVO(csstartdate, cslastdate, memcode, cgcode);
								dao.careInsert(vo);
								JOptionPane.showMessageDialog(null, "신청 완료");

								showBookList(memCode);
								tfCareGiver.setText("");
							} catch (Exception e3) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, "신청실패 : " + e3.getMessage());
							}
						} else {
							JOptionPane.showMessageDialog(null, "시작일이 종료일보다 빨라야합니다.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "시작일은 신청일로부터 3일 이후여야 합니다.");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}

		});
		// 선택한 서비스 조기종료
		// 종료일 sysdate로 update
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int col = 0;
				int row = apptable.getSelectedRow();
				int sCode = (Integer) apptable.getValueAt(row, col);
				try {

					if (dao.isStarted(sCode) == true) {
						if (dao.isPossible(sCode) == false) {
							JOptionPane.showMessageDialog(null, "이미 종료된 서비스 입니다.");
						} else {
							dao.CareModify(sCode);
							JOptionPane.showMessageDialog(null, "오늘부로 서비스가 종료됩니다.");
							showBookList(memCode);
						}
					} else if(dao.isStarted(sCode)==false) {
						dao.careCancl(sCode);
						JOptionPane.showMessageDialog(null, "서비스 신청이 취소됐습니다.");
						showBookList(memCode);
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		// click이에요 clik아니에요
		// 테이블에서 선택한 간병인 코드 띄우기
		cgtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = cgtable.getSelectedRow();
				String vNum = String.valueOf(cgtable.getValueAt(row, col));

				tfCareGiver.setText(vNum);
			}
		});

		// 지역별 간병인 정보 띄우기
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					System.out.println("서울선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "1콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 1) {
					System.out.println("대전선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "2콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 2) {
					System.out.println("부산선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "3콤보연결못했어요 : " + e1.getMessage());
					}

				} else if (index == 3) {
					System.out.println("경상도선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "4콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 4) {
					System.out.println("전라도선택");
					ArrayList all;
					try {
						all = dao.CareSearch(index);
						tmCare.data = all;
						cgtable.setModel(tmCare);
						tmCare.fireTableDataChanged();
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "5콤보연결못했어요 : " + e1.getMessage());
					}
					// TODO Auto-generated method stub
				}
			}

		});
	}

	class CareTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "간병인코드", "간병인명", "소속회사", "연락처", "급여코드", "지역" };

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

	class CaregiverTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "돌봄서비스코드", "시작일", "종료일", "회원코드", "간병인코드", "신청날짜" };

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
