package la.neu.leqi.tools;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author HXS
 * GET请求发送
 */

public class HttpGet {
    public static String send(String base_url) throws IOException {
        URL url = new URL(base_url);
        URLConnection rulConnection = url.openConnection();
        HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
        httpUrlConnection.setDoInput(true); //允许输入流，即允许下载
        httpUrlConnection.setDoOutput(true); //允许输出流，即允许上传
        httpUrlConnection.setUseCaches(false); //不使用缓冲
        httpUrlConnection.setRequestMethod("GET"); //使用get请求
        final DataInputStream dis = new DataInputStream(httpUrlConnection.getInputStream());
        String result = "";
        byte[] bytes = new byte[1024];
        int len;
        while ((len = dis.read(bytes, 0, bytes.length)) != -1) {
            result += new String(bytes, 0, len);
        }
        return result;
    }
}
