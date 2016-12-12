package gui;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

import org.apache.lucene.document.Document;

public class LogDisplay extends TableView<Document> {

	private ObservableList<Document> docs;
	private final LoggerWindow parent;
	private String highlightText = null;
	
	@SuppressWarnings("unchecked")
	public LogDisplay(final LoggerWindow parent, ObservableList<Document> docs) {
		
		this.parent = parent;
		this.docs = docs;
		this.setEditable(false);
		this.setMinSize(675, 400);
		this.setStyle("-fx-border-color: black");
		
		TableColumn<Document, String> c1 = new TableColumn<Document, String>("Logs");
		TableColumn<Document, String> c2 = new TableColumn<Document, String>("File name");

		c1.setCellValueFactory(new DocumentValueFactory<Document, String>("contents"));
		c2.setCellValueFactory(new DocumentValueFactory<Document, String>("filename"));
		
		c1.setCellFactory(new Callback<TableColumn<Document, String>, TableCell<Document, String>>() 
		{
	        public TableCell<Document, String> call(TableColumn<Document, String> param) {
	            TableCell<Document, String> newCell = new TableCell<Document, String>() {
	            	
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (!isEmpty()) {
	                    	if (getHighlightText() != null && item.contains(getHighlightText())) {
	                            this.setTextFill(Color.RED);
	                        } else {
	                        	this.setTextFill(Color.BLACK);
	                        }
	                        setText(item);
	                    } else {
	                    	setText("");
	                    }
	                }
	            };

	            newCell.setOnMouseClicked(new EventHandler() {
					@Override
					public void handle(Event e) {
						TableCell cell = (TableCell)e.getSource();
						Text text = new Text();
						
						// DEAL WITH HIGHLIGHTING
			            if (getHighlightText() != null && cell.getText().contains(getHighlightText())) {
			            	System.out.println("The text is supposed to be red...");
			            	text.setFill(Color.RED);
                        } else {
                        	text.setFill(Color.BLACK);
                        }
			            
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
			            text.wrappingWidthProperty().bind(cell.widthProperty());
			            text.textProperty().bind(cell.itemProperty());
					}
	            });
	            return newCell;
	        }
	    });
		
		c2.setMinWidth(120);
		c2.setMaxWidth(120);
		
		c1.setPrefWidth(655);
		
		this.getColumns().addAll(c2, c1);	
		this.setItems(docs);
	}
	
	public void setDocs(ObservableList<Document> docs) {
		this.docs = docs;
		this.setItems(docs);
	}
	
	public void highlightDocs(String subString) {
		
		if (subString == null || subString.trim().equals("")) {
			highlightText = null;
		} else {
			highlightText = subString;
		}

		this.refresh();
	}
	
	public String getHighlightText() {
		return highlightText;
	}

	
}
