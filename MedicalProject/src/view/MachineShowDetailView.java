package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.rec.OrderVO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

public class MachineShowDetailView extends JDialog {

	JPanel contentPanel = new JPanel();
	JTextField tfName, tfCycle, tfPrice;
	JTextArea taMethod, taManage, taSignificant;
	JLabel lbImg, lbMachineName, lbMethod, lbChangeCycle, lbManagement, lbSignificant, lbPrice, lbBackground;
	Image img;
	ImageIcon imgIcon;
	String imgUrl;

	public MachineShowDetailView(OrderVO vo) {
		setFont(new Font("HY중고딕", Font.PLAIN, 12));
		setTitle("기구 상세보기");
		newObject(vo);
		addLayout(vo);
	}

	void newObject(OrderVO vo) {
		lbMachineName = new JLabel("기구명");
		lbMachineName.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbMethod = new JLabel("특징");
		lbMethod.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbChangeCycle = new JLabel("교환주기");
		lbChangeCycle.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbManagement = new JLabel("관리방법");
		lbManagement.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbSignificant = new JLabel("특이사항");
		lbSignificant.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lbPrice = new JLabel("가격");
		lbPrice.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		tfName = new JTextField(vo.getName());
		tfName.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfCycle = new JTextField(vo.getMachinecycle());
		tfCycle.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfPrice = new JTextField(vo.getAmsprice());
		tfPrice.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		taMethod = new JTextArea(vo.getMethod());
		taManage = new JTextArea(vo.getManagement());
		taSignificant = new JTextArea(vo.getAmssignificant());
		lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon("src/Image/pexels-codioful-(formerly-gradienta)-7130874 (1).jpg"));
	}

	void addLayout(OrderVO vo) {
		setBounds(100, 100, 800, 680);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		imgUrl = vo.getImage();
		try {
			img = new ImageIcon(new URL(imgUrl)).getImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		imgIcon = new ImageIcon(img.getScaledInstance(400, 500, Image.SCALE_SMOOTH));
		
		lbImg = new JLabel("", imgIcon, JLabel.CENTER);
		lbImg.setBounds(336, 36, 420, 556);
		contentPanel.add(lbImg);

		lbMachineName.setBounds(35, 56, 64, 16);
		contentPanel.add(lbMachineName);

		lbMethod.setBounds(35, 110, 52, 16);
		contentPanel.add(lbMethod);

		lbChangeCycle.setBounds(35, 236, 76, 16);
		contentPanel.add(lbChangeCycle);

		lbManagement.setBounds(35, 295, 76, 16);
		contentPanel.add(lbManagement);

		lbSignificant.setBounds(35, 418, 76, 16);
		contentPanel.add(lbSignificant);

		lbPrice.setBounds(35, 539, 52, 16);
		contentPanel.add(lbPrice);

		tfName.setEditable(false);
		tfName.setBounds(111, 53, 174, 21);
		contentPanel.add(tfName);
		tfName.setColumns(10);

		tfCycle.setEditable(false);
		tfCycle.setBounds(111, 233, 174, 21);
		contentPanel.add(tfCycle);
		tfCycle.setColumns(10);

		tfPrice.setEditable(false);
		tfPrice.setBounds(111, 536, 174, 21);
		contentPanel.add(tfPrice);
		tfPrice.setColumns(10);

		taMethod.setEditable(false);
		taMethod.setBounds(111, 110, 174, 87);
		taMethod.setLineWrap(true);
		contentPanel.add(taMethod);

		taManage.setEditable(false);
		taManage.setBounds(111, 290, 174, 87);
		taManage.setLineWrap(true);
		contentPanel.add(taManage);

		taSignificant.setEditable(false);
		taSignificant.setBounds(111, 413, 174, 87);
		taSignificant.setLineWrap(true);
		contentPanel.add(taSignificant);

		lbBackground.setBounds(0, 0, 316, 640);
		contentPanel.add(lbBackground);
		setLocationRelativeTo(null);
	}
}
