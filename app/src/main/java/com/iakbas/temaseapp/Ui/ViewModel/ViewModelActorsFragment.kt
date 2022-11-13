package com.iakbas.temaseapp.Ui.ViewModel

import Cast
import CastMovie
import PersonBaseModel
import PersonDetailsBaseModel
import PersonMovieBaseModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iakbas.temaseapp.Service.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ViewModelActorsFragment : ViewModel() {

    var personDetailsData = MutableLiveData<PersonDetailsBaseModel>()
    var moviesOfPersons = MutableLiveData<List<CastMovie>>()
    private var compositeDisposable = CompositeDisposable()

    fun getDetailsData(creditId : String){
        val api = RetrofitService()

        compositeDisposable.add(
            api.personDetailsData(creditId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PersonDetailsBaseModel>(){
                    override fun onSuccess(t: PersonDetailsBaseModel) {
                        personDetailsData.value = t
                        getMoviesOfActor(t.person.id.toString(),"tr-TR")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("errorPerson",e.localizedMessage)
                    }
                })
        )

    }

    fun getMoviesOfActor(playerId : String,language : String){
        val api = RetrofitService()
        compositeDisposable.add(
            api.personsMovies(playerId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PersonMovieBaseModel>(){
                    override fun onSuccess(t: PersonMovieBaseModel) {
                        moviesOfPersons.value = t.cast
                    }

                    override fun onError(e: Throwable) {

                    }
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }



}