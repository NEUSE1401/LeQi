package la.neu.leqi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.adapter.ShopGoodItemAdapter;
import la.neu.leqi.bean.Good;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/2.
 */

public class ShopAllGoodContent extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_all_good_content, null);
        ListView list= (ListView) view.findViewById(R.id.shop_all_good_content);
        ImageLoader imageLoader=new ImageLoader(container.getContext());

        final ArrayList<String> pics1 = new ArrayList<>();
        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        Good good1 = new Good(1, "自行车", 19.9, 18.8, pics1);
        final ArrayList<String> pics2 = new ArrayList<>();
        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
        Good good2 = new Good(2, "自行车", 19.9, 18.8, pics2);
        final ArrayList<String> pics3 = new ArrayList<>();
        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
        Good good3 = new Good(3, "自行车", 19.9, 18.8, pics3);
        final ArrayList<String> pics4 = new ArrayList<>();
        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        Good good4 = new Good(4, "自行车", 19.9, 18.8, pics4);

        ShopGoodItemAdapter adapter=new ShopGoodItemAdapter(inflater,imageLoader);
        adapter.addGood(good1);
        adapter.addGood(good2);
        adapter.addGood(good3);
        adapter.addGood(good4);
        adapter.addGood(good1);
        adapter.addGood(good2);
        adapter.addGood(good3);
        adapter.addGood(good4);
        adapter.addGood(good1);



        list.setAdapter(adapter);
        return view;
    }


}
