package scribd.yee.interview.yistest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Places;

import org.json.JSONArray;

import scribd.yee.interview.yistest.adapter.WeatherAdapter;
import scribd.yee.interview.yistest.data.Channel;
import scribd.yee.interview.yistest.service.WeatherAlert;
import scribd.yee.interview.yistest.service.WeatherCallback;
import scribd.yee.interview.yistest.service.WeatherService;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherActivity extends Activity implements WeatherCallback {

    EditText mInputCity;
    EditText mInputState;
    Button mSubmit;
    RecyclerView mDailyList;
    ProgressDialog mDialog;
    WeatherAdapter mAdapter;
    WeatherService mService;
    SharedPreferences forecastPreference;
    private String mCity = "", mState = "";
    private static JSONArray mForecastArray = new JSONArray();
    public static final String DEFAULT_CITY = "Fremont";
    public static final String DEFAULT_STATE = "CA";
    // The google Api environment has been setup in case of using specific map functions.
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mService = new WeatherService(this, this);
        mInputCity = (EditText) findViewById(R.id.inputCity);
        mInputState = (EditText) findViewById(R.id.inputState);
        mSubmit = (Button) findViewById(R.id.submitButton);
        mDailyList = (RecyclerView) findViewById(R.id.dailyList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mDailyList.setLayoutManager(manager);
        mDailyList.setHasFixedSize(true);
        forecastPreference = getSharedPreferences("location", MODE_PRIVATE);
        if (mForecastArray.length() == 0) {
            startService(DEFAULT_CITY, DEFAULT_STATE, this);
            new WeatherAlert(this, R.string.default_location, R.string.confirm_alert);
        } else {
            mInputCity.setText(forecastPreference.getString("city", DEFAULT_CITY));
            mInputState.setText(forecastPreference.getString("state", DEFAULT_STATE));
        }
        initializeAdapter();
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputValid(v)) {
                    startService(mCity, mState, v.getContext());
                    forecastPreference = getSharedPreferences("location", MODE_PRIVATE);
                    SharedPreferences.Editor editor = forecastPreference.edit();
                    editor.putString("city", mCity);
                    editor.putString("state", mState);
                    editor.apply();
                }
            }
        });
    }

    @Override
    public void onActionSuccess(Channel channel) {
        mDialog.hide();
        mForecastArray = channel.getForecastArray();
        //check either the city's or state's input see if it is incorrect
        int code = mForecastArray.optJSONObject(0).optInt("code");
        if (code == 16 || code == 31 || code == 24)
            new WeatherAlert(this, R.string.general_input_error_alert, R.string.confirm_alert);
        else if (code == 11) {
            new WeatherAlert(this, R.string.city_state_not_match_alert, R.string.confirm_alert);
        } else initializeAdapter();
    }

    @Override
    public void onActionFailed(Exception exception) {
        mDialog.hide();
    }

    private void startService(String city, String state, Context context) {
        mDialog = new ProgressDialog(context);
        mDialog.setMessage(context.getString(R.string.loading));
        mDialog.setCancelable(false);
        mDialog.show();
        mService.getWeather(city + "," + state);
    }

    private void initializeAdapter() {
        mAdapter = new WeatherAdapter(mForecastArray);
        mDailyList.setAdapter(mAdapter);
    }

    private boolean inputValid(View v) {
        //either city or state empty case has been handled by "query" "count = 0"
        if ((mCity = mInputCity.getText().toString()).isEmpty()) {
            new WeatherAlert(v.getContext(), R.string.no_city_alert, R.string.confirm_alert);
            return false;
        } else if ((mState = mInputState.getText().toString()).isEmpty()) {
            new WeatherAlert(v.getContext(), R.string.no_city_alert, R.string.confirm_alert);
            return false;
        }
        return true;
    }
}
