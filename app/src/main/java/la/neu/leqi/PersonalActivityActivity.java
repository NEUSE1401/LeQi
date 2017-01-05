package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import la.neu.leqi.adapter.ActivityListViewAdapter;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.customview.PullToRefreshSwipeMenuListView;
import la.neu.leqi.thread.PersonalActivityDataRefresh;
import la.neu.leqi.thread.RemoveSelfActivityWebThread;
import la.neu.leqi.tools.image.ImageLoader;

public class PersonalActivityActivity extends Activity {
    private ImageView releaseActivity;
    private SwipeMenuListView listView;
    private TextView back_title;
    private ImageView imageView;
    private ImageLoader imageLoader;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_personal_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_add_title);
        back_title = (TextView) findViewById(R.id.back_title);
        back_title.setText("我的发布");
        imageView = (ImageView) findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        releaseActivity = (ImageView) findViewById(R.id.add);
        final PullToRefreshSwipeMenuListView pullToRefreshSwipeMenuListView = (PullToRefreshSwipeMenuListView) findViewById(R.id.personal_activity_list);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(R.color.RED);
                openItem.setWidth((int) getResources().getDimension(R.dimen.activity_item_height));
                openItem.setTitle("删除");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }
        };
        listView = pullToRefreshSwipeMenuListView.getRefreshableView();
        listView.setMenuCreator(creator);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        pullToRefreshSwipeMenuListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullToRefreshSwipeMenuListView.setScrollingWhileRefreshingEnabled(true);
        imageLoader = new ImageLoader(this);
        final ActivityListViewAdapter adapter = new ActivityListViewAdapter(imageLoader, this);
        adapter.setFlag(false);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        pullToRefreshSwipeMenuListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<SwipeMenuListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<SwipeMenuListView> refreshView) {
                new PersonalActivityDataRefresh(getString(R.string.WEB_PERSONAL_ACTIVITY_LIST_REFRESH), pullToRefreshSwipeMenuListView, adapter, PersonalActivityActivity.this).execute();
            }
        });
        //延时以完成初始自动下拉刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshSwipeMenuListView.setRefreshing();
            }
        }, 500);
        releaseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivityActivity.this, ReleaseActivityActivity.class);
                startActivity(intent);
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    adapter.remove(msg.getData().getInt("position"));
                    Toast.makeText(PersonalActivityActivity.this, "删除发布的活动成功", Toast.LENGTH_SHORT).show();
                } else if (msg.what == 0) {
                    Toast.makeText(PersonalActivityActivity.this, "删除发布的活动失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                new RemoveSelfActivityWebThread(getString(R.string.WEB_REMOVE_SELF_ACTIVITY), adapter.getItem(position), position, handler).start();
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
}
