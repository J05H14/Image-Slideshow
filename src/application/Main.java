package application;

import java.io.File;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	LinkedList list = null;
	int pos = -1;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 200, 150);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Button importImg = new Button("Import Images");

			importImg.setOnAction(e -> {
				list = new LinkedList(getFiles());
				pos = 0;

				viewerStage();
			});

			root.setBottom(importImg);
			
			primaryStage.setTitle("Image Importer");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void viewerStage(){
		BorderPane bp = new BorderPane();
		Scene sc = new Scene(bp, 900, 700);
		Stage stage = new Stage();

		Button add = new Button("Add Image");
		Button delete = new Button("Remove Image");
		Button next = new Button("Next");
		Button prev = new Button("Previous");
		Button slideshow = new Button("Slideshow");

		HBox mathButtons = new HBox(add, delete);
		
		HBox moveButtons = new HBox(prev, next);	
		
		GridPane bottom = new GridPane();
		
		bp.setCenter(loadImage(list.get(pos)));
		
		add.setOnAction(e -> {
			list.add(getFiles());
		});
		prev.setOnAction(e -> {
			if(pos != 0){
				pos--;
				bp.setCenter(loadImage(list.get(pos)));
			}
		});
		next.setOnAction(e -> {
			if(pos != list.size() - 1){
				pos++;
				bp.setCenter(loadImage(list.get(pos)));
			}
		});
		delete.setOnAction(e -> {
			if(pos == list.size() - 1){
				list.delete(pos);
				pos--;
				bp.setCenter(loadImage(list.get(pos)));
			}
			else{
				list.delete(pos);
				bp.setCenter(loadImage(list.get(pos)));
			}
		});
		

		bp.setCenter(loadImage(list.get(pos)));
		
		bp.setBottom(buttonBox);
		
		stage.setTitle("Image Slideshow");
		stage.setScene(sc);
		stage.show();
	}
	
	public Label blankSpace() {
		return new Label ("                                 ");
	}


	public ImageView loadImage(File imgFile){
		String fileLocation = imgFile.toURI().toString();
		Image img = new Image(fileLocation);

		ImageView iv = new ImageView(img);

		iv.setPreserveRatio(true);
		iv.setFitHeight(600);

		return iv;
	}

	public File[] getFiles(){
		JFileChooser fc = new JFileChooser(".");
		fc.setMultiSelectionEnabled(true);
		File[] files = null;
		int retVal = fc.showOpenDialog(null);
		if(retVal == JFileChooser.APPROVE_OPTION){
			files = fc.getSelectedFiles();
		}

		return files;
	}

}
