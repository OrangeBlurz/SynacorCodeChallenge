package com.example.sean.synacorcodechallenge;
/**
 * Synacor Code Challenge
 * Used The Movie DB API : https://github.com/holgerbrandl/themoviedbapi
 * Name: Matthew Sean Brett
 */
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbDiscover;
import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.MovieDb;




public class MainActivity extends ActionBarActivity {
    private ArrayList<Movie> masterCollection;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize master list of movies
        masterCollection = new ArrayList<Movie>();

        //Setup Adapter for listview
        adapter = new MovieAdapter(this, masterCollection);
        ListView lvMovies = (ListView) findViewById(R.id.movies);
        lvMovies.setAdapter(adapter);

        //Retrieve list of movies
        new RetrieveMovieData().execute();

        Button btnAlphaSort = (Button) findViewById(R.id.sort_by_name);
        Button btnDateSort = (Button) findViewById(R.id.sort_by_date);

        btnAlphaSort.setOnClickListener(new View.OnClickListener() {
            //Alphabetical comparator for Movies
            @Override
            public void onClick(View v) {
                Collections.sort(masterCollection, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie lhs, Movie rhs) {
                        return lhs.getTitle().compareTo(rhs.getTitle());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        btnDateSort.setOnClickListener(new View.OnClickListener() {
            //Date comparator for Movies
            @Override
            public void onClick(View v) {
                Collections.sort(masterCollection, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie lhs, Movie rhs) {

                        return rhs.getReleaseYear() - lhs.getReleaseYear();
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class RetrieveMovieData extends AsyncTask<Void, Void, ArrayList<Movie>>{

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            //Setup API key with library
            TmdbDiscover movies = new TmdbApi("b85ef4db483e7c46a948026e8b25b8af").getDiscover();

            //API only returns 20 movies at a time, so 5 calls needed
            for (int i=1; i <= 5; i++) {
                //List discovery criteria
                Discover discover = new Discover();
                discover.voteCountGte(250);
                discover.language("en");
                discover.sortBy("vote_average.desc");
                discover.page(i);

                List<MovieDb> results = movies.getDiscover(discover).getResults();

                //Convert Movie to my custom data structure
                for (MovieDb movie : results) {
                    masterCollection.add(new Movie(movie));
                }
            }
            return masterCollection;
        }

        protected void onPostExecute(ArrayList<Movie> result){
            //Notify the adapter that the dataset has changed
            adapter.notifyDataSetChanged();
        }


    }
}
