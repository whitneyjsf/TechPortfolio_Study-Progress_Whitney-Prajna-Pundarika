import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminMainForm extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JMenu menuUser, menuManage;
	private JMenuItem logOffItem, manageMusicGenreItem, manageMusicItem; 
	private ManageMusicGenreForm managemusicgenreform;
	private ManageMusicForm managemusicform;
	private Login login;
	private JDesktopPane desktopPane;
	public Connect con = new Connect();
	
	
	public AdminMainForm() {
		
		desktopPane = new JDesktopPane();
    	desktopPane.setBackground(Color.DARK_GRAY);
    	setContentPane(desktopPane);
    	
    	mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(mainPanel);
		
		
		//make menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		//MENU USER
		//make JMenu
		menuUser = new JMenu("User");
		menuBar.add(menuUser);
		
		//make the menu items
		logOffItem = new JMenuItem("Log Off");
		menuUser.add(logOffItem);
		
		
		//MENU MANAGE
		//make JMenu
		menuManage = new JMenu("Manage");
		menuBar.add(menuManage);
		
		///make the menu items
		manageMusicGenreItem = new JMenuItem("Manage Music Genre");
		menuManage.add(manageMusicGenreItem);
		
		manageMusicItem = new JMenuItem("Manage Music");
		menuManage.add(manageMusicItem);
		
		//add Action listener
		manageMusicGenreItem.addActionListener(this);
		manageMusicItem.addActionListener(this);
		logOffItem.addActionListener(this);
		
		
		this.setVisible(true);
		this.setSize(970,530);
		this.setTitle("Clay's Music Store");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminMainForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		if(e.getSource()== logOffItem) {
			this.dispose();
			login = new Login();
			setVisible(false);
		
		}else if(e.getSource()== manageMusicItem) {
			
			managemusicform = new ManageMusicForm(this);
			managemusicform.setVisible(true);
			desktopPane.add(managemusicform);
			
		}else if(e.getSource()== manageMusicGenreItem) {
			
			managemusicgenreform = new ManageMusicGenreForm(this);
			managemusicgenreform.setVisible(true);
			desktopPane.add(managemusicgenreform);
			
		}
		
	}

}
