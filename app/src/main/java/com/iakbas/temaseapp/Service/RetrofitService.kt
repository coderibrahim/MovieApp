package com.iakbas.temaseapp.Service

import MovieBaseModel
import MovieDetails
import PersonBaseModel
import PersonDetailsBaseModel
import PersonMovieBaseModel
import com.iakbas.temaseapp.Util.ServicesBaseUrls
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {


    fun retrofit(): ServiceApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(ServicesBaseUrls.BASE_URL_MOVİES)
            .build()
            .create(ServiceApi::class.java)

        return retrofit
    }

    fun getAllMovies(page : String): Single<MovieBaseModel> {
        return retrofit().getAllPopularMovies(ServicesBaseUrls.APİ_KEY,"en-US",page)
    }

    fun getAllNowPlayingMovies(page : String): Single<MovieBaseModel> {
        return retrofit().getAllNowPlayingMovies(ServicesBaseUrls.APİ_KEY,"en-US",page)
    }

    fun getAllUpComingMovies(page : String): Single<MovieBaseModel> {
        return retrofit().getAllUpComingMovies(ServicesBaseUrls.APİ_KEY,"en-US",page)
    }

    fun searchMovies(page : String , query : String) : Single<MovieBaseModel>{
        return retrofit().searchMovies(ServicesBaseUrls.APİ_KEY,"en-US",page,query)
    }

    fun movieDetails(movieId : String) : Single<MovieDetails>{
        return retrofit().getMovieDetails(movieId,ServicesBaseUrls.APİ_KEY,"en-US")
    }

    fun personData(movieId : String) : Single<PersonBaseModel>{
        return retrofit().getPersonData(movieId,ServicesBaseUrls.APİ_KEY)
    }

    fun personDetailsData(creditId : String) : Single<PersonDetailsBaseModel>{
        return retrofit().getPersonDetails(creditId,ServicesBaseUrls.APİ_KEY)
    }

    fun personsMovies(playerId : String) : Single<PersonMovieBaseModel>{
        return retrofit().getPersonsMovies(playerId,ServicesBaseUrls.APİ_KEY)
    }



}