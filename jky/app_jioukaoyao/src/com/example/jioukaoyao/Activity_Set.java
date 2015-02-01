package com.example.jioukaoyao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Activity_Set extends Activity {

	private ArrayAdapter<String> listPlace;
	private Handler ThreadHandler;
	private HandlerThread updateThread;
	private String[] allRestaurant;
	private TextView restChoice;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		Button goChooseButton=(Button) findViewById(R.id.goFriendButton);
		goChooseButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(
						Activity_Set.this,
						Activity_Friend.class);
				Activity_Set.this
						.startActivity(intent);
			}
		});
		restChoice=(TextView)findViewById(R.id.restaurant_choice);
		ImageButton newPlaceButton=(ImageButton) findViewById(R.id.newPlaceButton);
		newPlaceButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				for(int i=0;i<allRestaurant.length;i++){
					listPlace.add(allRestaurant[i]);
				}
				show_place_list();
			}
		});
		listPlace = new ArrayAdapter<String>(this, R.layout.custom);
		updateThread = new HandlerThread("update");
		updateThread.start();
		ThreadHandler = new Handler(updateThread.getLooper());
		ThreadHandler.post(downloadRestThread);
	}
	
	private Runnable downloadRestThread = new Runnable() {
		public void run() {
			Class_download_restaurant downloadRest = new Class_download_restaurant();
			allRestaurant= downloadRest.rest_list();
		}
	};
	
	private void show_place_list()
	{
		AlertDialog.Builder placeWindow = new AlertDialog.Builder(
				Activity_Set.this);
		placeWindow.setTitle("Choose a place.");
		placeWindow.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		placeWindow.setAdapter(listPlace,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String selectedObject = listPlace.getItem(which);
						
						restChoice.setText(selectedObject);
					}
				});
		placeWindow.show();
		
	}		
	
}
