package scribd.yee.interview.yistest.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;

import scribd.yee.interview.yistest.R;
import scribd.yee.interview.yistest.data.Forecast;

/**
 * Created by Yee on 11/15/15.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    JSONArray mDataSet;
    Forecast mForecast;

    public WeatherAdapter(JSONArray dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_layout, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        mForecast = new Forecast();
        mForecast.parseJSON(mDataSet.optJSONObject(position));
        holder.mDay.setText(mForecast.getDay());
        holder.mDate.setText(mForecast.getDate());
        holder.mHigh.setText(mForecast.getHigh());
        holder.mLow.setText(mForecast.getLow());
        holder.mInGeneral.setText(mForecast.getInGeneral());
    }

    @Override
    public int getItemCount() {
        return mDataSet.length();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        CardView mDailyView;
        TextView mDay;
        TextView mDate;
        TextView mHigh;
        TextView mLow;
        TextView mInGeneral;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            mDailyView = (CardView) itemView.findViewById(R.id.daliy);
            mDay = (TextView) itemView.findViewById(R.id.day);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mHigh = (TextView) itemView.findViewById(R.id.high);
            mLow = (TextView) itemView.findViewById(R.id.low);
            mInGeneral = (TextView) itemView.findViewById(R.id.inGeneral);
        }
    }
}
