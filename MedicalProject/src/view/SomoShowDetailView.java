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
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class SomoShowDetailView extends JDialog {

	JPanel contentPanel = new JPanel();
	JTextField tfName, tfWarranty, tfExp, tfPrice;
	JLabel lbImg, lbSomoName, lbWarranty, lbExp, lbPrice, lbBackground;
	Image img;
	ImageIcon imgIcon;
	
	String imgUrl;
	
	public SomoShowDetailView(OrderVO vo) {
		setFont(new Font("HY중고딕", Font.PLAIN, 12));
		setResizable(false);
		setTitle("소모품 상세보기");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Image/150.png"));
		newObject(vo);
		addLayout(vo);
	}
	void newObject(OrderVO vo) {
		lbSomoName = new JLabel("소모품명");
		lbSomoName.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		
		lbWarranty = new JLabel("평균소모일");
		lbWarranty.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		
		lbExp = new JLabel("유통기한");
		lbExp.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		
		lbPrice = new JLabel("가격");
		lbPrice.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		
		tfName = new JTextField(vo.getAcname());
		tfName.setFont(new Font("HY중고딕", Font.PLAIN, 12));

		tfWarranty = new JTextField(vo.getAcwarranty());
		tfWarranty.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		
		tfExp = new JTextField(vo.getAcexp());
		tfExp.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		
		tfPrice = new JTextField(vo.getAmsprice());
		tfPrice.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		
		lbBackground = new JLabel("");
	}
	
	void addLayout(OrderVO vo) {
		
		setBounds(100, 100, 550, 700);
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
		
		lbImg = new JLabel("",imgIcon,JLabel.CENTER);
		lbImg.setBackground(new Color(192, 192, 192));
		lbImg.setBounds(71, 142, 400, 500);
		contentPanel.add(lbImg);
		
		lbSomoName.setBounds(36, 30, 69, 16);
		contentPanel.add(lbSomoName);
		
		lbWarranty.setBounds(36, 70, 82, 16);
		contentPanel.add(lbWarranty);
	
		lbExp.setBounds(292, 28, 69, 16);
		contentPanel.add(lbExp);
	
		lbPrice.setBounds(292, 71, 57, 16);
		contentPanel.add(lbPrice);
		
		tfName.setEditable(false);
		tfName.setBounds(130, 27, 116, 21);
		tfName.setColumns(10);
		contentPanel.add(tfName);
		
		tfWarranty.setEditable(false);
		tfWarranty.setBounds(130, 68, 116, 21);
		tfWarranty.setColumns(10);
		contentPanel.add(tfWarranty);
		
		tfExp.setEditable(false);
		tfExp.setBounds(373, 28, 116, 21);
		tfExp.setColumns(10);
		contentPanel.add(tfExp);
		
		tfPrice.setEditable(false);
		tfPrice.setBounds(373, 72, 116, 21);
		tfPrice.setColumns(10);
		contentPanel.add(tfPrice);
		
		lbBackground.setIcon(new ImageIcon("src/Image/pexels-codioful-(formerly-gradienta)-7130554.jpg"));
		lbBackground.setBounds(0, 0, 540, 123);
		contentPanel.add(lbBackground);
		setLocationRelativeTo(null);
	}

}
