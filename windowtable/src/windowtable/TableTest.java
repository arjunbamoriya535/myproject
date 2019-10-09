package windowtable;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class TableTest {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTest window = new TableTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TableTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 397, 239);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 24, 339, 117);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLUE, null));
		
		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"id", "username", "password", "name"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(76);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				 try {
			            
			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_test", "root", "");
			            PreparedStatement pstm = con.prepareStatement("SELECT * FROM `users`");
			            ResultSet Rs = pstm.executeQuery();
			            while(Rs.next()){
			            	int t1=Rs.getInt(1);
			            	String t2=Rs.getNString(2);
			            	String t3=Rs.getNString(3);
			            	String t4=Rs.getNString(4);
			                model.addRow(new Object[]{"t1","t2","t3","t4"});
			            }
			        } catch (Exception e) {
			            System.out.println(e.getMessage());
			        }
				
				
			}
		});
		btnNewButton.setBounds(10, 205, 89, 23);
		panel.add(btnNewButton);
		scrollPane.setViewportView(table);
	}
}
