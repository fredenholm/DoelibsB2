package Objects;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class helper {
	public static User getCurrentPerson(Activity activity) {
		User person = new User();
		try {
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity);
			person.userId = settings.getInt("UserId", 0);
			person.firstName = settings.getString("FirstName", "");
			person.lastName = settings.getString("LastName", "");
			person.email = settings.getString("Email", "");
			person.isStaff = settings.getBoolean("IsStaff", false);
			person.validationCode = settings.getString("ValidationCode", null);
		}
		catch(Exception e) {
			Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();
		}
		return person;
	}
	
	public static void deletePreferences(Activity activity) {
		try {
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity);
			settings.edit().clear().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
