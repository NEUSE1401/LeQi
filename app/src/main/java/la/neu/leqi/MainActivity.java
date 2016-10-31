package la.neu.leqi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;

import la.neu.leqi.adapter.AutoScrollViewAdapter;
import la.neu.leqi.customview.AutoScrollViewPager;

/**
 * @author HeXunshi
 * 首页Activity
 */
public class MainActivity extends Activity {

    private AutoScrollViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自定义title
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.main_title);
        //滚动广告栏
        final ArrayList<String> viewList = new ArrayList<>();
        viewList.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider5.jpg");
        final AutoScrollViewAdapter viewAdapter = new AutoScrollViewAdapter(viewList, this);
        mPager=(AutoScrollViewPager)findViewById(R.id.vPager);
        mPager.setAdapter(viewAdapter);
        mPager.setCurrentItem(0);
        mPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);
        mPager.setCycle(true);
        mPager.setInterval(2000);
        mPager.startAutoScroll();
    }
}
