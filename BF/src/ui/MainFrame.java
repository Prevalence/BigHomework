package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import rmi.RemoteHelper;


public class MainFrame extends JFrame {
	private JTextArea codetext;
	private JLabel resultLabel;
	private JTextArea paramtext;
	private JTextArea resultText;

	public MainFrame() {
		// 鍒涘缓绐椾綋
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu accountMenu=new JMenu("Account");
		menuBar.add(accountMenu);
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		JMenuItem loginMenuItem = new JMenuItem("Login");
		accountMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		accountMenu.add(logoutMenuItem);
		JMenuItem signMenuItem = new JMenuItem("Registration");
		accountMenu.add(signMenuItem);
		JMenuItem showMenuItem = new JMenuItem("ShowAccount");
		accountMenu.add(showMenuItem);
		frame.setJMenuBar(menuBar);
		JSplitPane splitpane=new JSplitPane();
		JButton b=new JButton("确定");
		
		
		
		paramtext=new JTextArea(2,15);
		resultText=new JTextArea(2,15);
		splitpane.setResizeWeight(0.5);//设置两部分等分
		splitpane.setEnabled(false);
		JPanel leftpane=new JPanel();
		JPanel rightpane=new JPanel();
		JLabel paramlb=new JLabel ("param");
		JLabel resultlb=new JLabel("result");
		leftpane.add(BorderLayout.NORTH,paramlb);
		leftpane.add(paramtext);
		splitpane.add(leftpane,JSplitPane.LEFT);
		rightpane.add(BorderLayout.NORTH,resultlb);
		rightpane.add(resultText);
		splitpane.add(rightpane, JSplitPane.RIGHT);
		
	
		
		
		
		

		//注册监听的时候，new open run都是作为MenuItem来传入的。
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		loginMenuItem.addActionListener(new MenuItem2ActionListener());
		logoutMenuItem.addActionListener(new MenuItem2ActionListener());
		signMenuItem.addActionListener(new MenuItem2ActionListener());
		

		codetext = new JTextArea("Please input the code",7,16);
		codetext.setLineWrap(true);		//激活自动换行功能
		codetext.setMargin(new Insets(10, 10, 10, 10));
		codetext.setBackground(Color.WHITE);
		frame.add(codetext, BorderLayout.CENTER);
		
		
		
		frame.add(splitpane,BorderLayout.SOUTH);

		// 鏄剧ず缁撴灉
		resultLabel = new JLabel();
		resultLabel.setText("result");
		frame.add(resultLabel,BorderLayout.NORTH);
		//frame.add(b, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 瀛愯彍鍗曞搷搴斾簨浠�
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				try {
					resultLabel.setText("Opened");
					String re=RemoteHelper.getInstance().getIOService().readFile("300", "05");
					codetext.setText(re);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (cmd.equals("Save")) {
				resultLabel.setText("Saved");
			} else if (cmd.equals("Run")) {
				try {
					String re=RemoteHelper.getInstance().getExecuteService().execute(codetext.getText(), paramtext.getText());
					System.out.println(re);
					resultText.setText(re);
					resultLabel.setText("Runned");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else if(cmd.equals("New")){
				resultLabel.setText("A new file");
			}
			
		}
	}
	
	
	class MenuItem2ActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String cmd=e.getActionCommand();
			String user=null;
			 if(cmd.equals("Login")){
				String regi=codetext.getText();
				String u1=regi.split("\n")[0];
				String p1=regi.split("\n")[1];
				try {
					boolean flag=RemoteHelper.getInstance().getUserService().login(u1, p1);
					if(flag==true)
						user=u1;
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("Logout")){
				user=null;
			}
			else if(cmd.equals("Registration")){
				System.out.println("regiing");
				String regi=codetext.getText();
				try {
					RemoteHelper.getInstance().getUserService().registration(regi.split("\n")[0], regi.split("\n")[1]);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("ShowAccount")){
				System.out.println("showing");
				//if(user!=null)
					//codetext.setText("username: "+user);
				//else
				//	codetext.setText("Haven't logged in");
			}
		}
	}
	
	
	
		//Save按钮应该实现的接口
	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = codetext.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "300", "05");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
	
}
