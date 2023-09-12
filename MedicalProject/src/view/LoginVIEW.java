package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import model.MemlogDAO;
import model.rec.LoginVO;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class LoginVIEW extends JFrame implements ActionListener {
	private boolean flag = false;
	JFrame frmMsmtms;
	JTextField tfid;
	JPasswordField pfpw;
	JButton btlog, btmemlog;
	JLabel lbBackground, lbID, lbpw, lblog;

	LoginVO vo;
	MemlogDAO mdao;

	ArrayList list;

	public LoginVIEW() {

		try {
			mdao = new MemlogDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
			e.printStackTrace();
		}
		newObject();
		addLayout();
		event();

	}

	void newObject() {
		frmMsmtms = new JFrame();
		lbID = new JLabel("ID");
		lbID.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbpw = new JLabel("PW");
		lbpw.setFont(new Font("Segoe UI", Font.BOLD, 13));
		tfid = new JTextField();
		lblog = new JLabel("");
		lblog.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		pfpw = new JPasswordField();
		btlog = new JButton("Log in");
		btlog.setFont(new Font("HY중고딕", Font.BOLD, 13));
		btmemlog = new JButton("Sign Up");
		btmemlog.setFont(new Font("HY중고딕", Font.BOLD, 13));
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(LoginVIEW.class.getResource("/Image/11.jpg")));
	}

	void addLayout() {
		getContentPane().setLayout(null);
		frmMsmtms.setResizable(false);
		frmMsmtms.getContentPane().setBackground(new Color(255, 255, 255));
		frmMsmtms.setTitle("MSMTMS");
		frmMsmtms.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Image/150.png"));

		lbID.setBounds(305, 272, 30, 20);
		lbID.setHorizontalAlignment(SwingConstants.CENTER);

		lbpw.setHorizontalAlignment(SwingConstants.CENTER);
		lbpw.setBounds(305, 332, 30, 20);

		tfid.setBounds(362, 272, 160, 20);
		tfid.setColumns(10);

		lblog.setIcon(new ImageIcon(LoginVIEW.class.getResource("/Image/150.png")));
		lblog.setBounds(350, 78, 150, 150);

		pfpw.setBounds(362, 332, 160, 20);

		btlog.setBounds(305, 387, 97, 23);

		btmemlog.setBounds(425, 387, 97, 23);

		frmMsmtms.getContentPane().setLayout(null);
		frmMsmtms.getContentPane().add(btlog);
		frmMsmtms.getContentPane().add(btmemlog);
		frmMsmtms.getContentPane().add(lbpw);
		frmMsmtms.getContentPane().add(lbID);
		frmMsmtms.getContentPane().add(lblog);
		frmMsmtms.getContentPane().add(tfid);
		frmMsmtms.getContentPane().add(pfpw);

		lbBackground.setBounds(0, 0, 866, 513);

		frmMsmtms.getContentPane().add(lbBackground);
		frmMsmtms.setSize(854, 532);
		frmMsmtms.setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	void event() {
		// 로그인 버튼이벤트
		btlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();
				String pw = pfpw.getText();

				try {
					int code = mdao.Login(id, pw);
					if (tfid.getText().length() == 0 || pfpw.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "ID, PW칸을 확인해주세요!");
					} else if (code == 0) {
						JOptionPane.showMessageDialog(null, "ID, PW을 확인해주세요!");
					} else {
						JOptionPane.showMessageDialog(null, "" + id + "님 환영합니다!");

						frmMsmtms.setVisible(false);
						new MedicalStart(code, id);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, " 로그인 실패 : " + ex.getMessage());
					ex.printStackTrace();
				}

			}
		});

		// 회원가입 버튼이벤트
		btmemlog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmMsmtms.dispose(); // 현재 화면 닫기
				MemlogVIEW view = new MemlogVIEW(); // 회원가입 화면 객체 생성
				view.setVisible(true);
			}

		});
	}

	public class JTextFieldLimit extends PlainDocument {
		private int limit;
		private boolean toUppercase = false;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
			this.toUppercase = upper;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null) {
				return;
			}

			if ((getLength() + str.length()) <= limit) {
				if (toUppercase) {
					str = str.toUpperCase();
				}
				super.insertString(offset, str, (javax.swing.text.AttributeSet) attr);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginVIEW window = new LoginVIEW();
					window.frmMsmtms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
