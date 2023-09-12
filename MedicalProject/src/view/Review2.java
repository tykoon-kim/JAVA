package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import model.Review2Dao;
import model.ReviewDAO;
import model.rec.ReviewVO;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;

public class Review2 extends JDialog implements ActionListener {

	JTextField tfMachineName, tfShopCode, tfMemberCode;
	JTable tbAcceptedReview;
	JButton btnEdit, btnRegist, btnDelete;
	JTextArea taReviewContent;
	JLabel lbMachineName, lbRegistReview, lbShopCode, lbReviewAvg, lbMemberCode, lbTitle, lbBackground;
	JScrollPane scrollPane;
	JComboBox comboBox;
	ReviewTableModel tmReview;

	ReviewVO vo;
	Review2Dao dao;

	ArrayList list;
	int memCode, desc;
	String machineName;

	public Review2(int memCode, String machineName, int desc) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Image/rounded-rectangle (2).png"));

		this.memCode = memCode;
		this.machineName = machineName;
		this.desc = desc;
		getContentPane().setLayout(null);
		newObject(memCode, machineName);
		addLayout();
		eventProc(memCode, machineName);
		tfMemberCode.setText(String.valueOf(memCode));
		tfMemberCode.setEditable(false);
		tfMachineName.setText(machineName);
		tfShopCode.setText(String.valueOf(desc));

		try {
			dao = new Review2Dao();
			System.out.println("review 디비 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "review DB연결 실패 : " + e.getMessage());
		}
		selectTable(memCode, machineName);
	}

