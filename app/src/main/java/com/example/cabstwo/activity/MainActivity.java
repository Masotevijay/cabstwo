package com.example.cabstwo.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.androidnetworking.AndroidNetworking;
import com.example.cabstwo.R;
import com.example.cabstwo.activity.PagerAdapter1;
import com.example.cabstwo.adapter.PagerAdapter;
import com.example.cabstwo.fragment.OneWayFragment;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper;
    androidx.appcompat.widget.Toolbar toolbar;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize r2xandroid
        AndroidNetworking.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        Places.initialize(getApplicationContext(), getString(R.string.mapp_pi));



        toolbar = findViewById(R.id.tool);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        viewFlipper = findViewById(R.id.pager);

        int array_image_product[] = {R.drawable.image_11, R.drawable.image_12, R.drawable.image_13, R.drawable.image_14, R.drawable.image_15,};

        for (int i = 0; i < array_image_product.length; i++) {
            showimage(array_image_product[i]);
        }
    //........TabLayout1..........
        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabItem tabOneway = findViewById(R.id.oneway);
        TabItem tabRound = findViewById(R.id.round);
        TabItem tabLocal = findViewById(R.id.local);
        TabItem tabHourly = findViewById(R.id.hour_rental);

        ViewPager viewPager = findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
      //....TabLayout2..............
        TabLayout tabLayout2 = findViewById(R.id.tablayout2);
        TabItem tabSun = findViewById(R.id.tab1);
        TabItem tabMon = findViewById(R.id.tab2);
        TabItem tabTues = findViewById(R.id.tab3);
        TabItem tabWed = findViewById(R.id.tab4);
        TabItem tabThurs= findViewById(R.id.tab5);
        TabItem tabFri=findViewById(R.id.tab6);
        TabItem tabSat=findViewById(R.id.tab7);
        ViewPager viewPager1=findViewById(R.id.viewpager2);
       PagerAdapter1 pagerAdapter1= new PagerAdapter1(getSupportFragmentManager(),tabLayout2.getTabCount());
       viewPager1.setAdapter(pagerAdapter1);
       tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               viewPager1.setCurrentItem(tab.getPosition());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
//.............Navigation drawer....................
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Drawer Simple Dark");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_book_taxi:
                        Toast.makeText(getApplicationContext(), "Home Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_orders:
                        Toast.makeText(getApplicationContext(), "Category Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_notifications:
                        Toast.makeText(getApplicationContext(), "Trip Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_aboutus:
                        Toast.makeText(getApplicationContext(), "Login Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_faq:
                        Toast.makeText(getApplicationContext(), "LogOut Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_rateus:
                        Toast.makeText(getApplicationContext(), "About Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_shareapp:
                        Toast.makeText(getApplicationContext(), "About Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(getApplicationContext(), "About Fragment", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        actionBar.setTitle(menuItem.getTitle());
                        break;
                }
                return false;
            }
        });


    }
//..........ViewFlipper..........
    private void showimage(int img) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setBackgroundResource(img);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {

            //super.onBackPressed();
           finish();
      //  }
    }

}