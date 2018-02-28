package com.dubion.service.ip_API;

import com.dubion.service.GoogleMaps.GoogleMapsDTORepository;
import com.dubion.service.dto.ip_API.IdApiDTO;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class ip_apiDTOService {

    private final ip_apiDTORepository apiid = ip_apiDTORepository.retrofit.create(ip_apiDTORepository.class);

    // GoogleMapsDTORepository.retrofit.create(GoogleMapsDTORepository.class);

    public IdApiDTO getCoordinatesUser (String ip) {

        IdApiDTO maps = new IdApiDTO();
        Call<IdApiDTO> callid_API = apiid.getCoordinatesUser(ip);

        try{
            Response<IdApiDTO> response = callid_API.execute();

            if(response.isSuccessful()) {
                maps = response.body();
                System.out.println(maps);
            }

        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return maps;
    }
}
