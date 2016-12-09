package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.MainActivityAdapter;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.thread.MainListRefreshWebThread;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;
import la.neu.leqi.tools.image.ImageLoader;


/**
 * @author HeXunshi
 *         首页Activity
 */
public class MainActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener,BottomNavigationBar.OnTabSelectedListener {

    private CircleImageView userFace;
    private DrawerLayout drawerLayout;
    private NavigationView menu;
    private ImageLoader imageLoader;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;
    private ListView activity_main_list;
    private MainActivityAdapter mainActivityAdapter;

    private BottomNavigationBar bottomNavigationBar;
    private final Class<?>[] classes ={null,BicycleShopListActivity.class,ShareListActivity.class,ClubListActivity.class,ActivityListActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自定义title
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBarBuilder.build(bottomNavigationBar,0);
        bottomNavigationBar.setTabSelectedListener(this);

        imageLoader = new ImageLoader(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        findBarComponent();
//        firstPagePic.setImageResource(R.drawable.home_active);
//        firstPageText.setTextColor(Color.parseColor("#12b6f6"));
        //滑动触发
//        View touch_view = findViewById(R.id.touch_view);
//        gestureDetector = new GestureDetector(MainActivity.this, this);
//        touch_view.setLongClickable(true);
//        touch_view.setOnTouchListener(this);
        //头像点击监听
        userFace = (CircleImageView) findViewById(R.id.user_face);
        userFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        menu = (NavigationView) findViewById(R.id.nav_view);
        menu.setNavigationItemSelectedListener(new MenuClickListener(MainActivity.this, drawerLayout));
        //商品列表
        final PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.activity_main_list);
        activity_main_list = pullToRefreshListView.getRefreshableView();
        mainActivityAdapter = new MainActivityAdapter(imageLoader, this);
        activity_main_list.setAdapter(mainActivityAdapter);
        //activity_main_list.setOnItemClickListener(mainActivityAdapter);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new MainListRefreshWebThread(getString(R.string.WEB_MAIN_LIST_REFRESH), refreshView, mainActivityAdapter, MainActivity.this).execute();
            }
        });
        pullToRefreshListView.setRefreshing();
    }

    //处理滑动后操作
    public void doResult(int action) {
        System.out.println(action);
        switch (action) {
            case RIGHT:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case LEFT:
                break;

        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float x = motionEvent1.getX() - motionEvent.getX();
        float y = motionEvent1.getY() - motionEvent.getY();

        if (x > 0) {
            doResult(RIGHT);
        } else if (x < 0) {
            doResult(LEFT);
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onTabSelected(int position) {
        if(classes[position]!=null){
            final Intent intent = new Intent();
            intent.setClass(MainActivity.this,classes[position]);
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
