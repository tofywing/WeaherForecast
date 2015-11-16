package scribd.yee.interview.yistest.service;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import scribd.yee.interview.yistest.R;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherDialog {
    public WeatherDialog(Context context, ProgressDialog dialog){
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.loading));
        dialog.show();
    }
}
