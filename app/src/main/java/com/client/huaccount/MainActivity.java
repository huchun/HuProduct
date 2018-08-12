package com.client.huaccount;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.client.huaccount.account.LoginActivity;
import com.client.huaccount.base.BaseActivity;
import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.configure.ConstantInfo;
import com.client.huaccount.util.UserUtil;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    private Toolbar mToolbar = null;
    private FloatingActionButton  fab = null;
    private DrawerLayout  mDrawerLayout;
    private NavigationView mNavigationView = null;
    public boolean  firstInto = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        /*
         * fix toolbar left icon
         */
        toggle.setDrawerIndicatorEnabled(false);
        /*
         * update left icon
          */
        toggle.setHomeAsUpIndicator(R.drawable.login_icon_default_avatar);

        String  name = null;
        final SharedPreferences sp = this.getSharedPreferences(name, this.MODE_PRIVATE);
        final boolean firstInto = sp.getBoolean(ConstantInfo.isFirst, true);
        final SharedPreferences.Editor editor = sp.edit();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  if (firstInto == true){
                     editor.putBoolean(ConstantInfo.isFirst, false);
                     editor.commit();
                Intent  intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                     Toast.makeText(MainActivity.this, "login", Toast.LENGTH_SHORT).show();
            //     }else{
                  //   mDrawerLayout.openDrawer(GravityCompat.START);
                   //  mNavigationView  = (NavigationView) findViewById(R.id.nav_view);
                   //  mNavigationView.setNavigationItemSelectedListener(MainActivity.this);
                  //   Toast.makeText(MainActivity.this, "navi", Toast.LENGTH_SHORT).show();
               //  }
               /* LoginInfo loginInfo = UserUtil.getUserCache();
                if (loginInfo != null && !TextUtils.isEmpty(loginInfo.getId())){
                       mDrawerLayout.openDrawer(GravityCompat.START);
                      mNavigationView  = (NavigationView) findViewById(R.id.nav_view);
                      mNavigationView.setNavigationItemSelectedListener(MainActivity.this);
                       Toast.makeText(MainActivity.this, "navi", Toast.LENGTH_SHORT).show();
                }else {
                    Intent  intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }*/
            }
        });

        //mNavigationView  = (NavigationView) findViewById(R.id.nav_view);
        //mNavigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id ==R.id.layout_header_main){

        }else if (id ==R.id.imageView){

        }else if (id ==R.id.txt_account){

        }else if (id ==R.id.txt_name){

        }else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent  intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
