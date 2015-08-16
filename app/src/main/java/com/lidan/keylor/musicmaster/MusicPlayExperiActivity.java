package com.lidan.keylor.musicmaster;

import android.content.Intent;

import android.media.MediaPlayer;

import android.media.audiofx.Visualizer;

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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;

import com.lidan.keylor.musicmaster.experiment.VisualizerView;

import org.json.JSONObject;

import java.math.BigInteger;


public class MusicPlayExperiActivity extends AppCompatActivity {

    private static final String TAG = MusicPlayExperiActivity.class.getSimpleName();
    private String songId="";
    private String songMP3URL;
    ImageButton play;
    ImageButton next;
    ImageButton previous;
    MediaPlayer player=new MediaPlayer();
    PlayStatus status;
    JsonObjectRequest request;
    RequestQueue queue;
    VisualizerView mVisualizerView;
    Visualizer mVisualizer;
    LinearLayout mLinearLayout;
    MediaController controller;
    enum PlayStatus {
        playing, stop ,complete,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_musicplaycontroller);


//        controller = (MediaController) findViewById(R.id.mc_main);


        findViews();



        Intent intent = getIntent();

        songId = intent.getStringExtra(MainActivity.MUSIC_ID);
        Log.i(TAG, "song ID " + songId);



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


    private void setupVisualizerFxAndUI() {


        mVisualizerView = new VisualizerView(this);
        mVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                (int) (360f * getResources()
                        .getDisplayMetrics().density)));

        mVisualizerView.setProgress(13);// 设置波形的高度O
        mVisualizerView.setmHeight(8);// 让水位处于最高振幅
        mLinearLayout.addView(mVisualizerView);

        mVisualizer = new Visualizer(player.getAudioSessionId());
//        mVisualizer = new Visualizer(player.getAudioSessionId());
        // 设置需要转换的音乐内容长度，专业的说这就是采样,该采样值一般为2的指数倍
        mVisualizer.setCaptureSize(256);
        mVisualizer.setEnabled(true);
        final int maxCR = Visualizer.getMaxCaptureRate();

        // 实例化Visualizer，参数SessionId可以通过MediaPlayer的对象获得

        // 接下来就好理解了设置一个监听器来监听不断而来的所采集的数据。一共有4个参数，第一个是监听者，第二个单位是毫赫兹，表示的是采集的频率，第三个是是否采集波形，第四个是是否采集频率
        mVisualizer.setDataCaptureListener(
                // 这个回调应该采集的是波形数据
                new Visualizer.OnDataCaptureListener() {
                    public void onWaveFormDataCapture(Visualizer visualizer,
                                                      byte[] bytes, int samplingRate) {
                        System.out.println("new bytes");
                        mVisualizerView.updateVisualizer(bytes); // 按照波形来画图
                    }

                    // 这个回调应该采集的是快速傅里叶变换有关的数据
                    public void onFftDataCapture(Visualizer visualizer,
                                                 byte[] fft, int samplingRate) {
                        mVisualizerView.updateVisualizer(fft);
                    }
                }, maxCR / 2, false, true);
    }

    private MediaPlayer getMediaPlayer() {
        return player == null ? new MediaPlayer() : player;
    }

    private void findViews() {
        play = (ImageButton) findViewById(R.id.ib_main_start);
        next = (ImageButton) findViewById(R.id.ib_main_right);
        previous = (ImageButton) findViewById(R.id.ib_main_left);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_play);


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
                    JSONObject bitrate= response.getJSONObject("bitrate");

                    songMP3URL = bitrate.getString("file_link");
                    Log.i(TAG, "MP3地址为：" + songMP3URL);
                    Uri uri = Uri.parse(songMP3URL);
                    player = MediaPlayer.create(MusicPlayExperiActivity.this, uri);

                    setupVisualizerFxAndUI();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            status = PlayStatus.complete;
                            play.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                        }
                    });
                    player.start();


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
