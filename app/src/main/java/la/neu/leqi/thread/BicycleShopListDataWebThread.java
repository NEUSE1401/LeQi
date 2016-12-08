package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;

import la.neu.leqi.adapter.BicycleShopListViewItemAdapter;
import la.neu.leqi.bean.BicycleShop;

/**
 * @author HeXunshi
 *         车铺列表加载更多接口
 */

public class BicycleShopListDataWebThread extends AsyncTask<Void, Void, ArrayList<BicycleShop>> {
    private String BASE_URL;
    private PullToRefreshBase<ListView> pullToRefreshView;
    private BicycleShopListViewItemAdapter bicycleShopListViewItemAdapter;
    private Context context;

    public BicycleShopListDataWebThread(String base_url,PullToRefreshBase<ListView> paramPullToRefreshListView, BicycleShopListViewItemAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.bicycleShopListViewItemAdapter = paramMyAdapter;
        this.context = paramContext;
    }
    @Override
    protected ArrayList<BicycleShop> doInBackground(Void... voids) {
        //        try {
//            final String result = HttpGet.send(BASE_URL);
//            final JSONObject jsonObject = new JSONObject(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        BicycleShop shop1=new BicycleShop("2b101","槲荷缎花","超值优惠",5,"1878117xxxx",101,"东北大学浑南校区");
        BicycleShop shop2=new BicycleShop("2b102","花花","超值优惠",5,"1878117xxxx",101,"东北大学浑南校区");
        ArrayList<String> pic=new ArrayList<>();
        pic.add("http://neu.la/leqi/img/slider/Homeslider1.jpg");
        shop1.setShopPics(pic);
        ArrayList<BicycleShop> shops=new ArrayList<>();
        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop1);
        shops.add(shop2);
        return shops;
    }

    @Override
    protected void onPostExecute(ArrayList<BicycleShop> bicycleShops) {
        pullToRefreshView.onRefreshComplete();
        for (BicycleShop b : bicycleShops) {
            bicycleShopListViewItemAdapter.add(b);
        }
        super.onPostExecute(bicycleShops);
    }
}
