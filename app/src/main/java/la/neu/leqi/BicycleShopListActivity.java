package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.BicycleShopListViewItemAdapter;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.thread.BicycleShopListDataWebThread;
import la.neu.leqi.thread.BicycleShopListRefreshWebThread;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;
import la.neu.leqi.tools.image.ImageLoader;

public class BicycleShopListActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener{

    private DrawerLayout drawerLayout;
    private CircleImageView userFace;
    private NavigationView menu;
    private ListView listView;
    private BottomNavigationBar bottomNavigationBar;
    private BicycleShopListViewItemAdapter adapter;
    private final Class<?>[] classes ={MainActivity.class,null,ShareListActivity.class,ClubListActivity.class,ActivityListActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_shop_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBarBuilder.build(bottomNavigationBar,1);
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
        menu.setNavigationItemSelectedListener(new MenuClickListener(BicycleShopListActivity.this, drawerLayout));


        final PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.bicycle_listview);
        listView = pullToRefreshListView.getRefreshableView();
        ImageLoader imageLoader=new ImageLoader(this);
        adapter=new BicycleShopListViewItemAdapter(this,imageLoader);
        listView.setOnItemClickListener(adapter);
        listView.setAdapter(adapter);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new BicycleShopListRefreshWebThread(getString(R.string.WEB_BICYCLE_SHOP_LIST_REFRESH), refreshView, adapter, BicycleShopListActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new BicycleShopListDataWebThread(getString(R.string.WEB_BICYCLE_SHOP_LIST_DATA), refreshView, adapter, BicycleShopListActivity.this).execute();
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
        if(classes[position]!=null){
            final Intent intent = new Intent();
            intent.setClass(BicycleShopListActivity.this,classes[position]);
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
