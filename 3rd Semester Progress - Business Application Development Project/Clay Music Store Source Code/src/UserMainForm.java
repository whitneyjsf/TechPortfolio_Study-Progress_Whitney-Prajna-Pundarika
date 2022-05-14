import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
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
import javax.swing.event.MouseInputListener;

public class UserMainForm extends JFrame implements ActionListener{

	private JDesktopPane desktopPane;
    private JMenuBar menuBar; 
    private JMenu menuUser, menuStore;
    private JMenuItem logOff, buymusic, history; 
    private History historyView;
    private Login login;
    public static String UserId= Login.UserId;
    
    private BuyMusicForm buymusicform;
    public Connect con = new Connect();
   

    public UserMainForm(String UserId) {

    	desktopPane = new JDesktopPane();
    	desktopPane.setBackground(Color.DARK_GRAY);
    	setContentPane(desktopPane);
    	this.UserId = UserId;
    	
        menuBar = new JMenuBar();
        
        menuUser = new JMenu("User");
        menuBar.add(menuUser);

        logOff = new JMenuItem("Log Off");
        menuUser.add(logOff);
        
        menuStore = new JMenu("Store");
        menuBar.add(menuStore);
        
        buymusic = new JMenuItem("Buy Music");
        menuStore.add(buymusic);
        
        history = new JMenuItem("History");
        menuStore.add(history);
        
        //add ActionListener
        history.addActionListener(this);
        buymusic.addActionListener(this);
        logOff.addActionListener(this);
        
        
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        this.setSize(970,530);
        this.setResizable(false);
        this.setTitle("Clay's Music Store");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
       
        new UserMainForm(UserId);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==history) {
			
		historyView = new History(this);
		historyView.setVisible(true);
		desktopPane.add(historyView);
		
		} else if(e.getSource()== logOff) {
			dispose();
			login = new Login();
			setVisible(false);
			
		}else if(e.getSource()==buymusic) {
			
		buymusicform = new BuyMusicForm(this);
		buymusicform.setVisible(true);
		desktopPane.add(buymusicform);
		} 
		
		// YANG INI NYUSUL KARENA HARUS DARI AWAL KE AKHIR Coding nya!
	}

	

}