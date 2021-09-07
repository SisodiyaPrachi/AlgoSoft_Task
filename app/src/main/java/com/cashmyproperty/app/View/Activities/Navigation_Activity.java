package com.cashmyproperty.app.View.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;

import com.cashmyproperty.app.View.Home.HomeFragment_Agent;
import com.cashmyproperty.app.View.Profile.Profile_Fragment;
import com.cashmyproperty.app.View.Repository.DataRepository;
import com.cashmyproperty.app.View.Steps.Step_Second;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.DrawerController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

import me.ibrahimsn.lib.SmoothBottomBar;


public class Navigation_Activity extends AppCompatActivity implements DrawerController {


    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    public static DrawerLayout drawer;
    private NavigationView navigationView;
   /* private BottomNavigationView bottomNavView;*/
   private SmoothBottomBar bottomNavView;
    private CoordinatorLayout contentView;
    Toolbar toolbar;
    TextView nav_name,name,prop_id;
    CircularImageView user_profile,profile;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initToolbar();
        initNavigation();
        setupSmoothBottomMenu();

    }

    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void initNavigation() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavView = findViewById(R.id.bottom_nav_view);
        contentView = findViewById(R.id.content_view);
        name=findViewById(R.id.name);
        profile=findViewById(R.id.profile);
        prop_id=findViewById(R.id.prop_id);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        View navHeaderView=navigationView.getHeaderView(0);
        nav_name=  navHeaderView.findViewById(R.id.nav_name);
        user_profile=navHeaderView.findViewById(R.id.user_profile);


       setData();

        if(toolbar!=null) {

            mAppBarConfiguration = new AppBarConfiguration.Builder(/*navController.getGraph()*/
                R.id.nav_home, R.id.nav_search, R.id.nav_category,
                R.id.nav_profile, R.id.side_home,
                R.id.side_profile)
                    .setDrawerLayout(drawer)
                    .build();
            NavigationUI.setupWithNavController(toolbar, navController, mAppBarConfiguration);

            NavigationUI.setupWithNavController(navigationView, navController);
           // NavigationUI.setupWithNavController(bottomNavView, navController);
        }
        else{
            drawer.setDrawerListener(null);
        }

        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.profile);

            }
        });


    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        else {
            super.onBackPressed();
            return;
        }

    }

    private void setupSmoothBottomMenu() {

        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.bottom_nav_menu);
        Menu menu = popupMenu.getMenu();
        bottomNavView.setupWithNavController(menu, navController);
    }


    @Override
    public void setDrawer_Locked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void setDrawer_UnLocked() {
      drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
      toolbar.setVisibility(View.VISIBLE);
        setData();
    }

    private void setData() {
        nav_name.setText(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserName));
        if( PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage).equals("")){
            //profile.setBackgroundResource(R.drawable.profile_user_icon);
            Glide.with(getApplicationContext()).load(R.drawable.user).into(user_profile);
        }
        else {
            Glide.with(getApplicationContext()).load(image_base_url + PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage)).into(user_profile);
        }

        name.setText(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserName));
        prop_id.setText("Buyer ID # "+PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.USER_SEQ_ID));
        if( PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage).equals("")){
            //profile.setBackgroundResource(R.drawable.profile_user_icon);
            Glide.with(getApplicationContext()).load(R.drawable.user).into(profile);
        }
        else {
            Glide.with(getApplicationContext()).load(image_base_url + PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage)).into(profile);
        }
    }

    @Override
    public void setbottom_Locked() {

        bottomNavView.setVisibility(View.GONE);
    }

    @Override
    public void setbottom_UnLocked() {
        bottomNavView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initNavigation();
       // Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initNavigation();
        //Toast.makeText(getApplicationContext(), "resue", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initNavigation();
        //Toast.makeText(getApplicationContext(), "restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        initNavigation();
        //Toast.makeText(getApplicationContext(),"pause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
       // Toast.makeText(getApplicationContext(),"stop",Toast.LENGTH_LONG).show();
    }

}