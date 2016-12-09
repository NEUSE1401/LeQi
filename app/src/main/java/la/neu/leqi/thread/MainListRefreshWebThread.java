package la.neu.leqi.thread;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;

import la.neu.leqi.adapter.MainActivityAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.bean.Good;
import la.neu.leqi.bean.MainBean;
import la.neu.leqi.bean.Share;


/**
 * @author HeXunshi
 *         首页列表刷新接口
 */

public class MainListRefreshWebThread extends AsyncTask<Void, Void,MainBean> {
    private String BASE_URL;
    private PullToRefreshBase<ListView> pullToRefreshView;
    private MainActivityAdapter mainActivityAdapter;
    private Context context;
    public MainListRefreshWebThread(String base_url, PullToRefreshBase<ListView> paramPullToRefreshListView, MainActivityAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.mainActivityAdapter = paramMyAdapter;
        this.context = paramContext;
    }
    @Override
    protected MainBean doInBackground(Void... voids) {
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        Good good1 = new Good(1, "自行车", 19.9, 18.8, pics1);
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        Good good2 = new Good(2, "自行车", 19.9, 18.8, pics2);
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        Good good3 = new Good(3, "自行车", 19.9, 18.8, pics3);
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        Good good4 = new Good(4, "自行车", 19.9, 18.8, pics4);
        final ArrayList<Good> goods = new ArrayList<>();
        goods.add(good1);
        goods.add(good2);
        goods.add(good3);
        goods.add(good4);
        final ActivityBean activityBean1 = new ActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1,10);
        final ActivityBean activityBean2 = new ActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2,100);
        final ActivityBean activityBean3 = new ActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3,45);
        final ActivityBean activityBean4 = new ActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4,20);
        final ArrayList<ActivityBean> activityBeen = new ArrayList<>();
        activityBeen.add(activityBean1);
        activityBeen.add(activityBean2);
        activityBeen.add(activityBean3);
        activityBeen.add(activityBean4);
        final Share share1 = new Share(1, "分享1", pics1);
        final Share share2 = new Share(2, "分享2", pics2);
        final Share share3 = new Share(3, "分享3", pics3);
        final Share share4 = new Share(4, "分享4", pics4);
        final ArrayList<Share> shares = new ArrayList<>();
       shares.add(share1);
       shares.add(share2);
       shares.add(share3);
       shares.add(share4);
        final MainBean mainBean = new MainBean();
        mainBean.setGoods(goods);
        mainBean.setActivities(activityBeen);
        mainBean.setShares(shares);
        return mainBean;
    }

    @Override
    protected void onPostExecute(MainBean mainBean) {
        pullToRefreshView.onRefreshComplete();
        mainActivityAdapter.setGoods(mainBean.getGoods());
        mainActivityAdapter.setActivities(mainBean.getActivities());
        mainActivityAdapter.setShares(mainBean.getShares());
    }
}
