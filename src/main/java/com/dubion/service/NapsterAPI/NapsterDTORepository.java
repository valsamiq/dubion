package com.dubion.service.NapsterAPI;

import com.dubion.service.dto.GoogleMaps.Geocoding.GoogleMapsGeocodingDTO;
import com.dubion.service.dto.GoogleMaps.Geolocation.GoogleMapsGeolocationDTO;
import com.dubion.service.dto.NapsterAPI.Napster;
import com.dubion.service.dto.NapsterAPI.NapsterAlbum;
import com.dubion.service.dto.NapsterAPI.NapsterArtist;
import com.dubion.service.dto.NapsterAPI.NapsterGenre;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NapsterDTORepository {

    public static String url = "http://api.napster.com/v2.2/";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    @GET("tracks/top")
    Call<Napster> getTopSong(@Query("limit") int limit
        , @Query("catalog") String catalog
        , @Query("apikey") String apiKey);

    @GET("albums/top")
    Call<NapsterAlbum> getTopAlbums(@Query("limit") int limit
            , @Query("catalog") String catalog
            , @Query("apikey") String apiKey);
    @GET("artists/top")
    Call<NapsterArtist> getTopArtists(@Query("limit") int limit
        , @Query("catalog") String catalog
        , @Query("apikey") String apiKey);
    @GET("genres")
    Call<NapsterGenre> getGenres(@Query("catalog") String catalog
            , @Query("apikey") String apiKey);
    @GET("genres/")
    Call<NapsterGenre> getGenresById(@Query("catalog") String catalog
        , @Query("apikey") String apiKey
        , @Query("id") String id);
    @GET("albums/")
    Call<NapsterAlbum> getAlbumById(@Query("catalog") String catalog
        , @Query("apikey") String apiKey
        , @Query("id") String id);
}
