package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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
		File file =null;
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