package com.example.adminapp;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AdminBookingsActivity extends AppCompatActivity {

    private Spinner tourPackageSpinner, tourDateSpinner;
    private Button fetchDetailsButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_bookings);

        // Initialize views
        tourPackageSpinner = findViewById(R.id.tourPackageSpinner);
        tourDateSpinner = findViewById(R.id.tourDateSpinner);
        fetchDetailsButton = findViewById(R.id.fetchDetailsButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Populate spinners
        populateSpinners();

        // Set button click listener
        fetchDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchTourDetails();
            }
        });
    }

    private void populateSpinners() {
        // Populate tour packages
        String[] tourPackages = {
                "Dakshina Kannada Tour - ₹10,500",
                "Badami-Bijapur Tour - ₹12,000",
                "Dandeli Tour - ₹6,000",
                "Jog Falls Tour - ₹2,600",
                "Chikkamangaluru Tour - ₹9,000",
                "Mysore-Coorg Tour - ₹9,000",
                "Shirdi Tour - ₹20,000",
                "Munnar - Alleppey Tour - ₹14,000",
                "Tamilnadu Tour - ₹21,600",
                "Goa Dudhsagar Tour - ₹12,500",
                "Mantralaya Srishailam Tour - ₹12,500"
        };

        ArrayAdapter<String> packageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tourPackages);
        tourPackageSpinner.setAdapter(packageAdapter);

        // Populate tour dates
        String[] tourDates = {
                "Jan 7", "Feb 21", "Mar 4", "Apr 18", "May 11", "Jun 25",
                "Jul 8", "Aug 22", "Sep 6", "Oct 20", "Nov 3", "Dec 24"
        };

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tourDates);
        tourDateSpinner.setAdapter(dateAdapter);
    }

    private void fetchTourDetails() {
        String selectedPackage = tourPackageSpinner.getSelectedItem().toString().trim();
        String selectedDate = tourDateSpinner.getSelectedItem().toString().trim();

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "TourDetails.txt");
        if (!file.exists()) {
            resultTextView.setText("No data found. Ensure TourDetails.txt exists.");
            return;
        }

        StringBuilder results = new StringBuilder();
        boolean matching = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder record = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Check for record separators
                if (line.trim().isEmpty()) {
                    if (matching) {
                        results.append(record.toString()).append("\n");
                        matching = false;
                    }
                    record.setLength(0); // Clear the record builder
                    continue;
                }

                record.append(line).append("\n");

                // Check for matching criteria
                if (line.toLowerCase().contains(("Tour Date: " + selectedDate).toLowerCase()) &&
                        record.toString().toLowerCase().contains(("Tour Package: " + selectedPackage).toLowerCase())) {
                    matching = true;
                }
            }

            // Append last record if it matches
            if (matching) {
                results.append(record.toString()).append("\n");
            }

            // Display results
            if (results.length() > 0) {
                resultTextView.setText(results.toString());
            } else {
                resultTextView.setText("No matching data found for the selected criteria.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultTextView.setText("Error reading data.");
        }
    }
}
