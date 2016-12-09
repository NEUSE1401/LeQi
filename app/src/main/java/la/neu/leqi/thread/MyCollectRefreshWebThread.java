package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;

import la.neu.leqi.adapter.MyCollectAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.bean.Club;
import la.neu.leqi.bean.MainBean;

/**
 * @author hxs
 */

public class MyCollectRefreshWebThread extends AsyncTask<Void, Void, MainBean> {
    private String BASE_URL;
    private PullToRefreshBase<SwipeMenuListView> pullToRefreshView;
    private MyCollectAdapter mainActivityAdapter;
    private Context context;

    public MyCollectRefreshWebThread(String base_url, PullToRefreshBase<SwipeMenuListView> paramPullToRefreshListView, MyCollectAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.mainActivityAdapter = paramMyAdapter;
        this.context = paramContext;
    }

    @Override
    protected MainBean doInBackground(Void... voids) {
        final MainBean mainBean = new MainBean();
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");

        final ActivityBean activityBean1 = new ActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1, 10);
        final ActivityBean activityBean2 = new ActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2, 100);
        final ActivityBean activityBean3 = new ActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3, 45);
        final ActivityBean activityBean4 = new ActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4, 20);
        final ArrayList<ActivityBean> activityBeen = new ArrayList<>();
        activityBeen.add(activityBean1);
        activityBeen.add(activityBean2);
        activityBeen.add(activityBean3);
        activityBeen.add(activityBean4);
        mainBean.setActivities(activityBeen);
        BicycleShop shop1 = new BicycleShop("2b101", "槲荷缎花", "超值优惠", 5, "1878117xxxx", 101, "东北大学浑南校区");
        BicycleShop shop2 = new BicycleShop("2b102", "花花", "超值优惠", 5, "1878117xxxx", 101, "东北大学浑南校区");
        ArrayList<String> pic = new ArrayList<>();
        pic.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        shop1.setShopPics(pic);
        final ArrayList<BicycleShop> bicycleShops = new ArrayList<>();
        bicycleShops.add(shop1);
        bicycleShops.add(shop2);
        bicycleShops.add(shop1);
        mainBean.setShops(bicycleShops);
        final ArrayList<Club> clubs = new ArrayList<>();
        String des="旨在通过组织骑行运动，以“分享骑行乐趣”为主题，以“真诚、互助”为宗旨，旨在开展自行车的骑行和极限等活动，促进自行车文化发展，同时积极开拓户外运动，宣传环保，培养挑战自我的信心和勇气，宣传健康、简单的生活方式。";
        final Club club1=new Club("101俱乐部","胡荷缎花",des,5,"暂无","辽宁","沈阳","浑南区","智慧大街",pics1,200);
        Club club2=new Club("向阳","xxx",des,4,"暂无","辽宁","沈阳","和平区","青年大街",pics2,200);
        Club club3=new Club("追随","xxx",des,7,"暂无","辽宁","沈阳","和平区","青年大街",pics3,200);
        Club club4=new Club("悦骑","xxx",des,7,"暂无","辽宁","沈阳","和平区","青年大街",pics4,200);
        clubs.add(club1);
        clubs.add(club2);
        clubs.add(club3);
        clubs.add(club4);
        mainBean.setClubs(clubs);
        return mainBean;
    }

    @Override
    protected void onPostExecute(MainBean mainBean) {
        pullToRefreshView.onRefreshComplete();
        mainActivityAdapter.setCollectClubs(mainBean.getClubs());
        mainActivityAdapter.setCollectShopActivities(mainBean.getActivities());
        mainActivityAdapter.setCollectShops(mainBean.getShops());
        super.onPostExecute(mainBean);
    }
}
