package com.LuisdbosquezGmailCom.Shltr0Xd.estimote;

/**
 * Created by luisdbosquez on 10/22/16.
 */

public class ProfileItem {
    static int countProfileItems = 0;
    public Profile profile;
    public int id;
    public ProfileItem(Profile _profile) {
        profile = _profile;
        id = ++countProfileItems;
    }

}

