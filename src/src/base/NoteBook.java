package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteBook implements  Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}
	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);
	}
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public NoteBook(String file){
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			in.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	public boolean save(String file){
		//TODO
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		NoteBook note = new NoteBook();
		note = this;
		try {
		//TODO
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(note);
			out.close();		
			
		} catch (Exception e) {
		 return false;
		}
		return true;
		}
	
	
	
    public int compareTo(Note o){
    	//String ME =  date.toString();
    	//String Other = o.date.toString();
    	//int result = ME.compareTo(Other);
    	//return result;
    	return 0;
    	
    }
	public void sortFolders(){
		for (Folder f1:folders){
			f1.sortNotes();
		}
		
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