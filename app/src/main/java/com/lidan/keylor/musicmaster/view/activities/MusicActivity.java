package com.lidan.keylor.musicmaster.view.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;
import com.lidan.keylor.musicmaster.R;
import com.lidan.keylor.musicmaster.common.BusProvider;
import com.lidan.keylor.musicmaster.domain.GetMusicUsecase;
import com.lidan.keylor.musicmaster.model.rest.RestMusicSource;
import com.lidan.keylor.musicmaster.mvp.presenters.MusicPresenter;
import com.lidan.keylor.musicmaster.mvp.views.MusicView;
import com.lidan.keylor.musicmaster.view.adapters.MusicRecyclerViewAdapter;
import com.nispok.snackbar.SnackbarManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MusicActivity extends AppCompatActivity implements MusicView,Thread.UncaughtExceptionHandler{

    //注入

    @Bind(R.id.activity_music_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.activity_music_progressbar)
    ProgressBar progressBar;
    @Bind(R.id.activity_music_toolbar)
    Toolbar toolBar;

    MusicPresenter musicPresenter;

    MusicRecyclerViewAdapter musicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

//        String  a=null;
//        a.length();

        Thread.setDefaultUncaughtExceptionHandler(this);
        ButterKnife.bind(this);

        initPresenter();

        initToolBar();

        initRecyclerView();


    }

    private void initPresenter() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        GetMusicUsecase getMusicUsecase;
        getMusicUsecase = new GetMusicUsecase(new RestMusicSource(requestQueue, BusProvider.getBus()),BusProvider.getBus());
        musicPresenter = new MusicPresenter(getMusicUsecase, BusProvider.getBus());
        musicPresenter.attach(this);

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean actionBarShowing = false;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visable = recyclerView.getLayoutManager().getChildCount();
                int totel = recyclerView.getLayoutManager().getItemCount();
                int passed = ((LinearLayoutManager)recyclerView.getLayoutManager() ).findFirstVisibleItemPosition();

                if (visable + passed >= totel) {
                    musicPresenter.onEndReached();
                }

                if(dy < -10){//向下拉
                    if(!actionBarShowing){
                        
                        toolBar.setVisibility(View.GONE);
                    }
                }

                if(dy > 10){//向上拉
                    if(actionBarShowing){
                        toolBar.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

    }

    private void initToolBar() {

        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");

    }

    @Override
    protected void onStart() {
        super.onStart();
        musicPresenter.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMusic(List<MusicInfo> musics) {
        musicAdapter = new MusicRecyclerViewAdapter(musics);
        recyclerView.setAdapter(musicAdapter);

    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showLoadingLabel() {
        com.nispok.snackbar.Snackbar loadingSnackBar = com.nispok.snackbar.Snackbar.with(this)
                .text("正在加载")
                .actionLabel("cancel")
                .duration(com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                .color(getResources().getColor(R.color.background_floating_material_dark))
                .actionColor(getResources().getColor(R.color.accent_material_dark));

        SnackbarManager.show(loadingSnackBar);

    }

    @Override
    public void hideLoadingLabel() {
        SnackbarManager.dismiss();

    }

    @Override
    public boolean isEmpty() {
        return musicAdapter==null || musicAdapter.getMusics().isEmpty();

    }

    @Override
    public void appendMusic(List<MusicInfo> musics) {

        musicAdapter.appendMusics(musics);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.err.println(thread);
        System.err.println(ex.fillInStackTrace());
    }

}
