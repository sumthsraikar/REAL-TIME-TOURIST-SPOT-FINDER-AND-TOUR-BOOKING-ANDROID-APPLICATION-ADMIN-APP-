package com.example.adminapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TourScheduler extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;

    private Spinner tourPackageSpinner, tourDateSpinner;
    private EditText adultsInput, childrenWithBedInput, childrenWithoutBedInput, infantsInput;
    private TextView totalCostText;

    private final String[] tourPackages = {
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

    private final int[] tourCosts = {
            10500, 12000, 6000, 2600, 9000,
            9000, 20000, 14000, 21600, 12500,
            12500
    };

    private final String[] tourDates = {
            "Jan 7", "Feb 21", "Mar 4", "Apr 18", "May 11",
            "Jun 25", "Jul 8", "Aug 22", "Sep 6", "Oct 20",
            "Nov 3", "Dec 24"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_scheduler);

        // Initialize views
        tourPackageSpinner = findViewById(R.id.tourPackageSpinner);
        tourDateSpinner = findViewById(R.id.tourDateSpinner);
        adultsInput = findViewById(R.id.adultsInput);
        childrenWithBedInput = findViewById(R.id.childrenWithBedInput);
        childrenWithoutBedInput = findViewById(R.id.childrenWithoutBedInput);
        infantsInput = findViewById(R.id.infantsInput);
        totalCostText = findViewById(R.id.totalCostText);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button saveButton = findViewById(R.id.saveButton); // Add Save Button

        // Set up spinner for tour packages
        ArrayAdapter<String> packageAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tourPackages);
        packageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tourPackageSpinner.setAdapter(packageAdapter);

        // Set up spinner for tour dates
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tourDates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tourDateSpinner.setAdapter(dateAdapter);

        // Set click listener for calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalCost();
            }
        });

        // Set click listener for save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    saveData();
                }
            }
        });
    }

    private void calculateTotalCost() {
        // Get user inputs
        int adults = Integer.parseInt(adultsInput.getText().toString().trim());
        int childrenWithBed = Integer.parseInt(childrenWithBedInput.getText().toString().trim());
        int childrenWithoutBed = Integer.parseInt(childrenWithoutBedInput.getText().toString().trim());
        int infants = Integer.parseInt(infantsInput.getText().toString().trim());

        // Get selected tour package cost
        int selectedPackageIndex = tourPackageSpinner.getSelectedItemPosition();
        int tourCost = tourCosts[selectedPackageIndex];

        // Calculate total cost
        int totalCost = (adults + childrenWithBed) * tourCost + (childrenWithoutBed * (tourCost / 2)) + (infants * (tourCost / 4));

        // Display total cost
        totalCostText.setText("Total Cost: ₹" + totalCost);
    }

    private boolean checkPermission() {
        // Check if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_CODE);
            return false;
        }
        return true;
    }

    private void saveData() {
        String guestNames = "Guest Names: " + getGuestNames();
        int adults = Integer.parseInt(adultsInput.getText().toString().trim());
        int childrenWithBed = Integer.parseInt(childrenWithBedInput.getText().toString().trim());
        int childrenWithoutBed = Integer.parseInt(childrenWithoutBedInput.getText().toString().trim());
        int infants = Integer.parseInt(infantsInput.getText().toString().trim());

        int selectedPackageIndex = tourPackageSpinner.getSelectedItemPosition();
        String tourPackage = tourPackages[selectedPackageIndex];
        String tourDate = tourDates[tourDateSpinner.getSelectedItemPosition()];

        String data = "Tour Package: " + tourPackage + "\n" +
                "Tour Date: " + tourDate + "\n" +
                "Adults: " + adults + "\n" +
                "Children with Bed: " + childrenWithBed + "\n" +
                "Children without Bed: " + childrenWithoutBed + "\n" +
                "Infants: " + infants + "\n" +
                guestNames + "\n";

        // Save data to external storage
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "TourDetails.txt");
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(data.getBytes());
            fos.write("\n".getBytes()); // New line for each entry
            fos.close();
            totalCostText.setText("Data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            totalCostText.setText("Error saving data.");
        }
    }

    private String getGuestNames() {
        StringBuilder guestNames = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            EditText guestNameInput = findViewById(getResources().getIdentifier("guestName" + i, "id", getPackageName()));
            String name = guestNameInput.getText().toString().trim();
            if (!name.isEmpty()) {
                guestNames.append(name).append(", ");
            }
        }
        // Remove last comma and space
        if (guestNames.length() > 0) {
            guestNames.setLength(guestNames.length() - 2);
        }
        return guestNames.toString();
    }
}
