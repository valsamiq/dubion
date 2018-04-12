package com.dubion.service.TicketMasterAPI;


import com.dubion.service.ip_API.Ip_apiDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Service
public class TicketMasterDTOService {
    public static final String apiKey = "ueEf63mXLYXpOGEmDC2mbXFkhbZY1lW2";
    static TicketMasterDTORepository apiService = TicketMasterDTORepository.retrofit.create(TicketMasterDTORepository.class);

    @Autowired
    private Ip_apiDTOService IP_API_DTO_SERVICE;

    public Call<com.dubion.service.dto.TiquetMasterAPI.TicketMasterAPI> getCity(){

        String cityName= null;
        try {
            cityName = IP_API_DTO_SERVICE.getCoordinatesUser().getCity();
            Call<com.dubion.service.dto.TiquetMasterAPI.TicketMasterAPI> callGenres = apiService.getByCity(cityName,apiKey);
            System.out.println(callGenres);
            return callGenres;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }



}
