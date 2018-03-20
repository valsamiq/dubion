package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.Artist;
import com.dubion.domain.Band;
import com.dubion.domain.Instrument;
import com.dubion.domain.RatingArtist;
import com.dubion.repository.ArtistRepository;
import com.dubion.service.ArtistService;
import com.dubion.web.rest.errors.ExceptionTranslator;
import com.dubion.service.dto.ArtistCriteria;
import com.dubion.service.ArtistQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.dubion.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ArtistResource REST controller.
 *
 * @see ArtistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class ArtistResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO = "BBBBBBBBBB";

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistQueryService artistQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restArtistMockMvc;

    private Artist artist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArtistResource artistResource = new ArtistResource(artistService, artistQueryService);
        this.restArtistMockMvc = MockMvcBuilders.standaloneSetup(artistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Artist createEntity(EntityManager em) {
        Artist artist = new Artist()
            .name(DEFAULT_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .bio(DEFAULT_BIO)
            .photo(DEFAULT_PHOTO);
        return artist;
    }

    @Before
    public void initTest() {
        artist = createEntity(em);
    }

    @Test
    @Transactional
    public void createArtist() throws Exception {
        int databaseSizeBeforeCreate = artistRepository.findAll().size();

        // Create the Artist
        restArtistMockMvc.perform(post("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artist)))
            .andExpect(status().isCreated());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeCreate + 1);
        Artist testArtist = artistList.get(artistList.size() - 1);
        assertThat(testArtist.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testArtist.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testArtist.getBio()).isEqualTo(DEFAULT_BIO);
        assertThat(testArtist.getPhoto()).isEqualTo(DEFAULT_PHOTO);
    }

    @Test
    @Transactional
    public void createArtistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = artistRepository.findAll().size();

        // Create the Artist with an existing ID
        artist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArtistMockMvc.perform(post("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artist)))
            .andExpect(status().isBadRequest());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArtists() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList
        restArtistMockMvc.perform(get("/api/artists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(artist.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())));
    }

    @Test
    @Transactional
    public void getArtist() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get the artist
        restArtistMockMvc.perform(get("/api/artists/{id}", artist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(artist.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()));
    }

    @Test
    @Transactional
    public void getAllArtistsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where name equals to DEFAULT_NAME
        defaultArtistShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the artistList where name equals to UPDATED_NAME
        defaultArtistShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllArtistsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where name in DEFAULT_NAME or UPDATED_NAME
        defaultArtistShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the artistList where name equals to UPDATED_NAME
        defaultArtistShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllArtistsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where name is not null
        defaultArtistShouldBeFound("name.specified=true");

        // Get all the artistList where name is null
        defaultArtistShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllArtistsByBirthDateIsEqualToSomething() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where birthDate equals to DEFAULT_BIRTH_DATE
        defaultArtistShouldBeFound("birthDate.equals=" + DEFAULT_BIRTH_DATE);

        // Get all the artistList where birthDate equals to UPDATED_BIRTH_DATE
        defaultArtistShouldNotBeFound("birthDate.equals=" + UPDATED_BIRTH_DATE);
    }

    @Test
    @Transactional
    public void getAllArtistsByBirthDateIsInShouldWork() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where birthDate in DEFAULT_BIRTH_DATE or UPDATED_BIRTH_DATE
        defaultArtistShouldBeFound("birthDate.in=" + DEFAULT_BIRTH_DATE + "," + UPDATED_BIRTH_DATE);

        // Get all the artistList where birthDate equals to UPDATED_BIRTH_DATE
        defaultArtistShouldNotBeFound("birthDate.in=" + UPDATED_BIRTH_DATE);
    }

    @Test
    @Transactional
    public void getAllArtistsByBirthDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where birthDate is not null
        defaultArtistShouldBeFound("birthDate.specified=true");

        // Get all the artistList where birthDate is null
        defaultArtistShouldNotBeFound("birthDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllArtistsByBirthDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where birthDate greater than or equals to DEFAULT_BIRTH_DATE
        defaultArtistShouldBeFound("birthDate.greaterOrEqualThan=" + DEFAULT_BIRTH_DATE);

        // Get all the artistList where birthDate greater than or equals to UPDATED_BIRTH_DATE
        defaultArtistShouldNotBeFound("birthDate.greaterOrEqualThan=" + UPDATED_BIRTH_DATE);
    }

    @Test
    @Transactional
    public void getAllArtistsByBirthDateIsLessThanSomething() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where birthDate less than or equals to DEFAULT_BIRTH_DATE
        defaultArtistShouldNotBeFound("birthDate.lessThan=" + DEFAULT_BIRTH_DATE);

        // Get all the artistList where birthDate less than or equals to UPDATED_BIRTH_DATE
        defaultArtistShouldBeFound("birthDate.lessThan=" + UPDATED_BIRTH_DATE);
    }


    @Test
    @Transactional
    public void getAllArtistsByBioIsEqualToSomething() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where bio equals to DEFAULT_BIO
        defaultArtistShouldBeFound("bio.equals=" + DEFAULT_BIO);

        // Get all the artistList where bio equals to UPDATED_BIO
        defaultArtistShouldNotBeFound("bio.equals=" + UPDATED_BIO);
    }

    @Test
    @Transactional
    public void getAllArtistsByBioIsInShouldWork() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where bio in DEFAULT_BIO or UPDATED_BIO
        defaultArtistShouldBeFound("bio.in=" + DEFAULT_BIO + "," + UPDATED_BIO);

        // Get all the artistList where bio equals to UPDATED_BIO
        defaultArtistShouldNotBeFound("bio.in=" + UPDATED_BIO);
    }

    @Test
    @Transactional
    public void getAllArtistsByBioIsNullOrNotNull() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where bio is not null
        defaultArtistShouldBeFound("bio.specified=true");

        // Get all the artistList where bio is null
        defaultArtistShouldNotBeFound("bio.specified=false");
    }

    @Test
    @Transactional
    public void getAllArtistsByPhotoIsEqualToSomething() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where photo equals to DEFAULT_PHOTO
        defaultArtistShouldBeFound("photo.equals=" + DEFAULT_PHOTO);

        // Get all the artistList where photo equals to UPDATED_PHOTO
        defaultArtistShouldNotBeFound("photo.equals=" + UPDATED_PHOTO);
    }

    @Test
    @Transactional
    public void getAllArtistsByPhotoIsInShouldWork() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where photo in DEFAULT_PHOTO or UPDATED_PHOTO
        defaultArtistShouldBeFound("photo.in=" + DEFAULT_PHOTO + "," + UPDATED_PHOTO);

        // Get all the artistList where photo equals to UPDATED_PHOTO
        defaultArtistShouldNotBeFound("photo.in=" + UPDATED_PHOTO);
    }

    @Test
    @Transactional
    public void getAllArtistsByPhotoIsNullOrNotNull() throws Exception {
        // Initialize the database
        artistRepository.saveAndFlush(artist);

        // Get all the artistList where photo is not null
        defaultArtistShouldBeFound("photo.specified=true");

        // Get all the artistList where photo is null
        defaultArtistShouldNotBeFound("photo.specified=false");
    }

    @Test
    @Transactional
    public void getAllArtistsByBandIsEqualToSomething() throws Exception {
        // Initialize the database
        Band band = BandResourceIntTest.createEntity(em);
        em.persist(band);
        em.flush();
        artist.addBand(band);
        artistRepository.saveAndFlush(artist);
        Long bandId = band.getId();

        // Get all the artistList where band equals to bandId
        defaultArtistShouldBeFound("bandId.equals=" + bandId);

        // Get all the artistList where band equals to bandId + 1
        defaultArtistShouldNotBeFound("bandId.equals=" + (bandId + 1));
    }


    @Test
    @Transactional
    public void getAllArtistsByInstrumentIsEqualToSomething() throws Exception {
        // Initialize the database
        Instrument instrument = InstrumentResourceIntTest.createEntity(em);
        em.persist(instrument);
        em.flush();
        artist.addInstrument(instrument);
        artistRepository.saveAndFlush(artist);
        Long instrumentId = instrument.getId();

        // Get all the artistList where instrument equals to instrumentId
        defaultArtistShouldBeFound("instrumentId.equals=" + instrumentId);

        // Get all the artistList where instrument equals to instrumentId + 1
        defaultArtistShouldNotBeFound("instrumentId.equals=" + (instrumentId + 1));
    }


    @Test
    @Transactional
    public void getAllArtistsByRatingIsEqualToSomething() throws Exception {
        // Initialize the database
        RatingArtist rating = RatingArtistResourceIntTest.createEntity(em);
        em.persist(rating);
        em.flush();
        artist.addRating(rating);
        artistRepository.saveAndFlush(artist);
        Long ratingId = rating.getId();

        // Get all the artistList where rating equals to ratingId
        defaultArtistShouldBeFound("ratingId.equals=" + ratingId);

        // Get all the artistList where rating equals to ratingId + 1
        defaultArtistShouldNotBeFound("ratingId.equals=" + (ratingId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultArtistShouldBeFound(String filter) throws Exception {
        restArtistMockMvc.perform(get("/api/artists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(artist.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultArtistShouldNotBeFound(String filter) throws Exception {
        restArtistMockMvc.perform(get("/api/artists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingArtist() throws Exception {
        // Get the artist
        restArtistMockMvc.perform(get("/api/artists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArtist() throws Exception {
        // Initialize the database
        artistService.save(artist);

        int databaseSizeBeforeUpdate = artistRepository.findAll().size();

        // Update the artist
        Artist updatedArtist = artistRepository.findOne(artist.getId());
        updatedArtist
            .name(UPDATED_NAME)
            .birthDate(UPDATED_BIRTH_DATE)
            .bio(UPDATED_BIO)
            .photo(UPDATED_PHOTO);

        restArtistMockMvc.perform(put("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArtist)))
            .andExpect(status().isOk());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeUpdate);
        Artist testArtist = artistList.get(artistList.size() - 1);
        assertThat(testArtist.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testArtist.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testArtist.getBio()).isEqualTo(UPDATED_BIO);
        assertThat(testArtist.getPhoto()).isEqualTo(UPDATED_PHOTO);
    }

    @Test
    @Transactional
    public void updateNonExistingArtist() throws Exception {
        int databaseSizeBeforeUpdate = artistRepository.findAll().size();

        // Create the Artist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restArtistMockMvc.perform(put("/api/artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(artist)))
            .andExpect(status().isCreated());

        // Validate the Artist in the database
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteArtist() throws Exception {
        // Initialize the database
        artistService.save(artist);

        int databaseSizeBeforeDelete = artistRepository.findAll().size();

        // Get the artist
        restArtistMockMvc.perform(delete("/api/artists/{id}", artist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Artist> artistList = artistRepository.findAll();
        assertThat(artistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Artist.class);
        Artist artist1 = new Artist();
        artist1.setId(1L);
        Artist artist2 = new Artist();
        artist2.setId(artist1.getId());
        assertThat(artist1).isEqualTo(artist2);
        artist2.setId(2L);
        assertThat(artist1).isNotEqualTo(artist2);
        artist1.setId(null);
        assertThat(artist1).isNotEqualTo(artist2);
    }
}
