package com.example.moveuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.moveuitemplate.adapters.MoviAdapter;
import com.example.moveuitemplate.adapters.MovieItemClickListener;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.SlidePagerAdapter;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.movie;
import com.example.moveuitemplate.models.slide;
import com.example.moveuitemplate.request.Servicey;
import com.example.moveuitemplate.response.MovieSearchResponse;
import com.example.moveuitemplate.utils.DataSource;
import com.example.moveuitemplate.utils.MovieApi;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moveuitemplate.utils.DataSource.getPopularMovies;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<slide> lstSlides;
    private ViewPager sliderpages;
    private TabLayout indicator;
    private RecyclerView MoviesRV, movieRVWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        iniSlider();
        GetRetrofitResponseTop();
        GetRetrofitResponsePopular();

    }

    private void iniWeekMovies(List<MovieModel> movies) {

        MoviAdapter weekMoviAdapter = new MoviAdapter(this, DataSource.getWeeMovies(movies), this);
        movieRVWeek.setAdapter(weekMoviAdapter);
        movieRVWeek.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }

    private void iniPopularMovies(List<MovieModel> movies) {
        //RecyclerView
        //init data


        MoviAdapter moviAdapter = new MoviAdapter(this, getPopularMovies(movies), this);
        MoviesRV.setAdapter(moviAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void iniSlider() {
        //prepare a list of slide
        lstSlides = new ArrayList<>();
        lstSlides.add(new slide(R.drawable.anh1, "The Avergers : Endgame"));
        lstSlides.add(new slide(R.drawable.anh2, "Thor 2 : The Dark World "));
        lstSlides.add(new slide(R.drawable.anh3, "Venom 2"));
        lstSlides.add(new slide(R.drawable.anh4, "Fast and Furiour : Hobbs & Shaw "));

        SlidePagerAdapter adapter = new SlidePagerAdapter(this, lstSlides);

        sliderpages.setAdapter(adapter);

        //Cài đặt thời gian
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        indicator.setupWithViewPager(sliderpages, true);
    }

    private void iniViews() {
        sliderpages = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        movieRVWeek = findViewById(R.id.rv_movies_week);
    }

    @Override
    public void onMovieClick(movie movie, ImageView movieImageView) {
        //Hàm đưa ra thông tin chi tiết của phim

        Intent intent = new Intent(this, MovieDetailActivity.class);
        //Gửi thông tin film đến detailActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        intent.putExtra("description", movie.getDescription());
        intent.putExtra("API", movie.getMovieID());

        //Tạo animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, movieImageView, "sharedName"
        );

        startActivity(intent, options.toBundle());
        //Test nếu click hoạt động



    }

    //Chuyển trang theo thời gian
    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpages.getCurrentItem() < lstSlides.size()- 1){
                        sliderpages.setCurrentItem(sliderpages.getCurrentItem()+1);
                    } else {
                        sliderpages.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void GetRetrofitResponsePopular() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getPopularMovie();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    iniPopularMovies(movies);
                }
                else
                {
                    try {
                        Log.v("Tag","Error" + response.errorBody().string());
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void GetRetrofitResponseTop() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.getTop();
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    iniWeekMovies(movies);
                }
                else
                {
                    try {
                        Log.v("Tag","Error" + response.errorBody().string());
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}