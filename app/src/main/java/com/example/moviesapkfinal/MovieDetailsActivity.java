package com.example.moviesapkfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView poster;
    TextView title, imdbId, year, type;
    private String  mtitle, mimdbId, myear, mtype,mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        poster = findViewById(R.id.img_details);
        title = findViewById(R.id.title_details);
        imdbId = findViewById(R.id.imdbID);
        year = findViewById(R.id.year_details);
        type = findViewById(R.id.type);

        Intent intent = getIntent();
        mtitle = intent.getStringExtra("title");
        mimdbId = intent.getStringExtra("imdbID");
        myear = intent.getStringExtra("Type");
        mtype = intent.getStringExtra("Year");
        mImg = intent.getStringExtra("img");

        title.setText(mtitle);
        imdbId.setText(mimdbId);
        year.setText(myear);
        type.setText(mtype);
        RequestOptions requestOptions = new RequestOptions();

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(poster);
    }

}
