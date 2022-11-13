package com.iakbas.temaseapp.Adapter

import Results
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.RecyclerViewClickListener
import com.iakbas.temaseapp.Util.MovieClickListener
import kotlinx.android.synthetic.main.movie_row.view.imageViewMovieRow
import kotlinx.android.synthetic.main.movie_row.view.textViewMovieName
import kotlinx.android.synthetic.main.movie_row.view.textViewMovieRankRow

class MovieRowAdapter(private val movieArray: List<Results>, var clickListener: RecyclerViewClickListener) : RecyclerView.Adapter<MovieRowAdapter.MovieRowViewHolder>() {

    inner class MovieRowViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRowViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row,parent,false)


        /*val binding: MovieRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_row, parent, false
        )*/

        return MovieRowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieArray.size
    }

    override fun onBindViewHolder(holder: MovieRowViewHolder, position: Int) {
        MovieRowViewHolder(holder.itemView)

        val movieObject = movieArray.get(position)

        holder.itemView.textViewMovieName.text = movieObject.title
        holder.itemView.textViewMovieRankRow.text = movieObject.vote_average.toString()
        Glide
            .with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/" + movieObject.backdrop_path)
            .centerCrop()
            .into(holder.itemView.imageViewMovieRow);

        holder.itemView.setOnClickListener {
            clickListener.clickListener(movieObject.id)
        }

    }



}