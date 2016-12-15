package test;

import gui.SearchTermDisplay;

public class MockSearchDisplay extends SearchTermDisplay {

	public MockSearchDisplay() { }
	
	public String text = "";
	public String searchType = "";
	public String buttonText = "";
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getSearchType() {
		return searchType;
	}

	@Override
	public String getButtonText() {
		return buttonText;
	}

}
