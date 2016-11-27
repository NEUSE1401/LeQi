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
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.bean.Club;
import la.neu.leqi.bean.ShopActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/27.
 */

public class MyCollectAdapter extends BaseAdapter {
    private ArrayList<BicycleShop> collectShops;
    private ArrayList<Club> collectClubs;
    private ArrayList<ShopActivityBean> collectShopActivities;
    private Context context;
    private ImageLoader imageLoader;


    public MyCollectAdapter(ImageLoader imageLoader, Context context){
        collectShops=new ArrayList<>();
        collectClubs=new ArrayList<>();
        collectShopActivities=new ArrayList<>();
        this.imageLoader = imageLoader;
        this.context = context;
    }
    @Override
    public int getCount() {
        return collectClubs.size()+collectShopActivities.size()+collectShops.size()+3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(i==0){
            return getTitle("收藏的店铺",view,viewGroup);
        }else if(i<=collectShops.size()){
            return getShopItme(i,view,viewGroup);
        }else if(i==collectShops.size()+1){
            return getTitle("收藏的活动",view,viewGroup);
        }else if(i<=collectShops.size()+collectShopActivities.size()+1){
            return getShopAcyivityItem(i,view,viewGroup);
        }else if(i==collectShops.size()+collectShopActivities.size()+2){
            return getTitle("收藏的俱乐部",view,viewGroup);
        }
        return null;
    }

    private View getTitle(String title, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_title_item, viewGroup, false);
        TextView title_view = (TextView) view.findViewById(R.id.title);
        title_view.setText(title);
        return view;
    }
    private Context getContext() {
        return context;
    }

    //加载收藏的店铺项
    private View getShopItme(int i, View view, ViewGroup viewGroup){
        i = i - 1;
        ShopViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.bicycle_shop_listview_item, viewGroup, false);
            viewHolder=new ShopViewHolder();
            viewHolder.pic= (ImageView) view.findViewById(R.id.bicycle_shop_list_item_pic);
            viewHolder.name=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_title);
            viewHolder.level=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_level);
            viewHolder.address=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_address);
            view.setTag(viewHolder );
        }else{
            final Object tag = view.getTag();
            if (tag != null && tag instanceof ShopViewHolder) {
                viewHolder = (ShopViewHolder) tag;
            }
            else{
                view = LayoutInflater.from(context).inflate(R.layout.bicycle_shop_listview_item, viewGroup, false);
                viewHolder=new ShopViewHolder();
                viewHolder.pic= (ImageView) view.findViewById(R.id.bicycle_shop_list_item_pic);
                viewHolder.name=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_title);
                viewHolder.level=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_level);
                viewHolder.address=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_address);
                view.setTag(viewHolder );
            }
        }
        final BicycleShop shop=collectShops.get(i);
        viewHolder.name.setText(shop.getShopName());
        viewHolder.address.setText(shop.getAddress());
        viewHolder.level.setText(shop.getLevel()+"");
        ArrayList<String> pics=shop.getShopPics();
        if(pics.size()==0){
            viewHolder.pic.setImageResource(R.drawable.default_background);
        }else {
            imageLoader.bindBitmap(pics.get(0),viewHolder.pic);
        }
        return view;
    }

    //加载收藏活动项
    public View getShopAcyivityItem(int i, View view, ViewGroup viewGroup){
        i=i-collectShops.size()-2;
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
        }else{
            final Object tag = view.getTag();
            if (tag != null && tag instanceof ActivityViewHolder) {
                viewHolder = (ActivityViewHolder) tag;
            }else{
                viewHolder = new ActivityViewHolder();
                view = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, viewGroup, false);
                viewHolder.activity_image = (ImageView) view.findViewById(R.id.activity_image);
                viewHolder.activity_title = (TextView) view.findViewById(R.id.activity_title);
                viewHolder.activity_start_time = (TextView) view.findViewById(R.id.activity_start_time);
                viewHolder.activity_end_time = (TextView) view.findViewById(R.id.activity_end_time);
                viewHolder.activity_count = (TextView) view.findViewById(R.id.activity_count);
                view.setTag(viewHolder);
            }
        }
        final ShopActivityBean activity = collectShopActivities.get(i);
        final ArrayList<String> pic_list = activity.getPic_listp();
        viewHolder.activity_image.setImageResource(R.drawable.default_background);
        if (pic_list.size() != 0) {
            imageLoader.bindBitmap(pic_list.get(0),  viewHolder.activity_image);
        }
        viewHolder.activity_title.setText(activity.getTitle());
        viewHolder.activity_start_time.setText(activity.getStartTime());
        viewHolder.activity_end_time.setText(activity.getEndTime());
        viewHolder.activity_count.setText(String.valueOf(activity.getCount()));

        return view;
    }

    private class ShopViewHolder{
        ImageView pic;
        TextView name;
        TextView level;
        TextView address;
    }
    private  class ActivityViewHolder {
        ImageView activity_image;
        TextView activity_title;
        TextView activity_start_time;
        TextView activity_end_time;
        TextView activity_count;
    }

    public void addShop(BicycleShop shop){
        collectShops.add(shop);
    }
    public void addShopActivity(ShopActivityBean shopActivity){
        collectShopActivities.add(shopActivity);
    }
    public void addClub(Club club){
        collectClubs.add(club);
    }
}
