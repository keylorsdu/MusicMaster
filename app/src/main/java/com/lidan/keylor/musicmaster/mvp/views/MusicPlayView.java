package com.lidan.keylor.musicmaster.mvp.views;

import com.lidan.keylor.musicmaster.model.entity.MusicPlayInfo;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public interface MusicPlayView extends MVPView {



    void play(MusicPlayInfo musicPlayInfo);

    void stop();

    boolean isPlaying();

    void showLoading();

    void hideLoading();


}
