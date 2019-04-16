package beyou.school.ADAPTERS;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    ArrayList<Fragment> fragmentlist=new ArrayList<>();
    ArrayList<String> fragmenttitle =new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmenttitle.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenttitle.get(position);
    }

    public void AddFragmnet(Fragment manager, String name)
    {
        fragmentlist.add(manager);
        fragmenttitle.add(name);

    }
}
