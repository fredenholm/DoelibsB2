package com.example.doelibs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;





public class RegisterFragment extends Fragment{
	
	Button _register;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        
        
        _register=(Button)rootView.findViewById(R.id.register); 
        _register.setOnClickListener(new OnClickListener() {
        @Override
		public void onClick(View v) {			

		}
	});    
	
	
        return rootView;
	}
	
	
	

}
