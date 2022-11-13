package com.iakbas.temaseapp.Ui.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.iakbas.temaseapp.Adapter.MovieRowAdapter
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.RecyclerViewClickListener
import com.iakbas.temaseapp.Ui.ViewModel.ViewModelHomeFragment
import com.iakbas.temaseapp.Util.MovieClickListener
import com.iakbas.temaseapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() , RecyclerViewClickListener{

    private lateinit var binding : FragmentHomeBinding
    private var viewModelHomeFragment = ViewModelHomeFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        viewVisibility(true)

        viewModelHomeFragment.getAllPopularMovies()
        viewModelHomeFragment.getAllNowPlayingMovies()
        viewModelHomeFragment.getAllUpComingMovies()

        viewModelHomeFragment.arrayListPopularMovies.observe(viewLifecycleOwner, Observer {
            viewVisibility(false)
            val rvAdapter = MovieRowAdapter(it.results,this)
            binding.rvPopularMovies.adapter = rvAdapter
            binding.rvPopularMovies.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        })

        viewModelHomeFragment.arrayListNowPlayingMovies.observe(viewLifecycleOwner, Observer {
            val rvAdapter = MovieRowAdapter(it.results,this)
            binding.rvOnTv.adapter = rvAdapter
            binding.rvOnTv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        })

        viewModelHomeFragment.arrayListUpComingMovies.observe(viewLifecycleOwner, Observer {
            val rvAdapter = MovieRowAdapter(it.results,this)
            binding.soonOnTv.adapter = rvAdapter
            binding.soonOnTv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        })

        binding.imageViewButton.setOnClickListener {
            val text = binding.editTextSearchMovie.text
            if(text.equals("")){
                Toast.makeText(requireContext(),"Search Input must not be null", Toast.LENGTH_LONG).show()
            }else{
                val action : NavDirections = HomeFragmentDirections.actionHomeFragmentToSearchFragment(text.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModelHomeFragment.getAllPopularMovies()
        viewModelHomeFragment.getAllNowPlayingMovies()
        viewModelHomeFragment.getAllUpComingMovies()
    }

    fun viewVisibility(visibility: Boolean){
        if(visibility)
        {
            binding.progressBar.visibility = View.VISIBLE
            binding.textView.visibility = View.GONE
            binding.textView2.visibility = View.GONE
            binding.textView3.visibility = View.GONE
        }else{
            binding.progressBar.visibility = View.GONE
            binding.textView.visibility = View.VISIBLE
            binding.textView2.visibility = View.VISIBLE
            binding.textView3.visibility = View.VISIBLE
        }
    }

    override fun clickListener(movieId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movieId.toString())
        Navigation.findNavController(binding.root).navigate(action)

    }
}