package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.ActivityListViewAdapter;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.thread.ActivityListDataWebThread;
import la.neu.leqi.thread.ActivityListRefreshWebThread;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;
import la.neu.leqi.tools.image.ImageLoader;

public class ActivityListActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    private CircleImageView userFace;
    private DrawerLayout drawerLayout;
    private NavigationView menu;
    private ImageLoader imageLoader;
    private ListView activity_list;
    private BottomNavigationBar bottomNavigationBar;
    private PullToRefreshListView pullToRefreshListView;
    private final Class<?>[] classes = {MainActivity.class, BicycleShopListActivity.class, ShareListActivity.class, ClubListActivity.class, null};

    private LinearLayout general_button;
    private LinearLayout hot_button;
    private LinearLayout near_button;
    private TextView general_text;
    private TextView hot_text;
    private TextView near_text;
    private int type;

    private void initHead(){
        final View headerView = menu.getHeaderView(0);
        final TextView name = (TextView) headerView.findViewById(R.id.name);
        final TextView textView = (TextView) headerView.findViewById(R.id.textView);
        final SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        final String username = user.getString("username", "");
        String token = user.getString("token","");
        if (!username.isEmpty()&&!token.isEmpty()) {
            headerView.setClickable(false);
        }else{
            name.setText("登录/注册");
            textView.setText("Hello,leqi!");
            headerView.setClickable(true);
            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ActivityListActivity.this, LogAndRegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onStart() {
        initHead();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
        type = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBarBuilder.build(bottomNavigationBar, 4);
        bottomNavigationBar.setTabSelectedListener(this);
        imageLoader = new ImageLoader(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //分类选择
        general_button = (LinearLayout) findViewById(R.id.general_button);
        hot_button = (LinearLayout) findViewById(R.id.hot_button);
        near_button = (LinearLayout) findViewById(R.id.near_button);
        general_text = (TextView) findViewById(R.id.general_text);
        hot_text = (TextView) findViewById(R.id.hot_text);
        near_text = (TextView) findViewById(R.id.near_text);
        general_text.setTextColor(Color.parseColor("#FFFF7700"));
        general_button.setOnClickListener(this);
        hot_button.setOnClickListener(this);
        near_button.setOnClickListener(this);
        //头像点击监听
        userFace = (CircleImageView) findViewById(R.id.user_face);
        userFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        menu = (NavigationView) findViewById(R.id.nav_view);
        menu.setNavigationItemSelectedListener(new MenuClickListener(ActivityListActivity.this, drawerLayout));
        //列表加载
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.activity_list);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        activity_list = pullToRefreshListView.getRefreshableView();
        final ActivityListViewAdapter adapter = new ActivityListViewAdapter(imageLoader, this);
        activity_list.setAdapter(adapter);
        activity_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.onItemClick(adapterView, view, i, l);
            }
        });
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new ActivityListRefreshWebThread(getString(R.string.WEB_ACTIVITY_LIST_REFRESH), type, refreshView, adapter, ActivityListActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new ActivityListDataWebThread(getString(R.string.WEB_ACTIVITY_LIST_DATA), type, refreshView, adapter, ActivityListActivity.this).execute();
            }
        });
        //延时以完成初始自动下拉刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshListView.setRefreshing();
            }
        }, 500);
    }

    @Override
    public void onTabSelected(int position) {
        if (classes[position] != null) {
            final Intent intent = new Intent();
            intent.setClass(ActivityListActivity.this, classes[position]);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.general_button:
                general_text.setTextColor(Color.parseColor("#FFFF7700"));
                hot_text.setTextColor(Color.parseColor("#000000"));
                near_text.setTextColor(Color.parseColor("#000000"));
                type = 0;
                pullToRefreshListView.setCurrentMode(PullToRefreshBase.Mode.PULL_FROM_START);
                pullToRefreshListView.setRefreshing();
                break;
            case R.id.hot_button:
                general_text.setTextColor(Color.parseColor("#000000"));
                hot_text.setTextColor(Color.parseColor("#FFFF7700"));
                near_text.setTextColor(Color.parseColor("#000000"));
                type = 1;
                pullToRefreshListView.setCurrentMode(PullToRefreshBase.Mode.PULL_FROM_START);
                pullToRefreshListView.setRefreshing();
                break;
            case R.id.near_button:
                general_text.setTextColor(Color.parseColor("#000000"));
                hot_text.setTextColor(Color.parseColor("#000000"));
                near_text.setTextColor(Color.parseColor("#FFFF7700"));
                type = 2;
                pullToRefreshListView.setCurrentMode(PullToRefreshBase.Mode.PULL_FROM_START);
                pullToRefreshListView.setRefreshing();
                break;
        }
    }

}
