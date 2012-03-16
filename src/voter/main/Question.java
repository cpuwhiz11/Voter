package voter.main;

import java.util.ArrayList;

/* Question Class */

public class Question {

	private String title; 
	
	private String content;
	
	private ArrayList<String> possibleResponses;
	
	private String owner;
	
	private int selectedResponse = 0;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public ArrayList<String> getPossibleResponses() {
		return possibleResponses;
	}
	
	public void addPossibleResponses(ArrayList<String> possibleResponses) {
		this.possibleResponses = possibleResponses; 
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getSelectedResponse() {
		return selectedResponse;
	}

	public void setSelectedResponse(int selectedResponse) {
		this.selectedResponse = selectedResponse;
	}
	
}
