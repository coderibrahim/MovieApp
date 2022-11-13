package com.iakbas.temaseapp.Ui.View

import Cast
import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.iakbas.temaseapp.Adapter.ActorsMovieAdapter
import com.iakbas.temaseapp.R
import com.iakbas.temaseapp.Ui.ViewModel.ViewModelActorsFragment
import com.iakbas.temaseapp.Util.MovieClickListener
import com.iakbas.temaseapp.databinding.FragmentActorsBinding


class ActorsFragment : Fragment() , MovieClickListener {

    private var viewModelActorsFragment = ViewModelActorsFragment()
    private lateinit var binding : FragmentActorsBinding
    val args: ActorsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_actors,container,false)
        val creditId = args.creditId
        viewModelActorsFragment.getDetailsData(creditId)


        binding.imageViewBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModelActorsFragment.personDetailsData.observe(viewLifecycleOwner, Observer {

            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500/" + it.person.profile_path)
                .centerCrop()
                .into(binding.imageViewActor)

            binding.textViewActorName.text = it.person.name
            binding.textViewActorBio.text = it.media.overview
        })

        viewModelActorsFragment.moviesOfPersons.observe(viewLifecycleOwner, Observer {
          val adapter = ActorsMovieAdapter(it,this)
            binding.rvByActor.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.rvByActor.adapter = adapter

        })

        return binding.root
    }

    override fun movieClickListener(cast: Cast) {
    }
}