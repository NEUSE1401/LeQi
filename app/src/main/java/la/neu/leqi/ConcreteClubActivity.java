package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import la.neu.leqi.bean.Club;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;

public class ConcreteClubActivity extends Activity {
    private TextView back_title;
    private ImageView imageView;

    private TextView clubName;
    private TextView address;
    private TextView level;
    private TextView personCount;
    private TextView owner;
    private TextView description;
    private TextView contact;
    private Button participate;
    private CarouselView carouselView;

    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_concrete_club);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);

        Intent intent=getIntent();
        Club club= (Club) intent.getSerializableExtra("club");
        findComponent();

       back_title.setText(club.getTitle());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imageLoader=new ImageLoader(this);
        ArrayList<String> pics=club.getPics();
        final AdViewListener adViewListener = new AdViewListener(pics,imageLoader,carouselView);
        carouselView.setPageCount(pics.size());
        carouselView.setImageListener(adViewListener);
        carouselView.addOnPageChangeListener(adViewListener);

        clubName.setText(club.getTitle());
        address.setText(club.getAddress());
        level.setText(club.getLevel()+"");
        personCount.setText(club.getParticipateCount()+"");
        owner.setText(club.getOwner());
        description.setText(club.getDescription());
        contact.setText(club.getContectWay());

    }

    private void findComponent(){
        clubName= (TextView) findViewById(R.id.concrete_club_name);
        address= (TextView) findViewById(R.id.concrete_club_address);
        level= (TextView) findViewById(R.id.concrete_club_level);
        personCount= (TextView) findViewById(R.id.concrete_club_count);
        owner= (TextView) findViewById(R.id.concrete_club_owner);
        description= (TextView) findViewById(R.id.concrete_club_description);
        contact= (TextView) findViewById(R.id.concrete_club_contact_way);
        carouselView= (CarouselView) findViewById(R.id.concrete_club_pic);
        participate= (Button) findViewById(R.id.concrete_club_join);

        back_title = (TextView)findViewById(R.id.back_title);
        imageView  =(ImageView)findViewById(R.id.back_icon);
    }
}
