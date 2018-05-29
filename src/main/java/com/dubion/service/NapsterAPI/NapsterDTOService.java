package com.dubion.service.NapsterAPI;

import com.dubion.domain.*;
import com.dubion.repository.*;
import com.dubion.service.dto.NapsterAPI.*;
import com.dubion.service.dto.NapsterAPI.Artist.Bio;
import com.dubion.service.dto.NapsterAPI.Artist.images.Image;
import com.dubion.service.dto.NapsterAPI.Artist.images.Images;
import com.dubion.service.dto.NapsterAPI.Search.Artists;
import com.dubion.service.dto.NapsterAPI.Search.Search;
import com.dubion.service.dto.NapsterAPI.Search.Tracks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String eraserEvilBytes (String dataI) {
        String dataO = null;

        System.out.println("Eliminamos bytes malignos.");

        try {
            byte[] utf8Bytes = dataI.getBytes("UTF-8");
            dataO = new String(utf8Bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Pattern unicodeOutliers = Pattern.compile("[^\\x00-\\x7F]",
            Pattern.UNICODE_CASE | Pattern.CANON_EQ
                | Pattern.CASE_INSENSITIVE);
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(dataO);

        dataO = unicodeOutlierMatcher.replaceAll(" ");

        System.out.println("Bytes malignos eliminados.");

        return dataO;
    }

    public List<Album> importTopAlbum () throws IOException {
        NapsterAlbum topAlbumNapster = getTopAlbumNap();
        List<Album> topAlbums = new ArrayList<>();
        for (com.dubion.service.dto.NapsterAPI.Albums.Album t:
            topAlbumNapster.getAlbums()) {
            importAlbumById(t.getId());
            if(albumRepository.findByNapsterId(t.getId())==null){
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
                topAlbums.add(albumRepository.findByNapsterId(t.getId()));
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

            String name ="";
            if(albumRepository.findByNapsterId(t.getId())==null){
                Album a = new Album();
                System.out.println(t.getLinks().getGenres());
                if(t.getLinks().getGenres()!=null){
                    String genre = t.getLinks().getGenres().getIds().get(0);
                    System.out.println(genre);
                    NapsterGenre callgenres = null;
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
                }



                NapsterArtist callartist = null;
                System.out.println("id artista: "+t.getContributingArtists().getPrimaryArtist());
                String id = t.getContributingArtists().getPrimaryArtist();
                System.out.println(id);
                if(id!=null){
                    Call<NapsterArtist> artists = apiService.getArtistByAlbum(id,apiKey);
                    System.out.println("songs  "+artists);
                    callartist = artists.execute().body();
                    System.out.println("adivina    "+callartist);
                    if(callartist==null){
                        Band desconocido = new Band();
                        if(!bandRepository.findByNameOptional("Desconocido").isPresent()){
                            desconocido = new Band("Desconocido",null,"No tiene bio, sry", null, "http://static.rhap.com/img/1500x1000/4/4/0/6/8756044_1500x1000.jpg", null,null,null,null,null,null,null,null);
                            bandRepository.save(desconocido);
                        }
                        a.setBand(bandRepository.findByName("Desconocido"));

                    }else{
                        for (com.dubion.service.dto.NapsterAPI.Artist.Artist g:
                            callartist.getArtists()) {
                            if(bandRepository.findByNapsterId(eraserNA(g.getId()))==null){
                                Band guardar = new Band();
                                List<Band> topArtist = new ArrayList<>();
                                for (com.dubion.service.dto.NapsterAPI.Artist.Artist art:
                                    callartist.getArtists()) {
                                    if (songRepository.findByName(eraserNA(art.getName())) == null) {

                                        Band artist = new Band();

                                        artist.setName(g.getName());
                                        artist.setNapsterId(g.getId());
                                        Napster ca = null;

                                        Images callartistImages = null;
                                        Call <Images> artistsImages = apiService.getArtistImages(g.getId(),apiKey);
                                        System.out.println(artistsImages);
                                        callartistImages = artistsImages.execute().body();
                                        System.out.println(callartistImages);
                                        System.out.println("\n\n\n" +callartistImages+ "\n\n\n");
                                        if(callartistImages.getImages().size()!=0){
                                            for (Image artistImages:
                                                callartistImages.getImages()) {
                                                artist.setPhoto(artistImages.getUrl());
                                            }
                                        }else{
                                            artist.setPhoto("content/images/artists/unknown.png");
                                        }
                                        String bio;
                                        bio = "";

                                        if (g.getBlurbs().size() != 0) {
                                            for (String bios : g.getBlurbs()) {
                                                System.out.println(bios);
                                                bio = bio + " " + bios;
                                                System.out.println(bio);
                                            }
                                            System.out.println("\n\n\n" + bio + "\n\n\n");
                                            artist.setBio(eraserNA(bio));
                                        }else if(g.getBios()!=null) {
                                            for (Bio bios: g.getBios()){
                                                artist.setBio(bios.getBio());
                                            }
                                            System.out.println(artist.getId());
                                        }else{
                                            artist.setBio("No tiene bio, sry    ");
                                        }
                                        artist = bandRepository.save(artist);
                                        topArtist.add(artist);
                                        guardar = artist;
                                        System.out.println("artist " + artist);

                                    } else {
                                        topArtist.add(bandRepository.findByName(eraserNA(t.getName())));
                                    }
                                }
                                a.setBand(guardar);
                            }
                            a.setBand(bandRepository.findByNapsterId(eraserNA(g.getId())));
                        }
                    }
                }else{
                    Band desconocido = new Band();
                    if(!bandRepository.findByNameOptional("Desconocido").isPresent()){
                        desconocido = new Band("Desconocido",null,"No tiene bio, sry", null, "http://static.rhap.com/img/1500x1000/4/4/0/6/8756044_1500x1000.jpg", "11111",null,null,null,null,null,null,null);
                        bandRepository.save(desconocido);
                    }
                    bandRepository.findByName(desconocido.getName());
                    a.setBand(bandRepository.findByName(desconocido.getName()));
                    System.out.println(a.getBand());
                }




                a.setName(eraserEvilBytes(eraserNA(t.getName())));
                a.setNapsterId(t.getId());
                if(!t.getReleased().equalsIgnoreCase("")){
                    a.setReleaseDate(LocalDate.from(ZonedDateTime.parse(t.getReleased())));
                }
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
                    if(songRepository.findByNapsterId(eraserNA(t.getId()))==null){

                        Song s = new Song();
                        s.setUrl(g.getPreviewURL());
                        s.setName(eraserEvilBytes(eraserNA(g.getName())));
                        s.setNapsterId(g.getId());
                        s.setAlbums(albumRepository.findByNameCR(eraserNA(g.getAlbumName())));
                        System.out.println(s.getAlbums());
                        s=songRepository.save(s);
                        topSongs.add(s);
                        System.out.println("cacion "+s);
                    }else{
                        topSongs.add(songRepository.findByName(eraserNA(t.getName())));
                    }
                }
            }else{
                albums.add( albumRepository.findByNapsterId(t.getId()));
            }

        }
    }

    public Search searchAndImportAlbums(String search){
        Search topAlbums = null;
        Call<Search> callTopAlbums = apiService.searchAlbum(search,apiKey,"album",10);
        try {
            Response<Search> response=callTopAlbums.execute();
            if(response.isSuccessful()){
                topAlbums = response.body();
            }
            for (com.dubion.service.dto.NapsterAPI.Search.Album album: topAlbums.getSearch().getData().getAlbums()){
                System.out.println(album.getId());
                importAlbumById(album.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return topAlbums;
    }
    public Search searchSongs(String search){
        Search topAlbums = null;
        Call<Search> callTopAlbums = apiService.searchAlbum(search,apiKey,"tracks",10);
        try {
            Response<Search> response=callTopAlbums.execute();
            if(response.isSuccessful()){
                topAlbums = response.body();
            }
            for (com.dubion.service.dto.NapsterAPI.Search.Tracks album: topAlbums.getSearch().getData().getTracks()){
                System.out.println(album.getAlbumId());
                importAlbumById(album.getAlbumId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return topAlbums;
    }
    public List<Search> searchBands(String search){
        List<Search > artist = null;
        Call <Search> callArtist = apiService.searchBandNapster(search,apiKey,"artists",15);
        try {
            Response <Search> response=callArtist.execute();
            if(response.isSuccessful()){
                artist = Collections.singletonList(response.body());
                System.out.println("topAlbums "+artist);
            }

            for (com.dubion.service.dto.NapsterAPI.Search.Search album: artist){
                for (com.dubion.service.dto.NapsterAPI.Search.Tracks albums: album.getSearch().getData().getTracks())
                importAlbumById(albums.getAlbumId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return artist;
    }
    public void importArtist(String id){
        NapsterArtist artist = null;
        Call <NapsterArtist> callArtist = apiService.getArtistByAlbum(id,apiKey);
        List<Band> topBand = new ArrayList<>();
        try {
            Response <NapsterArtist> response=callArtist.execute();
            if(response.isSuccessful()){
                artist = response.body();
                System.out.println("topAlbums "+artist);
                for(com.dubion.service.dto.NapsterAPI.Artist.Artist a: artist.getArtists()){
                    if(bandRepository.findByNapsterId(a.getId())==null){
                        Band s = new Band();
                        String a1="";
                        for (String b: a.getBlurbs()){
                            a1= b;
                        }
                        s.setName(a.getName());
                        s.setBio(a1);
                        s.setPhoto("http://direct.napster.com/imageserver/v2/artists/"+a.getId()+"/images/356x237.jpg");
                        s.setNapsterId(a.getId());
                        s=bandRepository.save(s);
                        topBand.add(s);
                        if(a.getAlbumGroups().getSinglesAndEPs()!=null){
                            for(String album: a.getAlbumGroups().getSinglesAndEPs())importAlbumById(album);
                        }else if(a.getAlbumGroups().getCompilations()!=null){
                            for(String album: a.getAlbumGroups().getCompilations())importAlbumById(album);
                        }else if (a.getAlbumGroups().getOthers()!=null){
                            for(String album: a.getAlbumGroups().getSinglesAndEPs())importAlbumById(album);
                        }else if (a.getAlbumGroups().getMain()!=null){
                            for(String album: a.getAlbumGroups().getSinglesAndEPs())importAlbumById(album);
                        }else{

                        }




                    }else{
                        topBand.add(bandRepository.findByNapsterId(a.getId()));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
