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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.ConcreteActivityActivity;
import la.neu.leqi.R;
import la.neu.leqi.ShopActivity;
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.bean.Club;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/27.
 */

public class MyCollectAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private ArrayList<BicycleShop> collectShops;
    private ArrayList<Club> collectClubs;
    private ArrayList<ActivityBean> collectShopActivities;
    private Context context;
    private ImageLoader imageLoader;


    public MyCollectAdapter(ImageLoader imageLoader, Context context){
        collectShops=new ArrayList<>();
        collectClubs=new ArrayList<>();
        collectShopActivities=new ArrayList<>();
        this.imageLoader = imageLoader;
        this.context = context;
    }

    public void setCollectShops(ArrayList<BicycleShop> collectShops) {
        this.collectShops = collectShops;
        notifyDataSetChanged();

    }

    public void setCollectClubs(ArrayList<Club> collectClubs) {
        this.collectClubs = collectClubs;
        notifyDataSetChanged();
    }

    public void setCollectShopActivities(ArrayList<ActivityBean> collectShopActivities) {
        this.collectShopActivities = collectShopActivities;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return collectClubs.size()+collectShopActivities.size()+collectShops.size()+3;
    }
    public void remove(int position){

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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else if(position<=collectShops.size()){
            return 1;
        }else if(position==collectShops.size()+1){
            return 0;
        }else if(position<=collectShops.size()+collectShopActivities.size()+1){
            return 1;
        }else if(position==collectShops.size()+collectShopActivities.size()+2){
            return 0;
        }
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("getView"+i);
        if(i==0){
            System.out.println("收藏的店铺");
            return getTitle("收藏的店铺",view,viewGroup);
        }else if(i<=collectShops.size()){
            System.out.println("店铺"+(i-collectShops.size()));
            return getShopItem(i,view,viewGroup);
        }else if(i==collectShops.size()+1){
            System.out.println("收藏的活动");
            return getTitle("收藏的活动",view,viewGroup);
        }else if(i<=collectShops.size()+collectShopActivities.size()+1){
            System.out.println("活动"+(i-(collectShops.size()+collectShopActivities.size()+1)));
            return getShopActivityItem(i,view,viewGroup);
        }else if(i==collectShops.size()+collectShopActivities.size()+2){
            System.out.println("加入的俱乐部");
            return getTitle("加入的俱乐部",view,viewGroup);
        }else{
            System.out.println("俱乐部");
            return getClubItem(i,view,viewGroup);
        }

    }

    private View getTitle(String title, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_title_item, viewGroup, false);
        TextView title_view = (TextView) view.findViewById(R.id.title);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.title_head);
        final ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        title_view.setText(title);
        return view;
    }
    private Context getContext() {
        return context;
    }

    //加载收藏的店铺项
    private View getShopItem(int i, View view, ViewGroup viewGroup){
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
    public View getShopActivityItem(int i, View view, ViewGroup viewGroup){
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
        final ActivityBean activity = collectShopActivities.get(i);
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

    //加载加入的俱乐部
    public View getClubItem(int i,View view,ViewGroup viewGroup){
        i=i-collectShops.size()-collectShopActivities.size()-3;
        ClubHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.club_list_item, viewGroup, false);
            viewHolder=new ClubHolder();
            viewHolder.pic= (ImageView) view.findViewById(R.id.club_list_item_pic);
            viewHolder.name=(TextView) view.findViewById(R.id.club_list_item_title);
            viewHolder.level=(TextView) view.findViewById(R.id.club_list_item_level);
            viewHolder.address=(TextView) view.findViewById(R.id.club_list_item_address);
            view.setTag(viewHolder );
        }else{
            final Object tag = view.getTag();
            if (tag != null && tag instanceof ClubHolder) {
                viewHolder = (ClubHolder) tag;
            }else {
                view = LayoutInflater.from(context).inflate(R.layout.club_list_item, viewGroup, false);
                viewHolder=new ClubHolder();
                viewHolder.pic= (ImageView) view.findViewById(R.id.club_list_item_pic);
                viewHolder.name=(TextView) view.findViewById(R.id.club_list_item_title);
                viewHolder.level=(TextView) view.findViewById(R.id.club_list_item_level);
                viewHolder.address=(TextView) view.findViewById(R.id.club_list_item_address);
                view.setTag(viewHolder );
            }
        }
        final Club club=collectClubs.get(i);
        viewHolder.name.setText(club.getTitle());
        viewHolder.level.setText(club.getLevel()+"");
        viewHolder.address.setText(club.getProvince()+" "+club.getCity()+" "+club.getDistrict());
        ArrayList<String> pics=club.getPics();
        if(pics.size()==0){
            viewHolder.pic.setImageResource(R.drawable.default_background);
        }else {
            imageLoader.bindBitmap(pics.get(0),viewHolder.pic);
        }
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i<=collectShops.size()){
            //shop
            Intent intent=new Intent(context, ShopActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("shop",collectShops.get(i-1));
            intent.putExtras(bundle);
            context.startActivity(intent);

        }else if(i<=collectShops.size()+collectShopActivities.size()+1){
            Intent intent=new Intent(context, ConcreteActivityActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("activity",collectShopActivities.get(i-collectShops.size()-2));
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else if(i>collectShops.size()+collectShopActivities.size()+2){

        }
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
    private class ClubHolder{
        ImageView pic;
        TextView name;
        TextView level;
        TextView address;
    }

    public void addShop(BicycleShop shop){
        collectShops.add(shop);
    }
    public void addShopActivity(ActivityBean shopActivity){
        collectShopActivities.add(shopActivity);
    }
    public void addClub(Club club){
        collectClubs.add(club);
    }
}
