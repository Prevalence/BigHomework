package ui;

import java.awt.BorderLayout;
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

public class filenaming extends JFrame{
	JLabel l;
	JTextField t;
	JPanel p;
	JPanel p2;
	JButton b;
	
	public filenaming(){
		l=new JLabel("File Name: ");
		t=new JTextField(20);
		p=new JPanel();
		b=new JButton("确定");
		p2=new JPanel();
		b.setSize(20, 20);
		p.add(l);
		p.add(t);
		this.add(p,BorderLayout.NORTH);
		p2.add(b);
		this.add(p2);
		b.addActionListener(new acActionListener());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 150);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	
	
	class acActionListener implements ActionListener{
  public void actionPerformed(ActionEvent e){
	  try {
		RemoteHelper.getInstance().getIOService().writeFile(MainFrame.getcode(), MainFrame.getuser(), t.getText()+"_0");
	} catch (RemoteException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  JOptionPane.showMessageDialog(null,"创建成功");
	  MainFrame.setfilename(t.getText());
	  dispose();
  }
}
}
