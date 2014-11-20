package com.example.doelibs;


import com.example.doelibs.REST.LoginTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment{
	
	Button _btnLogin,_btnRegister;
	EditText _editEmail, _editPassword;
	LoginActivity _activity;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        
        _activity = (LoginActivity) getActivity();
        _btnLogin=(Button)rootView.findViewById(R.id. btnLogin); 
        _btnRegister=(Button)rootView.findViewById(R.id. btnRegister); 
        _editEmail=(EditText)rootView.findViewById(R.id.editEmail);        
        _editPassword=(EditText)rootView.findViewById(R.id.editPassword);        
        
        _editEmail.setHint("Email");
        
        _btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				login();
			}
		}); 
        
        _btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				_activity.navigateTo(new RegisterFragment(), true, null);
			}
		});
        
        return rootView;         
	}
	
	private void login() {
		boolean error = false;
		if(_editEmail.length() == 0) {
			_editEmail.setError("Please enter your email");
			error = true;
		}
		if(_editPassword.length() == 0) {
			_editPassword.setError("Please enter you Password");
			error = true;
		}
		
		if(!error) {
			new LoginTask(this, _editEmail.getText().toString(), _editPassword.getText().toString()).execute();
		}
	}

}
