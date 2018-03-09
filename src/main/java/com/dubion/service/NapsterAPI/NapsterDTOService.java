package com.dubion.service.NapsterAPI;

import com.dubion.domain.Album;
import com.dubion.domain.Song;
import com.dubion.repository.AlbumRepository;
import com.dubion.repository.SongRepository;
import com.dubion.service.dto.NapsterAPI.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import com.dubion.service.dto.NapsterAPI.Napster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NapsterDTOService {

    public static final String apiKey = "MjM4OWE1MzQtNTUyMy00ODIzLWEyNTMtNDQ1MzFlN2ExYzll";
    static NapsterDTORepository apiService = NapsterDTORepository.retrofit.create(NapsterDTORepository.class);


    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Napster getTopSongNap(){
        Napster topSongs = null;
        Call<Napster> callTopSongs = apiService.getTopSong(10,"ES",apiKey);
        System.out.println(callTopSongs);
        try {
            topSongs = callTopSongs.execute().body();
            System.out.println(topSongs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topSongs;
    }

    public List<Song> importTopSongs (){
        Napster topSongsNapster = getTopSongNap();
        List<Song> topsongs = new ArrayList<>();
        for (Track t:
             topSongsNapster.getTracks()) {
            if(songRepository.findByName(t.getName()).equals("")){

                Song s = new Song();

                s.setName(t.getName());
                s.addAlbum(createAlbumByName(t.getAlbumName()));

                s=songRepository.save(s);
                topsongs.add(s);
            }
        }
        return topsongs;
    }

    public Album createAlbumByName(String nombre){
        Album album = new Album();
        album.setName(nombre);
        album=albumRepository.save(album);
        return album;
    }


}
