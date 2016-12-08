package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;

import la.neu.leqi.adapter.ShareListAdapter;
import la.neu.leqi.bean.Share;

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
        //        try {
//            final String result = HttpGet.send(BASE_URL);
//            final JSONObject jsonObject = new JSONObject(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        final ArrayList<Share> shares = new ArrayList<>();
        final Share share1 = new Share(1, "分享1", pics1);
        final Share share2 = new Share(2, "分享2", pics2);
        final Share share3 = new Share(3, "分享3", pics3);
        final Share share4 = new Share(4, "分享4", pics4);
        shares.add(share1);
        shares.add(share2);
        shares.add(share3);
        shares.add(share4);
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
