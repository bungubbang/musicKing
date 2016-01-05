package com.munsang.musicking.musicking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.munsang.musicking.musicking.domain.Member;
import com.munsang.musicking.musicking.util.WebViewSetting;


public class InviteActivity extends Activity {

    private SharedPreferences loginPreferences;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("친구초대");
        setContentView(R.layout.activity_invite);

        loginPreferences = getSharedPreferences(getResources().getString(R.string.loginPref), MODE_PRIVATE);

        Intent intent = getIntent();
        Member member = (Member) intent.getSerializableExtra("member");

        webView = (WebView) findViewById(R.id.invite_webView);
        WebViewSetting.appin(this, webView);

        webView.loadUrl("http://www.appinkorea.co.kr/fevi/invite.php?id=" + member.getId() + "&password=" + member.getPassword());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}