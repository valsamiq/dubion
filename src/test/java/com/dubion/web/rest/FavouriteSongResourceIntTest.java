package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.FavouriteSong;
import com.dubion.repository.FavouriteSongRepository;
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
 * Test class for the FavouriteSongResource REST controller.
 *
 * @see FavouriteSongResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class FavouriteSongResourceIntTest {

    private static final Boolean DEFAULT_LIKED = false;
    private static final Boolean UPDATED_LIKED = true;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FavouriteSongRepository favouriteSongRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFavouriteSongMockMvc;

    private FavouriteSong favouriteSong;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FavouriteSongResource favouriteSongResource = new FavouriteSongResource(favouriteSongRepository);
        this.restFavouriteSongMockMvc = MockMvcBuilders.standaloneSetup(favouriteSongResource)
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
    public static FavouriteSong createEntity(EntityManager em) {
        FavouriteSong favouriteSong = new FavouriteSong()
            .liked(DEFAULT_LIKED)
            .date(DEFAULT_DATE);
        return favouriteSong;
    }

    @Before
    public void initTest() {
        favouriteSong = createEntity(em);
    }

    @Test
    @Transactional
    public void createFavouriteSong() throws Exception {
        int databaseSizeBeforeCreate = favouriteSongRepository.findAll().size();

        // Create the FavouriteSong
        restFavouriteSongMockMvc.perform(post("/api/favourite-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteSong)))
            .andExpect(status().isCreated());

        // Validate the FavouriteSong in the database
        List<FavouriteSong> favouriteSongList = favouriteSongRepository.findAll();
        assertThat(favouriteSongList).hasSize(databaseSizeBeforeCreate + 1);
        FavouriteSong testFavouriteSong = favouriteSongList.get(favouriteSongList.size() - 1);
        assertThat(testFavouriteSong.isLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testFavouriteSong.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createFavouriteSongWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = favouriteSongRepository.findAll().size();

        // Create the FavouriteSong with an existing ID
        favouriteSong.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFavouriteSongMockMvc.perform(post("/api/favourite-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteSong)))
            .andExpect(status().isBadRequest());

        // Validate the FavouriteSong in the database
        List<FavouriteSong> favouriteSongList = favouriteSongRepository.findAll();
        assertThat(favouriteSongList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFavouriteSongs() throws Exception {
        // Initialize the database
        favouriteSongRepository.saveAndFlush(favouriteSong);

        // Get all the favouriteSongList
        restFavouriteSongMockMvc.perform(get("/api/favourite-songs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(favouriteSong.getId().intValue())))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED.booleanValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getFavouriteSong() throws Exception {
        // Initialize the database
        favouriteSongRepository.saveAndFlush(favouriteSong);

        // Get the favouriteSong
        restFavouriteSongMockMvc.perform(get("/api/favourite-songs/{id}", favouriteSong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(favouriteSong.getId().intValue()))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED.booleanValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFavouriteSong() throws Exception {
        // Get the favouriteSong
        restFavouriteSongMockMvc.perform(get("/api/favourite-songs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFavouriteSong() throws Exception {
        // Initialize the database
        favouriteSongRepository.saveAndFlush(favouriteSong);
        int databaseSizeBeforeUpdate = favouriteSongRepository.findAll().size();

        // Update the favouriteSong
        FavouriteSong updatedFavouriteSong = favouriteSongRepository.findOne(favouriteSong.getId());
        updatedFavouriteSong
            .liked(UPDATED_LIKED)
            .date(UPDATED_DATE);

        restFavouriteSongMockMvc.perform(put("/api/favourite-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFavouriteSong)))
            .andExpect(status().isOk());

        // Validate the FavouriteSong in the database
        List<FavouriteSong> favouriteSongList = favouriteSongRepository.findAll();
        assertThat(favouriteSongList).hasSize(databaseSizeBeforeUpdate);
        FavouriteSong testFavouriteSong = favouriteSongList.get(favouriteSongList.size() - 1);
        assertThat(testFavouriteSong.isLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testFavouriteSong.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFavouriteSong() throws Exception {
        int databaseSizeBeforeUpdate = favouriteSongRepository.findAll().size();

        // Create the FavouriteSong

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFavouriteSongMockMvc.perform(put("/api/favourite-songs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteSong)))
            .andExpect(status().isCreated());

        // Validate the FavouriteSong in the database
        List<FavouriteSong> favouriteSongList = favouriteSongRepository.findAll();
        assertThat(favouriteSongList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFavouriteSong() throws Exception {
        // Initialize the database
        favouriteSongRepository.saveAndFlush(favouriteSong);
        int databaseSizeBeforeDelete = favouriteSongRepository.findAll().size();

        // Get the favouriteSong
        restFavouriteSongMockMvc.perform(delete("/api/favourite-songs/{id}", favouriteSong.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FavouriteSong> favouriteSongList = favouriteSongRepository.findAll();
        assertThat(favouriteSongList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteSong.class);
        FavouriteSong favouriteSong1 = new FavouriteSong();
        favouriteSong1.setId(1L);
        FavouriteSong favouriteSong2 = new FavouriteSong();
        favouriteSong2.setId(favouriteSong1.getId());
        assertThat(favouriteSong1).isEqualTo(favouriteSong2);
        favouriteSong2.setId(2L);
        assertThat(favouriteSong1).isNotEqualTo(favouriteSong2);
        favouriteSong1.setId(null);
        assertThat(favouriteSong1).isNotEqualTo(favouriteSong2);
    }
}
