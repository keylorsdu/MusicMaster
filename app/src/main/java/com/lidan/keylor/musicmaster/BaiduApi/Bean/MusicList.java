package com.lidan.keylor.musicmaster.BaiduApi.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keylor on 15-7-9.
 */
public class MusicList {
    public ArrayList<MusicInfo> song_list;
    public Map<String, String> extention = new HashMap<>();

    @Override
    public String toString() {
        String result ="";
        for (MusicInfo info : song_list) {
            result += info.toString();
        }
        return result;
    }
}
