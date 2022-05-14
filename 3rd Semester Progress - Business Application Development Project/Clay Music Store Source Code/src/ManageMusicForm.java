import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ManageMusicForm extends JInternalFrame implements MouseListener {

    private JTable musicTbl;
    private JInternalFrame internalF;
    private JPanel leftPan, rightPan, rightRightPan;
    private DefaultTableModel dtmMusic;
    private JScrollPane scrollMusic;
    private JLabel mName, mGenre, aName, price;
    private JTextField mNameTex, aNameTex;
    private JSpinner priceSpin;
    private JComboBox<String> mGenreBox;
    private JButton addBtn, updateBtn, deleteBtn;
    private AdminMainForm adminmainform;

    private String musicname, genreSelected, artistName, 
    				priceTobeInsert, genreId,SelectedRowId;
    
    private boolean dataSelected = false; 
    public ManageMusicForm(AdminMainForm adminmainform) {
    	this.adminmainform = adminmainform;
        internalF = new JInternalFrame();
        leftPan = new JPanel();
        rightRightPan = new JPanel();
        rightPan = new JPanel(new GridLayout(11,4));
        

        // TABEL MUSIC
        String music[] = {"ID", "Name", "Genre", "Price", "Artist Name", "Release Date"};
        dtmMusic = new DefaultTableModel(music, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        musicTbl = new JTable(dtmMusic);
        musicTbl.addMouseListener(this);
        scrollMusic = new JScrollPane(musicTbl);

        //label
        mName = new JLabel("Music Name");
        mGenre = new JLabel("Genre");
        aName = new JLabel("Artist Name");
        price = new JLabel("Price");

        //text
        mNameTex = new JTextField();
        aNameTex = new JTextField();

        //combo
        mGenreBox = new JComboBox();

        //spinner
        priceSpin = new JSpinner();

        //BUTTON ADD
        addBtn = new JButton("Add");
        
        addBtn.setSize(50, 50);


        //BUTTON Delete
        deleteBtn = new JButton("Delete");
        
        
        //BUTTON update
        updateBtn = new JButton("Update");
       

        //ADD PANEL
        internalF.add(leftPan);
        internalF.add(rightPan);
        internalF.add(rightRightPan);
        this.add(leftPan, BorderLayout.WEST);
        this.add(rightPan, BorderLayout.CENTER);
        this.add(rightRightPan, BorderLayout.SOUTH);
        
        rightPan.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        leftPan.add(scrollMusic);
        rightPan.add(mName);
        rightPan.add(mNameTex);
        rightPan.add(mGenre);
        rightPan.add(mGenreBox);
        rightPan.add(aName);
        rightPan.add(aNameTex);
        rightPan.add(price);
        rightPan.add(priceSpin);
        rightPan.add(rightRightPan);
        rightRightPan.add(addBtn);
        rightRightPan.add(updateBtn);
        rightRightPan.add(deleteBtn);
        
        //add Mouse Listener
        addBtn.addMouseListener(this);
        deleteBtn.addMouseListener(this);
        updateBtn.addMouseListener(this);
        
        // Menarik Data pada Database untuk melihat Music yang ada
        getDataMusic();
        
        // Mengupdate JComboBox untuk opsi genre agar sesuai dengan Database music_genres
        updateGenreComboBox();
        
        //JFRAME
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(950, 480);
        this.setTitle("Manage Music");
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);    
        this.setResizable(false);
    }
    
    
    public void ClearMusicTabel() {
    		dtmMusic.getDataVector().removeAllElements();
    	    revalidate();  	
    }
    
    
    public void updateGenreComboBox() {
    	adminmainform.con.GetGenreComboxData();
    	try {
			while(adminmainform.con.rs.next()) {
				mGenreBox.addItem(adminmainform.con.rs.getString("genre_name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void getDataMusic() {
		try {
			adminmainform.con.SelectGetMusicData();
			while (adminmainform.con.rs.next()) {
				Vector<String> Tabledata = new Vector<String>();
				for (int i = 1; i <= adminmainform.con.rsm.getColumnCount(); i++) {
					String str = adminmainform.con.rs.getObject(i).toString();
					Tabledata.add(str);
				}
				dtmMusic.addRow(Tabledata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource()== addBtn) {
	        if(mNameTex.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please input Music Name!", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        }else if(aNameTex.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please input Artist Name!", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        }else if(priceSpin.getValue().equals(0)) {
	            JOptionPane.showMessageDialog(this, "Please input Price!", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        }else if(aNameTex.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please input Artist Name", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        	}else {
					try {
						// get music name
						musicname = mNameTex.getText();
						// get temporary genre name
						genreSelected = (String) mGenreBox.getSelectedItem();
						// get artist name
						artistName = aNameTex.getText();
						// get price
						priceTobeInsert = priceSpin.getValue().toString();
						//get release date
						LocalDate releaseDateMusic = LocalDate.now();	
						//get Genre ID
						String query = "SELECT id FROM music_genres WHERE genre_name ='"+genreSelected+"'";
						adminmainform.con.rs = adminmainform.con.stat.executeQuery(query);
						adminmainform.con.rs.next();
						genreId = adminmainform.con.rs.getString("id");
						
						//Insert new Music Data to the DATABASE
						adminmainform.con.insertMusics(genreId, musicname, priceTobeInsert, artistName, releaseDateMusic.toString());
						
						musicTbl.clearSelection();
						ClearMusicTabel();
						getDataMusic();
						mNameTex.setText("");
						aNameTex.setText("");
						priceSpin.setValue(0);
						} catch (Exception n) {
						n.printStackTrace();
						}			
					}
			}
		if(e.getSource()== updateBtn) {
			if(!(musicTbl.getSelectedRowCount() == 1)) {
	            JOptionPane.showMessageDialog(this, "Please select a row", "Message",
	                    JOptionPane.INFORMATION_MESSAGE);
	        return;
	        }else if(mNameTex.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please input Music Name!", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        }else if(aNameTex.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please input Artist Name!", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        }else if(priceSpin.getValue().equals(0)) {
	            JOptionPane.showMessageDialog(this, "Please input Price!", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        }else if(aNameTex.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Please input Artist Name", "Alert",
	                    JOptionPane.WARNING_MESSAGE);
	        return;
	        	}else if(musicTbl.getSelectedRowCount()==1) {
			
					//get genreId
					try {
						int row = musicTbl.getSelectedRow();
						SelectedRowId = musicTbl.getModel().getValueAt(row, 0).toString();
					//get music name
					String MusicName = mNameTex.getText();
					// get temporary genre name
					String genreChoosen = (String) mGenreBox.getSelectedItem();
					// get artist name
					String ArtistName = aNameTex.getText();
					// get price
					String MusicPrice = priceSpin.getValue().toString();
				

					String query = "SELECT id FROM music_genres WHERE genre_name ='"+genreChoosen+"'";
					adminmainform.con.rs = adminmainform.con.stat.executeQuery(query);
					adminmainform.con.rs.next();
					genreId = adminmainform.con.rs.getString("id");
						
					adminmainform.con.UpdateMusic(genreId, MusicName, MusicPrice, ArtistName, SelectedRowId);
					//Reset the Form
					mNameTex.setText("");
					aNameTex.setText("");
					priceSpin.setValue(0);
					//Refresh the tabel
					ClearMusicTabel();
					getDataMusic();
					adminmainform.con.rs.next();
				} catch (SQLException error) {
					error.printStackTrace();
				}
	        }
		}
		
		if(e.getSource()== deleteBtn) {

				        if(musicTbl.getSelectedRowCount()==0) {
				            JOptionPane.showMessageDialog(this, "Please Select a row!", "Message",
				            		JOptionPane.INFORMATION_MESSAGE);
				        return;
				        }else if(musicTbl.getSelectedRowCount()==1) {
				        	int row = musicTbl.getSelectedRow();
							String value = musicTbl.getModel().getValueAt(row, 0).toString();
							
							if (!value.isEmpty()) {
								dataSelected = true;
									}
							
							adminmainform.con.DeleteMusics(value);
							musicTbl.getSelectionModel().clearSelection();
							ClearMusicTabel();
							getDataMusic();
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
		
	}}

