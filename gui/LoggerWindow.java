package gui;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;

import controller.LogAppController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class LoggerWindow extends Application {

	private LogAppController controller;
	
	private LogDisplay logs;
	private SearchBar searchbar;
	private Header header;
	private Footer footer;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public LoggerWindow() {
		searchbar = new SearchBar(this);
		header = new Header(this);
		
		controller = new LogAppController(this);
		
		ObservableList<Document> allLogs = controller.allLogs();
		logs = new LogDisplay(this, allLogs);
		footer = new Footer(this, allLogs.size());
	}

	@Override
	public void start(Stage stage) {

		BorderPane grid = new BorderPane();
		
		RowConstraints rc = new RowConstraints();
		rc.setFillHeight(true);
		rc.setVgrow(Priority.ALWAYS);
		
		Scene scene = new Scene(grid, 1020, 600);
		
		stage.setTitle("OSX Log Viewer");
		stage.setWidth(1020);
		stage.setHeight(600);
		
		grid.setTop(header);
		grid.setBottom(footer);
		grid.setCenter(logs);
		grid.setLeft(searchbar);
		
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	
	}
	
	public void createIndex() {
		System.out.println("VIEW: Creating Index...");
		controller.createIndex();
	}
	
	public void filterSearch(Query query) {
		System.out.println("VIEW: Filtering...");
		ObservableList<Document> results = controller.filterSearch(query);
		logs.setDocs(results);
		footer.setNumLogs(results.size());
	}
	
	public void highlight() {
		System.out.println("VIEW: Highlighting logs...");
		controller.highlight();
	}
}
