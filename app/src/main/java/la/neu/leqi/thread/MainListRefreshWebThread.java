package la.neu.leqi.thread;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import la.neu.leqi.adapter.MainActivityAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.bean.Good;
import la.neu.leqi.bean.MainBean;
import la.neu.leqi.bean.Share;
import la.neu.leqi.tools.HttpGet;


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
        final ArrayList<Good> goods = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL+"good_list.php");
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0;i<jsonArray.length();i++){
                if(i<4){
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    final ArrayList<String> pics = new ArrayList<>();
                    final JSONArray picsAl = jsonObject.getJSONArray("pics");
                    for (int j = 0; j < picsAl.length(); j++) {
                        pics.add(BASE_URL+picsAl.getString(j));
                    }
                    final Good good = new Good(jsonObject.getInt("good_id"), jsonObject.getString("name"),
                            jsonObject.getString("brand"), jsonObject.getDouble("original_price"),
                            jsonObject.getDouble("current_price"), jsonObject.getString("description"),
                            false, jsonObject.getString("type_id"),
                            jsonObject.getInt("hit_count"), jsonObject.getString("on_sale_time"), pics);
                    goods.add(good);
                }else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
       // Good good1 = new Good(1,  "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null,  pics1);
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
       // Good good2 = new Good(2, "自行车",  "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null, pics2);
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
      //  Good good3 = new Good(3,  "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null,  pics3);
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        //Good good4 = new Good(4,  "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null,  pics4);
//
//        goods.add(good1);
//        goods.add(good2);
//        goods.add(good3);
//        goods.add(good4);
//        final ActivityBean activityBean1 = new ActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1,10);
//        final ActivityBean activityBean2 = new ActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2,100);
//        final ActivityBean activityBean3 = new ActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3,45);
//        final ActivityBean activityBean4 = new ActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4,20);
        final ArrayList<ActivityBean> activityBeans = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL+"list_activities.php");
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0;i<jsonArray.length();i++){
                if(i<4){
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    final ArrayList<String> pics = new ArrayList<>();
                    final JSONArray picsAl = jsonObject.getJSONArray("pics");
                    for (int j = 0; j < picsAl.length(); j++) {
                        pics.add(BASE_URL+picsAl.getString(j));
                    }
                    final ActivityBean activityBean = new ActivityBean(jsonObject.getInt("activity_id"),
                            jsonObject.getString("title"),jsonObject.getString("start_time"),jsonObject.getString("end_time"),pics,(int)(1+Math.random()*(100-1+1)));
                    activityBeans.add(activityBean);
                }else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Share share1 = new Share(1, "分享1", pics1);
        final Share share2 = new Share(2, "分享2", pics2);
        final Share share3 = new Share(3, "分享3", pics3);
        final Share share4 = new Share(4, "分享4", pics4);
        final ArrayList<Share> shares = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL+"share_list.php");
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0;i<jsonArray.length();i++){
                if(i<4){
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    final ArrayList<String> pics = new ArrayList<>();
                    final JSONArray picsAl = jsonObject.getJSONArray("pics");
                    for (int j = 0; j < picsAl.length(); j++) {
                        pics.add(BASE_URL+picsAl.getString(j));
                    }
                    final Share share = new Share(jsonObject.getInt("share_id"), jsonObject.getString("theme"), pics);
                    shares.add(share);
                }else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final MainBean mainBean = new MainBean();
        mainBean.setGoods(goods);
        mainBean.setActivities(activityBeans);
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
