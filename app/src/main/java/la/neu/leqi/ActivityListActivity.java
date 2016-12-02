package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.ActivityListViewAdapter;
import la.neu.leqi.bean.ShopActivityBean;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;
import la.neu.leqi.tools.image.ImageLoader;

public class ActivityListActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener,View.OnClickListener {

    private CircleImageView userFace;
    private DrawerLayout drawerLayout;
    private NavigationView menu;
    private ImageLoader imageLoader;
    private ListView activity_list;
    private BottomNavigationBar bottomNavigationBar;
    private final Class<?>[] classes ={MainActivity.class,BicycleShopListActivity.class,null,ClubListActivity.class,null};

    private LinearLayout general_button;
    private LinearLayout hot_button;
    private LinearLayout near_button;
    private TextView general_text;
    private TextView hot_text;
    private TextView near_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);
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
        general_button=(LinearLayout)findViewById(R.id.general_button);
        hot_button=(LinearLayout)findViewById(R.id.hot_button);
        near_button=(LinearLayout)findViewById(R.id.near_button);
        general_text=(TextView)findViewById(R.id.general_text);
        hot_text=(TextView)findViewById(R.id.hot_text);
        near_text=(TextView)findViewById(R.id.near_text);
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
        activity_list = (ListView)findViewById(R.id.activity_list);
        final ActivityListViewAdapter adapter = new ActivityListViewAdapter(imageLoader, this);
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        final ShopActivityBean activityBean1 = new ShopActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1,23);
        final ShopActivityBean activityBean2 = new ShopActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2,23);
        final ShopActivityBean activityBean3 = new ShopActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3,100);
        final ShopActivityBean activityBean4 = new ShopActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4,90);
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);
        activity_list.setAdapter(adapter);
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
        switch (view.getId()){
            case R.id.general_button:
                general_text.setTextColor(Color.parseColor("#FFFF7700"));
                hot_text.setTextColor(Color.parseColor("#000000"));
                near_text.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.hot_button:
                general_text.setTextColor(Color.parseColor("#000000"));
                hot_text.setTextColor(Color.parseColor("#FFFF7700"));
                near_text.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.near_button:
                general_text.setTextColor(Color.parseColor("#000000"));
                hot_text.setTextColor(Color.parseColor("#000000"));
                near_text.setTextColor(Color.parseColor("#FFFF7700"));
                break;
        }
    }
}
