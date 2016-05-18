package com.example.sean.synacorcodechallenge;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Custom Movie data model
 * Name: Matthew Sean Brett
 */
public class Movie {

    private String title;
    private int releaseYear;
    private int voteCount;

    public Movie (MovieDb movie){
        title = movie.getTitle();
        String year = movie.getReleaseDate();
        year = year.split("-")[0];
        releaseYear = Integer.parseInt(year);
        voteCount = movie.getVoteCount();
    }

    public Movie (String title, int releaseYear, int voteCount){
        this.title = title;
        this.releaseYear = releaseYear;
        this.voteCount = voteCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String toString(){ return "("+releaseYear+")\t" + title;}

}
