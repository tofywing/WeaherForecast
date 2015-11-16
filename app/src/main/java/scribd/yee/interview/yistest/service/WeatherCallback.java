package scribd.yee.interview.yistest.service;

import scribd.yee.interview.yistest.data.Channel;

/**
 * Created by Yee on 11/15/15.
 */
public interface WeatherCallback {

    void onActionSuccess(Channel channel);

    void onActionFailed(Exception exception);
}
