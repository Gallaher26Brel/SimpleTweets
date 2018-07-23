package com.codepath.apps.simpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    //List of attributes
    public String body;
    public String createdAt;
    public long uid; // database ID for the tweet
    public User user;

    //Deserialize the JSON
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException{
        Tweet tweet = new Tweet();

        // extract values
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        return  tweet;
    }
}
