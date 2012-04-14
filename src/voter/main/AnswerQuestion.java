package voter.main;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AnswerQuestion extends Activity implements OnClickListener {

	private static final String TAG = "AnswerQuestion";
	
	private EditText questionIdField; 
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answerquestion);
		
		questionIdField = (EditText) findViewById(R.id.questionIdField);
		questionIdField.setOnClickListener(this); 
		
		
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

			String url = "http://129.63.70.103/setResponse.php";

			//Package up for sending
			Map<String, String> data = new HashMap<String, String>();
			data.put("ID", id);

			//Send data
			JsonResult parser = new JsonConnection(url).post(data);

			//Check result
			String result = parser.valueForKey("ID");


			break;

			// Get Send responses 
		case R.id.send:

			//Send responses back to owners

			//Display msg that we sent questions
			Toast ts = new Toast(this);
			ts.setText("Responses(s) Sent!");
			ts.show();

			//Return to main page
			Intent VoterActivity = new Intent(this, VoterActivity.class);
			startActivity(VoterActivity);
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}

}
