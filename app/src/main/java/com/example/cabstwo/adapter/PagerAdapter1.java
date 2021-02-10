package com.example.cabstwo.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cabstwo.fragment.FriFragment;
import com.example.cabstwo.fragment.MonFragment;
import com.example.cabstwo.fragment.SatFragment;
import com.example.cabstwo.fragment.SunFragment;
import com.example.cabstwo.fragment.ThurFragment;
import com.example.cabstwo.fragment.TuesFragment;
import com.example.cabstwo.fragment.WedkFragment;

public class PagerAdapter1 extends FragmentPagerAdapter {
    private int numoftabs1;
    public PagerAdapter1(FragmentManager fm1, int numofTabs1) {
        super(fm1);
        this.numoftabs1=numofTabs1;

    }
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new SunFragment();
            case 1:
                return new MonFragment();
            case 2:
                return new TuesFragment();
            case 3:
                return new WedkFragment();
            case 4:
                return new ThurFragment();
            case 5:
                return new FriFragment();
            case 6:
                return new SatFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return numoftabs1;
    }


}
