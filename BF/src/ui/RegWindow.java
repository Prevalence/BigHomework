package ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.*;

import rmi.RemoteHelper;

public class RegWindow extends JFrame {
	JLabel account;
	JLabel password;
	JTextField t1;
	JTextField t2;
	JButton b1;
	JButton b2;
	JOptionPane JOptionPane;
	public RegWindow(){
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
		setTitle("注册");
		setVisible(true);
		setLocationRelativeTo(null);
		}	
		class okActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.out.println(t1.getText());
				System.out.println(t2.getText());
				try {
					RemoteHelper.getInstance().getUserService().registration(t1.getText(),t2.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane=new JOptionPane();
				JOptionPane.showMessageDialog(null,"注册成功！");
				dispose();
			}
		
	}
	

}
