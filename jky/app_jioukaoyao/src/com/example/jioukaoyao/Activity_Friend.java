package com.example.jioukaoyao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Activity_Friend extends Activity {
	private ListView userListView;
	private Handler ThreadHandler;
	private HandlerThread updateThread;
	private String[] downloadUserResult;
	private ArrayAdapter<String> listAdapter;
	private NumberPicker bumbSecPick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		userListView = (ListView) findViewById(R.id.friend_list_View);
		bumbSecPick = (NumberPicker) findViewById(R.id.bombTimePicker);
		bumbSecPick
				.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		final NumberPicker np = (NumberPicker) findViewById(R.id.bombTimePicker);
		String[] nums = new String[32];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.toString(i);
		np.setMinValue(0);
		np.setMaxValue(30);
		np.setWrapSelectorWheel(false);
		np.setDisplayedValues(nums);
		np.setValue(1);
		Button goChooseButton = (Button) findViewById(R.id.goChooseButton);
		goChooseButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				int len = userListView.getCount();
				int[] selectedUser = new int[len+1];
				int a = 0;
				SparseBooleanArray checked = userListView
						.getCheckedItemPositions();
				for (int i = 0; i < len; i++) {
					if (checked.get(i)) {
						// Log.w("Test",String.valueOf(i));
						selectedUser[a] = i;
						a++;
					}
				}
				Intent intent = new Intent(Activity_Friend.this,
						Activity_Choose.class);
				Bundle bundle = new Bundle();
				bundle.putInt("bombSec", np.getValue());
				intent.putExtras(bundle);
				Activity_Friend.this.startActivity(intent);
			}
		});
		updateThread = new HandlerThread("update");
		updateThread.start();
		ThreadHandler = new Handler(updateThread.getLooper());
		ThreadHandler.post(downloadUserThread);
	}

	private Runnable downloadUserThread = new Runnable() {
		public void run() {
			Class_download_user downloadUser = new Class_download_user();
			downloadUserResult = downloadUser.user_list();
			Message message = new Message();
			message.what = 1;
			downloadUserHandler.sendMessage(message);
		}
	};

	private Handler downloadUserHandler = new Handler() { // Timer §ó·s UI
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				listAdapter = new ArrayAdapter(Activity_Friend.this,
						android.R.layout.simple_list_item_multiple_choice,
						downloadUserResult);
				userListView.setAdapter(listAdapter);
				userListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				userListView.setItemsCanFocus(false);

				break;
			}
		}
	};

}
