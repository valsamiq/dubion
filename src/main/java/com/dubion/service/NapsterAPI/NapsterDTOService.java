package com.dubion.service.NapsterAPI;

import com.dubion.domain.Album;
import com.dubion.domain.Genre;
import com.dubion.domain.Song;
import com.dubion.domain.Artist;
import com.dubion.repository.AlbumRepository;
import com.dubion.repository.ArtistRepository;
import com.dubion.repository.GenreRepository;
import com.dubion.repository.SongRepository;
import com.dubion.service.dto.NapsterAPI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;


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

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

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
    public NapsterGenre getGenres(){
        NapsterGenre genres = null;
        Call<NapsterGenre> callGenres = apiService.getGenres("ES",apiKey);
        System.out.println(callGenres);
        try {
            genres = callGenres.execute().body();
            System.out.println(genres);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genres;
    }


    public NapsterArtist getTopArtistNap(){
        NapsterArtist topArtist = null;
        Call<NapsterArtist> callTopArtists = apiService.getTopArtists(10,"ES",apiKey);
        System.out.println(callTopArtists);
        try {
            topArtist = callTopArtists.execute().body();
            System.out.println(topArtist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topArtist;
    }

    public NapsterAlbum getTopAlbumNap(){
        NapsterAlbum topAlbums = null;
        Call<NapsterAlbum> callTopAlbums = apiService.getTopAlbums(10,"ES",apiKey);
        System.out.println(callTopAlbums);
        try {
            topAlbums = callTopAlbums.execute().body();
            System.out.println(topAlbums);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topAlbums;
    }


    public List<Song> importTopSongs (){
        Napster topSongsNapster = getTopSongNap();
        List<Song> topSongs = new ArrayList<>();
        for (Track t:
             topSongsNapster.getTracks()) {
            if(songRepository.findByName(t.getName())==null){

                Song s = new Song();

                s.setName(t.getName());
                s.addAlbum(createAlbumByName(t.getAlbumName()));

                s=songRepository.save(s);
                topSongs.add(s);
            }else{
                topSongs.add(songRepository.findByName(t.getName()));
            }
        }
        return topSongs;
    }



    public List<Album> importTopAlbum () throws IOException {
        NapsterAlbum topAlbumNapster = getTopAlbumNap();
        List<Album> topAlbums = new ArrayList<>();
        for (com.dubion.service.dto.NapsterAPI.Albums.Album t:
            topAlbumNapster.getAlbums()) {
          //  if(albumRepository.findByName(t.getName())==null){
                Album s = new Album();

                s.setName(t.getName());
                String genre = t.getLinks().getGenres().getIds().get(0);
                System.out.println(genre);
                NapsterGenre callgenres = null; String name ="";
                Call<NapsterGenre> genres = apiService.getGenresById("ES", apiKey,genre);
                callgenres = genres.execute().body();
                for (com.dubion.service.dto.NapsterAPI.Genre.Genre g:
                    callgenres.getGenre()) {
                    name = g.getName();}
                System.out.println(name);
                System.out.println(genreRepository.findByName(name));
                if (genreRepository.findByName(name)!=null){
                    s.setGenres(genreRepository.findByNames(name));
                }else{
                    importGenreByName(genre);
                    s.setGenres(genreRepository.findByNames(name));
                }


                s=albumRepository.save(s);
                topAlbums.add(s);
          /*  }else{
                topAlbums.add(albumRepository.findByName(t.getName()));
            }*/

        }
        return topAlbums;
    }
    public List<Artist> importTopArtist (){
        NapsterArtist topArtistNapster = getTopArtistNap();
        List<Artist> topArtists = new ArrayList<>();
        for (com.dubion.service.dto.NapsterAPI.Artist.Artist t:
            topArtistNapster.getArtists()) {
            if(artistRepository.findByName(t.getName())==null){
                Artist s = new Artist();
                String a="";
                s.setName(t.getName());
                for (String b: t.getBlurbs()){
                    a= b;
                }
                s.setBio(a);

                s=artistRepository.save(s);
                topArtists.add(s);
            }else{
                topArtists.add(artistRepository.findByName(t.getName()));
            }

        }
        return topArtists;
    }

    public List<Genre> importGenres (){
        NapsterGenre NapsterGenres = getGenres();
        List<Genre> genres = new ArrayList<>();
        importGenre(NapsterGenres, genres);
        return genres;
    }


    public Album createAlbumByName(String nombre){
        Album album = new Album();
        album.setName(nombre);
        album=albumRepository.save(album);
        return album;
    }

    public List<Genre> importGenreByName(String id) throws IOException {
        NapsterGenre callgenres = null; String name ="";
        Call<NapsterGenre> genre = apiService.getGenresById("ES", apiKey,id);
        callgenres = genre.execute().body();
        List<Genre> genres = new ArrayList<>();
        importGenre(callgenres, genres);
        return  genres;
    }

    private void importGenre(NapsterGenre napsterGenres, List<Genre> genres) {
        for (com.dubion.service.dto.NapsterAPI.Genre.Genre t:
            napsterGenres.getGenre()) {
            if(genreRepository.findByName(t.getName())==null){
                Genre s = new Genre();
                s.setName(t.getName());
                s=genreRepository.save(s);
                genres.add(s);
            }else{
                genres.add( genreRepository.findByName(t.getName()));
            }

        }
    }


}
