package lucene;

import gui.SearchTermDisplay;

import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.WildcardQuery;

public class QueryCreator {

	public QueryCreator() { }
	
	/**
	 * Creates a Lucene Query based on the user's selected preferences
	 * @param searchTerms
	 * @return
	 */
	public Query createQuery(ArrayList<SearchTermDisplay> searchTerms, String searchField) {
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		
		String field = null;
		if (searchField.trim().toLowerCase().equals("logs")) {
			field = "contents";
		} else {
			field = "filename";
		}
		
		
		if (searchTerms.size() == 1) {
			return miniQuery(field, searchTerms.get(0));
		}
		
		else if (searchTerms.size() == 2) {
			Query q1 = miniQuery(field, searchTerms.get(0));
			Query q2 = miniQuery(field, searchTerms.get(1));
			
			if (searchTerms.get(0).getButtonText().equals("OR"))  {
				builder.add(q1, BooleanClause.Occur.SHOULD);
				builder.add(q2, BooleanClause.Occur.SHOULD);
			} else {
				builder.add(q1, BooleanClause.Occur.MUST);
				builder.add(q2, BooleanClause.Occur.MUST);
			}
		}
		
		else if (searchTerms.size() == 3) {
			
			if (searchTerms.get(0).getButtonText().equals("OR") &&
				searchTerms.get(1).getButtonText().equals("OR")) {
				for (int i = 0; i < searchTerms.size(); i++) {
					Query q = miniQuery(field, searchTerms.get(i));
					if (q == null) { break; }
					builder.add(q, BooleanClause.Occur.SHOULD);
				}
			}
			
			if (searchTerms.get(0).getButtonText().equals("AND") &&
				searchTerms.get(1).getButtonText().equals("OR")) {
				BooleanQuery.Builder subClause = new BooleanQuery.Builder();
				
				Query q1 = miniQuery(field, searchTerms.get(0));
				Query q2 = miniQuery(field, searchTerms.get(1));
				Query q3 = miniQuery(field, searchTerms.get(2));
				
				subClause.add(q2, BooleanClause.Occur.SHOULD);
				subClause.add(q3, BooleanClause.Occur.SHOULD);
				
				builder.add(q1, BooleanClause.Occur.MUST);
				builder.add(subClause.build(), BooleanClause.Occur.MUST);
				
			}
			
			if (searchTerms.get(0).getButtonText().equals("OR") &&
				searchTerms.get(1).getButtonText().equals("AND")) {
				BooleanQuery.Builder subClause = new BooleanQuery.Builder();
				Query q1 = miniQuery(field, searchTerms.get(0));
				Query q2 = miniQuery(field, searchTerms.get(1));
				Query q3 = miniQuery(field, searchTerms.get(2));
				
				subClause.add(q1, BooleanClause.Occur.SHOULD);
				subClause.add(q2, BooleanClause.Occur.SHOULD);
				
				builder.add(q3, BooleanClause.Occur.MUST);
				builder.add(subClause.build(), BooleanClause.Occur.MUST);
			}
			
			if (searchTerms.get(0).getButtonText().equals("AND") &&
				searchTerms.get(1).getButtonText().equals("AND")) {
				for (int i = 0; i < searchTerms.size(); i++) {
					Query q = miniQuery(field, searchTerms.get(i));
					if (q == null) { break; }
					builder.add(q, BooleanClause.Occur.MUST);
				}
			}
			
		}
		
		return builder.build();
	}
	
	/**
	 * Creates a single Query object
	 * @param currDisplay
	 * @return
	 */
	public Query miniQuery(String field, SearchTermDisplay currDisplay) {
		
		if (currDisplay.getText().trim().equals("")) {
			return null;
		}
		
		PhraseQuery.Builder builder = new PhraseQuery.Builder();
		Query q = null;
		
		if (currDisplay.getSearchType().equals("String")) {
			builder.add(new Term(field, currDisplay.getText()));
			q = builder.build();
			
		} else if (currDisplay.getSearchType().equals("Regex")) {
			q = new RegexpQuery(new Term(field, currDisplay.getText()));
			
		} else if (currDisplay.getSearchType().equals("Substring")) {
			String text = "*" + currDisplay.getText() + "*";
			q = new WildcardQuery(new Term(field, text));
			
		} else if (currDisplay.getSearchType().equals("Fuzzy")) {
			q = new FuzzyQuery(new Term(field, currDisplay.getText()));
		}
		
		return q;
	}
	
}
