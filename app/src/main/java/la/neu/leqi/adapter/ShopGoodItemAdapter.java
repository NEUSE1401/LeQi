package la.neu.leqi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.Good;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/6.
 */

public class ShopGoodItemAdapter  extends BaseAdapter{
    private ArrayList<Good> allGoods;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ShopGoodItemAdapter(LayoutInflater inflater,ImageLoader imageLoader){
        this.inflater=inflater;
        this.imageLoader=imageLoader;
        allGoods=new ArrayList<>();
    }
    @Override
    public int getCount() {
        return allGoods.size()/2;
    }

    @Override
    public Object getItem(int i) {
        return allGoods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BicycleViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.bicycle_item, viewGroup, false);
            viewHolder = new BicycleViewHolder();
            viewHolder.image_left = (ImageView) view.findViewById(R.id.image_left);
            viewHolder.title_left = (TextView) view.findViewById(R.id.title_left);
            viewHolder.original_price_left = (TextView) view.findViewById(R.id.original_price_left);
            viewHolder.current_price_left = (TextView) view.findViewById(R.id.current_price_left);
            viewHolder.image_right = (ImageView) view.findViewById(R.id.image_right);
            viewHolder.title_right = (TextView) view.findViewById(R.id.title_right);
            viewHolder.original_price_right = (TextView) view.findViewById(R.id.original_price_right);
            viewHolder.current_price_right = (TextView) view.findViewById(R.id.current_price_right);
            view.setTag(viewHolder);
        }else{
            viewHolder = (BicycleViewHolder)view.getTag();
        }
        ImageView image_left = viewHolder.image_left;
        TextView title_left = viewHolder.title_left;
        TextView original_price_left = viewHolder.original_price_left;
        TextView current_price_left = viewHolder.current_price_left;
        ImageView image_right = viewHolder.image_right;
        TextView title_right = viewHolder.title_right;
        TextView original_price_right = viewHolder.original_price_right;
        TextView current_price_right = viewHolder.current_price_right;
        Good good_left = allGoods.get(2 * i);
        Good good_right = allGoods.get(2 * i + 1);
        title_left.setText(good_left.getName());
        original_price_left.setText(String.valueOf("￥" + good_left.getOriginal_price()));
        current_price_left.setText(String.valueOf("￥" + good_left.getCurrent_price()));
        image_left.setImageResource(R.drawable.default_background);
        if (good_left.getPic_list().size() != 0) {
            imageLoader.bindBitmap(good_left.getPic_list().get(0), image_left);
        }
        title_right.setText(good_right.getName());
        original_price_right.setText(String.valueOf("￥" + good_right.getOriginal_price()));
        current_price_right.setText(String.valueOf("￥" + good_right.getCurrent_price()));
        image_right.setImageResource(R.drawable.default_background);
        if (good_right.getPic_list().size() != 0) {
            imageLoader.bindBitmap(good_right.getPic_list().get(0), image_right);
        }
        return view;
    }

    private  class BicycleViewHolder {
        ImageView image_left;
        TextView title_left;
        TextView original_price_left;
        TextView current_price_left;
        ImageView image_right;
        TextView title_right;
        TextView original_price_right;
        TextView current_price_right;
    }

    //添加商品
    public void addGood(Good good) {
        allGoods.add(good);
    }
}
