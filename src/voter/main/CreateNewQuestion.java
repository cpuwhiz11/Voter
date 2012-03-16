package voter.main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CreateNewQuestion extends Activity implements OnClickListener {

	private static final String TAG = "CreateNewQuestionActivity";
	
	//Buttons
	private Button addAnsBtn;
	private Button saveBtn; 
	private Button helpBtn; 
	
	//Fields to enter information about this question
	private EditText titleField; 
	private EditText ansField; 
	private EditText entryField; 
	
	//View that contains the list of possible answers
	private ListView ansList;  
	
	//Test string to make sure fields are filled
	private String testValue;
	
	//Adapter to Handle data
	private ArrayAdapter<String> adapter;
	
	//Strings to hold data
	private ArrayList<String> listItems = new ArrayList<String>();
	
	//Counter for how many possible answers 
	private int clickCounter = 0 ; 


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createnewquestion);
		
		titleField = (EditText) findViewById(R.id.titleField);
		entryField = (EditText) findViewById(R.id.entryField);
		ansField = (EditText) findViewById(R.id.possibleAnsField);
				
		addAnsBtn = (Button) findViewById(R.id.addAnsBtn);
		addAnsBtn.setOnClickListener(this);

		saveBtn = (Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(this);

		helpBtn = (Button) findViewById(R.id.helpBtn);
		helpBtn.setOnClickListener(this);
		
		ansList = (ListView) findViewById(R.id.listResponses); 
		
		//Setup List stuff
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				listItems);
		ansList.setAdapter(adapter); 


	}
	

	/** Handle clicks */
	public void onClick(View v) {
		switch (v.getId()) {
		
		// Get some help
		case R.id.helpBtn:
			Intent help = new Intent(this, Help.class);
			startActivity(help);
			break;

	    // Add a possible answer
		case R.id.addAnsBtn:
			
			//Check for blank field
		    testValue = ansField.getText().toString();	
			if (testValue == null || testValue.equals("")) {
				Toast ts = Toast.makeText(this, "Please enter a possible response!", Toast.LENGTH_SHORT);
				ts.show();
				break; 
			}
			
            //Take value from possible response field
			listItems.add(clickCounter++ + " : " + ansField.getText()); 
			
			//Clear field
			ansField.setText(""); 
			
			//Update List
			adapter.notifyDataSetChanged();
			
			break;

	    // Save the question
		case R.id.saveBtn:
			
			//TODO consider changing these to AlertDialogs 
			//Check for blank title
		    testValue = titleField.getText().toString();	
			if (testValue == null || testValue.equals("")) {
				Toast ts = Toast.makeText(this, "Please enter a title!", Toast.LENGTH_SHORT);
				ts.show();
				break; 
			}
			
			//Check for blank question
		    testValue = entryField.getText().toString();	
			if (testValue == null || testValue.equals("")) {
				Toast ts = Toast.makeText(this, "Please enter a question!", Toast.LENGTH_SHORT);
				ts.show();
				break; 
			}
			
			//Check for empty ans list
			if (listItems.size() < 0) {
				Toast ts = Toast.makeText(this, "Please enter atleast one possible answer!", Toast.LENGTH_SHORT);
				ts.show();
				break; 
			}
			
			//Package up question
			Question question = new Question(); 
			
			//Save strings
			question.setTitle(titleField.getText().toString()); 
			question.setContent(entryField.getText().toString());
			
			//Save possible responses
			question.addPossibleResponses(listItems); 
			
			//Save owner data
			//FIXME Possible if a user is using a sim card that this will return null
			//also possible to get the wrong number as this number may persist across phone wipes
			String mPhoneNumber = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getLine1Number();
            question.setOwner(mPhoneNumber);
			
			//Display msg that we saved the question
			Toast ts = Toast.makeText(this, "Question Saved", Toast.LENGTH_SHORT);
			ts.show();
			
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}

}
