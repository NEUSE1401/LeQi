package la.neu.leqi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import la.neu.leqi.ConcreteClubActivity;
import la.neu.leqi.R;
import la.neu.leqi.bean.Club;
import la.neu.leqi.tools.image.ImageLoader;

/**
 * Created by lenovo on 2016/12/2.
 */

public class ClubListItemAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private ArrayList<Club> clubs;
    private Context context;
    private ImageLoader imageLoader;

    public ClubListItemAdapter(ArrayList<Club> clubs, Context context, ImageLoader imageLoader) {
        this.clubs = clubs;
        this.context = context;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return clubs.size();
    }

    @Override
    public Object getItem(int i) {
        return clubs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.club_list_item, viewGroup, false);
            viewHolder=new ViewHolder();
            viewHolder.pic= (ImageView) view.findViewById(R.id.club_list_item_pic);
            viewHolder.name=(TextView) view.findViewById(R.id.club_list_item_title);
            viewHolder.level=(TextView) view.findViewById(R.id.club_list_item_level);
            viewHolder.address=(TextView) view.findViewById(R.id.club_list_item_address);
            view.setTag(viewHolder );
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
       final Club club=clubs.get(i);
        viewHolder.name.setText(club.getTitle());
        viewHolder.level.setText(club.getLevel()+"");
        viewHolder.address.setText(club.getProvince()+" "+club.getCity()+" "+club.getDistrict());
        ArrayList<String> pics=club.getPics();
        if(pics.size()==0){
            viewHolder.pic.setImageResource(R.drawable.default_background);
        }else {
            imageLoader.bindBitmap(pics.get(0),viewHolder.pic);
        }

        return view;
    }

    public void add(Club c){
        clubs.add(c);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(context, ConcreteClubActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("club",clubs.get(i));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private class ViewHolder{
        ImageView pic;
        TextView name;
        TextView level;
        TextView address;
    }
}
