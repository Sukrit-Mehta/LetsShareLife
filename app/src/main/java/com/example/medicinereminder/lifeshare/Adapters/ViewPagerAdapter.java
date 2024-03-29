package com.example.medicinereminder.lifeshare.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.medicinereminder.lifeshare.Fragments.FirstFragment;
import com.example.medicinereminder.lifeshare.Fragments.SecondFragment;
import com.example.medicinereminder.lifeshare.Fragments.ThirdFragment;

/**
 * Created by sukrit on 20/2/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                FirstFragment firstFragment = new FirstFragment();
                return firstFragment;
            case 1:
                SecondFragment secondFragment = new SecondFragment();
                return secondFragment;
            case 2:
                ThirdFragment thirdFragment = new ThirdFragment();
                return thirdFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Required";
            case 1:
                return "Hospitals";
            case 2:
                return "Users";
            default:
                return null;
        }
    }
}
