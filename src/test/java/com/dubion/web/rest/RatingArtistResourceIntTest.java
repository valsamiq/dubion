package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.RatingArtist;
import com.dubion.repository.RatingArtistRepository;
import com.dubion.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.dubion.web.rest.TestUtil.sameInstant;
import static com.dubion.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RatingArtistResource REST controller.
 *
 * @see RatingArtistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class RatingArtistResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    @Autowired
    private RatingArtistRepository ratingArtistRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRatingArtistMockMvc;

    private RatingArtist ratingArtist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RatingArtistResource ratingArtistResource = new RatingArtistResource(ratingArtistRepository);
        this.restRatingArtistMockMvc = MockMvcBuilders.standaloneSetup(ratingArtistResource)
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
    public static RatingArtist createEntity(EntityManager em) {
        RatingArtist ratingArtist = new RatingArtist()
            .date(DEFAULT_DATE)
            .rating(DEFAULT_RATING);
        return ratingArtist;
    }

    @Before
    public void initTest() {
        ratingArtist = createEntity(em);
    }

    @Test
    @Transactional
    public void createRatingArtist() throws Exception {
        int databaseSizeBeforeCreate = ratingArtistRepository.findAll().size();

        // Create the RatingArtist
        restRatingArtistMockMvc.perform(post("/api/rating-artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingArtist)))
            .andExpect(status().isCreated());

        // Validate the RatingArtist in the database
        List<RatingArtist> ratingArtistList = ratingArtistRepository.findAll();
        assertThat(ratingArtistList).hasSize(databaseSizeBeforeCreate + 1);
        RatingArtist testRatingArtist = ratingArtistList.get(ratingArtistList.size() - 1);
        assertThat(testRatingArtist.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRatingArtist.getRating()).isEqualTo(DEFAULT_RATING);
    }

    @Test
    @Transactional
    public void createRatingArtistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ratingArtistRepository.findAll().size();

        // Create the RatingArtist with an existing ID
        ratingArtist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRatingArtistMockMvc.perform(post("/api/rating-artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingArtist)))
            .andExpect(status().isBadRequest());

        // Validate the RatingArtist in the database
        List<RatingArtist> ratingArtistList = ratingArtistRepository.findAll();
        assertThat(ratingArtistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRatingArtists() throws Exception {
        // Initialize the database
        ratingArtistRepository.saveAndFlush(ratingArtist);

        // Get all the ratingArtistList
        restRatingArtistMockMvc.perform(get("/api/rating-artists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ratingArtist.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }

    @Test
    @Transactional
    public void getRatingArtist() throws Exception {
        // Initialize the database
        ratingArtistRepository.saveAndFlush(ratingArtist);

        // Get the ratingArtist
        restRatingArtistMockMvc.perform(get("/api/rating-artists/{id}", ratingArtist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ratingArtist.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING));
    }

    @Test
    @Transactional
    public void getNonExistingRatingArtist() throws Exception {
        // Get the ratingArtist
        restRatingArtistMockMvc.perform(get("/api/rating-artists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRatingArtist() throws Exception {
        // Initialize the database
        ratingArtistRepository.saveAndFlush(ratingArtist);
        int databaseSizeBeforeUpdate = ratingArtistRepository.findAll().size();

        // Update the ratingArtist
        RatingArtist updatedRatingArtist = ratingArtistRepository.findOne(ratingArtist.getId());
        updatedRatingArtist
            .date(UPDATED_DATE)
            .rating(UPDATED_RATING);

        restRatingArtistMockMvc.perform(put("/api/rating-artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRatingArtist)))
            .andExpect(status().isOk());

        // Validate the RatingArtist in the database
        List<RatingArtist> ratingArtistList = ratingArtistRepository.findAll();
        assertThat(ratingArtistList).hasSize(databaseSizeBeforeUpdate);
        RatingArtist testRatingArtist = ratingArtistList.get(ratingArtistList.size() - 1);
        assertThat(testRatingArtist.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRatingArtist.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    public void updateNonExistingRatingArtist() throws Exception {
        int databaseSizeBeforeUpdate = ratingArtistRepository.findAll().size();

        // Create the RatingArtist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRatingArtistMockMvc.perform(put("/api/rating-artists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingArtist)))
            .andExpect(status().isCreated());

        // Validate the RatingArtist in the database
        List<RatingArtist> ratingArtistList = ratingArtistRepository.findAll();
        assertThat(ratingArtistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRatingArtist() throws Exception {
        // Initialize the database
        ratingArtistRepository.saveAndFlush(ratingArtist);
        int databaseSizeBeforeDelete = ratingArtistRepository.findAll().size();

        // Get the ratingArtist
        restRatingArtistMockMvc.perform(delete("/api/rating-artists/{id}", ratingArtist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RatingArtist> ratingArtistList = ratingArtistRepository.findAll();
        assertThat(ratingArtistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingArtist.class);
        RatingArtist ratingArtist1 = new RatingArtist();
        ratingArtist1.setId(1L);
        RatingArtist ratingArtist2 = new RatingArtist();
        ratingArtist2.setId(ratingArtist1.getId());
        assertThat(ratingArtist1).isEqualTo(ratingArtist2);
        ratingArtist2.setId(2L);
        assertThat(ratingArtist1).isNotEqualTo(ratingArtist2);
        ratingArtist1.setId(null);
        assertThat(ratingArtist1).isNotEqualTo(ratingArtist2);
    }
}
