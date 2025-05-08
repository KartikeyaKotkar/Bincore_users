package com.app.bincore;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Ensure layout file is named correctly

        switchNotifications = findViewById(R.id.switch_notifications);

        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message = isChecked ? "Notifications Enabled" : "Notifications Disabled";
                Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
