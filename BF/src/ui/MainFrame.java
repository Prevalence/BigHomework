package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		frame.setJMenuBar(menuBar);
		JSplitPane splitpane=new JSplitPane();
		
		
		
		paramtext=new JTextArea();
		resultText=new JTextArea();
		paramtext.setRows(5);
		resultText.setRows(5);
		splitpane.setResizeWeight(0.3);//设置两部分等分
		splitpane.setEnabled(false);
		paramtext.setText("Please input the param");
		splitpane.add(paramtext,JSplitPane.LEFT);
		splitpane.add(resultText, JSplitPane.RIGHT);
		
		

		//注册监听的时候，new open run都是作为MenuItem来传入的。
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		

		codetext = new JTextArea("Please input the code",7,15);
		codetext.setLineWrap(true);		//激活自动换行功能
		codetext.setMargin(new Insets(10, 10, 10, 10));
		codetext.setBackground(Color.WHITE);
		frame.add(codetext, BorderLayout.CENTER);
		
		
		
		frame.add(splitpane,BorderLayout.SOUTH);

		// 鏄剧ず缁撴灉
		resultLabel = new JLabel();
		resultLabel.setText("result");
		//frame.add(resultLabel, BorderLayout.SOUTH);

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
				resultLabel.setText("Opened");
				System.out.println("Open successfully.");
			} else if (cmd.equals("Save")) {
				resultLabel.setText("Saved");
			} else if (cmd.equals("Run")) {
				System.out.println("slvhosvno");
				try {
					RemoteHelper.getInstance().getExecuteService().execute(codetext.getText(), paramtext.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resultLabel.setText("Runned");
			} else if(cmd.equals("New")){
				resultLabel.setText("A new file");
			}
		}
	}
		//Save按钮应该实现的接口
	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = codetext.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
	
}
