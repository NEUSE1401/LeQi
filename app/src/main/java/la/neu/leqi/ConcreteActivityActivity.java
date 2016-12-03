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

import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;

public class ConcreteActivityActivity extends Activity {
    private TextView back_title;
    private ImageView imageView;

    private TextView name;
    private TextView count;
    private TextView releaseTime;
    private TextView startTime;
    private TextView endTime;
    private TextView place;
    private TextView description;
    private TextView requirement;
    private TextView partivipateWay;
    private TextView owner;
    private Button collect;
    private CarouselView carouselView;

    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_concrete_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);

        Intent intent=getIntent();
        ActivityBean activity= (ActivityBean) intent.getSerializableExtra("activity");
        findComponent();

        back_title.setText("活动详情");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imageLoader=new ImageLoader(this);

        ArrayList<String> pics=activity.getPic_listp();
        final AdViewListener adViewListener = new AdViewListener(pics,imageLoader,carouselView);
        carouselView.setPageCount(pics.size());
        carouselView.setImageListener(adViewListener);
        carouselView.addOnPageChangeListener(adViewListener);

        name.setText(activity.getTitle());
        count.setText(activity.getCount()+"");
        releaseTime.setText(activity.getReleaseTime());
        startTime.setText(activity.getStartTime());
        endTime.setText(activity.getEndTime());
        place.setText(activity.getActivityPlace());
        description.setText(activity.getDescription());
        requirement.setText(activity.getRequirement());
        partivipateWay.setText(activity.getParticipateWay());
        owner.setText(activity.getOwner());
    }

    private void findComponent(){
        back_title = (TextView)findViewById(R.id.back_title);
        imageView  =(ImageView)findViewById(R.id.back_icon);

        name= (TextView) findViewById(R.id.concrete_activity_name);
        count=(TextView) findViewById(R.id.concrete_activity_count);
        releaseTime=(TextView) findViewById(R.id.concrete_activity_release_time);
        startTime=(TextView) findViewById(R.id.concrete_activity_start_time);
        endTime=(TextView) findViewById(R.id.concrete_activity_end_time);
        place=(TextView) findViewById(R.id.concrete_activity_place);
        description=(TextView) findViewById(R.id.concrete_activity_description);
        requirement=(TextView) findViewById(R.id.concrete_activity_requirement);
        partivipateWay=(TextView) findViewById(R.id.concrete_activity_participate_way);
        owner=(TextView) findViewById(R.id.concrete_activity_owner);
        collect= (Button) findViewById(R.id.concrete_activity_collect);
        carouselView= (CarouselView) findViewById(R.id.concrete_activity_pic);
    }
}
