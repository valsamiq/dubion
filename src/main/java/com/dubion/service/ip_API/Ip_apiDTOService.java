package com.dubion.service.ip_API;

import com.dubion.service.dto.ip_API.IdApiDTO;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class Ip_apiDTOService {

    private final Ip_apiDTORepository apiid = Ip_apiDTORepository.retrofit.create(Ip_apiDTORepository.class);

    // GoogleMapsDTORepository.retrofit.create(GoogleMapsDTORepository.class);

    public IdApiDTO getCoordinatesUser () throws IOException {
        java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A");

        IdApiDTO maps = new IdApiDTO();
        Call<IdApiDTO> callid_API = apiid.getCoordinatesUser(s.next());

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
