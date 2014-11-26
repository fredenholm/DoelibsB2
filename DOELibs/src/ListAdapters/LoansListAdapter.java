package ListAdapters;

import java.util.ArrayList;

import com.example.doelibs.R;

import Objects.Loan;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LoansListAdapter extends BaseAdapter {
	private LayoutInflater _inflater;
	private ArrayList<Loan> _items;
	private Context _context;
	
	public LoansListAdapter(Context context, ArrayList<Loan> items) {
        _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _items = items;
        _context = context;
    }
	
	@Override
	public int getCount() {
		return _items.size();
	}

	@Override
	public Loan getItem(int position) {
		return _items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position + 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		final Loan loan = getItem(position);
		
		if(row == null)
			row = _inflater.inflate(R.layout.loan_row_item, parent);
		
		if(loan != null) {
			TextView titleTextView = (TextView) row.findViewById(R.id.loan_title);
	        TextView returnDateTextView = (TextView) row.findViewById(R.id.loan_returnDate);
	        TextView locationTextView = (TextView) row.findViewById(R.id.loan_location);
	        
	        titleTextView.setText(loan.Title);
	        returnDateTextView.setText(loan.ReturnDate);
	        locationTextView.setText(loan.LocationRoom + ", " + loan.LocationCategory);
		}
		
		return null;
	}

}
