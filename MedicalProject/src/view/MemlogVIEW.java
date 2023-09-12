package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import model.MemlogDAO;
import model.rec.LoginVO;
import model.rec.MemlogVO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class MemlogVIEW extends JFrame implements ActionListener {

	JFrame frame;
	JTextField tfid, tftel, tfemr, tfaddr, tfname;
	JButton btdouid, btmemlog, btcancel;
	JComboBox cbob;
	JPasswordField pfpw, pfju;
	JLabel lbBackground, lbTitle, lbid, lbpw, lbtel, lbemr, lbaddr, lbname, lbju, lbob;

	LoginVIEW lv;
	MemlogDAO mdao;
	LoginVO vo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemlogVIEW window = new MemlogVIEW();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MemlogVIEW() {

		try {
			mdao = new MemlogDAO();
			System.out.println("로그인창 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인창 연결 실패 : " + e.getMessage());
		}
		newObject();
		addLayout();
		event();
		
		setSize(544, 693);
		setTitle("회원가입");
	}

	void newObject() {
		lbTitle = new JLabel("회원 가입");
		lbTitle.setFont(new Font("HY견고딕", Font.PLAIN, 23));

		lbid = new JLabel("ID");
		lbid.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbpw = new JLabel("Password");
		lbpw.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbtel = new JLabel("연락처");
		lbtel.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbemr = new JLabel("비상 연락처");
		lbemr.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbaddr = new JLabel("주소");
		lbaddr.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbname = new JLabel("이름");
		lbname.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbju = new JLabel("주민번호");
		lbju.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		lbob = new JLabel("장애등급");
		lbob.setFont(new Font("HY견고딕", Font.PLAIN, 14));

		btdouid = new JButton("중복 확인");
		btdouid.setFont(new Font("HY견고딕", Font.PLAIN, 12));

		btmemlog = new JButton("회원 가입");
		btmemlog.setFont(new Font("HY견고딕", Font.PLAIN, 12));

		btcancel = new JButton("취소");
		btcancel.setFont(new Font("HY견고딕", Font.PLAIN, 12));

		tfid = new JTextField();
		tftel = new JTextField();
		tfemr = new JTextField();
		tfaddr = new JTextField();
		tfname = new JTextField();
		pfpw = new JPasswordField();
		pfju = new JPasswordField();
		cbob = new JComboBox();
		lbBackground = new JLabel("");
	}

	void addLayout() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Image/hospital.png"));
		getContentPane().setLayout(null);

		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(164, 10, 182, 53);
		getContentPane().add(lbTitle);

		lbid.setBounds(62, 94, 90, 25);
		getContentPane().add(lbid);

		lbpw.setBounds(62, 154, 90, 25);
		getContentPane().add(lbpw);

		lbtel.setBounds(62, 274, 90, 25);
		getContentPane().add(lbtel);

		lbemr.setBounds(62, 334, 90, 25);
		getContentPane().add(lbemr);

		lbaddr.setBounds(62, 394, 90, 25);
		getContentPane().add(lbaddr);

		lbname.setBounds(62, 454, 90, 25);
		getContentPane().add(lbname);

		lbju.setBounds(62, 514, 90, 25);
		getContentPane().add(lbju);

		lbob.setBounds(62, 214, 90, 25);
		getContentPane().add(lbob);

		btdouid.setBounds(383, 94, 110, 25);
		getContentPane().add(btdouid);

		btmemlog.setBounds(143, 586, 110, 25);
		getContentPane().add(btmemlog);

		btcancel.setBounds(284, 586, 110, 25);
		getContentPane().add(btcancel);

		tfid.setColumns(10);
		tfid.setBounds(164, 94, 182, 25);
		getContentPane().add(tfid);

		tftel.setColumns(10);
		tftel.setBounds(164, 274, 182, 25);
		getContentPane().add(tftel);

		tfemr.setColumns(10);
		tfemr.setBounds(164, 334, 182, 25);
		getContentPane().add(tfemr);

		tfaddr.setColumns(10);
		tfaddr.setBounds(164, 394, 182, 25);
		getContentPane().add(tfaddr);

		tfname.setColumns(10);
		tfname.setBounds(164, 454, 182, 25);
		getContentPane().add(tfname);

		pfpw.setBounds(164, 154, 182, 25);
		getContentPane().add(pfpw);

		pfju.setBounds(164, 514, 182, 25);
		getContentPane().add(pfju);

		cbob.setModel(new DefaultComboBoxModel(new String[] { "5급", "4급", "3급", "2급", "1급" }));
		cbob.setMaximumRowCount(5);
		cbob.setBounds(164, 215, 182, 23);
		getContentPane().add(cbob);

		lbBackground.setIcon(
				new ImageIcon(MemlogVIEW.class.getResource("/Image/pexels-codioful-(formerly-gradienta)-7130497.jpg")));
		lbBackground.setBounds(0, 0, 530, 63);
		getContentPane().add(lbBackground);

	}

	void event() {
		// 아이디 중복확인 및 자리수 버튼 이벤트
		btdouid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();

				try {
					String chid = mdao.checkid(id);
					if (tfid.getText().length() < 2) {
						JOptionPane.showMessageDialog(null, "최소 2글자 이상아이디를 입력해주세요!");
					} else {
						if (chid == null) {
							JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
						} else {
							JOptionPane.showMessageDialog(null, "이미 사용된 아이디입니다.");
							tfid.setText("");
						}
					}
				} catch (Exception e1) {
				}
			}
		});
		// 회원가입 버튼 이벤트
		btmemlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MemlogVO vo = new MemlogVO();

				String id = tfid.getText();
				String pw = pfpw.getText();
				String memtel = tftel.getText();
				String memaddr = tfaddr.getText();
				String mememr = tfemr.getText();
				String memname = tfname.getText();
				String memjuban = pfju.getText();
				Object memobstacle = cbob.getSelectedItem();

				vo.setID(id);
				vo.setPW(pw);
				vo.setMemtel(memtel);
				vo.setMemaddr(memaddr);
				vo.setMememr(mememr);
				vo.setMemname(memname);
				vo.setMemjuban(memjuban);
				vo.setMemobstacle(memobstacle);

				while (true) {

					if (tfid.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "아이디를 입력해주세요!");
						break;
					} else if (pfpw.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요!");
						break;
					} else if (tftel.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "연락처을 입력해주세요!");
						break;
					} else if (tfemr.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "비상연락처를 입력해주세요!");
						break;
					} else if (tfaddr.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "주소를 입력해주세요!");
						break;
					} else if (tfname.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "이름을 입력해주세요!");
						break;
					} else if (pfju.getText().length() == 0) {
						frame.setVisible(false);
						frame.setSize(500, 500);
						JOptionPane.showMessageDialog(null, "주민번호 앞자리를 입력해주세요!");
						break;
					} else {
						try {
							mdao.ijoin(vo);
							dispose();
							frame.setVisible(true);
							frame.setSize(500, 500);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
					}
					break;
				}
			}
		});

		btcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
	}

	// 클리어 스크린 메소드
	public void clearScreen() {
		tfid.setText("");
		pfpw.setText("");
		tftel.setText("");
		tfemr.setText("");
		tfaddr.setText("");
		tfname.setText("");
		pfju.setText("");
		cbob.setSelectedItem("");
	}

	// 글자 수 제한 메소드
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

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
