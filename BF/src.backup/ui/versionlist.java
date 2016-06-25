package ui;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class versionlist extends JFrame{
	JList list;
	JButton b;
	JScrollPane jsp;
	public void versionlist(){
		list=new JList();
		b=new JButton();
		jsp=new JScrollPane();
		jsp.add(list);
		JPanel p1=new JPanel();
		p1.add(b);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		this.add(jsp);
		this.add(p1);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void listfile(){
		File f=new File("d:/IOExample/"+MainFrame.getuser()+"/"+MainFrame.getfilename());
		String[] filelist=f.list();
		for(int i;i<filelist.length;i++)
		
	}
}
