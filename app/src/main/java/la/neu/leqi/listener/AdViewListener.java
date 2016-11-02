package la.neu.leqi.listener;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import la.neu.leqi.R;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * @author HeXunshi
 * 广告栏滚动事件监听器
 */
public class AdViewListener implements ImageListener, ViewPager.OnPageChangeListener {
    private ArrayList<String> urlList;
    private Map<Integer, ImageView> imageViews;
    private Context context;
    private ImageLoader imageLoader;
    private CarouselView carouselView;

    public AdViewListener(Context context, CarouselView carouselView) {
        this.urlList = new ArrayList<>();
        this.context = context;
        this.carouselView = carouselView;
        imageLoader = new ImageLoader(context);
    }

    public AdViewListener(ArrayList<String> urlList, Context context, CarouselView carouselView) {
        this.urlList = urlList;
        this.imageViews = new HashMap<>();
        this.context = context;
        this.carouselView = carouselView;
        imageLoader = new ImageLoader(context);
    }

    public void addUrl(String url){
        urlList.add(url);
        carouselView.setPageCount(urlList.size());
    }
    @Override
    public void setImageForPosition(int position, ImageView imageView) {
        System.out.println("setImageForPosition"+position);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(R.drawable.default_background);
        if(!imageViews.containsKey(position)) {
            System.out.println("put"+position);
            imageViews.put(position, imageView);
        }
        imageLoader.bindBitmap(urlList.get(position), imageView);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        System.out.println("onPageScrolled"+position);
    }

    /**
     * 监听广告滚动事件，并且更新页面
     *
     * @param position 当前位置
     */
    @Override
    public void onPageSelected(int position) {
        final ImageView imageView = imageViews.get(position);
        setImageForPosition(position, imageView);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        System.out.println("onPageScrollStateChanged"+state);
    }
}
