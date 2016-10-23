package com.LuisdbosquezGmailCom.Shltr0Xd;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.LuisdbosquezGmailCom.Shltr0Xd.estimote.BeaconID;
import com.LuisdbosquezGmailCom.Shltr0Xd.estimote.BeaconNotificationsManager;
import com.LuisdbosquezGmailCom.Shltr0Xd.estimote.Profile;
import com.estimote.sdk.EstimoteSDK;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MyApplication extends Application {

    private final String ESTIMOTE_BASE_BEACON_IDS[] = {"","",""};
    public static final ArrayList<Profile> profiles;
    static
    {
        profiles = new ArrayList<Profile>();

        profiles.add(new Profile("Louis J.", "Baton Rouge, Lousiana", "I am about to be evicted from my house due to lack of payments. I tried to negotiate with the landlord, but he is tired of waiting.\n" +
                "I just need to wait a few more days for my paycheck to go through, after that I should be able to sustain myself and my kids.  \nAnything to help would be greatly appreciated.", "b9407f30-f5f8-466e-aff9-25556b57fe6d:9662:55986"));
        profiles.add(new Profile("Tiffany B.", "St. Louis, Missouri", "I am a single mother of one who was recently laid off from my job. I am seeking assistance for rent and daycare expenses during this difficult time so that I can search for new employments. \nThank you in advance for your graciousness.", "b9407f30-f5f8-466e-aff9-25556b57fe6d:8469:60382"));
        profiles.add(new Profile("John S.", "Seattle, Washington", "I have a small gambling problem, and I lost a portion of money for my utility bills on the casino. Please help if you can.\n" +
                "My car has been acting up lately and the mechanic notified me that I must replace my transition. This is a very unexpected expense for me, and I need my car to commute back and forth to work.  Anything to help would be greatly appreciated.", "b9407f30-f5f8-466e-aff9-25556b57fe6d:20787:41524"));

        //profiles.add("b9407f30-f5f8-466e-aff9-25556b57fe6d:9662:55986", "Louis");
        //profiles.put("b9407f30-f5f8-466e-aff9-25556b57fe6d:8469:60382", "Jenny");
        //profiles.put("b9407f30-f5f8-466e-aff9-25556b57fe6d:20787:41524", "John");
    }
    private boolean beaconNotificationsEnabled = false;
    private static String userLocation = "St. Louis, MO";
    private static String fundeeNames[] = {"Louis", "Jenny", "John"};

    public static ArrayList<Profile> profileList;

    public static Profile getProfileById(String id)
    {
        for(Profile profile : profiles){
            if (profile.beaconId.equals(id))
                return profile;
        }

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        profileList = new ArrayList<Profile>();
        EstimoteSDK.initialize(getApplicationContext(), "shltr-0xd", "eeb612d3cd9b518c27445cf221880cbb");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }

    public ArrayList<IdTuple> loadNearbyIds(String location){
        ArrayList<IdTuple> idTuples = new ArrayList<IdTuple>();
        idTuples.add(new IdTuple(20787, 41524));
        idTuples.add(new IdTuple(9662, 55986));
        idTuples.add(new IdTuple(8469, 60382));

        return idTuples;
    }

    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);

        int i = 0;
        for(Profile profile : profiles){
            String baseKey = profile.beaconId.substring(0, 36);
            String firstId = profile.beaconId.substring(37, profile.beaconId.lastIndexOf(':'));
            String secondId = profile.beaconId.substring(profile.beaconId.lastIndexOf(':')+1);

            beaconNotificationsManager.addNotification(
                    new BeaconID(baseKey, Integer.parseInt(firstId), Integer.parseInt(secondId)),
                    "You have crossed paths with " + profile.name + ". Open their profile to learn more.", null);
            i++;
        }

        beaconNotificationsManager.startMonitoring();

        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }

    private class IdTuple {
        public final int x;
        public final int y;
        public IdTuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


