package com.lidan.keylor.musicmaster.mvp.presenters;

import android.net.Uri;
import android.util.Log;

import com.lidan.keylor.musicmaster.domain.PlayMusicUsecase;
import com.lidan.keylor.musicmaster.model.entity.MusicPlayInfo;
import com.lidan.keylor.musicmaster.mvp.views.MusicPlayView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class MusicPlayerPresenter implements Presenter {
    private static String TAG = MusicPlayerPresenter.class.getSimpleName();

    MusicPlayView musicPlayView;
    PlayMusicUsecase PlayMusicUsecase;
    Bus bus;

//    String songId;
    public MusicPlayerPresenter(PlayMusicUsecase PlayMusicUsecase, Bus bus) {
        this.PlayMusicUsecase = PlayMusicUsecase;
        this.bus = bus;
        bus.register(this);
    }
    @Subscribe
    public void onMusicPlayInfoRecieved(MusicPlayInfo musicPlayInfo) {

        Log.i(TAG, "音乐信息收到");
        musicPlayView.hideLoading();
        Uri musicUri = Uri.parse(musicPlayInfo.getBitrate().getFileLink());


        String bgURL = musicPlayInfo.getSonginfo().getPicBig();

        musicPlayView.play(musicPlayInfo);
        Log.i(TAG, "开始播放");


    }

//    public void setSongId(String songId) {
//        this.songId = songId;
//    }

    public void attach(MusicPlayView musicPlayView) {
        this.musicPlayView = musicPlayView;
    }
    @Override
    public void start() {
        musicPlayView.showLoading();

//        PlayMusicUsecase.setSongId(songId);

        PlayMusicUsecase.execute();
        Log.i(TAG, "音乐播放开始执行");
    }

    @Override
    public void stop() {
        bus.unregister(this);

    }
}
