package gui;

import java.util.ArrayList;

import org.apache.lucene.search.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lucene.QueryCreator;

public class SearchBar extends VBox {

	private final LoggerWindow parent;
	
	private ArrayList<SearchTermDisplay> searchTerms;
	private SearchTermDisplay filter;
	private ComboBox<String> field;
	private Button filterButton;
	
	private TextField highlight;
	private Button highlightButton;
	
	private QueryCreator queryCreator;

	public SearchBar(final LoggerWindow parent) {
		this.parent = parent;
		this.queryCreator = new QueryCreator();
		
		final Label filterLabel = new Label("FILTER OPTIONS");
		filterLabel.setFont(new Font("Arial", 16));
		
		searchTerms = new ArrayList<SearchTermDisplay>();
        filter = new SearchTermDisplay(this);
        filterButton = new Button("Filter");
        searchTerms.add(filter);
        
        // CREATE COMBO BOX TO SELECT FIELD
        final Label fieldLabel = new Label("SEARCH FIELD:");
        fieldLabel.setFont(new Font("Arial", 12));
        
        ObservableList<String> fieldOptions = FXCollections.observableArrayList(
				"Logs",
				"Log File Names");
        field = new ComboBox<String>(fieldOptions);
        field.setValue("Logs");
        field.setMinWidth(185);
        field.setMaxWidth(185);

        // HIGHLIGHT SECTION
        final Label highlightLabel = new Label("HIGHLIGHT OPTIONS");
        highlightLabel.setFont(new Font("Arial", 16));
		
        highlight = new TextField();
        highlight.setMinWidth(185);
        highlight.setMaxWidth(185);
        highlightButton = new Button("Highlight");
        
        // Event handler for the filter button
        filterButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Button b = (Button)e.getSource();
        		SearchBar p = (SearchBar)b.getParent();
        		
        		Query query = queryCreator.createQuery(searchTerms, p.getSearchField());
        		
        		// TODO: error checking
        		System.out.println("QUERYSTRING: " + query.toString());
        		p.parent.filterSearch(query);
        	}
        });
        
     // Event handler for the highlight button
        highlightButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Button b = (Button)e.getSource();
        		String text = highlight.getText();
        		//System.out.println(text);
        		SearchBar p = (SearchBar)b.getParent();
            	p.parent.highlight(text);
        	}
        });
        
        this.setStyle("-fx-border-color: black");
        
        this.setMinWidth(345);
        this.setMaxWidth(345);
        this.setSpacing(10);
        this.setPadding(new Insets(40, 10, 10, 10));
        this.getChildren().addAll(filterLabel, filter, fieldLabel, field, filterButton, highlightLabel, highlight, highlightButton);
	}
	
	public void addNewSearchTerm() {
		SearchTermDisplay newTerm = new SearchTermDisplay(this);
		searchTerms.add(newTerm);
		
		this.getChildren().add(searchTerms.size(), newTerm);
	}
	
	public int numSearchTerms() { return searchTerms.size(); }
	
	public String getSearchField() {
		return field.getValue();
	}
	
}
