package onlinetiktaktoe;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Dbconnector;
import utilities.Note;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Viewnotes {

	private JFrame frmViewNotes;
	private int userId;
	
	Dbconnector dbc;

	private List<Note> noteList = new ArrayList<>();
	private JLabel[] lblNewLabel = new JLabel[5];
	private JButton[] btnNewButton = new JButton[5];
	
	List<Note> notes = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Viewnotes(int userId) {
		this.userId = userId;
		dbc = new Dbconnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewNotes = new JFrame();
		frmViewNotes.setTitle("View notes");
		frmViewNotes.setBounds(100, 100, 450, 300);
		frmViewNotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{217, 2, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{2, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frmViewNotes.getContentPane().setLayout(gridBagLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmViewNotes.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Notes");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add New Note");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewNote ann = new AddNewNote(userId);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		
		
		
		
		
		
		notes = dbc.readNotes(this.userId);
		
		if(notes.size() == 0) {
			System.out.println("User has not added any note");
		} else {
			System.out.println("Notes size is " + notes.size());
			for(int i = 0; i < notes.size(); i++) {
				System.out.println(notes.get(i).getNoteHeading());
				lblNewLabel[i] = new JLabel(notes.get(i).getNoteHeading());
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1 + i;
				frmViewNotes.getContentPane().add(lblNewLabel[i], gbc_lblNewLabel);
				
				btnNewButton[i] = new JButton("Edit");
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
				gbc_btnNewButton.gridx = 5;
				gbc_btnNewButton.gridy = 1 + i;
				frmViewNotes.getContentPane().add(btnNewButton[i], gbc_btnNewButton);
				
				final int indexOfLoop = i;
				
				btnNewButton[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
							EditNote en = new EditNote(notes.get(indexOfLoop).getNoteId(), notes.get(indexOfLoop).getNoteHeading(), notes.get(indexOfLoop).getNoteBody());
					}
				});
			}
		}
		frmViewNotes.setVisible(true);
	}

}
