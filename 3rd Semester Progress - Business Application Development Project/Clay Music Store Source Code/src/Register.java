import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class Register extends JFrame implements ActionListener{
	
	private JPanel mainPanel, southPanel, centerPanel, northPanel, genderPanel;
	private JLabel title, usernamelbl, emaillbl, passwordlbl, cpasswordlbl, genderlbl;
	private JTextField usernametxt, emailtxt;
	private JPasswordField passwordfield, cpasswordfield;
	private ButtonGroup gendergroup;
	private JRadioButton malebtn, femalebtn;
	private JButton register, ihaveacc;
	private Login login;
	private String  gender, username, email, password, role;
	private Connect con;
	
	public void makePanel() {
		//MAIN PANEL
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		//NORTH PANEL
		northPanel = new JPanel();
		northPanel.setBackground(Color.DARK_GRAY);
		
		//CENTER PANEL
		centerPanel = new JPanel(new GridLayout(6,2));
		centerPanel.setBackground(Color.DARK_GRAY);
		centerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		//SOUTH PANEL
		southPanel = new JPanel();
		southPanel.setBackground(Color.DARK_GRAY);
		southPanel.setBorder(new EmptyBorder(10, 10, 50, 10));
	}
	
	public void makeDesigns() {
		//JUDUL REGISTER
		title = new JLabel("REGISTER");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Dialog", Font.BOLD, 30));
		northPanel.add(title);
		
		// ISI
		//USERNAME
		usernamelbl = new JLabel("Username");
		usernamelbl.setForeground(Color.WHITE);
		usernamelbl.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		usernametxt = new JTextField();
		
		emaillbl = new JLabel("Email");
		emaillbl.setForeground(Color.WHITE);
		emaillbl.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		emailtxt = new JTextField();
		
		passwordlbl = new JLabel("Password");
		passwordlbl.setForeground(Color.WHITE);
		passwordlbl.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		passwordfield = new JPasswordField();
		
		cpasswordlbl = new JLabel("Confirm Password");
		cpasswordlbl.setForeground(Color.WHITE);
		cpasswordlbl.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		cpasswordfield = new JPasswordField();
		
		genderlbl = new JLabel("Gender");
		genderlbl.setForeground(Color.WHITE);
		genderlbl.setFont(new Font("Dialog", Font.PLAIN, 20));

		malebtn = new JRadioButton("Male");
		malebtn.setForeground(Color.WHITE);
		malebtn.setBackground(Color.DARK_GRAY);
		malebtn.setFont(new Font("Dialog", Font.BOLD, 15));
		
		femalebtn = new JRadioButton("Female");
		femalebtn.setForeground(Color.WHITE);
		femalebtn.setBackground(Color.DARK_GRAY);
		femalebtn.setFont(new Font("Dialog", Font.BOLD, 16));
		
		gendergroup = new ButtonGroup();
		gendergroup.add(malebtn);
		gendergroup.add(femalebtn);
		
		genderPanel = new JPanel();
		genderPanel.setBackground(Color.DARK_GRAY);
		genderPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		genderPanel.add(malebtn);
		genderPanel.add(femalebtn);
	}
	
	public void addDesignsAndButtonToPanel() {
		centerPanel.add(usernamelbl);
		centerPanel.add(usernametxt);
		centerPanel.add(emaillbl);
		centerPanel.add(emailtxt);
		centerPanel.add(passwordlbl);
		centerPanel.add(passwordfield);
		centerPanel.add(cpasswordlbl);
		centerPanel.add(cpasswordfield);
		centerPanel.add(genderlbl);
		centerPanel.add(genderPanel);
	}
	
	public void makeButtons(){
		//BUTTON
		register = new JButton("Register");
	    register.setBackground(Color.PINK);
	    register.setPreferredSize(new Dimension(200,50));

	    ihaveacc = new JButton("I Have an account");
	    ihaveacc.setBackground(Color.PINK);
	    ihaveacc.setPreferredSize(new Dimension(200,50));

	    southPanel.add(register);
	    southPanel.add(ihaveacc);
	}
	
	public void addAllToMainPanel() {
		//ADD TO
		add(mainPanel);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}
	
	public void addActionListener() {
		
		register.addActionListener(this);
		ihaveacc.addActionListener(this);
		
	}
	public Register() {
	con = new Connect();
	makePanel();
	
	makeDesigns();
	
	makeButtons();
	
	addDesignsAndButtonToPanel();
	
	addAllToMainPanel();
	
	addActionListener();
	
	setVisible(true);
	setSize(700, 600);
	setTitle("Clay's Music Store");
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
	new Register();

	}
	
	public static boolean validateEmail(String input) {
		String emailFormat =  "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailFormat, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(input);
		return matcher.find();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(femalebtn.isSelected()) {
			gender = "female";
		}
		if(malebtn.isSelected()) {
			gender = "male";
		}
		
		if(e.getSource()==register) {
			if(passwordfield.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(this, "Password Cannot be Empty", "Alert",
							JOptionPane.WARNING_MESSAGE);
					
				}else if(cpasswordfield.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Confirmation password cannot be empty", "Alert",
							JOptionPane.WARNING_MESSAGE);
					
				}else if(!(passwordfield.getText().equals(cpasswordfield.getText()))) {
					 JOptionPane.showMessageDialog(this, "Password must be same with the confirmation password", "Alert",
								JOptionPane.WARNING_MESSAGE);
				}else if(usernametxt.getText().isEmpty()) {
					 JOptionPane.showMessageDialog(this, "Username cannot be empty", "Alert",
								JOptionPane.WARNING_MESSAGE);
				}else if(validateEmail(emailtxt.getText()) == false){
					JOptionPane.showMessageDialog(this, "Please input a valid email", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}else if(!(malebtn.isSelected() || femalebtn.isSelected())) {
					JOptionPane.showMessageDialog(this, "Please select Male or Female", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}else {
				
					
				//isi ke database	
					
				role = "2";
				username = usernametxt.getText();
				//Data gender sudah di tarik ketika memilih gender pada halaman Register 
				password = passwordfield.getText();
				email = emailtxt.getText();

				con.insertUser(username, email, password, gender, role);
				login= new Login();
				setVisible(false);
				}
			}
		
		if(e.getSource()==ihaveacc) {
			login= new Login();
			setVisible(false);
		}
	}}
		


