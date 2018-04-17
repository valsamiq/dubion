package com.dubion.service.ip_API;

import com.dubion.service.dto.GoogleMaps.Geocoding.GoogleMapsGeocodingDTO;
import com.dubion.service.dto.GoogleMaps.Geolocation.GoogleMapsGeolocationDTO;
import com.dubion.service.dto.ip_API.IdApiDTO;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Ip_apiDTORepository {
    @GET("/json?")
    Call<IdApiDTO> getCoordinatesUser(@Query("ip") String ip);

    public static String url = "http://ip-api.com";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
