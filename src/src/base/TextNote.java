package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextNote extends Note {
	public String content;
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
		BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(absolutePath));
            try {
                String x = br.readLine();
                while ( x != null ) {
                    // printing out each line in the file
                    result += x;
                } 
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
		return result;
		}
		public void exportTextToFile(String pathFolder) {
			String text= this.content;
			BufferedWriter bw;
			File file = new File( pathFolder + File.separator + this.getTitle() + ".txt");
			try {
				file.createNewFile();
				 bw = new BufferedWriter(new FileWriter(file));
				 bw.write(text);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// TODO
			}
	/*public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}*/
}