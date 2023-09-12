package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.MAccountPaymentDAO;
import model.SAccountPaymentDAO;
import model.rec.AccountPaymentVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class MaccountPay extends JDialog {
	JLabel lbTitle, lbBackground, lbOrderCode, lbBankName, lbAccountNum, lbName, lbBillNum, lbPrice;
	JTextField tfOrderCode, tfAccountNum, tfName, tfBillNum, tfPrice;
	JComboBox cbBankName;
	JButton btnPay;
	
	AccountPaymentVO vo;
	MAccountPaymentDAO dao;

	// 공유할 데이터 선언
	int orderNum, price;

	// 부모로부터 초기데이터 받을 생성자 선언
	public MaccountPay(int orderNum, int price) {
		this.orderNum = orderNum;
		this.price = price;

		try {
			dao = new MAccountPaymentDAO();
			System.out.println("디비 연결 성공!");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "DB 연결 실패 : " + e.getMessage());
		}
		newObject(orderNum, price);
		addLayout(orderNum, price);
		event();
	}

	void newObject(int orderNum, int price) {
		lbTitle = new JLabel("계좌이체");
		lbTitle.setFont(new Font("HY견고딕", Font.PLAIN, 18));
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(MaccountPay.class.getResource("/Image/blurry-g72d76acb2_1280.jpg")));
		lbOrderCode = new JLabel("주문코드");
		lbOrderCode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbBankName = new JLabel("은행명");
		lbBankName.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbAccountNum = new JLabel("계좌번호");
		lbAccountNum.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbName = new JLabel("입금자명");
		lbName.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbBillNum = new JLabel("현금영수증번호");
		lbBillNum.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		lbPrice = new JLabel("결제금액");
		lbPrice.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfOrderCode = new JTextField(String.valueOf(orderNum));
		tfOrderCode.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		tfAccountNum = new JTextField();
		tfAccountNum.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfName = new JTextField();
		tfName.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfBillNum = new JTextField();
		tfBillNum.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfPrice = new JTextField(String.valueOf(price));
		tfPrice.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		String[] bank = { "국민은행", "하나은행", "카카오뱅크", "신한은행", "기업은행", "우리은행", "농협" };
		cbBankName = new JComboBox(bank);
		cbBankName.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnPay = new JButton("결제");
		btnPay.setFont(new Font("HY견고딕", Font.PLAIN, 12));
	}

	void addLayout(int orderNum, int price) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Image/150.png"));
		getContentPane().setLayout(null);

		lbTitle.setBounds(12, 10, 91, 18);
		getContentPane().add(lbTitle);

		lbBackground.setBounds(0, 0, 376, 40);
		getContentPane().add(lbBackground);

		lbOrderCode.setBounds(33, 64, 57, 15);
		getContentPane().add(lbOrderCode);

		lbBankName.setBounds(33, 105, 57, 15);
		getContentPane().add(lbBankName);

		lbAccountNum.setBounds(33, 143, 57, 15);
		getContentPane().add(lbAccountNum);

		lbName.setBounds(33, 187, 57, 15);
		getContentPane().add(lbName);

		lbBillNum.setBounds(33, 236, 100, 15);
		getContentPane().add(lbBillNum);

		lbPrice.setBounds(33, 281, 57, 15);
		getContentPane().add(lbPrice);

		tfOrderCode.setEditable(false);
		tfOrderCode.setBounds(199, 58, 116, 21);
		getContentPane().add(tfOrderCode);
		tfOrderCode.setColumns(10);

		tfAccountNum.setColumns(10);
		tfAccountNum.setBounds(102, 140, 213, 21);
		getContentPane().add(tfAccountNum);

		tfName.setColumns(10);
		tfName.setBounds(199, 181, 116, 21);
		getContentPane().add(tfName);

		tfBillNum.setColumns(10);
		tfBillNum.setBounds(139, 230, 176, 21);
		getContentPane().add(tfBillNum);

		tfPrice.setEditable(false);
		tfPrice.setColumns(10);
		tfPrice.setBounds(199, 275, 116, 21);
		getContentPane().add(tfPrice);

		setSize(355, 395);
		setLocationRelativeTo(null);

		cbBankName.setBounds(199, 98, 117, 23);
		getContentPane().add(cbBankName);
		
		btnPay.setBounds(119, 319, 97, 23);
		getContentPane().add(btnPay);
	}
	void event() {
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o == btnPay) {
					String account = tfAccountNum.getText();
					String name = tfName.getText();
					String bank = (String) cbBankName.getSelectedItem();
					String cashbill = tfBillNum.getText();
					int scode = Integer.parseInt(tfOrderCode.getText());
					vo = new AccountPaymentVO(account, name, bank, cashbill, scode);

					try {
						int cnt = dao.regist(vo);
						if (cnt > 0) {
							clearScreen();
							System.out.println("결제완료");
						} else {
							System.out.println("결제실패");
						}
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "결제 실패!! : " + e2.getMessage());
					}
					dispose();
				}
			}
		});
	}
	void clearScreen() {
		tfOrderCode.setText(null);
		tfAccountNum.setText(null);
		tfBillNum.setText(null);
		tfName.setText(null);
	}
}