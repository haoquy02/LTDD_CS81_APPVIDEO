package com.example.movieapp.utils;

import com.example.movieapp.models.MovieModel;
import com.example.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //Search for movies
    @GET("https://api.themoviedb.org/3/search/movie?api_key=cba2811f7a46c96495af2752c15b2d0c&query=query")
    Call<MovieSearchResponse> searchMovie(
            @Query ("query") String query,
            @Query ("page") int page
    );
    @GET("https://api.themoviedb.org/3/movie/{movie_id}?api_key=cba2811f7a46c96495af2752c15b2d0c")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id
    );
}
