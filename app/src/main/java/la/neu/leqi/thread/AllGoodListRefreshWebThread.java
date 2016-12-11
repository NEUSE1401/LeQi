package la.neu.leqi.thread;

import android.content.Context;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import la.neu.leqi.adapter.ShopGoodItemAdapter;
import la.neu.leqi.bean.Good;
import la.neu.leqi.tools.HttpGet;

/**
 * @author hxs
 */

public class AllGoodListRefreshWebThread extends AsyncTask<Void,Void,ArrayList<Good>>{
    private String BASE_URL;
    private ShopGoodItemAdapter shopGoodItemAdapter;
    private Context context;

    public AllGoodListRefreshWebThread(String base_url, ShopGoodItemAdapter paramMyAdapter, Context paramContext) {
        BASE_URL = base_url;
        this.shopGoodItemAdapter = paramMyAdapter;
        this.context = paramContext;
    }
    @Override
    protected ArrayList<Good> doInBackground(Void... voids) {
        final ArrayList<Good> goods = new ArrayList<>();
        try {
            final String result = HttpGet.send(BASE_URL+"good_list.php");
            final JSONArray jsonArray = new JSONArray(result);
            for (int i = 0;i<jsonArray.length();i++){
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    final ArrayList<String> pics = new ArrayList<>();
                    final JSONArray picsAl = jsonObject.getJSONArray("pics");
                    for (int j = 0; j < picsAl.length(); j++) {
                        pics.add(BASE_URL+picsAl.getString(j));
                    }
                    final Good good = new Good(jsonObject.getInt("good_id"), jsonObject.getString("name"),
                            jsonObject.getString("brand"), jsonObject.getDouble("original_price"),
                            jsonObject.getDouble("current_price"), jsonObject.getString("description"),
                            false, jsonObject.getString("type_id"),
                            jsonObject.getInt("hit_count"), jsonObject.getString("on_sale_time"), pics);
                    goods.add(good);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    protected void onPostExecute(ArrayList<Good> goods) {
        shopGoodItemAdapter.setData(goods);
        super.onPostExecute(goods);
    }
}
