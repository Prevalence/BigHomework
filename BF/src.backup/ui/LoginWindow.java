package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rmi.RemoteHelper;
import ui.RegWindow.okActionListener;

public class LoginWindow extends JFrame{
	JLabel account;
	JLabel password;
	JTextField t1;
	JTextField t2;
	JButton b1;
	JButton b2;
	JOptionPane JOptionPane;
	String accountstr;
	String passwordstr;
	public LoginWindow(){
		account=new JLabel("Account");
		password=new JLabel("Password");
		t1=new JTextField(16);
		t2=new JTextField(16);
		b1=new JButton("确定");
		b2=new JButton("取消");
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		p1.add(account);
		p1.add(t1);
		p2.add(password);
		p2.add(t2);
		p3.add(b1);
		p3.add(b2);
		
		
		b1.addActionListener(new okActionListener());
		//b2.addActionListener(new cancelActionListener());
		
		add(p1);
		add(p2);
		add(p3);
		setLayout(new GridLayout(3,1));
		setSize(400,200);
		setTitle("登录");
		setVisible(true);
		setLocationRelativeTo(null);
		}
	
	
		class okActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				JOptionPane=new JOptionPane();
				try {
					boolean flag=RemoteHelper.getInstance().getUserService().login(t1.getText(),t2.getText());
					if(flag){
						JOptionPane.showMessageDialog(null,"登录成功�?");
						MainFrame.setuser(t1.getText());
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "账户或密码错�?", "",JOptionPane.ERROR_MESSAGE);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
	}
		public String getinput(){
			return t1.getText();
		}

}
