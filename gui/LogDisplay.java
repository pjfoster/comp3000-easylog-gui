package gui;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import org.apache.lucene.document.Document;

public class LogDisplay extends TableView<Document> {

	private ObservableList<Document> docs;
	private final LoggerWindow parent;
	
	// WHAT THE HELL IS AN OBSERVABLE LIST?
	@SuppressWarnings("unchecked")
	public LogDisplay(final LoggerWindow parent, ObservableList<Document> docs) {
		
		this.parent = parent;
		this.setEditable(false);
		this.setMinSize(600, 400);
		this.setStyle("-fx-border-color: black");
		
		TableColumn<Document, String> c1 = new TableColumn<Document, String>("Logs");
		TableColumn<Document, String> c2 = new TableColumn<Document, String>("File name");

		c1.setCellValueFactory(new DocumentValueFactory("contents"));
		c2.setCellValueFactory(new DocumentValueFactory("filename"));
		
		this.getColumns().addAll(c2, c1);	
		
		this.setItems(docs);
	}
	
	public void setDocs(ObservableList<Document> docs) {
		this.docs = docs;
	}
	
}
