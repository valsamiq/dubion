package com.dubion.service.TicketMasterAPI;

import com.dubion.service.dto.TicketMasterAPI.TicketMasterAPI;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TicketMasterDTORepository {
    public static String url = "https://app.ticketmaster.com/discovery/v2/";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    @GET("events/")
    Call<TicketMasterAPI> getByCity(@Query("city")String cityName
        , @Query("apikey") String apiKey);


}
