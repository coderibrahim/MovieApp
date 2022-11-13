package com.iakbas.temaseapp.Ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.iakbas.temaseapp.Adapter.MovieRowAdapter
import com.iakbas.temaseapp.Adapter.SearchMovieAdapter
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.RecyclerViewClickListener
import com.iakbas.temaseapp.Ui.ViewModel.ViewModelSearchFragment
import com.iakbas.temaseapp.databinding.FragmentSearchBinding


class SearchFragment : Fragment() , RecyclerViewClickListener {

    private lateinit var binding : FragmentSearchBinding
    private var viewModelSearchFragment = ViewModelSearchFragment()
    val args: SearchFragmentArgs by navArgs()
    private lateinit var navigationController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        viewModelSearchFragment = ViewModelSearchFragment()
        val searchQuery = args.searchKey
        binding.editTextSearchMovie.setText(searchQuery)
        viewModelSearchFragment.getSearchMovies(searchQuery)

        viewModelSearchFragment.searchArrayList.observe(viewLifecycleOwner, Observer {
            binding.rvSearchFragment.layoutManager = LinearLayoutManager(requireContext())
            if(it != null){
                val rvAdapter = SearchMovieAdapter(it.results,this)
                binding.rvSearchFragment.adapter = rvAdapter
            }
        })
        navigationController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)


        binding.imageViewSearchBtn.setOnClickListener {
            val searchText = binding.editTextSearchMovie.text
            viewModelSearchFragment.getSearchMovies(searchText.toString())
        }

        binding.imageViewBackButton.setOnClickListener {
            NavigationUI.navigateUp(navigationController,null)
            binding.editTextSearchMovie.setText("")
        }

        return binding.root
    }

    override fun clickListener(movieId: Int) {
        println(movieId.toString())
        val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movieId.toString())
        Navigation.findNavController(binding.root).navigate(action)
        binding.editTextSearchMovie.setText("")
    }

}