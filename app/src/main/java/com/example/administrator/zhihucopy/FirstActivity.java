package com.example.administrator.zhihucopy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationBar bottomNavigationBar;

    int lastSelectedPosition = 0;

    BadgeItem numberBadgeItem;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        //tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.firstactivity_toolbar);
        setSupportActionBar(toolbar);
        //bottom navigation bar
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        refresh();
        //RecyclerView and floating action bar
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, getResources()
                .getStringArray(R.array.countries));
        recyclerView.setAdapter(adapter);

        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);
    }


    @Override
    public void onClick(View v) {
    }

    private void refresh() {
        Log.d("FirstActivity","method refresh is been use");

        bottomNavigationBar.clearAll();

        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("" + lastSelectedPosition)
                .setHideOnSelect(true);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_bottomtabbar_feed, "").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottomtabbar_discover, "").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottomtabbar_notification, "").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottomtabbar_message, "").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_bottomtabbar_more, "").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition > 3 ? 3 : lastSelectedPosition)
                .initialise();
    }

}
