package com.iakbas.temaseapp.Service

import MovieBaseModel
import MovieDetails
import PersonBaseModel
import PersonDetailsBaseModel
import PersonMovieBaseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("3/movie/popular")
    fun getAllPopularMovies(@Query("api_key") apiKey : String , @Query("language") language : String , @Query("page") page : String) : Single<MovieBaseModel>

    @GET("3/movie/now_playing")
    fun getAllNowPlayingMovies(@Query("api_key") apiKey : String , @Query("language") language : String , @Query("page") page : String) : Single<MovieBaseModel>

    @GET("3/movie/upcoming")
    fun getAllUpComingMovies(@Query("api_key") apiKey : String , @Query("language") language : String , @Query("page") page : String) : Single<MovieBaseModel>

    @GET("3/search/movie")
    fun searchMovies(@Query("api_key") apiKey : String , @Query("language") language : String , @Query("page") page : String,@Query("query") query : String) : Single<MovieBaseModel>

    @GET("3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movie_id : String , @Query("api_key") apiKey: String ,@Query("language") language: String) : Single<MovieDetails>

    @GET("3/movie/{movie_id}/credits")
    fun getPersonData(@Path("movie_id") movie_id: String , @Query("api_key") apiKey : String) : Single<PersonBaseModel>

    @GET("3/credit/{credit_id}")
    fun getPersonDetails(@Path("credit_id") creditId : String ,@Query("api_key") apiKey: String) : Single<PersonDetailsBaseModel>

    @GET("3/person/{player_id}/movie_credits")
    fun getPersonsMovies(@Path("player_id") playerId : String , @Query("api_key") apiKey: String) : Single<PersonMovieBaseModel>
}