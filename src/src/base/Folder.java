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
		List<Note> list2 = new ArrayList<Note>();
		String name ;
		String tBefore ;
		String tAfter ;
		
		while(i<n)
		{if(temp[i].contains("or")||temp[i].contains("OR"))
		{
			int before = i-1;
			int after = i+1;
			String name2;
			tBefore = temp[before]; 
			
			String tBefore2 = tBefore.toLowerCase();
			tAfter = temp[after];
			
			String tAfter2 = tAfter.toLowerCase();
			//before
			for (Note f1:notes){
				if(f1 instanceof TextNote){
					 name = f1.getTitle();
					 name2 = name.toLowerCase();
					if(name2.contains(tBefore2)||name2.contains(tAfter2))
						{int redundant = 0;
						for(Note f2: list)
						  {if(f2.getTitle()==f1.getTitle())redundant++;}
						if(redundant==0) list.add(f1);
						}
				
				else{ name = ((TextNote) f1).content;
				      name2 = name.toLowerCase();
				      if(name2.contains(tBefore2)||name2.contains(tAfter2))
				    	  {int redundant = 0;
							for(Note f2: list)
							  {if(f2.getTitle()==f1.getTitle())redundant++;}
							if(redundant==0) list.add(f1);}
					
					}
				}
				else
				{ name = f1.getTitle();
				name2= name.toLowerCase();
				if(name2.contains(tBefore2)||name2.contains(tAfter2))
					{int redundant = 0;
					for(Note f2: list)
					  {if(f2.getTitle()==f1.getTitle())redundant++;}
					if(redundant==0) list.add(f1);}
					
				}
			}break;
		}i++;
	}   i=i+3;
	
		if(temp[i].contains("or")||temp[i].contains("OR"))
		{
			int before = i-1;
			int after = i+1;
			String name2;
			tBefore = temp[before]; 
			
			String tBefore2 = tBefore.toLowerCase();
			tAfter = temp[after];
			
			String tAfter2 = tAfter.toLowerCase();
			//before
			for (Note f1:list){
				if(f1 instanceof TextNote){
					 name = f1.getTitle();
					 name2 = name.toLowerCase();
					if(name2.contains(tBefore2)||name2.contains(tAfter2))
						{int redundant = 0;
						for(Note f2: list2)
						  {if(f2.getTitle()==f1.getTitle())redundant++;}
						if(redundant==0) list2.add(f1);
						}
				
				else{ name = ((TextNote) f1).content;
				      name2 = name.toLowerCase();
				      if(name2.contains(tBefore2)||name2.contains(tAfter2))
				    	  {int redundant = 0;
							for(Note f2: list2)
							  {if(f2.getTitle()==f1.getTitle())redundant++;}
							if(redundant==0) list2.add(f1);}
					
					}
				}
				else
				{ name = f1.getTitle();
				name2= name.toLowerCase();
				if(name2.contains(tBefore2)||name2.contains(tAfter2))
					{int redundant = 0;
					for(Note f2: list2)
					  {if(f2.getTitle()==f1.getTitle())redundant++;}
					if(redundant==0) list2.add(f1);}
					
				}
			}
		}
	
		
		

		return list2;	 
		
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