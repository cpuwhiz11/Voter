package voter.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class ViewingStats extends Activity implements OnClickListener{

	private static final String TAG = "ViewingStats";

	//Buttons
	private Button helpBtn; 
	
	//Fields to enter information about this question
	private EditText titleField; 
	private EditText entryField; 
	private EditText ansField; 
	
	private TextView noAns; 
	
	// field of the id, uneditable, int in that field
	private EditText questionIdField; 
	
	//Adapter to Handle data
	private ArrayAdapter<String> adapter;
	
	//View that contains the list of possible answers
	private ListView possibleAnsList;  
	private ListView ansList; 

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewingstats);
		
		helpBtn = (Button) findViewById(R.id.helpBtn);
		helpBtn.setOnClickListener(this);
		
		//Get the bundle
		Bundle extras = getIntent().getExtras(); 
		
		String title = extras.getString("Title");
		String question = extras.getString("Question");
		
		//Toast ts = Toast.makeText(this, title, Toast.LENGTH_SHORT);
		//ts.show();
		
		//Get selected answers if any
		List<String> answers = null;
		String answersBeforeCleaning = extras.getString("Responses");
		
		noAns = (TextView)findViewById(R.id.noAns);	
		if(answersBeforeCleaning.compareTo("NONE") == 0){
			//No answers
		} else {
			//Hide TextView saying no answers
			noAns.setVisibility(View.GONE);
			//Clean out "" and split based on commas
			answersBeforeCleaning = answersBeforeCleaning.replaceAll("\"", ""); 
			answers = Arrays.asList(answersBeforeCleaning.substring(1, answersBeforeCleaning.length() - 1).split(",")); 
		}		
			
		//Get list of possible answers
		List<String> possibleResponse = Arrays.asList(extras.getString("PossibleResponse").split(","));
		
		//Parse the list to remove ugly things
		for(int i = 0; i < possibleResponse.size(); i++){
			String noClean;
			
			if(i == 0){
			    noClean = possibleResponse.get(0);
				noClean = noClean.substring(4);
				possibleResponse.set(0, noClean);
				continue;
			}
			
			if(i == possibleResponse.size() - 1){
			    noClean = possibleResponse.get(possibleResponse.size() - 1);
				noClean = noClean.substring(4, noClean.length() - 1);
				possibleResponse.set(possibleResponse.size() - 1, noClean);
				continue;
			}
			
			noClean = possibleResponse.get(i);
			noClean = noClean.substring(3);
			possibleResponse.set(i, noClean);
			
		}
		
		//Calculate question stats
		List<String> ansStatList = new ArrayList<String>(); 
		int responseSize = possibleResponse.size();
		int answersToCheck = answers.size();
		int numAnswered = 0; 
		int i = 0, j = 0;
		
		for(i = 0; i < responseSize ; ++i){
			for(j = 0; j < answersToCheck; ++j){
				if(i == Integer.parseInt(answers.get(j))) numAnswered++;
			}
			ansStatList.add(possibleResponse.get(i) + ", Answered: " + String.valueOf(numAnswered));
			numAnswered = 0; 
		}
		
		
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
		
		possibleAnsList = (ListView) findViewById(R.id.listResponses); 
		
		//Setup List possible response stuff
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				possibleResponse);
		possibleAnsList.setAdapter(adapter); 
		 
		ansList = (ListView) findViewById(R.id.listAnswers); 
		
		//Setup List answer stuff if needed
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				ansStatList);
		ansList.setAdapter(adapter); 
		
	}

	public void onClick(View v) {
		
		switch (v.getId()) {

		// Get some help
		case R.id.helpBtn:
			Intent help = new Intent(this, Help.class);
			startActivity(help);
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}
	
}
