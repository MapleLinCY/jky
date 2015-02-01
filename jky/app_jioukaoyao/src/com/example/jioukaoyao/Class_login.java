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
public class Class_login {
	public String verify(String phone) {
		try {
			String json = "";
			String url = "http://teamplay.gronexus.com/jky/query.php";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(url);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("page", "user");
			jsonObject.put("type", "Select");
			Log.w("Return", phone);
			jsonObject.put("new_u_Phone", phone);
			jsonObject.put("method", "Identify");
			StringEntity se = new StringEntity(jsonObject.toString(), "UTF-8");
			method.setEntity(se);
			HttpResponse response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			Log.w("Do", "login_verify");
			if (entity != null) {
				String result = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				String[] strSplit1 = result.split("\\[");
				String[] temp = strSplit1[1].split(",");
				String[] temp2= temp[2].split("\"");
				String pw = temp2[3];
				Log.w("Return", pw);
				Log.w("Return", result);
					return pw;
				
			} else {
				Log.i("Entity", "Nothing");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
