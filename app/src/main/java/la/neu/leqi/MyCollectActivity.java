package la.neu.leqi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.adapter.MyCollectAdapter;
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.bean.ShopActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

public class MyCollectActivity extends Activity {
    private TextView back_title;
    private ImageView imageView;

    private ListView content;
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

        content= (ListView) findViewById(R.id.my_collect_content);
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

        final ShopActivityBean activityBean1 = new ShopActivityBean(1, "活动1", "2016-11-3", "2016-11-3", pics1,10);
        final ShopActivityBean activityBean2 = new ShopActivityBean(2, "活动2", "2016-11-3", "2016-11-3", pics2,100);
        final ShopActivityBean activityBean3 = new ShopActivityBean(3, "活动3", "2016-11-3", "2016-11-3", pics3,45);
        final ShopActivityBean activityBean4 = new ShopActivityBean(4, "活动4", "2016-11-3", "2016-11-3", pics4,20);
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
