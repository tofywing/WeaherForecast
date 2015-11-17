package scribd.yee.interview.yistest.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Yee on 11/15/15.
 */
public class Channel implements ParseData {
    JSONArray forecastArray;
    JSONObject Channel;

    public JSONObject getChannel() {
        return Channel;
    }

    public JSONArray getForecastArray() {
        return forecastArray;
    }

    @Override
    public void parseJSON(JSONObject object) {
        this.Channel = object;
        forecastArray = object.optJSONObject("item").optJSONArray("forecast");
    }
}
