
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

    @Override
    public String toString() {
        return file_link;
    }


    public String getFileLink() {
        return file_link;
    }
}
