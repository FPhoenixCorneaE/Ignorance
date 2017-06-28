package com.livelearn.ignorance.eventbusevent;

/**
 * Created on 2017/2/14.
 */

public class BookClassEvent {

    private int position;

    public BookClassEvent() {
    }

    public BookClassEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public BookClassEvent setPosition(int position) {
        this.position = position;
        return this;
    }
}
