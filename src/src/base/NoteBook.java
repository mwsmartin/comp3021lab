package base;

import java.util.ArrayList;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}
	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);
	}
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	public void sortFolders(){
		for (Folder f1:folders){
			f1.sortNotes();
		}
		
	}
	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	private boolean insertNote(String folderName,Note note){
		Folder f = null;
		for (Folder f1:folders){
			if(f1.getName().equals(folderName)){
				f = f1;
			}
		}
		if (f == null){
			f = new Folder(folderName);
			folders.add(f);
		}
		for(Note n:f.getNotes()){
			if(note.equals(n)){
				System.out.println("Creating note " + note.getTitle()+ " under folder " + folderName + "failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
		
		
			
		
	}
	public List<Note> searchNotes(String string) {
		List<Note> list = new ArrayList<Note>();
//		list = null;
		for (Folder f1:folders){
			list.addAll(f1.searchNotes(string));
		}
		// TODO Auto-generated method stub
		return list;
	}
	
}