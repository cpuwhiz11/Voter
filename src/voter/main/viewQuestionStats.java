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

public class viewQuestionStats extends Activity implements OnClickListener{

	private static final String TAG = "viewQuestionStats";

	//Buttons
	private Button helpBtn; 
	private Button retrieveQuestionBtn; 
	
	private EditText questionNumField; 

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewquestionstats);

	    retrieveQuestionBtn = (Button) findViewById(R.id.retrieveQuestionBtn);
		retrieveQuestionBtn.setOnClickListener(this); 
		
		//helpBtn = (Button) findViewById(R.id.helpBtn);
		//helpBtn.setOnClickListener(this);
			
		questionNumField = (EditText) findViewById(R.id.questionNumField);
		questionNumField.setOnClickListener(this); 
	}

	public void onClick(View v) {
		switch (v.getId()) {

		// Send to people
		case R.id.retrieveQuestionBtn:

			// go to the site and retrieve the question
			// pull id from field
			String id = questionNumField.getText().toString(); 
			
			String url = "http://129.63.70.81/getQuestion.php";

			//Package up for sending
			Map<String, String> data = new HashMap<String, String>();
			data.put("ID", id);
			
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
			} else {
				
				//Parse results
				String title = parser.valueForKey("Title");
				String question = parser.valueForKey("Question");
				String possibleResponses = parser.valueForKey("PossibleResponse");
				String response = parser.valueForKey("Responses");
				
				//pack up and create new intent displaying question
	        	Intent ViewingStats = new Intent(this, ViewingStats.class);
	        	Bundle questionBundle = new Bundle();

	        	questionBundle.putString("Title", title); 
	        	questionBundle.putString("Question", question); 
	        	questionBundle.putString("PossibleResponse", possibleResponses); 
	        	questionBundle.putString("ID", id); 
	        	questionBundle.putString("Responses", response);
	        	
	        	ViewingStats.putExtras(questionBundle); 
	        	startActivity(ViewingStats);
	        	
				//DEBUG STUFF
				//String title = parser.valueForKey("Title");
				//Toast ts = Toast.makeText(this, title, Toast.LENGTH_SHORT);
				//ts.show();
				
	        	break;
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
