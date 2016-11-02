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
import la.neu.leqi.bean.Good;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * @author HeXunshi
 *         自行车适配器
 */

public class BicycleAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Good> goods;
    private ImageLoader imageLoader;

    public BicycleAdapter(Context context) {
        this.context = context;
        imageLoader = new ImageLoader(context);
        goods = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return goods.size() / 2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Context getContext() {
        return context;
    }

    public void addGood(Good good){
        goods.add(good);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view= LayoutInflater.from(getContext()).inflate(R.layout.bicycle_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.image_left = (ImageView) view.findViewById(R.id.image_left);
            viewHolder.title_left = (TextView) view.findViewById(R.id.title_left);
            viewHolder.original_price_left = (TextView) view.findViewById(R.id.original_price_left);
            viewHolder.current_price_left = (TextView) view.findViewById(R.id.current_price_left);
            viewHolder.image_right = (ImageView) view.findViewById(R.id.image_right);
            viewHolder.title_right = (TextView) view.findViewById(R.id.title_right);
            viewHolder.original_price_right = (TextView) view.findViewById(R.id.original_price_right);
            viewHolder.current_price_right = (TextView) view.findViewById(R.id.current_price_right);
            view.setTag(viewHolder );
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        ImageView image_left=viewHolder.image_left;
        TextView title_left=viewHolder.title_left;
        TextView original_price_left=viewHolder.original_price_left;
        TextView current_price_left=viewHolder.current_price_left;
        ImageView image_right=viewHolder.image_right;
        TextView title_right=viewHolder.title_right;
        TextView original_price_right=viewHolder.original_price_right;
        TextView current_price_right=viewHolder.current_price_right;
        int height = image_left.getHeight();
        int width = image_left.getWidth();
        Good good_left = goods.get(2*i);
        Good good_right = goods.get(2*i+1);
        title_left.setText(good_left.getName());
        original_price_left.setText("￥"+String.valueOf(good_left.getOriginal_price()));
        current_price_left.setText("￥"+String.valueOf(good_left.getCurrent_price()));
        image_left.setTag(good_left.getPic_list().get(0));
        imageLoader.bindBitmap(good_left.getPic_list().get(0),image_left,height,width);
        title_right.setText(good_right.getName());
        original_price_right.setText("￥"+ String.valueOf(good_right.getOriginal_price()));
        current_price_right.setText("￥"+ String.valueOf(good_right.getCurrent_price()));
        imageLoader.bindBitmap(good_right.getPic_list().get(0),image_right,height,width);
        return view;
    }

    private static class ViewHolder {
        public ImageView image_left;
        public TextView title_left;
        public TextView original_price_left;
        public TextView current_price_left;
        public ImageView image_right;
        public TextView title_right;
        public TextView original_price_right;
        public TextView current_price_right;
    }
}
