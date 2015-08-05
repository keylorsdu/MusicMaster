package com.lidan.keylor.musicmaster;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.math.BigInteger;


public class MusicPlayActivity extends AppCompatActivity {

    private static final String TAG = MusicPlayActivity.class.getSimpleName();
    private String songId="";
    private String songMP3URL;
    ImageButton play;
    ImageButton next;
    ImageButton previous;
    MediaPlayer player;
    PlayStatus status;
    JsonObjectRequest request;
    RequestQueue queue;

    enum PlayStatus {
        playing, stop ,complete,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_musicplaycontroller);

        findViews();
        Intent intent = getIntent();

        songId = intent.getStringExtra(MainActivity.MUSIC_ID);
        Log.i(TAG, "song ID "+songId);
        player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "mediaplayer 准备好了,开始播放");
                status = PlayStatus.playing;
                play.setImageResource(R.drawable.ic_pause_black_24dp);
                player.start();
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                status = PlayStatus.complete;
                play.setImageResource(R.drawable.ic_play_arrow_black_24dp);

            }
        });


//        String songURL = "http://ting.baidu.com/data/music/links?songIds=38233821";
//        String stringURL ="http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&songid=121523276";
//        Log.i(TAG, "URL :" + songURL);
        request = getRequestBySongID(songId);
        queue = Volley.newRequestQueue(this);
        queue.add(request);
        setListeners();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setListeners() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigInteger intSongID = new BigInteger(songId);
                BigInteger one = new BigInteger("1");
                String nextSongID = intSongID.add(one).toString();
                songId = nextSongID;
                playMusic();
            }


        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigInteger intSongID = new BigInteger(songId);
                BigInteger one = new BigInteger("1");
                String nextSongID = intSongID.add(one.negate()).toString();
                songId = nextSongID;
                playMusic();

            }
        });
    }

    public void playMusic() {
        if(status == PlayStatus.playing){
            status = PlayStatus.stop;
            play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            player.stop();

            return;
        }
        if (status == PlayStatus.stop) {
            player.start();
            return;

        }
        request = getRequestBySongID(songId);
        Log.i(TAG, "加入队列" + request.getUrl());
        queue.add(request);

    }


    private MediaPlayer getMediaPlayer() {
        return player == null ? new MediaPlayer() : player;
    }

    private void findViews() {
        play = (ImageButton) findViewById(R.id.ib_main_start);
        next = (ImageButton) findViewById(R.id.ib_main_right);
        previous = (ImageButton) findViewById(R.id.ib_main_left);
    }


    private  JsonObjectRequest getRequestBySongID(String mySongId) {
        Log.i(TAG, "开始获取request song id :" + mySongId);
        String songURL ="http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&songid="+mySongId;

        Log.i(TAG, "开始获取request URl :" + songURL);
        JsonObjectRequest newRequest = new JsonObjectRequest(Request.Method.GET, songURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "return successfully");
                Log.i(TAG, "原始数据为：" + response.toString());
                try {
                    JSONObject bitrate= response.getJSONObject( "bitrate");

                    songMP3URL = bitrate.getString("file_link");
                    Log.i(TAG, "MP3地址为：" + songMP3URL);
                    Uri uri = Uri.parse(songMP3URL);
                    player = getMediaPlayer();
                    player.setDataSource(MusicPlayActivity.this, uri);
                    player.prepareAsync();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "failed");
            }
        });

        return newRequest;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inf
        // late the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
