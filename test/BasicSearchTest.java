package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import lucene.QueryCreator;
import gui.SearchTermDisplay;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.LogAppController;

public class BasicSearchTest {

	private LogAppController controller;
	private QueryCreator queryCreator;
	
	@Before
	public void setUp() throws Exception {
		controller = new LogAppController(null);
		queryCreator = new QueryCreator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void stringSearchTest() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay dummyDisplay = new MockSearchDisplay();
		dummyDisplay.text = "kernel";
		dummyDisplay.searchType = "String";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(dummyDisplay);
		
		Query q = queryCreator.createQuery(wrapper, "logs");
		
		ObservableList<Document> results = controller.filterSearch(q);
		
		assertEquals(3, results.size());
	
	}
	
	@Test
	public void regexSearchTest() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay dummyDisplay = new MockSearchDisplay();
		dummyDisplay.text = "[kp]ernel";
		dummyDisplay.searchType = "Regex";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(dummyDisplay);
		
		Query q = queryCreator.createQuery(wrapper, "logs");
		
		ObservableList<Document> results = controller.filterSearch(q);
		
		assertEquals(3, results.size());
	}
	
	@Test
	public void subStringSearchTest() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay dummyDisplay = new MockSearchDisplay();
		dummyDisplay.text = "store";
		dummyDisplay.searchType = "Substring";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(dummyDisplay);
		
		Query q = queryCreator.createQuery(wrapper, "logs");
		
		ObservableList<Document> results = controller.filterSearch(q);
		
		assertEquals(2, results.size());
	}
	
	@Test
	public void fuzzySearchTest() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay dummyDisplay = new MockSearchDisplay();
		dummyDisplay.text = "paur"; // should match 'pair'
		dummyDisplay.searchType = "Fuzzy";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(dummyDisplay);
		
		Query q = queryCreator.createQuery(wrapper, "logs");
		
		ObservableList<Document> results = controller.filterSearch(q);
		
		assertEquals(2, results.size());
	}
	
	@Test
	public void searchFilename() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay dummyDisplay = new MockSearchDisplay();
		dummyDisplay.text = "wifi"; // should match 'pair'
		dummyDisplay.searchType = "Substring";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(dummyDisplay);
		
		Query q = queryCreator.createQuery(wrapper, "filename");
		
		ObservableList<Document> results = controller.filterSearch(q);
		
		assertEquals(5, results.size());
	}

}
