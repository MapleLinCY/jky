package com.example.jioukaoyao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

public class Class_download_us_Status {
	public String marker_position() {
		try {
			String url = "http://teamplay.gronexus.com/jky/query.php";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(url);
			JSONObject jsonObject = new JSONObject();
			//String in="{\"STATUS\":403,\"VALUE\":[{\"us_No\":\"\",\"us_RoomNo\":\"\",\"us_UserId\":\"\",\"us_Status\":\"\"}]}";
			
			
			jsonObject.put("page", "user_status");
			jsonObject.put("type", "Select");
			jsonObject.put("new_us_UserId", "1");
			StringEntity se = new StringEntity(jsonObject.toString());
			method.setEntity(se);
			HttpResponse response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			Log.w("Do", "UpdateMapMarker");
			//Log.i("Http", "Get entity");
			if (entity != null) {
				String result = EntityUtils.toString(response.getEntity(),"UTF-8");
				Log.w("Do", result);
				JSONObject reader2 = new JSONObject(result);
				JSONArray VALUE  = reader2.getJSONArray("VALUE");
				String js;
				String us_Status;
				js = VALUE.getString(0);
				JSONObject reader3 = new JSONObject(js);
				us_Status = reader3.getString("us_Status").toString();
				
				Log.w("Do", us_Status);
				/*
				us_RoomNo = VALUE.getString("us_RoomNo").toString();
				us_UserId = VALUE.getString("us_UserId").toString();
				us_Status = VALUE.getString("us_Status").toString();
				*/
				//String us[][];
				//us[1][0]="us_No";
				Log.w("Do", us_Status);
				
				return us_Status;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
