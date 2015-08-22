package com.lidan.keylor.musicmaster.domain;

import android.util.Log;

import com.lidan.keylor.musicmaster.model.rest.MusicSource;
import com.squareup.otto.Bus;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class GetHotMusicUsecase implements Usecase {
    String TAG = GetHotMusicUsecase.class.getSimpleName();

    MusicSource musicSource;
    Bus bus;

    public GetHotMusicUsecase(MusicSource musicSource, Bus bus) {
        this.musicSource = musicSource;
        this.bus = bus;
    }
    @Override
    public void execute() {
        Log.i(TAG, "musicSource getmusics ");
        musicSource.getHotBillListMusics();
    }

}
