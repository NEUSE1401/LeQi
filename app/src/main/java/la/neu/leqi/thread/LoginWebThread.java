package la.neu.leqi.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import la.neu.leqi.tools.HttpGet;


/**
 * @author HeXunshi
 *         登录接口
 */

public class LoginWebThread extends Thread {
    private Handler handler;
    private String BASE_URL;

    public LoginWebThread(String base_url, Handler handler, String username, String password) {
        BASE_URL = base_url;
        this.handler = handler;
        BASE_URL += "?username=" + username + "&password=" + password;
    }

    @Override
    public void run() {
        try {
            final String result = HttpGet.send(BASE_URL);
            final JSONObject jsonObject = new JSONObject(result);
            final String state = jsonObject.getString("state");
            if (state.equals("success")) {
                String token = jsonObject.getString("token");
                final Message message = new Message();
                message.what = 1;
                final Bundle bundle = new Bundle();
                bundle.putString("token", token);
                message.setData(bundle);
                handler.sendMessage(message);
            } else {
                handler.sendEmptyMessage(0);
            }
        } catch (Exception e) {
            handler.sendEmptyMessage(0);
            e.printStackTrace();
        }

    }
}
