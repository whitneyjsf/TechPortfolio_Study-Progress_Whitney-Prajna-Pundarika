import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManageMusicGenreForm extends JInternalFrame implements MouseListener {
    
    private JPanel mainPanel, eastPanel, westPanel, kananBawahPanel;
    private DefaultTableModel dtmheader;
    private JTable GenreTable;
    private JScrollPane scrollHeader;
    
    private JLabel genreNameLabel;
    private JTextField genreNameTextField;
    private JButton addBtn, updateBtn, deleteBtn;
    private AdminMainForm adminmainform;
    public String genrename;
    private boolean dataSelected = false;
    
    public void ClearMusicTabel() {
		dtmheader.getDataVector().removeAllElements();
	    revalidate();  	
    }
    
    public void fetchTableData() {
    	try {
			adminmainform.con.GetTableData();
			while (adminmainform.con.rs.next()) {
				Vector<String> data = new Vector<String>();
				for (int i = 1; i <= adminmainform.con.rsm.getColumnCount(); i++) {
					String str = adminmainform.con.rs.getObject(i).toString();
					data.add(str);
				}
				dtmheader.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   
    }
    
    public ManageMusicGenreForm(AdminMainForm adminmainform) {
        this.adminmainform = adminmainform;
        mainPanel = new JPanel(new GridLayout(1,2));
        eastPanel = new JPanel(new GridLayout(8, 6));
        westPanel = new JPanel();
        kananBawahPanel = new JPanel();
        
        //  TABEL KIRI
        	String header[] = {"ID", "Genre"};
        	dtmheader = new DefaultTableModel(header, 0);
        	GenreTable = new JTable(dtmheader);
        	scrollHeader = new JScrollPane(GenreTable);
        
        //  PANEL KANAN
            eastPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
            genreNameLabel = new JLabel("Genre Name");
            
            genreNameTextField = new JTextField();
            genreNameTextField.setPreferredSize(new Dimension(30, 50));

        //  PANEL KANAN BAWAH
            addBtn = new JButton("Add");
            addBtn.addMouseListener(this);
            
            updateBtn = new JButton("Update");
            updateBtn.addMouseListener(this);
            
            deleteBtn = new JButton("Delete");
            deleteBtn.addMouseListener(this);

            addBtn.setSize(50, 50);
            
        //  ADD PANEL
            mainPanel.add(westPanel);
            //west = kiri 
            //east = kanan
            mainPanel.add(eastPanel);
            this.add(westPanel, BorderLayout.WEST);
            this.add(eastPanel, BorderLayout.CENTER);
            this.add(kananBawahPanel, BorderLayout.SOUTH);
            eastPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
            westPanel.add(scrollHeader);
            eastPanel.add(genreNameLabel);
            eastPanel.add(genreNameTextField);
            eastPanel.add(kananBawahPanel);
            kananBawahPanel.add(addBtn);
            kananBawahPanel.add(updateBtn);
            kananBawahPanel.add(deleteBtn);
         
        	fetchTableData();

    //  JInternalFrame
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(950, 480);
        this.setTitle("Manage Music Genre");
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);    
        this.setResizable(false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	
    		if(e.getSource()== addBtn) {
    			if(genreNameTextField.getText().isEmpty()) {
        			JOptionPane.showMessageDialog(this, "Please input Genre Name", "Alert",
    						JOptionPane.WARNING_MESSAGE);
        	    return;
    			}	
    			genrename = genreNameTextField.getText();
				adminmainform.con.insertGenre(genrename);
				genreNameTextField.setText("");
				ClearMusicTabel();
				fetchTableData();
    		}
    		
    		else if(e.getSource()== deleteBtn) {
    			
    			if(!(GenreTable.getSelectedRowCount() == 1)) {
                    JOptionPane.showMessageDialog(this, "Please select a row", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                return;
                } else if(GenreTable.getSelectedRowCount () == 1) {
                	int row = GenreTable.getSelectedRow();
                	String value = GenreTable.getModel().getValueAt(row, 0).toString();
    					
                		if (!value.isEmpty()) {
    					dataSelected = true;
    						}
						adminmainform.con.DeleteGenre(value);
						dtmheader.removeRow(GenreTable.getSelectedRow());
						GenreTable.getSelectionModel().clearSelection();
                }
    		}
    		
    		else if (e.getSource() == updateBtn) {
    			if(!(GenreTable.getSelectedRowCount() == 1)) {
                    JOptionPane.showMessageDialog(this, "Please select a row", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                return;
    			} else if(genreNameTextField.getText().isEmpty()) {
    				JOptionPane.showMessageDialog(this, "Please input Genre Name", "Alert",
                            JOptionPane.WARNING_MESSAGE);
    			} else if(GenreTable.getSelectedRowCount () == 1) {
    						String AlterGenre;
    		    			int row = GenreTable.getSelectedRow();
    		    			String valueId = GenreTable.getModel().getValueAt(row, 0).toString();
    						
    						if (!valueId.isEmpty()) {
    		    				dataSelected = true;
    						}
    						AlterGenre = genreNameTextField.getText();
    						
    						adminmainform.con.UpdateGenre(AlterGenre, valueId);
    						GenreTable.getSelectionModel().clearSelection();
    						
    						ClearMusicTabel();
    						fetchTableData();

    				}
    		}
    		
    		
    }
    		
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}