package com.LuisdbosquezGmailCom.Shltr0Xd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.LuisdbosquezGmailCom.Shltr0Xd.estimote.Profile;

import org.w3c.dom.Text;

/**
 * Created by luisdbosquez on 10/22/16.
 */

public class ProfileActivity extends AppCompatActivity {

    public String[] problems = {"school tuition", "rent", "utility bills", "medical bills", "parking tickets", "groceries", "home repair", "car repair", "daycare", "a doctor's visit"};
    Profile profile;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        Bundle extras = getIntent().getExtras();
        String beaconId = extras.getString("beaconId");

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(profile.fund);

        int factor = (int) Math.floor(Math.random()*10) % 5;
        int progress = (int) Math.floor(profile.fund/factor);

        progressBar.setProgress(progress);

        profile = MyApplication.getProfileById(beaconId);

        setContentView(R.layout.activity_profile);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(profile.name);

        TextView location = (TextView) findViewById(R.id.location);
        location.setText(profile.hometown);

        TextView story = (TextView) findViewById(R.id.story);
        story.setText(profile.story);

        int index = (int) (Math.floor(Math.random() * 100)) % 10;

        TextView goal = (TextView) findViewById(R.id.goal);
        goal.setText("The current goal is to reach: $" + profile.fund + " to pay for " + problems[index] + ".");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void donate (View v) {
        Intent intent = new Intent(ProfileActivity.this, DonateActivity.class);
        intent.putExtra("beaconId", profile.beaconId);
        startActivity(intent);
    }
}
