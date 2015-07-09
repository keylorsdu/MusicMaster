package com.lidan.keylor.musicmaster.BaiduApi;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * 负责请求数据
 * 并转化为Java类如MusicInfo，MusicList
 * Created by keylor on 15-7-9.
 */
public class BaiduMusicRequest<T> extends Request<T>{

    public BaiduMusicRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(T response) {

    }
}
