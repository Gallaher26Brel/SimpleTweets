package com.codepath.apps.simpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.codepath.apps.simpletweets.fragments.TweetsPagerAdapter;
import com.codepath.apps.simpletweets.models.EndlessRecyclerViewScrollListener;

 public class TimelineActivity extends AppCompatActivity {

//    TweetsListFragment fragmentTweetsLists;
    Button btCompose;
    private EndlessRecyclerViewScrollListener scrollListener;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // get the view pager
        ViewPager vpPager = findViewById(R.id.viewpager);
        // set the adapter for the view pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        // setup the TabLayout to use the view pager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);

//        fragmentTweetsLists = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
/*
        // find recycler view
        rvTweets = findViewById(R.id.rvTweet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // init array list with data source
        tweets = new ArrayList<>();
        // construct the adapter from data source
        tweetAdapter = new TweetAdapter(tweets);
        // recyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(linearLayoutManager);
*/
/*
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                populateTimeline();
            }
        };
*/
/*
        // Adds the scroll listener to RecyclerView
        //rvTweets.addOnScrollListener(scrollListener);
        rvTweets.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(!rvTweets.canScrollVertically(1)){
                    loadMoreTimeline(tweets.get(tweets.size()-1).uid);
                }
            }
        });
*/

//        btCompose = findViewById(R.id.btCompose);
    }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline_activity_actions, menu);
        return true;
    }


    @Override
     public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.btCompose:
                Intent composeNewTweet = new Intent(this, ComposeActivity.class);
                this.startActivityForResult(composeNewTweet, REQUEST_CODE);
//                SystemClock.sleep(2000);
/*
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        populateTimeline();
                    }
                }, 2000);
*/
//                populateTimeline();
                return true;
        }
        return false;
    }
//    private void composeTweet(MenuItem item){
//        Intent composeNewTweet = new Intent(this, ComposeActivity.class);
//        this.startActivity(composeNewTweet);
//    }
}

