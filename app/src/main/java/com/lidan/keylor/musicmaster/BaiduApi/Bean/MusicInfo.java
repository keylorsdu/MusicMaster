package com.lidan.keylor.musicmaster.BaiduApi.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by keylor on 15-7-9.
 */
public class MusicInfo implements Parcelable{
    String artist_id;// 57520;
    String language;// 国语;
    String pic_big;// http;//;//c.hiphotos.baidu.com/ting/pic/item/b151f8198618367a863be6502b738bd4b21ce589.jpg;
    String pic_small;// http;//;//a.hiphotos.baidu.com/ting/pic/item/4bed2e738bd4b31ce68e0d3682d6277f9f2ff889.jpg;
    String country;// 内地;
    String area;// 0;
    String publishtime;// 2015-06-21;
    String album_no;// 1;
    String lrclink;// http;//;//musicdata.baidu.com/data2/lrc/242591350/%E5%B0%8F%E6%B0%B4%E6%9E%9C.lrc;
    String copy_type;// 1;
    String hot;// 1677888;
    String all_artist_ting_uid;// 9295;
    String resource_type;// 0;
    String is_new;// 0;
    String rank_change;// 0;
    String rank;// 1;
    String all_artist_id;// 57520;
    String style;// 流行;
    String del_status;// 0;
    String relate_status;// 0;
    String toneid;// 0;
    String all_rate;// flac;320;256;192;128;64;24;
    String sound_effect;// 0;
    String file_duration;// 198;
    int has_mv_mobile;// 0;
    String song_id;// 242137130;
    String title;// 小水果;
    String ting_uid;// 9295;
    String author;// 筷子兄弟;
    String album_id;// 242137150;
    String album_title;// 小水果;
    int is_first_publish;// 0;
    int havehigh;// 2;
    int charge;// 0;
    int has_mv;// 1;
    int learn;// 0;
    String song_source;// web;
    String piao_id;// 0;
    String korean_bb_song;// 0;
    String resource_type_ext;// 0;
    String artist_name;// 筷子兄弟

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public String getLanguage() {
        return language;
    }

    public String getPic_big() {
        return pic_big;
    }

    public String getPic_small() {
        return pic_small;
    }

    public String getCountry() {
        return country;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public String getArea() {
        return area;
    }

    public String getAlbum_no() {
        return album_no;
    }

    public String getLrclink() {
        return lrclink;
    }

    public String getCopy_type() {
        return copy_type;
    }

    public String getHot() {
        return hot;
    }

    public String getAll_artist_ting_uid() {
        return all_artist_ting_uid;
    }

    public String getIs_new() {
        return is_new;
    }

    public String getResource_type() {
        return resource_type;
    }

    public String getRank_change() {
        return rank_change;
    }

    public String getRank() {
        return rank;
    }

    public String getAll_artist_id() {
        return all_artist_id;
    }

    public String getStyle() {
        return style;
    }

    public String getDel_status() {
        return del_status;
    }

    public String getRelate_status() {
        return relate_status;
    }

    public String getToneid() {
        return toneid;
    }

    public String getAll_rate() {
        return all_rate;
    }

    public String getSound_effect() {
        return sound_effect;
    }

    public String getFile_duration() {
        return file_duration;
    }

    public int getHas_mv_mobile() {
        return has_mv_mobile;
    }

    public String getSong_id() {
        return song_id;
    }

    public String getTitle() {
        return title;
    }

    public String getTing_uid() {
        return ting_uid;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public int getIs_first_publish() {
        return is_first_publish;
    }

    public int getHavehigh() {
        return havehigh;
    }

    public int getCharge() {
        return charge;
    }

    public int getHas_mv() {
        return has_mv;
    }

    public int getLearn() {
        return learn;
    }

    public String getSong_source() {
        return song_source;
    }

    public String getPiao_id() {
        return piao_id;
    }

    public String getKorean_bb_song() {
        return korean_bb_song;
    }

    public String getResource_type_ext() {
        return resource_type_ext;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String getArtist_id() {

        return artist_id;
    }
}
