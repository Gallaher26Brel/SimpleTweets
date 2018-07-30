package com.codepath.apps.simpletweets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ComposeActivity extends AppCompatActivity {

    Button btCompose;
    EditText etCompose;
    TextView tvCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        btCompose = findViewById(R.id.btCompose);
        etCompose = findViewById(R.id.etCompose);
        tvCount = findViewById(R.id.tvCount);
    }
}
