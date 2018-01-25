package com.dubion.service.DiscogsAPI;

import com.dubion.service.dto.DiscogsAPI.DiscogsApiDTO;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;


@Service
public class DiscogsApiService {
    public static final String apiKey = "e9146e088c2bfd128d974ae6fe70bdf4";
    static DiscogsApiRepository apiService = DiscogsApiRepository.retrofit.create(DiscogsApiRepository.class);

    public static DiscogsApiDTO getDisc(int id){
        DiscogsApiDTO movie = null;
        Call<DiscogsApiDTO> callMovie = apiService.getRelease();
        System.out.println(callMovie);
        try {
            movie = callMovie.execute().body();
            System.out.println(movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movie;
    }
    /*public static List<Movie> getTopRated(){
        Call<MovieResponse> call = apiService.getTopRatedMovies(apiKey);
        List<Movie> moviesList = null;
        try {
            moviesList = call.execute().body().results;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesList;
    }*/


}
