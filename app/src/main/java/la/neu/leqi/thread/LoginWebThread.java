package la.neu.leqi.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * @author HeXunshi
 * 登录接口
 */

public class LoginWebThread extends Thread {
    private Handler handler;
    private String BASE_URL = "http://neu.la/leqi/login.php";
    public LoginWebThread(Handler handler,String username,String password){
        this.handler = handler;
        BASE_URL+="?username="+username+"&password="+password;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(BASE_URL);
            URLConnection rulConnection = url.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
            httpUrlConnection.setDoInput(true); //允许输入流，即允许下载
            httpUrlConnection.setDoOutput(true); //允许输出流，即允许上传
            httpUrlConnection.setUseCaches(false); //不使用缓冲
            httpUrlConnection.setRequestMethod("GET"); //使用get请求
            final DataInputStream dis = new DataInputStream(httpUrlConnection.getInputStream());
            String result ="";
            byte[] bytes = new byte[1024];
            int len;
            while ((len=dis.read(bytes,0,bytes.length))!=-1){
                result += new String(bytes,0,len);
            }
            final JSONObject jsonObject = new JSONObject(result);
            final String state = jsonObject.getString("state");
            if(state.equals("success")){
                String token = jsonObject.getString("token");
                final Message message = new Message();
                message.what=1;
                final Bundle bundle = new Bundle();
                bundle.putString("token",token);
                message.setData(bundle);
                handler.sendMessage(message);
            }else{
                handler.sendEmptyMessage(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
