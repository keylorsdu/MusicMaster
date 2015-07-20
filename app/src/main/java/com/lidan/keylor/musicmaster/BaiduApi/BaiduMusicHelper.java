package com.lidan.keylor.musicmaster.BaiduApi;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicList;
import com.lidan.keylor.musicmaster.IMusic;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by keylor on 15-7-9.
 */
public class BaiduMusicHelper implements IMusic{
    public static final String TAG = "BaiduMesicHelper";

    private static final String BaseUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting";


    public static final String FORMAT = "json";
    public static final String FROM = "webapp_music";
    public static final String LISTMETHOD = "baidu.ting.billboard.billList";
    public static final int  HOTLIST = 1;


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
    @Override
    public String  getHotMusicListURL(int start, int count) {
        Map<String, String> params = new HashMap<>();
        params.put("format", FORMAT);
        params.put("from", FROM);
        String methodString = LISTMETHOD + "&type=" + HOTLIST + "&size=" + count + "&offset=" + start;
        params.put("method", methodString);
        String targetUrl = appendParams(params);

        Log.i(TAG, targetUrl);
        return targetUrl;

    }

    @Override
    public String getMusicInfoURL(int id) {
        return null;
    }


}
