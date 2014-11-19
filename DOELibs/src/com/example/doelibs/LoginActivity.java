package com.example.doelibs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class LoginActivity extends ActionBarActivity {
	private ActionBar _actionBar;
	private LoginFragment _loginFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	
}
