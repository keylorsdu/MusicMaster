package com.lidan.keylor.musicmaster.mvp.views;

import android.net.Uri;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public interface MusicPlayView extends MVPView {



    void play(Uri uri,String bgURL);

    void stop();

    boolean isPlaying();

    void showLoading();

    void hideLoading();


}
