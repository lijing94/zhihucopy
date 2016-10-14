package com.example.administrator.zhihucopy;


import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    BottomNavigationBar bottomNavigationBar;

    int lastSelectedPosition = 0;

    BadgeItem numberBadgeItem;

    private SwipeRefreshLayout mSwipeRefreshWidget;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        //tool bar
        //这边要留意的是setNavigationIcon需要放在 setSupportActionBar之后才会生效。
        Toolbar toolbar = (Toolbar) findViewById(R.id.firstactivity_toolbar);
        setSupportActionBar(toolbar);
        //将onMenuItemClick监听者设置给toolbar
        // Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClick);



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

        //SwipeRefreshLayout
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.behavior_demo_swipe_refresh);
        mSwipeRefreshWidget.setColorScheme(
                R.color.blue,
                R.color.teal,
                R.color.ripple,
                R.color.brown);
        mSwipeRefreshWidget.setOnRefreshListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //解決一進入Activity就自動彈出虛擬鍵盤
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_all_live:
                    msg += "Click edit";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(FirstActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.main, menu);
        //搜索栏
        return true;
    }

    //下拉刷新相关逻辑
    public void onRefresh() {
        Log.w("test", "in onRefresh");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSwipeRefreshWidget.setRefreshing(false);
            }
        }, 1500);
    }


    @Override
    public void onClick(View v) {
    }

    private void refresh() {
        Log.d("FirstActivity", "method refresh is been use");

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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("First Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
