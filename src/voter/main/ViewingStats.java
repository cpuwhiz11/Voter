package voter.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


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
		setContentView(R.layout.viewingstats);
		
		//Get the bundle
		Bundle extras = getIntent().getExtras(); 
		
		String title = extras.getString("Title");
		String question = extras.getString("Question");
		
		Toast ts = Toast.makeText(this, title, Toast.LENGTH_SHORT);
		ts.show();
		
		//This might be the worse thing I have ever done FIXME
		List<String> possibleResponse = Arrays.asList(extras.getString("PossibleResponse"));

		String id = extras.getString("ID");
		
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
		/*
		//Setup List stuff
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				possibleResponse);
		ansList.setAdapter(adapter); 
		*/ 
		
		
		
	}
	
}
