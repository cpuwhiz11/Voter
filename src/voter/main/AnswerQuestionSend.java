package voter.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerQuestionSend extends Activity implements OnClickListener {

	private static final String TAG = "AnswerQuestionSend";
	
	//Fields to enter information about this question
	private TextView titleField; 
	private EditText ansField; 
	private TextView entryField; 
	
	// field of the id, uneditable, int in that field
	private TextView questionIdField; 
	
	//Adapter to Handle data
	private ArrayAdapter<String> adapter;
	
	//View that contains the list of possible answers
	private ListView ansList;  
	
	//Buttons
	private Button helpBtn; 
	private Button ansBtn; 
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answerquestionsend);

		//Setup buttons
		helpBtn = (Button) findViewById(R.id.helpBtn);
		helpBtn.setOnClickListener(this);
		
		ansBtn = (Button) findViewById(R.id.ansBtn);
		ansBtn.setOnClickListener(this);
		
		//Get the bundle
		Bundle extras = getIntent().getExtras(); 

		String title = extras.getString("Title");
		String question = extras.getString("Question");

		//Toast ts = Toast.makeText(this, title, Toast.LENGTH_SHORT);
		//ts.show();

		//This might be the worse thing I have ever done FIXME
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

		String id = extras.getString("ID");

		//Fill the fields up 
		titleField = (TextView) findViewById(R.id.titleField);
		entryField = (TextView) findViewById(R.id.entryField);
		ansField = (EditText) findViewById(R.id.possibleAnsField);
		questionIdField = (TextView) findViewById(R.id.questionIdField);

		titleField.setText(title);
		titleField.setFocusable(false); 	

		entryField.setText(question);
		entryField.setFocusable(false); 	

		questionIdField.setText(id);
		questionIdField.setFocusable(false); 	

		ansList = (ListView) findViewById(R.id.listResponses); 

		//Setup List stuff
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, 
				possibleResponse);
		ansList.setAdapter(adapter); 
		ansList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.ansBtn:
			//Get selected answers
			SparseBooleanArray answers = ansList.getCheckedItemPositions();
			if(answers == null){
				Toast ts = Toast.makeText(this, "You did not select any answers", Toast.LENGTH_SHORT);
				ts.show();
			} else {
				for(int i = 0; i < answers.size(); i++){
					
					if(answers.get(i) == false){
						continue;
					}
					
					String url = "http://129.63.70.81/setResponse.php";

					//Package up for sending
					Map<String, String> data = new HashMap<String, String>();
					data.put("ID", questionIdField.getText().toString());
					data.put("ANS", String.valueOf(i)); 

					//Send data
					JsonResult parser = new JsonConnection(url).post(data);

					String error = null;

					//Check result
					try {
						error = parser.valueForKey("ERROR");
					} catch (NullPointerException e){
						Toast ts = Toast.makeText(this, "Could not connect to server", Toast.LENGTH_SHORT);
						ts.show();
						break;
					}

					//Check for error
					if(error.compareTo("NONE") != 0){
						Toast ts = Toast.makeText(this, error, Toast.LENGTH_SHORT);
						ts.show();
						break;
					} else {
						Toast ts = Toast.makeText(this, "Answer(s) posted", Toast.LENGTH_SHORT);
						ts.show();	
					}
				}
			}
			break;
			
		
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
