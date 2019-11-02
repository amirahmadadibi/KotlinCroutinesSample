package com.amirahmadadibi.projects.androidcoroutine;

/**
 * Created by Amirahmad Adibi.
 * AndroidCoroutine | Copyrights 2019-10-30.
 */


interface onGeekEventListeneer {
    void onGeekEvent();
}

public class Synchornous {
    private onGeekEventListeneer onGeekEventListeneer;

    public void registerListener(onGeekEventListeneer onGeekEventListeneer) {
        onGeekEventListeneer.onGeekEvent();
    }

    public void doGeekStaff() {
        System.out.println("doing geek staff");
        if (onGeekEventListeneer != null) {
            onGeekEventListeneer.onGeekEvent();
        }
    }



}

class B implements onGeekEventListeneer {

    @Override
    public void onGeekEvent() {
        System.out.println("preform callback after Asynchronous operation");
    }
}
