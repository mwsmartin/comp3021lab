package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteBook implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	public NoteBook(String file){
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook nb = (NoteBook)in.readObject();
			in.close();
			this.folders = nb.folders;
		}catch(Exception ex){
			System.out.println(ex);
			ex.printStackTrace();
		}
	}
	public void addFolder(String folderName) {
		Folder t = new Folder(folderName);
		folders.add(t);
		}
	public boolean createTextNote(String foldername,String title){
		TextNote note = new TextNote(title);
		return insertNote(foldername,note);
	}
	
	public boolean createTextNote(String foldername,String title,String content){
		TextNote note = new TextNote(title,content);
		return insertNote(foldername,note);
	}
	
	public boolean createImageNote(String foldername,String title){
		ImageNote note = new ImageNote(title);
		return insertNote(foldername,note);
	}
	
	public boolean insertNote(String foldername,Note note){
		Folder f = null;
		
		for(Folder f1:folders){
			if(f1.equals(new Folder(foldername)))
				f = f1;
		}
		
		if(f == null){
			f = new Folder(foldername);
			f.addNote(note);
			folders.add(f);
			return true;
		}
		
		for(Note n:f.getNotes()){
			if(n.getTitle().equals(note.getTitle())){
				System.out.println("Creating note " + note.getTitle() + " under folder " + foldername + " failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public Folder searchFolder(String s){
		Folder dummy=null;
		
		for(Folder f : folders)
			if(f.getName().equals(s))
				dummy=f;
	    
		return dummy;
	}
	
	public void sortFolders(){
		for(Folder f:folders){
			f.sortNotes();
		}
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> resultnote = new ArrayList<Note>();
		for(Folder f:folders){
			resultnote.addAll(f.searchNotes(keywords));
		}
		return resultnote;
	}
	public boolean isExit(String e){
		boolean r = false;
		for(Folder f: folders){
			if(f.getName().equals(e))
					r = true;
		}
		return r;
	}
	
	public boolean 	save(String file){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			//return true;
			
		}catch(Exception ex){
			System.out.println("aaa");
			return false;
		}
		return true;
		
	}
}