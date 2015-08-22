package com.lidan.keylor.musicmaster.domain;

import android.util.Log;

import com.lidan.keylor.musicmaster.model.rest.MusicSource;
import com.squareup.otto.Bus;

/**
 * Created by keylorlidan on 2015/8/22.
 */
public class GetNewestMusicUsecase implements Usecase {

    String TAG = GetNewestMusicUsecase.class.getSimpleName();
    MusicSource musicSource;
    Bus bus;

    public GetNewestMusicUsecase(MusicSource musicSource, Bus bus) {
        this.musicSource = musicSource;
        this.bus = bus;
    }
    @Override

    public void execute() {
        Log.i(TAG, "musicSource getmusics ");
        musicSource.getNewestBillListMusics();
    }

}
