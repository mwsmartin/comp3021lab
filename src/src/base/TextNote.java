package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

public class TextNote extends Note implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	
	public TextNote(String title){
		super(title);
		content="";
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					
				}
				if (count.get(c) > b) {
					b = count.get(c);
					r = c;
				}
			}
		}
		return r;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		File file = null;
		Scanner input = null;
		try{
			file = new File(absolutePath);
			input = new Scanner(file);
			while(input.hasNextLine()){
				content = content.concat(input.nextLine());
			}
			input.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public TextNote(String title,String content){
		super(title);
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	public void saveText(String s){
		content = s;
	}
	
	public void exportTextToFile(String pathFolder) {
		File file = null;
		PrintWriter pw = null;
		try{
			if(pathFolder != "")
				file = new File(pathFolder + File.separator + getTitle().replaceAll(" ","_") + ".txt");
			else
				file = new File(getTitle().replaceAll(" ","_") + ".txt");
			pw = new PrintWriter(file);
			pw.print(content);
			pw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}