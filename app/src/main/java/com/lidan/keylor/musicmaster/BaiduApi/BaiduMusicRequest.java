package com.lidan.keylor.musicmaster.BaiduApi;



import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * 负责请求数据
 * 并转化为Java类如MusicInfo，MusicList
 * Created by keylor on 15-7-9.
 */
public class BaiduMusicRequest<T> extends Request<T>{
    String TAG = BaiduMusicRequest.class.getSimpleName();

    Gson gson = new Gson();
    Response.Listener mListener;
    Class<T> clazz;

    public BaiduMusicRequest(int method,Class<T> clazz, String url,Response.Listener<T> slistener, Response.ErrorListener listener) {
        super(method, url, listener);
        mListener = slistener;
        this.clazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.i(TAG, json);
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);

    }
}
