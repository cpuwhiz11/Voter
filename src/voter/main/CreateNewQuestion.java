package voter.main;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
	
	// field of the id, uneditable, int in that field
	private EditText questionIdField; 
	private int randomInt; 
	
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
		
		questionIdField = (EditText) findViewById(R.id.questionIdField);
		questionIdField.setFocusable(false); 
		
		//Autogenerate id number 
		//TODO will need to query site so that number does not already exist
		//will return an array of existing ints, check to see that random int
		//is not in the list
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(10000); 
		questionIdField.setText(String.valueOf(randomInt));


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
			
			//Save questionId 
			question.setQuestionId(randomInt);
			
			//Push packaged question to website
			saveDataOnline(question); 
			
			//Display msg that we saved the question
			Toast ts = Toast.makeText(this, ("Question Online with ID:" + questionIdField.toString()), Toast.LENGTH_SHORT);
			//ts.show();
			
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}


	private void saveDataOnline(Question question) {
		String url = "http://129.63.70.103/setQuestion.php";

		//Package up for sending
		Map<String, String> data = new HashMap<String, String>();
		data.put("Title", question.getTitle());
		data.put("Question", question.getContent());
		data.put("PossibleResponses", question.getPossibleResponses().toString());
		data.put("ID", String.valueOf(question.getQuestionId()));

		//Send data
		JsonResult parser = new JsonConnection(url).post(data);

		//Check result
		String result = parser.valueForKey("ID");

		Toast ts = Toast.makeText(this, "Saved under question id:" + result, Toast.LENGTH_SHORT);
		ts.show();
	}

}
