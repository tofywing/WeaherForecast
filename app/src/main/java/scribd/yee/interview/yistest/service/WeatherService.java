package scribd.yee.interview.yistest.service;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import scribd.yee.interview.yistest.R;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherService {

    WeatherCallback mCallback;
    Context mContext;

    public WeatherService(WeatherCallback callback, Context context) {
        this.mCallback = callback;
        this.mContext = context;
    }

    public void getWeather(final String location) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo" +
                        ".places(1) where text=\"%s\")", params[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri
                        .encode(YQL));
                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder data = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) data.append(line);
                    return data.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return mContext.getString(R.string.service_error);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonData = new JSONObject(s);
                    JSONObject parseQuery = jsonData.optJSONObject("query");
                    int count = parseQuery.optInt("count");
                    //Check general input error including empty city and state
                    if (count == 0) {
                        mCallback.onActionFailed(new ActionFailedException());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(location);
    }

    public class ActionFailedException extends Exception {
        public ActionFailedException() {
            super();
            WeatherAlert.getAlert(mContext, R.string.general_input_error_alert, R.string.confirm_alert);
        }
    }
}
