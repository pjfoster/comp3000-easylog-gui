package test;

import static org.junit.Assert.*;
import javafx.collections.ObservableList;

import org.apache.lucene.document.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.LogAppController;

public class IndexingTest {

	private LogAppController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new LogAppController(null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFullIndex() {
		// set indexing details
		controller.indexData = "src/test/testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
		
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 14);
	}
	
	public void testRestrictedIndex() {
		// set indexing details
		controller.indexData = "testLogs/";
		controller.indexPath = "/Users/PJF/Desktop/test_index2/";
		controller.fileExpression = "wifi_test.log";
				
		controller.createIndex();
		
		ObservableList<Document> allLogs = controller.allLogs();
		assertEquals(allLogs.size(), 5);
	}

}
