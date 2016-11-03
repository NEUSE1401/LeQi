package la.neu.leqi.shopfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/2.
 */

public class ShopHomePageContent extends Fragment {

    private TextView shopName;
    private TextView address;
    private TextView owner;
    private TextView description;
    private TextView contact;
    private ImageLoader imageLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_home_page_content, null);

        final CarouselView carouselView = (CarouselView) view.findViewById(R.id.shop_show_pic);
        final ArrayList<String> viewList = new ArrayList<>();
        viewList.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        viewList.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        final AdViewListener adViewListener = new AdViewListener(viewList, view.getContext(), carouselView);
        carouselView.setPageCount(3);
        carouselView.setImageListener(adViewListener);
        carouselView.addOnPageChangeListener(adViewListener);

        shopName= (TextView) view.findViewById(R.id.shop_shop_name);
        address= (TextView) view.findViewById(R.id.shop_shop_address);
        owner= (TextView) view.findViewById(R.id.shop_shop_owner);
        description= (TextView) view.findViewById(R.id.shop_shop_description);
        contact= (TextView) view.findViewById(R.id.shop_shop_contact);

        return view;
    }
}
