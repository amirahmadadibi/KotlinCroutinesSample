package com.amirahmadadibi.projects.androidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext


class FileActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Main
    lateinit var textFileContext: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        textFileContext = ""
        buttonCoroutine.setOnClickListener {
            val a = CoroutineScope(IO).async {
                Log.i("TAG start", "Starting...")

                val one = async { numberOne() }
                val two = async { numberTwo() }
                val three = async { numberThree() }

                return@async one.await() + two.await() + three.await()
            }

            launch {
                buttonCoroutine.text = a.await().toString()
            }

        }

    }

    private suspend fun numberOne(): Int {
        delay(500)
        return 2
    }

    private suspend fun numberTwo(): Int {
        delay(2000)
        return 3
    }

    private suspend fun numberThree(): Int {
        delay(1000)
        return 4
    }


    private fun makeCall(): String {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos/1")
            .build()

        val response = client.newCall(request).execute()
        return response.body!!.string()
    }

}
