package com.lidan.keylor.musicmaster.mvp.views;

import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;

import java.util.List;

/**
 * Created by keylorlidan on 2015/8/15.
 */
public interface MusicView extends MVPView {
    void showMusic(List<MusicInfo> musics);

    void showLoading();

    void hideLoading();

    void showLoadingLabel();//当下拉时，没有数据时要去请求数据

    void hideLoadingLabel();

    boolean isEmpty();

    void appendMusic(List<MusicInfo> musics);
}
