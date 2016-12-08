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
import la.neu.leqi.bean.Share;
import la.neu.leqi.bean.ActivityBean;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * @author HeXunshi
 *         主界面适配器
 */

public class MainActivityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Good> goods;
    private ArrayList<ActivityBean> activities;
    private ArrayList<Share> shares;
    private ImageLoader imageLoader;

    //储存Head对象
    private View head_view;

    public MainActivityAdapter(ImageLoader imageLoader, Context context) {
        this.imageLoader = imageLoader;
        this.context = context;
        goods = new ArrayList<>();
        activities = new ArrayList<>();
        shares = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return goods.size() / 2 + activities.size() + shares.size() / 2 + 1 + 3;
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

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
        notifyDataSetChanged();
    }

    public void setShares(ArrayList<Share> shares) {
        this.shares = shares;
        notifyDataSetChanged();
    }

    public void setActivities(ArrayList<ActivityBean> activities) {
        this.activities = activities;
        notifyDataSetChanged();
    }

    //添加商品
    public void addGood(Good good) {
        goods.add(good);
    }

    //添加活动
    public void addActivity(ActivityBean activity) {
        activities.add(activity);
    }

    //添加分享
    public void addShard(Share share) {
        shares.add(share);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0) {
            return getHeadView(view, viewGroup);
        } else if (i == 1) {
            return getTitle("热门商品", view, viewGroup);
        } else if (i <= goods.size() / 2 + 1) {
            return getBicycleView(i, view, viewGroup);
        } else if (i == goods.size() / 2 + 2) {
            return getTitle("活动展示", view, viewGroup);
        } else if (i <= goods.size() / 2 + activities.size() + 2) {
            return getActivityView(i, view, viewGroup);
        } else if (i == goods.size() / 2 + activities.size() + 3) {
            return getTitle("行程分享", view, viewGroup);
        } else if (i <= goods.size() / 2 + activities.size() + shares.size() / 2 + 3) {
            return getShareView(i, view, viewGroup);
        }
        return view;
    }

    private View getTitle(String title, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_title_item, viewGroup, false);
        TextView title_view = (TextView) view.findViewById(R.id.title);
        title_view.setText(title);
        return view;
    }

    //加载首页头部
    private View getHeadView(View view, ViewGroup viewGroup) {
        if (head_view == null) {
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
            activity_pic.setImageResource(R.drawable.default_background);
            imageLoader.bindBitmap("http://neu.la/leqi/img/slider/Homeslider4.jpg", activity_pic);
            head_view = view;
        }
        return head_view;
    }

    //加载商品列表
    private View getBicycleView(int i, View view, ViewGroup viewGroup) {
        i = i - 1 - 1;
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
                viewHolder = (BicycleViewHolder) tag;
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

    //加载活动列表
    private View getActivityView(int i, View view, ViewGroup viewGroup) {
        i = i - goods.size() / 2 - 1 - 2;
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
            final Object tag = view.getTag();
            if (tag != null && tag instanceof ActivityViewHolder) {
                viewHolder = (ActivityViewHolder) tag;
            } else {
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

    //加载分享列表
    private View getShareView(int i, View view, ViewGroup viewGroup) {
        i = i - goods.size() / 2 - activities.size() - 1 - 3;
        ShareViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ShareViewHolder();
            view = LayoutInflater.from(getContext()).inflate(R.layout.share_item, viewGroup, false);
            viewHolder.image_left = (ImageView) view.findViewById(R.id.image_left);
            viewHolder.title_left = (TextView) view.findViewById(R.id.title_left);
            viewHolder.title_right = (TextView) view.findViewById(R.id.title_right);
            viewHolder.image_right = (ImageView) view.findViewById(R.id.image_right);
            view.setTag(viewHolder);
        } else {
            final Object tag = view.getTag();
            if (tag != null && tag instanceof ShareViewHolder) {
                viewHolder = (ShareViewHolder) tag;
            } else {
                viewHolder = new ShareViewHolder();
                view = LayoutInflater.from(getContext()).inflate(R.layout.share_item, viewGroup, false);
                viewHolder.image_left = (ImageView) view.findViewById(R.id.image_left);
                viewHolder.title_left = (TextView) view.findViewById(R.id.title_left);
                viewHolder.title_right = (TextView) view.findViewById(R.id.title_right);
                viewHolder.image_right = (ImageView) view.findViewById(R.id.image_right);
                view.setTag(viewHolder);
            }
        }
        TextView title_left = viewHolder.title_left;
        ImageView image_left = viewHolder.image_left;
        TextView title_right = viewHolder.title_right;
        ImageView image_right = viewHolder.image_right;
        final Share share_left = shares.get(2 * i);
        final Share share_right = shares.get(2 * i + 1);
        final ArrayList<String> pic_list_left = share_left.getPic_list();
        final ArrayList<String> pic_list_right = share_right.getPic_list();
        image_left.setImageResource(R.drawable.default_background);
        if (pic_list_left.size() != 0) {
            imageLoader.bindBitmap(pic_list_left.get(0), image_left);
        }
        title_left.setText(share_left.getTheme());
        image_right.setImageResource(R.drawable.default_background);
        if (pic_list_right.size() != 0) {
            imageLoader.bindBitmap(pic_list_right.get(0), image_right);
        }
        title_right.setText(share_right.getTheme());
        return view;
    }

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

    private static class ActivityViewHolder {
        ImageView activity_image;
        TextView activity_title;
        TextView activity_start_time;
        TextView activity_end_time;
        TextView activity_count;
    }

    private static class ShareViewHolder {
        private TextView title_left;
        private ImageView image_left;
        private TextView title_right;
        private ImageView image_right;
    }

}
