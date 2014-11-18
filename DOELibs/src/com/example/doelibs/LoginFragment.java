package com.example.doelibs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginFragment extends Fragment{
	
Button _btnLogin,_btnRegister;
EditText _editEmail;



	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        
        
        _btnLogin=(Button)rootView.findViewById(R.id. btnLogin); 
        _btnRegister=(Button)rootView.findViewById(R.id. btnRegister); 
        _editEmail=(EditText)rootView.findViewById(R.id.editEmail);        
        
        _editEmail.setHint("Email");
        
        
        
       _btnLogin.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);

			}
		});    
        
        _btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Fragment newFragment = new RegisterFragment();
//				
//				FragmentTransaction ft = getFragmentManager().beginTransaction();
//				//ft.remove(new LoginFragment());
//				ft.replace(R.id.fragment_login, newFragment);
//				ft.addToBackStack(null);
//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//				ft.commit();
			}
		});
       
        
        return rootView;         
        
        }
	
	

}
