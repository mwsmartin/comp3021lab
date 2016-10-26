package ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import base.Folder;
import base.Note;
import base.NoteBook;
import base.TextNote;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * NoteBook GUI with JAVAFX
 * 
 * COMP 3021
 * 
 * 
 * @author valerio
 *
 */
public class NoteBookWindow extends Application {

	/**
	 * TextArea containing the note
	 */
	final TextArea textAreaNote = new TextArea("");
	/**
	 * list view showing the titles of the current folder
	 */
	final ListView<String> titleslistView = new ListView<String>();
	/**
	 * 
	 * Combobox for selecting the folder
	 * 
	 */
	final ComboBox<String> foldersComboBox = new ComboBox<String>();
	/**
	 * This is our Notebook object
	 */
	NoteBook noteBook = null;
	/**
	 * current folder selected by the user
	 */
	String currentFolder = "";
	/**
	 * current search string
	 */
	String currentSearch = "";
	
	String currentNote = "";
	
	List<Note> matchedNotes = null;
	Stage stage;
	public static void main(String[] args) {
		launch(NoteBookWindow.class, args);
	}

	@Override
	public void start(Stage stage) {
		
		this.stage = stage;
		// Use a border pane as the root for scene
		BorderPane border = new BorderPane();
		// add top, left and center
		border.setTop(addHBox());
		border.setLeft(addVBox());
		
		border.setCenter(addGridPane());

		Scene scene = new Scene(border);
		stage.setScene(scene);
		stage.setTitle("NoteBook COMP 3021");
		stage.show();
	}

