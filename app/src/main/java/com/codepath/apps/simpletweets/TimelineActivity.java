 package com.codepath.apps.simpletweets;

 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;
 import android.util.Log;
 import android.view.Menu;

 import com.codepath.apps.simpletweets.adapters.TweetAdapter;
 import com.codepath.apps.simpletweets.models.EndlessRecyclerViewScrollListener;
 import com.codepath.apps.simpletweets.models.Tweet;
 import com.loopj.android.http.JsonHttpResponseHandler;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;

 import cz.msebera.android.httpclient.Header;

 public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        client = TwitterApp.getRestClient(getApplicationContext());
        // find recycler view
        rvTweets = findViewById(R.id.rvTweet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // init array list with data source
        tweets = new ArrayList<>();
        // construct the adapter from data source
        tweetAdapter = new TweetAdapter(tweets);
        // recyclerView setup (layout manager, use adapter)
        rvTweets.setLayoutManager(linearLayoutManager);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                populateTimeline();
            }
        };
        //set adapter
        rvTweets.setAdapter(tweetAdapter);
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
        populateTimeline();
    }

     private void loadMoreTimeline(long uid) {
         client.loadMoreTimeline(new JsonHttpResponseHandler(){
                                     @Override
                                     public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                         for (int i = 0 ; i < response.length() ; i++){
                                             // convert each object to a Tweet model
                                             // add that Tweet mode to our data source
                                             // notify the adapter that we've added an item
                                             try {
                                                 Tweet tweet = Tweet.fromJson(response.getJSONObject(i));
                                                 tweets.add(tweet);
                                                 tweetAdapter.notifyItemInserted(tweets.size() - 1);
                                             } catch (JSONException e) {
                                                 e.printStackTrace();
                                             }
                                         }
                                     }
                                 },
                 uid);
     }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline_activity_actions, menu);
        return true;
    }

    private void populateTimeline (){
        tweets.clear();
        tweetAdapter.notifyDataSetChanged();
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                Log.d("TwitterClient", response.toString());
                // iterate through JSON Array
                // for each entry, deserialize
                for (int i = 0 ; i < response.length() ; i++){
                    // convert each object to a Tweet model
                    // add that Tweet mode to our data source
                    // notify the adapter that we've added an item
                    try {
                        Tweet tweet = Tweet.fromJson(response.getJSONObject(i));
                        tweets.add(tweet);
//                        tweetAdapter.notifyItemInserted(tweets.size() - 1);
                        tweetAdapter.notifyItemInserted(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}

