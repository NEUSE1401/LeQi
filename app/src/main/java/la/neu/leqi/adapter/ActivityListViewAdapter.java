package la.neu.leqi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.ConcreteActivityActivity;
import la.neu.leqi.ConcreteClubActivity;
import la.neu.leqi.R;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * 活动适配器
 */

public class ActivityListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private ArrayList<ActivityBean> activities;
    private ImageLoader imageLoader;
    private Context context;
    private boolean flag;

    public ActivityListViewAdapter(ImageLoader imageLoader, Context context) {
        this.activities = new ArrayList<>();
        this.imageLoader = imageLoader;
        this.context = context;
        flag = true;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setData(ArrayList<ActivityBean> activities) {
        this.activities = activities;
        notifyDataSetChanged();
    }
    synchronized public void  remove(int position){
        activities.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public ActivityBean getItem(int i) {
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
        final ActivityBean activity = activities.get(i);
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

    public void add(ActivityBean activityBean){
        activities.add(activityBean);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(context, ConcreteActivityActivity.class);
        Bundle bundle=new Bundle();
        if(flag) {
            bundle.putSerializable("activity", activities.get(i - 1));
        }else {
            bundle.putSerializable("activity", activities.get(i));

        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static class ActivityViewHolder {
        ImageView activity_image;
        TextView activity_title;
        TextView activity_start_time;
        TextView activity_end_time;
        TextView activity_count;
    }
}
