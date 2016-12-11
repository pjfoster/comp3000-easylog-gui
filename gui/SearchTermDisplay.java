package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class SearchTermDisplay extends FlowPane {

	private TextField search;
	private Button addButton;
	private Button andOrButton;
	private final SearchBar parent;
	
	public SearchTermDisplay(final SearchBar parent) {
		this.parent = parent;
		search = new TextField();
		search.setMinWidth(150);
		search.setMaxWidth(150);
		
		addButton = new Button("+");
		if (parent.numSearchTerms() >= 2) {
			addButton.setVisible(false);	
		}
		
		addButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Button b = (Button)e.getSource();
        		String text = search.getText();
        		
        		// Get parent to add new search term
        		parent.addNewSearchTerm();
        		
        		// Disable this button
        		b.setVisible(false);
        		SearchTermDisplay p = (SearchTermDisplay)b.getParent();
        		p.getChildren().remove(1);
        		
        		// add the and/or button
        		andOrButton = new Button("AND");
        		andOrButton.setOnAction(new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent e) {
                		Button b = (Button)e.getSource();
                		if (b.getText().equals("AND")) {
                			b.setText("OR");
                		} else {
                			b.setText("AND");
                		}
                	}
                });
        		p.getChildren().add(andOrButton);
        		
        	}
        });
		
		this.setHgap(5);
		this.getChildren().addAll(search, addButton);
	}
	
	public String getText() {
		return search.getText();
	}
	
	public String getButtonText() {
		if (andOrButton == null) {
			return "";
		} else {
			return andOrButton.getText();
		}
	}
}
