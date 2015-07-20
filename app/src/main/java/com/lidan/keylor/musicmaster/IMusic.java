package com.lidan.keylor.musicmaster;

import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicList;

/**
 * Created by keylor on 15-7-9.
 */
public interface IMusic {

    String getHotMusicListURL(int start, int count);

    String getMusicInfoURL(int id);

}
