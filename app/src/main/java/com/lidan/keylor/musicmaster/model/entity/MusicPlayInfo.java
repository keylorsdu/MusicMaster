
package com.lidan.keylor.musicmaster.model.entity;

import java.util.HashMap;
import java.util.Map;


public class MusicPlayInfo {

    private Integer error_code;
    private Bitrate bitrate;
    private Songinfo songinfo;
    private Map<String, Object> additional_roperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return error_code + bitrate.toString()+songinfo;
    }


    public Songinfo getSonginfo() {
        return songinfo;
    }

    public Bitrate getBitrate() {
        return bitrate;
    }
}
