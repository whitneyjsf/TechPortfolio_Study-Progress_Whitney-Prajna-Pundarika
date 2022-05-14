import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class History extends JInternalFrame implements MouseListener {

	private JPanel leftpan, rightpan, mainpan;
	private JTable HeaderTransactionTable, DetailTransactionTable;
	private DefaultTableModel dtmheader, dtmdetail;
	private JScrollPane scrollHeader, scrollDetail;
	private UserMainForm usermainform;
	private Boolean dataSelected;
	private Connect con;
	
	public History(UserMainForm usermainform) {
		this.usermainform = usermainform;
		mainpan = new JPanel(new GridLayout(1,2));
		leftpan = new JPanel();
		rightpan = new JPanel();

		//MAIN PANEL
		//TABEL KIRI
		String header[] = {"ID", "Total Purchase (IDR)", "Date Purchase"};
		dtmheader = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		HeaderTransactionTable = new JTable(dtmheader);
		getHistory();
		scrollHeader = new JScrollPane(HeaderTransactionTable);
		HeaderTransactionTable.addMouseListener(this);


		//TABEL KANAN
		String detail[] = {"History ID", "Music Name", "Music Artist", "Music Price"};
		dtmdetail = new DefaultTableModel(detail, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DetailTransactionTable = new JTable(dtmdetail);
		scrollDetail = new JScrollPane(DetailTransactionTable);


		//ADD PANEL

		leftpan.add(scrollHeader);
		rightpan.add(scrollDetail);

		mainpan.add(leftpan);
		mainpan.add(rightpan);

		add(mainpan);
		//JInternalFrame

		this.setClosable(true);
		this.setTitle("History");
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setSize(950, 480);

	}

	public void getHistory(){
		try {
			usermainform.con.getHistoryData();
			while (usermainform.con.rs.next()) {
				Vector<String> data = new Vector<String>();
				for (int i = 1; i <= usermainform.con.rsm.getColumnCount(); i++) {
					String str = usermainform.con.rs.getObject(i).toString();
					data.add(str);
				}
				dtmheader.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void ClearDetail() {
		dtmdetail.getDataVector().removeAllElements();
		revalidate();
	}
	
	
	private int listeners() {
		MouseListener[] listeners = HeaderTransactionTable.getMouseListeners();

		return 0;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (HeaderTransactionTable.getSelectedRowCount() == 1) {
			ClearDetail();
			int row = HeaderTransactionTable.getSelectedRow();
			String SelectedRowHistoryId = HeaderTransactionTable.getModel().getValueAt(row, 0).toString();
			
			try {
				usermainform.con.SelectGetHistoDetailData(SelectedRowHistoryId);
				while (usermainform.con.rs.next()) {
					Vector<String> dataHistoryDetail = new Vector<String>();
					for (int i = 1; i <= usermainform.con.rsm.getColumnCount(); i++) {
						String addData =usermainform.con.rs.getObject(i).toString();
						dataHistoryDetail.add(addData);
					}
					dtmdetail.addRow(dataHistoryDetail);
				}
				
			}catch (Exception e2) {
			e2.printStackTrace();

			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

