package database;

import utilities.Constants;
import utilities.Note;
import utilities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Dbconnector {
	
	private Connection dbConnection;
	private Statement statement;  
	private ResultSet result;
	public List<User> users = new ArrayList<>();
	public List<Note> notes = new ArrayList<>();
	
	public Dbconnector() {
		// TODO Auto-generated constructor stub
		try {
			
			this.dbConnection = DriverManager.getConnection(Constants.CONNECTION_STRING,Constants.USERNAME,Constants.PASSWORD);
			this.statement = this.dbConnection.createStatement();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public List<User> readAllUsers() {
		
		try {
			this.result = this.statement.executeQuery("SELECT id, username, password FROM " + Constants.TBL_USERS + " ORDER BY id asc");
			while(result.next()) {
				users.add(new User(result.getInt(1), result.getString(2), result.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}
	
	public int loginUser(String username, String password) {
		int userId = -1;
		try {
			this.result = this.statement.executeQuery("SELECT id, username, password FROM " + Constants.TBL_USERS + " WHERE username = '" + username + "' AND password = '" + password + "'");
			while(result.next()) {
				userId = result.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	
	public boolean inserNote(int userId, String noteHeading, String noteBody) {
		try {
			int result = this.statement.executeUpdate("INSERT INTO " + Constants.TBL_NOTES + " (user_id, note_heading, note_body) VALUES (" + userId + ", '" + noteHeading + "', '" + noteBody + "')");
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Note> readNotes(int userId) {
		try {
			this.result = this.statement.executeQuery("SELECT id, user_id, note_heading, note_body FROM " + Constants.TBL_NOTES + " WHERE user_id = '" + userId + "'");
			while(this.result.next()) {
				notes.add(new Note(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}
	
	public boolean deleteData(int rowId, String tableName) throws SQLException {
		int result = this.statement.executeUpdate("DELETE " + tableName + " WHERE id = " + rowId);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateChanges(int rowId, String tableName, String updateQuery) throws SQLException {
		int result = this.statement.executeUpdate("UPDATE " + tableName + " SET " + updateQuery + " WHERE id = " + rowId);
		System.out.println("Update returned " + result);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insertData(String interName, int points) throws SQLException {
		
		int result = this.statement.executeUpdate("INSERT INTO interns (intern_name, points) VALUES ('" + interName + "', " + points + ")");
		
		if(result == 1) {
			System.out.println("Row inserted successfully");
		} else {
			System.out.println("Something went wrong");
		}
		
		
	}
	
	public void updateData(int internId, int points, String interName) throws SQLException {
		
		int result = this.statement.executeUpdate("UPDATE interns SET intern_name = '" + interName + "', points = " + points + " WHERE id = " + internId);
		
		if(result == 1) {
			System.out.println("Row updated successfully");
		} else {
			System.out.println("Something went wrong");
		}
		
	}
	
	public void removeData(int internId) throws SQLException {
		
		int result = this.statement.executeUpdate("DELETE interns WHERE id = " + internId);
		
		if(result == 1) {
			System.out.println("Row deleted successfully");
		} else {
			System.out.println("Something went wrong");
		}
		
	}

}
