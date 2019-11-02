package com.amirahmadadibi.projects.androidcoroutine;

/**
 * Created by Amirahmad Adibi.
 * AndroidCoroutine | Copyrights 2019-10-30.
 */
public class TestSynchronous {

    public static void main(String[] args) {
        Synchornous synchornous = new Synchornous();
        onGeekEventListeneer listeneer = new B();
        synchornous.registerListener(listeneer);
        synchornous.doGeekStaff();
    }
}
