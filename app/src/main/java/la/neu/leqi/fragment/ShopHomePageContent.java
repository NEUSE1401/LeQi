package la.neu.leqi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.listener.AdViewListener;
import la.neu.leqi.tools.image.ImageLoader;


/**
 *
 * Created by lenovo on 2016/11/2.
 */

public class ShopHomePageContent extends Fragment {

    private TextView shopName;
    private TextView address;
    private TextView owner;
    private TextView description;
    private TextView contact;
    private CarouselView carouselView;
    private ImageLoader imageLoader;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_home_page_content, null);
        carouselView = (CarouselView) view.findViewById(R.id.shop_show_pic);

        Bundle bundle=this.getArguments();
        BicycleShop shop= (BicycleShop) bundle.getSerializable("shop");
        final ArrayList<String> viewList = shop.getShopPics();
        imageLoader = new ImageLoader(container.getContext());
        final AdViewListener adViewListener = new AdViewListener(viewList,imageLoader,carouselView);
        carouselView.setPageCount(viewList.size());
        carouselView.setImageListener(adViewListener);
        carouselView.addOnPageChangeListener(adViewListener);
        shopName= (TextView) view.findViewById(R.id.shop_shop_name);
        address= (TextView) view.findViewById(R.id.shop_shop_address);
        owner= (TextView) view.findViewById(R.id.shop_shop_owner);
        description= (TextView) view.findViewById(R.id.shop_shop_description);
        contact= (TextView) view.findViewById(R.id.shop_shop_contact);

        shopName.setText(shop.getShopName());
        address.setText(shop.getAddress());
        owner.setText(shop.getOwnerName());
        description.setText(shop.getDeccription());
        contact.setText(shop.getTele());
        return view;
    }
}
