package com.example.doelibs.REST;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

public class BaseAsyncTask extends AsyncTask<Void, String, Boolean> {
	protected Fragment _fragment;
	private ProgressDialog _dialog;
	private int _dialogMessageStringID;
	protected boolean _connectionError = false;
	protected HttpClient _client;
	private AsyncTask<Void, String, Boolean> task = null;

	public BaseAsyncTask(Fragment fragment, int dialogMessageStringID) {
		_fragment = fragment;
		_dialogMessageStringID = dialogMessageStringID;
		setClient();
	}
	
	public BaseAsyncTask(Fragment fragment) {
		_fragment = fragment;
		setClient();
	}
	
	private void setClient() {
		// Create HttpClient with timeout parameters  
		_client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(_client.getParams(), 60000);
		HttpConnectionParams.setSoTimeout(_client.getParams(), 60000);
	}
	
	 protected void onPreExecute() {
		task = this;
		if(_fragment == null || _fragment.getActivity() == null)
			return;
		
		if(!RESTHelper.isNetworkAvailable(_fragment.getActivity())) {
			_connectionError = true;
			RESTHelper.showConnectionErrorDialog(_fragment);
			return;
		}
			
    	// Show Loading dialog
		if(_dialogMessageStringID != 0) {
			_dialog = new ProgressDialog(_fragment.getActivity());
			_dialog.setMessage(_fragment.getString(_dialogMessageStringID));
			_dialog.setIndeterminate(true);
			_dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
		          public void onCancel(DialogInterface dialog) {
		              task.cancel(true);
		          }
		    });
			_dialog.show();
		}
        super.onPreExecute();
    } 
	
	
	@Override
	protected Boolean doInBackground(Void... arg0) {
		return null;
	}

	@Override
    protected void onPostExecute(Boolean result) {
    	super.onPostExecute(result);
    	// Remove loading dialog
    	try {
	    	if (_dialog != null && _dialog.isShowing() && !isCancelled())
	    		_dialog.dismiss();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
