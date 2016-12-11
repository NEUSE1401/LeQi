package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.adapter.ShareListAdapter;
import la.neu.leqi.bean.Share;
import la.neu.leqi.tools.HttpGet;

/**
 * @author hxs
 *         分享列表加载更多接口
 */

public class ShareListDataWebThread extends AsyncTask<Void, Void, ArrayList<Share>> {
    private String BASE_URL;
    private PullToRefreshBase<RecyclerView> pullToRefreshView;
    private ShareListAdapter shareListAdapter;
    private Context context;

    public ShareListDataWebThread(String base_url, PullToRefreshBase<RecyclerView> paramPullToRefreshListView, ShareListAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.shareListAdapter = paramMyAdapter;
        this.context = paramContext;
    }

    @Override
    protected ArrayList<Share> doInBackground(Void... voids) {
        final ArrayList<Share> shares = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL);
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length()&&i<4; i++) {
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
        pullToRefreshView.onRefreshComplete();
        for (Share s : shares) {
            shareListAdapter.addData(s);
        }
        super.onPostExecute(shares);
    }

}
