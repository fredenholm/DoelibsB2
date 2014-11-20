package com.example.doelibs.REST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.example.doelibs.R;
import Dialogs.ErrorDialogFragment;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;

public class RESTHelper {
	
	public static JSONObject parseJSONObjectResponse(InputStream stream) {
		 
		BufferedReader reader;
		StringBuilder builder = new StringBuilder();
		JSONObject finalResult = new JSONObject();
		
		try {
			reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			for (String line = null; (line = reader.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
			JSONTokener tokener = new JSONTokener(builder.toString());
			finalResult = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalResult;
	}
	
	public static JSONArray parseJSONArrayResponse(InputStream stream) {
		 
		BufferedReader reader;
		StringBuilder builder = new StringBuilder();
		JSONArray finalResult = new JSONArray();
		
		try {
			reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			for (String line = null; (line = reader.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
			JSONTokener tokener = new JSONTokener(builder.toString());
			finalResult = new JSONArray(tokener);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalResult;
	}

	public static String inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();

	    // Read response until the end
	    try {
	    	// Wrap a BufferedReader around the InputStream
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			while ((line = rd.readLine()) != null) { 
			    total.append(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    // Return full string
	    return total.toString();
	}
	
	public static boolean isNetworkAvailable(Activity activity) {
		try {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static boolean handleStatusCode(Fragment fragment, InputStream stream, int statusCode) {
		if(RESTHelper.isErrorCode(statusCode)) {
			JSONObject userJson = RESTHelper.parseJSONObjectResponse(stream);
			if(userJson.has("Title") && userJson.has("Message")) {
				try {
					RESTHelper.errorDialog(fragment, userJson.getString("Message"), userJson.getString("Title"));
				} catch (JSONException e) {
					e.printStackTrace();
					RESTHelper.errorDialog(fragment, fragment.getString(R.string.dialog_alert_error_unhandledMessage));
				}
			} else {
				RESTHelper.errorDialog(fragment, fragment.getString(R.string.dialog_alert_error_unhandledMessage));
			}
			return false;
		}
		return true;
	}
	
	public static void showConnectionErrorDialog(Fragment fragment) {
		if(fragment != null) {
			errorDialog(fragment, fragment.getString(R.string.dialog_nointernet_message), 
					fragment.getString(R.string.dialog_nointernet_title));
		}
	}
	
	public static void showTimoutErrorDialog(Fragment fragment) {
		if(fragment != null) {
			errorDialog(fragment, fragment.getString(R.string.dialog_timeout_title), 
					fragment.getString(R.string.dialog_timeout_title));
		}
	}
	
	private static boolean isErrorCode(int statusCode) {
		if(statusCode == 200)
			return false;
		else
			return true;
	}
	
	public static void errorDialog(Fragment fragment, String errorMessage, String title) {
		ErrorDialogFragment dialog = new ErrorDialogFragment();
		dialog.setMessage(errorMessage);
		dialog.setTitle(title);
		dialog.show(fragment.getFragmentManager(), title);
	}
	
	private static void errorDialog(Fragment fragment, String errorMessage) {
			if(fragment != null) {
			ErrorDialogFragment dialog = new ErrorDialogFragment();
			dialog.setMessage(errorMessage);
			dialog.show(fragment.getFragmentManager(), "Error");
		}
	}
	
}
