package com.LuisdbosquezGmailCom.Shltr0Xd.estimote;

/**
 * Created by luisdbosquez on 10/22/16.
 */

public class Profile {
    private static long count = 0;

    public String beaconId;
    public String name;
    public String hometown;
    public String story;
    public long id;

    public int fund;

    public Profile(String _name, String _hometown, String _story, String _beaconId)
    {
        name = _name;
        hometown = _hometown;
        story = _story;
        beaconId = _beaconId;
        id = count++;
        fund = (int) Math.ceil(Math.random() * 100) * 10;
    }

    public int addToFundraiser(int donation){
        this.fund += donation;
        return this.fund;
    }

    public void setFund(int _fund)
    {
        this.fund = _fund;
    }
}
