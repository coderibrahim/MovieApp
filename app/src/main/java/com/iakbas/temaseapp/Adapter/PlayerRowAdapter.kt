package com.iakbas.temaseapp.Adapter

import Cast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.Util.MovieClickListener
import kotlinx.android.synthetic.main.players_row.view.cardViewPlayer
import kotlinx.android.synthetic.main.players_row.view.imageViewPlayer
import kotlinx.android.synthetic.main.players_row.view.textViewActorssName

class PlayerRowAdapter (private val arrayPlayer : List<Cast>, private val clickListener: MovieClickListener ) : RecyclerView.Adapter<PlayerRowAdapter.PlayerRowViewHolder>() {

    inner class PlayerRowViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.players_row,parent,false)
        return PlayerRowViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerRowViewHolder, position: Int) {
        PlayerRowViewHolder(holder.itemView)
        val personObject = arrayPlayer.get(position)

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/" + personObject.profile_path)
            .centerCrop()
            .into(holder.itemView.imageViewPlayer)

        holder.itemView.textViewActorssName.text = personObject.name

        holder.itemView.cardViewPlayer.setBackgroundColor(R.color.primary_color)

        holder.itemView.setOnClickListener {

            clickListener.movieClickListener(personObject)

        }
    }

    override fun getItemCount(): Int {
        return arrayPlayer.size
    }
}