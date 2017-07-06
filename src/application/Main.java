package application;
	
import java.io.File;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	LinkedList list = null;
	int pos = -1;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,900,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button add = new Button("Add Image");
			Button delete = new Button("Remove Image");
			Button next = new Button("Next");
			Button prev = new Button("Previous");
			
			add.setOnAction(e -> {
				if(list == null){
					list = new LinkedList(getFiles());
					pos = 0;
				}
				else{
					list.add(getFiles());
				}
			});
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public File[] getFiles(){
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		File[] files = null;
		int retVal = fc.showOpenDialog(null);
		if(retVal == JFileChooser.APPROVE_OPTION){
			files = fc.getSelectedFiles();
		}
		
		return files;
	}
	
}
