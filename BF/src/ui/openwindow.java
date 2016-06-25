package ui;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ui.versionlist.version2ActionListener;
import ui.versionlist.versionActionListener;
public class openwindow extends JFrame{
	JList<String> list;
	JButton b;
	JButton b2;
	JScrollPane jsp;
	JPanel p;
	public openwindow(){
		list=new JList<String>();
		list.setSize(500, 200);
		list.setFont(new Font("Dialog",0,20));
		b=new JButton("确定");
		b.setSize(100, 20);
		b2=new JButton("取消");
		b2.setSize(100, 20);
		p=new JPanel();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listfile();
		
		
		p.add(b);
		p.add(b2);
		
		b.addActionListener(new versionActionListener());
		b2.addActionListener(new version2ActionListener());
		
		
		this.add(list,BorderLayout.CENTER);
		this.add(p,BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
		
		public void listfile(){
			File f=new File("d:/IOExample/"+MainFrame.getuser());
			String[] filelist=f.list();
			list.setListData(filelist);
			
		}
		
		
		class versionActionListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String content=list.getSelectedValue();//filename version
				File f=new File("d:/IOExample/"+MainFrame.getuser()+"/"+content);
				String[] list=f.list();
				String newestfile=list[list.length-1];
				File f2=new File("d:/IOExample/"+MainFrame.getuser()+"/"+content+"/"+newestfile);
				System.out.println(f2.exists());
				String code=null;
				try {
					FileReader fr=new FileReader(f2);
					BufferedReader bfr=new BufferedReader(fr);
					code=bfr.readLine();
					System.out.println(code);
					MainFrame.setcode(code);
					MainFrame.setfilename(content);
					bfr.close();
					dispose();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
			}

		}
		
		
		class version2ActionListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		}
}
