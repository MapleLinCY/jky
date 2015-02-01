package com.example.jioukaoyao;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Class_download_user {
	public String[] user_list() {
		try {
			String json = "";
			String url = "http://teamplay.gronexus.com/jky/query.php";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(url);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("page", "user");
			jsonObject.put("type", "Select");
			json = jsonObject.toString();
			StringEntity se = new StringEntity(json);
			method.setEntity(se);
			HttpResponse response = httpclient.execute(method);
			String entity = response.getEntity().toString();
			Log.w("Do", entity);
			Log.w("Do", "download user");
			if (entity != null) {
				String result = EntityUtils.toString(response.getEntity(),"UTF-8");
				String[] strSplit1 = result.split("\\[");
				String[] strSplit2 = strSplit1[1].split("\\},");
				String[] strSplit = new String[strSplit2.length];
				String[] temp;
				String[] temp2;
				for(int i=0;i<strSplit2.length;i++){
					temp = strSplit2[i].split(",");
					temp2 = temp[3].split("\"");
					strSplit[i] = temp2[3];
					Log.w("Return", strSplit[i].toString());
				}
				Log.i("Return", result);
				
				return strSplit;
			} else {
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
