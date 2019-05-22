package onlinetiktaktoe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextPane;

import database.Dbconnector;
import utilities.Constants;

import javax.swing.JButton;

public class EditNote {

	private JFrame frmEditNote;
	private JTextField textField;
	private int noteId;
	private String noteBody, noteHeading;
	private Dbconnector dbc;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public EditNote(int noteId, String noteHeading, String noteBody) {
		this.noteId = noteId;
		this.noteHeading = noteHeading;
		this.noteBody = noteBody;
		dbc = new Dbconnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditNote = new JFrame();
		frmEditNote.setTitle("Edit Note");
		frmEditNote.setBounds(100, 100, 450, 300);
		frmEditNote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditNote.getContentPane().setLayout(null);
		
		JLabel lblNoteHeading = new JLabel("Note Heading");
		lblNoteHeading.setBounds(26, 22, 62, 13);
		frmEditNote.getContentPane().add(lblNoteHeading);
		
		textField = new JTextField();
		textField.setBounds(26, 45, 378, 19);
		textField.setText(this.noteHeading);
		frmEditNote.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNoteBody = new JLabel("Note Body");
		lblNoteBody.setBounds(26, 74, 47, 13);
		frmEditNote.getContentPane().add(lblNoteBody);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(26, 97, 378, 105);
		textPane.setText(this.noteBody);
		frmEditNote.getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("Delete Note");
		btnNewButton.setBounds(26, 232, 111, 21);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(dbc.deleteData(noteId, Constants.TBL_NOTES)) {
						frmEditNote.setVisible(false);
						frmEditNote.dispose();
					} else {
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmEditNote.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save Changes");
		btnNewButton_1.setBounds(307, 232, 97, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(new JFrame(), "Test", "Great",
				        JOptionPane.INFORMATION_MESSAGE);
				try {
					boolean result = dbc.updateChanges(noteId, Constants.TBL_NOTES, "note_heading = '" + textField.getText() + "', note_body = '" + textPane.getText() + "'");
					if(result) {
						String message = "Note updated successfully!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Great",
					        JOptionPane.INFORMATION_MESSAGE);
					} else {
						String message = "Something went wrong!";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Oops",
					        JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		frmEditNote.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.setBounds(180, 232, 85, 21);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmEditNote.setVisible(false);
				frmEditNote.dispose();
			}
		});
		frmEditNote.getContentPane().add(btnNewButton_2);
		
		frmEditNote.setVisible(true);
	}
}
