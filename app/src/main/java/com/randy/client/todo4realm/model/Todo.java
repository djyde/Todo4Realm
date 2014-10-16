package com.randy.client.todo4realm.model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by randy on 14-10-12.
 */
public class Todo extends RealmObject{
    private String title;
    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
