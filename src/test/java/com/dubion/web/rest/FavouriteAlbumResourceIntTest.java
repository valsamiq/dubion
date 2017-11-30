package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.FavouriteAlbum;
import com.dubion.repository.FavouriteAlbumRepository;
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
 * Test class for the FavouriteAlbumResource REST controller.
 *
 * @see FavouriteAlbumResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class FavouriteAlbumResourceIntTest {

    private static final Boolean DEFAULT_LIKED = false;
    private static final Boolean UPDATED_LIKED = true;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FavouriteAlbumRepository favouriteAlbumRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFavouriteAlbumMockMvc;

    private FavouriteAlbum favouriteAlbum;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FavouriteAlbumResource favouriteAlbumResource = new FavouriteAlbumResource(favouriteAlbumRepository);
        this.restFavouriteAlbumMockMvc = MockMvcBuilders.standaloneSetup(favouriteAlbumResource)
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
    public static FavouriteAlbum createEntity(EntityManager em) {
        FavouriteAlbum favouriteAlbum = new FavouriteAlbum()
            .liked(DEFAULT_LIKED)
            .date(DEFAULT_DATE);
        return favouriteAlbum;
    }

    @Before
    public void initTest() {
        favouriteAlbum = createEntity(em);
    }

    @Test
    @Transactional
    public void createFavouriteAlbum() throws Exception {
        int databaseSizeBeforeCreate = favouriteAlbumRepository.findAll().size();

        // Create the FavouriteAlbum
        restFavouriteAlbumMockMvc.perform(post("/api/favourite-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteAlbum)))
            .andExpect(status().isCreated());

        // Validate the FavouriteAlbum in the database
        List<FavouriteAlbum> favouriteAlbumList = favouriteAlbumRepository.findAll();
        assertThat(favouriteAlbumList).hasSize(databaseSizeBeforeCreate + 1);
        FavouriteAlbum testFavouriteAlbum = favouriteAlbumList.get(favouriteAlbumList.size() - 1);
        assertThat(testFavouriteAlbum.isLiked()).isEqualTo(DEFAULT_LIKED);
        assertThat(testFavouriteAlbum.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createFavouriteAlbumWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = favouriteAlbumRepository.findAll().size();

        // Create the FavouriteAlbum with an existing ID
        favouriteAlbum.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFavouriteAlbumMockMvc.perform(post("/api/favourite-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteAlbum)))
            .andExpect(status().isBadRequest());

        // Validate the FavouriteAlbum in the database
        List<FavouriteAlbum> favouriteAlbumList = favouriteAlbumRepository.findAll();
        assertThat(favouriteAlbumList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFavouriteAlbums() throws Exception {
        // Initialize the database
        favouriteAlbumRepository.saveAndFlush(favouriteAlbum);

        // Get all the favouriteAlbumList
        restFavouriteAlbumMockMvc.perform(get("/api/favourite-albums?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(favouriteAlbum.getId().intValue())))
            .andExpect(jsonPath("$.[*].liked").value(hasItem(DEFAULT_LIKED.booleanValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getFavouriteAlbum() throws Exception {
        // Initialize the database
        favouriteAlbumRepository.saveAndFlush(favouriteAlbum);

        // Get the favouriteAlbum
        restFavouriteAlbumMockMvc.perform(get("/api/favourite-albums/{id}", favouriteAlbum.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(favouriteAlbum.getId().intValue()))
            .andExpect(jsonPath("$.liked").value(DEFAULT_LIKED.booleanValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFavouriteAlbum() throws Exception {
        // Get the favouriteAlbum
        restFavouriteAlbumMockMvc.perform(get("/api/favourite-albums/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFavouriteAlbum() throws Exception {
        // Initialize the database
        favouriteAlbumRepository.saveAndFlush(favouriteAlbum);
        int databaseSizeBeforeUpdate = favouriteAlbumRepository.findAll().size();

        // Update the favouriteAlbum
        FavouriteAlbum updatedFavouriteAlbum = favouriteAlbumRepository.findOne(favouriteAlbum.getId());
        updatedFavouriteAlbum
            .liked(UPDATED_LIKED)
            .date(UPDATED_DATE);

        restFavouriteAlbumMockMvc.perform(put("/api/favourite-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFavouriteAlbum)))
            .andExpect(status().isOk());

        // Validate the FavouriteAlbum in the database
        List<FavouriteAlbum> favouriteAlbumList = favouriteAlbumRepository.findAll();
        assertThat(favouriteAlbumList).hasSize(databaseSizeBeforeUpdate);
        FavouriteAlbum testFavouriteAlbum = favouriteAlbumList.get(favouriteAlbumList.size() - 1);
        assertThat(testFavouriteAlbum.isLiked()).isEqualTo(UPDATED_LIKED);
        assertThat(testFavouriteAlbum.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFavouriteAlbum() throws Exception {
        int databaseSizeBeforeUpdate = favouriteAlbumRepository.findAll().size();

        // Create the FavouriteAlbum

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFavouriteAlbumMockMvc.perform(put("/api/favourite-albums")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(favouriteAlbum)))
            .andExpect(status().isCreated());

        // Validate the FavouriteAlbum in the database
        List<FavouriteAlbum> favouriteAlbumList = favouriteAlbumRepository.findAll();
        assertThat(favouriteAlbumList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFavouriteAlbum() throws Exception {
        // Initialize the database
        favouriteAlbumRepository.saveAndFlush(favouriteAlbum);
        int databaseSizeBeforeDelete = favouriteAlbumRepository.findAll().size();

        // Get the favouriteAlbum
        restFavouriteAlbumMockMvc.perform(delete("/api/favourite-albums/{id}", favouriteAlbum.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FavouriteAlbum> favouriteAlbumList = favouriteAlbumRepository.findAll();
        assertThat(favouriteAlbumList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FavouriteAlbum.class);
        FavouriteAlbum favouriteAlbum1 = new FavouriteAlbum();
        favouriteAlbum1.setId(1L);
        FavouriteAlbum favouriteAlbum2 = new FavouriteAlbum();
        favouriteAlbum2.setId(favouriteAlbum1.getId());
        assertThat(favouriteAlbum1).isEqualTo(favouriteAlbum2);
        favouriteAlbum2.setId(2L);
        assertThat(favouriteAlbum1).isNotEqualTo(favouriteAlbum2);
        favouriteAlbum1.setId(null);
        assertThat(favouriteAlbum1).isNotEqualTo(favouriteAlbum2);
    }
}
