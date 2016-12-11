package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;


import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.adapter.PersonalShareAdapter;
import la.neu.leqi.bean.Share;
import la.neu.leqi.tools.HttpGet;

/**
 * @author hxs
 */

public class MyShareListRefreshWebThread extends AsyncTask<Void, Void, ArrayList<Share>> {
    private String BASE_URL;
    private PersonalShareAdapter shareListAdapter;
    private PullToRefreshBase<ListView> pullToRefreshBase;
    private Context context;

    public MyShareListRefreshWebThread(String base_url,PullToRefreshBase<ListView> pullToRefreshBase, PersonalShareAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.shareListAdapter = paramMyAdapter;
        this.pullToRefreshBase = pullToRefreshBase;
        this.context = paramContext;
    }

    @Override
    protected ArrayList<Share> doInBackground(Void... voids) {
        final ArrayList<Share> shares = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL);
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length()&&i<6; i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final ArrayList<String> pics = new ArrayList<>();
                final JSONArray picsAl = jsonObject.getJSONArray("pics");
                for (int j = 0; j < picsAl.length(); j++) {
                    pics.add(context.getString(R.string.WEB_BASE) + picsAl.getString(j));
                }
                final Share share = new Share(jsonObject.getInt("share_id"), jsonObject.getString("theme"), pics);
                shares.add(share);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shares;
    }

    @Override
    protected void onPostExecute(ArrayList<Share> shares) {
        pullToRefreshBase.onRefreshComplete();
        shareListAdapter.setData(shares);
        super.onPostExecute(shares);
    }
}
