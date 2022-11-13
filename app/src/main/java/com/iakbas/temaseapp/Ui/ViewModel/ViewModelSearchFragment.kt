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

class ViewModelSearchFragment : ViewModel() {

    var searchArrayList = MutableLiveData<MovieBaseModel>()
    private lateinit var compositDisposable : CompositeDisposable


    fun getSearchMovies(query : String){

        compositDisposable = CompositeDisposable()

        val retrofitService = RetrofitService()

        compositDisposable.add(
            retrofitService.searchMovies("1",query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieBaseModel>(){
                    override fun onSuccess(t: MovieBaseModel) {
                        searchArrayList.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.d("errordisposable",e.localizedMessage.toString())
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        compositDisposable.clear()
    }



}