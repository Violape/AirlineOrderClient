package src;
import javax.swing.*;
public class Msgbox {
	public Msgbox(String s) {
		MsgboxLayout window = new MsgboxLayout(s);
		window.setBounds(600,300,200,150);
		window.setTitle("提示");
	}
}

class MsgboxLayout extends JFrame{
	JLabel text;
	JButton OK;
	MsgboxLayout(String s){
		setLayout(null);
		text = new JLabel(s,JLabel.CENTER);
		add(text);
		text.setBounds(0, 20, 200, 30);
		OK = new JButton("确认");
		add(OK);
		OK.setBounds(70, 70, 60, 25);
		OK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
