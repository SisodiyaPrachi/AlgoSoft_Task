package com.cashmyproperty.app.View.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.DrawerController;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

import me.ibrahimsn.lib.SmoothBottomBar;


public class Navigation_Seller extends AppCompatActivity implements DrawerController {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    public static DrawerLayout drawer;
    private NavigationView navigationView;
   BottomAppBar bottomAppBar;
    SmoothBottomBar bottomNavView;
    ImageView fab;
    /*private BottomNavigationView bottomNavView;*/
    private CoordinatorLayout contentView;
    Toolbar toolbar;
    TextView nav_name,name,propid;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";
    CircularImageView user_profile,profile_seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_seller);


        initToolbar();
        initNavigation();
        setupSmoothBottomMenu();

    }

    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private void initNavigation() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavView = findViewById(R.id.bottom_nav_view);
        bottomAppBar=findViewById(R.id.bottomAppBar);
        contentView = findViewById(R.id.content_view);
        name=findViewById(R.id.name);
        propid=findViewById(R.id.propid);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        View navHeaderView=navigationView.getHeaderView(0);
        nav_name=  navHeaderView.findViewById(R.id.nav_name);
        user_profile=navHeaderView.findViewById(R.id.user_profile);

        fab=findViewById(R.id.fab);
       profile_seller= findViewById(R.id.profile_seller);

        setData();

        if(toolbar!=null) {

            mAppBarConfiguration = new AppBarConfiguration.Builder(/*navController.getGraph()*/
                R.id.home, R.id.search, R.id.fab,
                R.id.user, R.id.side_home,
                R.id.side_profile,R.id.profile_seller)
                    .setDrawerLayout(drawer)
                    .build();
            //Toolbar toolbar = findViewById(R.id.toolbar);

            NavigationUI.setupWithNavController(toolbar, navController, mAppBarConfiguration);

            NavigationUI.setupWithNavController(navigationView, navController);
           // NavigationUI.setupWithNavController(bottomNavView, navController);
        }
        else{
            drawer.setDrawerListener(null);
        }

        findViewById(R.id.profile_seller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.profile_seller);

            }
        });


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.fab);

            }
        });

       /* bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.plusbtn:
                        Fragment addDetails_fragment=new AddDetails_Fragment();
                        bottomNavView.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,addDetails_fragment).commit();

                }
                return false;
            }
        });*/

    }

    private void setData() {

        nav_name.setText(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserName));
        if( PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage).equals("")){
            Glide.with(getApplicationContext()).load(R.drawable.user).into(user_profile);
        }
        else {
            Glide.with(getApplicationContext()).load(image_base_url + PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage)).into(user_profile);
        }

        name.setText(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserName));
        propid.setText("Seller ID # "+PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.USER_SEQ_ID));
       // Toast.makeText(getApplicationContext(),PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.USER_SEQ_ID),Toast.LENGTH_SHORT).show();
        if( PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage).equals("")){
            Glide.with(getApplicationContext()).load(R.drawable.user).into(profile_seller);
        }
        else {
            Glide.with(getApplicationContext()).load(image_base_url + PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserImage)).into(profile_seller);
        }

    }

    private void setupSmoothBottomMenu() {

        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.bottom_nav_seller);
        Menu menu = popupMenu.getMenu();
        bottomNavView.setupWithNavController(menu, navController);
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

    @Override
    public void setbottom_Locked() {
        bottomNavView.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        bottomAppBar.setVisibility(View.GONE);
    }

    @Override
    public void setbottom_UnLocked() {
        bottomNavView.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        bottomAppBar.setVisibility(View.VISIBLE);
    }


}