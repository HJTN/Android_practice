package com.example.movieapi_practice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class YtsData {
    private String status;
    private String status_message;
    private MyData data;

    @Data
    public class MyData {
        private int movie_count;
        private int limit;
        private int page_number;
        private List<Movie> movies;

        @Data
        public class Movie {
            private String title;
            private float rating;
            private String medium_cover_image;

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle() {
                return title;
            }

            public void setRating(float rating) {
                this.rating = rating;
            }

            public float getRating() {
                return rating;
            }

            public void setMedium_cover_image(String medium_cover_image){
                this.medium_cover_image = medium_cover_image;
            }

            public String getMedium_cover_image() {
                return medium_cover_image;
            }
        }

        public void setMovie_count(int movie_count) {
            this.movie_count = movie_count;
        }
        public int getMovie_count() {
            return movie_count;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }
        public int getLimit() {
            return limit;
        }

        public void setPage_number(int page_number) {
            this.page_number = page_number;
        }
        public int getPage_number() {
            return page_number;
        }

        public void setMovies(List<Movie> movies) {
            this.movies = movies;
        }
        public List<Movie> getMovies() {
            return movies;
        }

        @Override
        public String toString() {
            return "MyData{" +
                    "movie_count=" + movie_count +
                    ", limit=" + limit +
                    ", page_number=" + page_number +
                    ", movies=" + movies +
                    "}";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
    public String getStatus_message() {
        return status_message;
    }

    public void setData(MyData data) {
        this.data = data;
    }
    public MyData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "YtsData{" +
                "status='" + status + '\'' +
                ", status_message='" + status_message + '\'' +
                ", data=" + data +
                '}';
    }

}
