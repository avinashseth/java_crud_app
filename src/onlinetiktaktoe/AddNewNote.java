package onlinetiktaktoe;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import database.Dbconnector;

import javax.swing.JButton;

public class AddNewNote {

	private JFrame frmAddNewNote;
	private JTextField textField;
	
	private Dbconnector dbc;
	
	private int userId;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AddNewNote(int userId) {
		dbc = new Dbconnector();
		this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddNewNote = new JFrame();
		frmAddNewNote.setTitle("Add New Note");
		frmAddNewNote.setBounds(100, 100, 450, 300);
		frmAddNewNote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddNewNote.getContentPane().setLayout(null);
		
		JLabel lblNoteHeading = new JLabel("Note Heading");
		lblNoteHeading.setBounds(10, 20, 95, 13);
		frmAddNewNote.getContentPane().add(lblNoteHeading);
		
		textField = new JTextField();
		textField.setBounds(10, 43, 416, 19);
		frmAddNewNote.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNoteBody = new JLabel("Note Body");
		lblNoteBody.setBounds(10, 72, 95, 13);
		frmAddNewNote.getContentPane().add(lblNoteBody);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 95, 416, 108);
		frmAddNewNote.getContentPane().add(textPane);
		
		JButton btnAddNote = new JButton("Add Note");
		btnAddNote.setBounds(325, 221, 85, 21);
		btnAddNote.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String noteHeading = textField.getText();
				String noteBody = textPane.getText();
				if(noteHeading.length() == 0 || noteBody.length() == 0) {
					String message = "Note heading and body are required!";
				    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				        JOptionPane.ERROR_MESSAGE);
				} else {
					boolean result = dbc.inserNote(userId, noteHeading, noteBody);
					if(result) {
						String message = "Note added successfully!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Success",
					        JOptionPane.INFORMATION_MESSAGE);
					} else {
						String message = "Something went wrong!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					        JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frmAddNewNote.getContentPane().add(btnAddNote);
		
		JButton btnCancel = new JButton("Cancle");
		btnCancel.setBounds(10, 221, 85, 21);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmAddNewNote.setVisible(false);
				frmAddNewNote.dispose();
			}
		});
		frmAddNewNote.getContentPane().add(btnCancel);
		frmAddNewNote.setVisible(true);
	}
}
