package com.example.jioukaoyao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FriendActivity extends Activity {
	private SimpleAdapter adapter;
	private ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		
		listView1 = (ListView) findViewById(R.id.listView1);
		//²M³æ­±ª©
		adapter = new SimpleAdapter(this, getData(), R.layout.view2content,
				new String[] { "title", "info", "img" }, new int[] {
						R.id.title, R.id.info, R.id.img });
		listView1.setAdapter(adapter);
	/*	listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListView listView = (ListView) arg0;
				Toast.makeText(	ListView2.this,
						"ID¡G" + arg3 + 
						"   ¿ï³æ¤å¦r¡G"+ listView.getItemAtPosition(arg2).toString(),
						Toast.LENGTH_LONG).show();
			}
		});*/
		
		
		
		NumberPicker np = (NumberPicker) findViewById(R.id.bombTimePicker);
		String[] nums = new String[20];
		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.toString(i);
		np.setMinValue(1);
		np.setMaxValue(20);
		np.setWrapSelectorWheel(false);
		np.setDisplayedValues(nums);
		np.setValue(1);
		Button goChooseButton = (Button) findViewById(R.id.goChooseButton);
		goChooseButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(FriendActivity.this,
						ChooseActivity.class);
				FriendActivity.this.startActivity(intent);
			}
		});
	}
	private List<? extends Map<String, ?>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "©PÞ³³Ç");
		//map.put("info", "¬õ¨§");
		map.put("img", R.drawable.b9900001);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "¿à­NÂE");
		//map.put("info", "ºñ¨§");
		map.put("img", R.drawable.b9917052);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "»¯±Ó¦Ú");
		//map.put("info", "¤g¨§");
		map.put("img", R.drawable.b10000001);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "ªL®f¹Å");
		//map.put("info", "¤ò¨§");
		map.put("img", R.drawable.b10000002);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "»Z§»©ö");
		//map.put("info", "¤ò¨§");
		map.put("img", R.drawable.b10000035);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "­J­õ»¨");
		//map.put("info", "¤ò¨§");
		map.put("img", R.drawable.b10000059);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "ªL®a¦t");
		//map.put("info", "¤ò¨§");
		map.put("img", R.drawable.b10000076);
		list.add(map);

		return list;
	}
}
