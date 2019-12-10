package com.example.moviesapkfinal;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;
import com.example.moviesapkfinal.Adapters.Adapter;
import com.example.moviesapkfinal.Models.Movie;
import com.example.moviesapkfinal.ViewModels.MovieViewModel;
import com.example.moviesapkfinal.api.MappingApi.MovieResponse;
import com.example.moviesapkfinal.api.MappingApi.MovieListResponse;
import com.example.moviesapkfinal.api.MovieApiClient;
import com.example.moviesapkfinal.api.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String id = "2ac2797e";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;

    private List<MovieResponse> movieResponses = new ArrayList<>();
    private List<Movie> moviesList = new ArrayList<>();

    private MovieViewModel movieViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        LoadJson("");

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    adapter = new Adapter(movies, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    initListener(adapter);
                }
            }
        });
    }

    //calling the api and getting back info plus populating the adapter and recycler view
    public void LoadJson(final String keyword){

        MovieApi movieApi = MovieApiClient.getApiClient().create(MovieApi.class);

        String plot = "short";
        String s = "Joker";
        Call<MovieListResponse> call;
        if(keyword.length() > 0 ){
            call = movieApi.getMovie(id,plot,keyword);
        }else{
            call = movieApi.getMovie(id,plot,s);
        }

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {

                if(response.isSuccessful() && response.body().getMovieResponse() != null){
                    if(!movieResponses.isEmpty()){
                        movieResponses.clear();
                    }

                    moviesList.clear();
                    movieViewModel.deleteAll();

                    movieResponses = response.body().getMovieResponse();
                    for(MovieResponse movie : movieResponses){
                        Movie mov = new Movie(movie.getTitle(),movie.getYear(),movie.getImdbID(),movie.getType(),movie.getPoster());
                        moviesList.add(mov);
                        movieViewModel.insert(mov);
                    }
                    Toast.makeText(MainActivity.this,"Success saved!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"No result", Toast.LENGTH_SHORT).show();
                    moviesList.clear();
                    movieViewModel.deleteAll();
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"No result", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //when some item is clicked
    private void initListener(Adapter adapter){
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                Movie movie = moviesList.get(position);
                intent.putExtra("id",movie.getId());
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("imdbID", movie.getImdbID());
                intent.putExtra("Poster", movie.getPoster());
                intent.putExtra("Type", movie.getType());
                intent.putExtra("Year", movie.getYear());
                intent.putExtra("img", movie.getPoster());
                startActivity(intent);
            }
        });
    }


    //init the menu and send query in search bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search movie...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 2) LoadJson(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);

        return true;
    }
}
