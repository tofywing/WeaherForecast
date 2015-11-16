package scribd.yee.interview.yistest.service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherAlert {
    public static void getAlert(Context context, int alertMessage, int alertButton) {
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
