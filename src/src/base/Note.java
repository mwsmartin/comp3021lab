package base;

import java.util.Date;

public class Note implements Comparable<Note> {
	private Date date;
	private String title;
	public Note(String title){
		this.title = title;
		date = new Date (System.currentTimeMillis());
	}
	public String getTitle(){
		return title;
	}
	public String toString(){
		return date.toString() + "\t" + title;
	}
	
    @Override
    public int compareTo(Note o){
    	String ME =  date.toString();
    	String Other = o.date.toString();
    	int result = ME.compareTo(Other);
    	return result;
    	
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Note other = (Note) obj;
		if(title == null){
			if(other.title != null)
				return false;
		}else if (!title.equals(other.title))
			return false;
		return true;
	}
}