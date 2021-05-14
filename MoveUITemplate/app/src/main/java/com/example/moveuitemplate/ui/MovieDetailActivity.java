package com.example.moveuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.DienVienAdapter;
import com.example.moveuitemplate.models.MovieModel;
import com.example.moveuitemplate.models.dienvien;
import com.example.moveuitemplate.models.movie;
import com.example.moveuitemplate.request.Servicey;
import com.example.moveuitemplate.response.Caster;
import com.example.moveuitemplate.response.MovieSearchResponse;
import com.example.moveuitemplate.utils.MovieApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView Rv_dienvien;
    private DienVienAdapter dienVienAdapter;
    private List<dienvien> cast = new ArrayList<>();


    String movieTitle = "";
    String imageResourceId = "";
    String imageCover = "";
    String overViewMovie = "";
    String movie_API = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //khởi tạo view
        initView();



    }

    void initView(){
        Rv_dienvien = findViewById(R.id.rv_dienvien);
        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        String imageResourceId = getIntent().getExtras().getString("imgURL");
        String imageCover = getIntent().getExtras().getString("imgCover");
        String overViewMovie = getIntent().getExtras().getString("description");
        String movie_API = getIntent().getExtras().getString("API");


//        Intent intent = getIntent();
//        movieTitle = intent.getStringExtra("title2");
//        imageResourceId = intent.getStringExtra("imgURL2");
//        imageCover = intent.getStringExtra("imgCover2");
//        overViewMovie = intent.getStringExtra("description2");
//        movie_API = intent.getStringExtra("API2");


        GetRetrofitResponseCaster(movie_API);
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + imageResourceId).into(MovieThumbnailImg);

        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + imageCover).into(MovieCoverImg);

        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);

        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(overViewMovie);


        //Cài đặt animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));



    }

    //Hàm show diễn viên
    void setupRvCast(){
        dienVienAdapter = new DienVienAdapter(this, cast);
        Rv_dienvien.setAdapter(dienVienAdapter);
        Rv_dienvien.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
    private void GetRetrofitResponseCaster(String movie_API) {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<Caster> responseCall = movieApi.getMovieDetail(movie_API);
        responseCall.enqueue(new Callback<Caster>() {
            @Override
            public void onResponse(Call<Caster> call, Response<Caster> response) {
                if (response.code() == 200){
                    Map<String,List<Map<String,String>>> casters = response.body().getCast();
                    //casters.get("cast")
                    for (Map<String,String> info: casters.get("cast")) {
                        dienvien castInfo = new dienvien(info.get("name"),info.get("profile_path"));
                        cast.add(castInfo);

                    }

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
                setupRvCast();
            }

            @Override
            public void onFailure(Call<Caster> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}