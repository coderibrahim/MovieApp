package com.iakbas.temaseapp.Adapter

import MovieBaseModel
import Results
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.RecyclerViewClickListener
import kotlinx.android.synthetic.main.movie_row.view.imageViewMovieRow
import kotlinx.android.synthetic.main.search_movie_row.view.imageViewSearch
import kotlinx.android.synthetic.main.search_movie_row.view.textViewSearchDescription
import kotlinx.android.synthetic.main.search_movie_row.view.textViewSearchImdb
import kotlinx.android.synthetic.main.search_movie_row.view.textViewSearchMovieName

class SearchMovieAdapter (val searchMovieList : List<Results> , private val searchMovieClickListener : RecyclerViewClickListener) : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    inner class SearchMovieViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_movie_row,parent,false)
        return SearchMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchMovieList.size
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        SearchMovieViewHolder(holder.itemView)

        val searchObject = searchMovieList.get(position)

        holder.itemView.textViewSearchMovieName.text = searchObject.title
        holder.itemView.textViewSearchDescription.text = searchObject.overview
        holder.itemView.textViewSearchImdb.text = searchObject.vote_average.toString()

        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/" + searchObject.poster_path)
            .centerCrop()
            .into(holder.itemView.imageViewSearch);

        holder.itemView.setOnClickListener {
            searchMovieClickListener.clickListener(searchObject.id)
        }

    }

}