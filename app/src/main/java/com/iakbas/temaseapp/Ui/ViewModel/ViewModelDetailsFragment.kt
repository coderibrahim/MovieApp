package com.iakbas.temaseapp.Ui.ViewModel

import Cast
import MovieDetails
import PersonBaseModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iakbas.temaseapp.Service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ViewModelDetailsFragment : ViewModel() {

    val movieDetails = MutableLiveData<MovieDetails>()
    val actorss = MutableLiveData<PersonBaseModel>()
    private val compositeDisposable = CompositeDisposable()

    fun getMovieDetails(movieId : String){
        val api = RetrofitService()
        compositeDisposable.add(
            api.movieDetails(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieDetails>(){
                    override fun onSuccess(t: MovieDetails) {
                        movieDetails.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.d("errordetails",e.localizedMessage.toString())
                    }
                })
        )
    }

    fun getActorssData(movieId: String){
        val api = RetrofitService()

        compositeDisposable.add(
            api.personData(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PersonBaseModel>(){
                    override fun onSuccess(t: PersonBaseModel) {
                        actorss.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.d("actorsserror",e.localizedMessage)
                    }
                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}