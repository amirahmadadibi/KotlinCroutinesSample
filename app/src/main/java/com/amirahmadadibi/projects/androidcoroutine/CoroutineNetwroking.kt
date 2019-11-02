package com.amirahmadadibi.projects.androidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_coroutine_netwroking.*
import okhttp3.OkHttpClient
import okhttp3.Request

class CoroutineNetwroking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_netwroking)
        button.setOnClickListener {
            makeCall()
        }
    }


    fun makeCall(){
        var client = OkHttpClient()
        val request = Request.Builder().
            url("https://jsonplaceholder.typicode.com/todos/1")
            .build()

        val response = client.newCall(request).execute()
        Log.d("tagxx", response.body!!.string())

    }
}
§