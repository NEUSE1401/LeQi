package la.neu.leqi.listener;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import la.neu.leqi.ConcreteGoodActivity;
import la.neu.leqi.LogAndRegisterActivity;
import la.neu.leqi.MyCollectActivity;
import la.neu.leqi.PersonalInformationActivity;
import la.neu.leqi.R;

/**
 * Created by lenovo on 2016/11/2.
 */

public class MenuClickListener  implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private DrawerLayout drawerLayout;
    public MenuClickListener(Context context,DrawerLayout drawerLayout){
        this.context=context;
        this.drawerLayout=drawerLayout;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        drawerLayout.closeDrawer(Gravity.LEFT);
        if(id== R.id.personal_information){
            Intent intent=new Intent(context, PersonalInformationActivity.class);
            context.startActivity(intent);

        }else if(id==R.id.personal_activity){

        }else if(id==R.id.personal_collect){
            Intent intent=new Intent(context, MyCollectActivity.class);
            context.startActivity(intent);
        }else if(id==R.id.personal_share){

        }else if(id==R.id.setting){
            Intent intent=new Intent(context, ConcreteGoodActivity.class);
            context.startActivity(intent);
        }else if(id==R.id.log_out){
            Intent intent=new Intent(context, LogAndRegisterActivity.class);
            context.startActivity(intent);
        }

        return false;
    }
}
