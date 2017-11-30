package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.RatingAlbum;
import com.dubion.repository.RatingAlbumRepository;
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
 * Test class for the RatingAlbumResource REST controller.
 *
 * @see RatingAlbumResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class RatingAlbumResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    @Autowired
    private RatingAlbumRepository ratingAlbumRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRatingAlbumMockMvc;

    private RatingAlbum ratingAlbum;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RatingAlbumResource ratingAlbumResource = new RatingAlbumResource(ratingAlbumRepository);
        this.restRatingAlbumMockMvc = MockMvcBuilders.standaloneSetup(ratingAlbumResource)
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
    public static RatingAlbum createEntity(EntityManager em) {
        RatingAlbum ratingAlbum = new RatingAlbum()
            .date(DEFAULT_DATE)
            .rating(DEFAULT_RATING);
        return ratingAlbum;
    }

    @Before
    public void initTest() {
        ratingAlbum = createEntity(em);
    }

    @Test
    @Transactional
    public void createRatingAlbum() throws Exception {
        int databaseSizeBeforeCreate = ratingAlbumRepository.findAll().size();

        // Create the RatingAlbum
        restRatingAlbumMockMvc.perform(post("/api/rating-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingAlbum)))
            .andExpect(status().isCreated());

        // Validate the RatingAlbum in the database
        List<RatingAlbum> ratingAlbumList = ratingAlbumRepository.findAll();
        assertThat(ratingAlbumList).hasSize(databaseSizeBeforeCreate + 1);
        RatingAlbum testRatingAlbum = ratingAlbumList.get(ratingAlbumList.size() - 1);
        assertThat(testRatingAlbum.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRatingAlbum.getRating()).isEqualTo(DEFAULT_RATING);
    }

    @Test
    @Transactional
    public void createRatingAlbumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ratingAlbumRepository.findAll().size();

        // Create the RatingAlbum with an existing ID
        ratingAlbum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRatingAlbumMockMvc.perform(post("/api/rating-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingAlbum)))
            .andExpect(status().isBadRequest());

        // Validate the RatingAlbum in the database
        List<RatingAlbum> ratingAlbumList = ratingAlbumRepository.findAll();
        assertThat(ratingAlbumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRatingAlbums() throws Exception {
        // Initialize the database
        ratingAlbumRepository.saveAndFlush(ratingAlbum);

        // Get all the ratingAlbumList
        restRatingAlbumMockMvc.perform(get("/api/rating-albums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ratingAlbum.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }

    @Test
    @Transactional
    public void getRatingAlbum() throws Exception {
        // Initialize the database
        ratingAlbumRepository.saveAndFlush(ratingAlbum);

        // Get the ratingAlbum
        restRatingAlbumMockMvc.perform(get("/api/rating-albums/{id}", ratingAlbum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ratingAlbum.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING));
    }

    @Test
    @Transactional
    public void getNonExistingRatingAlbum() throws Exception {
        // Get the ratingAlbum
        restRatingAlbumMockMvc.perform(get("/api/rating-albums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRatingAlbum() throws Exception {
        // Initialize the database
        ratingAlbumRepository.saveAndFlush(ratingAlbum);
        int databaseSizeBeforeUpdate = ratingAlbumRepository.findAll().size();

        // Update the ratingAlbum
        RatingAlbum updatedRatingAlbum = ratingAlbumRepository.findOne(ratingAlbum.getId());
        updatedRatingAlbum
            .date(UPDATED_DATE)
            .rating(UPDATED_RATING);

        restRatingAlbumMockMvc.perform(put("/api/rating-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRatingAlbum)))
            .andExpect(status().isOk());

        // Validate the RatingAlbum in the database
        List<RatingAlbum> ratingAlbumList = ratingAlbumRepository.findAll();
        assertThat(ratingAlbumList).hasSize(databaseSizeBeforeUpdate);
        RatingAlbum testRatingAlbum = ratingAlbumList.get(ratingAlbumList.size() - 1);
        assertThat(testRatingAlbum.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRatingAlbum.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    public void updateNonExistingRatingAlbum() throws Exception {
        int databaseSizeBeforeUpdate = ratingAlbumRepository.findAll().size();

        // Create the RatingAlbum

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRatingAlbumMockMvc.perform(put("/api/rating-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingAlbum)))
            .andExpect(status().isCreated());

        // Validate the RatingAlbum in the database
        List<RatingAlbum> ratingAlbumList = ratingAlbumRepository.findAll();
        assertThat(ratingAlbumList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRatingAlbum() throws Exception {
        // Initialize the database
        ratingAlbumRepository.saveAndFlush(ratingAlbum);
        int databaseSizeBeforeDelete = ratingAlbumRepository.findAll().size();

        // Get the ratingAlbum
        restRatingAlbumMockMvc.perform(delete("/api/rating-albums/{id}", ratingAlbum.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RatingAlbum> ratingAlbumList = ratingAlbumRepository.findAll();
        assertThat(ratingAlbumList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingAlbum.class);
        RatingAlbum ratingAlbum1 = new RatingAlbum();
        ratingAlbum1.setId(1L);
        RatingAlbum ratingAlbum2 = new RatingAlbum();
        ratingAlbum2.setId(ratingAlbum1.getId());
        assertThat(ratingAlbum1).isEqualTo(ratingAlbum2);
        ratingAlbum2.setId(2L);
        assertThat(ratingAlbum1).isNotEqualTo(ratingAlbum2);
        ratingAlbum1.setId(null);
        assertThat(ratingAlbum1).isNotEqualTo(ratingAlbum2);
    }
}
