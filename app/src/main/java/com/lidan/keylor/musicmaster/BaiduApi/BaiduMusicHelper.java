package com.lidan.keylor.musicmaster.BaiduApi;

import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicList;
import com.lidan.keylor.musicmaster.IMusic;

/**
 *
 * Created by keylor on 15-7-9.
 */
public class BaiduMusicHelper implements IMusic{

    private static final String BaseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting";



    private static BaiduMusicHelper instance ;

    public static BaiduMusicHelper getBaiduMusicHelper() {
        if (instance == null) {
            instance = new BaiduMusicHelper();
        }
        return instance;
    }
    @Override
    public MusicList getHotMusicList(int start, int count) {
        return null;
    }

    @Override
    public MusicInfo getMusicInfo(int id) {
        return null;
    }
}
