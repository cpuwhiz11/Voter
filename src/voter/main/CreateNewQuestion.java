package voter.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewQuestion extends Activity implements OnClickListener {

	private static final String TAG = "CreateNewQuestionActivity";
	private Button addAnsBtn;
	private Button saveBtn; 
	private Button helpBtn; 
	
	private EditText titleField; 
	private EditText entryField; 
	
	//private ArrayList<String> listItems = new ArrayList<String>();
	//private int clickCounter = 0;

	//private ListView listView; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createnewquestion);

		titleField = (EditText) findViewById(R.id.titleField);
		entryField = (EditText) findViewById(R.id.entryField);
		
		addAnsBtn = (Button) findViewById(R.id.addAnsBtn);
		addAnsBtn.setOnClickListener(this);

		saveBtn = (Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(this);

		helpBtn = (Button) findViewById(R.id.helpBtn);
		helpBtn.setOnClickListener(this);
		
		//Create list of possible responses, initially it is empty
		//new ArrayAdapter<String>(this, R.id.listResponses, possibleResponse));
		//listView =(ListView) findViewById(R.id.possibleResponses);

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
			//Add new blank ans to list
			 //listItems.add("STUFF" +clickCounter++);
			 //listView.addFocusables(listItems, 1); 
			//Display new blank editable ans on list
			
			break;

	    // Save the question
		case R.id.saveBtn:
			
			//Package up question
			Question question = new Question(); 
			
			//Save strings
			question.setTitle(titleField.getText().toString()); 
			question.setContent(entryField.getText().toString());
			
			//Save possible responses
			
			//Save owner data
			//question.setOwner(SOMETHING);
			
			//Display msg that we saved the question
			Toast ts = new Toast(this);
			ts.setText("Question Saved");
			ts.show();
			
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}

}
