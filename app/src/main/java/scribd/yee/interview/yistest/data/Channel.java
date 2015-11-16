package scribd.yee.interview.yistest.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Yee on 11/15/15.
 */
public class Channel implements ParseData {
    JSONArray forecastArray;

    public JSONArray getForecastArray() {
        return forecastArray;
    }

    @Override
    public void parseJSON(JSONObject object) {
        forecastArray = object.optJSONObject("item").optJSONArray("forecast");
    }
}
