package com.munsang.musicking.musicking.util;

import android.os.AsyncTask;

import com.munsang.musicking.musicking.domain.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minyumi on 2015. 12. 22..
 */
public class MusicCall extends AsyncTask<String, Void, List<Music>> {

    private static final String URL = "http://musicdecade.cafe24.com/api/rand?rank=";

    @Override
    protected List<Music> doInBackground(String... params) {
        String callUrl = URL;
        if(params.length > 0) {
            callUrl = callUrl + params[0];
        }
        try {
            return parseToMusic(getJsonObject(callUrl));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray getJsonObject(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            InputStream inputStream = connection.getInputStream();
            byte[] contents = new byte[1024];

            int bytesRead=0;
            StringBuilder sb = new StringBuilder();
            while( (bytesRead = inputStream.read(contents)) != -1){
                sb.append(new String(contents, 0, bytesRead));
            }

            return new JSONArray(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Music> parseToMusic(JSONArray object) throws JSONException {

        List<Music> musics = new ArrayList<>();
        if (object == null) {
            return musics;
        }

        for (int i = 0; i < object.length(); i++) {
            JSONObject musicObject = object.getJSONObject(i);
            Music music = new Music();
            music.parse(musicObject);
            musics.add(music);
        }

        return musics;

    }
}
