package la.neu.leqi;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import la.neu.leqi.adapter.LogAdapter;
import la.neu.leqi.fragment.Log;
import la.neu.leqi.fragment.Register;

public class LogAndRegisterActivity extends FragmentActivity implements View.OnClickListener{
    private LinearLayout logLinear;
    private LinearLayout registerLinear;
    private ImageView back;
    private FragmentManager fr;
    private FragmentTransaction ft;
    private ImageView arrow;
    private int bmpW;// 动画图片宽度
    private int currIndex;
    private int one;
    private int two;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;
    //实现Tab滑动效果
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.logMainColor));
        }
        logLinear = (LinearLayout) findViewById(R.id.log_linear);
        registerLinear = (LinearLayout) findViewById(R.id.register_linear);
        arrow = (ImageView) findViewById(R.id.log_arrow);
        back = (ImageView) findViewById(R.id.back);
        initImageView();
        logLinear.setOnClickListener(this);
        registerLinear.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initImageView();
        initFragment();
        initViewPager();
    }

    private void initImageView() {
        arrow = (ImageView) findViewById(R.id.log_arrow);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.arrow)
                .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        one = 0;
        two = screenW / 2 ;
        currIndex = 1;
        final Matrix matrix = new Matrix();
        matrix.postTranslate(screenW / 4 - bmpW / 2,0);
        arrow.setImageMatrix(matrix);
    }
    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void initFragment(){
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new Log());
        fragmentArrayList.add(new Register());
        fr = getSupportFragmentManager();

    }
    /**
            * 初始化页卡内容区
    */
    private void initViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.log_and_register_content);
        mViewPager.setAdapter(new LogAdapter(fr, fragmentArrayList));
        mViewPager.setOffscreenPageLimit(0);
        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //设置viewpager页面滑动监听事件
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }
    /**
     * 页卡切换监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            Animation animation = null ;
            switch (position){
                //当前为页卡1
                case 0:
                    currIndex =1;
                    animation = new TranslateAnimation(two, one, 0, 0);
                    animation.setFillAfter(true);// True:图片停在动画结束位置
                    animation.setDuration(300);
                    arrow.startAnimation(animation);
                    mViewPager.setCurrentItem(0);
                    break;
                case 1:
                    currIndex=2;
                    animation = new TranslateAnimation(one, two, 0, 0);
                    animation.setFillAfter(true);// True:图片停在动画结束位置
                    animation.setDuration(300);
                    arrow.startAnimation(animation);
                    mViewPager.setCurrentItem(1);
                    break;

            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_linear:
                if (currIndex == 2) {
                    currIndex =1;
                    mViewPager.setCurrentItem(0);
                }
                break;
            case R.id.register_linear:
                if (currIndex == 1) {
                    currIndex=2;
                    mViewPager.setCurrentItem(1);
                }
                break;
        }
    }


}
