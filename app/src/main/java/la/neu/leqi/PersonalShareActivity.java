package la.neu.leqi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.adapter.PersonalShareAdapter;
import la.neu.leqi.bean.Share;
import la.neu.leqi.tools.image.ImageLoader;

public class PersonalShareActivity extends Activity {
    private Button button;
    private ListView listView;
    private TextView back_title;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_personal_share);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("我的分享");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        button= (Button) findViewById(R.id.personal_share_release);
        listView= (ListView) findViewById(R.id.personal_share_list);

        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        Share share=new Share(1,"我的分享",pics1);
        PersonalShareAdapter adapter=new PersonalShareAdapter(this,new ImageLoader(this));
        adapter.add(share);
        adapter.add(share);
        adapter.add(share);
        adapter.add(share);
        adapter.add(share);
        adapter.add(share);
        adapter.add(share);

        listView.setAdapter(adapter);

    }
}
