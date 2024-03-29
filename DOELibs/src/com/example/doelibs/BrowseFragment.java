package com.example.doelibs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BrowseFragment extends Fragment {
	EditText _input;
	ImageView _searchImage; 
	LinearLayout _inputLayout;
	
	public static BrowseFragment newInstance() {
		BrowseFragment fragment = new BrowseFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        
        _input = (EditText) rootView.findViewById(R.id.browse_input);
        _searchImage = (ImageView)rootView.findViewById(R.id.browse_searchImage);
        _inputLayout = (LinearLayout) rootView.findViewById(R.id.browse_inputLayout);
        
        
        _inputLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				_input.requestFocus();
			}
		});
        
        _searchImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Fragment newFragment = new BrowseResultFragment();
				
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				
				ft.replace(R.id.fragment_browse, newFragment);
				ft.addToBackStack(null);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
        
        _input.requestFocus();
        return rootView;
    }
    
}
