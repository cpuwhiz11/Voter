package voter.main;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnswerQuestion extends Activity implements OnClickListener {

	private static final String TAG = "AnswerQuestion";
	
	private EditText questionIdField; 
	
	private Button helpBtn; 
	private Button getQuestionBtn; 
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answerquestion);
		
		questionIdField = (EditText) findViewById(R.id.questionIdField);
		questionIdField.setOnClickListener(this); 
		
		helpBtn = (Button) findViewById(R.id.helpBtn);
		helpBtn.setOnClickListener(this);
		
		getQuestionBtn = (Button) findViewById(R.id.getQuestionBtn);
		getQuestionBtn.setOnClickListener(this);
	}
	
	/** Handle clicks */
	public void onClick(View v) {
		switch (v.getId()) {

		// Get some help
		case R.id.helpBtn:
			Intent help = new Intent(this, Help.class);
			startActivity(help);
			break;

		case R.id.getQuestionBtn:
			// go to the site and retrieve the question
			// pull id from field
			String id = questionIdField.getText().toString(); 

			String url = "http://129.63.70.81/getQuestion.php";

			//Package up for sending
			Map<String, String> data = new HashMap<String, String>();
			data.put("ID", id);

			//Send data
			JsonResult parser = new JsonConnection(url).post(data);

			String error = null;
			try {
			    error = parser.valueForKey("ERROR");
			} catch (NullPointerException e){
				Toast ts = Toast.makeText(this, "Could not connect to server", Toast.LENGTH_SHORT);
				ts.show();
			}
			
			//Check for error
			if(error.compareTo("NONE") != 0){
				Toast ts = Toast.makeText(this, error, Toast.LENGTH_SHORT);
				ts.show();
			} else {
				//Parse results
				String title = parser.valueForKey("Title");
				String question = parser.valueForKey("Question");
				String possibleResponses = parser.valueForKey("PossibleResponse");

				//pack up and create new intent displaying question
				Intent AnswerQuestionSend = new Intent(this, AnswerQuestionSend.class);
				Bundle questionBundle = new Bundle();

				questionBundle.putString("Title", title); 
				questionBundle.putString("Question", question); 
				questionBundle.putString("PossibleResponse", possibleResponses); 
				questionBundle.putString("ID", id);        	
				
				AnswerQuestionSend.putExtras(questionBundle); 
				startActivity(AnswerQuestionSend);
			}
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}

}
