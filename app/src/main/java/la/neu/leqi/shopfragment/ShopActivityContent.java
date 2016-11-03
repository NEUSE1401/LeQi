package la.neu.leqi.shopfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.adapter.ShopActivityContentItemAdapter;
import la.neu.leqi.bean.ShopActivityBean;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/11/2.
 */

public class ShopActivityContent extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_activity_content, null);
        ListView listView= (ListView) view.findViewById(R.id.shop_activity_content_listview);

        ImageLoader imageLoader=new ImageLoader(container.getContext());
        ArrayList<ShopActivityBean> shopActivitys=new ArrayList<ShopActivityBean>();
        ArrayList<String> pic=new ArrayList<>();
        pic.add("http://neu.la/leqi/img/slider/Homeslider4.jpg");
        shopActivitys.add(new ShopActivityBean("2B101大促销","花花大甩卖","2016-11-1","2016-12-12","2016-11-1",pic,1));
        shopActivitys.add(new ShopActivityBean("2B103大促销","全部半价","2016-11-1","2016-12-12","2016-11-1",pic,2));
        ShopActivityContentItemAdapter adapter=new ShopActivityContentItemAdapter(inflater,imageLoader,shopActivitys);
        listView.setAdapter(adapter);
        return view;
    }
}
