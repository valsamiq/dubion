package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.FavouriteBand;
import com.dubion.repository.FavouriteBandRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.dubion.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FavouriteBandResource REST controller.
 *
 * @see FavouriteBandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class FavouriteBandResourceIntTest {

    private static final Boolean DEFAULT_LIKED = false;
    private static final Boolean UPDATED_LIKED = true;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FavouriteBandRepository favouriteBandRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFavouriteBandMockMvc;

    private FavouriteBand favouriteBand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FavouriteBandResource favouriteBandResource = new FavouriteBandResource(favouriteBandRepository);
        this.restFavouriteBandMockMvc = MockMvcBuilders.standaloneSetup(favouriteBandResource)
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
    public static FavouriteBand createEntity(EntityManager em) {
        FavouriteBand favouriteBand = new FavouriteBand()
            .liked(DEFAULT_LIKED)
            .date(DEFAULT_DATE);
        return favouriteBand;
    }

    @Before
    public void initTest() {
        favouriteBand = createEntity(em);
    }

    @Test
    @Transactional
    public void createFavouriteBand() throws Exception {
        int databaseSizeBeforeCreate = favouriteBandRepository.findAll().size();

        // Create the FavouriteBand
        restFavouriteBandMockMvc.perform(post("/api/favourite-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteBand)))
            .andExpect(status().isCreated());

        // Validate the FavouriteBand in the database
        List<FavouriteBand> favouriteBandList = favouriteBandRepository.findAll();
        assertThat(favouriteBandList).hasSize(databaseSizeBeforeCreate + 1);
        FavouriteBand testFavouriteBand = favouriteBandList.get(favouriteBandList.size() - 1);
        assertThat(testFavouriteBand.isLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testFavouriteBand.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createFavouriteBandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = favouriteBandRepository.findAll().size();

        // Create the FavouriteBand with an existing ID
        favouriteBand.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFavouriteBandMockMvc.perform(post("/api/favourite-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteBand)))
            .andExpect(status().isBadRequest());

        // Validate the FavouriteBand in the database
        List<FavouriteBand> favouriteBandList = favouriteBandRepository.findAll();
        assertThat(favouriteBandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFavouriteBands() throws Exception {
        // Initialize the database
        favouriteBandRepository.saveAndFlush(favouriteBand);

        // Get all the favouriteBandList
        restFavouriteBandMockMvc.perform(get("/api/favourite-bands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(favouriteBand.getId().intValue())))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED.booleanValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getFavouriteBand() throws Exception {
        // Initialize the database
        favouriteBandRepository.saveAndFlush(favouriteBand);

        // Get the favouriteBand
        restFavouriteBandMockMvc.perform(get("/api/favourite-bands/{id}", favouriteBand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(favouriteBand.getId().intValue()))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED.booleanValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFavouriteBand() throws Exception {
        // Get the favouriteBand
        restFavouriteBandMockMvc.perform(get("/api/favourite-bands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFavouriteBand() throws Exception {
        // Initialize the database
        favouriteBandRepository.saveAndFlush(favouriteBand);
        int databaseSizeBeforeUpdate = favouriteBandRepository.findAll().size();

        // Update the favouriteBand
        FavouriteBand updatedFavouriteBand = favouriteBandRepository.findOne(favouriteBand.getId());
        updatedFavouriteBand
            .liked(UPDATED_LIKED)
            .date(UPDATED_DATE);

        restFavouriteBandMockMvc.perform(put("/api/favourite-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFavouriteBand)))
            .andExpect(status().isOk());

        // Validate the FavouriteBand in the database
        List<FavouriteBand> favouriteBandList = favouriteBandRepository.findAll();
        assertThat(favouriteBandList).hasSize(databaseSizeBeforeUpdate);
        FavouriteBand testFavouriteBand = favouriteBandList.get(favouriteBandList.size() - 1);
        assertThat(testFavouriteBand.isLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testFavouriteBand.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFavouriteBand() throws Exception {
        int databaseSizeBeforeUpdate = favouriteBandRepository.findAll().size();

        // Create the FavouriteBand

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFavouriteBandMockMvc.perform(put("/api/favourite-bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteBand)))
            .andExpect(status().isCreated());

        // Validate the FavouriteBand in the database
        List<FavouriteBand> favouriteBandList = favouriteBandRepository.findAll();
        assertThat(favouriteBandList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFavouriteBand() throws Exception {
        // Initialize the database
        favouriteBandRepository.saveAndFlush(favouriteBand);
        int databaseSizeBeforeDelete = favouriteBandRepository.findAll().size();

        // Get the favouriteBand
        restFavouriteBandMockMvc.perform(delete("/api/favourite-bands/{id}", favouriteBand.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FavouriteBand> favouriteBandList = favouriteBandRepository.findAll();
        assertThat(favouriteBandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteBand.class);
        FavouriteBand favouriteBand1 = new FavouriteBand();
        favouriteBand1.setId(1L);
        FavouriteBand favouriteBand2 = new FavouriteBand();
        favouriteBand2.setId(favouriteBand1.getId());
        assertThat(favouriteBand1).isEqualTo(favouriteBand2);
        favouriteBand2.setId(2L);
        assertThat(favouriteBand1).isNotEqualTo(favouriteBand2);
        favouriteBand1.setId(null);
        assertThat(favouriteBand1).isNotEqualTo(favouriteBand2);
    }
}
