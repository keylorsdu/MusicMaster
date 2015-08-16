package com.lidan.keylor.musicmaster.common;

import com.squareup.otto.Bus;

/**
 * Created by keylorlidan on 2015/8/16.
 */
public class BusProvider {
    static Bus  bus = new Bus();

    public static Bus getBus() {
        if (bus == null) {
            return new Bus();
        }
        return bus;
    }
}
