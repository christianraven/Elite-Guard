package com.example.eliteguard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // 1. Declare UI components and Firebase Auth
    private Button superButtonGetStarted;
    private Button superButtonLearnMore;
    private Button superButtonLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // 3. Bind UI Buttons
        superButtonGetStarted = findViewById(R.id.superButtonGetStarted);
        superButtonLearnMore = findViewById(R.id.superButtonLearnMore);
        superButtonLogin = findViewById(R.id.superButtonLogin);

        // 4. Check if user is currently logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User IS logged in -> Change button to Log Out
            superButtonLogin.setText("Log Out");
            superButtonLogin.setOnClickListener(v -> {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                recreate(); // Refresh activity UI
            });
        } else {
            // User IS NOT logged in -> Navigate to LoginActivity
            superButtonLogin.setText("Log In");
            superButtonLogin.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            });
        }

        // 5. Get Started Button Navigation -> RegisterActivity
        superButtonGetStarted.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // 6. Learn More Button Navigation -> DetailsActivity
        superButtonLearnMore.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        });
    }
}