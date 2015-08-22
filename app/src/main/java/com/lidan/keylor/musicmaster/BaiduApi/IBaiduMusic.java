package com.lidan.keylor.musicmaster.BaiduApi;

import java.util.Date;

/**
 * Created by keylorlidan on 2015/8/22.
 */
public interface IBaiduMusic {
    String newBillListURL(int type, int size, int offset);

    String newCatalogSugURL(String keyword);

    String newPlayURL(String songId);

    String newPlayACCURL(String songId);

    String newLryURL(String songId);

    String newRecommandSongListURL(String songId, int num);

    //    baidu.ting.song.downWeb  {songid: id, bit:"24, 64, 128, 192, 256, 320, flac", _t: (new Date())}
//    songid: //歌曲id
//    bit: //码率
//    _t: //时间戳
    String newDownWebURL(String songId, String bit, Date date);

    String newArtistInfoURL(String tingUid);

    String newArtistSongList(String tingUid, int limit, int userCluster, int order);


}
