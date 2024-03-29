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

public class RegisterTask extends BaseAsyncTask {
	private String _email, _password, _firstName, _lastName;
	private String _staffId;
	private String _isStaff;
	private HttpResponse response = null;
	
	public RegisterTask(Fragment fragment, String firstName, String lastName, String email, String password,
			String staffId, String isStaff) {
		super(fragment, R.string.dialog_processing);
		
		_email = email;
		_password = password;
		_firstName = firstName;
		_lastName = lastName;
		_staffId = staffId;
		_isStaff = isStaff;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		if(_connectionError)
			return false;
		
		String restUrl = _fragment.getString(R.string.rest_register);
		HttpPost httppost = new HttpPost(restUrl);
		
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair("FirstName", _firstName));
		valuePairs.add(new BasicNameValuePair("LastName", _lastName));
		valuePairs.add(new BasicNameValuePair("Email", _email));
		valuePairs.add(new BasicNameValuePair("Password", _password));
		valuePairs.add(new BasicNameValuePair("StaffId", _staffId));
		valuePairs.add(new BasicNameValuePair("IsStaff", _isStaff));
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
			loginActivity.registerSuccessful();
		}
	}
	
	
	private boolean handleLoginResponse(InputStream stream, int statusCode) {
		if(!RESTHelper.handleStatusCode(_fragment, stream, statusCode))
    		return false;
		
		return true;
	}
	
}
