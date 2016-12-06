package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;

import la.neu.leqi.adapter.ClubListItemAdapter;
import la.neu.leqi.bean.Club;

/**
 * @author HeXunshi
 *         俱乐部列表加载更多接口
 */

public class ClubListDataWebThread extends AsyncTask<Void, Void, ArrayList<Club>> {
    private String BASE_URL;
    private PullToRefreshBase<ListView> pullToRefreshView;
    private ClubListItemAdapter clubListItemAdapter;
    private Context context;

    public ClubListDataWebThread(String base_url, PullToRefreshBase<ListView> paramPullToRefreshListView, ClubListItemAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.clubListItemAdapter = paramMyAdapter;
        this.context = paramContext;
    }

    @Override
    protected ArrayList<Club> doInBackground(Void... voids) {
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
        String des="旨在通过组织骑行运动，以“分享骑行乐趣”为主题，以“真诚、互助”为宗旨，旨在开展自行车的骑行和极限等活动，促进自行车文化发展，同时积极开拓户外运动，宣传环保，培养挑战自我的信心和勇气，宣传健康、简单的生活方式。";
        final Club club1=new Club("101俱乐部","胡荷缎花",des,5,"暂无","辽宁","沈阳","浑南区","智慧大街",pics1,200);
        Club club2=new Club("向阳","xxx",des,4,"暂无","辽宁","沈阳","和平区","青年大街",pics2,200);
        Club club3=new Club("追随","xxx",des,7,"暂无","辽宁","沈阳","和平区","青年大街",pics3,200);
        Club club4=new Club("悦骑","xxx",des,7,"暂无","辽宁","沈阳","和平区","青年大街",pics4,200);
        ArrayList<Club> clubs=new ArrayList<>();
        clubs.add(club1);
        clubs.add(club2);
        clubs.add(club3);
        clubs.add(club4);
        clubs.add(club1);
        clubs.add(club2);
        clubs.add(club3);
        clubs.add(club4);
        clubs.add(club1);
        clubs.add(club2);
        clubs.add(club3);
        clubs.add(club4);
        return clubs;
    }

    @Override
    protected void onPostExecute(ArrayList<Club> clubs) {
        pullToRefreshView.onRefreshComplete();
        for (Club c : clubs) {
            clubListItemAdapter.add(c);
        }
        super.onPostExecute(clubs);
    }
}
