package com.codepath.apps.simpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    Button btCompose;
    EditText etCompose;
    TextView tvCount;
    String newTweet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        btCompose = findViewById(R.id.btCompose);
        etCompose = findViewById(R.id.etCompose);
        tvCount = findViewById(R.id.tvCount);
    }

    public void composeTweet (View v) {
        Intent i = new Intent();
        TwitterClient client = new TwitterClient(getApplicationContext());
        newTweet = etCompose.getText().toString();
        client.postTweet(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast toast = Toast.makeText(getApplicationContext(), "Oops, something went wrong!", Toast.LENGTH_LONG);
                toast.show();
            }
        } , newTweet);
        setResult(RESULT_OK, i);
        finish();
    }
}
