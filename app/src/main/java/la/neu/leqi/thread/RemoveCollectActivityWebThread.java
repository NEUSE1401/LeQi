package la.neu.leqi.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import la.neu.leqi.bean.ActivityBean;

/**
 * @author hxs
 */

public class RemoveCollectActivityWebThread extends Thread {
    private String BASE_URL;
    private ActivityBean activityBean;
    private int position;
    private Handler handler;

    public RemoveCollectActivityWebThread(String BASE_URL, ActivityBean activityBean, int position, Handler handler) {
        this.BASE_URL = BASE_URL;
        this.activityBean = activityBean;
        this.position = position;
        this.handler = handler;
    }

    @Override
    public void run() {
        //        try {
//            final String result = HttpGet.send(BASE_URL);
//            final JSONObject jsonObject = new JSONObject(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        final Message message = new Message();
        message.what = 1;
        final Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("info","活动");
        message.setData(bundle);
        handler.sendMessage(message);
    }
}
