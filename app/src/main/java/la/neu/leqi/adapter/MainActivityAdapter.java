package la.neu.leqi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.Good;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * @author HeXunshi
 *         自行车适配器
 */

public class MainActivityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Good> goods;
    private ImageLoader imageLoader;

    //储存Head对象
    private View head_view;

    public MainActivityAdapter(ImageLoader imageLoader, Context context) {
        this.imageLoader = imageLoader;
        this.context = context;
        goods = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return goods.size() / 2 + 1;
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

    public void addGood(Good good) {
        goods.add(good);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0) {
            return getHeadView(view, viewGroup);
        } else if (i <= goods.size() / 2) {
            return getBicycleView(i, view, viewGroup);
        }
        return view;
    }

    //加载首页头部
    private View getHeadView(View view, ViewGroup viewGroup) {
        if(head_view==null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_content_head, viewGroup, false);
            view.setTag("head");
            final String tag = (String) view.getTag();
            if (!tag.equals("head")) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_content_head, viewGroup, false);
                view.setTag("head");
            }
            CarouselView carouselView = (CarouselView) view.findViewById(R.id.vPager);
            final ArrayList<String> viewList = new ArrayList<>();
            viewList.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
            viewList.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
            viewList.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
            viewList.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
            viewList.add("http://neu.la/leqi/img/slider/Homeslider5.jpg");
            final AdViewListener adViewListener = new AdViewListener(viewList, imageLoader, carouselView);
            carouselView.setPageCount(5);
            carouselView.setImageListener(adViewListener);
            carouselView.addOnPageChangeListener(adViewListener);
            //乐骑活动显示
            final ImageView activity_pic = (ImageView) view.findViewById(R.id.activity_pic);
            imageLoader.bindBitmap("http://neu.la/leqi/img/slider/Homeslider4.jpg", activity_pic);
            head_view = view;
        }
        return head_view;
    }

    //加载商品列表
    private View getBicycleView(int i, View view, ViewGroup viewGroup) {
        i = i - 1;
        BicycleViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.bicycle_item, viewGroup, false);
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
        } else {
            final Object tag = view.getTag();
            if (tag != null && tag instanceof BicycleViewHolder) {
                viewHolder = (BicycleViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(getContext()).inflate(R.layout.bicycle_item, viewGroup, false);
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
            }
        }
        ImageView image_left = viewHolder.image_left;
        TextView title_left = viewHolder.title_left;
        TextView original_price_left = viewHolder.original_price_left;
        TextView current_price_left = viewHolder.current_price_left;
        ImageView image_right = viewHolder.image_right;
        TextView title_right = viewHolder.title_right;
        TextView original_price_right = viewHolder.original_price_right;
        TextView current_price_right = viewHolder.current_price_right;
        Good good_left = goods.get(2 * i);
        Good good_right = goods.get(2 * i + 1);
        title_left.setText(good_left.getName());
        original_price_left.setText(String.valueOf("￥" + good_left.getOriginal_price()));
        current_price_left.setText(String.valueOf("￥" + good_left.getCurrent_price()));
        image_left.setImageResource(R.drawable.default_background);
        if (good_left.getPic_list().size() == 0) {
            image_left.setTag("");
            image_left.setImageResource(R.drawable.default_background);
        } else {
            imageLoader.bindBitmap(good_left.getPic_list().get(0), image_left);
        }
        title_right.setText(good_right.getName());
        original_price_right.setText(String.valueOf("￥" + good_right.getOriginal_price()));
        current_price_right.setText(String.valueOf("￥" + good_right.getCurrent_price()));
        image_right.setImageResource(R.drawable.default_background);
        if (good_right.getPic_list().size() == 0) {
            imageLoader.bindBitmap("", image_right);
        } else {
            imageLoader.bindBitmap(good_right.getPic_list().get(0), image_right);
        }
        return view;
    }

    //加载
    private static class BicycleViewHolder {
        ImageView image_left;
        TextView title_left;
        TextView original_price_left;
        TextView current_price_left;
        ImageView image_right;
        TextView title_right;
        TextView original_price_right;
        TextView current_price_right;
    }

    private static class ActivityViewHolder{
        ImageView activity_image;
        TextView activity_title;
        TextView activity_start_time;
        TextView activity_end_time;
    }

}
