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
		int numofOR = 0;
		List<Integer> OR = new ArrayList<Integer>();
		List<Note> list = new ArrayList<Note>();
		
		String name ;
		String tBefore ;
		String tAfter ;
		System.out.println(temp[0]);
		System.out.println(temp[1]);
		System.out.println(temp[2]);
		while(i<n){
		if(temp[i]=="or"||temp[i]=="oR"||temp[i]=="OR"||temp[i]=="Or")
			{   numofOR++;
			System.out.println("numofOR  " + Integer.toString(numofOR));
				int before = i-1;
				int after = i+1;
				
				tBefore = temp[before]; tBefore.toLowerCase();
				tAfter = temp[after]; tAfter.toLowerCase();
				//before
				for (Note f1:notes){
					if(f1 instanceof TextNote){
						 name = f1.getTitle();
						name.toLowerCase();
						if(name.contains(tBefore))
							{list.add(f1);break;}
					
					else{ name = ((TextNote) f1).content;
					      name.toLowerCase();
					      if(name.contains(tBefore))
					    	  {list.add(f1);break;}
						
						}
					}
					else
					{ name = f1.getTitle();
					name.toLowerCase();
					if(name.contains(tBefore))
						{list.add(f1);break;}
						
					}
				}
				//After
				for (Note f1:notes){
					if(f1 instanceof TextNote){
						 name = f1.getTitle();
						name.toLowerCase();
						if(name.contains(tAfter))
							{list.add(f1);break;}
					
					else{ name = ((TextNote) f1).content;
					      name.toLowerCase();
					      if(name.contains(tAfter))
					    	  {list.add(f1);break;}
						
						}
					}
					else
					{ name = f1.getTitle();
					name.toLowerCase();
					if(name.contains(tAfter))
						list.add(f1);
						
					}
					
				}
				
			
			
			
			
			
			}
		i++;
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