package com.example.jioukaoyao;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private MyReceiver receiver;
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.w("AlertNotifi", "1");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
        filter.addAction("com.cloay.receiver");
        registerReceiver(receiver,filter);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Log.v("AlertNotifi", "I am running.....");
				Class_download_us_Status cus_Status = new Class_download_us_Status(); 
				String status;
				status = cus_Status.marker_position();
				Log.v("AlertNotifi", status);
				KeyguardManager mKeyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
				if (!mKeyguardManager.inKeyguardRestrictedInputMode()) {
					Log.v("AlertNotifi", "Screen is on.....");
				}else{
					Log.v("AlertNotifi", "Screen is off.....");
					if (status.equals("1"))
					{
						Intent intentReceiver = new Intent("com.cloay.receiver");
						sendBroadcast(intentReceiver);
						
					}
					//Class_upload_us_Status upus_Status = new Class_upload_us_Status();
					//upus_Status.marker_position();
				}
			}
		}, 5*1000, 5*1000);   //send a new message every 10 seconds.
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	
}
