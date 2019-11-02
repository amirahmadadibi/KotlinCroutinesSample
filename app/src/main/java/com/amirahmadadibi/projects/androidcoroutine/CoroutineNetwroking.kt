package com.amirahmadadibi.projects.androidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_coroutine_netwroking.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext

class CoroutineNetwroking() : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    //makeing a wo
    override val coroutineContext: CoroutineContext
        get() = Main + job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_netwroking)
        job = Job()
        button.setOnClickListener {
            val a = GlobalScope.async(IO) {
                makeCall()
            }

            launch {

            }

        }
        //create an independent scope and doing the work
        CoroutineScope(IO).launch {

        }
    }


    suspend fun makeCall(): String {
        var client = OkHttpClient()
        val request = Request.Builder().url("https://jsonplaceholder.typicode.com/todos/1")
            .build()

        val response = client.newCall(request).execute()

        withContext(Main) {
            Toast.makeText(this@CoroutineNetwroking, "done", Toast.LENGTH_SHORT).show()
        }
        return response.body!!.string()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}