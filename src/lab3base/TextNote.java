package base;

public class TextNote extends Note {
	public String content;
	public TextNote(String title){
		super(title);
	}
	public TextNote(String title, String content){
		super(title);
		this.content=content;
		
	}
	/*public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}*/
}