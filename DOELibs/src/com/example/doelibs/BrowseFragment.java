package com.example.doelibs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class BrowseFragment extends Fragment {
	EditText _edit;
	ImageView _searchImageView; 
	
	public static BrowseFragment newInstance() {
		BrowseFragment fragment = new BrowseFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        
        _edit = (EditText) rootView.findViewById(R.id.edit);
        _searchImageView = (ImageView)rootView.findViewById(R.id.browse_searchImageView);
        
        _edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				_edit.requestFocus();
			}
		});
        
        _searchImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
        
        _edit.requestFocus();
        return rootView;
    }
    
}
