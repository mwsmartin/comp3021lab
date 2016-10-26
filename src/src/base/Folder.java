package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		notes = new ArrayList<Note>();
		this.name = name;
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	public boolean removeNotes(String title) {
		// TODO
		// Given the title of the note, delete it from the folder.
		// Return true if it is deleted successfully, otherwise return false.
		for(Note n: notes){
			if(n.getTitle().equals(title)){
			  notes.remove(n);
			  
			  return true;
			}
					
		}
		return false;
		
		
		}
	public String toString(){
		int nText = 0;
		int nImage = 0;
		
		for(Note n:notes){
			if(n instanceof TextNote){
				nText++;
			}
			else{
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	public boolean equals(Folder folder){
		if(name.equals(folder.name)){
			return true;
		}
		return false;
	}
	
	public void sortNotes(){
		List<Note> listnote = new ArrayList<Note>(notes);
		Collections.sort(listnote);
		notes = (ArrayList<Note>)listnote;
	}
	
	public List<Note> searchNotes(String keywords){
		 
		 List<Note> List_notes = new ArrayList<Note>();
		 String[] strSplit = keywords.split(" ",0);
		
		 List<String> andArr = new ArrayList<>();
		 List<String> orArr  = new ArrayList<>();
		
		 for ( int i =0 ; i< strSplit.length ; i++){
		
		 if ( strSplit[i] != null && strSplit[i].equalsIgnoreCase("or") ){
			
			 orArr.add(strSplit[i+1].toLowerCase());
			 
			 if(strSplit[i-1]!=null)
			  orArr.add(strSplit[i-1].toLowerCase()); 
			 strSplit[i] = strSplit[i+1] = strSplit[i-1] =null;
			 
		 }
		 //
		
		 }
	 
		 //
		 for(int i =0;i<orArr.size();i++){
			 System.out.println(orArr.get(i));
		 }
		 
		 for ( String s : strSplit ){
			 if ( s != null )
				 andArr.add(s.toLowerCase());
		 }
	 
		 for ( Note n: notes ){
		 
		 boolean consistOfKeys = true;
		 boolean consistOfContent = true;
		 if ( n instanceof ImageNote){
			
			 String nTitle = n.getTitle().toLowerCase();


			 for ( String s: andArr){
				 if ( nTitle.contains(s) );
				 else consistOfKeys = false;
			 }
		 if(orArr.size()==3){
			 for ( int j = 0; j < orArr.size() ; j+=3){

				 if ( nTitle.contains( orArr.get(j) ) || nTitle.contains( orArr.get(j+1) )||nTitle.contains( orArr.get(j+2) ));
				 else consistOfKeys = false;}
			 
		 }else
			 for ( int j = 0; j < orArr.size() ; j+=2){

				 if ( nTitle.contains( orArr.get(j) ) || nTitle.contains( orArr.get(j+1) ));
				 else consistOfKeys = false;
		 
			 	}
	 
			 consistOfContent = false; 
			 
		 }else if ( n instanceof TextNote){
				
			 String nTitle = n.getTitle().toLowerCase();
			 String nContent = ((TextNote) n).getContent().toLowerCase();

			 for ( String s: andArr){
				 if ( nTitle.contains(s) );
				 else consistOfKeys = false;
			 }
			 if(orArr.size()==3){
				 for ( int j = 0; j < orArr.size() ; j+=3){
						
				      if(orArr.get(j)!=null&&orArr.get(j+1)!=null&&orArr.get(j+2)!=null)
				        if ( nTitle.contains( orArr.get(j) ) || nTitle.contains( orArr.get(j+1))||nTitle.contains( orArr.get(j+2)));
					 else consistOfKeys = false;
				     
			 
				 	}
			 }
			 else
			 for ( int j = 0; j < orArr.size() ; j+=2){
				
			      if(orArr.get(j)!=null&&orArr.get(j+1)!=null)
			        if ( nTitle.contains( orArr.get(j) ) || nTitle.contains( orArr.get(j+1)));
				 else consistOfKeys = false;
			     
		 
			 	}
	 
			 for ( String s: andArr ){

				 if ( s != null && nContent.contains(s));
				 else consistOfContent = false;
			 
			 }
			 if(orArr.size()==3){
				 for ( int j = 0 ; j < orArr.size() ; j+=3){
                       
					 if ( nContent.contains( orArr.get(j) ) || nContent.contains( orArr.get(j+1) )||nContent.contains( orArr.get(j+2) ) );
					 else consistOfContent = false;
				 	}
			 }
			 else
			 for ( int j = 0 ; j < orArr.size() ; j+=2){

				 if ( nContent.contains( orArr.get(j) ) || nContent.contains( orArr.get(j+1) ) );
				 else consistOfContent = false;
			 	}
	 
		 }
		 
		 if ( consistOfKeys || consistOfContent )
		 List_notes.add(n);
		 
		 }
		 
		 return List_notes;
	}

	

}