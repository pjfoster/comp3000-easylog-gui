package test;

import static org.junit.Assert.*;
import gui.SearchTermDisplay;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import lucene.QueryCreator;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.LogAppController;

public class SearchTests {

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
	public void boolean2TermTest() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay d1 = new MockSearchDisplay();
		d1.text = "kernel";
		d1.searchType = "Substring";
		d1.buttonText = "AND";
		
		MockSearchDisplay d2 = new MockSearchDisplay();
		d2.text = "airport";
		d2.searchType = "Substring";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(d1);
		wrapper.add(d2);
		
		Query q = queryCreator.createQuery(wrapper, "logs");
		ObservableList<Document> results = controller.filterSearch(q);
		assertEquals(1, results.size());
		
		d1.buttonText = "OR";
		
		q = queryCreator.createQuery(wrapper, "logs");
		results = controller.filterSearch(q);
		assertEquals(4, results.size());
	}
	
	@Test
	public void boolean3TermTest() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
		
		MockSearchDisplay d1 = new MockSearchDisplay();
		d1.text = "kernel";
		d1.searchType = "Substring";
		d1.buttonText = "AND";
		
		MockSearchDisplay d2 = new MockSearchDisplay();
		d2.text = "airport";
		d2.searchType = "Substring";
		d2.buttonText = "AND";
		
		MockSearchDisplay d3 = new MockSearchDisplay();
		d3.text = "bluetooth";
		d3.searchType = "Substring";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(d1);
		wrapper.add(d2);
		wrapper.add(d3);
		
		//AND, AND
		Query q = queryCreator.createQuery(wrapper, "logs");
		ObservableList<Document> results = controller.filterSearch(q);
		assertEquals(0, results.size());
		
		// OR, AND
		d1.buttonText = "OR";
		q = queryCreator.createQuery(wrapper, "logs");
		results = controller.filterSearch(q);
		assertEquals(1, results.size());
		
		// OR, OR
		d2.buttonText = "OR";
		q = queryCreator.createQuery(wrapper, "logs");
		results = controller.filterSearch(q);
		assertEquals(4, results.size());
		
		// AND, OR
		d1.buttonText = "AND";
		q = queryCreator.createQuery(wrapper, "logs");
		results = controller.filterSearch(q);
		assertEquals(2, results.size());

	}
	

}