	/**
	 * This create the top section
	 * 
	 * @return
	 */
	private HBox add2HBoex(){
		HBox hbox = new HBox();
		Button buttonSave = new Button("Save");
		ImageView saveView = new ImageView(new Image(new File("save.png").toURI().toString()));
		saveView.setFitHeight(18);
		saveView.setFitWidth(18);
		saveView.setPreserveRatio(true);
		
		Button buttonDelete = new Button("Delete");
		ImageView deleteView = new ImageView(new Image(new File("delete.png").toURI().toString()));
		deleteView.setFitHeight(18);
		deleteView.setFitWidth(18);
		deleteView.setPreserveRatio(true);
		
		
		buttonSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				if(currentFolder.equals("")||currentNote.equals("")){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Warning");
					alert.setContentText("PLZ select a folder and a note "
					);
					alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
					}
					});
					
					return;
				}
				
				else{
					String N = textAreaNote.getText();
					((TextNote)noteBook.searchNotes(currentNote).get(0)).saveText(textAreaNote.getText());
					
					
					
				}
				
				
				
				
			}
			
				
		});
		
		buttonDelete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				if(currentFolder.equals("")||currentNote.equals("")){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Warning");
					alert.setContentText("PLZ select a folder and a note "
					);
					alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
					}
					});
					
					return;
				}
				else{
					Folder dummy = null;
					dummy = noteBook.searchFolder(currentFolder);
					if(dummy.removeNotes(currentNote))
						{updateListView();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText("success");
						alert.setContentText("successfully removed");

						alert.showAndWait();
						}
					
				}
				
				
				
				
			}
			
		});
		
		hbox.getChildren().addAll(saveView,buttonSave,deleteView,buttonDelete);
		return hbox;
	}
	private HBox addHBox() {
		ImageView saveView = new ImageView(new Image(new File("save.png").toURI().toString()));
		saveView.setFitHeight(18);
		saveView.setFitWidth(18);
		saveView.setPreserveRatio(true);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10); // Gap between nodes

		Button buttonLoad = new Button("Load From File");
		buttonLoad.setPrefSize(100, 20);
		//buttonLoad.setDisable(true);
		Button buttonSave = new Button("Save");
		buttonSave.setPrefSize(100, 20);
		//buttonSave.setDisable(true);
		Label label = new Label("Search:");
		TextField searchField = new TextField();
		Button buttonSearch = new Button("Search");
		Button buttonClearSearch = new Button("Clear Search");
		hbox.getChildren().addAll(buttonLoad, buttonSave,label,searchField,buttonSearch,buttonClearSearch);
		buttonSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				currentSearch = searchField.getText();
				
				updateListView();
			}
		});
		
		buttonLoad.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Please Choose An File Which Contains a NoteBook Object!");
				
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized Object File (*.ser)", "*.ser");
				fileChooser.getExtensionFilters().add(extFilter);
				
				File file = fileChooser.showOpenDialog(stage);
				if(file != null){
					
					loadNoteBook(file);
					refresh();
					
				}
				
			}


			
		});
		buttonSave.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Please Choose An File Which Contains a NoteBook Object!");
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Serialized Object File (*.ser)", "*.ser");
				fileChooser.getExtensionFilters().add(extFilter);
				
				File file = fileChooser.showSaveDialog(stage);
				if(file==null)
					return;
				if(file != null){
					noteBook.save(file.getName());
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Successfully saved");
					alert.setContentText("You file has been saved to file "
					+ file.getName());
					alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
					}
					});
					
					
				}
			}
		});
		buttonClearSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				matchedNotes = null;
				searchField.setText("");
				currentSearch = "";
				
			}
		});
		return hbox;
	}
	
			private void refresh() {
				if(noteBook!=null)
					for(Folder i : noteBook.getFolders()){
						foldersComboBox.getItems().add(i.getName());
					}
			}
	/**
	 * this create the section on the left
	 * 
	 * @return
	 */
			
    			
	private VBox addVBox() {

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10)); // Set all sides to 10
		vbox.setSpacing(8); // Gap between nodes
		if(noteBook!=null)
		for(Folder i : noteBook.getFolders()){
			foldersComboBox.getItems().add(i.getName());
		}
		
        HBox hbox = new HBox();
        Button folderbutton = new Button("add a folder");
        folderbutton.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent e){
        		TextInputDialog dialog = new TextInputDialog("Add a Folder");
        		dialog.setTitle("Input");
        		dialog.setHeaderText("Add a new folder for your notebook:");
        		dialog.setContentText("Please enter the name you want to create:");
        		// Traditional way to get the response value.
        		Optional<String> result = dialog.showAndWait();
        		if(result.get().isEmpty()){
        			Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Empty ");
					alert.setContentText("The input is Empty "
					);
					alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
					}
					});
        		}
        		
        		if (!result.get().isEmpty()){
        			if(noteBook.isExit(result.get())==true)
        			{
        				Alert alert = new Alert(AlertType.INFORMATION);
    					alert.setTitle("Exist");
    					alert.setContentText("Already Exist"
    					);
    					alert.showAndWait().ifPresent(rs -> {
    					if (rs == ButtonType.OK) {
    					System.out.println("Pressed OK.");
    					}
    					});
        			}
        			else{
        		noteBook.addFolder(result.get());
        		foldersComboBox.getItems().add(result.get());}
        		}
        	}
        });
        
		foldersComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				
		   
		   
	
				
				if(noteBook==null)
					return;
				currentFolder = t1.toString();
				// this contains the name of the folder selected
				// TODO update listview
				updateListView();

			}

		});

		foldersComboBox.setValue("-----");
		 hbox.getChildren().addAll(foldersComboBox,folderbutton);
		titleslistView.setPrefHeight(100);

		titleslistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				if(noteBook==null)
					return;
				
				if (t1 == null)
					return;
				currentNote=t1.toString();
				String title = t1.toString();
				// This is the selected title
				// TODO load the content of the selected note in
				// textAreNote
				String content = "";
				for(Folder f : noteBook.getFolders()){
					if(f.getName().equals(currentFolder)){
						for(Note n : f.getNotes()){
							if(n.getTitle().equals(title)){
								TextNote tn = (TextNote)n;
								content = tn.getContent();
							}
						}
					}	
				}
				textAreaNote.setText(content);

			}
		});
		
		Button addnote = new Button("add a note");
		
		addnote.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent e){
        		if(currentFolder==""){
        			
        			Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Empty folder");
					alert.setContentText("please choose a folder first!!!"
					);
					alert.showAndWait().ifPresent(rs -> {
					if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
					}
					});
        			
        			return;
        		}
        		else{
        			
        			TextInputDialog dialog = new TextInputDialog("Add a NOTE");
            		dialog.setTitle("Input");
            		dialog.setHeaderText("Add a new note:");
            		dialog.setContentText("Please enter the name you want to create:");
            		// Traditional way to get the response value.
            		Optional<String> result = dialog.showAndWait();
        			
            		if(!result.get().isEmpty()){
            			noteBook.createTextNote(currentFolder, result.get());
            			updateListView();
            			
            		}
        		}
        		
        		
        	}
        	
		});
		
		vbox.getChildren().add(new Label("Choose folder: "));
		vbox.getChildren().add(hbox);
		vbox.getChildren().add(new Label("Choose note title"));
		vbox.getChildren().add(titleslistView);
		vbox.getChildren().add(addnote);
		return vbox;
	}

	private void updateListView() {
		ArrayList<String> list = new ArrayList<String>();
		if(!currentSearch.equals("")){
			textAreaNote.setText("");
			for(Folder f : noteBook.getFolders()){
				if(f.getName().equals(currentFolder)){
					matchedNotes = f.searchNotes(currentSearch);
					break;
				}	
			}
		}
		// TODO populate the list object with all the TextNote titles of the
		// currentFolder
		titleslistView.getSelectionModel().clearSelection();
		for(Folder f : noteBook.getFolders()){
			if(f.getName().equals(currentFolder)){
				for(Note n : f.getNotes()){
					if(n instanceof TextNote)
						if(matchedNotes == null || matchedNotes.contains(n))
							list.add(n.getTitle());
				}
			}	
		}
		ObservableList<String> combox2 = FXCollections.observableArrayList(list);
		titleslistView.setItems(combox2);
		textAreaNote.setText("");
	}
	


	/*
	 * Creates a grid for the center region with four columns and three rows
	 */
	private GridPane addGridPane() {

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		textAreaNote.setEditable(true);
		textAreaNote.setMaxSize(450, 400);
		textAreaNote.setWrapText(true);
		textAreaNote.setPrefWidth(450);
		textAreaNote.setPrefHeight(400);
        grid.add(add2HBoex(),0,0);
		// 0 0 is the position in the grid
		
		grid.add(textAreaNote, 0, 1);

		return grid;
	}

	private void loadNoteBook() {
		NoteBook nb = new NoteBook();
		nb.createTextNote("COMP3021", "COMP3021 syllabus", "Be able to implement object-oriented concepts in Java.");
		nb.createTextNote("COMP3021", "course information",
				"Introduction to Java Programming. Fundamentals include language syntax, object-oriented programming, inheritance, interface, polymorphism, exception handling, multithreading and lambdas.");
		nb.createTextNote("COMP3021", "Lab requirement",
				"Each lab has 2 credits, 1 for attendence and the other is based the completeness of your lab.");

		nb.createTextNote("Books", "The Throwback Special: A Novel",
				"Here is the absorbing story of twenty-two men who gather every fall to painstakingly reenact what ESPN called éˆ¥æ¸¢he most shocking play in NFL historyéˆ¥ï¿½ and the Washington Redskins dubbed the éˆ¥æ·­hrowback Specialéˆ¥ï¿½: the November 1985 play in which the Redskinséˆ¥ï¿½ Joe Theismann had his leg horribly broken by Lawrence Taylor of the New York Giants live on Monday Night Football. With wit and great empathy, Chris Bachelder introduces us to Charles, a psychologist whose expertise is in high demand; George, a garrulous public librarian; Fat Michael, envied and despised by the others for being exquisitely fit; Jeff, a recently divorced man who has become a theorist of marriage; and many more. Over the course of a weekend, the men reveal their secret hopes, fears, and passions as they choose roles, spend a long night of the soul preparing for the play, and finally enact their bizarre ritual for what may be the last time. Along the way, mishaps, misunderstandings, and grievances pile up, and the comforting traditions holding the group together threaten to give way. The Throwback Special is a moving and comic tale filled with pitch-perfect observations about manhood, marriage, middle age, and the rituals we all enact as part of being alive.");
		nb.createTextNote("Books", "Another Brooklyn: A Novel",
				"The acclaimed New York Times bestselling and National Book Awardéˆ¥æ�˜inning author of Brown Girl Dreaming delivers her first adult novel in twenty years. Running into a long-ago friend sets memory from the 1970s in motion for August, transporting her to a time and a place where friendship was everythingéˆ¥æ”—ntil it wasnéˆ¥æª›. For August and her girls, sharing confidences as they ambled through neighborhood streets, Brooklyn was a place where they believed that they were beautiful, talented, brilliantéˆ¥æ”� part of a future that belonged to them. But beneath the hopeful veneer, there was another Brooklyn, a dangerous place where grown men reached for innocent girls in dark hallways, where ghosts haunted the night, where mothers disappeared. A world where madness was just a sunset away and fathers found hope in religion. Like Louise Meriwetheréˆ¥æªš Daddy Was a Number Runner and Dorothy Allisonéˆ¥æªš Bastard Out of Carolina, Jacqueline Woodsonéˆ¥æªš Another Brooklyn heartbreakingly illuminates the formative time when childhood gives way to adulthoodéˆ¥æ”–he promise and peril of growing upéˆ¥æ”�nd exquisitely renders a powerful, indelible, and fleeting friendship that united four young lives.");

		nb.createTextNote("Holiday", "Vietnam",
				"What I should Bring? When I should go? Ask Romina if she wants to come");
		nb.createTextNote("Holiday", "Los Angeles", "Peter said he wants to go next Agugust");
		nb.createTextNote("Holiday", "Christmas", "Possible destinations : Home, New York or Rome");
		noteBook = nb;

	}
	private void loadNoteBook(File file){
		NoteBook nb = new NoteBook(file.getAbsolutePath());
		noteBook = nb;
		updateListView();

	}

}