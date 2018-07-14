package src;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class SeatSelect {
	SeatSelect(Execute exe, int i){
		SeatSelectLayout window = new SeatSelectLayout(exe, i);
		window.setBounds(480,260,400,200);
		window.setTitle("座位选择");
	}
}
class SeatSelectLayout extends JFrame{
	JLabel s_lb_actp, s_lb_warn;
	JComboBox<String> s_cb_rows, s_cb_seat;
	JButton s_bt_sele, s_bt_clos;
	int seat[];
	SeatSelectLayout(Execute exe, int bkid) {
		setLayout(null);
		s_lb_actp = new JLabel("");
		s_lb_warn = new JLabel("注意：选座后将无法退票！");
		s_cb_rows = new JComboBox<String>();
		s_cb_seat = new JComboBox<String>();
		s_bt_sele = new JButton("确认");
		s_bt_clos = new JButton("取消");
		seat = new int[81];
		exe.ExecuteQuery("select * from Airtype where act_id in " + 
				"(select sch_aircraft from Schedule where sch_id in " + 
				"(select lin_flight from Flight where lin_id in " + 
				"(select bkn_flight from Booking where bkn_id = '"+String.valueOf(bkid)+"')))");
		try {
			exe.rs.next();
			s_lb_actp.setText("航班所用机型："+exe.rs.getString("act_name"));
			for(int i=1;i<81;i++)
				if(i<6||i>10)
					seat[i] = Integer.valueOf(exe.rs.getString("act_row"+String.valueOf(i)));
				else
					seat[i] = 0;
		}
		catch (Exception ex) {}
		exe.ExecuteQuery("select bkn_class from Booking where bkn_id = '"+String.valueOf(bkid)+"'");
		try {
			exe.rs.next();
			String clas = exe.rs.getString("bkn_class");
			if(clas.equals("First Class"))
				for(int i=1; i<11; i++)
					if(seat[i] > 0)
						s_cb_rows.addItem("第0"+String.valueOf(i)+"排");
			if(clas.equals("Business"))
				for(int i=11; i<31; i++)
					if(seat[i] > 0)
						s_cb_rows.addItem("第"+String.valueOf(i)+"排");
			if(clas.equals("Economy"))
				for(int i=31; i<81; i++)
					if(seat[i] > 0)
						s_cb_rows.addItem("第"+String.valueOf(i)+"排");
		}
		catch(Exception ex) {}
		add(s_lb_actp);
		add(s_lb_warn);
		add(s_cb_rows);
		add(s_cb_seat);
		add(s_bt_sele);
		add(s_bt_clos);
		s_lb_actp.setBounds(40, 20, 300, 25);
		s_lb_warn.setBounds(100, 120, 200, 25);
		s_cb_rows.setBounds(40, 50, 140, 25);
		s_cb_seat.setBounds(200, 50, 140, 25);
		s_bt_sele.setBounds(100, 90, 80, 25);
		s_bt_clos.setBounds(200, 90, 80, 25);
		s_lb_actp.setFont(new Font("宋体", Font.PLAIN, 14));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s_cb_rows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s_cb_seat.removeAllItems();
				String s = s_cb_rows.getSelectedItem().toString().substring(1,3);
				exe.ExecuteQuery("select * from Seatdist where sd_id = '"+String.valueOf(seat[Integer.valueOf(s)])+"'");
				try {
					exe.rs.next();
					if(exe.rs.getBoolean("sd_A")) s_cb_seat.addItem("A(靠窗座位)");
					if(exe.rs.getBoolean("sd_B")) s_cb_seat.addItem("B");
					if(exe.rs.getBoolean("sd_C")) s_cb_seat.addItem("C(靠过道座位)");
					if(exe.rs.getBoolean("sd_D")) s_cb_seat.addItem("D(靠过道座位)");
					if(exe.rs.getBoolean("sd_E")) s_cb_seat.addItem("E");
					if(exe.rs.getBoolean("sd_F")) s_cb_seat.addItem("F");
					if(exe.rs.getBoolean("sd_G")) s_cb_seat.addItem("G");
					if(exe.rs.getBoolean("sd_H")) s_cb_seat.addItem("H(靠过道座位)");
					if(exe.rs.getBoolean("sd_J")) s_cb_seat.addItem("J(靠过道座位)");
					if(exe.rs.getBoolean("sd_K")) s_cb_seat.addItem("K");
					if(exe.rs.getBoolean("sd_L")) s_cb_seat.addItem("L(靠窗座位)");
				}
				catch (Exception ex) {}
			}
		});
		s_bt_clos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		s_bt_sele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seatpos = s_cb_rows.getSelectedItem().toString().substring(1,3)+s_cb_seat.getSelectedItem().toString().substring(0,1);
				exe.ExecuteQuery("select bkn_id from Booking where bkn_flight in (select bkn_flight from Booking where bkn_id = '"
						+ String.valueOf(bkid) + "') and bkn_seat = '" + seatpos + "'");
				try {
					if(exe.rs.next())
						new Msgbox("座位已被占用！");
					else {
						exe.ExecuteModify("update Booking set bkn_seat = '"+ seatpos + "' where bkn_id = '"+String.valueOf(bkid)+"'");
						new Msgbox("座位已选定！");
						setVisible(false);
						dispose();
					}
				}
				catch(Exception ex) {}
			}
		});
	}
}
