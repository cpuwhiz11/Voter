package voter.main;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class AddAnswers extends ListActivity {


	//Strings to hold data
	ArrayList<String> listItems = new ArrayList<String>();

	//Handle data
	ArrayAdapter<String> adapter;

	int clickCounter = 0;
	
	//Content of answer
	private EditText entryField; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addanswers);
		
		adapter = new ArrayAdapter<String>(this,
				  android.R.layout.simple_list_item_1,
				  listItems);
		setListAdapter(adapter);
		
		entryField = (EditText) findViewById(R.id.entryField);
		
	}

	//Add Items to list
	public void addItems(View v) {
		listItems.add(clickCounter++ + " : " + entryField.getText());
		adapter.notifyDataSetChanged();
	}
	
	/* Override back arrow to bundle data up for CreateNewQuestion */ 
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){

            //Bundle up list for CreateNewQuestion activity
			Intent addAns = new Intent(this, CreateNewQuestion.class);
			addAns.putExtra("LIST", listItems); 	
			
			
			//return true;
			return super.onKeyDown(keyCode, event);

		}
		return super.onKeyDown(keyCode, event);
	}

}

