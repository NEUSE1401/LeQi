package la.neu.leqi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.ShopActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * 活动适配器
 */

public class ActivityListViewAdapter extends BaseAdapter {
    private ArrayList<ShopActivityBean> activities;
    private ImageLoader imageLoader;
    private Context context;

    public ActivityListViewAdapter(ImageLoader imageLoader, Context context) {
        this.activities = new ArrayList<>();
        this.imageLoader = imageLoader;
        this.context = context;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int i) {
        return activities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ActivityViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ActivityViewHolder();
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, viewGroup, false);
            viewHolder.activity_image = (ImageView) view.findViewById(R.id.activity_image);
            viewHolder.activity_title = (TextView) view.findViewById(R.id.activity_title);
            viewHolder.activity_start_time = (TextView) view.findViewById(R.id.activity_start_time);
            viewHolder.activity_end_time = (TextView) view.findViewById(R.id.activity_end_time);
            viewHolder.activity_count = (TextView) view.findViewById(R.id.activity_count);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ActivityViewHolder) view.getTag();
        }
        ImageView activity_image = viewHolder.activity_image;
        TextView activity_title = viewHolder.activity_title;
        TextView activity_start_time = viewHolder.activity_start_time;
        TextView activity_end_time = viewHolder.activity_end_time;
        TextView activity_count = viewHolder.activity_count;
        final ShopActivityBean activity = activities.get(i);
        final ArrayList<String> pic_list = activity.getPic_listp();
        activity_image.setImageResource(R.drawable.default_background);
        if (pic_list.size() != 0) {
            imageLoader.bindBitmap(pic_list.get(0), activity_image);
        }
        activity_title.setText(activity.getTitle());
        activity_start_time.setText(activity.getStartTime());
        activity_end_time.setText(activity.getEndTime());
        activity_count.setText(String.valueOf(activity.getCount()));
        return view;
    }

    public Context getContext() {
        return context;
    }

    public void add(ShopActivityBean shopActivityBean){
        activities.add(shopActivityBean);
    }
    private static class ActivityViewHolder {
        ImageView activity_image;
        TextView activity_title;
        TextView activity_start_time;
        TextView activity_end_time;
        TextView activity_count;
    }
}
