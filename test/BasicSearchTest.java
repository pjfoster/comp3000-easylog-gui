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
		
		MockSearchDisplay dummyDisplay = new MockSearchDisplay();
		dummyDisplay.text = "kernel";
		dummyDisplay.searchType = "String";
		
		ArrayList<SearchTermDisplay> wrapper = new ArrayList<SearchTermDisplay>();
		wrapper.add(dummyDisplay);
		
		Query q = queryCreator.createQuery(wrapper, "contents");
		
		ObservableList<Document> results = controller.filterSearch(q);
		
		assertEquals(2, results.size());
	
	}
	
	@Test
	public void regexSearchTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void subStringSearchTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void fuzzySearchTest() {
		fail("Not yet implemented");
	}

}