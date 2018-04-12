package com.dubion.service.TicketMasterAPI;

import com.dubion.repository.AlbumRepository;
import com.dubion.repository.ArtistRepository;
import com.dubion.repository.GenreRepository;
import com.dubion.repository.SongRepository;
import com.dubion.service.NapsterAPI.NapsterDTORepository;
import com.dubion.service.dto.NapsterAPI.Napster;
import com.dubion.service.dto.NapsterAPI.NapsterGenre;
import com.dubion.service.dto.TicketMasterAPI.City;
import com.dubion.service.dto.TicketMasterAPI.TicketMasterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;

import java.io.IOException;

public class TicketMasterDTOService {
    public static final String apiKey = "ueEf63mXLYXpOGEmDC2mbXFkhbZY1lW2";
    static TicketMasterDTORepository apiService = TicketMasterDTORepository.retrofit.create(TicketMasterDTORepository.class);


    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    public static TicketMasterAPI getGenres(){
        TicketMasterAPI city = null;
        String city2 = "";
        Call<TicketMasterAPI> callGenres = apiService.getByCity(city2,apiKey);
        System.out.println(callGenres);

        return city;
    }

}
