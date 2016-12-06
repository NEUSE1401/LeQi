package la.neu.leqi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.adapter.ActivityListViewAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

public class PersonalActivityActivity extends Activity {
    private Button releaseActivity;
    private ListView listView;
    private TextView back_title;
    private ImageView imageView;
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_personal_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("我的发布");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        releaseActivity= (Button) findViewById(R.id.personal_activity_release);
        listView= (ListView) findViewById(R.id.personal_activity_list);
        imageLoader=new ImageLoader(this);
        final ActivityListViewAdapter adapter = new ActivityListViewAdapter(imageLoader, this);
        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        //    public ActivityBean(int activityId, String title, String description, String startTime, String endTime, String releaseTime, ArrayList<String> pic_listp, int count, String startPlace, String endPlace, String activityPlace, String participateWay, String owner, String requirement) {
        String des="沈阳今来天气好转，故想骑行，从和平区出发，到棋盘山，历时一天，感兴趣的骑友欢迎一起来";
        String way="致电187XXXX2641";
        final ActivityBean activityBean1 = new ActivityBean(1, "出行", des,"2016-11-3", "2016-11-6","2016-11-3", pics1,23,"沈阳","沈阳","沈阳XXX",way,"个人","无");
        final ActivityBean activityBean2 = new ActivityBean(2, "骑行", des,"2016-11-3", "2016-11-7","2016-11-3", pics2,23,"沈阳","沈阳","沈阳XXX",way,"个人","无");
        final ActivityBean activityBean3 = new ActivityBean(3, "骑行", des,"2016-11-3", "2016-11-3","2016-11-1", pics3,100,"沈阳","沈阳","沈阳XXX",way,"个人","无");
        final ActivityBean activityBean4 = new ActivityBean(4, "远足", des,"2016-11-3", "2016-11-3", "2016-11-1",pics4,90,"沈阳","沈阳","沈阳XXX",way,"个人","无");
        adapter.add(activityBean1);
        adapter.add(activityBean2);
        adapter.add(activityBean3);
        adapter.add(activityBean4);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.onItemClick(adapterView,view,i,l);
            }
        });
    }
}
