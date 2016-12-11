package controller;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lucene.IndexFiles;
import lucene.SearchFiles;
import gui.LoggerWindow;

public class LogAppController {

	private LoggerWindow view;
	private IndexFiles indexTool;
	private SearchFiles searchTool;
	
	// indexing information
	private String indexPath = "/Users/PJF/Desktop/test_index/";
	private String indexData = "/Users/PJF/Desktop/test_data/";
	
	private ObservableList<Document> docs;
	
	// searching information

	public LogAppController(LoggerWindow view) {
		indexTool = new IndexFiles();
		searchTool = new SearchFiles();
		this.view = view;
		
		docs = FXCollections.observableArrayList();
	}
	
	public void createIndex() {
		System.out.println("CTRL: Creating Index...");
	}
	
	public ObservableList<Document> allLogs() {
		// DEFAULT SEARCH VALUES
		String index = indexPath;
	    String field = "contents";
	    int repeat = 0;
	    boolean raw = false;
	    int hitsPerPage = 100;
	    
	    Query query = new MatchAllDocsQuery();
	    
	    try {
	    	// search lucene index
	    	List<Document> searchResults = searchTool.searchQuery(query, index, field, repeat, raw, hitsPerPage);
	    	docs = FXCollections.observableArrayList(searchResults);
	    	return docs;
	    }
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	return null;
	    }
	}
	
	public ObservableList<Document> filterSearch(Query userQuery) {
		System.out.println("CTRL: Filtering...");
		
		// DEFAULT SEARCH VALUES
		String index = indexPath;
	    String field = "contents";
	    int repeat = 0;
	    boolean raw = false;
	    int hitsPerPage = 100;
	    
	    try {
	    	// search lucene index
	    	List<Document> searchResults = searchTool.searchQuery(userQuery, index, field, repeat, raw, hitsPerPage);
	    	docs = FXCollections.observableArrayList(searchResults);
	    	return docs;
	    }
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	return null;
	    }
	}
	
	public void highlight() {
		System.out.println("CTRL: Highlighting logs...");
	}
	
	
}
