package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}
	public void addNote(Note add){
		notes.add(add);
	}
	public String getName(){
		return name;
	}
	public ArrayList<Note> getNotes(){
		return notes;
	}
	public void sortNotes(){
		Collections.sort(notes);
	}
	public List<Note> searchNotes(String keywords){
		String[] temp = keywords.split(" ");
		int n = temp.length;
		int i =0;
		List<Note> list = new ArrayList<Note>();
		while(i<n){
			String temp2 = null;
			String temp3 = null;
			
			temp2 = temp[i]; 
			i++;
			if(i<n){
				//temp3 = temp[i++];
				if(temp[i]=="or"||temp[i]=="Or"||temp[i]=="OR"||temp[i]=="oR")
					{temp3 = temp[i++];
					i++;}
				else temp3 = null;}
				
			if (temp3==null){
				for (Note f1 : notes){
					String title = f1.getTitle();
					if(title.indexOf(temp2) != -1)
						list.add(f1);
					
				}
			}
			else if(temp3 != null) {
				for (Note f1 : notes){
					String title = f1.getTitle();
					if(title.indexOf(temp2) != -1 || title.indexOf(temp3) != -1)
						list.add(f1);
				}}
					
		    		    
		}
		return list;	 
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	 @Override
	    public int compareTo(Folder o){
	    	
	    	int result = name.compareTo(o.name);
	    	return result;
	    	
	    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public String toString(){
		int nText = 0;
		int nImage = 0;
		for(Note note : notes){
			if(note instanceof TextNote){
				nText += 1;
			}else {
				nImage +=1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
}