package com.app.bincore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnSignup;

    DatabaseHelper dbHelper;  // Initialize the DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Set login button listener
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (checkLogin(username, password)) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Set signup button listener
        btnSignup.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // Check if the username or password is empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please fill out both fields", Toast.LENGTH_SHORT).show();
            } else {
                signup(username, password);
            }
        });
    }

    // Method to check login credentials
    private boolean checkLogin(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_PASSWORD};
        String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        boolean valid = cursor.moveToFirst();
        cursor.close();

        return valid;
    }

    // Method to handle user signup
    private void signup(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Check if username already exists
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, null, DatabaseHelper.COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);
        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            cursor.close();
            return;
        }

        // Insert new user
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);

        // Check if the insert was successful
        if (result != -1) {
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error during signup", Toast.LENGTH_SHORT).show();
        }
    }
}
