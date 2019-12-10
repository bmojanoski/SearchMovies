package com.example.moviesapkfinal;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesapkfinal.Models.Movie;
import com.example.moviesapkfinal.db.MovieDatabase;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView poster;
    TextView title,  year, type;
    private String  mtitle, mimdbId, myear, mtype,mImg;

    private  MovieDatabase movieDatabase;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        poster = findViewById(R.id.img_details);
        title = findViewById(R.id.title_details);
        year = findViewById(R.id.year_details);
        type = findViewById(R.id.type);

        movieDatabase = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class,"movie_database").allowMainThreadQueries().build();

        Intent intent = getIntent();

        mimdbId = intent.getStringExtra("imdbID");
        Movie movie = movieDatabase.movieDao().getMovie(mimdbId);

        mtitle = movie.getTitle();
        myear = movie.getYear();
        mtype = movie.getType();
        mImg = movie.getPoster();

        title.setText("Movie title: " + mtitle);

        year.setText("Release date: " + myear);
        type.setText("Type: " + mtype);
        RequestOptions requestOptions = new RequestOptions();
        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(poster);
    }

}
