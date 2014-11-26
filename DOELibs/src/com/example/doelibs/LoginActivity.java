package com.example.doelibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import Objects.User;
import Objects.helper;

public class LoginActivity extends ActionBarActivity {
	private ActionBar _actionBar;
	private LoginFragment _loginFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		User user = helper.getCurrentPerson(this);
		if(user.isLoggedIn())
			loggedInSuccessful();
		
		setContentView(R.layout.activity_login);	
		
		_actionBar = getSupportActionBar();
		_actionBar.setTitle(getString(R.string.app_name));

		_loginFragment = new LoginFragment();
		navigateTo(_loginFragment, false, null);
	}
	
	public void navigateTo(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(addToBackStack)
        	transaction.addToBackStack(null);
        transaction.replace(R.id.frame_content, fragment, tag).commit();
	}

	public void loggedInSuccessful() {
		// Invoked from LoginTask if successful login
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
	


	public void registerSuccessful() {
		// Invoked from RegisterTask if successful registration
		navigateTo(new LoginFragment(), true, null);
	}
	
}
