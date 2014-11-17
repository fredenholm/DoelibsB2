package com.example.doelibs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoansFragment extends Fragment {

	public static LoansFragment newInstance() {
		LoansFragment fragment = new LoansFragment();
        return fragment;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.loans_fragment, container, false);
        
        return rootView;
	}
	
}
