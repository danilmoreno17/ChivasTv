package tqmgpartners.com.chivastv;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import tqmgpartners.com.chivastv.fragments.frg_Home;
import tqmgpartners.com.chivastv.fragments.frg_OnDemand;
import tqmgpartners.com.chivastv.fragments.frg_PPV;

public class act_Browse
        extends AppCompatActivity
        implements  frg_OnDemand.OnFragmentInteractionListener, frg_PPV.OnFragmentInteractionListener, frg_Home.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_browse);

        if(((ChivasTv)getApplication()).strToken==null){
            Intent intent = new Intent(this, act_Login.class);
            startActivity(intent);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toggle.syncState();
        NavigationView navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.menu_seccion_1) {
                    abrirFragmento("Home");
                    // Handle the camera action
                }else if(id == R.id.menu_seccion_2){
                    abrirFragmento("PPV");
                }else{
                    abrirFragmento("Videos");
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        frg_Home firstFragment = new frg_Home();
        // Add the fragment to the 'fragment_container' FrameLayout/
         getSupportFragmentManager().beginTransaction()
              .replace(R.id.contentFrame, firstFragment).commit();
    }



    public void abrirFragmento(String _pStrFragmento){
        try {
            switch (_pStrFragmento) {
                case "Videos": {
                    // Create a new Fragment to be placed in the activity layout
                    frg_OnDemand firstFragment = new frg_OnDemand();
                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, firstFragment).commit();
                    break;
                }
                case "PPV": {
                    // Create a new Fragment to be placed in the activity layout
                    frg_PPV firstFragment = new frg_PPV();
                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, firstFragment).commit();
                    break;
                }
                case "Home": {
                    // Create a new Fragment to be placed in the activity layout
                    frg_Home firstFragment = new frg_Home();
                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, firstFragment).commit();
                    break;
                }
            }
        }
        catch(Exception ex)
        {
            Log.e("Exception_abrirFragmento",ex.getMessage());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.frm_dwr_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_seccion_1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_seccion_1) {
            abrirFragmento("movies");
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
