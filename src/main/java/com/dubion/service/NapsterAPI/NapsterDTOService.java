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
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    public List<Song> importTopSongs () throws IOException {
        Napster topSongsNapster = getTopSongNap();
        List<Song> topSongs = new ArrayList<>();
        for (Track t:
             topSongsNapster.getTracks()) {
            if(songRepository.findByName(t.getName())==null){

                Song s = new Song();
                s.setUrl(t.getPreviewURL());
                s.setName(t.getName());
                importAlbumById(t.getAlbumId());
                s.setAlbums(albumRepository.findByNameCR(t.getAlbumName()));
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
            if(albumRepository.findByName(t.getName())==null){
                Album s = new Album();


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
                s.setName(t.getName());
                s.setReleaseDate(LocalDate.from(ZonedDateTime.parse(t.getReleased())));
                s.setPhoto("http://direct.napster.com/imageserver/v2/albums/"+t.getId()+"/images/500x500.jpg");
                s=albumRepository.save(s);
                topAlbums.add(s);
            }else{
                topAlbums.add(albumRepository.findByName(t.getName()));
            }

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
                for (String b: t.getBlurbs()){
                    a= b;
                }
                s.setName(t.getName());
                s.setBio(a);
                s.setPhoto("http://direct.napster.com/imageserver/v2/artists/"+t.getId()+"/images/356x237.jpg");
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
    public List<Album> importAlbumById(String id) throws IOException {
        NapsterAlbum callgenres = null; String name ="";
        Call<NapsterAlbum> genre = apiService.getAlbumById("ES", apiKey,id);
        callgenres = genre.execute().body();
        List<Album> albums = new ArrayList<>();
        importAlbum(callgenres, albums);
        return  albums;
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
    private void importAlbum(NapsterAlbum napsterAlbum, List<Album> albums) throws IOException {
        for (com.dubion.service.dto.NapsterAPI.Albums.Album t:
            napsterAlbum.getAlbums()) {
            if(albumRepository.findByName(t.getName())==null){
                Album s = new Album();

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
                    System.out.println("1");
                    System.out.println(genreRepository.findByName(name));
                    s.setGenres(genreRepository.findByNames(name));
                    System.out.println(s.getGenres());
                }else{
                    System.out.println("2");
                    importGenreByName(genre);
                    s.setGenres(genreRepository.findByNames(name));
                }
                s.setName(t.getName());
                s.setReleaseDate(LocalDate.from(ZonedDateTime.parse(t.getReleased())));
                s.setPhoto("http://direct.napster.com/imageserver/v2/albums/"+t.getId()+"/images/500x500.jpg");
                s.setGenres(genreRepository.findByNames(name));

                s=albumRepository.save(s);
                System.out.println(s);
                albums.add(s);
            }else{
                albums.add( albumRepository.findByName(t.getName()));
            }

        }
    }


}
