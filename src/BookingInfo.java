package src;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class BookingInfo {
	BookingInfo(Execute exe, int i){
		BookingInfoLayout window = new BookingInfoLayout(exe, i);
		window.setBounds(480,60,350,600);
		window.setTitle("订票信息");
	}
}
class BookingInfoLayout extends JFrame{
	JLabel b_lb_depl, b_lb_arrl, b_lb_flit, b_lb_actp, b_lb_dept, b_lb_arrt, b_lb_date, b_lb_acft;
	JLabel b_lb_clas, b_lb_pric, b_lb_psgn, b_lb_psgi, b_lb_arln, b_lb_bkid, b_lb_seat, b_lb_time;
	JButton b_bt_seat, b_bt_clos, b_bt_refu;
	BookingInfoLayout(Execute exe, int bkid) {
		setLayout(null);
		b_lb_depl = new JLabel("");
		b_lb_arrl = new JLabel("");
		b_lb_flit = new JLabel("");
		b_lb_actp = new JLabel("");
		b_lb_dept = new JLabel("");
		b_lb_arrt = new JLabel("");
		b_lb_date = new JLabel("");
		b_lb_acft = new JLabel("");
		b_lb_clas = new JLabel("");
		b_lb_pric = new JLabel("");
		b_lb_psgn = new JLabel("");
		b_lb_psgi = new JLabel("");
		b_lb_arln = new JLabel("");
		b_lb_bkid = new JLabel("订单编号："+String.valueOf(bkid));
		b_lb_seat = new JLabel("");
		b_lb_time = new JLabel("");
		b_bt_seat = new JButton("选座");
		b_bt_clos = new JButton("关闭");
		b_bt_refu = new JButton("退订");
		add(b_lb_depl);
		add(b_lb_arrl);
		add(b_lb_flit);
		add(b_lb_actp);
		add(b_lb_dept);
		add(b_lb_arrt);
		add(b_lb_date);
		add(b_lb_acft);
		add(b_lb_clas);
		add(b_lb_pric);
		add(b_lb_psgn);
		add(b_lb_psgi);
		add(b_lb_arln);
		add(b_lb_bkid);
		add(b_lb_seat);
		add(b_lb_time);
		add(b_bt_seat);
		add(b_bt_clos);
		add(b_bt_refu);
		b_lb_bkid.setBounds(50, 20, 250, 25);
		b_lb_date.setBounds(50, 50, 250, 25);
		b_lb_flit.setBounds(50, 80, 250, 25);
		b_lb_arln.setBounds(50, 110, 250, 25);
		b_lb_depl.setBounds(50, 140, 250, 25);
		b_lb_arrl.setBounds(50, 170, 250, 25);
		b_lb_dept.setBounds(50, 200, 250, 25);
		b_lb_arrt.setBounds(50, 230, 250, 25);
		b_lb_clas.setBounds(50, 260, 250, 25);
		b_lb_pric.setBounds(50, 290, 250, 25);
		b_lb_psgn.setBounds(50, 320, 250, 25);
		b_lb_psgi.setBounds(50, 350, 250, 25);
		b_lb_seat.setBounds(50, 380, 250, 25);
		b_lb_actp.setBounds(50, 410, 250, 25);
		b_lb_acft.setBounds(50, 440, 250, 25);
		b_lb_time.setBounds(50, 470, 250, 25);
		b_bt_seat.setBounds(55, 520, 70, 25);
		b_bt_clos.setBounds(145, 520, 70, 25);
		b_bt_refu.setBounds(235, 520, 70, 25);
		Font font = new Font("宋体", Font.PLAIN, 16);
		b_lb_depl.setFont(font);
		b_lb_arrl.setFont(font);
		b_lb_flit.setFont(font);
		b_lb_actp.setFont(font);
		b_lb_dept.setFont(font);
		b_lb_arrt.setFont(font);
		b_lb_date.setFont(font);
		b_lb_acft.setFont(font);
		b_lb_clas.setFont(font);
		b_lb_pric.setFont(font);
		b_lb_psgn.setFont(font);
		b_lb_psgi.setFont(font);
		b_lb_arln.setFont(font);
		b_lb_bkid.setFont(font);
		b_lb_time.setFont(font);
		b_lb_seat.setFont(font);
		setVisible(true);
		b_bt_seat.setEnabled(false);
		b_bt_refu.setEnabled(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exe.ExecuteQuery("select * from Airport a1, Airport a2, AirType atp, Schedule s, Flight f, Booking b "
				+ "where b.bkn_id = '"+ String.valueOf(bkid)
				+ "' and b.bkn_flight = f.lin_id and f.lin_flight = s.sch_id "
				+ "and s.sch_depl = a1.apt_IATA and s.sch_arrl = a2.apt_IATA and atp.act_id = s.sch_aircraft");
		try {
			exe.rs.next();
			b_lb_depl.setText("出发机场："+exe.rs.getString(3));
			b_lb_arrl.setText("到达机场："+exe.rs.getString(7));
			b_lb_flit.setText("　航班号："+exe.rs.getString("sch_flight"));
			b_lb_actp.setText("执飞机型："+exe.rs.getString("act_name"));
			b_lb_dept.setText("起飞时间："+exe.rs.getString("sch_dept").substring(0,5));
			b_lb_arrt.setText("落地时间："+exe.rs.getString("sch_arrt").substring(0,5));
			b_lb_date.setText("航班日期："+exe.rs.getString("lin_date"));
			b_lb_acft.setText("飞机编号："+exe.rs.getString("lin_aircraft"));
			if(exe.rs.getString("bkn_class").equals("Economy"))
				b_lb_clas.setText("选择舱位：经济舱");
			if(exe.rs.getString("bkn_class").equals("Business"))
				b_lb_clas.setText("选择舱位：商务舱");
			if(exe.rs.getString("bkn_class").equals("First Class"))
				b_lb_clas.setText("选择舱位：头等舱");
			b_lb_pric.setText("订单总价："+exe.rs.getString("bkn_price"));
			b_lb_psgn.setText("乘客姓名："+exe.rs.getString("bkn_psgrname"));
			b_lb_psgi.setText("身份证号："+exe.rs.getString("bkn_psgrid").substring(0, 8)+"******"+exe.rs.getString("bkn_psgrid").substring(14, 18));
			if(exe.rs.getString("bkn_seat") == null) {
				b_lb_seat.setText("　座位号：未选座");
				b_bt_seat.setEnabled(true);
				b_bt_refu.setEnabled(true);
			}
			else
				b_lb_seat.setText("　座位号："+exe.rs.getString("bkn_seat"));
			b_lb_time.setText("下单时间："+exe.rs.getString("bkn_ordertime").substring(0, 16));
		}
		catch(Exception ex) {}
		exe.ExecuteQuery("select cmp_name from Company where cmp_code in (select ac_company from Aircraft where ac_id in "
				+ "(select lin_aircraft from Flight where lin_id in "
				+ "(select bkn_flight from Booking where bkn_id = '"+ String.valueOf(bkid) +"')))");
		try {
			exe.rs.next();
			b_lb_arln.setText("执飞航司："+exe.rs.getString("cmp_name"));
		}
		catch(Exception ex) {}
		b_bt_clos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		b_bt_seat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_bt_seat.setEnabled(false);
				b_bt_refu.setEnabled(false);
				new SeatSelect(exe, bkid);
			}
		});
		b_bt_refu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(b_lb_clas.getText().equals("选择舱位：经济舱"))
					{
						exe.ExecuteQuery("select lin_cos from Flight where lin_id in (select bkn_flight from Booking where bkn_id = '"+ bkid +"')");
						exe.rs.next();
						int seats = Integer.valueOf(exe.rs.getString("lin_cos"));
						exe.ExecuteModify("update Flight set lin_cos = '"+String.valueOf(seats-1)+"' where lin_id in (select bkn_flight from Booking where bkn_id = '"+ bkid +"')");
					}
					if(b_lb_clas.getText().equals("选择舱位：商务舱"))
					{
						exe.ExecuteQuery("select lin_bus from Flight where lin_id in (select bkn_flight from Booking where bkn_id = '"+ bkid +"')");
						exe.rs.next();
						int seats = Integer.valueOf(exe.rs.getString("lin_bus"));
						exe.ExecuteModify("update Flight set lin_bus = '"+String.valueOf(seats-1)+"' where lin_id in (select bkn_flight from Booking where bkn_id = '"+ bkid +"')");
					}
					if(b_lb_clas.getText().equals("选择舱位：头等舱"))
					{
						exe.ExecuteQuery("select lin_fcs from Flight where lin_id in (select bkn_flight from Booking where bkn_id = '"+ bkid +"')");
						exe.rs.next();
						int seats = Integer.valueOf(exe.rs.getString("lin_fcs"));
						exe.ExecuteModify("update Flight set lin_fcs = '"+String.valueOf(seats-1)+"' where lin_id in (select bkn_flight from Booking where bkn_id = '"+ bkid +"')");
					}
					exe.ExecuteModify("delete from Booking where bkn_id = '"+ bkid +"'");
				}
				catch(Exception ex) {}
				new Msgbox("退订成功！");
				setVisible(false);
				dispose();
			}
		});
	}
}
