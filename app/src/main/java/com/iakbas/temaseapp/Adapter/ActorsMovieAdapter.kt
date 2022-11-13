package com.iakbas.temaseapp.Adapter

import Cast
import CastMovie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.Util.MovieClickListener
import kotlinx.android.synthetic.main.movie_row.view.imageViewMovieRow
import kotlinx.android.synthetic.main.movie_row.view.textViewMovieName
import kotlinx.android.synthetic.main.movie_row.view.textViewMovieRankRow

class ActorsMovieAdapter(private val arrayMovie : List<CastMovie>, private val clickListener: MovieClickListener ) : RecyclerView.Adapter<ActorsMovieAdapter.rvViewHolder>()  {

    inner class rvViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row,parent,false)
        return  rvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayMovie.size
    }

    override fun onBindViewHolder(holder: rvViewHolder, position: Int) {

        var objectMovie = arrayMovie.get(position)

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/" + objectMovie.poster_path)
            .centerCrop()
            .into(holder.itemView.imageViewMovieRow)

        holder.itemView.textViewMovieName.text = objectMovie.title
        holder.itemView.textViewMovieRankRow.text = objectMovie.vote_average.toString()
    }
}