package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.OrderDAO;
import model.ReviewDAO;
import model.rec.OrderVO;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class OrderView extends JPanel {

	JFrame frame;
	JPanel total;
	JTable tbTotal, tbShop;
	JLabel lbTitle, lbBackground, lbPayMethod;
	JButton btnCartIn, btnCartOut, btnCard, btnAccount, btnShowReview, btnDetail, btnSearch;
	JComboBox cbCategory, cbDetail;
	JTextField tfSearch;
	JScrollPane allListScrollPane, shoppingListScrollPane;
	
	OrderTableModel tmOrder;
	ShopTableModel tmShop;
	OrderVO vo;
	OrderDAO dao;

	ArrayList list;
	int index, totalQuantity, orderNum, memCode;
	String id; // 회원 아이디 받아올 변수


	public OrderView(int memCode, String id) {
		setBackground(new Color(255, 255, 255));
		this.memCode = memCode;
		this.id = id;

		newObject(memCode, id);
		addLayout(memCode, id);
		eventProc(memCode, id);

		// 데이터베이스 연결
		try {
			dao = new OrderDAO();
			System.out.println("연결 성공");
			System.out.println("회원번호 : " + memCode + "회원 아이디: " + id);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "연결 실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}

	void newObject(int memCode, String id) {
		frame = new JFrame();
		lbTitle = new JLabel("주문");
		lbTitle.setFont(new Font("HY중고딕", Font.BOLD, 26));
		lbBackground = new JLabel();
		lbBackground.setIcon(new ImageIcon("src/Image/blurry-g72d76acb2_1280.jpg"));
		lbPayMethod = new JLabel("결제수단");
		lbPayMethod.setFont(new Font("HY중고딕", Font.BOLD, 15));
		allListScrollPane = new JScrollPane();
		shoppingListScrollPane = new JScrollPane();
		tbTotal = new JTable();
		tbTotal.setFont(new Font("돋움", Font.PLAIN, 12));
		tbTotal.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "코드", "품목명", "제조국", "제조사", "가격", "수량", "특징" }) {
					Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class,
							String.class, Integer.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		
		tbShop = new JTable();
		tbShop.setFont(new Font("돋움", Font.PLAIN, 12));
		tbShop.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "제품명", "수량", "가격", "구매처별코드", "주문코드" }) {
			Class[] columnTypes = new Class[] { String.class, Integer.class, String.class, Integer.class,
					Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		// 테이블에 스피너 넣기
		TableColumnModel model = tbShop.getColumnModel();
		TableColumn col = model.getColumn(1);
		col.setCellEditor(new SpinnerEditor());
		btnSearch = new JButton("검색");
		btnCard = new JButton("카드결제");
		btnCard.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnCard.setBounds(713, 606, 95, 25);
		btnAccount = new JButton("계좌결제");
		btnAccount.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnAccount.setBounds(607, 606, 95, 25);
		btnShowReview = new JButton("리뷰확인");
		btnShowReview.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnCartIn = new JButton("\u25BC");
		btnCartIn.setBackground(new Color(255, 255, 255));
		btnCartOut = new JButton("\u25B2");
		btnCartOut.setBackground(new Color(255, 255, 255));
		cbCategory = new JComboBox();
		cbCategory.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tmOrder = new OrderTableModel();
		tmShop = new ShopTableModel();
		btnDetail = new JButton("상세보기");
		btnDetail.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		cbDetail = new JComboBox();
		cbDetail.setFont(new Font("HY중고딕", Font.PLAIN, 12));
		tfSearch = new JTextField();
	}

	public class SpinnerEditor extends DefaultCellEditor {
		JSpinner sp;
		DefaultEditor defaultEdit;
		JTextField text;

		public SpinnerEditor() {
			super(new JTextField());
			sp = new JSpinner();
			defaultEdit = ((DefaultEditor) sp.getEditor());
			text = defaultEdit.getTextField();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			sp.setValue(value);
			return sp;
		}

		public Object getCellEditorValue() {
			return sp.getValue();
		}
	}

	void addLayout(int memCode, String id) {
		total = new JPanel(new BorderLayout());
		total.setBackground(new Color(255, 255, 255));
		JPanel panel = new JPanel();
		total.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		JPanel tablePane = new JPanel();
		tablePane.setBackground(new Color(255, 255, 255));
		total.add(tablePane, BorderLayout.CENTER);
		tablePane.setLayout(null);

		allListScrollPane.setBounds(12, 104, 796, 229);
		allListScrollPane.setViewportView(tbTotal);
		tablePane.add(allListScrollPane);
		
		shoppingListScrollPane.setBounds(12, 377, 796, 219);
		shoppingListScrollPane.setViewportView(tbShop);
		tablePane.add(shoppingListScrollPane);

		btnCartIn.setBounds(414, 343, 57, 23);
		tablePane.add(btnCartIn);

		btnCartOut.setBounds(483, 343, 57, 23);
		tablePane.add(btnCartOut);

		cbCategory.setModel(new DefaultComboBoxModel(new String[] { "소모품", "기구" }));
		cbCategory.setBounds(664, 69, 144, 23);
		tablePane.add(cbCategory);

		btnShowReview.setBounds(607, 343, 95, 25);
		tablePane.add(btnShowReview);
		btnShowReview.setVisible(false);

		btnDetail.setBounds(713, 343, 95, 25);
		tablePane.add(btnDetail);

		cbDetail.setModel(new DefaultComboBoxModel(new String[] { "제품명", "질병코드", "질병명" }));
		cbDetail.setBounds(508, 69, 144, 23);
		tablePane.add(cbDetail);
		cbDetail.setVisible(false);

		tfSearch.setBounds(12, 69, 220, 25);
		tablePane.add(tfSearch);
		tfSearch.setColumns(10);

		btnSearch.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnSearch.setBounds(244, 71, 97, 23);
		tablePane.add(btnSearch);		
		
		JPanel panel_1 = new JPanel();
		total.add(panel_1, BorderLayout.SOUTH);
		
		lbPayMethod.setBounds(517, 606, 78, 25);
		panel_1.add(lbPayMethod);
		panel_1.add(btnAccount);
		panel_1.add(btnCard);
		
		lbTitle.setBounds(12, 10, 140, 35);
		tablePane.add(lbTitle);
		
		lbBackground.setBounds(0, 51, 364, 611);
		tablePane.add(lbBackground);
		
		setLayout(new BorderLayout());
		add("Center", total);
	}

	void eventProc(int memCode, String id) {
		BtnEvent evt = new BtnEvent();

		btnAccount.addActionListener(evt);
		btnCard.addActionListener(evt);
		btnCartIn.addActionListener(evt);
		btnCartOut.addActionListener(evt);
		btnShowReview.addActionListener(evt);
		btnDetail.addActionListener(evt);
		btnSearch.addActionListener(evt);
		// 소모, 기구 선택
		cbCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index = cbCategory.getSelectedIndex();
				
				if (index == 0) {// 소모품일때
					System.out.println("소모품 선택");
					ArrayList all;
					cbDetail.setVisible(false);
					tmShop = new ShopTableModel();
					try {
						orderNum = dao.aNum();
						all = dao.somoSearch();

						tmShop.data = all;
						tbTotal.setModel(tmShop);
						btnShowReview.setVisible(false);
						clearShopTb(tbShop);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {// 기구일때
					System.out.println("기구 선택");
					ArrayList all;
					cbDetail.setVisible(true);
					
					tmOrder = new OrderTableModel();
					try {
						orderNum = dao.mNum();
						all = dao.machineSearch();
						tmOrder.data = all;
						tbTotal.setModel(tmOrder);
						tbTotal.getColumnModel().getColumn(0).setResizable(false);
						tbTotal.getColumnModel().getColumn(0).setPreferredWidth(40);
						tbTotal.getColumnModel().getColumn(1).setResizable(false);
						tbTotal.getColumnModel().getColumn(1).setPreferredWidth(50);
						tbTotal.getColumnModel().getColumn(2).setResizable(false);
						tbTotal.getColumnModel().getColumn(2).setPreferredWidth(60);
						tbTotal.getColumnModel().getColumn(3).setResizable(false);
						tbTotal.getColumnModel().getColumn(3).setPreferredWidth(120);
						tbTotal.getColumnModel().getColumn(4).setResizable(false);
						tbTotal.getColumnModel().getColumn(4).setPreferredWidth(100);
						tbTotal.getColumnModel().getColumn(5).setResizable(false);
						tbTotal.getColumnModel().getColumn(5).setPreferredWidth(300);
						btnShowReview.setVisible(true);
						// 콤보박스 변경하면 장바구니 초기화
						clearShopTb(tbShop);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	void clearShopTb(JTable tbShops) {
		DefaultTableModel model = (DefaultTableModel) tbShops.getModel();
		model.setNumRows(0);
	}

	class BtnEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object evt = e.getSource();
			// 카드 결제 눌렀을 때
			if (evt == btnCard) {
				int payMethod = 3;
				int rowCount = tbShop.getModel().getRowCount();
				int sum = 0;// 총수량
				int price = 0;// 총가격
				int quantity = 0; // 수량
				int temp = 0;

				if (rowCount > 0) {
					for (int i = 0; i < rowCount; i++) {
						sum += Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						quantity = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						temp = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 2)));
						price = price + (quantity * temp);
					}
					System.out.println(quantity + " " + price);
				}

				// 소모품
				if (index == 0) {
					// 다오 불러와서 주문이랑 주문 상세 생성하기
					try {
						dao.aOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.aOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "소모품 주문 실패 : " + e2.getMessage());
						e2.printStackTrace();
					}
					SoCardPay sc = new SoCardPay(orderNum, price);
					sc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sc.setModal(true);
					sc.setVisible(true);
				} else { // 기구
					try {
						dao.mOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.mOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "기구 주문 실패 : " + e2.getMessage());
					}
					McardPay mc = new McardPay(orderNum, price);
					mc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					mc.setModal(true);
					mc.setVisible(true);
				}
				clearShopTb(tbShop);
				// 계좌이체 눌렀을때
			} else if (evt == btnAccount) {
				int payMethod = 2;
				int rowCount = tbShop.getRowCount();
				int sum = 0;// 총수량
				int price = 0;// 총가격
				int quantity = 0;// 수량

				if (rowCount > 0) {
					for (int i = 0; i < rowCount; i++) {
						sum += Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						quantity = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 1)));
						int temp = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 2)));
						price = price + (quantity * temp);
					}
					System.out.println(quantity + " " + price);
				}
				// 소모품
				if (index == 0) {
					try {
						dao.aOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.aOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "소모품 주문 실패 : " + e2.getMessage());
						e2.printStackTrace();
					}
					SoAccountPay sa = new SoAccountPay(orderNum, price);
					sa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					sa.setModal(true);
					sa.setVisible(true);
				} else { // 기구
					try {
						dao.mOrder(memCode, orderNum, payMethod, sum, price);
						for (int i = 0; i < rowCount; i++) {
							// 테이블에 있는값 list에 담아서 전송하기
							String quantityStr = String.valueOf(tbShop.getModel().getValueAt(i, 1));
							int acc = Integer.parseInt(String.valueOf(tbShop.getModel().getValueAt(i, 3)));
							vo = new OrderVO(quantityStr, acc, orderNum);
							dao.mOrderDesc(vo);
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "기구 주문 실패 : " + e2.getMessage());
					}
					MaccountPay ma = new MaccountPay(orderNum, price);
					ma.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					ma.setModal(true);
					ma.setVisible(true);
				}
				clearShopTb(tbShop);
			} // 카트에 추가
			else if (evt == btnCartIn) {

				int row = tbTotal.getSelectedRow();
				int code = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 0)));
				int price = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 4)));
				String name = String.valueOf(tbTotal.getValueAt(row, 1));
				int rowCount = tbShop.getRowCount();

				int sameCnt = 0;

				if (rowCount <= 0) {
					CartIn(name, price, code);
				} else {
					for (int i = 0; i < rowCount; i++) {
						int shopCode = Integer.parseInt(String.valueOf(tbShop.getValueAt(i, 3)));
						if (code == shopCode) {
							sameCnt += 1;
						}
					}
					if (sameCnt == 1) {
						JOptionPane.showMessageDialog(null, "이미 추가한 내역입니다.");
						sameCnt = 0;
					} else {
						CartIn(name, price, code);
					}
				}

			} // 카트에서 삭제
			else if (evt == btnCartOut) {
				CartOut();

			} // 리뷰 확인
			else if (evt == btnShowReview) {
				int row = tbTotal.getSelectedRow();
				String shareData = String.valueOf(tbTotal.getValueAt(row, 1));

				ReviewView rView = new ReviewView(shareData);
				rView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				rView.setVisible(true);
			}
			// 상세보기
			else if(evt == btnDetail) {
				int category = cbCategory.getSelectedIndex();
				int row = tbTotal.getSelectedRow();
				int sellerCode = Integer.parseInt(String.valueOf(tbTotal.getValueAt(row, 0)));
				String productName = String.valueOf(tbTotal.getValueAt(row, 1));
				
				if(category == 0) {
					// 소모품
					try {
//						String img = dao.searchSomoImg(sellerCode, productName);
//						SomoShowDetailView ssd = new SomoShowDetailView(img);
						vo = new OrderVO();
						vo = dao.searchSomoAll(sellerCode, productName);
						SomoShowDetailView ssd = new SomoShowDetailView(vo);
						ssd.setVisible(true);
						ssd.setSize(540,700);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}else if(category == 1 ) {
					// 기구
					try {
//						String img = dao.searchImg(sellerCode, productName);
//						MachineShowDetailView msd = new MachineShowDetailView(img);
						vo = new OrderVO();
						vo = dao.searchMachineAll(sellerCode, productName);
						MachineShowDetailView msd = new MachineShowDetailView(vo);
						msd.setVisible(true);
						msd.setSize(799,679);
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
			// 검색
			else if(evt == btnSearch){
				int category = cbCategory.getSelectedIndex();
				String txt= tfSearch.getText().toUpperCase();
				// 소모품 검색
				if(category ==0) {
					tmShop = new ShopTableModel();
					try {
						list = dao.somoFind(txt);
						tmShop.data = list;
						tbTotal.setModel(tmShop);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					tfSearch.setText("");
				}// 기구 검색
				else if(category ==1) {
					int col = cbDetail.getSelectedIndex();
					
					tmOrder = new OrderTableModel();
					try {
						list = dao.machineFind(col, txt);
						tmOrder.data = list;
						tbTotal.setModel(tmOrder);
						tbTotal.getColumnModel().getColumn(0).setResizable(false);
						tbTotal.getColumnModel().getColumn(0).setPreferredWidth(40);
						tbTotal.getColumnModel().getColumn(1).setResizable(false);
						tbTotal.getColumnModel().getColumn(1).setPreferredWidth(50);
						tbTotal.getColumnModel().getColumn(2).setResizable(false);
						tbTotal.getColumnModel().getColumn(2).setPreferredWidth(60);
						tbTotal.getColumnModel().getColumn(3).setResizable(false);
						tbTotal.getColumnModel().getColumn(3).setPreferredWidth(120);
						tbTotal.getColumnModel().getColumn(4).setResizable(false);
						tbTotal.getColumnModel().getColumn(4).setPreferredWidth(100);
						tbTotal.getColumnModel().getColumn(5).setResizable(false);
						tbTotal.getColumnModel().getColumn(5).setPreferredWidth(300);
//						btnShowReview.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tfSearch.setText("");
				}
				
			}
		}
	}
	// 카트에 추가
	void CartIn(String name, int price, int code) {
		Vector shop = new Vector();

		shop.add(name);
		shop.add(1);
		shop.add(price);
		shop.add(code);
		shop.add(orderNum);

		DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
		model.addRow(shop);
		System.out.println("카트에 추가~");
	}
	// 카트에서 삭제
	void CartOut() {
		DefaultTableModel model = (DefaultTableModel) tbShop.getModel();
		int index = tbShop.getSelectedRow();

		if (index < 0) {
			System.out.println("삭제할 행을 선택하세요");
		} else {
			model.removeRow(index);
		}
	}
}
// 기구 테이블 모델
class OrderTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "코드", "기구명", "제조국", "제조사", "가격", "특징" };

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
// 소모품 테이블 모델
class ShopTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "코드", "소모품명", "제조국", "제조사", "가격", "유통기한" };

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
