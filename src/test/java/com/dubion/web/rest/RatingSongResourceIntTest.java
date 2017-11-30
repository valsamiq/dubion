package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.RatingSong;
import com.dubion.repository.RatingSongRepository;
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
 * Test class for the RatingSongResource REST controller.
 *
 * @see RatingSongResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class RatingSongResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    @Autowired
    private RatingSongRepository ratingSongRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRatingSongMockMvc;

    private RatingSong ratingSong;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RatingSongResource ratingSongResource = new RatingSongResource(ratingSongRepository);
        this.restRatingSongMockMvc = MockMvcBuilders.standaloneSetup(ratingSongResource)
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
    public static RatingSong createEntity(EntityManager em) {
        RatingSong ratingSong = new RatingSong()
            .date(DEFAULT_DATE)
            .rating(DEFAULT_RATING);
        return ratingSong;
    }

    @Before
    public void initTest() {
        ratingSong = createEntity(em);
    }

    @Test
    @Transactional
    public void createRatingSong() throws Exception {
        int databaseSizeBeforeCreate = ratingSongRepository.findAll().size();

        // Create the RatingSong
        restRatingSongMockMvc.perform(post("/api/rating-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingSong)))
            .andExpect(status().isCreated());

        // Validate the RatingSong in the database
        List<RatingSong> ratingSongList = ratingSongRepository.findAll();
        assertThat(ratingSongList).hasSize(databaseSizeBeforeCreate + 1);
        RatingSong testRatingSong = ratingSongList.get(ratingSongList.size() - 1);
        assertThat(testRatingSong.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRatingSong.getRating()).isEqualTo(DEFAULT_RATING);
    }

    @Test
    @Transactional
    public void createRatingSongWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ratingSongRepository.findAll().size();

        // Create the RatingSong with an existing ID
        ratingSong.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRatingSongMockMvc.perform(post("/api/rating-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingSong)))
            .andExpect(status().isBadRequest());

        // Validate the RatingSong in the database
        List<RatingSong> ratingSongList = ratingSongRepository.findAll();
        assertThat(ratingSongList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRatingSongs() throws Exception {
        // Initialize the database
        ratingSongRepository.saveAndFlush(ratingSong);

        // Get all the ratingSongList
        restRatingSongMockMvc.perform(get("/api/rating-songs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ratingSong.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }

    @Test
    @Transactional
    public void getRatingSong() throws Exception {
        // Initialize the database
        ratingSongRepository.saveAndFlush(ratingSong);

        // Get the ratingSong
        restRatingSongMockMvc.perform(get("/api/rating-songs/{id}", ratingSong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ratingSong.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING));
    }

    @Test
    @Transactional
    public void getNonExistingRatingSong() throws Exception {
        // Get the ratingSong
        restRatingSongMockMvc.perform(get("/api/rating-songs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRatingSong() throws Exception {
        // Initialize the database
        ratingSongRepository.saveAndFlush(ratingSong);
        int databaseSizeBeforeUpdate = ratingSongRepository.findAll().size();

        // Update the ratingSong
        RatingSong updatedRatingSong = ratingSongRepository.findOne(ratingSong.getId());
        updatedRatingSong
            .date(UPDATED_DATE)
            .rating(UPDATED_RATING);

        restRatingSongMockMvc.perform(put("/api/rating-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRatingSong)))
            .andExpect(status().isOk());

        // Validate the RatingSong in the database
        List<RatingSong> ratingSongList = ratingSongRepository.findAll();
        assertThat(ratingSongList).hasSize(databaseSizeBeforeUpdate);
        RatingSong testRatingSong = ratingSongList.get(ratingSongList.size() - 1);
        assertThat(testRatingSong.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRatingSong.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    public void updateNonExistingRatingSong() throws Exception {
        int databaseSizeBeforeUpdate = ratingSongRepository.findAll().size();

        // Create the RatingSong

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRatingSongMockMvc.perform(put("/api/rating-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingSong)))
            .andExpect(status().isCreated());

        // Validate the RatingSong in the database
        List<RatingSong> ratingSongList = ratingSongRepository.findAll();
        assertThat(ratingSongList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRatingSong() throws Exception {
        // Initialize the database
        ratingSongRepository.saveAndFlush(ratingSong);
        int databaseSizeBeforeDelete = ratingSongRepository.findAll().size();

        // Get the ratingSong
        restRatingSongMockMvc.perform(delete("/api/rating-songs/{id}", ratingSong.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RatingSong> ratingSongList = ratingSongRepository.findAll();
        assertThat(ratingSongList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingSong.class);
        RatingSong ratingSong1 = new RatingSong();
        ratingSong1.setId(1L);
        RatingSong ratingSong2 = new RatingSong();
        ratingSong2.setId(ratingSong1.getId());
        assertThat(ratingSong1).isEqualTo(ratingSong2);
        ratingSong2.setId(2L);
        assertThat(ratingSong1).isNotEqualTo(ratingSong2);
        ratingSong1.setId(null);
        assertThat(ratingSong1).isNotEqualTo(ratingSong2);
    }
}
