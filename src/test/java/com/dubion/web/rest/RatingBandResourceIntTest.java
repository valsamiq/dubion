package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.RatingBand;
import com.dubion.repository.RatingBandRepository;
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
 * Test class for the RatingBandResource REST controller.
 *
 * @see RatingBandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class RatingBandResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    @Autowired
    private RatingBandRepository ratingBandRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRatingBandMockMvc;

    private RatingBand ratingBand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RatingBandResource ratingBandResource = new RatingBandResource(ratingBandRepository);
        this.restRatingBandMockMvc = MockMvcBuilders.standaloneSetup(ratingBandResource)
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
    public static RatingBand createEntity(EntityManager em) {
        RatingBand ratingBand = new RatingBand()
            .date(DEFAULT_DATE)
            .rating(DEFAULT_RATING);
        return ratingBand;
    }

    @Before
    public void initTest() {
        ratingBand = createEntity(em);
    }

    @Test
    @Transactional
    public void createRatingBand() throws Exception {
        int databaseSizeBeforeCreate = ratingBandRepository.findAll().size();

        // Create the RatingBand
        restRatingBandMockMvc.perform(post("/api/rating-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingBand)))
            .andExpect(status().isCreated());

        // Validate the RatingBand in the database
        List<RatingBand> ratingBandList = ratingBandRepository.findAll();
        assertThat(ratingBandList).hasSize(databaseSizeBeforeCreate + 1);
        RatingBand testRatingBand = ratingBandList.get(ratingBandList.size() - 1);
        assertThat(testRatingBand.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testRatingBand.getRating()).isEqualTo(DEFAULT_RATING);
    }

    @Test
    @Transactional
    public void createRatingBandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ratingBandRepository.findAll().size();

        // Create the RatingBand with an existing ID
        ratingBand.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRatingBandMockMvc.perform(post("/api/rating-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingBand)))
            .andExpect(status().isBadRequest());

        // Validate the RatingBand in the database
        List<RatingBand> ratingBandList = ratingBandRepository.findAll();
        assertThat(ratingBandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRatingBands() throws Exception {
        // Initialize the database
        ratingBandRepository.saveAndFlush(ratingBand);

        // Get all the ratingBandList
        restRatingBandMockMvc.perform(get("/api/rating-bands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ratingBand.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }

    @Test
    @Transactional
    public void getRatingBand() throws Exception {
        // Initialize the database
        ratingBandRepository.saveAndFlush(ratingBand);

        // Get the ratingBand
        restRatingBandMockMvc.perform(get("/api/rating-bands/{id}", ratingBand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ratingBand.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING));
    }

    @Test
    @Transactional
    public void getNonExistingRatingBand() throws Exception {
        // Get the ratingBand
        restRatingBandMockMvc.perform(get("/api/rating-bands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRatingBand() throws Exception {
        // Initialize the database
        ratingBandRepository.saveAndFlush(ratingBand);
        int databaseSizeBeforeUpdate = ratingBandRepository.findAll().size();

        // Update the ratingBand
        RatingBand updatedRatingBand = ratingBandRepository.findOne(ratingBand.getId());
        updatedRatingBand
            .date(UPDATED_DATE)
            .rating(UPDATED_RATING);

        restRatingBandMockMvc.perform(put("/api/rating-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRatingBand)))
            .andExpect(status().isOk());

        // Validate the RatingBand in the database
        List<RatingBand> ratingBandList = ratingBandRepository.findAll();
        assertThat(ratingBandList).hasSize(databaseSizeBeforeUpdate);
        RatingBand testRatingBand = ratingBandList.get(ratingBandList.size() - 1);
        assertThat(testRatingBand.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testRatingBand.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    public void updateNonExistingRatingBand() throws Exception {
        int databaseSizeBeforeUpdate = ratingBandRepository.findAll().size();

        // Create the RatingBand

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRatingBandMockMvc.perform(put("/api/rating-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ratingBand)))
            .andExpect(status().isCreated());

        // Validate the RatingBand in the database
        List<RatingBand> ratingBandList = ratingBandRepository.findAll();
        assertThat(ratingBandList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRatingBand() throws Exception {
        // Initialize the database
        ratingBandRepository.saveAndFlush(ratingBand);
        int databaseSizeBeforeDelete = ratingBandRepository.findAll().size();

        // Get the ratingBand
        restRatingBandMockMvc.perform(delete("/api/rating-bands/{id}", ratingBand.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RatingBand> ratingBandList = ratingBandRepository.findAll();
        assertThat(ratingBandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingBand.class);
        RatingBand ratingBand1 = new RatingBand();
        ratingBand1.setId(1L);
        RatingBand ratingBand2 = new RatingBand();
        ratingBand2.setId(ratingBand1.getId());
        assertThat(ratingBand1).isEqualTo(ratingBand2);
        ratingBand2.setId(2L);
        assertThat(ratingBand1).isNotEqualTo(ratingBand2);
        ratingBand1.setId(null);
        assertThat(ratingBand1).isNotEqualTo(ratingBand2);
    }
}
