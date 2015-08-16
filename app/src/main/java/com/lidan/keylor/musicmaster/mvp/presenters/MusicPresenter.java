package com.lidan.keylor.musicmaster.mvp.presenters;

import android.util.Log;

import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicList;
import com.lidan.keylor.musicmaster.domain.Usecase;
import com.lidan.keylor.musicmaster.mvp.views.MusicView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class MusicPresenter implements Presenter {
    private static String TAG = MusicPresenter.class.getSimpleName();
    private MusicView musicView;
    private Bus bus;
    Usecase GetMusicUsecase;


    public MusicPresenter(Usecase GetMusicUsecase,Bus bus){
        this.GetMusicUsecase = GetMusicUsecase;
        this.bus = bus;
    }



    public void attach(MusicView musicView) {
        this.musicView = musicView;
    }

    public void onEndReached() {
        musicView.showLoadingLabel();
        GetMusicUsecase.execute();
    }

    @Subscribe
    public void onMusicsRecieved(MusicList result) {
        Log.i(TAG, "音乐收到了" + result);
        if (musicView.isEmpty()) {
            musicView.hideLoading();
            musicView.showMusic(result.song_list);

        } else {
            musicView.hideLoadingLabel();
            musicView.appendMusic(result.song_list);
        }
    }

    @Override
    public void start() {
        Log.i(TAG, "presenter started");
        if (musicView.isEmpty()) {
            bus.register(this);
            musicView.showLoading();
            GetMusicUsecase.execute();
            Log.i(TAG, "GetMusicUsecase executed");


        }


    }

    @Override
    public void stop() {
//        bus.unregister(this);

    }
}
