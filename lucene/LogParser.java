package lucene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

public class LogParser {

	public int MAX_PER_FILE;
	
	public LogParser(int max) {
		MAX_PER_FILE = max;
	}
	
	public ArrayList<String> getLogsFromFile(InputStream stream, Field pathField, Path file, String filename, IndexWriter writer) throws IOException {
		
		  BufferedReader txtReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
	      int counter = 0;
	      
    	  String currLog = null;
	      
    	  // iterate through each line of text in the log
	      while (true) {
	    	  String line = txtReader.readLine();
	    	  if (line != null) { 
	    		  if (isNewLog(line, filename)) {
	    			  
	    			  // create a document with the previously aggregated log entry
	    			  if (currLog != null) {
		    			  Document newLog = new Document();
		    			  newLog.add(pathField);
		    			  newLog.add(new StringField("filename", filename, Field.Store.YES));
		    			  newLog.add(new TextField("contents", currLog, Field.Store.YES));
		    			  IndexFiles.addDocToIndex(writer, newLog, file);
		    			  counter++;
	    			  }
	    			  
	    			  // start aggregating a new log entry
	    			  currLog = line;
	    		  } else {
	    			  // since the segment read is just a piece of a log, add it to current log
	    			  currLog += ("\n" + line);
	    		  }
	    	  }
	    	  // if line == null, we have reached the end of the log file
	    	  else {
	    		  
	    		  // create last documents
	    		  if (currLog != null) {
	    			  Document newLog = new Document();
	    			  newLog.add(pathField);
	    			  newLog.add(new StringField("filename", filename, Field.Store.YES));
	    			  newLog.add(new TextField("contents", currLog, Field.Store.YES));
	    			  IndexFiles.addDocToIndex(writer, newLog, file);
	    			  counter++;
    			  }
	    		  
	    		  break;
	    	  }
	    	  if (counter >= MAX_PER_FILE) break;  
	      }
	      //System.out.println("COUNT FOR " + file.toString() + ": " + counter);
		
		return null;
	}
	
	public boolean isNewLog(String log, String filename) {
		
		// deal with known special cases
		if (filename.equals("commerce.log") || filename.equals("install.log") || filename.equals("system.log") ||
			filename.equals("commerce_test.log") || filename.equals("system_test.log")) {
			
			if (log.startsWith("Jan") || log.startsWith("Feb") || log.startsWith("Mar") ||
				log.startsWith("Apr") || log.startsWith("May") || log.startsWith("Jun") ||
				log.startsWith("Jul") || log.startsWith("Aug") || log.startsWith("Sep") ||
				log.startsWith("Oct") || log.startsWith("Nov") || log.startsWith("Dec")) {
				return true;
			}
			
			return false;
		}
		
		
		else if (filename.equals("fsck_hfs.log")) {
			return true;
		}
		
		// default parsing (split at new lines)
		else {
			return true;
		}
	}
	
}
