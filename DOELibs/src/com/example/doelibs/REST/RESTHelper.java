package com.example.doelibs.REST;

import java.io.InputStream;
import com.example.doelibs.R;
import Dialogs.ErrorDialogFragment;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;

public class RESTHelper {
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
			RESTHelper.errorDialog(fragment, fragment.getString(R.string.dialog_alert_error_unhandledMessage));
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
