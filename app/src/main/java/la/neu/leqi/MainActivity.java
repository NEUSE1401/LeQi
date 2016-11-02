package la.neu.leqi;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;


/**
 * @author HeXunshi
 *         首页Activity
 */
public class MainActivity extends Activity {

    private CarouselView CarouselView;
    private CircleImageView userFace;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自定义title
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //滚动广告栏
        final CarouselView carouselView = (CarouselView) findViewById(R.id.vPager);
        final ArrayList<String> viewList = new ArrayList<>();
        viewList.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider5.jpg");
        final AdViewListener adViewListener = new AdViewListener(viewList,this,carouselView);
        carouselView.setPageCount(5);
        carouselView.setImageListener(adViewListener);
        carouselView.addOnPageChangeListener(adViewListener);
        //乐骑活动显示
        final ImageLoader imageLoader = new ImageLoader(this);
        final ImageView activity_pic = (ImageView) findViewById(R.id.activity_pic);
        imageLoader.bindBitmap("http://neu.la/leqi/img/slider/Homeslider4.jpg",activity_pic);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        userFace= (CircleImageView) findViewById(R.id.user_face);
        userFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
}
