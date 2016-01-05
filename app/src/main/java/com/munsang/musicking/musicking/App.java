package com.munsang.musicking.musicking;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseInstallation;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Minyumi on 2016. 1. 5..
 */
public class App extends Application {

    public static Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        parseInit();
    }

    private void parseInit() {
        Parse.initialize(this, "2iC7nc2t8jrm8DFPtV03BT1TiXODEkNy6cUomq95", "N8HSWDLfQqDKQ1d5okDbYuIHJtgg7yjugSdffDo1");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

            mTracker = analytics.newTracker("UA-39699634-6");
            mTracker.enableExceptionReporting(true);
            mTracker.enableAdvertisingIdCollection(true);
            mTracker.enableAutoActivityTracking(true);
        }
        return mTracker;
    }
}