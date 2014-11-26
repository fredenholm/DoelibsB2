package com.example.doelibs.REST;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.doelibs.LoansFragment;
import com.example.doelibs.R;
import com.google.gson.Gson;
import Objects.Loan;
import Objects.User;
import android.support.v4.app.Fragment;

public class LoansTask extends BaseAsyncTask {
	
	private User _user;
	private HttpResponse response;
	private ArrayList<Loan> _loans;
	
	public LoansTask(Fragment fragment, User user) {
		super(fragment, R.string.dialog_loans_loading);
		_user = user;
	}

	@Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
	
	@Override
	protected Boolean doInBackground(Void... arg0) {
		// End if connection error
		if(_connectionError)
			return false;
		
		String restUrl = String.format("%s?UserId=%s&ValidationCode=%s", _fragment.getString(R.string.rest_loans), 
																		 _user.userId, 
																		 _user.validationCode);
		HttpGet get = new HttpGet(restUrl);
		
		try {
			// Do call 
			response = _client.execute(get);
			InputStream stream = response.getEntity().getContent();
			
			// Check for error from server
			if(!RESTHelper.handleStatusCode(_fragment, stream, response.getStatusLine().getStatusCode()))
	    		return false;
			
			// Parse Json
			parseJson(stream);
			return true;
		} catch (Exception e) {
			// Probably timeout error.
			RESTHelper.showTimoutErrorDialog(_fragment);
			return false;
		}
	}
    
    @Override
    protected void onPostExecute(Boolean result) {
    	super.onPostExecute(result);
    	if(result.booleanValue()) {
    		if(_fragment != null && _fragment instanceof LoansFragment) {
    			((LoansFragment)_fragment).loansRecieved(_loans);
    		}
    	}
    }
    
    private void parseJson(InputStream stream) throws Exception {
		// Parse to Json from REST
		JSONArray json = RESTHelper.parseJSONArrayResponse(stream);
		_loans = new ArrayList<Loan>();
		Gson gson = new Gson();
		
		for(int i = 0; i < json.length(); i++) {
			_loans.add(gson.fromJson(json.get(i).toString(), Loan.class));
		}
	}
}
