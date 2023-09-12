package view;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.ConsultDAO;
import model.rec.ConsultVO;

public class ConsultView extends JPanel implements ActionListener {

	JTextField tfRepcode, tfMemCode, tfMememr, taivcon;
	JLabel lbivcon, lbivDate, lbReporter, lbRepdept, lbRepcode, lbMemCode, lbMememr, lbIvNow, lbBackground, lbTitle;
	JComboBox cbRepdept;
	JTable tbReporter, tbivList;
	JButton btnRegist, btnDelete;
	JScrollPane repoterScrollPane, interviewScrollPane;
	JDateChooser hopeDate;
	InterviewTableModel tmInterview;
	ConsultTableModel tmConsult;

	SimpleDateFormat simpleFormat;
	ConsultVO vo;
	ConsultDAO dao;

	int memCode;
	String id, emr, todayFm;
	ArrayList list;

	public ConsultView(int memCode, String id) {
		setBackground(new Color(255, 255, 255));
		this.memCode = memCode;
		this.id = id;

		newObject(memCode, id);
		addLayout();
		eventProc(memCode, id);

		tfMemCode.setText(String.valueOf(memCode));

		// 데이터베이스 연결
		try {
			dao = new ConsultDAO();
			emr = dao.findMemTel(memCode);
			tfMememr.setText(emr);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "연결실패했어요 : " + e.getMessage());
		}
		selectTable();
	}

	// 객체생성
	void newObject(int memCode, String id) {
		tmConsult = new ConsultTableModel();
		tmInterview = new InterviewTableModel();
		tfMemCode = new JTextField();
		tfMemCode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfMememr = new JTextField();
		tfMememr.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfRepcode = new JTextField();
		tfRepcode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		cbRepdept = new JComboBox();
		cbRepdept.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tbReporter = new JTable();
		tbReporter.setFont(new Font("돋움", Font.PLAIN, 12));
		tbivList = new JTable();
		tbivList.setFont(new Font("돋움", Font.PLAIN, 12));
		repoterScrollPane = new JScrollPane();
		interviewScrollPane = new JScrollPane();
		taivcon = new JTextField();
		taivcon.setForeground(UIManager.getColor("TextArea.foreground"));
		taivcon.setBackground(new Color(255, 255, 255));
		taivcon.setFont(new Font("HY중고딕", Font.PLAIN, 14));
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(ConsultView.class.getResource("/Image/signup.jpg")));
		lbTitle = new JLabel("상담");
		lbTitle.setFont(new Font("HY중고딕", Font.BOLD, 26));
		lbIvNow = new JLabel("상담 신청 현황");
		lbIvNow.setFont(new Font("HY중고딕", Font.BOLD, 15));
		lbMemCode = new JLabel("회원 번호");
		lbMemCode.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbMememr = new JLabel("보호자 연락망");
		lbMememr.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbivDate = new JLabel("희망날짜");
		lbivDate.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbReporter = new JLabel("상담원");
		lbReporter.setFont(new Font("HY중고딕", Font.BOLD, 15));
		lbRepcode = new JLabel("상담원코드");
		lbRepcode.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		lbivcon = new JLabel("상담 내용");
		lbivcon.setFont(new Font("HY중고딕", Font.BOLD, 15));
		lbRepdept = new JLabel("상담분야");
		lbRepdept.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		btnRegist = new JButton("등록");
		btnRegist.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnDelete = new JButton("취소");
		btnDelete.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		hopeDate = new JDateChooser();
		hopeDate.getCalendarButton().setFont(new Font("HY중고딕", Font.PLAIN, 12));
		simpleFormat = new SimpleDateFormat("yy/MM/dd");
	}

	// 자리배치
	void addLayout() {
		setLayout(null);
		tfMemCode.setEditable(false);
		tfMemCode.setColumns(10);
		tfMemCode.setBounds(12, 81, 100, 20);
		tfMememr.setEditable(false);
		tfMememr.setColumns(10);
		tfMememr.setBounds(12, 214, 120, 20);
		tfRepcode.setEditable(false);
		tfRepcode.setBounds(12, 326, 100, 20);
		tfRepcode.setColumns(10);
		lbTitle.setBounds(12, 10, 85, 26);
		lbIvNow.setBounds(164, 424, 110, 30);
		lbivcon.setBounds(164, 184, 80, 30);
		lbivDate.setBounds(12, 244, 100, 15);
		lbReporter.setBounds(164, 23, 80, 16);
		lbRepdept.setBounds(12, 122, 86, 15);
		lbRepcode.setBounds(12, 300, 100, 15);
		lbMemCode.setBounds(12, 56, 100, 15);
		lbMememr.setBounds(12, 190, 100, 15);
		lbBackground.setForeground(new Color(0, 128, 255));
		lbBackground.setBackground(new Color(0, 128, 255));
		lbBackground.setBounds(0, 44, 144, 611);

		tbReporter.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "상담원코드", "상담원이름", "담당부서" }));
		tbivList.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "상담코드", "상담 희망 날짜", "상담 내용", "회원코드", "상담원 코드" }));

		repoterScrollPane.setBounds(164, 44, 630, 130);
		repoterScrollPane.setViewportView(tbReporter);
		interviewScrollPane.setBounds(164, 452, 630, 150);
		interviewScrollPane.setViewportView(tbivList);
		taivcon.setBounds(164, 214, 630, 183);
		cbRepdept.setModel(new DefaultComboBoxModel(new String[] { "A/S", "수거", "구매", "돌봄" }));
		cbRepdept.setBounds(12, 147, 63, 20);
		btnRegist.setBounds(704, 405, 90, 22);
		btnDelete.setBounds(704, 612, 90, 22);
		hopeDate.setBounds(12, 270, 120, 20);

		add(tfMemCode);
		add(tfMememr);
		add(tfRepcode);
		add(taivcon);
		add(repoterScrollPane);
		add(interviewScrollPane);
		add(lbMemCode);
		add(lbTitle);
		add(lbIvNow);
		add(lbMememr);
		add(lbivDate);
		add(lbivcon);
		add(lbReporter);
		add(lbRepdept);
		add(lbRepcode);
		add(cbRepdept);
		add(btnRegist);
		add(btnDelete);
		add(hopeDate);
		add(lbBackground);
	}

	// 이벤트 생성
	public void eventProc(int memCode, String id) {
		this.memCode = memCode;
		this.id = id;

		btnRegist.addActionListener(this);
		btnDelete.addActionListener(this);
		tbReporter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = tbReporter.getSelectedRow();
				String vNum = String.valueOf(tbReporter.getValueAt(row, col));
				tfRepcode.setText(vNum);
			}
		});

		cbRepdept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = (int) cbRepdept.getSelectedIndex();
				if (index == 0) { //as 선택
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "1콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 1) { // 수거 선택
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "2콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 2) { // 구매 선택
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "3콤보연결못했어요 : " + e1.getMessage());
					}
				} else if (index == 3) { // 돌봄 선택
					ArrayList all;
					try {
						all = dao.ConSearch(index);
						tmConsult.data = all;
						tbReporter.setModel(tmConsult);
						tmConsult.fireTableDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "4콤보연결못했어요 : " + e1.getMessage());
					}
				}
			}
		});
	}

	private void selectTable() {
		try {
			ArrayList list = new ArrayList();
			list = dao.findByIvcode(memCode);
			tmInterview.data1 = list;
			tbivList.setModel(tmInterview);
			tbivList.getColumnModel().getColumn(2).setResizable(false);
			tbivList.getColumnModel().getColumn(2).setPreferredWidth(290);
			tmInterview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	class ConsultTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "상담원코드", "상담원이름", "담당부서" };

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

	class InterviewTableModel extends AbstractTableModel {
		ArrayList data1 = new ArrayList();
		String[] columnNames1 = { "상담코드", "상담 희망 날짜", "상담 내용", "회원코드", "상담원 코드" };

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames1.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data1.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			ArrayList temp1 = (ArrayList) data1.get(rowIndex);
			return temp1.get(columnIndex);
		}

		public String getColumnName(int col) {
			return columnNames1[col];
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o == btnRegist) {// 상담 신청

			// 상담 희망일
			Date date = hopeDate.getDate();
			String ivDate = simpleFormat.format(date);
			String ivcon = taivcon.getText();
			int memcode = Integer.parseInt(tfMemCode.getText());
			int repcode = Integer.parseInt(tfRepcode.getText());
			ConsultVO vo = new ConsultVO(ivDate, ivcon, memcode, repcode);
			// 오늘 날짜 yy/mm/dd형태로 생성
			todayFm = simpleFormat.format(new Date(System.currentTimeMillis()));
			try {
				Date today = new Date(simpleFormat.parse(todayFm).getTime());
				Date hopeDay = new Date(simpleFormat.parse(ivDate).getTime());

				int compare = hopeDay.compareTo(today);
				if (compare < 0) {
					JOptionPane.showMessageDialog(null, "이전 날짜는 선택 불가능");
				} else if (compare > 0) {
					try {
						dao.consultInsert(vo);
						selectTable();
						tfRepcode.setText("");
						cbRepdept.setSelectedIndex(0);
						tmConsult = new ConsultTableModel();
						tbReporter.setModel(tmConsult);
						taivcon.setText("");
						tfRepcode.setText("");
					} catch (Exception e1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "등록 실패");
					}
				} else {
					JOptionPane.showMessageDialog(null, "당일 예약 불가능");
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o == btnDelete) { // 상담 취소
			int row = tbivList.getSelectedRow();
			int rNum = (Integer) tbivList.getValueAt(row, 0);
			String ivDate = (String) tbivList.getValueAt(row, 1);
			String ivcon = (String) tbivList.getValueAt(row, 2);
			int memcode = (Integer) tbivList.getValueAt(row, 3);
			int repcode = (Integer) tbivList.getValueAt(row, 4);

			ConsultVO vo = new ConsultVO(ivDate, ivcon, memcode, repcode);
			try {
				if (dao.isPossible(rNum) == false) {
					JOptionPane.showMessageDialog(null, "상담이 완료된 건입니다.");
				} else {
					dao.consultDelete(rNum, vo);
					selectTable();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
