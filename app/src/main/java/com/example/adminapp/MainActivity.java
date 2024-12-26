package com.example.adminapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adminapp.models.User;
import com.example.adminapp.adapters.UserAdapter;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // RecyclerView for displaying user list
    private RecyclerView recyclerView;
    private UserAdapter adapter; // Adapter to manage user list in RecyclerView
    private List<User> userList = new ArrayList<>(); // List to hold user data
    private FirebaseFirestore db; // Firestore instance for database operations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firestore and adapter
        db = FirebaseFirestore.getInstance();
        adapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(adapter);

        // Fetch users from Firestore
        fetchUsers();
    }

    private void fetchUsers() {
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userList.clear(); // Clear the list before adding new data
                        for (DocumentSnapshot doc : task.getResult()) {
                            // Create a User object from Firestore document
                            User user = new User(
                                    doc.getId(), // Document ID as user ID
                                    doc.getString("name"), // Get name
                                    doc.getString("email"), // Get email
                                    doc.getString("phone") // Get phone
                            );
                            userList.add(user); // Add user to the list
                        }
                        adapter.notifyDataSetChanged(); // Notify adapter of data change
                    } else {
                        // Handle fetch error (e.g., log or show a toast)
                    }
                });
    }
}
