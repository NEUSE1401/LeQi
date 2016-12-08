package la.neu.leqi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author Hexunshi
 */

public class CustomFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentsList;
    private ArrayList<String> titleList;

    public CustomFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList,ArrayList<String> titleList) {
        super(fm);
        this.fragmentsList = fragmentsList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
