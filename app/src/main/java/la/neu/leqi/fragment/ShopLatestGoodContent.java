package la.neu.leqi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import la.neu.leqi.R;
import la.neu.leqi.adapter.ShopGoodItemAdapter;
import la.neu.leqi.thread.AllGoodListRefreshWebThread;
import la.neu.leqi.thread.LatestGoodRefreshWebThread;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/2.
 */

public class ShopLatestGoodContent extends Fragment {
    private boolean isCreate = false;
    private ShopGoodItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isCreate = true;
        View view = inflater.inflate(R.layout.shop_latest_good_content, null);
        ListView listView = (ListView) view.findViewById(R.id.shop_latest_good_content_list);
        ImageLoader imageLoader = new ImageLoader(container.getContext());

//        final ArrayList<String> pics1 = new ArrayList<>();
//        pics1.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
//        Good good1 = new Good(1,  "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null,  pics1);
//        final ArrayList<String> pics2 = new ArrayList<>();
//        pics2.add("http://neu.la/leqi/img/slider/Homeslider2.jpg");
//        Good good2 = new Good(2, "自行车",  "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null, pics2);
//        final ArrayList<String> pics3 = new ArrayList<>();
//        pics3.add("http://neu.la/leqi/img/slider/Homeslider3.jpg");
//        Good good3 = new Good(3,  "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null,  pics3);
//        final ArrayList<String> pics4 = new ArrayList<>();
//        pics4.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
//        Good good4 = new Good(4,  "自行车", "XXpinp",19.9, 18.8,"拥有最新设计，S级液压减震",false,null,0,null,  pics4);


        adapter = new ShopGoodItemAdapter(inflater, imageLoader);
//        adapter.addGood(good4);
//        adapter.addGood(good3);
//        adapter.addGood(good2);

        listView.setAdapter(adapter);
        new LatestGoodRefreshWebThread(getString(R.string.WEB_BASE), adapter, getContext()).execute();

        //listView.setOnItemClickListener(adapter);
        return view;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser && isCreate) {
            new AllGoodListRefreshWebThread(getString(R.string.WEB_BASE), adapter, getContext()).execute();
        }
    }
}
