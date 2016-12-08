package la.neu.leqi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import la.neu.leqi.R;
import la.neu.leqi.bean.Share;
import la.neu.leqi.tools.image.ImageLoader;


/**
 * @author hxs
 */

public class ShareListAdapter extends RecyclerView.Adapter<ShareListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Share> shares;
    private ImageLoader imageLoader;

    public ShareListAdapter(Context context, ImageLoader imageLoader) {
        this.shares = new ArrayList<>();
        this.context = context;
        this.imageLoader = imageLoader;
    }

    public ShareListAdapter(Context context, ArrayList<Share> shares, ImageLoader imageLoader) {
        this.context = context;
        this.shares = shares;
        this.imageLoader = imageLoader;
    }

    public void setData(ArrayList<Share> shares) {
        this.shares = shares;
        notifyDataSetChanged();
    }

    public void addData(Share share){
        shares.add(share);
        notifyItemInserted(shares.size()-1);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_share, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(shares.get(position).getTheme());
        holder.im.setImageResource(R.drawable.default_background);
        if (shares.get(position).getPic_list().size()!=0) {
            imageLoader.bindBitmap(shares.get(position).getPic_list().get(0), holder.im);
        }
    }

    @Override
    public int getItemCount() {
        return shares.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView im;

        MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            im =  (ImageView) view.findViewById(R.id.id_image);
        }
    }

}
