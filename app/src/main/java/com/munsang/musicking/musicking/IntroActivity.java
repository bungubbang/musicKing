package com.munsang.musicking.musicking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.common.base.Strings;
import com.munsang.musicking.musicking.domain.Member;
import com.munsang.musicking.musicking.util.LoginCall;
import com.munsang.musicking.musicking.util.MemberInfoFactory;
import com.munsang.musicking.musicking.util.NetworkManager;
import com.nextapps.naswall.NASWall;



public class IntroActivity extends Activity {


    private static final String LOG_IN_URL = "http://www.appinkorea.co.kr/mk/login.php";

    private SharedPreferences loginPreferences;
    private ProgressDialog mProgressDialog;

    private String vid;

    private static final String SENDER_ID = "34043858841";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        sendGA();

        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);

        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);

    //    	registerReceiver(mHandleMessageReceiver, new IntentFilter(
    //    			DISPLAY_MESSAGE_ACTION));

        // Get GCM registration id
        final String aregId = GCMRegistrar.getRegistrationId(this);

        // Check if regid already presents
        if (aregId.isEmpty()) {
            // Registration is not present, register now with GCM
            GCMRegistrar.register(this, SENDER_ID);
            Log.i("mundong", "registered with GCM");
        } else {
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.
                Log.i("mundong", "Already registered with GCM");
            } else {
                GCMRegistrar.register(this, SENDER_ID);
            }
        }



        if (!NetworkManager.isOnline(this)) {
            Toast.makeText(this, "해당 앱을 사용하려면 인터넷에 접속하여야 합니다. 인터넷에 접속후 다시 실행해 주십시오.", Toast.LENGTH_LONG).show();
            return;
        }

        FacebookSdk.sdkInitialize(this);
        NASWall.init(this, false);

        vid = getIntent().getStringExtra("vid");

        loginPreferences = getSharedPreferences(getResources().getString(R.string.loginPref), MODE_PRIVATE);
        String id = loginPreferences.getString("id", null);
        String password = loginPreferences.getString("password", null);
        boolean isAutoLogin = loginPreferences.getBoolean("isAutoLogin", false);
        mProgressDialog = ProgressDialog.show(IntroActivity.this, "", "잠시만 기다려 주세요.", true);

        if(!Strings.isNullOrEmpty(id) && !Strings.isNullOrEmpty(password) && isAutoLogin) {
            Member member = new Member();
            member.setId(id);
            member.setPassword(password);
            new MemberInfoFactory(member, this).getInfo();
            new LoginCall(this, true, member, mProgressDialog).execute(this.vid);
        } else {
            mProgressDialog.dismiss();
            Intent loginIntent = new Intent(this, LoginActivity.class);
            this.startActivity(loginIntent);
            this.finish();
        }

    }

    private void sendGA() {
        /**
         * GA setting
         */
        App application = (App) getApplication();
        Tracker tracker = application.getDefaultTracker();

        tracker.setScreenName("Intro Activity");
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("intro")
                .setAction("show")
                .build());
    }

    public void finishActivity() {
        this.finish();
    }



}
