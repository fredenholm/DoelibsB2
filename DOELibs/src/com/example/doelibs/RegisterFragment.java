package com.example.doelibs;

import java.util.ArrayList;
import java.util.List;

import com.example.doelibs.REST.RegisterTask;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class RegisterFragment extends Fragment{	
	
	Button _register;
	Spinner _spin;
	Switch _roleSwitch;
	EditText _editFirstName, _editLastName, _editEmail, _editPassword, _editCofirmPassword;	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
 
        _register=(Button)rootView.findViewById(R.id.register); 
        
        _editFirstName = (EditText)rootView.findViewById(R.id.editFirstName);
        _editLastName= (EditText)rootView.findViewById(R.id.editLastName);
        _editEmail= (EditText)rootView.findViewById(R.id.editEmail);
        _editPassword= (EditText)rootView.findViewById(R.id.editPassword);
        _editCofirmPassword= (EditText)rootView.findViewById(R.id.editConfirmPassword);
        
        _roleSwitch = (Switch)rootView. findViewById(R.id.roleSwitch);		  
		_roleSwitch.setChecked(true); 	//set the switch to ON 	
		_roleSwitch.setTextOff("Staff");
		_roleSwitch.setTextOn("Student");  
		
		_spin=(Spinner)rootView.findViewById(R.id.spin);
		
		
	    List<String> toSpin = new ArrayList<String>();
	    toSpin.add("Staff1");
	    toSpin.add("Staff2");
	    toSpin.add("Staff3");
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_spinner_item,toSpin);

	    _spin.setAdapter(adapter);     
        
        
        
        _register.setOnClickListener(new OnClickListener() {
	        @Override
			public void onClick(View v) {			
	register();
			}
		});    
        
        return rootView;
	}

	public void register()
	{
		boolean error = false;
		if(_editFirstName.length() == 0) {
			_editFirstName.setError("Please enter your first name");
			error = true;
		}
		if(_editLastName.length() == 0) {
			_editLastName.setError("Please enter your last name");
			error = true;
		}
		if(_editEmail.length() == 0) {
			_editEmail.setError("Please enter an email");
			error = true;
		}
		if(_editPassword.length() == 0) {
			_editPassword.setError("Please enter a Password");
			error = true;
		}
		if(_editCofirmPassword.length() == 0) {
			_editCofirmPassword.setError("Please confirm your Password");
			error = true;
		}
		
		if(!error) {
//			new RegisterTask(this, _editFirstName.getText().toString(), _editLastName.getText().toString(),
//					_editEmail.getText().toString(), _editPassword.getText().toString(), 
//					String.valueOf(_spin.getSelectedItemId()), String.valueOf((_roleSwitch.getText().toString() == "Staff")));
		
			
		}		
	}
	}
