package la.neu.leqi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import la.neu.leqi.tools.image.ImageLoader;

/**
 * @author HeXunshi
 *         AutoScrollViewPager适配器
 */
public class AutoScrollViewAdapter extends PagerAdapter {
    private ImageLoader imageLoader;
    private List<String> mListViews;
    private Context context;

    public AutoScrollViewAdapter(List<String> mListViews,Context context) {
        this.mListViews = mListViews;
        this.context = context;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.bindBitmap(mListViews.get(position),imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }
}
