package com.example.jioukaoyao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class Activity_Start extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		ImageButton startButton=(ImageButton) findViewById(R.id.startButton);
		startButton.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(
						Activity_Start.this,
						Activity_Set.class);
				Activity_Start.this
						.startActivity(intent);
			}
		});
	}
}
