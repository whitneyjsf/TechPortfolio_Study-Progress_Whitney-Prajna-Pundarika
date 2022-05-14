import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BuyMusicForm extends JInternalFrame implements MouseListener{

    private JTable musictbl, carttbl;
    private JPanel leftpan, rightpan, botpan, mainpan;
    private DefaultTableModel dtmmusic, dtmcart;
    private JScrollPane scrollmusic, scrollcart;
    private JButton addbtn, removebtn, buybtn;
	private JSpinner qtySpinner;
	private boolean musicSelected = false;
	private boolean cartSelected = false;
	private Vector<String> carttemp = new Vector<String>();
	
	public Login login;
    public UserMainForm usermainform;
    public Connect con = new Connect();
    private String  musicId, cartId,cartMusicId, historyId;
    private int cartTotalPrice;
    private LocalDate cartDatePurchase = LocalDate.now();
    public BuyMusicForm(UserMainForm usermainform) {
    	this.usermainform = usermainform;
    	
    leftpan = new JPanel();
    rightpan = new JPanel();
    botpan = new JPanel(new GridLayout(2,2));
    
    // TABEL MUSIC    
    String music[] = {"ID", "Name", "Genre", "Price", "Artist Name", "Release Date"};
    dtmmusic = new DefaultTableModel(music, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    musictbl = new JTable(dtmmusic);
    musictbl.addMouseListener(this);
    getData();
    scrollmusic = new JScrollPane(musictbl);
    
    //TABEL KANAN
    String cart[] = {"ID", "Name", "Genre", "Price", "Artist Name", "Release Date"};
    dtmcart = new DefaultTableModel(cart, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    carttbl = new JTable(dtmcart);
    scrollcart = new JScrollPane(carttbl);
    
    //BUTTON
    addbtn = new JButton("Add To Cart");
    addbtn.addMouseListener(this);
    
    removebtn = new JButton("Remove From Cart");
    removebtn.addMouseListener(this);
    
    buybtn = new JButton("Buy");
    buybtn.addMouseListener(this);

        //ADD PANEL
        this.add(leftpan, BorderLayout.WEST);
        this.add(rightpan, BorderLayout.EAST);
        this.add(botpan, BorderLayout.SOUTH);
        botpan.setBorder(new EmptyBorder(10, 5, 10, 5));
        leftpan.add(scrollmusic);
        rightpan.add(scrollcart);
        botpan.add(addbtn);
        botpan.add(removebtn);
        botpan.add(buybtn);
        
        //JFRAME
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(950, 480);
        this.setTitle("Buy Music");
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);    
        this.setResizable(false);    
        
    }

    public void getData() {
		try {
			usermainform.con.GetMusicData();
			while (usermainform.con.rs.next()) {
				Vector<String> data = new Vector<String>();
				for (int i = 1; i <= usermainform.con.rsm.getColumnCount(); i++) {
					String str = usermainform.con.rs.getObject(i).toString();
					data.add(str);
				}
				dtmmusic.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == addbtn) {
			if(!(musictbl.getSelectedRowCount() == 1)) {
    			JOptionPane.showMessageDialog(this, "Please Select Any Music", "Message",
						JOptionPane.INFORMATION_MESSAGE);
    	    return;
		} else if(musictbl.getSelectedRowCount () == 1) {
			int row = musictbl.getSelectedRow();
			String value = musictbl.getModel().getValueAt(row, 0).toString();
			
			try {
				String musicId = musictbl.getModel().getValueAt(row, 0).toString();
				String musicName = musictbl.getModel().getValueAt(row, 1).toString();
				String musicGenre = musictbl.getModel().getValueAt(row, 2).toString();
				String musicPrice = musictbl.getModel().getValueAt(row, 3).toString();
				String artistName = musictbl.getModel().getValueAt(row, 4).toString();;
				String releaseDate = musictbl.getModel().getValueAt(row, 5).toString();
				
				carttemp = new Vector<String>();
				carttemp.add(musicId);
				carttemp.add(musicName);
				carttemp.add(musicGenre);
				carttemp.add(musicPrice);
				carttemp.add(artistName);
				carttemp.add(releaseDate);
				
				dtmcart.addRow(carttemp);
				carttbl.setModel(dtmcart);
				carttbl.getSelectionModel().clearSelection();
				
			}catch (Exception e2) {
				e2.printStackTrace();
				}

			}
	
		}else if(e.getSource()== removebtn) {
			if(!(carttbl.getSelectedRowCount() == 1)) {
				JOptionPane.showMessageDialog(null, "Please Select Any Music From Cart", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
			return;
			} else if(carttbl.getSelectedRowCount() == 1) {
				int row = musictbl.getSelectedRow();
				String value = musictbl.getModel().getValueAt(row, 0).toString();
			
				if (!value.isEmpty()) {
					boolean dataSelected = true;
					dtmcart.removeRow(carttbl.getSelectedRow());
					carttbl.getSelectionModel().clearSelection();
						}
			}
		}
		
	if(e.getSource()==buybtn) {
		//get music Id
		Vector<String> cartMusicIdVector = new Vector<String>();
		
		cartTotalPrice=0;
		for(int i=0; i<carttbl.getRowCount(); i++) {
		//get Total Price
		cartTotalPrice += Integer.parseInt(carttbl.getValueAt(i, 3).toString());
		//get the music Id
			cartMusicId = carttbl.getValueAt(i, 0).toString();	
			cartMusicIdVector.add(cartMusicId);
		//get the date purchase
		
		}

			usermainform.con.insertHistoryHeader(usermainform.UserId, cartTotalPrice);
			
		try {
			String queryGetHistoryId = "SELECT MAX(id) FROM history_header WHERE user_id = ('"+UserMainForm.UserId+"') "
					+ "AND total_purchase = "+Integer.toString(cartTotalPrice)+" GROUP BY user_id ";
			usermainform.con.rs =usermainform.con.stat.executeQuery(queryGetHistoryId);
			usermainform.con.rs.next();
			historyId= usermainform.con.rs.getString("MAX(id)");
			
			for(int i=0; i<cartMusicIdVector.size(); i++) {
				String musicIdSaved = cartMusicIdVector.get(i);
				String queryAddHistoryDetail = "INSERT INTO history_detail(history_id,music_id) "
						+ "VALUES ('"+historyId+"','"+musicIdSaved+"')";
				usermainform.con.stat.execute(queryAddHistoryDetail);
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		dtmcart.getDataVector().removeAllElements();
		dtmcart.fireTableDataChanged();	
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