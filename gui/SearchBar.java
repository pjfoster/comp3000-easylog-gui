package gui;

import java.util.ArrayList;

import org.apache.lucene.search.Query;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lucene.QueryCreator;

public class SearchBar extends VBox {

	private final LoggerWindow parent;
	
	private ArrayList<SearchTermDisplay> searchTerms;
	private SearchTermDisplay filter;
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
        		
        		Query query = queryCreator.createQuery(searchTerms);
        		
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
        this.setSpacing(15);
        this.setPadding(new Insets(40, 10, 10, 10));
        this.getChildren().addAll(filterLabel, filter, filterButton, highlightLabel, highlight, highlightButton);
	}
	
	public void addNewSearchTerm() {
		SearchTermDisplay newTerm = new SearchTermDisplay(this);
		searchTerms.add(newTerm);
		
		this.getChildren().add(searchTerms.size(), newTerm);
	}
	
	public int numSearchTerms() { return searchTerms.size(); }
	
}
