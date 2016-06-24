package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

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
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import rmi.RemoteHelper;


public class MainFrame extends JFrame {
	private JTextArea codetext;
	private JLabel resultLabel;
	private JTextArea paramtext;
	private JTextArea resultText;
	static String user=null;
	public static String filename;
	String version="0";
	
	
	
	public static void setuser(String username){
		user=username;
	}
	public static void setfilename(String Filename){
		filename=Filename;
	}
	
	
	public MainFrame() {
		// 鍒涘缓绐椾綋
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu accountMenu=new JMenu("Account");
		JMenu versionMenu=new JMenu("Version");
		menuBar.add(accountMenu);
		menuBar.add(fileMenu);
		menuBar.add(versionMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenu saveMenu = new JMenu("Save");
		fileMenu.add(saveMenu);
		JMenuItem saveversion=new JMenuItem("save as a version");
		saveMenu.add(saveversion);
		JMenuItem savefile=new JMenuItem("save as a file");
		saveMenu.add(savefile);
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
		JMenuItem nextversion=new JMenuItem("Next Version");
		versionMenu.add(nextversion);
		JMenuItem lastversion=new JMenuItem("Last Version");
		versionMenu.add(lastversion);
		JMenuItem listversion=new JMenuItem("Version List");
		versionMenu.add(listversion);
		frame.setJMenuBar(menuBar);
		JSplitPane splitpane=new JSplitPane();
		
		
		
		
		
		
		paramtext=new JTextArea(5,25);
		resultText=new JTextArea(5,25);
		splitpane.setResizeWeight(0.5);//设置两部分等分
		splitpane.setEnabled(false);
		JPanel leftpane=new JPanel();
		JPanel rightpane=new JPanel();
		JLabel paramlb=new JLabel ("param");
		JLabel resultlb=new JLabel("result");
		leftpane.add(BorderLayout.WEST,paramlb);
		leftpane.add(paramtext);
		splitpane.add(leftpane,JSplitPane.LEFT);
		rightpane.add(BorderLayout.WEST,resultlb);
		rightpane.add(resultText);
		splitpane.add(rightpane, JSplitPane.RIGHT);
		frame.add(splitpane,BorderLayout.SOUTH);
	
		
		
		
		

		//注册监听的时候，new open run都是作为MenuItem来传入的。
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		savefile.addActionListener(new SaveActionListener());
		saveversion.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		loginMenuItem.addActionListener(new MenuItem2ActionListener());
		logoutMenuItem.addActionListener(new MenuItem2ActionListener());
		signMenuItem.addActionListener(new MenuItem2ActionListener());
		showMenuItem.addActionListener(new MenuItem2ActionListener());
		lastversion.addActionListener(new MenuItem3ActionListener());
		nextversion.addActionListener(new MenuItem3ActionListener());
		listversion.addActionListener(new MenuItem3ActionListener());

		codetext = new JTextArea("Please input the code",7,16);
		codetext.setLineWrap(true);		//激活自动换行功能
		codetext.setMargin(new Insets(10, 10, 10, 10));
		codetext.setBackground(Color.WHITE);
		frame.add(codetext, BorderLayout.CENTER);
		
		
		
		

		// 鏄剧ず缁撴灉
		//resultLabel = new JLabel();
		//resultLabel.setText("result");
		//frame.add(resultLabel,BorderLayout.NORTH);
		
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 700);
		frame.setLocationRelativeTo(null);
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
					String re=RemoteHelper.getInstance().getIOService().readFile(user,version);
					codetext.setText(re);
					resultLabel.setText("Opened");
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
				if(user!=null)
					new filenaming();
				else{
					JOptionPane a=new JOptionPane();
					a.showMessageDialog(null, "尚未登录", "",JOptionPane.ERROR_MESSAGE);
				}
					
			}
			
		}
	}
	
	
	class MenuItem2ActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String cmd=e.getActionCommand();
			 if(cmd.equals("Login")){
				new LoginWindow();
			}
			 if(cmd.equals("Logout")){
				user=null;
				resultLabel.setText("Logged out!");
			}
			
			else if(cmd.equals("Registration")){
					new RegWindow();
				
			}
			else if(cmd.equals("ShowAccount")){
				if(user!=null)
					resultLabel.setText("username: "+user);
				else
					resultLabel.setText("Haven't logged in");
			}
			
		}
	}
	
	
	
	class MenuItem3ActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String cmd=e.getActionCommand();
			if(cmd.equals("Last Version")){
				try {
					version=RemoteHelper.getInstance().getUserService().lastversion(version);
					String re=RemoteHelper.getInstance().getIOService().readFile(user,version);
					if(re.equals("Sorry,this version don't existed.")){
						resultLabel.setText("Sorry,this version don't existed.");
					}
					else{
					codetext.setText(re);
					resultLabel.setText("Now is version "+Integer.valueOf(version));
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("Next Version")){
				try {
					version=RemoteHelper.getInstance().getUserService().lastversion(version);
					String re=RemoteHelper.getInstance().getIOService().readFile(user,version);
					if(re.equals("Sorry,this version don't existed.")){
						resultLabel.setText("Sorry,this version don't existed.");
					}
					else{
					codetext.setText(re);
					resultLabel.setText("Now is version "+Integer.valueOf(version));
					}

				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(cmd.equals("Version List")){
				new versionlist();
			}
		}
	}
	
	
		//Save按钮应该实现的接口
	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = codetext.getText();
			String cmd=e.getActionCommand();
			if(user!=null){
			try {
				if(cmd.equals("save as a file")){
				RemoteHelper.getInstance().getIOService().writeFile(code, user, filename+"0");
				}
				if(cmd.equals("save as a version"))//版本数字上升，需要知道当前版本的maximum
					RemoteHelper.getInstance().getIOService().writeFile(code, user, filename+"0");
				version=String.valueOf(Integer.valueOf(version)+1);
				
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			}
			else
				resultLabel.setText("Please log in");
		}

	}
	
}
