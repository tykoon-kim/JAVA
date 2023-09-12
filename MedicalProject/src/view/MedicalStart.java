package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.Review2Dao;

import java.awt.*;

public class MedicalStart extends JFrame {
	OrderView order;
	MyPageView myPage;
	CareView care;
	ConsultView consult;
	AsView as;
	String machine;
	Review2Dao dao;
	ReturnView rtn;

	public MedicalStart(int memCode, String id) {
		setTitle("MSMTMS");
		setFont(new Font("문체부 제목 돋음체", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Image/image10.png"));
		myPage = new MyPageView(memCode, id);
		order = new OrderView(memCode, id);
		care = new CareView(memCode, id);
		consult = new ConsultView(memCode, id);
		as = new AsView(memCode, id);
		rtn = new ReturnView(memCode, id);

		JTabbedPane pane = new JTabbedPane();
		pane.addTab("마이페이지", myPage);
		pane.addTab("주문", order);
		pane.addTab("돌봄", care);
		pane.addTab("상담", consult);
		pane.addTab("AS", as);
		pane.addTab("수거", rtn);

		getContentPane().add("Center", pane);
		setSize(850, 710);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
