package com.munsang.musicking.musicking;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.munsang.musicking.musicking.domain.Music;
import com.munsang.musicking.musicking.util.MusicCall;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.Collections.shuffle;

public class MusicQuiz extends Activity {

    CardView q1Card;
    CardView q2Card;
    CardView q3Card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_music_quiz);
        try {
            List<Music> musics = new MusicCall().execute().get(5, TimeUnit.SECONDS);
            Music music = musics.get(0);
            final Integer rank = music.getRank();
            TextView quizTitle = (TextView) findViewById(R.id.quiz_title);
            String title = music.getYear() + "년 " + music.getMonth() + "월 " + rank + " 위 곡은?";
            quizTitle.setText(title);

            for (Music mm : musics) {
                Log.i("music", mm.toString());
            }

            Collections.shuffle(musics);

            drawSelectList(musics);

            q1Card = (CardView) findViewById(R.id.q1);
            q1Card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkRight(v, rank);
                }
            });
            q2Card = (CardView) findViewById(R.id.q2);
            q2Card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkRight(v, rank);
                }
            });
            q3Card = (CardView) findViewById(R.id.q3);
            q3Card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkRight(v, rank);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkRight(View v, Integer rank) {
        if(rank == (Integer)v.getTag()) {
            Toast.makeText(getApplicationContext(), "정답!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "오답!", Toast.LENGTH_SHORT).show();
        }
    }

    private void drawSelectList(final List<Music> musics) throws IOException {
        q1Card = (CardView) findViewById(R.id.q1);
        q1Card.setTag(musics.get(0).getRank());

        SimpleDraweeView q1Pic = (SimpleDraweeView) findViewById(R.id.q1_pic);
        q1Pic.setImageURI(Uri.parse(musics.get(0).getAlbumImage()));

        TextView q1Music = (TextView) findViewById(R.id.q1_music);
        q1Music.setText(musics.get(0).getSongName());

        TextView q1Singer = (TextView) findViewById(R.id.q1_singer);
        q1Singer.setText(musics.get(0).getSinger());

        q2Card = (CardView) findViewById(R.id.q2);
        q2Card.setTag(musics.get(1).getRank());

        SimpleDraweeView q2Pic = (SimpleDraweeView) findViewById(R.id.q2_pic);
        q2Pic.setImageURI(Uri.parse(musics.get(1).getAlbumImage()));

        TextView q2Music = (TextView) findViewById(R.id.q2_music);
        q2Music.setText(musics.get(1).getSongName());

        TextView q2Singer = (TextView) findViewById(R.id.q2_singer);
        q2Singer.setText(musics.get(1).getSinger());

        q3Card = (CardView) findViewById(R.id.q3);
        q3Card.setTag(musics.get(2).getRank());

        SimpleDraweeView q3Pic = (SimpleDraweeView) findViewById(R.id.q3_pic);
        q3Pic.setImageURI(Uri.parse(musics.get(2).getAlbumImage()));

        TextView q3Music = (TextView) findViewById(R.id.q3_music);
        q3Music.setText(musics.get(2).getSongName());

        TextView q3Singer = (TextView) findViewById(R.id.q3_singer);
        q3Singer.setText(musics.get(2).getSinger());
    }


}
