package la.neu.leqi;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import la.neu.leqi.customview.SquareImageView;
import la.neu.leqi.listener.MenuClickListener;

public class BicycleShopListActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private CircleImageView userFace;
    private NavigationView menu;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_shop_list);

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

        findBarComponent();

        bicycleShopPic.setImageResource(R.drawable.cycling_active);
        bicycleShopText.setTextColor(Color.parseColor("#12b6f6"));

    }

    private void findBarComponent(){
        firstPageLinear= (LinearLayout) findViewById(R.id.bar_first_page_linear);
        bicycleShopLinear= (LinearLayout) findViewById(R.id.bar_bicycle_shop_linear);
        shareLinear= (LinearLayout) findViewById(R.id.bar_share_linear);
        clubLinear= (LinearLayout) findViewById(R.id.bar_club_linear);
        activityLinear= (LinearLayout) findViewById(R.id.bar_activity_linear);

        firstPagePic= (SquareImageView) findViewById(R.id.bar_first_page_pic);
        bicycleShopPic= (SquareImageView) findViewById(R.id.bar_bicycle_shop_pic);
        sharePic= (SquareImageView) findViewById(R.id.bar_share_pic);
        clubPic= (SquareImageView) findViewById(R.id.bar_club_pic);
        activityPic= (SquareImageView) findViewById(R.id.bar_activity_pic);

        firstPageText= (TextView) findViewById(R.id.bar_first_page_text);
        bicycleShopText= (TextView) findViewById(R.id.bar_bicycle_shop_text);
        shareText= (TextView) findViewById(R.id.bar_share_text);
        clubText= (TextView) findViewById(R.id.bar_club_text);
        activityText= (TextView) findViewById(R.id.bar_activity_text);
    }
}
