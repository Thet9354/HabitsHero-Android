package com.sp.madproposal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HistoryDetailActivity extends AppCompatActivity {

    Intent intent;

    private String name, dateTime;

    private ImageView btn_back;
    private TextView txtView_name, txtView_dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        intent = getIntent();

        initWidget();

        getIntentData();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getIntentData() {

        name = intent.getStringExtra("Name");
        dateTime = intent.getStringExtra("DateTime");

        initUI();

    }

    private void initUI() {

        txtView_name.setText(name);
        txtView_dateTime.setText(dateTime);
    }

    private void initWidget() {

        // ImageView
        btn_back = findViewById(R.id.btn_back);

        // TextView
        txtView_name = findViewById(R.id.txtView_name);
        txtView_dateTime = findViewById(R.id.txtView_dateTime);
    }
}