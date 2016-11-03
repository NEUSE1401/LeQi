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
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/3.
 */

public class BicycleShopListViewItemAdapter extends BaseAdapter {

    private ArrayList<BicycleShop> shops;
    private Context context;
    private ImageLoader imageLoader;

    public BicycleShopListViewItemAdapter(ArrayList<BicycleShop> shops, Context context, ImageLoader imageLoader) {
        this.shops = shops;
        this. context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public Object getItem(int i) {
        return shops.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if(view==null){
//            view=inflater.inflate(R.layout.bicycle_shop_listview_item, viewGroup, false);
            view = LayoutInflater.from(context).inflate(R.layout.bicycle_shop_listview_item, viewGroup, false);
            viewHolder=new ViewHolder();
            viewHolder.pic= (ImageView) view.findViewById(R.id.bicycle_shop_list_item_pic);
            viewHolder.name=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_title);
            viewHolder.level=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_level);
            viewHolder.address=(TextView) view.findViewById(R.id.bicycle_shop_list_item_shop_address);

            view.setTag(viewHolder );
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BicycleShop shop=shops.get(i);
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

    class ViewHolder{
        ImageView pic;
        TextView name;
        TextView level;
        TextView address;
    }
}
