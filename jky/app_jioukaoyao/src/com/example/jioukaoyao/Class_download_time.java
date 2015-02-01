package com.example.jioukaoyao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Class_download_time {
	public String[] choose_time() {
		try {
			String url = "http://teamplay.gronexus.com/jky/query.php";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(url);
			JSONObject jsonObject = new JSONObject();
			// String
			// in="{\"STATUS\":403,\"VALUE\":[{\"us_No\":\"\",\"us_RoomNo\":\"\",\"us_UserId\":\"\",\"us_Status\":\"\"}]}";

			jsonObject.put("page", "room_choice_eattime");
			jsonObject.put("type", "Select");
			jsonObject.put("new_rce_RoomNo", "1");

			StringEntity se = new StringEntity(jsonObject.toString());
			method.setEntity(se);
			HttpResponse response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			
			// Log.i("Http", "Get entity");
			if (entity != null) {
				String result = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				
				JSONObject reader2 = new JSONObject(result);
				JSONArray VALUE = reader2.getJSONArray("VALUE");
				String js;
				String[] time = new String[VALUE.length()];
				// js = VALUE.getString(0);
				for (int i = 0; i < VALUE.length(); i++) {
					js = VALUE.getString(i).toString();
					
					JSONObject reader3 = new JSONObject(js);
					time[i] = reader3.getString("rce_EatTime").toString();
					Log.w("rce_EatTime", time[i]);
				}
				return time;

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
