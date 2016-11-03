package la.neu.leqi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.LineNumberReader;

import la.neu.leqi.shopfragment.ShopActivityContent;
import la.neu.leqi.shopfragment.ShopAllGoodContent;
import la.neu.leqi.shopfragment.ShopHomePageContent;
import la.neu.leqi.shopfragment.ShopLatestGoogContent;
import la.neu.leqi.tools.image.ImageLoader;

public class ShopActivity extends Activity implements View.OnClickListener{

    private TextView back_title;
    private ImageView imageView;

    private LinearLayout homeLinear;
    private LinearLayout allGoodLinear;
    private LinearLayout latestLinear;
    private LinearLayout activityLinear;

    private ImageView shopBarHomePic;
    private ImageView shopBarAllGoodPic;
    private ImageView shopBarLatestPic;
    private ImageView shopBarActivityPic;

    private TextView shopBarHomeText;
    private TextView shopBarAllGoodText;
    private TextView shopBarLatestText;
    private TextView shopBarActivityText;

    private FragmentManager fr;
    private FragmentTransaction ft;

    private ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_shop);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        imageLoader = new ImageLoader(this);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("...");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        homeLinear= (LinearLayout) findViewById(R.id.shop_home_linear);
        allGoodLinear= (LinearLayout) findViewById(R.id.shop_all_good_linear);
        latestLinear= (LinearLayout) findViewById(R.id.shop_latest_linear);
        activityLinear= (LinearLayout) findViewById(R.id.shop_activity_linear);

        homeLinear.setOnClickListener(this);
        allGoodLinear.setOnClickListener(this);
        latestLinear.setOnClickListener(this);
        activityLinear.setOnClickListener(this);

        shopBarHomePic= (ImageView) findViewById(R.id.shop_home_page_pic);
        shopBarAllGoodPic= (ImageView) findViewById(R.id.shop_all_good_pic);
        shopBarLatestPic= (ImageView) findViewById(R.id.shop_latest_good_pic);
        shopBarActivityPic= (ImageView) findViewById(R.id.shop_activity_pic);

        shopBarHomeText= (TextView) findViewById(R.id.shop_home_text);
        shopBarAllGoodText= (TextView) findViewById(R.id.shop_allgood_text);
        shopBarLatestText= (TextView) findViewById(R.id.shop_latest_text);
        shopBarActivityText= (TextView) findViewById(R.id.shop_activity_text);

        fr=getFragmentManager();
        ft=fr.beginTransaction();

        ft.replace(R.id.shop_content,new ShopHomePageContent());
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        ft=fr.beginTransaction();
        switch (view.getId()){
            case R.id.shop_home_linear:
                ft.replace(R.id.shop_content,new ShopHomePageContent());
                changeToShopHomePage();
                break;
            case R.id.shop_all_good_linear:
                ft.replace(R.id.shop_content,new ShopAllGoodContent());
                changeToShopAllGood();
                break;
            case R.id.shop_latest_linear:
                ft.replace(R.id.shop_content,new ShopLatestGoogContent());
                changeToShopLatestGood();
                break;
            case R.id.shop_activity_linear:
                ft.replace(R.id.shop_activity_linear,new ShopActivityContent());
                changeToShopActivity();
                break;
        }
        ft.commit();
    }

    public void changeToShopHomePage(){
        shopBarHomePic.setImageResource(R.drawable.shop_home_red);
        shopBarAllGoodPic.setImageResource(R.drawable.all_commodites_gray);
        shopBarLatestPic.setImageResource(R.drawable.newest_commodites_gray);
        shopBarActivityPic.setImageResource(R.drawable.shop_activities_gray);

        shopBarHomeText.setTextColor(Color.parseColor("#a00808"));
        shopBarAllGoodText.setTextColor(Color.parseColor("#999ca9"));
        shopBarLatestText.setTextColor(Color.parseColor("#999ca9"));
        shopBarActivityText.setTextColor(Color.parseColor("#999ca9"));

    }

    public void changeToShopAllGood(){
        shopBarHomePic.setImageResource(R.drawable.shop_home_gray);
        shopBarAllGoodPic.setImageResource(R.drawable.all_commodites_red);
        shopBarLatestPic.setImageResource(R.drawable.newest_commodites_gray);
        shopBarActivityPic.setImageResource(R.drawable.shop_activities_gray);

        shopBarHomeText.setTextColor(Color.parseColor("#999ca9"));
        shopBarAllGoodText.setTextColor(Color.parseColor("#a00808"));
        shopBarLatestText.setTextColor(Color.parseColor("#999ca9"));
        shopBarActivityText.setTextColor(Color.parseColor("#999ca9"));
    }

    public void changeToShopLatestGood(){
        shopBarHomePic.setImageResource(R.drawable.shop_home_gray);
        shopBarAllGoodPic.setImageResource(R.drawable.all_commodites_gray);
        shopBarLatestPic.setImageResource(R.drawable.newest_commodites_red);
        shopBarActivityPic.setImageResource(R.drawable.shop_activities_gray);

        shopBarHomeText.setTextColor(Color.parseColor("#999ca9"));
        shopBarAllGoodText.setTextColor(Color.parseColor("#999ca9"));
        shopBarLatestText.setTextColor(Color.parseColor("#a00808"));
        shopBarActivityText.setTextColor(Color.parseColor("#999ca9"));
    }

    public void changeToShopActivity(){
        shopBarHomePic.setImageResource(R.drawable.shop_home_gray);
        shopBarAllGoodPic.setImageResource(R.drawable.all_commodites_gray);
        shopBarLatestPic.setImageResource(R.drawable.newest_commodites_gray);
        shopBarActivityPic.setImageResource(R.drawable.shop_activities_red);

        shopBarHomeText.setTextColor(Color.parseColor("#999ca9"));
        shopBarAllGoodText.setTextColor(Color.parseColor("#999ca9"));
        shopBarLatestText.setTextColor(Color.parseColor("#999ca9"));
        shopBarActivityText.setTextColor(Color.parseColor("#a00808"));
    }
}
