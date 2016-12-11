package gui;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.apache.lucene.document.Document;

public class LogDisplay extends TableView<Document> {

	private ObservableList<Document> docs;
	private final LoggerWindow parent;
	
	// WHAT THE HELL IS AN OBSERVABLE LIST?
	public LogDisplay(final LoggerWindow parent, ObservableList<Document> docs) {
		
		this.parent = parent;
		this.setEditable(true);
		this.setMinSize(600, 400);
		this.setStyle("-fx-border-color: black");
		
		TableColumn c1 = new TableColumn("Logs");
		TableColumn c2 = new TableColumn("File name");
		this.getColumns().addAll(c2, c1);	
		
		this.setItems(docs);
	}
	
}
