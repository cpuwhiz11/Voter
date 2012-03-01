package voter.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreateNewQuestion extends Activity implements OnClickListener {

	private static final String TAG = "CreateNewQuestionActivity";
	private Button addAnsBtn;
	private Button saveBtn; 
	private Button helpBtn; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		addAnsBtn = (Button) findViewById(R.id.addAnsBtn);
		addAnsBtn.setOnClickListener(this);

		saveBtn = (Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(this);

		helpBtn = (Button) findViewById(R.id.helpBtn);
		helpBtn.setOnClickListener(this);

	}

	/** Handle clicks */
	public void onClick(View v) {
		switch (v.getId()) {

		// Get some help
		case R.id.helpBtn:
			//Intent help = new Intent(this, Help.class);
			//startActivity(help);
			break;

	    // Add a possible answer
		case R.id.addAnsBtn:
			break;

	    // Save the question
		case R.id.saveBtn:
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}

}
