package la.neu.leqi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;


import la.neu.leqi.adapter.MyCollectAdapter;

import la.neu.leqi.customview.PullToRefreshSwipeMenuListView;
import la.neu.leqi.thread.MyCollectRefreshWebThread;
import la.neu.leqi.tools.image.ImageLoader;

public class MyCollectActivity extends Activity {
    private TextView back_title;
    private ImageView imageView;

    private SwipeMenuListView content;
    private ImageLoader imageLoader;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_my_collect);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        back_title = (TextView)findViewById(R.id.back_title);
        back_title.setText("我的收藏");
        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final PullToRefreshSwipeMenuListView pullToRefreshSwipeMenuListView= (PullToRefreshSwipeMenuListView) findViewById(R.id.my_collect_content);
        content = pullToRefreshSwipeMenuListView.getRefreshableView();
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                if (menu.getViewType()==1) {
                    SwipeMenuItem openItem = new SwipeMenuItem(
                            getApplicationContext());
                    openItem.setBackground(R.color.RED);
                    openItem.setWidth((int) getResources().getDimension(R.dimen.activity_item_height));
                    openItem.setTitle("删除");
                    openItem.setTitleSize(18);
                    openItem.setTitleColor(Color.WHITE);
                    menu.addMenuItem(openItem);
                }
            }
        };
        content.setMenuCreator(creator);
        content.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        imageLoader = new ImageLoader(this);
        final MyCollectAdapter adapter=new MyCollectAdapter(imageLoader,this);
        content.setAdapter(adapter);
        content.setOnItemClickListener(adapter);
        pullToRefreshSwipeMenuListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<SwipeMenuListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<SwipeMenuListView> refreshView) {
                new MyCollectRefreshWebThread(getString(R.string.WEB_MY_COLLECTION_LIST_REFRESH),pullToRefreshSwipeMenuListView,adapter,MyCollectActivity.this).execute();
            }
        });
        //延时以完成初始自动下拉刷新
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshSwipeMenuListView.setRefreshing();
            }
        }, 500);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    adapter.remove(msg.getData().getInt("position"));
                    Toast.makeText(MyCollectActivity.this, "删除发布的活动成功", Toast.LENGTH_SHORT).show();
                } else if (msg.what == 0) {
                    Toast.makeText(MyCollectActivity.this, "删除发布的活动失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
        content.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
               // new RemoveSelfActivityWebThread(getString(R.string.WEB_REMOVE_SELF_ACTIVITY), adapter.getItem(position), position, handler).start();
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
}
