package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainFrame {
	MainFrame(Execute database){
		MainLayout window = new MainLayout(database);
		window.setBounds(280,60,800,600);
		window.setTitle("机票预订管理系统");
	}
}

class MainLayout extends JFrame{
	JButton m_bt_sear, m_bt_note, m_bt_flit, m_bt_user, m_bt_quit;
	JLabel m_lb_topb, m_lb_dwnb;
	SearchFlight m_pn_sear;
	Usercenter m_pn_user;
	public MainLayout(Execute exe)
	{
		setLayout(null);
		m_lb_topb = new JLabel(new ImageIcon("D:/Eclipse/workspace/AirportMgr/img/titlebar.png"));
		m_lb_dwnb = new JLabel(new ImageIcon("D:/Eclipse/workspace/AirportMgr/img/downbar.png"));
		m_bt_sear = new JButton("航班查询");
		m_bt_note = new JButton("个人中心");
		m_bt_flit = new JButton("航班管理");
		m_bt_user = new JButton("用户管理");
		m_bt_quit = new JButton("退出系统");
		m_pn_sear = new SearchFlight(exe);
		m_pn_user = new Usercenter(exe);
		add(m_bt_sear);
		add(m_bt_note);
		add(m_pn_sear);
		add(m_pn_user);
		add(m_bt_flit);
		add(m_bt_user);
		add(m_bt_quit);
		add(m_lb_topb);
		add(m_lb_dwnb);
		m_bt_flit.setEnabled(false);
		m_bt_user.setEnabled(false);
		m_bt_sear.setBounds(20, 160, 100, 30);
		m_bt_sear.setFont(new Font("幼圆",Font.BOLD, 16));
		m_bt_sear.setBackground(Color.black);
		m_bt_note.setBounds(20, 230, 100, 30);
		m_bt_note.setFont(new Font("幼圆",Font.BOLD, 16));
		m_bt_note.setBackground(Color.black);
		m_bt_flit.setBounds(20, 300, 100, 30);
		m_bt_flit.setFont(new Font("幼圆",Font.BOLD, 16));
		m_bt_flit.setBackground(Color.black);
		m_bt_user.setBounds(20, 370, 100, 30);
		m_bt_user.setFont(new Font("幼圆",Font.BOLD, 16));
		m_bt_user.setBackground(Color.black);
		m_bt_quit.setBounds(20, 440, 100, 30);
		m_bt_quit.setFont(new Font("幼圆",Font.BOLD, 16));
		m_bt_quit.setBackground(Color.black);
		m_pn_sear.setBounds(140, 100, 660, 460);
		m_pn_user.setBounds(140, 100, 660, 460);
		m_lb_topb.setBounds(0, 0, 800, 100);
		m_lb_dwnb.setBounds(0, 0, 140, 700);
		m_bt_sear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_pn_user.setVisible(false);
				m_pn_sear.setVisible(true);
			}
		});
		m_bt_note.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_pn_sear.setVisible(false);
				m_pn_user.update(exe);
				m_pn_user.setVisible(true);
			}
		});
		m_bt_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		m_pn_sear.setVisible(false);
		m_pn_user.setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class SearchFlight extends JPanel{
	JLabel s_lb_dep, s_lb_arr, s_lb_tbl[][];
	JComboBox<String> s_cb_depp, s_cb_depa, s_cb_arrp, s_cb_arra, s_cb_date;
	JButton s_bt_sear, s_bt_info[], s_bt_prev, s_bt_next, s_bt_obdt, s_bt_obpc;
	String title[] = new String[] {"航班号","起飞时间","到达时间","头等舱票价","商务舱票价","经济舱票价"};
	String curdepa, curarra, curdate, order = "sch_dept";
	int width[] = new int[] {20, 120, 200, 280, 370, 460, 550};
	int fids[] = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0};
	int index = 0;
	public SearchFlight(Execute exe) {
		setLayout(null);
		s_lb_dep = new JLabel("选择出发地：");
		s_lb_arr = new JLabel("选择到达地：");
		s_lb_tbl = new JLabel[13][6];
		s_bt_info = new JButton[13];
		s_cb_depp = new JComboBox<String>();
		s_cb_depa = new JComboBox<String>();
		s_cb_arrp = new JComboBox<String>();
		s_cb_arra = new JComboBox<String>();
		s_cb_date = new JComboBox<String>();
		s_bt_sear = new JButton("查询航班");
		s_bt_prev = new JButton("上一页");
		s_bt_next = new JButton("下一页");
		s_bt_obdt = new JButton("按出发时间排序");
		s_bt_obpc = new JButton("按价格排序");
		for(int i=0; i<13; i++) {
			for(int j=0; j<6; j++)
			{
				s_lb_tbl[i][j] = new JLabel(String.valueOf(i+j),JLabel.CENTER);
				add(s_lb_tbl[i][j]);
				s_lb_tbl[i][j].setBounds(width[j], 90+25*i, width[j+1]-width[j], 25);
				s_lb_tbl[i][j].setVisible(false);
			}
			s_bt_info[i] = new JButton("订票");
			add(s_bt_info[i]);
			s_bt_info[i].setBounds(560, 90+25*i, 60, 25);
			s_bt_info[i].setVisible(false);
		}
		for(int j=0;j<6;j++)
			s_lb_tbl[0][j].setText(title[j]);
		add(s_lb_dep);
		add(s_lb_arr);
		add(s_cb_depp);
		add(s_cb_depa);
		add(s_cb_arrp);
		add(s_cb_arra);
		add(s_cb_date);
		add(s_bt_sear);
		add(s_bt_prev);
		add(s_bt_next);
		add(s_bt_obdt);
		add(s_bt_obpc);
		s_lb_dep.setBounds(20, 20, 90, 25);
		s_lb_arr.setBounds(20, 50, 90, 25);
		s_cb_depp.setBounds(120, 20, 100, 25);
		s_cb_depa.setBounds(240, 20, 200, 25);
		s_cb_arrp.setBounds(120, 50, 100, 25);
		s_cb_arra.setBounds(240, 50, 200, 25);
		s_cb_date.setBounds(460, 20, 150, 25);
		s_bt_sear.setBounds(460, 50, 150, 25);
		s_bt_prev.setBounds(430, 425, 80, 25);
		s_bt_next.setBounds(520, 425, 80, 25);
		s_bt_obdt.setBounds(50, 425, 140, 25);
		s_bt_obpc.setBounds(200, 425, 140, 25);
		s_cb_depp.addItem("不限省份");
		s_cb_arrp.addItem("不限省份");
		exe.ExecuteQuery("select distinct apt_province from Airport");
		try {
			while(exe.rs.next()) {
				String prov = exe.rs.getString("apt_province");
				s_cb_depp.addItem(prov);
				s_cb_arrp.addItem(prov);
			}
		}
		catch (Exception ex) {}
		exe.ExecuteQuery("select apt_name from Airport");
		try {
			while(exe.rs.next()) {
				String prov = exe.rs.getString("apt_name");
				s_cb_depa.addItem(prov);
				s_cb_arra.addItem(prov);
			}
		}
		catch (Exception ex) {}
		exe.ExecuteQuery("select distinct lin_date from Flight order by lin_date asc");
		try {
			while(exe.rs.next()) {
				String date = exe.rs.getString("lin_date");
				s_cb_date.addItem(date);
			}
		}
		catch (Exception ex) {}
		s_cb_depp.setSelectedItem(0);
		s_cb_arrp.setSelectedItem(0);
		s_cb_depa.setSelectedItem(null);
		s_cb_arra.setSelectedItem(null);
		s_cb_date.setSelectedItem(null);
		s_cb_depp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s_cb_depa.removeAllItems();
				String curprov = (String)(s_cb_depp.getSelectedItem());
				if(curprov == "任意省份") exe.ExecuteQuery("select apt_name from Airport");
				else exe.ExecuteQuery("select apt_name from Airport where apt_province = '"+curprov+"'");
				try {
					while(exe.rs.next()) {
						String prov = exe.rs.getString("apt_name");
						s_cb_depa.addItem(prov);
					}
				}
				catch (Exception ex) {}
				s_cb_depa.setSelectedItem(null);
			}
		});
		s_cb_arrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s_cb_arra.removeAllItems();
				String curprov = (String)(s_cb_arrp.getSelectedItem());
				if(curprov == "任意省份") exe.ExecuteQuery("select apt_name from Airport");
				else exe.ExecuteQuery("select apt_name from Airport where apt_province = '"+curprov+"'");
				try {
					while(exe.rs.next()) {
						String prov = exe.rs.getString("apt_name");
						s_cb_arra.addItem(prov);
					}
				}
				catch (Exception ex) {}
				s_cb_arra.setSelectedItem(null);
			}
		});
		s_bt_sear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				curdepa = (String)(s_cb_depa.getSelectedItem());
				if(curdepa == null){
					new Msgbox("请选择出发机场！");
					return;
					}
				curarra = (String)(s_cb_arra.getSelectedItem());
				if(curarra == null) {
					new Msgbox("请选择到达机场！");
					return;
				}
				curdate = (String)(s_cb_date.getSelectedItem());
				if(curdate == null) {
					new Msgbox("请选择起飞日期！");
					return;
				}
				index = 0;
				update(exe, 0, curdepa, curarra, curdate, order);
			}
		});
		for(int i=1;i<13;i++)
			s_bt_info[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JButton button=(JButton)e.getSource();
					for(int i=1;i<13;i++)
						if(s_bt_info[i]==button)
							new FlightDetail(String.valueOf(fids[i]), exe);
				}
			});
		s_bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				update(exe, index, curdepa, curarra, curdate, order);
			}
		});
		s_bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				update(exe, index, curdepa, curarra, curdate, order);
			}
		});
		s_bt_obdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order = "sch_dept";
				index = 0;
				update(exe, index, curdepa, curarra, curdate, order);
			}
		});
		s_bt_obpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order = "lin_cop";
				index = 0;
				update(exe, index, curdepa, curarra, curdate, order);
			}
		});
	}
	public void update(Execute exe, int index, String curdepa, String curarra, String curdate, String order) {
		for(int i=1; i<13; i++) {
			for(int j=0; j<6; j++)
				s_lb_tbl[i][j].setVisible(false);
			s_bt_info[i].setVisible(false);
			fids[i]=0;
		}
		exe.ExecuteQuery("select f.lin_id, s.sch_flight, s.sch_dept, s.sch_arrt, f.lin_fcp, f.lin_bup, f.lin_cop " + 
				"from Schedule s, Flight f " + 
				"where f.lin_flight = s.sch_id and s.sch_depl " + 
				"in (select apt_IATA from Airport where apt_name='"+ curdepa +"') " + 
				"and s.sch_arrl in (select apt_IATA from Airport where apt_name='"+ curarra +"') " + 
				"and f.lin_date = '"+ curdate + "' order by "+order+" asc");
		try {
			s_bt_prev.setEnabled(false);
			s_bt_next.setEnabled(false);
			for(int j=0; j<6; j++) {
				s_lb_tbl[0][j].setVisible(true);
			}
			for(int i=1; i<13; i++) {
				if(exe.rs.absolute(index * 12 + i)) {
					fids[i] = Integer.valueOf(exe.rs.getString(1));
					for(int j=0; j<6; j++) {
						s_lb_tbl[i][j].setText(exe.rs.getString(j+2));
						s_lb_tbl[i][j].setVisible(true);
					}
					s_lb_tbl[i][1].setText(s_lb_tbl[i][1].getText().substring(0, 5));
					s_lb_tbl[i][2].setText(s_lb_tbl[i][2].getText().substring(0, 5));
					s_bt_info[i].setVisible(true);
				}
				else break;
			}
			if(index > 0)
				s_bt_prev.setEnabled(true);
			if(exe.rs.absolute(index * 12 + 13))
				s_bt_next.setEnabled(true);
		}	
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

class Usercenter extends JPanel{
	JLabel u_lb_name, u_lb_tabl[][];
	JButton u_bt_info[];
	JButton u_bt_edit;
	String title[] = new String[] {"日期","航班号","起飞","到达","起飞机场","到达机场","座位号","乘客","票价"};
	int width[] = new int[] {0, 70, 120, 160, 200, 300, 400, 450, 500, 550, 600};
	int bkids[] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public Usercenter(Execute exe) {
		setLayout(null);
		u_lb_name = new JLabel("欢迎 " + exe.username + " 登陆机票预订管理系统！", JLabel.LEFT);
		u_lb_tabl = new JLabel[16][9];
		u_bt_info = new JButton[16];
		for(int i=0; i<16; i++) {
			for(int j=0; j<9; j++)
			{
				u_lb_tabl[i][j] = new JLabel("",JLabel.CENTER);
				add(u_lb_tabl[i][j]);
				u_lb_tabl[i][j].setBounds(width[j], 50+24*i, width[j+1]-width[j], 24);
				u_lb_tabl[i][j].setVisible(false);
			}
			u_bt_info[i] = new JButton("预订详情");
			add(u_bt_info[i]);
			u_bt_info[i].setBounds(560, 50+24*i, 80, 24);
			u_bt_info[i].setVisible(false);
		}
		for(int j=0; j<9; j++) {
			u_lb_tabl[0][j].setText(title[j]);
			u_lb_tabl[0][j].setVisible(true);
		}
		u_bt_edit = new JButton("修改个人资料");
		add(u_lb_name);
		add(u_bt_edit);
		u_lb_name.setBounds(50,20,350,25);
		u_bt_edit.setBounds(460,20,150,25);
		u_lb_name.setFont(new Font("宋体",Font.PLAIN, 14));
		update(exe);
		u_bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Useredit(exe);
			}
		});
		for(int i=1;i<16;i++)
			u_bt_info[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JButton button=(JButton)e.getSource();
					for(int i=1;i<16;i++)
						if(u_bt_info[i]==button)
							new BookingInfo(exe, bkids[i]);
				}
		});
	}
	
	public void update(Execute exe) {
		for(int i=1; i<16; i++) {
			for(int j=0; j<9; j++)
				u_lb_tabl[i][j].setVisible(false);
			u_bt_info[i].setVisible(false);
			bkids[i]=0;
		}
		try {
			exe.ExecuteQuery("select f.lin_date, s.sch_flight, s.sch_dept, s.sch_arrt, a1.apt_name, "
					+ "a2.apt_name, b.bkn_seat, b.bkn_psgrname, b.bkn_price, b.bkn_id "
					+ "from Airport a1, Airport a2, Schedule s, Flight f, Booking b "
					+ "where b.bkn_user = '"+exe.username
					+ "' and b.bkn_flight = f.lin_id and f.lin_flight = s.sch_id "
					+ "and s.sch_depl = a1.apt_IATA and s.sch_arrl = a2.apt_IATA");
			for(int i=1; i<16; i++){
				if (exe.rs.next()) {
					for(int j=0; j<9; j++)
						u_lb_tabl[i][j].setText(exe.rs.getString(j+1));
					u_lb_tabl[i][2].setText(u_lb_tabl[i][2].getText().substring(0, 5));
					u_lb_tabl[i][3].setText(u_lb_tabl[i][3].getText().substring(0, 5));
					u_lb_tabl[i][6].setForeground(Color.black);
					if(u_lb_tabl[i][6].getText() == null) {
						u_lb_tabl[i][6].setText("未选");
						u_lb_tabl[i][6].setForeground(Color.red);
					}
					bkids[i] = Integer.valueOf(exe.rs.getString(10));
					for(int j=0; j<9; j++)
						u_lb_tabl[i][j].setVisible(true);
					u_bt_info[i].setVisible(true);
				}
				else break;
			}
		}
		catch(Exception e) {}
	}
}
