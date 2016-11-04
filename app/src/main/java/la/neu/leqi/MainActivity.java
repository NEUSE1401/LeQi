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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.adapter.MainActivityAdapter;
import la.neu.leqi.bean.Good;
import la.neu.leqi.bean.Share;
import la.neu.leqi.bean.ShopActivityBean;
import la.neu.leqi.customview.SquareImageView;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.tools.image.ImageLoader;


/**
 * @author HeXunshi
 *         首页Activity
 */
public class MainActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private CircleImageView userFace;
    private DrawerLayout drawerLayout;
    private NavigationView menu;
    private ImageLoader imageLoader;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;
    private LinearLayout bicycleShop;

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
        imageLoader = new ImageLoader(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        findBarComponent();
        firstPagePic.setImageResource(R.drawable.home_active);
        firstPageText.setTextColor(Color.parseColor("#12b6f6"));
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
        final ListView activity_main_list = (ListView) findViewById(R.id.activity_main_list);
        final MainActivityAdapter mainActivityAdapter = new MainActivityAdapter(imageLoader, this);
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        Good good1 = new Good(1, "自行车", 19.9, 18.8, pics1);
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        Good good2 = new Good(2, "自行车", 19.9, 18.8, pics2);
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        Good good3 = new Good(3, "自行车", 19.9, 18.8, pics3);
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        Good good4 = new Good(4, "自行车", 19.9, 18.8, pics4);
        mainActivityAdapter.addGood(good1);
        mainActivityAdapter.addGood(good2);
        mainActivityAdapter.addGood(good3);
        mainActivityAdapter.addGood(good4);
        final ShopActivityBean activityBean1 = new ShopActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1);
        final ShopActivityBean activityBean2 = new ShopActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2);
        final ShopActivityBean activityBean3 = new ShopActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3);
        final ShopActivityBean activityBean4 = new ShopActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4);
        mainActivityAdapter.addActivity(activityBean1);
        mainActivityAdapter.addActivity(activityBean2);
        mainActivityAdapter.addActivity(activityBean3);
        mainActivityAdapter.addActivity(activityBean4);
        final Share share1 = new Share(1, "分享1", pics1);
        final Share share2 = new Share(2, "分享2", pics2);
        final Share share3 = new Share(3, "分享3", pics3);
        final Share share4 = new Share(4, "分享4", pics4);
        mainActivityAdapter.addShard(share1);
        mainActivityAdapter.addShard(share2);
        mainActivityAdapter.addShard(share3);
        mainActivityAdapter.addShard(share4);
        activity_main_list.setAdapter(mainActivityAdapter);
        //导航栏跳转

        bicycleShopLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BicycleShopListActivity.class);
                startActivity(intent);
            }
        });


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

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        totalHeight += (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }

    private void findBarComponent() {
        firstPageLinear = (LinearLayout) findViewById(R.id.bar_first_page_linear);
        bicycleShopLinear = (LinearLayout) findViewById(R.id.bar_bicycle_shop_linear);
        shareLinear = (LinearLayout) findViewById(R.id.bar_share_linear);
        clubLinear = (LinearLayout) findViewById(R.id.bar_club_linear);
        activityLinear = (LinearLayout) findViewById(R.id.bar_activity_linear);

        firstPagePic = (SquareImageView) findViewById(R.id.bar_first_page_pic);
        bicycleShopPic = (SquareImageView) findViewById(R.id.bar_bicycle_shop_pic);
        sharePic = (SquareImageView) findViewById(R.id.bar_share_pic);
        clubPic = (SquareImageView) findViewById(R.id.bar_club_pic);
        activityPic = (SquareImageView) findViewById(R.id.bar_activity_pic);

        firstPageText = (TextView) findViewById(R.id.bar_first_page_text);
        bicycleShopText = (TextView) findViewById(R.id.bar_bicycle_shop_text);
        shareText = (TextView) findViewById(R.id.bar_share_text);
        clubText = (TextView) findViewById(R.id.bar_club_text);
        activityText = (TextView) findViewById(R.id.bar_activity_text);
    }

    private LinearLayout bicycleShopLinear;
    private LinearLayout firstPageLinear;
    private LinearLayout shareLinear;
    private LinearLayout clubLinear;
    private LinearLayout activityLinear;

    private SquareImageView bicycleShopPic;
    private SquareImageView firstPagePic;
    private SquareImageView sharePic;
    private SquareImageView clubPic;
    private SquareImageView activityPic;

    private TextView bicycleShopText;
    private TextView firstPageText;
    private TextView shareText;
    private TextView clubText;
    private TextView activityText;
}
