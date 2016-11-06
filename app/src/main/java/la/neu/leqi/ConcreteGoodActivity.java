package la.neu.leqi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import la.neu.leqi.bean.Good;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;

public class ConcreteGoodActivity extends Activity {

    private TextView back_title;
    private ImageView imageView;

    private CarouselView goodsPics;
    private  TextView goodName;
    private TextView goodBrand;
    private TextView isRent;
    private TextView currentPrice;
    private TextView originalPrice;
    private TextView goodParameter;
    private TextView goodDescription;
    private TextView shopAddress;
    private Button contactSeller;
    private LinearLayout share;

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_concrete_good);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("商品详情");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        goodsPics= (CarouselView) findViewById(R.id.concrete_good_show_pic);
        goodName= (TextView) findViewById(R.id.concrete_good_name);
        goodBrand= (TextView) findViewById(R.id.concrete_good_brand);
        isRent= (TextView) findViewById(R.id.concrete_good_is_rent);
        currentPrice= (TextView) findViewById(R.id.concrete_good_current_price);
        originalPrice= (TextView) findViewById(R.id.concrete_good_original_price);
        goodParameter= (TextView) findViewById(R.id.concrete_good_parameter);
        goodDescription= (TextView) findViewById(R.id.concrete_good_description);
        shopAddress= (TextView) findViewById(R.id.concrete_good_shop_address);
        contactSeller= (Button) findViewById(R.id.concrete_good_contact_saller);
        share= (LinearLayout) findViewById(R.id.concrete_good_share_linear);

        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        Good good = new Good(1, "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null, pics1);

        imageLoader=new ImageLoader(this);
        ArrayList<String> pics=good.getPic_list();
        final AdViewListener adViewListener = new AdViewListener(pics,imageLoader,goodsPics);
        goodsPics.setPageCount(pics.size());
        goodsPics.setImageListener(adViewListener);
        goodsPics.addOnPageChangeListener(adViewListener);

        goodName.setText(good.getName());
        goodBrand.setText(good.getBrand());
        if(good.is_rent()){
            isRent.setText("可租");
        }else {
            isRent.setText("不可租");
        }
        currentPrice.setText(good.getCurrent_price()+"");
        originalPrice.setText(good.getOriginal_price()+"");
        goodDescription.setText(good.getDescription());

        String para="";
//        ArrayList<String> parametersList=good.getParemeters();
        ArrayList<String> parametersList=new ArrayList<>();
        for (String a:parametersList){
            para=para+a+"  ";
        }
        goodParameter.setText(para);
        shopAddress.setText("东北大学浑南校区");
    }
}
