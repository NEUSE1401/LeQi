package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.listener.MenuClickListener;
import la.neu.leqi.tools.builder.BottomNavigationBarBuilder;

public class BicycleShopListActivity extends Activity implements BottomNavigationBar.OnTabSelectedListener{

    private DrawerLayout drawerLayout;
    private CircleImageView userFace;
    private NavigationView menu;

    private BottomNavigationBar bottomNavigationBar;
    private final Class<?>[] classes ={MainActivity.class,null,null,null,null};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_shop_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBarBuilder.build(bottomNavigationBar,1);
        bottomNavigationBar.setTabSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        userFace = (CircleImageView) findViewById(R.id.user_face);
        userFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        menu = (NavigationView) findViewById(R.id.nav_view);
        menu.setNavigationItemSelectedListener(new MenuClickListener(BicycleShopListActivity.this, drawerLayout));

        ListView listView= (ListView) findViewById(R.id.bicycle_listview);

    }

    @Override
    public void onTabSelected(int position) {
        if(classes[position]!=null){
            final Intent intent = new Intent();
            intent.setClass(BicycleShopListActivity.this,classes[position]);
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

//    private void findBarComponent(){
//        firstPageLinear= (LinearLayout) findViewById(R.id.bar_first_page_linear);
//        bicycleShopLinear= (LinearLayout) findViewById(R.id.bar_bicycle_shop_linear);
//        shareLinear= (LinearLayout) findViewById(R.id.bar_share_linear);
//        clubLinear= (LinearLayout) findViewById(R.id.bar_club_linear);
//        activityLinear= (LinearLayout) findViewById(R.id.bar_activity_linear);
//
//        firstPagePic= (SquareImageView) findViewById(R.id.bar_first_page_pic);
//        bicycleShopPic= (SquareImageView) findViewById(R.id.bar_bicycle_shop_pic);
//        sharePic= (SquareImageView) findViewById(R.id.bar_share_pic);
//        clubPic= (SquareImageView) findViewById(R.id.bar_club_pic);
//        activityPic= (SquareImageView) findViewById(R.id.bar_activity_pic);
//
//        firstPageText= (TextView) findViewById(R.id.bar_first_page_text);
//        bicycleShopText= (TextView) findViewById(R.id.bar_bicycle_shop_text);
//        shareText= (TextView) findViewById(R.id.bar_share_text);
//        clubText= (TextView) findViewById(R.id.bar_club_text);
//        activityText= (TextView) findViewById(R.id.bar_activity_text);
//    }
}
