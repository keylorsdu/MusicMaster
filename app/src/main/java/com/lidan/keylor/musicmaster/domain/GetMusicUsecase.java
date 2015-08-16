package com.lidan.keylor.musicmaster.domain;

import com.lidan.keylor.musicmaster.model.rest.MusicSource;
import com.squareup.otto.Bus;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class GetMusicUsecase implements Usecase {

    MusicSource musicSource;
    Bus bus;

    public GetMusicUsecase(MusicSource musicSource, Bus bus) {
        this.musicSource = musicSource;
        this.bus = bus;
    }
    @Override
    public void execute() {
        musicSource.getMusics();
    }

}
