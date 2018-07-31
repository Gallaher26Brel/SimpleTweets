package com.codepath.apps.simpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.adapters.TweetAdapter;
import com.codepath.apps.simpletweets.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class TweetsListFragment extends Fragment{

    protected TweetAdapter tweetAdapter;
    protected ArrayList<Tweet> tweets;
    RecyclerView rvTweets;

    // inflation happens inside of onCreateView

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //inflate layout
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        // init array list with data source
        tweets = new ArrayList<>();
        // construct the adapter from data source
        tweetAdapter = new TweetAdapter(tweets);
        // recyclerView setup (layout manager, use adapter)
        rvTweets = v.findViewById(R.id.rvTweet);
        rvTweets.setLayoutManager(linearLayoutManager);
        //set adapter
        rvTweets.setAdapter(tweetAdapter);

        return  v;
    }

    public void addItems(JSONArray response){
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

}
