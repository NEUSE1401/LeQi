package la.neu.leqi;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.tools.image.ImageLoader;


/**
 * @author HeXunshi
 *         首页Activity
 */
public class MainActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private CarouselView CarouselView;
    private CircleImageView userFace;
    private DrawerLayout drawerLayout;
    private NavigationView menu;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;

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
        final AdViewListener adViewListener = new AdViewListener(viewList, this, carouselView);
        carouselView.setPageCount(5);
        carouselView.setImageListener(adViewListener);
        carouselView.addOnPageChangeListener(adViewListener);
        //乐骑活动显示
        final ImageLoader imageLoader = new ImageLoader(this);
        final ImageView activity_pic = (ImageView) findViewById(R.id.activity_pic);
        imageLoader.bindBitmap("http://neu.la/leqi/img/slider/Homeslider4.jpg", activity_pic);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        View touch_view =  findViewById(R.id.touch_view);
        gestureDetector = new GestureDetector(MainActivity.this, this);
        touch_view.setLongClickable(true);
        touch_view.setOnTouchListener(this);
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
        System.out.println("touch");
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
