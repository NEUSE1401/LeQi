package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;


import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.ShareListAdapter;
import la.neu.leqi.customview.PullToRefreshRecycleView;
import la.neu.leqi.customview.SpacesItemDecoration;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.thread.ShareListDataWebThread;
import la.neu.leqi.thread.ShareListRefreshWebThread;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;
import la.neu.leqi.tools.image.ImageLoader;

public class ShareListActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener{
    private RecyclerView mRecyclerView;
    private DrawerLayout drawerLayout;
    private CircleImageView userFace;
    private NavigationView menu;
    private BottomNavigationBar bottomNavigationBar;
    private final Class<?>[] classes ={MainActivity.class,BicycleShopListActivity.class,null,ClubListActivity.class,ActivityListActivity.class};

    @Override
    protected void onStart() {
        initHead();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBarBuilder.build(bottomNavigationBar,2);
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
        menu.setNavigationItemSelectedListener(new MenuClickListener(ShareListActivity.this, drawerLayout));
        final PullToRefreshRecycleView pullToRefreshRecycleView = (PullToRefreshRecycleView) findViewById(R.id.recycler);
        pullToRefreshRecycleView.setScrollingWhileRefreshingEnabled(true);
        pullToRefreshRecycleView.setMode(PullToRefreshRecycleView.Mode.BOTH);
        mRecyclerView = pullToRefreshRecycleView.getRefreshableView();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final ImageLoader imageLoader = new ImageLoader(this);
        final ShareListAdapter shareListAdapter = new ShareListAdapter(this,imageLoader);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setAdapter(shareListAdapter);
        pullToRefreshRecycleView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                System.out.println("aaaa");
                new ShareListRefreshWebThread(getResources().getString(R.string.WEB_BICYCLE_SHARE_LIST_REFRESH),pullToRefreshRecycleView,shareListAdapter,ShareListActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                new ShareListDataWebThread(getResources().getString(R.string.WEB_BICYCLE_SHARE_LIST_DATA),pullToRefreshRecycleView,shareListAdapter,ShareListActivity.this).execute();

            }
        });
        //延时以完成初始自动下拉刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshRecycleView.setRefreshing();
            }
        }, 500);
        pullToRefreshRecycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("erwer");
                pullToRefreshRecycleView.setRefreshing();
            }
        });
    }
    private void initHead(){
        final View headerView = menu.getHeaderView(0);
        final TextView name = (TextView) headerView.findViewById(R.id.name);
        final TextView textView = (TextView) headerView.findViewById(R.id.textView);
        final ImageView user_icon = (ImageView) headerView.findViewById(R.id.imageView);
        final SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        final String username = user.getString("username", "");
        String token = user.getString("token","");
        if (!username.isEmpty()&&!token.isEmpty()) {
            headerView.setClickable(false);
            user_icon.setImageResource(R.drawable.deault_icon2);
            userFace.setImageResource(R.drawable.default_icon);
        }else{
            name.setText("登录/注册");
            textView.setText("Hello,leqi!");
            user_icon.setImageResource(R.drawable.user);
            userFace.setImageResource(R.drawable.user);
            headerView.setClickable(true);
            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ShareListActivity.this, LogAndRegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    public void onTabSelected(int position) {
        if(classes[position]!=null){
            final Intent intent = new Intent();
            intent.setClass(ShareListActivity.this,classes[position]);
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
