package src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Useredit {
	public Useredit(Execute exe){
		Usereditlayout window = new Usereditlayout(exe);
		window.setBounds(580,230,300,250);
		window.setTitle("修改用户信息");
	}
}

class Usereditlayout extends JFrame{
	JLabel e_lb_name, e_lb_opsw, e_lb_npsw, e_lb_phon, e_lb_grup;
	JTextField e_tf_name, e_tf_phon;
	JPasswordField e_tf_opsw, e_tf_npsw;
	JComboBox e_cb_grup;
	JButton e_bt_okok, e_bt_clos;
	String opswd = null;
	public Usereditlayout(Execute exe){
		setLayout(null);
		e_bt_okok = new JButton("确认");
		e_bt_clos = new JButton("取消");
		e_lb_name = new JLabel("用户名：", JLabel.LEFT);
		e_lb_opsw = new JLabel("旧密码：", JLabel.LEFT);
		e_lb_npsw = new JLabel("新密码：", JLabel.LEFT);
		e_lb_phon = new JLabel("手机：", JLabel.LEFT);
		e_lb_grup = new JLabel("用户组：", JLabel.LEFT);
		e_tf_name = new JTextField();
		e_tf_opsw = new JPasswordField();
		e_tf_npsw = new JPasswordField();
		e_tf_phon = new JTextField();
		e_cb_grup = new JComboBox<String>();
		e_cb_grup.addItem("管理员");
		e_cb_grup.addItem("企业用户");
		e_cb_grup.addItem("个人用户");
		add(e_bt_okok);
		add(e_bt_clos);
		add(e_lb_name);
		add(e_lb_opsw);
		add(e_lb_npsw);
		add(e_lb_phon);
		add(e_lb_grup);
		add(e_tf_name);
		add(e_tf_opsw);
		add(e_tf_npsw);
		add(e_tf_phon);
		add(e_cb_grup);
		e_bt_okok.setBounds(40, 180, 100, 25);
		e_bt_clos.setBounds(160, 180, 100, 25);
		e_lb_name.setBounds(20, 20, 100, 25);
		e_lb_opsw.setBounds(20, 50, 100, 25);
		e_lb_npsw.setBounds(20, 80, 100, 25);
		e_lb_phon.setBounds(20, 110, 100, 25);
		e_lb_grup.setBounds(20, 140, 100, 25);
		e_tf_name.setBounds(140, 20, 140, 25);
		e_tf_opsw.setBounds(140, 50, 140, 25);
		e_tf_npsw.setBounds(140, 80, 140, 25);
		e_tf_phon.setBounds(140, 110, 140, 25);
		e_cb_grup.setBounds(140, 140, 140, 25);
		exe.ExecuteQuery("select * from Userlist where usr_id = '"+ exe.username +"'");
		try {
			exe.rs.next();
			e_tf_name.setText(exe.username);
			opswd = exe.rs.getString("usr_psw");
			e_tf_phon.setText(exe.rs.getString("usr_phone"));
			String grp = exe.rs.getString("usr_group");
			if(grp.equals("Individual"))
				e_cb_grup.setSelectedIndex(2);
			else if(grp.equals("Enterprise"))
				e_cb_grup.setSelectedIndex(1);
			else if(grp.equals("Administrator"))
				e_cb_grup.setSelectedIndex(0);
		}
		catch(Exception ex) {}
		e_tf_name.setEditable(false);
		e_cb_grup.setEnabled(false);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		e_bt_okok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pswd = String.valueOf(e_tf_opsw.getPassword());
				if(!pswd.equals(opswd)) {
					new Msgbox("原密码不正确！");
					return;
				}
				pswd = String.valueOf(e_tf_npsw.getPassword());
				if(pswd.equals("")) {
					new Msgbox("未输入新密码！");
					return;
				}
				String phon = e_tf_phon.getText();
				if(phon.equals(""))
					phon = "NULL";
				else if(phon.length()!=11) {
					new Msgbox("手机号格式错误！");
					return;
				}
				exe.ExecuteModify("update Userlist set usr_psw = '"+pswd+"', usr_phone = '"+phon+"' where usr_id = '"+exe.username+"'");
				new Msgbox("修改成功！");
				setVisible(false);
				dispose();
			}
		});
		e_bt_clos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
}
