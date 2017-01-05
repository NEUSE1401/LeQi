package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import la.neu.leqi.adapter.BicycleShopListViewItemAdapter;
import la.neu.leqi.bean.BicycleShop;
import la.neu.leqi.tools.HttpGet;

/**
 * @author HeXunshi
 *         车铺列表更新接口
 */

public class BicycleShopListRefreshWebThread extends AsyncTask<Void, Void, ArrayList<BicycleShop>> {
    private String BASE_URL;
    private PullToRefreshBase<ListView> pullToRefreshView;
    private BicycleShopListViewItemAdapter bicycleShopListViewItemAdapter;
    private Context context;

    public BicycleShopListRefreshWebThread(String base_url,PullToRefreshBase<ListView> paramPullToRefreshListView, BicycleShopListViewItemAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.pullToRefreshView = paramPullToRefreshListView;
        this.bicycleShopListViewItemAdapter = paramMyAdapter;
        this.context = paramContext;
    }
    @Override
    protected ArrayList<BicycleShop> doInBackground(Void... voids) {
        ArrayList<BicycleShop> shops=new ArrayList<>();
        try {
            BASE_URL="http://huyumi.cn/leqi/mobileForAllShops.action";
            final String result = HttpGet.send(BASE_URL);
            final JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final ArrayList<String> pics = new ArrayList<>();
                final JSONArray picsAl = jsonObject.getJSONArray("pics");
                for (int j = 0; j < picsAl.length(); j++) {
                    pics.add("http://huyumi.cn/leqi/" + picsAl.getJSONObject(j).getString("path"));
                }
                BicycleShop shop=new BicycleShop();
                shop.setShopPics(pics);
                shop.setAddress(jsonObject.getString("province")+jsonObject.getString("city")+jsonObject.getString("district")+
                        jsonObject.getString("detail"));
                shop.setShopName(jsonObject.getString("shopName"));
                shop.setTele(jsonObject.getString("tel"));
                shop.setDeccription(jsonObject.getString("description"));
                shop.setShopId(jsonObject.getInt("shopId"));
                shop.setOwnerName(jsonObject.getString("ownerName"));

                shops.add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shops;
    }

    @Override
    protected void onPostExecute(ArrayList<BicycleShop> bicycleShops) {
        pullToRefreshView.onRefreshComplete();
         bicycleShopListViewItemAdapter.setData(bicycleShops);
        super.onPostExecute(bicycleShops);
    }
}
