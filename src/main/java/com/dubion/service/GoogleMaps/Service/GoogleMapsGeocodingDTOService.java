package com.dubion.service.GoogleMaps.Service;

import com.dubion.service.GoogleMaps.GoogleMapsDTORepository;
import com.dubion.service.dto.GoogleMaps.Geocoding.GoogleMapsGeocodingDTO;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class GoogleMapsGeocodingDTOService {
    private final String key = "AIzaSyBe1pXPpXGDo5Fe5UhOwOdwz1DaBirM5Jg";

    private final GoogleMapsDTORepository apiGoogleMaps = GoogleMapsDTORepository.retrofit.create(GoogleMapsDTORepository.class);

    // GoogleMapsDTORepository.retrofit.create(GoogleMapsDTORepository.class);

    public GoogleMapsGeocodingDTO getCoordinates (String address) {

        GoogleMapsGeocodingDTO maps = new GoogleMapsGeocodingDTO();
        Call<GoogleMapsGeocodingDTO> callGoogleMaps = apiGoogleMaps.getCoordinates(address, key);

        try{
            Response<GoogleMapsGeocodingDTO> response = callGoogleMaps.execute();

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
