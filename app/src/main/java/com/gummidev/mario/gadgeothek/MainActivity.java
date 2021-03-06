package com.gummidev.mario.gadgeothek;

import android.content.ClipData;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.gummidev.mario.gadgeothek.Fragments.ConfigFrag;
import com.gummidev.mario.gadgeothek.Fragments.HomeFragment;
import com.gummidev.mario.gadgeothek.Fragments.LoanFrag;
import com.gummidev.mario.gadgeothek.Fragments.LoginFrag;
import com.gummidev.mario.gadgeothek.Fragments.RegFrag;
import com.gummidev.mario.gadgeothek.Fragments.ResFrag;

import ch.hsr.mge.gadgeothek.service.Callback;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SET address to your local IP
        String remote_gadgeothek;

        remote_gadgeothek = getPreferences(Context.MODE_PRIVATE).getString("remote_gadgeothek", "http://mge1.dev.ifs.hsr.ch");

        LibraryService.setServerAddress(remote_gadgeothek);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new HomeFragment()).addToBackStack("Home").commit();

         // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the menu icon instead of the launcher icon.
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        final NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);

        LayoutInflater inflater = getLayoutInflater();

        View listHeaderView = inflater.inflate(R.layout.header_menu,null, false);

        nvDrawer.addHeaderView(listHeaderView);

        setupDrawerContent(nvDrawer);

        if(LibraryService.isLoggedIn()){
            nvDrawer.getMenu().findItem(R.id.nav_login_fragment).setVisible(false);
            nvDrawer.getMenu().findItem(R.id.nav_registration_fragment).setVisible(false);
        }else {
            nvDrawer.getMenu().findItem(R.id.nav_ausleihe_fragment).setVisible(false);
            nvDrawer.getMenu().findItem(R.id.nav_reservation_fragment).setVisible(false);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) { // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_home_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.nav_login_fragment:
                fragmentClass = LoginFrag.class;
                break;
            case R.id.nav_ausleihe_fragment:
                fragmentClass = LoanFrag.class;
                break;
            case R.id.nav_reservation_fragment:
                fragmentClass = ResFrag.class;
                break;
            case R.id.nav_einstellung_fragment:
                fragmentClass = ConfigFrag.class;
                break;
            case R.id.nav_registration_fragment:
                fragmentClass = RegFrag.class;
                break;
            default:
                fragmentClass = LoginFrag.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack((String) menuItem.getTitle()).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
    }

    @Override
    public void onFragmentInteraction() {

    }
}
