package com.lidan.keylor.musicmaster.mvp.presenters;

import android.util.Log;

import com.lidan.keylor.musicmaster.BaiduApi.BaiduMusicHelper;
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
    Usecase usecase;


    public MusicPresenter(Usecase usecase,Bus bus){
        this.usecase = usecase;
        this.bus = bus;
        bus.register(this);
    }


    public Usecase getUsecase() {
        return usecase;
    }

    public void setUsecase(Usecase usecase) {
        this.usecase = usecase;
    }

    public void attach(MusicView musicView) {
        this.musicView = musicView;
    }

    public void onEndReached() {
        musicView.showLoadingLabel();
        usecase.execute();
    }

    @Subscribe
    public void onMusicsRecieved(MusicList result) {
        Log.i(TAG, "音乐收到了" + result);

        if(BaiduMusicHelper.METHODBILLLIST.equals(result.extention.get("method")) &&
                (BaiduMusicHelper.HOTLIST+"").equals(result.extention.get("type"))) {
            if (musicView.isEmpty()) {
                musicView.hideLoading();
                musicView.showMusic(result.song_list);

            } else {
                musicView.hideLoadingLabel();
                musicView.appendMusic(result.song_list);
            }
        }

        if(BaiduMusicHelper.METHODBILLLIST.equals(result.extention.get("method")) &&
                (BaiduMusicHelper.NEWLIST+"").equals(result.extention.get("type"))) {
            if (musicView.isEmpty()) {
                musicView.hideLoading();
                musicView.showMusic(result.song_list);

            } else {
                musicView.hideLoadingLabel();
                musicView.appendMusic(result.song_list);
            }
        }

    }

    @Override
    public void start() {
        Log.i(TAG, "presenter started");
        if (musicView.isEmpty()) {

            musicView.showLoading();
            usecase.execute();
            Log.i(TAG, "usecase executed");


        }else {
            Log.i(TAG, "usecase executed");
            musicView.clear();
            usecase.execute();
        }


    }

    @Override
    public void stop() {
//        bus.unregister(this);

    }
}
