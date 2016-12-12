package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IndexConfig extends Application implements ChangeListener {

	private final LoggerWindow parent;
	
	private Stage stage;
	private Scene scene;
	
	private Button applyButton;
	
	private TextField indexPath;
	private TextField docsPath;
	private CheckBox createBox;
	private TextField maxHits;
	
	//private String indexPathVal;
	//private String docsPathVal;
	
	public IndexConfig(final LoggerWindow parent) {
		this.parent = parent;
		stage = new Stage();
		stage.setTitle("Configuration Options");
		stage.setWidth(400);
		stage.setHeight(400);
		stage.setResizable(false);
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		
		// label
		final Label headerLabel = new Label("CONFIGURATION OPTIONS");
		headerLabel.setFont(new Font("Arial", 16));
		
		// input fields
		final Label indexPathLabel = new Label("Path of index:");
		indexPathLabel.setFont(new Font("Arial", 12));
		
		indexPath = new TextField();
		indexPath.setMaxWidth(350);
		indexPath.textProperty().addListener(this);
		
		final Label docsPathLabel = new Label("Path of documents to be indexed:");
		docsPathLabel.setFont(new Font("Arial", 12));
		
		docsPath = new TextField();
		docsPath.setMaxWidth(350);
		docsPath.textProperty().addListener(this);
		
		createBox = new CheckBox("Create Index From Scratch?");
		createBox.selectedProperty().addListener(this);
		
		final Label maxHitsLabel = new Label("Path of documents to be indexed:");
		maxHitsLabel.setFont(new Font("Arial", 12));
		
		maxHits = new TextField();
		maxHits.setMaxWidth(350);
		maxHits.textProperty().addListener(this);
		
		applyButton = new Button("APPLY");
		applyButton.setDisable(true);
		Button exitButton = new Button("EXIT");
		
		applyButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Button b = (Button)e.getSource();
        		b.setDisable(true);
        		parent.updateConfigInfo(
        				indexPath.getText(),
        				docsPath.getText(),
        				!createBox.isSelected(),
        				maxHits.getText());
        	}
        });
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		stage.close();
        	}
        });
		
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(headerLabel, indexPathLabel, indexPath,
									docsPathLabel, docsPath, createBox,
									maxHitsLabel, maxHits, applyButton,
									exitButton);
		
		scene = new Scene(layout, 550, 400);
		stage.setScene(scene);
	}
	
	public void show(String indexPathVal, String docsPathVal, boolean update, int maxHitsVal) {
		indexPath.setText(indexPathVal);
		docsPath.setText(docsPathVal);
		createBox.setSelected(!update);
		maxHits.setText("" + maxHitsVal);
		applyButton.setDisable(false);
		stage.show();
	}
	
	@Override
	public void start(Stage stage) throws Exception { }


	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		applyButton.setDisable(false);
	}
	
}
