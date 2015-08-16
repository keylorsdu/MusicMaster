package com.lidan.keylor.musicmaster.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidan.keylor.musicmaster.BaiduApi.Bean.MusicInfo;
import com.lidan.keylor.musicmaster.R;
import com.lidan.keylor.musicmaster.mvp.views.MusicView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = MusicRecyclerViewAdapter.class.getSimpleName();


    List<MusicInfo> musicInfoList;

    Context context;

    public MusicRecyclerViewAdapter(List<MusicInfo> musicInfoList) {
        this.musicInfoList = musicInfoList;
    }


    public void appendMusics(List<MusicInfo> musicsToAppend) {
        musicInfoList.addAll(musicsToAppend);
        notifyDataSetChanged();
    }

    public List<MusicInfo> getMusics() {
        return musicInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_music, viewGroup, false);
        MusicHolder musicHolder = new MusicHolder(itemView);
        return musicHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MusicHolder musicHolder = (MusicHolder) viewHolder;
        musicHolder.musicTitle.setText(musicInfoList.get(i).getTitle());

        Uri imageUri = Uri.parse(musicInfoList.get(i).getPic_big());//Picasso
        Picasso.with(context).load(imageUri).fit().centerCrop().into(musicHolder.musicImageView
                , new Callback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "图片加载成功");
            }

            @Override
            public void onError() {
                Log.i(TAG, "图片加载失败");

            }
        });
    }

    @Override
    public int getItemCount() {
        return musicInfoList.size();
    }

    private class MusicHolder extends  RecyclerView.ViewHolder{

        ImageView musicImageView;
        TextView musicTitle;

        public MusicHolder(View itemView) {
            super(itemView);
            musicImageView = (ImageView) itemView.findViewById(R.id.image_music);
            musicTitle = (TextView) itemView.findViewById(R.id.text_music_title);

        }
    }
}
