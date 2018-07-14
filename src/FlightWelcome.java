package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FlightDetail {
	public FlightDetail(String s, Execute exe) {
		FlightDetailLayout window = new FlightDetailLayout(s, exe);
		window.setBounds(380,160,600,400);
		window.setTitle("航班信息");
	}
}

class FlightDetailLayout extends JFrame {
	FlightInfo d_pn_info;
	FlightBook d_pn_book;
	JButton d_bt_clos, d_bt_book;
	int fs = 0, bs = 0, es = 0;
	public FlightDetailLayout(String s, Execute exe){
		exe.ExecuteQuery("select * from Airtype where act_id in"
				+ "(select sch_aircraft from Schedule where sch_id in"
				+ "(select lin_flight from Flight where lin_id = '"+ s +"'))");
		try {
			exe.rs.next();
			fs = exe.rs.getInt("act_sfc");
			bs = exe.rs.getInt("act_sbu");
			es = exe.rs.getInt("act_sco");
		}
		catch(Exception ex) {}
		exe.ExecuteQuery("select lin_fcs, lin_bus, lin_cos from Flight where lin_id = '"+ s +"'");
		try {
			exe.rs.next();
			fs -= exe.rs.getInt("lin_fcs");
			bs -= exe.rs.getInt("lin_bus");
			es -= exe.rs.getInt("lin_cos");
		}
		catch(Exception ex) {}
		d_bt_clos = new JButton("关闭");
		d_bt_book = new JButton("购票");
		d_pn_info = new FlightInfo(s, exe);
		d_pn_book = new FlightBook(s, exe, fs, bs, es);
		add(d_bt_clos);
		add(d_bt_book);
		add(d_pn_info);
		add(d_pn_book);
		d_bt_clos.setBounds(320,320,80,30);
		d_bt_book.setBounds(200,320,80,30);
		d_pn_info.setBounds(320,0,280,320);
		d_pn_book.setBounds(0,0,320,320);
		d_bt_clos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		d_bt_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(d_pn_book.price == 0) {
					new Msgbox("请选择舱位和数量！");
					return;
				}
				if(d_pn_book.d_rd_qua1.isSelected()) {
					if(d_pn_book.d_tf_nom1.getText().equals("")) {
						new Msgbox("请输入顾客1的姓名！");
					}
					else {
						String idcard = d_pn_book.d_tf_idc1.getText();
						if(!checkIDcard(idcard)) {
							new Msgbox("顾客1身份证号有误，请重新输入！");
							return;
						}
					}
				}
				if(d_pn_book.d_rd_qua2.isSelected()) {
					if(d_pn_book.d_tf_nom1.getText().equals("")) {
						new Msgbox("请输入顾客1的姓名！");
					}
					else {
						String idcard = d_pn_book.d_tf_idc1.getText();
						if(!checkIDcard(idcard)) {
							new Msgbox("顾客1身份证号有误，请重新输入！");
							return;
						}
					}
					if(d_pn_book.d_tf_nom2.getText().equals("")) {
						new Msgbox("请输入顾客2的姓名！");
					}
					else {
						String idcard = d_pn_book.d_tf_idc2.getText();
						if(!checkIDcard(idcard)) {
							new Msgbox("顾客2身份证号有误，请重新输入！");
							return;
						}
					}
					if(d_pn_book.d_rd_fcla.isSelected() && fs == 1) {
						new Msgbox("头等舱剩余票数不足！");
						return;
					}
					if(d_pn_book.d_rd_bcla.isSelected() && bs == 1) {
						new Msgbox("商务舱剩余票数不足！");
						return;
					}
					if(d_pn_book.d_rd_ecla.isSelected() && es == 1) {
						new Msgbox("经济舱剩余票数不足！");
						return;
					}
				}
				if(d_pn_book.d_rd_qua3.isSelected()) {
					if(d_pn_book.d_tf_nom1.getText().equals("")) {
						new Msgbox("请输入顾客1的姓名！");
					}
					else {
						String idcard = d_pn_book.d_tf_idc1.getText();
						if(!checkIDcard(idcard)) {
							new Msgbox("顾客1身份证号有误，请重新输入！");
							return;
						}
					}
					if(d_pn_book.d_tf_nom2.getText().equals("")) {
						new Msgbox("请输入顾客2的姓名！");
					}
					else {
						String idcard = d_pn_book.d_tf_idc2.getText();
						if(!checkIDcard(idcard)) {
							new Msgbox("顾客2身份证号有误，请重新输入！");
							return;
						}
					}
					if(d_pn_book.d_tf_nom3.getText().equals("")) {
						new Msgbox("请输入顾客3的姓名！");
					}
					else {
						String idcard = d_pn_book.d_tf_idc3.getText();
						if(!checkIDcard(idcard)) {
							new Msgbox("顾客3身份证号有误，请重新输入！");
							return;
						}
					}
					if(d_pn_book.d_rd_fcla.isSelected() && fs < 3) {
						new Msgbox("头等舱剩余票数不足！");
						return;
					}
					if(d_pn_book.d_rd_bcla.isSelected() && bs < 3) {
						new Msgbox("商务舱剩余票数不足！");
						return;
					}
					if(d_pn_book.d_rd_ecla.isSelected() && es < 3) {
						new Msgbox("经济舱剩余票数不足！");
						return;
					}
				}
				String s1,s2;
				String group = "None";
				if(d_pn_book.d_rd_fcla.isSelected())
					group = "First";
				if(d_pn_book.d_rd_bcla.isSelected())
					group = "Business";
				if(d_pn_book.d_rd_ecla.isSelected())
					group = "Economy";
				if(d_pn_book.d_cb_deli.isSelected()) s1="TRUE";
				else s1="FALSE";
				if(d_pn_book.d_cb_safi.isSelected()) s2="TRUE";
				else s2="FALSE";
				if(d_pn_book.d_rd_qua1.isSelected()) {
					String price = String.valueOf(d_pn_book.price);
					exe.ExecuteModify("insert into Booking values ('" + exe.username + "', '" + s + "', NULL,"
							+ "'" + d_pn_book.d_tf_nom1.getText() + "', '" + d_pn_book.d_tf_idc1.getText()
							+ "', GETDATE(), '" + s1 + "', '" + s2 + "', '"+ group + "', '"+ price + "')");
					exe.ExecuteQuery("select lin_fcs, lin_bus, lin_cos from Flight where lin_id = '"+ s +"'");
					try {
						exe.rs.next();
						if(group == "First") {
							int remain = Integer.valueOf(exe.rs.getString("lin_fcs"));
							remain += 1;
							exe.ExecuteModify("update Flight set lin_fcs = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
						else if(group == "Business") {
							int remain = Integer.valueOf(exe.rs.getString("lin_bus"));
							remain += 1;
							exe.ExecuteModify("update Flight set lin_bus = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
						else if(group == "Economy") {
							int remain = Integer.valueOf(exe.rs.getString("lin_cos"));
							remain += 1;
							exe.ExecuteModify("update Flight set lin_cos = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
					}
					catch(Exception ex) {}
				}
				else if(d_pn_book.d_rd_qua2.isSelected())
				{
					String price = String.valueOf(d_pn_book.price/2);
					exe.ExecuteModify("insert into Booking values ('" + exe.username + "', '" + s + "', NULL,"
							+ "'" + d_pn_book.d_tf_nom1.getText() + "', '" + d_pn_book.d_tf_idc1.getText()
							+ "', GETDATE(), '" + s1 + "', '" + s2 + "', '"+ group + "', '"+ price + "')");
					exe.ExecuteModify("insert into Booking values ('" + exe.username + "', '" + s + "', NULL,"
							+ "'" + d_pn_book.d_tf_nom2.getText() + "', '" + d_pn_book.d_tf_idc2.getText()
							+ "', GETDATE(), '" + s1 + "', '" + s2 + "', '"+ group + "', '"+ price + "')");
					exe.ExecuteQuery("select lin_fcs, lin_bus, lin_cos from Flight where lin_id = '"+ s +"'");
					try {
						exe.rs.next();
						if(group == "First") {
							int remain = Integer.valueOf(exe.rs.getString("lin_fcs"));
							remain += 2;
							exe.ExecuteModify("update Flight set lin_fcs = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
						else if(group == "Business") {
							int remain = Integer.valueOf(exe.rs.getString("lin_bus"));
							remain += 2;
							exe.ExecuteModify("update Flight set lin_bus = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
						else if(group == "Economy") {
							int remain = Integer.valueOf(exe.rs.getString("lin_cos"));
							remain += 2;
							exe.ExecuteModify("update Flight set lin_cos = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
					}
					catch(Exception ex) {}
				}
				else if(d_pn_book.d_rd_qua3.isSelected())
				{
					String price = String.valueOf(d_pn_book.price/3);
					exe.ExecuteModify("insert into Booking values ('" + exe.username + "', '" + s + "', NULL,"
							+ "'" + d_pn_book.d_tf_nom1.getText() + "', '" + d_pn_book.d_tf_idc1.getText()
							+ "', GETDATE(), '" + s1 + "', '" + s2 + "', '"+ group + "', '"+ price + "')");
					exe.ExecuteModify("insert into Booking values ('" + exe.username + "', '" + s + "', NULL,"
							+ "'" + d_pn_book.d_tf_nom2.getText() + "', '" + d_pn_book.d_tf_idc2.getText()
							+ "', GETDATE(), '" + s1 + "', '" + s2 + "', '"+ group + "', '"+ price + "')");
					exe.ExecuteModify("insert into Booking values ('" + exe.username + "', '" + s + "', NULL,"
							+ "'" + d_pn_book.d_tf_nom3.getText() + "', '" + d_pn_book.d_tf_idc3.getText()
							+ "', GETDATE(), '" + s1 + "', '" + s2 + "', '"+ group + "', '"+ price + "')");
					exe.ExecuteQuery("select lin_fcs, lin_bus, lin_cos from Flight where lin_id = '"+ s +"'");
					try {
						exe.rs.next();
						if(group == "First") {
							int remain = Integer.valueOf(exe.rs.getString("lin_fcs"));
							remain += 3;
							exe.ExecuteModify("update Flight set lin_fcs = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
						else if(group == "Business") {
							int remain = Integer.valueOf(exe.rs.getString("lin_bus"));
							remain += 3;
							exe.ExecuteModify("update Flight set lin_bus = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
						else if(group == "Economy") {
							int remain = Integer.valueOf(exe.rs.getString("lin_cos"));
							remain += 3;
							exe.ExecuteModify("update Flight set lin_cos = '"+String.valueOf(remain)+"' where lin_id = '"+s+"'");
						}
					}
					catch(Exception ex) {}
				}
				new Msgbox("购票成功！");
				setVisible(false);
				dispose();
			}
		});
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public boolean checkIDcard(String idcard) {
		if(idcard.length() != 18)
			return false;
		else if(Integer.valueOf(idcard.substring(6, 10))>2018 ||
				Integer.valueOf(idcard.substring(10, 12))>12 ||
				Integer.valueOf(idcard.substring(12, 14))>31)
			return false;
		else {
			int d[] = new int[17];
			int checksum = 0, actualsum=0;
			for(int i=0;i<17;i++) {
				d[i] = Integer.valueOf(idcard.charAt(i)-48);
				checksum += d[i]*((int)(Math.pow(2,17-i))%11);
			}
			checksum%=11;
			if(idcard.charAt(17)=='X')
				actualsum=10;
			else
				actualsum=Integer.valueOf(idcard.charAt(17)-48);
			if((checksum + actualsum) % 11 != 1)
				return false;
			else
				return true;
		}
	}
}

class FlightInfo extends JPanel{
	JLabel d_lb_flin, d_lb_dept, d_lb_arrt, d_lb_acty, d_lb_acid, d_lb_date, d_lb_fcp, d_lb_bup, d_lb_cop;
	Font windowfont = new Font("宋体",Font.PLAIN, 16);
	public FlightInfo(String s, Execute exe){
		setLayout(null);
		d_lb_flin = new JLabel("航班编号：");
		d_lb_dept = new JLabel("起飞时间：");
		d_lb_arrt = new JLabel("抵达时间：");
		d_lb_acty = new JLabel("执飞机型：");
		d_lb_acid = new JLabel("飞机编号：");
		d_lb_date = new JLabel("航班日期：");
		d_lb_fcp  = new JLabel("头等舱票价：");
		d_lb_bup  = new JLabel("商务舱票价：");
		d_lb_cop  = new JLabel("经济舱票价：");
		exe.ExecuteQuery("select sch_flight, sch_dept, sch_arrt from Schedule where sch_id in"
				+ "(select lin_flight from Flight where lin_id = '"+s+"')");
		try {
			exe.rs.next();
			String flightno = exe.rs.getString("sch_flight");
			String depttime = exe.rs.getString("sch_dept").substring(0, 5);
			String arrvtime = exe.rs.getString("sch_arrt").substring(0, 5);
			d_lb_flin.setText("航班编号："+flightno);
			d_lb_dept.setText("起飞时间："+depttime);
			d_lb_arrt.setText("抵达时间："+arrvtime);
		}
		catch(Exception ex) {}
		exe.ExecuteQuery("select act_name from Airtype where act_id in"
				+ "(select sch_aircraft from Schedule where sch_id in"
				+ "(select lin_flight from Flight where lin_id = '"+s+"'))");
		try {
			exe.rs.next();
			String airtype = exe.rs.getString("act_name");
			d_lb_acty.setText("执飞机型："+airtype);
		}
		catch(Exception ex) {}
		exe.ExecuteQuery("select * from Flight where lin_id = '"+s+"'");
		try {
			exe.rs.next();
			String flitdate = exe.rs.getString("lin_date");
			String aircraft = exe.rs.getString("lin_aircraft");
			String firprice = exe.rs.getString("lin_fcp");
			String busprice = exe.rs.getString("lin_bup");
			String ecoprice = exe.rs.getString("lin_cop");
			d_lb_date.setText("航班日期："+flitdate);
			d_lb_acid.setText("飞机编号："+aircraft);
			d_lb_fcp.setText("头等舱票价："+firprice+"元");
			d_lb_bup.setText("商务舱票价："+busprice+"元");
			d_lb_cop.setText("经济舱票价："+ecoprice+"元");
		}
		catch(Exception ex) {}
		add(d_lb_flin);
		add(d_lb_dept);
		add(d_lb_arrt);
		add(d_lb_acty);
		add(d_lb_date);
		add(d_lb_acid);
		add(d_lb_fcp);
		add(d_lb_bup);
		add(d_lb_cop);
		d_lb_flin.setBounds(50,20,300,25);
		d_lb_date.setBounds(50,50,300,25);
		d_lb_dept.setBounds(50,80,300,25);
		d_lb_arrt.setBounds(50,110,300,25);
		d_lb_acty.setBounds(50,140,300,25);
		d_lb_acid.setBounds(50,170,300,25);
		d_lb_fcp.setBounds(50,200,300,25);
		d_lb_bup.setBounds(50,230,300,25);
		d_lb_cop.setBounds(50,260,300,25);
		d_lb_flin.setFont(windowfont);
		d_lb_dept.setFont(windowfont);
		d_lb_arrt.setFont(windowfont);
		d_lb_acty.setFont(windowfont);
		d_lb_date.setFont(windowfont);
		d_lb_acid.setFont(windowfont);
		d_lb_fcp.setFont(windowfont);
		d_lb_bup.setFont(windowfont);
		d_lb_cop.setFont(windowfont);
	}
}

class FlightBook extends JPanel{
	JLabel d_lb_quan, d_lb_nome, d_lb_idcd, d_lb_clas, d_lb_pric, d_lb_remn;
	JRadioButton d_rd_qua1, d_rd_qua2, d_rd_qua3, d_rd_fcla, d_rd_bcla, d_rd_ecla;
	ButtonGroup d_bg_quan, d_bg_clas;
	JTextField d_tf_nom1, d_tf_nom2, d_tf_nom3, d_tf_idc1, d_tf_idc2, d_tf_idc3;
	JCheckBox d_cb_deli, d_cb_safi;
	int firp = 0, busp = 0, ecop = 0, price = 0;
	FlightBook(String s, Execute exe, int fs, int bs, int es){
		setLayout(null);
		d_lb_quan = new JLabel("购票数量：");
		d_lb_nome = new JLabel("购票者姓名",JLabel.CENTER);
		d_lb_idcd = new JLabel("购票者身份证号",JLabel.CENTER);
		d_lb_clas = new JLabel("选择舱位：");
		d_lb_pric = new JLabel("总价格：");
		d_lb_remn = new JLabel();
		d_rd_fcla = new JRadioButton("头等舱");
		d_rd_bcla = new JRadioButton("商务舱");
		d_rd_ecla = new JRadioButton("经济舱");
		d_rd_qua1 = new JRadioButton("1张");
		d_rd_qua2 = new JRadioButton("2张");
		d_rd_qua3 = new JRadioButton("3张");
		d_bg_clas = new ButtonGroup();
		d_bg_quan = new ButtonGroup();
		d_tf_nom1 = new JTextField();
		d_tf_nom2 = new JTextField();
		d_tf_nom3 = new JTextField();
		d_tf_idc1 = new JTextField();
		d_tf_idc2 = new JTextField();
		d_tf_idc3 = new JTextField();
		d_cb_deli = new JCheckBox("购买延误险");
		d_cb_safi = new JCheckBox("购买意外险");
		d_bg_clas.add(d_rd_fcla);
		d_bg_clas.add(d_rd_bcla);
		d_bg_clas.add(d_rd_ecla);
		d_bg_quan.add(d_rd_qua1);
		d_bg_quan.add(d_rd_qua2);
		d_bg_quan.add(d_rd_qua3);
		add(d_lb_quan);
		add(d_lb_nome);
		add(d_lb_idcd);
		add(d_lb_clas);
		add(d_lb_pric);
		add(d_lb_remn);
		add(d_rd_fcla);
		add(d_rd_bcla);
		add(d_rd_ecla);
		add(d_rd_qua1);
		add(d_rd_qua2);
		add(d_rd_qua3);
		add(d_tf_nom1);
		add(d_tf_nom2);
		add(d_tf_nom3);
		add(d_tf_idc1);
		add(d_tf_idc2);
		add(d_tf_idc3);
		add(d_cb_deli);
		add(d_cb_safi);
		d_lb_clas.setBounds(20,20,60,25);
		d_lb_quan.setBounds(20,50,100,25);
		d_lb_nome.setBounds(20,80,80,25);
		d_lb_idcd.setBounds(120,80,180,25);
		d_lb_remn.setBounds(70, 250, 150, 25);
		d_lb_pric.setBounds(70, 280, 150, 25);
		d_rd_fcla.setBounds(90, 20, 80, 25);
		d_rd_bcla.setBounds(170, 20, 80, 25);
		d_rd_ecla.setBounds(250, 20, 80, 25);
		d_rd_qua1.setBounds(90, 50, 80, 25);
		d_rd_qua2.setBounds(170, 50, 80, 25);
		d_rd_qua3.setBounds(250, 50, 80, 25);
		d_tf_nom1.setBounds(20, 110, 80, 25);
		d_tf_nom2.setBounds(20, 140, 80, 25);
		d_tf_nom3.setBounds(20, 170, 80, 25);
		d_tf_idc1.setBounds(120, 110, 180, 25);
		d_tf_idc2.setBounds(120, 140, 180, 25);
		d_tf_idc3.setBounds(120, 170, 180, 25);
		d_cb_deli.setBounds(30, 210, 100, 25);
		d_cb_safi.setBounds(170, 210, 100, 25);
		if(fs == 0)
			d_rd_fcla.setEnabled(false);
		if(bs == 0)
			d_rd_bcla.setEnabled(false);
		if(es == 0)
			d_rd_ecla.setEnabled(false);
		d_tf_nom1.setEditable(false);
		d_tf_nom2.setEditable(false);
		d_tf_nom3.setEditable(false);
		d_tf_idc1.setEditable(false);
		d_tf_idc2.setEditable(false);
		d_tf_idc3.setEditable(false);
		exe.ExecuteQuery("select lin_fcp, lin_bup, lin_cop from Flight where lin_id = '"+s+"'");
		try {
			exe.rs.next();
			firp = Integer.valueOf(exe.rs.getString("lin_fcp"));
			busp = Integer.valueOf(exe.rs.getString("lin_bup"));
			ecop = Integer.valueOf(exe.rs.getString("lin_cop"));
		}
		catch(Exception ex) {}
		d_rd_qua1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d_tf_nom1.setEditable(true);
				d_tf_nom2.setEditable(false);
				d_tf_nom3.setEditable(false);
				d_tf_nom2.setText("");
				d_tf_nom3.setText("");
				d_tf_idc1.setEditable(true);
				d_tf_idc2.setEditable(false);
				d_tf_idc3.setEditable(false);
				d_tf_idc2.setText("");
				d_tf_idc3.setText("");
				price = calprice(firp, busp, ecop);
			}
		});
		d_rd_qua2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d_tf_nom1.setEditable(true);
				d_tf_nom2.setEditable(true);
				d_tf_nom3.setEditable(false);
				d_tf_nom3.setText("");
				d_tf_idc1.setEditable(true);
				d_tf_idc2.setEditable(true);
				d_tf_idc3.setEditable(false);
				d_tf_idc3.setText("");
				price = calprice(firp, busp, ecop);
			}
		});
		d_rd_qua3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d_tf_nom1.setEditable(true);
				d_tf_nom2.setEditable(true);
				d_tf_nom3.setEditable(true);
				d_tf_idc1.setEditable(true);
				d_tf_idc2.setEditable(true);
				d_tf_idc3.setEditable(true);
				price = calprice(firp, busp, ecop);
			}
		});
		d_rd_fcla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d_lb_remn.setText("剩余座位数："+String.valueOf(fs));
				d_lb_remn.setVisible(true);
				price = calprice(firp, busp, ecop);
			}
		});
		d_rd_bcla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d_lb_remn.setText("剩余座位数："+String.valueOf(bs));
				d_lb_remn.setVisible(true);
				price = calprice(firp, busp, ecop);
			}
		});
		d_rd_ecla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d_lb_remn.setText("剩余座位数："+String.valueOf(es));
				d_lb_remn.setVisible(true);
				price = calprice(firp, busp, ecop);
			}
		});
		d_cb_safi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = calprice(firp, busp, ecop);
			}
		});
		d_cb_deli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price = calprice(firp, busp, ecop);
			}
		});
	}
	public int calprice(int firp, int busp, int ecop) {
		int quan = 0, price = 0;
		if(d_rd_qua1.isSelected()) {
			quan = 1;
		}
		else if(d_rd_qua2.isSelected()) {
			quan = 2;
		}
		else if(d_rd_qua3.isSelected()) {
			quan = 3;
		}
		else {
			d_lb_pric.setText("总价格：");
			return 0;
		}
		if(d_rd_fcla.isSelected()) {
			price = quan*firp;
		}
		else if(d_rd_bcla.isSelected()) {
			price = quan*busp;
		}
		else if(d_rd_ecla.isSelected()) {
			price = quan*ecop;
		}
		else {
			d_lb_pric.setText("总价格：");
			return 0;
		}
		if(d_cb_deli.isSelected())
			price += 15 * quan;
		if(d_cb_safi.isSelected())
			price += 10 * quan;
		d_lb_pric.setText("总价格："+ String.valueOf(price));
		return price;
	}
}
