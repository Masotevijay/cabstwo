package com.example.cabstwo.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cabstwo.fragment.Hourly_rentalFragment;
import com.example.cabstwo.fragment.LocalFragment;
import com.example.cabstwo.fragment.OneWayFragment;
import com.example.cabstwo.fragment.RoundFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numoftabs;
   public PagerAdapter(FragmentManager fm,int numoftabs)
   {
       super(fm);
       this.numoftabs=numoftabs;
   }
   @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               return new OneWayFragment();
           case 1:
               return new RoundFragment();
           case 2:
               return new LocalFragment();
           case 3:
               return new Hourly_rentalFragment();
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
