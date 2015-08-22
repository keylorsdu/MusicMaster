package com.lidan.keylor.musicmaster.model.rest;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public interface MusicSource {

    /*
    *   获取排行榜
    *    type: //1、新歌榜，2、热歌榜，
        11、摇滚榜，12、爵士，16、流行
        21、欧美金曲榜，22、经典老歌榜，23、情歌对唱榜，24、影视金曲榜，25、网络歌曲榜
     */
    void getHotBillListMusics();

    void getNewestBillListMusics();

    void getRockBillListMusics();

    void getJazzBillListMusics();

    void getPopuliarBillListMusics();

    void getEnglishHotBillListMusics();

    void getClassicBillListMusics();

    void getRetimBillListMusics();

    void getGoldMovieBillListMusics();

    void getPopularInternetBillListMusics();

    /*
    8   通过ID获取音乐播放地址
     */
    void getMusicById(String musicId);

    void getCatalogSug();

    void getPlayAAC();

    void getRecommandSongList();

    void getDownWeb();

    void getArtistInfo();

    void getArtistSongList();







}
