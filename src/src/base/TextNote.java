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
import java.util.Scanner;

public class TextNote extends Note implements Serializable {
	public String content;
	private static final long serialVersionUID = 1L;
	public TextNote(String title){
		super(title);
	}
	public TextNote(String title, String content){
		super(title);
		this.content=content;
		
	}
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
		}
		/**
		* get the content of a file
		*
		* @param absolutePath of the file
		* @return the content of the file
		*/
		private String getTextFromFile(String absolutePath) {
		String result = "";
		// TODO
		
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(absolutePath);
			in = new ObjectInputStream(fis);
			TextNote nb = (TextNote) in.readObject();
			in.close();
			this.content = nb.content;
			} catch (Exception e) {
			e.printStackTrace();
			}
		/*File file =null;
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
		}*/
		return result;
		
		}
		public void exportTextToFile(String pathFolder) {
			
			//TODO
			
			BufferedWriter bw =null;
			FileWriter writer = null;
			//File file = new File( pathFolder + File.separator +getTitle().replaceAll(" ","_") + ".txt");
			try {
			//TODO
				//File file = new File( pathFolder + File.separator +getTitle().replaceAll(" ","_") + ".txt");
				 writer = new FileWriter( pathFolder  +getTitle().replaceAll(" ","_") + "abbbbbb.txt", true);
				 bw = new BufferedWriter(writer);
				 //writer.write(content);
				 //writer.close();
				 bw.write(content);
				 
				bw.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// TODO
			/*File file = null;
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
			}*/
			
		}
		/*public void exportTextToFile(String pathFolder) {
			String text= this.content;
			BufferedWriter bw;
			File file = new File( pathFolder + this.getTitle() + ".txt");
			try {
				file.createNewFile();
				 bw = new BufferedWriter(new FileWriter(file));
				 bw.write(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// TODO
			}*/
	/*public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}*/
}