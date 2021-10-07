package com.example.youtubeapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class RecyclerAdapter (private val youtubeVideosIDs: Array<Array<String>>,
                       yt: YouTubePlayer): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val yt = yt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(v, yt)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.bt_item.text = youtubeVideosIDs[position][0]
        holder.tv_hiddenID = youtubeVideosIDs[position][1]
    }

    override fun getItemCount()  = youtubeVideosIDs.size

    inner class ViewHolder(itemView: View, yt: YouTubePlayer): RecyclerView.ViewHolder(itemView) {
        lateinit var bt_item: Button
        lateinit var tv_hiddenID: String

        init {
            bt_item = itemView.findViewById(R.id.bt_item)
            bt_item.setOnClickListener { handleButton(bt_item, tv_hiddenID) }
        }

        private fun handleButton(bt_itemPassed: Button, tv_hiddenID: String) {
            Log.d("RecyclerAdapter", "The selected video is ${bt_itemPassed.text.toString()}")
            Log.d("RecyclerAdapter", "The ID is $tv_hiddenID")
            yt.loadVideo(tv_hiddenID, 0f)
        }
    }

}