package la.neu.leqi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/3.
 */

public class ShopActivityContentItemAdapter extends BaseAdapter {

    ArrayList<ActivityBean> shopActivities;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ShopActivityContentItemAdapter(LayoutInflater inflater,ImageLoader imageLoader,ArrayList<ActivityBean> shopActivities){
        this.shopActivities=shopActivities;
        this.inflater=inflater;
        this.imageLoader=imageLoader;
    }
    @Override
    public int getCount() {
        return shopActivities.size();
    }

    @Override
    public Object getItem(int i) {
        return shopActivities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 有一个内部类，包含了这个item布局的所有组件，
     * 如果view为空，则找到所有组件，并setTag
     * 如果不为空，则直接得到，无需再找，可以提高效率
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=inflater.inflate(R.layout.shop_activity_listview_item, viewGroup, false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.shop_activity_content_listview_item_pic);
            viewHolder.textView=(TextView) view.findViewById(R.id.shop_activity_content_listview_item_title);
            view.setTag(viewHolder );
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        ActivityBean shopActivity=shopActivities.get(i);
        ArrayList<String> activityPics=shopActivity.getPic_listp();
        if(activityPics.size()==0){
            viewHolder.imageView.setImageResource(R.drawable.default_background);
        }else {
            imageLoader.bindBitmap(activityPics.get(0),viewHolder.imageView);
        }
        viewHolder.textView.setText(shopActivity.getTitle());

        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
