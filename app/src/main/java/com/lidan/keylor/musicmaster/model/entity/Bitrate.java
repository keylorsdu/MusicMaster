
package com.lidan.keylor.musicmaster.model.entity;

public class Bitrate {

    private Integer file_bitrate;
    private String file_link;
    private String file_extension;
    private Integer original;
    private Integer file_size;
    private Integer file_duration;
    private String show_link;
    private Integer song_file_id;
    private String replay_gain;
    private Integer free;

    public Integer getFile_bitrate() {
        return file_bitrate;
    }

    public String getFile_link() {
        return file_link;
    }

    public String getFile_extension() {
        return file_extension;
    }

    public Integer getOriginal() {
        return original;
    }

    public Integer getFile_size() {
        return file_size;
    }

    public Integer getFile_duration() {
        return file_duration;
    }

    public String getShow_link() {
        return show_link;
    }

    public Integer getSong_file_id() {
        return song_file_id;
    }

    public String getReplay_gain() {
        return replay_gain;
    }

    public Integer getFree() {
        return free;
    }

    @Override
    public String toString() {
        return file_link;
    }


    public String getFileLink() {
        return file_link;
    }
}
