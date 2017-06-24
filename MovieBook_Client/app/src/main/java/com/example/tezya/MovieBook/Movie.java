package com.example.tezya.MovieBook;

/**
 * Created by xietengxiao on 2017/6/5.
 */

public class Movie {

        private int ImageSrc;

        private String MovieName;

        private int Price;

        private double Rate;

        private int id;

        public int getImageSrc() {
            return ImageSrc;
        }
        public void setImageSrc(int s) {
            this.ImageSrc = s;
        }
        public String getMovieName() {
            return MovieName;
        }
        public void setMovieName(String MovieName) {
            this.MovieName = MovieName;
        }
        public int getPrice() {
            return Price;
        }
        public void setPrice(int price ) {
            this.Price = price;
        }
        public double getRate() {
            return Rate;
        }
        public void setRate(double Rate) {
            this.Rate = Rate;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

}
