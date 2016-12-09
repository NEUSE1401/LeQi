package la.neu.leqi.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.bean.Club;

/**
 * @author hxs
 */

public class RemoveCollectClubWebThread extends Thread {
    private String BASE_URL;
    private Club club;
    private int position;
    private Handler handler;

    public RemoveCollectClubWebThread(String BASE_URL, Club club, int position, Handler handler) {
        this.BASE_URL = BASE_URL;
        this.club = club;
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
        message.setData(bundle);
        handler.sendMessage(message);
    }
}
