package com.example.whatsapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StarterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        /* user + M4vSrVYUj6yt*/
        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("myappID")
                .clientKey("n57FdYpOMYP6")
                .server("http://3.142.70.74/parse/")
                .build()
        );
//
        //ParseObject object = new ParseObject("ExampleObject");
        //object.put("myNumber", "123");
        //object.put("myString", "rob");
//
        //object.saveInBackground(new SaveCallback() {
        //    @Override
        //    public void done(ParseException ex) {
        //        if (ex == null) {
        //            Log.i("Parse Result", "Successful!");
        //        } else {
        //            Log.i("Parse Result", "Failed" + ex.toString());
        //        }
        //    }
        //});
//
//
        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}