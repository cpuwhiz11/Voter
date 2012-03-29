package voter.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SelectSenders extends Activity implements OnClickListener {
	
	private static final String TAG = "SelectSenders";

	private Button send; 
	
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.selectsenders);
		
		// Populate a list of users 
		
		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(this);
	
	}
	
	public void onClick(View v) {
		switch (v.getId()) {

		// Send to people
		case R.id.send:

			//Send to selected people
			
			//Display msg that we sent questions
			Toast ts = new Toast(this);
			ts.setText("Question(s) Sent!");
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
