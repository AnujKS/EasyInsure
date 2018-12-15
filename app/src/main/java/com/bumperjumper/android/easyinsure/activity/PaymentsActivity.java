package com.bumperjumper.android.easyinsure.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bumperjumper.android.easyinsure.R;

public class PaymentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payments_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

    }
}
