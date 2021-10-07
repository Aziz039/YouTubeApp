package com.example.youtubeapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener




class MainActivity : AppCompatActivity() {
    private lateinit var clMain: ConstraintLayout
    private lateinit var player: YouTubePlayer
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var rv_videos: RecyclerView

    val youtubeVideosIDs = arrayOf(
        arrayOf("Orientation", "Ik4tMWQiSL4"),
        arrayOf("Week1 Day1", "6fvXhCffLaY"),
        arrayOf("Week1 Day2", "VZnLB-q55tw"),
        arrayOf("Week1 Day3", "pESK3Vp_pCY"),
        arrayOf("Week1 Day4", "YC1uPCY3_-0")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clMain = findViewById(R.id.clMain)
        rv_videos = findViewById(R.id.rv_videos)
        checkInternet()
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        getLifecycle().addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(youtubeVideosIDs[0][1], 0f)
                player.pause()
                initializeRV()
            }
        })

        Toast.makeText(clMain.context, "Click on the video to start..", Toast.LENGTH_LONG).show()
    }

    private fun initializeRV(){
        val recyclerView: RecyclerView = findViewById(R.id.rv_videos)
        recyclerView.adapter = RecyclerAdapter(youtubeVideosIDs, player)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun checkInternet(){
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if(activeNetwork?.isConnectedOrConnecting != true){
            Toast.makeText(clMain.context, "Connect to the internet!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(clMain.context, "Connected..", Toast.LENGTH_LONG).show()
        }
    }
}