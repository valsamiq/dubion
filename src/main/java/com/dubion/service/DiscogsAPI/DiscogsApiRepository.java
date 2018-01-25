package com.dubion.service.DiscogsAPI;

import com.dubion.service.dto.DiscogsAPI.DiscogsApiDTO;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.Map;

@Repository
public interface DiscogsApiRepository {
    @GET("releases/249504")
    Call<DiscogsApiDTO> getRelease();

    public static String url = "https://api.discogs.com/";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
