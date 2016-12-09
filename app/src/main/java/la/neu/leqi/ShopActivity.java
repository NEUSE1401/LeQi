package la.neu.leqi;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

import la.neu.leqi.adapter.CustomFragmentAdapter;
import la.neu.leqi.fragment.ShopActivityContent;
import la.neu.leqi.fragment.ShopAllGoodContent;
import la.neu.leqi.fragment.ShopHomePageContent;
import la.neu.leqi.fragment.ShopLatestGoodContent;

public class ShopActivity extends AppCompatActivity{
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> titleList;
    //实现Tab滑动效果
    private ViewPager mViewPager;
    private FragmentManager fr;
    private TabLayout tabLayout;
    private ImageView back;
    private final int default_icons[]={R.drawable.shop_home_default,R.drawable.all_commodites_default,R.drawable.newest_commodites_default,R.drawable.shop_activities_default};
    private final int selected_icons[]={R.drawable.shop_home_selected,R.drawable.all_commodites_selected,R.drawable.newest_commodites_selected,R.drawable.shop_activities_selected};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.logMainColor));
        }
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initFragment();
        initViewPager();
        initTab();
    }

    private void initTab() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        final TabLayout.Tab tab1 = tabLayout.getTabAt(0);
        tab1.setIcon(R.drawable.shop_home_default);
        final TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        tab2.setIcon(R.drawable.all_commodites_default);
        final TabLayout.Tab tab3 = tabLayout.getTabAt(2);
        tab3.setIcon(R.drawable.newest_commodites_default);
        final TabLayout.Tab tab4 = tabLayout.getTabAt(3);
        tab4.setIcon(R.drawable.shop_activities_default);
        final TabLayout.Tab tabs[]={tab1,tab2,tab3,tab4};
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println(tab.getPosition());
                for (int i = 0; i < 4; i++) {
                    if(i==tab.getPosition()){
                        tabs[i].setIcon(selected_icons[i]);
                    }else{
                        tabs[i].setIcon(default_icons[i]);
                    }
                }
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void initFragment(){
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new ShopHomePageContent());
        fragmentArrayList.add(new ShopAllGoodContent());
        fragmentArrayList.add(new ShopLatestGoodContent());
        fragmentArrayList.add(new ShopActivityContent());
        titleList = new ArrayList<>();
        titleList.add("首页");
        titleList.add("全部商品");
        titleList.add("最新商品");
        titleList.add("店铺活动");
        fr = getSupportFragmentManager();

    }
    /**
     * 初始化页卡内容区
     */
    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.shop_content);
        mViewPager.setAdapter(new CustomFragmentAdapter(fr, fragmentArrayList,titleList));
        mViewPager.setOffscreenPageLimit(0);
        //设置默认打开第一页
        mViewPager.setCurrentItem(0);
    }
}
