package com.lidan.keylor.musicmaster.BaiduApi;

import android.content.Context;
import android.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by keylor on 15-7-9.
 */
public class BaiduMusicHelper implements IBaiduMusic{
    public static final String TAG = "BaiduMesicHelper";

    private static final String BaseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting";


    public static final String FORMAT = "json";
    public static final String FROM = "webapp_music";
    public static final String METHODBILLLIST = "baidu.ting.billboard.billList";
    public static final String METHODSEARCH = "baidu.ting.search.catalogSug";
    public static final String METHODPLAY = "baidu.ting.song.play";
    public static final String METHODPLAYACC = "baidu.ting.song.playAAC";
    public static final String METHODLRC = "baidu.ting.song.lry";
    public static final String METHODRecommandSongList = "baidu.ting.song.getRecommandSongList";
    public static final String METHODDownWeb= "baidu.ting.song.downWeb";
    public static final String METHODARTISTINFO = "baidu.ting.artist.getInfo";
    public static final String METHODARTISTSONGLIST = "baidu.ting.artist.getSongList";

    /*
    *
    * baidu.ting.billboard.billList  {type:1,size:10, offset:0}
    type: //1、新歌榜，2、热歌榜，
        11、摇滚榜，12、爵士，16、流行
        21、欧美金曲榜，22、经典老歌榜，23、情歌对唱榜，24、影视金曲榜，25、网络歌曲榜
     */
    public static final int  ROCKLIST = 11;
    public static final int  NEWLIST = 1;
    public static final int  JAZZLIST = 12;
    public static final int  PopLIST = 16;
    public static final int  EngListLIST = 21;
    public static final int  CLASSICLIST = 22;
    public static final int  IEMICLIST = 23;
    public static final int  MOVIELIST = 24;
    public static final int  INTERNETLIST = 25;
    public static final int  HOTLIST = 2;



    private static BaiduMusicHelper instance ;
    private Context context;

    public static BaiduMusicHelper getBaiduMusicHelper() {
        if (instance == null) {
            instance = new BaiduMusicHelper();
        }
        return instance;
    }

    private String appendParams(Map<String, String> params) {
        StringBuffer sb = new StringBuffer(BaseUrl);
        sb.append("?");
        for (Map.Entry<String,String> e : params.entrySet()) {
            sb.append(e.getKey());
            sb.append("=");
            sb.append(e.getValue());
            sb.append("&");
        }
        return sb.toString();
    }

    public  String  getHotMusicListURL(int start, int count) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        String methodString = METHODBILLLIST + "&type=" + HOTLIST + "&size=" + count + "&offset=" + start;
        params.put("method", methodString);
        String targetUrl = appendParams(params);

        Log.i(TAG, targetUrl);
        return targetUrl;

    }


    public String getMusicInfoURL(int id) {
        return null;
    }




    @Override
    public String newBillListURL(int type, int size, int offset) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODBILLLIST);

        params.put("type", type+"");
        params.put("size", size + "");
        params.put("offset", offset + "");
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newCatalogSugURL(String keyword) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODSEARCH);
        params.put("keyword", keyword);
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newPlayURL(String songId) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODPLAY);
        params.put("songId", songId);
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newPlayACCURL(String songId) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODPLAYACC);
        params.put("songId", songId);
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newLryURL(String songId) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODLRC);
        params.put("songId", songId);
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newRecommandSongListURL(String songId, int num) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODRecommandSongList);
        params.put("songId", songId);
        params.put("num", num + "");
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newDownWebURL(String songId, String bit, Date date) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODDownWeb);
        params.put("songId", songId);
        params.put("date", System.currentTimeMillis()+"");
        params.put("bit", bit);

        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newArtistInfoURL(String tingUid) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODARTISTINFO);
        params.put("tingUid",tingUid);
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }

    @Override
    public String newArtistSongList(String tingUid, int limit, int userCluster, int order) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        params.put("method", METHODARTISTSONGLIST);
        params.put("tingUid", tingUid);
        params.put("limit", limit+"");
        params.put("userCluster", 1+"");
        params.put("order", 2 + "");
        String target = appendParams(params);
        Log.i(TAG, target);
        return target;
    }
}
