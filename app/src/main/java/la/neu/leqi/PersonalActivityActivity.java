package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import la.neu.leqi.adapter.ActivityListViewAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.thread.PersonalActivityDataRefresh;
import la.neu.leqi.tools.image.ImageLoader;

public class PersonalActivityActivity extends Activity {
    private ImageView releaseActivity;
    private ListView listView;
    private TextView back_title;
    private ImageView imageView;
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_personal_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_add_title);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("我的发布");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        releaseActivity= (ImageView) findViewById(R.id.add);
        final PullToRefreshListView pullToRefreshListView= (PullToRefreshListView) findViewById(R.id.personal_activity_list);
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        imageLoader=new ImageLoader(this);
        final ActivityListViewAdapter adapter = new ActivityListViewAdapter(imageLoader, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new PersonalActivityDataRefresh(getString(R.string.WEB_PERSONAL_ACTIVITY_LIST_REFRESH),pullToRefreshListView,adapter,PersonalActivityActivity.this).execute();
            }
        });
        //延时以完成初始自动下拉刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshListView.setRefreshing();
            }
        }, 500);
        releaseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PersonalActivityActivity.this,ReleaseActivityActivity.class);
                startActivity(intent);
            }
        });
    }
}
