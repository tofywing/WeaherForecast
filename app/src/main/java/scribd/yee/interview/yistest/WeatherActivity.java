package scribd.yee.interview.yistest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import scribd.yee.interview.yistest.adapter.WeatherAdapter;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherActivity extends Activity {

    EditText mInputCity;
    EditText mInputState;
    Button mSubmit;
    ProgressDialog mDialog;
    RecyclerView mDailyList;
    WeatherAdapter mAdapter;
    private static JSONArray mForecastArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mInputCity = (EditText) findViewById(R.id.inputCity);
        mInputState = (EditText) findViewById(R.id.inputState);
        mSubmit = (Button) findViewById(R.id.submitButton);
        mDailyList = (RecyclerView) findViewById(R.id.dailyList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mDailyList.setLayoutManager(manager);
        mDailyList.setHasFixedSize(true);
    }

    private void initializeAdapter() {
        mAdapter = new WeatherAdapter();
        mDailyList.setAdapter(mAdapter);
    }
}
