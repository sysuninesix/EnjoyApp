package com.example.tezya.MovieBook;

/**
 * Created by xietengxiao on 2017/6/6.
 */

public class MovieInfo {
        private int movieId;
        private int movieDate;

        private String columns;
        private String rows;
        private int movieTime;
        public MovieInfo( int a,int b, String d, String e, int m){
            this.movieId= a;
            this.movieDate= b;

            this.columns= d;
            this.rows= e;
            this.movieTime= m;
        }
        public void setMovieId(int a){
            this.movieId= a;
        }
        public int getMovieId(){
            return this.movieId;
        }
        public void setColumns(String a){
            this.columns= a;
        }
        public String getColumns(){
            return columns;
        }
        public void setMovieDate(int a){
            this.movieDate= a;
        }
        public int getMovieDate(){
            return this.movieDate;
        }
        public void setRows(String a){
            this.rows= a;
        }
        public String getRows(){
            return this.rows;
        }
        public void setMovieTime(int a){
            this.movieTime= a;
        }
        public int getMovieTime(){
            return this.movieTime;
        }

}
