package com.iakbas.temaseapp.Ui.View

import Cast
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.iakbas.temaseapp.Adapter.PlayerRowAdapter
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.RecyclerViewClickListener
import com.iakbas.temaseapp.Ui.ViewModel.ViewModelDetailsFragment
import com.iakbas.temaseapp.Util.MovieClickListener
import com.iakbas.temaseapp.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() , RecyclerViewClickListener , MovieClickListener{

    private lateinit var binding : FragmentMovieDetailsBinding
    private val args : MovieDetailsFragmentArgs by navArgs()
    private val viewModelMovieDetails = ViewModelDetailsFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)

        binding.detailsProgesBar.visibility = View.VISIBLE
        viewModelMovieDetails.getMovieDetails(args.MovieId)

        binding.imageViewBackButtonMovieDetails.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModelMovieDetails.movieDetails.observe(viewLifecycleOwner , Observer {
            if (it != null){
                binding.detailsProgesBar.visibility = View.GONE
                binding.textViewTitleDetails.text = it.title
                binding.textViewAboutDetails.text = it.overview
                binding.textViewDateDetails.text = it.release_date
                binding.textViewImdbDetails.text = it.vote_average.toString()
                binding.textViewBudgetDetails.text = "Budget : " + it.budget.toString()
                binding.textViewIncomeDetails.text = "In Come : " +  it.revenue.toString()
                 Glide.with(requireContext())
                    .load("https://image.tmdb.org/t/p/w500/" + it.backdrop_path)
                    .centerCrop()
                    .into(binding.imageViewMovieDetails)
                viewModelMovieDetails.getActorssData(it.id.toString())
            }
        })

        viewModelMovieDetails.actorss.observe(viewLifecycleOwner , Observer {
                val adapter = PlayerRowAdapter(it.cast,this)
                binding.rvPlayersDetails.adapter = adapter
                binding.rvPlayersDetails.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        })

        return binding.root
    }

    override fun clickListener(movieId: Int) {

    }

    override fun movieClickListener(cast: Cast) {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToActorsFragment(cast.credit_id)
        findNavController().navigate(action)
    }
}