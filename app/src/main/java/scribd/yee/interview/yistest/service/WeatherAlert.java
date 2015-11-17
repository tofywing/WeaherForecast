package scribd.yee.interview.yistest.service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherAlert {
    public WeatherAlert(Context context, int alertMessage, int alertButton) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage(alertMessage);
        alert.setCancelable(false);
        alert.setNegativeButton(alertButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    // For JSON or other data testing purpose
    public WeatherAlert(Context context, String alertMessage, int alertButton) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage(alertMessage);
        alert.setCancelable(false);
        alert.setNegativeButton(alertButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
