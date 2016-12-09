package la.neu.leqi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import la.neu.leqi.adapter.MyCollectAdapter;
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.customview.PullToRefreshSwipeMenuListView;
import la.neu.leqi.tools.image.ImageLoader;

public class MyCollectActivity extends Activity {
    private TextView back_title;
    private ImageView imageView;

    private SwipeMenuListView content;
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_my_collect);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("我的收藏");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        PullToRefreshSwipeMenuListView pullToRefreshSwipeMenuListView= (PullToRefreshSwipeMenuListView) findViewById(R.id.my_collect_content);
        content = pullToRefreshSwipeMenuListView.getRefreshableView();
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                if (menu.getViewType()==1) {
                    SwipeMenuItem openItem = new SwipeMenuItem(
                            getApplicationContext());
                    openItem.setBackground(R.color.RED);
                    openItem.setWidth((int) getResources().getDimension(R.dimen.activity_item_height));
                    openItem.setTitle("删除");
                    openItem.setTitleSize(18);
                    openItem.setTitleColor(Color.WHITE);
                    menu.addMenuItem(openItem);
                }
            }
        };
        content.setMenuCreator(creator);
        content.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        imageLoader = new ImageLoader(this);
        MyCollectAdapter adapter=new MyCollectAdapter(imageLoader,this);
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");

        final ActivityBean activityBean1 = new ActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1,10);
        final ActivityBean activityBean2 = new ActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2,100);
        final ActivityBean activityBean3 = new ActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3,45);
        final ActivityBean activityBean4 = new ActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4,20);
        adapter.addShopActivity(activityBean1);
        adapter.addShopActivity(activityBean2);
        adapter.addShopActivity(activityBean3);
        adapter.addShopActivity(activityBean4);

        BicycleShop shop1=new BicycleShop("2b101","槲荷缎花","超值优惠",5,"1878117xxxx",101,"东北大学浑南校区");
        BicycleShop shop2=new BicycleShop("2b102","花花","超值优惠",5,"1878117xxxx",101,"东北大学浑南校区");
        ArrayList<String> pic=new ArrayList<>();
        pic.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        shop1.setShopPics(pic);
        adapter.addShop(shop1);
        adapter.addShop(shop2);
        adapter.addShop(shop1);
        content.setAdapter(adapter);
    }
}
