package com.lidan.keylor.musicmaster.BaiduApi.Bean;

import java.util.ArrayList;

/**
 * Created by keylorlidan on 2015/8/5.
 */
public class MusicAddress {
    int  errorCode;// 22000,
     Data  data;//

    public String getAddress() {
        return data.songList.get(0).songLink;
    }

}