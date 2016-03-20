package nishanth.com.billing.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nishanth.com.billing.R;

/**
 * Created by Nishanth on 3/5/2016.
 */
public class AddModifyItemPager extends android.support.v4.app.Fragment {

    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_modify_pager,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }

    private void setViewPager(ViewPager viewPager)
    {
        nishanth.com.billing.Adapters.PagerAdapter pagerAdapter = new nishanth.com.billing.Adapters.PagerAdapter(getChildFragmentManager());
        pagerAdapter.addFrag(new AddModifyCommSide() ,"common");
        pagerAdapter.addFrag(new AddModifyStaffSide() ,"Staff");
        viewPager.setAdapter(pagerAdapter);

    }
}
