package com.amirahmadadibi.projects.androidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCo.setOnClickListener {
            Log.d("tagxx", "onClick : launched Coroutine")
            CoroutineScope(IO).launch {
                getResultOneFromApi()
            }
        }
    }

    private suspend fun fakeAPi():String{
        return getResultOneFromApi()
    }

    private suspend fun getResultOneFromApi():String {
        i++
        delay(1000)

        withContext(Main) {
            textViewStatus.text = "job one done! # $i"
        }

        getResultTwoFromApi()
        i++
        withContext(Main){
            textViewStatus.text  = "job two done! # $i"
        }

        return "#Job number one done!"
    }

    private suspend fun getResultTwoFromApi() {
        delay(2000)
    }

}
