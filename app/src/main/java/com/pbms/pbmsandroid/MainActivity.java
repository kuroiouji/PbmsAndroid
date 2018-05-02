package com.pbms.pbmsandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.pbms.pbmsandroid.model.ProjectDao;
import com.pbms.pbmsandroid.page.BudgetGraphFragment;
import com.pbms.pbmsandroid.page.DraftWithdrawFragment;
import com.pbms.pbmsandroid.page.HomeFragment;
import com.pbms.pbmsandroid.page.ProjectStatusFragment;
import com.pbms.pbmsandroid.page.WithdrawFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String bgyId;
    List<ProjectDao> pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentDetail, new HomeFragment().newInstance(bgyId));
        transaction.addToBackStack(null);
        transaction.commit();
        setTitle("Pbms - หน้าหลัก");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
            alertDlg.setMessage("Are you sure you want to exit?");
            alertDlg.setCancelable(false); // We avoid that the dialong can be cancelled, forcing the user to choose one of the options
            alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }
            );
            alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // We do nothing
                }
            });
            alertDlg.create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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
        Fragment fragment = new Fragment();

        if (id == R.id.nav_home) {
            fragment = new HomeFragment().newInstance(bgyId);
            setTitle("Pbms - หน้าหลัก");
        } else if (id == R.id.nav_pjstatus) {
            fragment = new ProjectStatusFragment().newInstance(bgyId);
            setTitle("Pbms - จัดการสถานะโครงการ");
        } else if (id == R.id.nav_withdraw) {
            fragment = new DraftWithdrawFragment().newInstance(bgyId);
            setTitle("Pbms - จัดการโครงสร้างใบเบิก");
        } else if (id == R.id.nav_graph) {
            fragment = new BudgetGraphFragment().newInstance(bgyId);
            setTitle("Pbms - กราฟการเบิกจ่าย");
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentDetail, fragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goToWithdraw(){
        Fragment fragment = new WithdrawFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentDetail, fragment);
        transaction.commit();
        setTitle("Pbms - เพิ่มใบเบิก");
    }

    public void goToDraft(){
        Fragment fragment = new DraftWithdrawFragment().newInstance(bgyId);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentDetail, fragment);
        transaction.commit();
        setTitle("Pbms - จัดการโครงสร้างใบเบิก");
    }

    public String getBgyId() {
        return bgyId;
    }

    public void setBgyId(String bgyId) {
        this.bgyId = bgyId;
        Log.d("Main", "setBgyId: " + this.bgyId);
    }

}
