package utilities;

public class Note {
	
	int id;
	String noteHeading;
	String noteBody;
	int userId;
	
	public Note(int id, int userId, String noteHeading, String noteBody) {
		this.id = id;
		this.noteHeading = noteHeading;
		this.noteBody = noteBody;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public int getNoteId() {
		return this.id;
	}
	
	public String getNoteHeading() {
		return this.noteHeading;
	}
	
	public String getNoteBody() {
		return this.noteBody;
	}

}
