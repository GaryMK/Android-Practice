package bjfu.it.mukaikai.stopwatch

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var seconds:Int = 0
    var running:Boolean = false
    var wasRunning:Boolean = false
    val TAG:String = "life cycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        val btnStart:Button = findViewById(R.id.button_start)
        val btnStop:Button = findViewById(R.id.button_stop)
        val btnReset:Button = findViewById(R.id.button_reset)
        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)
        btnReset.setOnClickListener(this)
        runTimer()
    }
    fun runTimer() {
        var handler:Handler = object : Handler() {}
        val timeView:TextView = findViewById(R.id.time_view)
        //每隔1000ms，重复提交该任务
        handler.post(object : Runnable{
            override fun run() {
                var hours:Int = seconds/3600
                var minutes:Int = (seconds%3600)/60
                var secs:Int = seconds%60
                var time:String = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.setText(time)
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        if (wasRunning) {
            running = true
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        running = false
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        if(wasRunning) {
            running = true
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        wasRunning = running
        running = false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("seconds", seconds)
        outState?.putBoolean("running", running)
        outState?.putBoolean("wasRunning", wasRunning)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.button_start -> {
                running = true
            }
            R.id.button_stop -> {
                running = false
            }
            R.id.button_reset -> {
                running = false
                seconds = 0
            }
            else -> return
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
