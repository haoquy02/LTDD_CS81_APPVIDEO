package com.example.moveuitemplate.utils;

import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.response.Caster;
import com.example.moveuitemplate.response.MovieSearchResponse;

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
    @GET("https://api.themoviedb.org/3/movie/popular?api_key=cba2811f7a46c96495af2752c15b2d0c&language=en-US&page=1")
    Call<MovieSearchResponse> getPopularMovie();
    @GET("https://api.themoviedb.org/3/movie/top_rated?api_key=cba2811f7a46c96495af2752c15b2d0c&language=en-US&page=1")
    Call<MovieSearchResponse> getTop();
    @GET("https://api.themoviedb.org/3/movie/460465?api_key=cba2811f7a46c96495af2752c15b2d0c&append_to_response=credits")
    Call<Caster> getMovieDetail(
    );
}
