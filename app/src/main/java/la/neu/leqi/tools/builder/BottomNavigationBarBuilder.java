package la.neu.leqi.tools.builder;

import android.content.Context;
import android.content.Intent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import la.neu.leqi.R;

/**
 * 底部导航栏
 */

public class BottomNavigationBarBuilder{

    public static void build(BottomNavigationBar bottomNavigationBar,int index) {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home, "首页").setActiveColorResource(R.color.navActiveTextColor))
                .addItem(new BottomNavigationItem(R.drawable.cycling, "车铺").setActiveColorResource(R.color.navActiveTextColor))
                .addItem(new BottomNavigationItem(R.drawable.share, "分享").setActiveColorResource(R.color.navActiveTextColor))
                .addItem(new BottomNavigationItem(R.drawable.groups, "俱乐部").setActiveColorResource(R.color.navActiveTextColor))
                .addItem(new BottomNavigationItem(R.drawable.flag, "活动").setActiveColorResource(R.color.navActiveTextColor))
                .setFirstSelectedPosition(index)
                .initialise();
    }

}
