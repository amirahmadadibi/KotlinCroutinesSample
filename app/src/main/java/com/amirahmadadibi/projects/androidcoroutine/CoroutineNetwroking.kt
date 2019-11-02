package com.amirahmadadibi.projects.androidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_coroutine_netwroking.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class CoroutineNetwroking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_netwroking)
        button.setOnClickListener {
            GlobalScope.launch(Default) {
                makeCall()
            }
        }
    }


    suspend fun makeCall(){
        var client = OkHttpClient()
        val request = Request.Builder().
            url("https://jsonplaceholder.typicode.com/todos/1")
            .build()

        val response = client.newCall(request).execute()
        Log.d("tagxx", response.body!!.string())

        withContext(Main){
            Toast.makeText(this@CoroutineNetwroking,"done",Toast.LENGTH_SHORT).show()
        }

    }
}