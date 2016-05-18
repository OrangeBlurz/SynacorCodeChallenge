package com.example.sean.synacorcodechallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Movie adapter for custom displays
 * Name: Matthew Sean Brett
 */
public class MovieAdapter extends ArrayAdapter<Movie>{

    private Context context;
    private ArrayList<Movie> movies;
    public MovieAdapter (Context context, ArrayList<Movie> values){
        super(context,0,values);
        this.context = context;
        movies = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Movie movie = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_view_template, parent, false);
        }

        //Fill in custom Adapter
        TextView tvMovieName = (TextView) convertView.findViewById(R.id.movie_name);
        TextView tvMovieRelease = (TextView) convertView.findViewById(R.id.release_year);

        tvMovieName.setText(movie.getTitle());
        tvMovieRelease.setText(Integer.toString(movie.getReleaseYear()));

        return convertView;
    }
}
