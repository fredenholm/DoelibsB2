package com.example.doelibs.REST;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.example.doelibs.LoginActivity;
import com.example.doelibs.R;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

public class LoginTask extends BaseAsyncTask {
	private String _email, _password;
	private HttpResponse response = null;
	
	public LoginTask(Fragment fragment, String email, String password) {
		super(fragment, R.string.dialog_login_loading);
		_email = email;
		_password = password;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		if(_connectionError)
			return false;
		
		String restUrl = _fragment.getString(R.string.rest_login);
		HttpPost httppost = new HttpPost(restUrl);
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair("Email", _email));
		valuePairs.add(new BasicNameValuePair("Password", _password));
		valuePairs.add(new BasicNameValuePair("CallingSystem", _fragment.getString(R.string.rest_callingSystem)));
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(valuePairs, HTTP.UTF_8));
			response = _client.execute(httppost);
			return handleLoginResponse(response.getEntity().getContent(), response.getStatusLine().getStatusCode());
		} catch(Exception e) {
			e.printStackTrace();
			RESTHelper.showTimoutErrorDialog(_fragment);
			return false;
		}
	}
	
	@Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
	
	@Override
    protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if(result.booleanValue()) {
			LoginActivity loginActivity = (LoginActivity)_fragment.getActivity();
			loginActivity.loggedInSuccessful();
		}
	}
	
	
	private boolean handleLoginResponse(InputStream stream, int statusCode) {
		if(!RESTHelper.handleStatusCode(_fragment, stream, statusCode))
    		return false;
		
		JSONObject userJson = RESTHelper.parseJSONObjectResponse(stream);
		return saveLoginInfo(userJson);
	}

	private boolean saveLoginInfo(JSONObject userJson) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(_fragment.getActivity());
		SharedPreferences.Editor editor = settings.edit();
		
		try {
			editor.putString("FirstName", userJson.getString("FirstName"));
			editor.putString("LastName", userJson.getString("LastName"));
			editor.putString("Email", userJson.getString("Email"));
			editor.putString("ValidationCode", userJson.getString("ValidationCode"));
			editor.putBoolean("IsStaff", userJson.getBoolean("IsStaff"));
			editor.putInt("UserId", userJson.getInt("UserId"));
			editor.commit();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
