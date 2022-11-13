package com.iakbas.temaseapp.Ui.ViewModel

import MovieBaseModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iakbas.temaseapp.Service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ViewModelHomeFragment : ViewModel() {

    private lateinit var compositeDisposable: CompositeDisposable
    var arrayListPopularMovies = MutableLiveData<MovieBaseModel>()
    var arrayListNowPlayingMovies = MutableLiveData<MovieBaseModel>()
    var arrayListUpComingMovies = MutableLiveData<MovieBaseModel>()

    fun getAllPopularMovies(){

        val retrofitService = RetrofitService()
        compositeDisposable = CompositeDisposable()


        compositeDisposable.add(
            retrofitService.getAllMovies("3")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieBaseModel>(){
                    override fun onSuccess(t: MovieBaseModel) {

                        if(t != null){
                            arrayListPopularMovies.value = t
                        }else{
                            Log.d("error","Not array Found")
                        }

                    }

                    override fun onError(e: Throwable) {
                        Log.d("error","Error Found on Popular Movies")
                    }

                })
        )
    }

    fun getAllNowPlayingMovies(){

        val retrofitService = RetrofitService()
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            retrofitService.getAllNowPlayingMovies("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieBaseModel>(){
                    override fun onSuccess(t: MovieBaseModel) {

                        if(t != null){
                            arrayListNowPlayingMovies.value = t
                        }else{
                            Log.d("error","Not array Found")
                        }

                    }

                    override fun onError(e: Throwable) {
                        Log.d("error","Error Found on Popular Movies")
                    }

                })
        )


    }

    fun getAllUpComingMovies(){

        val retrofitService = RetrofitService()
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            retrofitService.getAllUpComingMovies("2")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieBaseModel>(){
                    override fun onSuccess(t: MovieBaseModel) {

                        if(t != null){
                            arrayListUpComingMovies.value = t
                        }else{
                            Log.d("error","Not array Found")
                        }

                    }

                    override fun onError(e: Throwable) {
                        Log.d("error","Error Found on Popular Movies")
                    }

                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}