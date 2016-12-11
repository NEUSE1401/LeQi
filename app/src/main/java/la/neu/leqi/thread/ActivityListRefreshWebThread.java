package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.adapter.ActivityListViewAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.tools.HttpGet;

/**
 * @author HeXunshi
 *         活动列表刷新接口
 */

public class ActivityListRefreshWebThread extends AsyncTask<Void, Void, ArrayList<ActivityBean>> {
    private String BASE_URL;
    private PullToRefreshBase<ListView> pullToRefreshView;
    private ActivityListViewAdapter activityListViewAdapter;
    private Context context;
    private int type;

    public ActivityListRefreshWebThread(String base_url,int type, PullToRefreshBase<ListView> paramPullToRefreshListView, ActivityListViewAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.activityListViewAdapter = paramMyAdapter;
        this.context = paramContext;
        this.type = type;
    }

    @Override
    protected ArrayList<ActivityBean> doInBackground(Void... voids) {
        final ArrayList<ActivityBean> activityBeans = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL);
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final ArrayList<String> pics = new ArrayList<>();
                final JSONArray picsAl = jsonObject.getJSONArray("pics");
                for (int j = 0; j < picsAl.length(); j++) {
                    pics.add(context.getString(R.string.WEB_BASE) + picsAl.getString(j));
                }
                final ActivityBean activityBean = new ActivityBean(jsonObject.getInt("activity_id"),
                        jsonObject.getString("title"), jsonObject.getString("start_time"), jsonObject.getString("end_time"), pics, (int) (1 + Math.random() * (100 - 1 + 1)));
                activityBeans.add(activityBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        final ArrayList<String> pics1 = new ArrayList<>();
//        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
//        final ArrayList<String> pics2 = new ArrayList<>();
//        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
//        final ArrayList<String> pics3 = new ArrayList<>();
//        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
//        final ArrayList<String> pics4 = new ArrayList<>();
//        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
//        //    public ActivityBean(int activityId, String title, String description, String startTime, String endTime, String releaseTime, ArrayList<String> pic_listp, int count, String startPlace, String endPlace, String activityPlace, String participateWay, String owner, String requirement) {
//        String des = "本次骑行赛车活动规模大、内容丰富、参赛选手众多。我们策划这次活动，一方面，试图通过“骑行”这种形式，倡导绿色出行新风尚，身体力行实践低碳生活，落实国家低碳减排政策。另一方面，这也是一次传播自行车文化，普及大众骑行活动的播种之旅。";
//        String way = "联系负责人报名即可";
//        final ActivityBean activityBean1 = new ActivityBean(1, "活动1", des, "2016-11-3", "2016-11-6", "2016-11-3", pics1, 23, "沈阳", "沈阳", way, "单车101俱乐部", "无");
//        final ActivityBean activityBean2 = new ActivityBean(2, "活动2", des, "2016-11-3", "2016-11-7", "2016-11-3", pics2, 23, "沈阳", "沈阳",  way, "单车101俱乐部", "无");
//        final ActivityBean activityBean3 = new ActivityBean(3, "活动3", des, "2016-11-3", "2016-11-3", "2016-11-1", pics3, 100, "沈阳", "沈阳",  way, "单车101俱乐部", "无");
//        final ActivityBean activityBean4 = new ActivityBean(4, "活动4", des, "2016-11-3", "2016-11-3", "2016-11-1", pics4, 90, "沈阳", "沈阳",  way, "单车101俱乐部", "无");
//        final ArrayList<ActivityBean> activityBeen = new ArrayList<>();
//        activityBeen.add(activityBean1);
//        activityBeen.add(activityBean2);
//        activityBeen.add(activityBean3);
//        activityBeen.add(activityBean4);
//        activityBeen.add(activityBean1);
//        activityBeen.add(activityBean2);
//        activityBeen.add(activityBean3);
//        activityBeen.add(activityBean4);
//        activityBeen.add(activityBean1);
//        activityBeen.add(activityBean2);
//        activityBeen.add(activityBean3);
//        activityBeen.add(activityBean4);
//        activityBeen.add(activityBean1);
//        activityBeen.add(activityBean2);
//        activityBeen.add(activityBean3);
//        activityBeen.add(activityBean4);
        return activityBeans;
    }

    @Override
    protected void onPostExecute(ArrayList<ActivityBean> activityBeen) {
        pullToRefreshView.onRefreshComplete();
        activityListViewAdapter.setData(activityBeen);
        super.onPostExecute(activityBeen);
    }
}
