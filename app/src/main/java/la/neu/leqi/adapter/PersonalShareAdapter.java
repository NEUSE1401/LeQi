package la.neu.leqi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.Share;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/12/9.
 */

public class PersonalShareAdapter extends BaseAdapter {
    private Context context;
    private ImageLoader imageLoader;
    private ArrayList<Share> shares;

    public PersonalShareAdapter(Context context, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
        shares=new ArrayList<>();
    }

    @Override
    public int getCount() {
        if((shares.size()%2)==1){
            return shares.size()/2+1;
        }
        return shares.size()/2;
    }

    @Override
    public Object getItem(int i) {
        return shares.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ShareViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ShareViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.share_item, viewGroup, false);
            viewHolder.image_left = (ImageView) view.findViewById(R.id.image_left);
            viewHolder.title_left = (TextView) view.findViewById(R.id.title_left);
            viewHolder.title_right = (TextView) view.findViewById(R.id.title_right);
            viewHolder.image_right = (ImageView) view.findViewById(R.id.image_right);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ShareViewHolder) view.getTag();
        }
        TextView title_left = viewHolder.title_left;
        ImageView image_left = viewHolder.image_left;
        TextView title_right = viewHolder.title_right;
        ImageView image_right = viewHolder.image_right;
        final Share share_left = shares.get(2 * i);
        final ArrayList<String> pic_list_left = share_left.getPic_list();
        image_left.setImageResource(R.drawable.default_background);
        if (pic_list_left.size() != 0) {
            imageLoader.bindBitmap(pic_list_left.get(0), image_left);
        }
        title_left.setText(share_left.getTheme());

        image_right.setImageResource(R.drawable.default_background);

        if((2 * i + 1)<shares.size()){
            final Share share_right = shares.get(2 * i + 1);
            final ArrayList<String> pic_list_right = share_right.getPic_list();
            if (pic_list_right.size() != 0) {
                imageLoader.bindBitmap(pic_list_right.get(0), image_right);
            }
            title_right.setText(share_right.getTheme());
        }else{
            image_right.setVisibility(LinearLayout.GONE);
            title_right.setVisibility(LinearLayout.GONE);
        }

        return view;
    }

    public void setData(ArrayList<Share> data) {
        this.shares = data;
        notifyDataSetChanged();
    }

    private static class ShareViewHolder {
        private TextView title_left;
        private ImageView image_left;
        private TextView title_right;
        private ImageView image_right;
    }

    public void add(Share share){
        shares.add(share);
    }
}
