package Dialogs;

import com.example.doelibs.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ErrorDialogFragment extends DialogFragment {
	private String message, title = null;
	
	public void setMessage(String message) {
		this.message = message.replaceAll("^\"|\"$", "");
	}
	
	public void setTitle(String title) {
		this.title = title.replaceAll("^\"|\"$", "");
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
               .setNegativeButton(R.string.dialog_alert_error_cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   // Close the dialog
                	   getDialog().cancel();
                   }
               });
        if(title != null)
        	builder.setTitle(R.string.dialog_alert_error_title);
        else
        	builder.setTitle(title);
        
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