	void newObject(int memCode, String machineName) {
		lbTitle = new JLabel("내 구매 목록 후기");
		lbTitle.setFont(new Font("HY중고딕", Font.PLAIN, 26));
		lbMemberCode = new JLabel("회원코드");
		lbMemberCode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbShopCode = new JLabel("주문상세내역코드");
		lbShopCode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbMachineName = new JLabel("기기명");
		lbMachineName.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfMemberCode = new JTextField();
		tfMemberCode.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		tfShopCode = new JTextField();
		tfShopCode.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		tfMachineName = new JTextField();
		tfMachineName.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbBackground = new JLabel("");
		lbBackground.setIcon(
				new ImageIcon(Review2.class.getResource("/Image/pexels-codioful-(formerly-gradienta)-7130554.jpg")));
		tmReview = new ReviewTableModel();
		taReviewContent = new JTextArea();
		taReviewContent.setBackground(new Color(240, 240, 240));
		taReviewContent.setForeground(UIManager.getColor("TextArea.foreground"));
		tbAcceptedReview = new JTable();
		btnEdit = new JButton("수정");
		btnRegist = new JButton("등록");
		btnDelete = new JButton("삭제");
		lbRegistReview = new JLabel("등록된리뷰");
		lbReviewAvg = new JLabel("평점");
		scrollPane = new JScrollPane();
		comboBox = new JComboBox();
		taReviewContent.setFont(new Font("굴림", Font.PLAIN, 12));
		tbAcceptedReview.setFont(new Font("돋움", Font.PLAIN, 12));
		tbAcceptedReview
				.setModel(new DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "리뷰코드", "내용", "평점", "상세주문코드" }));
		btnEdit.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnRegist.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnDelete.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbRegistReview.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbReviewAvg.setFont(new Font("굴림", Font.PLAIN, 15));
		scrollPane.setViewportView(tbAcceptedReview);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "5", "4", "3", "2", "1" }));
	}

	void addLayout() {
		lbMemberCode.setBounds(13, 48, 120, 20);
		getContentPane().add(lbMemberCode);
		
		lbShopCode.setBounds(220, 38, 137, 40);
		getContentPane().add(lbShopCode);
		
		lbMachineName.setBounds(13, 65, 60, 40);
		getContentPane().add(lbMachineName);

		tfMemberCode.setColumns(10);
		tfMemberCode.setBounds(87, 48, 100, 20);
		getContentPane().add(tfMemberCode);
		
		tfShopCode.setEditable(false);
		tfShopCode.setBounds(357, 48, 100, 20);
		tfShopCode.setColumns(10);
		getContentPane().add(tfShopCode);

		tfMachineName.setEditable(false);
		tfMachineName.setBounds(87, 75, 100, 20);
		tfMachineName.setColumns(10);
		getContentPane().add(tfMachineName);
		lbTitle.setBounds(13, 10, 211, 28);
		getContentPane().add(lbTitle);
		lbBackground.setBounds(0, 0, 508, 105);
		getContentPane().add(lbBackground);
		taReviewContent.setBounds(13, 408, 480, 132);
		btnEdit.setBounds(207, 581, 90, 22);
		btnRegist.setBounds(13, 581, 90, 22);
		btnDelete.setBounds(403, 581, 90, 22);
		lbRegistReview.setBounds(13, 110, 80, 20);
		lbReviewAvg.setBounds(356, 548, 34, 22);
		scrollPane.setBounds(13, 140, 480, 258);
		comboBox.setBounds(403, 548, 90, 23);

		getContentPane().add(comboBox);
		getContentPane().add(taReviewContent);
		getContentPane().add(btnEdit);
		getContentPane().add(btnRegist);
		getContentPane().add(btnDelete);
		getContentPane().add(lbRegistReview);
		getContentPane().add(lbReviewAvg);
		getContentPane().add(scrollPane);

		setSize(522, 659);
		setLocationRelativeTo(null);
	}

	void eventProc(int memCode, String machineName) {
		btnEdit.addActionListener(this);
		btnRegist.addActionListener(this);
		btnDelete.addActionListener(this);
		tfMachineName.addActionListener(this);

		tbAcceptedReview.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int col = 0;
				int row = tbAcceptedReview.getSelectedRow();
				int rNum = (Integer) tbAcceptedReview.getValueAt(row, col);
				try {
					vo = dao.findByNum(rNum);
				} catch (Exception e1) {
					// TODO: handle exception
				}
//				tfReviewAvg.setText(String.valueOf(vo.getAvg()));
				comboBox.setSelectedItem(String.valueOf(vo.getAvg()));
				tfShopCode.setText(String.valueOf(vo.getCode()));
				taReviewContent.setText(String.valueOf(vo.getContent()));
				System.out.println("검색  성공");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnRegist) {
			String avg = String.valueOf(comboBox.getSelectedItem());
			String content = taReviewContent.getText();
			int code = Integer.parseInt(tfShopCode.getText());
			ReviewVO vo = new ReviewVO(avg, content, code);

			try {
				boolean poss = dao.isRegistPossible(code);
				if (poss) {
					try {
						dao.reviewRegist(vo);
						clearScreen();
						selectTable(memCode, machineName);
						System.out.println("등록 완료");
					} catch (Exception e3) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "등록 실패 " + e3.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "이미 등록된 주문입니다.");
				}
			} catch (Exception ea) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "이미 등록된 주문입니다. " + ea.getMessage());
			}
		} else if (o == btnEdit) {
			String avg = String.valueOf(comboBox.getSelectedItem());
			String content = taReviewContent.getText();
			int code = Integer.parseInt(tfShopCode.getText());
			vo = new ReviewVO(avg, content, code);
			int row = tbAcceptedReview.getSelectedRow();
			int rNum = (Integer) tbAcceptedReview.getValueAt(row, 0);
			vo.setNum(rNum);

			try {
				int cnt = dao.reviewEdit(vo);
//				selectTable();
				selectTable(memCode, machineName);
				if (cnt > 0) {
					System.out.println("수정 완료");
					clearScreen();
				} else {
					System.out.println("수정 실패");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "리뷰 수정 실패!! : " + e2.getMessage());
			}

		} else if (o == btnDelete) {
			int row = tbAcceptedReview.getSelectedRow();
			int rNum = (Integer) tbAcceptedReview.getValueAt(row, 0);
			vo.setNum(rNum);

			try {
				int cnt = dao.reviewDelete(vo);
//				selectTable();
//				selectTable(memCode, machineName);
				setVisible(false);

				if (cnt > 0) {
					clearScreen();
					System.out.println("삭제 완료");
				} else {
					System.out.println("삭제 실패");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "리뷰 삭제 실패!! : " + e2.getMessage());
			}

		}
	}

	private void selectTable(int memcode, String text) {
		// TODO Auto-generated method stub
		System.out.println(text + "이건 기구명");
		System.out.println(memcode + "이건 멤버코드");
		try {
			ArrayList list = new ArrayList();
			list = dao.ReviewSearch(text, memcode);
			System.out.println("뷰리스트" + list);
			tmReview.data = list;
			tbAcceptedReview.setModel(tmReview);
			tmReview.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "리뷰 검색 실패 : " + e.getMessage());
		}
	}

	void clearScreen() {
		comboBox.setSelectedIndex(0);
		taReviewContent.setText(null);
		tfShopCode.setText(null);
		tfMachineName.setText(null);
	}
}

class ReviewTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "리뷰코드", "내용", "평점", "상세주문코드" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}
