package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class LoggerWindow extends Application {

	private LogDisplay logs;
	private SearchBar searchbar;
	private Header header;
	private Footer footer;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public LoggerWindow() {
		logs = new LogDisplay(this, null);
		searchbar = new SearchBar(this);
		header = new Header();
		footer = new Footer(this);
	}

	@Override
	public void start(Stage stage) {

		BorderPane grid = new BorderPane();
		
		RowConstraints rc = new RowConstraints();
		rc.setFillHeight(true);
		rc.setVgrow(Priority.ALWAYS);
		
		Scene scene = new Scene(grid, 800, 600);
		
		stage.setTitle("OSX Log Viewer");
		stage.setWidth(800);
		stage.setHeight(600);
		
		grid.setTop(header);
		grid.setBottom(footer);
		grid.setCenter(logs);
		grid.setLeft(searchbar);
		
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	
	}
}
