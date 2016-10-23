package com.LuisdbosquezGmailCom.Shltr0Xd.estimote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.LuisdbosquezGmailCom.Shltr0Xd.MainActivity;
import com.LuisdbosquezGmailCom.Shltr0Xd.R;

import java.util.ArrayList;

/**
 * Created by luisdbosquez on 10/22/16.
 */

public class ProfileAdapter extends ArrayAdapter<Profile> {
    int resourceView;

    public ProfileAdapter(Context context, ArrayList<Profile> profiles){
        super(context, R.layout.activity_listitem, profiles);
        resourceView = R.layout.activity_listitem;
        MainActivity.detectedProfiles = profiles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        Profile profile = getItem(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(resourceView, parent, false);
        }

        if (profile != null) {
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView location = (TextView) v.findViewById(R.id.location);
            TextView story = (TextView) v.findViewById(R.id.story);


            if (name != null) {
                name.setText(profile.name);
                location.setText(profile.hometown);
                story.setText(profile.story.substring(0, 100) + "...");
            }
        }

        return v;
    }
}