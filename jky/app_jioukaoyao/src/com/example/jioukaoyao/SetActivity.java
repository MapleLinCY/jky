package com.example.jioukaoyao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		Button goChooseButton=(Button) findViewById(R.id.goFriendButton);
		goChooseButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(
						SetActivity.this,
						FriendActivity.class);
				SetActivity.this
						.startActivity(intent);
			}
		});
	}

}
