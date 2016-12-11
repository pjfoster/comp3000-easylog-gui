package controller;

import java.util.List;

import org.apache.lucene.document.Document;

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
	
	public void filterSearch() {
		System.out.println("CTRL: Filtering...");
		
		// DEFAULT SEARCH VALUES
		String index = indexPath;
	    String field = "contents";
	    String queries = null;
	    int repeat = 0;
	    boolean raw = false;
	    String queryString = "kernel";
	    int hitsPerPage = 10;
	    
	    try {
	    	// search lucene index
	    	List<Document> searchResults = searchTool.search(index, field, queries, repeat, raw, queryString, hitsPerPage);
	    	
	    	System.out.println("PRINTING SEARCH RESULTS...");
	    	for (Document d: searchResults) {
	    		String filename = d.get("filename");
				String log = d.get("contents");
				if (filename != null && log != null) {
					System.out.println(filename + ": " + log);
				}
	    	}
	    }
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void highlight() {
		System.out.println("CTRL: Highlighting logs...");
	}
	
	
}
