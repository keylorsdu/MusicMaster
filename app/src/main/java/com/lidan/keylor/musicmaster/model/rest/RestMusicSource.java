package com.lidan.keylor.musicmaster.model.rest;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lidan.keylor.musicmaster.BaiduApi.BaiduMusicHelper;
import com.lidan.keylor.musicmaster.BaiduApi.BaiduMusicRequest;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicList;
import com.lidan.keylor.musicmaster.model.entity.MusicPlayInfo;
import com.squareup.otto.Bus;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class RestMusicSource implements MusicSource {
    private static String TAG = RestMusicSource.class.getSimpleName();
    private static int pageCount = 10;

    private static String PLAYMUSIXBASEURL ="http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&songid=";

    RequestQueue requestQueue;
    Bus bus;

    int currentPage = 1;



    public RestMusicSource(RequestQueue requestQueue ,Bus bus) {
        this.requestQueue = requestQueue;
        this.bus = bus;
        bus.register(this);
    }

    @Override
    public void getMusics() {

        if (currentPage * pageCount >= 100) {
            bus.post("最后一页");
            return;
        }
        BaiduMusicHelper helper = new BaiduMusicHelper();
        String requestUri = helper.getHotMusicListURL((currentPage - 1) * pageCount, pageCount);
        Log.i(TAG, "开始请求 " + requestUri);
        BaiduMusicRequest musicRequest = new BaiduMusicRequest<MusicList>(Request.Method.GET,
                MusicList.class, requestUri, new Response.Listener<MusicList>() {
            @Override
            public void onResponse(MusicList response) {
                Log.i(TAG, "结果为" + response);
                Log.i(TAG, "bus 发布结果");
                if (response instanceof MusicList) {
                    bus.post(response);
                } else {
                    bus.post("最后一页");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "结果错误");
            }
        });
        requestQueue.add(musicRequest);
        currentPage++;

    }

    @Override
    public void getMusicById(final String musicId) {
        String  requestUri = PLAYMUSIXBASEURL + musicId;
        BaiduMusicRequest request = new BaiduMusicRequest<MusicPlayInfo>(Request.Method.GET, MusicPlayInfo.class
                , requestUri, new Response.Listener<MusicPlayInfo>() {
            @Override
            public void onResponse(MusicPlayInfo response) {
                Log.i(TAG, musicId + "的音乐信息已经得到:" + response.toString());

                bus.post(response);

                Log.i(TAG, "已经发出事件");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, musicId + "的音乐信息没收到");
            }
        });
        requestQueue.add(request);
        Log.i(TAG + musicId, "开始请求播放URL");
    }
}
