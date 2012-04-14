package voter.main;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class ViewingStats extends Activity {

	private static final String TAG = "ViewingStats";
	
	//Fields to enter information about this question
	private EditText titleField; 
	private EditText ansField; 
	private EditText entryField; 
	
	// field of the id, uneditable, int in that field
	private EditText questionIdField; 
	
	//Adapter to Handle data
	private ArrayAdapter<String> adapter;
	
	//View that contains the list of possible answers
	private ListView ansList;  

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewquestionstats);
		
		String title = (String) savedInstanceState.get("Title");
		String question = (String) savedInstanceState.get("Question");
		
		//This might be the worse thing I have ever done FIXME
		ArrayList<String> possibleResponse = (ArrayList<String>) savedInstanceState.get("PossibleResponse");

		String id = (String) savedInstanceState.get("ID");
		
		//Fill the fields up 
		titleField = (EditText) findViewById(R.id.titleField);
		entryField = (EditText) findViewById(R.id.entryField);
		ansField = (EditText) findViewById(R.id.possibleAnsField);
		questionIdField = (EditText) findViewById(R.id.questionIdField);
		
		titleField.setText(title);
		titleField.setFocusable(false); 	
		
		entryField.setText(question);
		entryField.setFocusable(false); 	
		
		questionIdField.setText(id);
		questionIdField.setFocusable(false); 	
		
		ansList = (ListView) findViewById(R.id.listResponses); 
		
		//Setup List stuff
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				possibleResponse);
		ansList.setAdapter(adapter); 
		
		
		
	}
	
}
