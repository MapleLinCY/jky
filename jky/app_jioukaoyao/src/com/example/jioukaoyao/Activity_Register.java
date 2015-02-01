package com.example.jioukaoyao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Register extends Activity {

	private Handler ThreadHandler;
	private HandlerThread registerThread;
	String name, phone, password;
	boolean registerResult;
	EditText nameEditText, phoneEditText, passwordEditText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		registerResult = false;
		nameEditText = (EditText) findViewById(R.id.name_editText);
		phoneEditText = (EditText) findViewById(R.id.phone_editText);
		passwordEditText = (EditText) findViewById(R.id.password_editText);
		Button registerFinish = (Button) findViewById(R.id.register_finish_button);
		registerFinish.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				name = nameEditText.getText().toString();
				phone = phoneEditText.getText().toString();
				password = passwordEditText.getText().toString();
				if (!name.equals("") && !phone.equals("") && !password.equals("")) {
					ThreadHandler.post(registerRunnalble);
				}

			}
		});
		registerThread = new HandlerThread("update");
		registerThread.start();
		ThreadHandler = new Handler(registerThread.getLooper());
	}

	private Runnable registerRunnalble = new Runnable() {
		public void run() {
			Class_register_account register = new Class_register_account();
			registerResult = register.register(name, phone, password);
			Message message = new Message();
			message.what = 1;
			downloadUserHandler.sendMessage(message);
		}
	};

	private Handler downloadUserHandler = new Handler() { // Timer 更新 UI
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if (registerResult == true) {
					Intent intent = new Intent(Activity_Register.this,
							Activity_login.class);
					Activity_Register.this.startActivity(intent);
					Activity_Register.this.finish();
				} else
					Toast.makeText(Activity_Register.this, "資料錯誤",
							Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

}
