package com.amirahmadadibi.projects.androidcoroutine

import android.util.Log
import kotlinx.coroutines.*

/**
 * Created by Amirahmad Adibi.
 * AndroidCoroutine | Copyrights 2019-11-01.
 */

data class User (var username:String)

fun main() {
    GlobalScope.launch {
        println(fetchUser())
    }
}



suspend fun fetchUser(){
    //fetch user online
    withContext(Dispatchers.IO) {
        delay(3000)
        println("afeter job is done")
    }
}