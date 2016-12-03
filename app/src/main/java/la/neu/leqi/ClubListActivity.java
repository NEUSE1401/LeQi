package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.ClubListItemAdapter;
import la.neu.leqi.bean.Club;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;
import la.neu.leqi.tools.image.ImageLoader;

public class ClubListActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener {
    private DrawerLayout drawerLayout;
    private CircleImageView userFace;
    private NavigationView menu;
    private ListView clubList;
    private BottomNavigationBar bottomNavigationBar;
    private final Class<?>[] classes ={MainActivity.class,BicycleShopListActivity.class,null,null,ActivityListActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBarBuilder.build(bottomNavigationBar,3);
        bottomNavigationBar.setTabSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        userFace = (CircleImageView) findViewById(R.id.user_face);
        userFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        menu = (NavigationView) findViewById(R.id.nav_view);
        menu.setNavigationItemSelectedListener(new MenuClickListener(ClubListActivity.this, drawerLayout));
        final PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.club_list);
        clubList = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setMode(PullToRefreshListView.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        //String title, String owner, String description, int level, String contectWay, String province, String city, String district, String addressDetial, ArrayList<String> pics, int participateCount
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
        final ClubListItemAdapter adapter=new ClubListItemAdapter(clubs,this,new ImageLoader(this));
        clubList.setAdapter(adapter);
        clubList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               adapter.onItemClick(adapterView,  view, i,  l);

            }
        });
    }

    @Override
    public void onTabSelected(int position) {
        if(classes[position]!=null){
            final Intent intent = new Intent();
            intent.setClass(ClubListActivity.this,classes[position]);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}
