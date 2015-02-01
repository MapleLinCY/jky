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

public class Class_register_account {
	public boolean register(String name, String phone, String password) {
		try {
			String json = "";
			String url = "http://teamplay.gronexus.com/jky/query.php";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(url);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("page", "user");
			jsonObject.put("type", "Insert");
			jsonObject.put("new_u_Name", name);
			jsonObject.put("new_u_Phone", phone);
			jsonObject.put("new_u_Password", password);
			StringEntity se = new StringEntity(jsonObject.toString(), "UTF-8");
			method.setEntity(se);
			HttpResponse response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			Log.w("Do", "RegisterAccount");
			if (entity != null) {
				String result = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				String[] strSplit = result.split("\"");
				Log.w("Return", result);
				Log.w("Return", strSplit[3]);
				
				if (strSplit[3].equals("100")) {
					return true;
				} else{
					return false;
				}

			} else {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
