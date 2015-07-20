package com.lidan.keylor.musicmaster.BaiduApi.Bean;

import java.util.ArrayList;

/**
 * Created by keylor on 15-7-9.
 */
public class MusicList {
    public ArrayList<MusicInfo> song_list;

    @Override
    public String toString() {
        String result ="";
        for (MusicInfo info : song_list) {
            result += info.toString();
        }
        return result;
    }
}
