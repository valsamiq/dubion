package com.dubion.service.NapsterAPI;

import com.dubion.domain.*;
import com.dubion.repository.*;
import com.dubion.service.dto.NapsterAPI.*;
import com.dubion.service.dto.NapsterAPI.Search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;


import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
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

    @Autowired
    private BandRepository bandRepository;

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
            if(songRepository.findByName(eraserNA(t.getName()))==null){


                importAlbumById(t.getAlbumId());
                if(songRepository.findByName(eraserNA(t.getName()))==null){
                    Song s = new Song();
                    s.setUrl(t.getPreviewURL());
                    s.setName(t.getName());
                    s.setAlbums(albumRepository.findByNameCR(t.getAlbumName()));
                    System.out.println(s.getAlbums());
                    s=songRepository.save(s);
                    topSongs.add(s);
                }


            }else{
                topSongs.add(songRepository.findByName(t.getName()));
            }
        }
        return topSongs;
    }

    public String eraserNA (String dataI) {
        String dataO = null;

        if(!dataI.equalsIgnoreCase("N/A")) {
            return dataI;
        }

        return dataO;
    }

    public List<Album> importTopAlbum () throws IOException {
        NapsterAlbum topAlbumNapster = getTopAlbumNap();
        List<Album> topAlbums = new ArrayList<>();
        for (com.dubion.service.dto.NapsterAPI.Albums.Album t:
            topAlbumNapster.getAlbums()) {
            importAlbumById(t.getId());
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
    public List<Album> importAlbumNew () throws IOException {
        NapsterAlbum newAlbum = null;
        List<Album> ListNewAlbums = new ArrayList<>();
        Call<NapsterAlbum> newAlbums = apiService.getAlbumNew( apiKey);
        newAlbum = newAlbums.execute().body();
        for (com.dubion.service.dto.NapsterAPI.Albums.Album t:
            newAlbum.getAlbums()) {
            if (songRepository.findByName(eraserNA(t.getName())) == null) {


                importAlbum(newAlbum, ListNewAlbums);
            }
        }
        return ListNewAlbums;
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
        NapsterAlbum callgenres = null;
        List<Album> albums = new ArrayList<>();
        String name ="";
        Call<NapsterAlbum> genre = apiService.getAlbumById("ES", apiKey,id);
        callgenres = genre.execute().body();
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
                Album a = new Album();

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
                    System.out.println(genreRepository.findByName(name));
                    a.setGenres(genreRepository.findByNames(name));
                    System.out.println(a.getGenres());
                }else{
                    importGenreByName(genre);
                    a.setGenres(genreRepository.findByNames(name));
                }


                NapsterArtist callartist = null;
                System.out.println("id artista: "+t.getContributingArtists().getPrimaryArtist());
                String id = t.getContributingArtists().getPrimaryArtist();
                Call<NapsterArtist> artists = apiService.getArtistByAlbum(id,apiKey);
                System.out.println("songs  "+artists);
                callartist = artists.execute().body();
                System.out.println("adivina    "+callartist);
                Band guardar = new Band();
                List<Band> topArtist = new ArrayList<>();
                for (com.dubion.service.dto.NapsterAPI.Artist.Artist g:
                    callartist.getArtists()) {
                    if(songRepository.findByName(eraserNA(t.getName()))==null){

                        Band artist = new Band();

                        artist.setName(g.getName());
                        String bio = null;
                        for (int i=1; i>=g.getBlurbs().size();i++){
                            bio+= g.getBlurbs().get(i);
                            System.out.println(bio);
                        }
                        artist.setBio(bio);
                        artist=bandRepository.save(artist);
                        topArtist.add(artist);
                        guardar=artist;
                        System.out.println("artist "+artist);

                    }else{
                        topArtist.add(bandRepository.findByName(t.getName()));
                    }
                }

                a.setName(t.getName());
                a.setBand(guardar);
                a.setReleaseDate(LocalDate.from(ZonedDateTime.parse(t.getReleased())));
                a.setPhoto("http://direct.napster.com/imageserver/v2/albums/"+t.getId()+"/images/500x500.jpg");
                a.setGenres(genreRepository.findByNames(name));

                a=albumRepository.save(a);
                System.out.println(a);
                albums.add(a);


                Napster callsongs = null;
                System.out.println(t.getId());
                Call<Napster> songs = apiService.getSongsByAlbum(t.getId(), apiKey);
                System.out.println("songs  "+songs);
                callsongs = songs.execute().body();
                System.out.println("adivina    "+callsongs);

                List<Song> topSongs = new ArrayList<>();
                for (Track g:
                    callsongs.getTracks()) {
                    if(songRepository.findByName(eraserNA(t.getName()))==null){

                        Song s = new Song();
                        s.setUrl(g.getPreviewURL());
                        s.setName(g.getName());
                        s.setAlbums(albumRepository.findByNameCR(g.getAlbumName()));
                        System.out.println(s.getAlbums());
                        s=songRepository.save(s);
                        topSongs.add(s);
                        System.out.println("cacion "+s);
                    }else{
                        topSongs.add(songRepository.findByName(t.getName()));
                    }
                }
            }else{
                albums.add( albumRepository.findByName(t.getName()));
            }

        }
    }

    public Search searchAlbums(String search){
        Search topAlbums = null;
        Call<Search> callTopAlbums = apiService.searchAlbum(search,apiKey,"album");
        try {
            Response<Search> response=callTopAlbums.execute();
            if(response.isSuccessful()){
                topAlbums = response.body();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return topAlbums;
    }
}
