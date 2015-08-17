package com.lidan.keylor.musicmaster.view.activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lidan.keylor.musicmaster.R;
import com.lidan.keylor.musicmaster.common.BusProvider;
import com.lidan.keylor.musicmaster.domain.PlayMusicUsecase;
import com.lidan.keylor.musicmaster.model.entity.MusicPlayInfo;
import com.lidan.keylor.musicmaster.model.rest.RestMusicSource;
import com.lidan.keylor.musicmaster.mvp.presenters.MusicPlayerPresenter;
import com.lidan.keylor.musicmaster.mvp.views.MusicPlayView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.mobiwise.library.MusicPlayerView;

public class PlayActivity extends AppCompatActivity implements MusicPlayView {
    public static String SONGID = "songId";

    private static String TAG = PlayActivity.class.getSimpleName();

    MusicPlayerView mpv ;
    private String songId="38233821";

    static MediaPlayer mediaPlayer;

    MusicPlayerPresenter playerPresenter;

    @Bind(R.id.textViewSong)
    TextView songTitle;
    @Bind(R.id.textViewSinger)
    TextView songSinger;

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer == null ? new MediaPlayer() : mediaPlayer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        mpv = (MusicPlayerView) findViewById(R.id.mpv);

        songId = getIntent().getStringExtra(SONGID);

        Log.i(TAG, "收到歌曲ID" + songId);
        ButterKnife.bind(this);

        initMPV();

        initPresenter();

        initPlayer();





    }

    @Override
    protected void onStart() {
        super.onStart();
        playerPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerPresenter.stop();
    }

    private void initMPV() {
        mpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mpv.isRotating()) {
                    mpv.stop();
                    mediaPlayer.pause();

                }else {
                    mpv.start();
                    mediaPlayer.start();
                }
            }
        });


    }

    private void initPlayer() {
        mediaPlayer = getMediaPlayer();
        mediaPlayer.reset();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "播放器准备好了，开始播放");
                mpv.start();
                mediaPlayer.start();
            }
        });

    }

    private void initPresenter() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        PlayMusicUsecase playMusicUsecase= new PlayMusicUsecase(new RestMusicSource(requestQueue, BusProvider.getBus()),BusProvider.getBus());
        playMusicUsecase.setSongId(songId);

        playerPresenter = new MusicPlayerPresenter(playMusicUsecase, BusProvider.getBus());
        playerPresenter.attach(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play, menu);
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

    @Override
    public void play(MusicPlayInfo musicPlayInfo) {

        Uri musicUri = Uri.parse(musicPlayInfo.getBitrate().getFileLink());


        String bgURL = musicPlayInfo.getSonginfo().getPicBig();
        String musicTitle = musicPlayInfo.getSonginfo().getTitle();
        String singer = musicPlayInfo.getSonginfo().getAuthor();
        int musicTimeLength = musicPlayInfo.getBitrate().getFile_duration();


        mpv.setCoverURL(bgURL);
        mpv.setMax(musicTimeLength);


        songTitle.setText(musicTitle);
        songSinger.setText(singer);
        
        try {
            mediaPlayer.setDataSource(this, musicUri);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        mediaPlayer.stop();

    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
