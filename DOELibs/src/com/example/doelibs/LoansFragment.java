package com.example.doelibs;

import java.util.ArrayList;

import com.example.doelibs.REST.LoansTask;

import ListAdapters.LoansListAdapter;
import Objects.Loan;
import Objects.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LoansFragment extends Fragment {

	private ArrayList<Loan> _loans;
	private User _user;
	private MainActivity _activity;
	private View _view;
	
	public static LoansFragment newInstance() {
		LoansFragment fragment = new LoansFragment();
		return fragment;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.loans_fragment, container, false);
        
        _activity = (MainActivity)getActivity();
        _user = _activity.getCurrentUser();
        
        if(_user.isLoggedIn())
        	getLoans();
        
        return _view;
	}

	public void loansRecieved(ArrayList<Loan> loans) {
		_loans = loans;
		if(_loans.size() > 0) {
			ListView list = (ListView)_view.findViewById(R.id.loansListView);
			if(list != null) {
				list.setVisibility(View.VISIBLE);
				LoansListAdapter adapter = new LoansListAdapter(_activity, _loans);
				list.setAdapter(adapter);
			}
		}
	}
	
	public void getLoans() {
		new LoansTask(this, _user).execute();
	}
	
}
