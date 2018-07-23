package com.codepath.apps.simpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    // List attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    //Deserialize JSON
    public static User fromJSON (JSONObject json) throws JSONException {
        User user = new User();

        // Extract and fill
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");

        return user;
    }
}
