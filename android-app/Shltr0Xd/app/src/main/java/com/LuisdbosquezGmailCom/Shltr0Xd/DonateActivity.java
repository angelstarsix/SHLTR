package com.LuisdbosquezGmailCom.Shltr0Xd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.LuisdbosquezGmailCom.Shltr0Xd.estimote.Profile;

import org.w3c.dom.Text;

/**
 * Created by luisdbosquez on 10/23/16.
 */

public class DonateActivity extends AppCompatActivity {
    public SeekBar donationBar;
    public TextView donationAmount;
    public TextView donationList;
    public Button donateButton;
    public Profile profile;
    public String[] donationItems = {"a warm meal.", "a week's worth of groceries", "a water/sewer bill.", "new clothes.", "part of a month's rent.","part of a medical bill."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Bundle extras = getIntent().getExtras();
        String beaconId = extras.getString("beaconId");

        donationList = (TextView) findViewById(R.id.donationList);
        donationBar = (SeekBar) findViewById(R.id.seek1);

        profile = MyApplication.getProfileById(beaconId);
        donationBar.setMax(profile.fund);

        donationAmount= (TextView) findViewById(R.id.label);
        donateButton = (Button)  findViewById(R.id.donateButton);

        donationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                donationAmount.setText("Donation amount: $" + progressChanged + ".");
                if(progressChanged > 10){
                    int index = 0;
                    if (progressChanged > 50)
                        index++;
                    if (progressChanged > 75)
                        index++;
                    if (progressChanged > 100)
                        index++;
                    if (progressChanged > 125)
                        index++;
                    if (progressChanged > 150)
                        index++;
                    donationList.setText("Your donation will pay for " + donationItems[index]);
                }
            }
        });
        donateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Thanks for your donation",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
