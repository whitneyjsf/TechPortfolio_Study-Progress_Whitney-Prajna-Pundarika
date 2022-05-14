import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener{

	private JPanel 	mainPanel, southPanel,centerPanel,northPanel, centerWestPanel, centerEastPanel;
	private JLabel loginLbl, emailLbl, passwordLbl;
	private JTextField emailTxt ;
	private JPasswordField passwordTxt;
	private JButton loginBtn, iDontHaveAccBtn;
	private Register register;
	private UserMainForm usermainform;
	private AdminMainForm adminmainform;
	private Connect con = new Connect();
	private boolean AccountIsFound = false;
	
	public static String UserId, username, role, password;
	
	public Login() {
		// TODO Auto-generated constructor stub
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		northPanel = new JPanel();
		northPanel.setBackground(Color.DARK_GRAY);
		centerPanel = new JPanel(new GridLayout(2,2));
		centerPanel.setBackground(Color.DARK_GRAY);
		centerPanel.setBorder(new EmptyBorder(25, 25, 25, 50));
		southPanel = new JPanel();
		southPanel.setBackground(Color.DARK_GRAY);

		
		//northpanel
		loginLbl = new JLabel("LOGIN");
		loginLbl.setForeground(Color.WHITE);
		loginLbl.setFont(new Font("Dialog",Font.BOLD,30));
		northPanel.add(loginLbl);
		
		//centerpanel
		
//		centerWestPanel
			emailLbl = new JLabel("Email");
			emailLbl.setForeground(Color.WHITE);
			emailLbl.setFont(new Font("Dialog",Font.PLAIN,20));
			
			emailTxt = new JTextField("");
			emailTxt.setPreferredSize(new Dimension (20, 10));
			
			passwordLbl = new JLabel("Password");
			passwordLbl.setForeground(Color.WHITE);
			passwordLbl.setFont(new Font("Dialog",Font.PLAIN,20));
			
			passwordTxt = new JPasswordField("");
			passwordTxt.setPreferredSize(new Dimension (50, 10));
			
			
			centerPanel.add(emailLbl);
			centerPanel.add(emailTxt);
			centerPanel.add(passwordLbl);
			centerPanel.add(passwordTxt);
		
		//southpanel
		
			loginBtn = new JButton("Login");
			loginBtn.setBackground(Color.PINK);
			loginBtn.setPreferredSize(new Dimension(200,50));
			
			iDontHaveAccBtn = new JButton("I Don't Have an Account");
			iDontHaveAccBtn.setBackground(Color.PINK);
			iDontHaveAccBtn.setPreferredSize(new Dimension(200,50));
		
			southPanel.add(loginBtn);
			southPanel.add(iDontHaveAccBtn);
			
			//add ActionListener
			loginBtn.addActionListener(this);
			iDontHaveAccBtn.addActionListener(this);
			
		
		//mainpanel
		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		add(mainPanel);
		
        setVisible(true);
        pack();
        setSize(600,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		setTitle("Clay's Music Store");
		setResizable(false);
	
	}

	public static void main(String[] args) {
		
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()== iDontHaveAccBtn) {
			setVisible(false);
			register = new Register();
		}else if(e.getSource()== loginBtn) {
			
			if (emailTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please input email", "Alert",
						JOptionPane.WARNING_MESSAGE);
				
			}else if(passwordTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please input password", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}else{
			
				String emailTmp = emailTxt.getText();
				String passTmp = passwordTxt.getText();
				con.rs = con.pStatLoginCheckAccount(emailTmp, passTmp);
				try {
				while(con.rs.next()) {
				
					username = con.rs.getString("username");
					UserId = con.rs.getString("id");
					role = con.rs.getString("role");
					password = con.rs.getString("password");

				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(username==null && password==null){
					JOptionPane.showMessageDialog(this, "Incorrect Email or Password", "Alert",
							JOptionPane.WARNING_MESSAGE);
					AccountIsFound = false;
				}else {
					JOptionPane.showMessageDialog(this, "Welcome, " + username, "Message", JOptionPane.INFORMATION_MESSAGE);
					AccountIsFound = true;
				if(role.equals("2")) {
					usermainform = new UserMainForm(UserId);
					usermainform.setVisible(true);
					setVisible(false);
				}else if(role.equals("1")){
					adminmainform = new AdminMainForm();
					setVisible(false);
				}

		}}
		
		
		}
	}}
