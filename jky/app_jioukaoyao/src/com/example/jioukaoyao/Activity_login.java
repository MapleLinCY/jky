package com.example.jioukaoyao;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_login extends Activity {

	private Handler ThreadHandler;
	private HandlerThread loginThread;
	private String Verify;
	private String userPhone, userPassword;
	private EditText loginPhoneEdit, loginPasswordEdit;
	private Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		DevicePolicyManager policyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		ComponentName componentName = new ComponentName(this, MyAdminReceiver.class);
		
		boolean isAdminActive = policyManager.isAdminActive(componentName);
		if(!isAdminActive){
			intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN); 
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName); 
			startActivity(intent);
		}
		
		intent = new Intent(this, MyService.class);
		startService(intent);
		loginPhoneEdit = (EditText) findViewById(R.id.edit_login_phone);
		loginPasswordEdit = (EditText) findViewById(R.id.edit_login_password);
		loginPhoneEdit.setText("0958527055");
		loginPasswordEdit.setText("123");
		ImageButton loginButton = (ImageButton) findViewById(R.id.login_button);
		loginButton.getBackground().setAlpha(0);
		loginButton.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				userPhone = loginPhoneEdit.getText().toString();
				userPassword = loginPasswordEdit.getText().toString();
				if (!userPhone.equals("") && !userPassword.equals("")) {
					ThreadHandler.post(loginRunnalble);
				} else
					Toast.makeText(Activity_login.this, "¿ù»~", Toast.LENGTH_LONG)
							.show();
			}
		});

		Button goRegisterButton = (Button) findViewById(R.id.go_register_button);
		goRegisterButton.getBackground().setAlpha(0);
		goRegisterButton.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity_login.this,
						Activity_Register.class);
				Activity_login.this.startActivity(intent);
				Activity_login.this.finish();
			}
		});
		loginThread = new HandlerThread("update");
		loginThread.start();
		ThreadHandler = new Handler(loginThread.getLooper());

	}

	private Runnable loginRunnalble = new Runnable() {
		public void run() {
			Class_login loginVerift = new Class_login();
			Verify = loginVerift.verify(userPhone);
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
				if(Verify.equals(userPassword))
				{
					Intent intent = new Intent(Activity_login.this,
							Activity_Set.class);
					Activity_login.this.startActivity(intent);
					Activity_login.this.finish();
				}
				break;
			}
		}
	};

}
