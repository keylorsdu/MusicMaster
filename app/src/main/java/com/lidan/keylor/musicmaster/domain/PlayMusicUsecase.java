package com.lidan.keylor.musicmaster.domain;

import com.lidan.keylor.musicmaster.model.rest.MusicSource;
import com.squareup.otto.Bus;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class PlayMusicUsecase implements Usecase {

    String songId;

    MusicSource musicSource;
    Bus bus;
    public PlayMusicUsecase(MusicSource musicSource, Bus bus) {
        this.musicSource = musicSource;
        this.bus = bus;
    }
    public void setSongId(String songId) {
        this.songId = songId;
    }
    @Override
    public void execute() {
        musicSource.getMusicById(songId);
    }
}
