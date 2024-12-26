package com.example.adminapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Find the box1 LinearLayout by ID
        LinearLayout box1 = findViewById(R.id.box1);

        // Set a click listener for box1 to navigate to MainActivity
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Find the box2 LinearLayout by ID
        LinearLayout box2 = findViewById(R.id.box2);

        // Set a click listener for box2 to navigate to AdminBookingsActivity
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, BookingList.class);
                startActivity(intent);
            }
        });

        // Find the box3 LinearLayout by ID
        LinearLayout box3 = findViewById(R.id.box3);

        // Set a click listener for box3 to navigate to TourScheduler
        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, TourScheduler.class);
                startActivity(intent);
            }
        });

        // Find the box2 LinearLayout by ID
        LinearLayout box4 = findViewById(R.id.box4);

        // Set a click listener for box4 to navigate to AdminBookingsActivity
        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AdminBookingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
