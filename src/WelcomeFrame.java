package src;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
public class WelcomeFrame {
	public static void main(String[] args) {
		WelcomeLayout window = new WelcomeLayout();
		window.setBounds(280,60,800,600);
		window.setTitle("机票预订管理系统");
	}
}

class WelcomeLayout extends JFrame{
	JRadioButton w_rd_log, w_rd_reg;
	ButtonGroup  w_bg_grp;
	JLabel w_lb_grp, w_lb_usr, w_lb_psw, w_lb_pho;
	JTextField w_tf_usr, w_tf_pho;
	JPasswordField w_tf_psw;
	JComboBox<String> w_cb_grp;
	JButton w_bt_log, w_bt_reg;
	public WelcomeLayout() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setBg();
		setLayout(null);
		w_bg_grp = new ButtonGroup();
		w_rd_log = new JRadioButton("登录");
		w_rd_reg = new JRadioButton("注册");
		w_cb_grp = new JComboBox<String>();
		w_lb_grp = new JLabel("用户组：",JLabel.RIGHT);
		w_lb_usr = new JLabel("用户名：",JLabel.RIGHT);
		w_lb_psw = new JLabel("密码：",JLabel.RIGHT);
		w_lb_pho = new JLabel("手机号：",JLabel.RIGHT);
		w_tf_usr = new JTextField("");
		w_tf_psw = new JPasswordField("");
		w_tf_pho = new JTextField("");
		w_bt_log = new JButton("登录");
		w_bt_reg = new JButton("注册");
		w_bg_grp.add(w_rd_log);
		w_bg_grp.add(w_rd_reg); 
		w_cb_grp.addItem("管理员");
		w_cb_grp.addItem("企业用户");
		w_cb_grp.addItem("个人用户");
		w_cb_grp.setSelectedItem(null);
		add(w_rd_log);
		add(w_rd_reg);
		add(w_lb_grp);
		add(w_cb_grp);
		add(w_lb_usr);
		add(w_tf_usr);
		add(w_lb_psw);
		add(w_tf_psw);
		add(w_lb_pho);
		add(w_tf_pho);
		add(w_bt_log);
		add(w_bt_reg);
		w_rd_log.setBounds(300,250,100,50);
		w_rd_reg.setBounds(440,250,100,50);
		w_lb_grp.setBounds(280,310,75,25);
		w_lb_usr.setBounds(280,350,75,25);
		w_lb_psw.setBounds(280,390,75,25);
		w_lb_pho.setBounds(280,310,75,25);
		w_cb_grp.setBounds(380,310,140,25);
		w_tf_usr.setBounds(380,350,140,25);
		w_tf_psw.setBounds(380,390,140,25);
		w_tf_pho.setBounds(380,310,140,25);
		w_bt_log.setBounds(340,440,120,35);
		w_bt_reg.setBounds(340,440,120,35);
		w_rd_log.setFont(new Font("幼圆",Font.BOLD, 18));
		w_rd_log.setForeground(Color.white);
		w_rd_log.setOpaque(false);
		w_rd_reg.setFont(new Font("幼圆",Font.BOLD, 18));
		w_rd_reg.setForeground(Color.white);
		w_rd_reg.setOpaque(false);
		w_lb_grp.setFont(new Font("幼圆",Font.BOLD, 16));
		w_lb_grp.setForeground(Color.white);
		w_lb_grp.setOpaque(false);
		w_lb_usr.setFont(new Font("幼圆",Font.BOLD, 16));
		w_lb_usr.setForeground(Color.white);
		w_lb_usr.setOpaque(false);
		w_lb_psw.setFont(new Font("幼圆",Font.BOLD, 16));
		w_lb_psw.setForeground(Color.white);
		w_lb_psw.setOpaque(false);
		w_lb_pho.setFont(new Font("幼圆",Font.BOLD, 16));
		w_lb_pho.setForeground(Color.white);
		w_lb_pho.setOpaque(false);
		w_cb_grp.setFont(new Font("幼圆",Font.BOLD, 14));
		w_tf_usr.setFont(new Font("幼圆",Font.BOLD, 14));
		w_tf_usr.setForeground(Color.white);
		w_tf_usr.setOpaque(false);
		w_tf_psw.setFont(new Font("Arial",Font.BOLD, 14));
		w_tf_psw.setForeground(Color.white);
		w_tf_psw.setOpaque(false);
		w_tf_pho.setFont(new Font("幼圆",Font.BOLD, 14));
		w_tf_pho.setForeground(Color.white);
		w_tf_pho.setOpaque(false);
		w_bt_log.setFont(new Font("幼圆",Font.BOLD, 18));
		w_bt_log.setBackground(Color.black);
		w_bt_reg.setFont(new Font("幼圆",Font.BOLD, 18));
		w_bt_reg.setBackground(Color.black);
		w_rd_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w_tf_usr.setText("");
				w_tf_psw.setText("");
				w_cb_grp.setSelectedItem(null);
				w_lb_grp.setVisible(true);
				w_cb_grp.setVisible(true);
				w_lb_pho.setVisible(false);
				w_tf_pho.setVisible(false);
				w_bt_log.setVisible(true);
				w_bt_reg.setVisible(false);
			}
		});
		w_rd_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w_tf_usr.setText("");
				w_tf_psw.setText("");
				w_tf_pho.setText("");
				w_lb_grp.setVisible(false);
				w_cb_grp.setVisible(false);
				w_lb_pho.setVisible(true);
				w_tf_pho.setVisible(true);
				w_bt_log.setVisible(false);
				w_bt_reg.setVisible(true);
			}
		});
		w_bt_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = w_tf_usr.getText();
				String password = String.valueOf(w_tf_psw.getPassword());
				String group;
				//检验用户名是否正确输入
				if(username == null || username.length()==0) {
					new Msgbox("请输入用户名！");
					return;
				}
				//检验密码是否正确输入
				else if(password == null||password.length()==0) {
					new Msgbox("请输入密码！");
					return;
				}
				int index = w_cb_grp.getSelectedIndex();
				if(index == 0) group = "Administrator";
				else if(index == 1) group = "Enterprise";
				else group = "Individual";
				Execute database = new Execute(username, group);
				database.Database(username, password, group);
				if(database.con!=null) {
					setVisible(false);
					dispose();
					new MainFrame(database);
				}
			}
		});
		w_bt_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = w_tf_usr.getText();
				String password = String.valueOf(w_tf_psw.getPassword());
				String phonenbr = w_tf_pho.getText();
				if(phonenbr.length()!=11)
				{
					new Msgbox("手机号格式错误，请重新输入！");
					return;
				}
				Execute database = new Execute(username, "Individual");
				database.Register(username, password, phonenbr);
			}
		});
		setVisible(true);
		w_lb_pho.setVisible(false);
		w_tf_pho.setVisible(false);
		w_bt_log.setVisible(false);
		w_bt_reg.setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setBg(){ 
		((JPanel)this.getContentPane()).setOpaque(false); 
		ImageIcon img = new ImageIcon("D:/Eclipse/workspace/AirportMgr/img/title.png"); 
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
	}
}
