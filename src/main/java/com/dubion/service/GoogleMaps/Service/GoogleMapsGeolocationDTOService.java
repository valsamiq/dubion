package com.dubion.service.GoogleMaps.Service;

import com.dubion.service.GoogleMaps.GoogleMapsDTORepository;
import com.dubion.service.dto.GoogleMaps.Geocoding.GoogleMapsGeocodingDTO;
import com.dubion.service.dto.GoogleMaps.Geolocation.GoogleMapsGeolocationDTO;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Service
public class GoogleMapsGeolocationDTOService {
    private final String key = "AIzaSyAfmJdUc9BYm3g40VnlNk_dW1FoH35qJiE";

    private final GoogleMapsDTORepository apiGoogleMaps = GoogleMapsDTORepository.retrofit.create(GoogleMapsDTORepository.class);

    // GoogleMapsDTORepository.retrofit.create(GoogleMapsDTORepository.class);

    public GoogleMapsGeolocationDTO getCoordinatesUser () {

        GoogleMapsGeolocationDTO maps = new GoogleMapsGeolocationDTO();
        Call<GoogleMapsGeolocationDTO> callGoogleMaps = apiGoogleMaps.getCoordinatesUser(key);

        try{
            Response<GoogleMapsGeolocationDTO> response = callGoogleMaps.execute();

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
