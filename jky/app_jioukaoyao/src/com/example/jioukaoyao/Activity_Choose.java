package com.example.jioukaoyao;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Choose extends Activity {

	int seconds=10;
	private Handler ThreadHandler;
	private HandlerThread downloadThread;
	private Button proposePlace;
	private Button proposeTime;
	private TextView bombTimeView;
	private Vibrator vibrator;
	private String rest[],time[];
	private int restaurant[] = { R.drawable.food_dozo, R.drawable.food_london,
			R.drawable.food_mge, R.drawable.food_vege };
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
		bombTimeView = (TextView) findViewById(R.id.bomb_time_view);
		proposePlace=(Button)findViewById(R.id.propose_place);
		proposeTime=(Button)findViewById(R.id.propose_time);
		Bundle bombTimeBundle = this.getIntent().getExtras();
		seconds=bombTimeBundle.getInt("bombSec")*60;
		final Timer bombTimer = new Timer();
		bombTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				seconds--;
				Message message = new Message();
				message.what = 1;
				updateBombTimer.sendMessage(message);
				if(seconds==0||seconds<0){
					message = new Message();
					message.what = 2;
					seconds=100;
					updateBombTimer.sendMessage(message);
					bombTimer.cancel();
				}
			}
		}, 0, 1000);
		downloadThread = new HandlerThread("update");
		downloadThread.start();
		ThreadHandler = new Handler(downloadThread.getLooper());
		ThreadHandler.post(downloadRestTimeRunnalbe);
	}
	
	private Runnable downloadRestTimeRunnalbe = new Runnable() {
		public void run() {
			Class_download_rest downloadRest = new Class_download_rest();
			Class_download_time downloadTime = new Class_download_time();
			String rest[] =downloadRest.choose_rest();
			String time[] =downloadTime.choose_time();
			Message message = new Message();
			message.what = 1;
			downloadRestTimeHandler.sendMessage(message);
		}
	};
	
	private Handler downloadRestTimeHandler = new Handler() { // Timer 更新 UI
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				break;
			}
		}
	};
	
	private Handler updateBombTimer = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				String bombTime=String.valueOf(seconds);
				bombTimeView.setText(bombTime);
				break;
			case 2:
				bombTimeView.setText("test");
				proposePlace.setEnabled(false);
				proposeTime.setEnabled(false);
				vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
				long[] pattern = {0, 2000, 500, 2000, 500, 2000}; // 震動節奏(毫秒) OFF/ON/OFF/ON...  
				vibrator.vibrate(pattern, -1); // 開始震動
				break;
				
			}
		}
	};
	

}
