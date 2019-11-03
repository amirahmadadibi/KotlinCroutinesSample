package com.amirahmadadibi.projects.androidcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_coroutine_job.*
import kotlinx.android.synthetic.main.activity_coroutine_netwroking.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CoroutineJobActivity : AppCompatActivity() {
    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000 //ms
    private var indexRun = 0
    private lateinit var job: CompletableJob//this object is initialized when it's needed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_job)
        buttonLaunchJob.setOnClickListener {
            if (!::job.isInitialized) {
                initJob()
            }
            progressBar.startJobOrCancel(job)
        }
    }

    fun initJob() {
        buttonLaunchJob.text = "Started Job#$indexRun"
        textViewStatus.text = ""
        job = Job()
        //either job is complete or cancel
        job.invokeOnCompletion { exception ->
            exception?.message.let {
                var message: String
                if (it.isNullOrBlank()) {
                    message = "unknown error"
                } else {
                    message = it
                }
                println(message)
            }
        }
        progressBar.max = PROGRESS_MAX
        progressBar.progress = PROGRESS_START
    }

    fun ProgressBar.startJobOrCancel(job: Job) {
        if (this.progress > 0) {
            showToast("${job} is already active. Cancelling... ")
            resetJob()
        } else {
            buttonLaunchJob.text = "Cancel"
            CoroutineScope(IO + job).launch {
                println("coroutine ${this} is activated with job #${job}")
                for (i in PROGRESS_START..PROGRESS_MAX) {
                    delay((JOB_TIME / PROGRESS_MAX).toLong())
                    this@startJobOrCancel.progress = i
                }

                withContext(Main) {
                    textViewStatus.text = "job is complete!"
                }
            }

        }
    }

    private fun resetJob() {
        if(job.isActive || job.isCompleted){
            //cancel or completion of the job
            job.cancel(CancellationException("Resstin Job"))
        }
        initJob()
    }

    fun showToast(text: String) {
        GlobalScope.launch(Main) {
            Toast.makeText(this@CoroutineJobActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}
