package voter.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VoterActivity extends Activity implements OnClickListener {
	
	private static final String TAG = "VoterActivity";
	private Button newQuestionBtn; 
	private Button sendQuestionBtn; 
	private Button ansQuestionBtn; 
	private Button helpBtn; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        newQuestionBtn = (Button) findViewById(R.id.newQuestionBtn);
        newQuestionBtn.setOnClickListener(this);
        
        sendQuestionBtn = (Button) findViewById(R.id.sendQuestionBtn);
        sendQuestionBtn.setOnClickListener(this);
        
        ansQuestionBtn = (Button) findViewById(R.id.ansQuestionBtn);
        ansQuestionBtn.setOnClickListener(this);
        
        helpBtn = (Button) findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(this);

    }
    
    /** Handle clicks */
    public void onClick(View v) {
        switch (v.getId()) {

        // Create A New Question
        case R.id.newQuestionBtn:
            Intent createNewQuestion = new Intent(this, CreateNewQuestion.class);
            startActivity(createNewQuestion);
            break;
            
        // Send a question to a friend
        case R.id.sendQuestionBtn:
        	//Intent sendQuestion = new Intent(this, SendQuestion.class);
        	//startActivity(sendQuestion);
        	break;
            
        // Answer a received question
        case R.id.ansQuestionBtn:
        	//Intent answerQuestion = new Intent(this, AnswerQuestion.class);
        	//startActivity(answerQuestion);
        	break;
    	
        // Get some help
        case R.id.helpBtn:
        	//Intent help = new Intent(this, Help.class);
        	//startActivity(help);
        	break;
        	
        default:
            Log.e(TAG, "onClick id not found");
        }
    }

    
}