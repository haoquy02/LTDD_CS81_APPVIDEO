package com.example.moveuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moveuitemplate.R;
import com.example.moveuitemplate.adapters.DienVienAdapter;
import com.example.moveuitemplate.models.dienvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView Rv_dienvien;
    private DienVienAdapter dienVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //khởi tạo view
        initView();
        setupRvCast();


    }

    void initView(){
        Rv_dienvien = findViewById(R.id.rv_dienvien);
        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);

        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);

        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieCoverImg);

        tv_title = findViewById(R.id.detail_movie_title);

        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);

        tv_description = findViewById(R.id.detail_movie_desc);

        //Cài đặt animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

    }

    void setupRvCast(){

        List<dienvien> mdata = new ArrayList<>();
        mdata.add(new dienvien("name", R.drawable.doraemon));
        mdata.add(new dienvien("name", R.drawable.nobita));
        mdata.add(new dienvien("name", R.drawable.shizuka));
        mdata.add(new dienvien("name", R.drawable.chaien));
        mdata.add(new dienvien("name", R.drawable.xeco));

        dienVienAdapter = new DienVienAdapter(this, mdata);
        Rv_dienvien.setAdapter(dienVienAdapter);
        Rv_dienvien.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }


}