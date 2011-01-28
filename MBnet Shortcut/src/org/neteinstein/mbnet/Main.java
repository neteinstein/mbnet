/*
 * Copyleft (c)
 * 
 * Code licensed under GPL v3.0 license. 
 * 
 * Pedro Vicente - neteinstein @ 2011/01/01
 */

package org.neteinstein.mbnet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

public class Main extends Activity {

	public static final String PREFS_NAME = "preferences";

	@Override
	public void onResume() {
		super.onResume();

		SharedPreferences settings = getSharedPreferences(PREFS_NAME,
				Activity.MODE_PRIVATE);
		
		boolean firstStart = settings.getBoolean("firstStart", true);

		if (firstStart) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(getResources().getText(R.string.firstStart))
					.setCancelable(false).setPositiveButton(
							getResources().getText(R.string.yes),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									goToMBnet();
								}
							});
			AlertDialog alert = builder.create();

			alert.show();

			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("firstStart", false);

			// Commit the edits!
			editor.commit();

		} else {
			goToMBnet();
		}

	}

	private void goToMBnet() {
		// Show toast
		CharSequence text = getResources().getText(R.string.toast);
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(this, text, duration);
		toast.show();

		// Launch url
		Uri uri = Uri.parse("https://www.mbnet.pt/sidebar.html");
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
		finish();
	}

}