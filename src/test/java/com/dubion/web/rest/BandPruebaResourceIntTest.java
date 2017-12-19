package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.BandPrueba;
import com.dubion.repository.BandPruebaRepository;
import com.dubion.service.BandPruebaService;
import com.dubion.web.rest.errors.ExceptionTranslator;
import com.dubion.service.dto.BandPruebaCriteria;
import com.dubion.service.BandPruebaQueryService;

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
import org.springframework.util.Base64Utils;

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
 * Test class for the BandPruebaResource REST controller.
 *
 * @see BandPruebaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class BandPruebaResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTHDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private BandPruebaRepository bandPruebaRepository;

    @Autowired
    private BandPruebaService bandPruebaService;

    @Autowired
    private BandPruebaQueryService bandPruebaQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBandPruebaMockMvc;

    private BandPrueba bandPrueba;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BandPruebaResource bandPruebaResource = new BandPruebaResource(bandPruebaService, bandPruebaQueryService);
        this.restBandPruebaMockMvc = MockMvcBuilders.standaloneSetup(bandPruebaResource)
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
    public static BandPrueba createEntity(EntityManager em) {
        BandPrueba bandPrueba = new BandPrueba()
            .name(DEFAULT_NAME)
            .birthdate(DEFAULT_BIRTHDATE)
            .bio(DEFAULT_BIO)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE);
        return bandPrueba;
    }

    @Before
    public void initTest() {
        bandPrueba = createEntity(em);
    }

    @Test
    @Transactional
    public void createBandPrueba() throws Exception {
        int databaseSizeBeforeCreate = bandPruebaRepository.findAll().size();

        // Create the BandPrueba
        restBandPruebaMockMvc.perform(post("/api/band-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandPrueba)))
            .andExpect(status().isCreated());

        // Validate the BandPrueba in the database
        List<BandPrueba> bandPruebaList = bandPruebaRepository.findAll();
        assertThat(bandPruebaList).hasSize(databaseSizeBeforeCreate + 1);
        BandPrueba testBandPrueba = bandPruebaList.get(bandPruebaList.size() - 1);
        assertThat(testBandPrueba.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBandPrueba.getBirthdate()).isEqualTo(DEFAULT_BIRTHDATE);
        assertThat(testBandPrueba.getBio()).isEqualTo(DEFAULT_BIO);
        assertThat(testBandPrueba.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testBandPrueba.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createBandPruebaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bandPruebaRepository.findAll().size();

        // Create the BandPrueba with an existing ID
        bandPrueba.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBandPruebaMockMvc.perform(post("/api/band-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandPrueba)))
            .andExpect(status().isBadRequest());

        // Validate the BandPrueba in the database
        List<BandPrueba> bandPruebaList = bandPruebaRepository.findAll();
        assertThat(bandPruebaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBandPruebas() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList
        restBandPruebaMockMvc.perform(get("/api/band-pruebas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bandPrueba.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))));
    }

    @Test
    @Transactional
    public void getBandPrueba() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get the bandPrueba
        restBandPruebaMockMvc.perform(get("/api/band-pruebas/{id}", bandPrueba.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bandPrueba.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)));
    }

    @Test
    @Transactional
    public void getAllBandPruebasByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where name equals to DEFAULT_NAME
        defaultBandPruebaShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the bandPruebaList where name equals to UPDATED_NAME
        defaultBandPruebaShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByNameIsInShouldWork() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where name in DEFAULT_NAME or UPDATED_NAME
        defaultBandPruebaShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the bandPruebaList where name equals to UPDATED_NAME
        defaultBandPruebaShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where name is not null
        defaultBandPruebaShouldBeFound("name.specified=true");

        // Get all the bandPruebaList where name is null
        defaultBandPruebaShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBirthdateIsEqualToSomething() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where birthdate equals to DEFAULT_BIRTHDATE
        defaultBandPruebaShouldBeFound("birthdate.equals=" + DEFAULT_BIRTHDATE);

        // Get all the bandPruebaList where birthdate equals to UPDATED_BIRTHDATE
        defaultBandPruebaShouldNotBeFound("birthdate.equals=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBirthdateIsInShouldWork() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where birthdate in DEFAULT_BIRTHDATE or UPDATED_BIRTHDATE
        defaultBandPruebaShouldBeFound("birthdate.in=" + DEFAULT_BIRTHDATE + "," + UPDATED_BIRTHDATE);

        // Get all the bandPruebaList where birthdate equals to UPDATED_BIRTHDATE
        defaultBandPruebaShouldNotBeFound("birthdate.in=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBirthdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where birthdate is not null
        defaultBandPruebaShouldBeFound("birthdate.specified=true");

        // Get all the bandPruebaList where birthdate is null
        defaultBandPruebaShouldNotBeFound("birthdate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBirthdateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where birthdate greater than or equals to DEFAULT_BIRTHDATE
        defaultBandPruebaShouldBeFound("birthdate.greaterOrEqualThan=" + DEFAULT_BIRTHDATE);

        // Get all the bandPruebaList where birthdate greater than or equals to UPDATED_BIRTHDATE
        defaultBandPruebaShouldNotBeFound("birthdate.greaterOrEqualThan=" + UPDATED_BIRTHDATE);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBirthdateIsLessThanSomething() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where birthdate less than or equals to DEFAULT_BIRTHDATE
        defaultBandPruebaShouldNotBeFound("birthdate.lessThan=" + DEFAULT_BIRTHDATE);

        // Get all the bandPruebaList where birthdate less than or equals to UPDATED_BIRTHDATE
        defaultBandPruebaShouldBeFound("birthdate.lessThan=" + UPDATED_BIRTHDATE);
    }


    @Test
    @Transactional
    public void getAllBandPruebasByBioIsEqualToSomething() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where bio equals to DEFAULT_BIO
        defaultBandPruebaShouldBeFound("bio.equals=" + DEFAULT_BIO);

        // Get all the bandPruebaList where bio equals to UPDATED_BIO
        defaultBandPruebaShouldNotBeFound("bio.equals=" + UPDATED_BIO);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBioIsInShouldWork() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where bio in DEFAULT_BIO or UPDATED_BIO
        defaultBandPruebaShouldBeFound("bio.in=" + DEFAULT_BIO + "," + UPDATED_BIO);

        // Get all the bandPruebaList where bio equals to UPDATED_BIO
        defaultBandPruebaShouldNotBeFound("bio.in=" + UPDATED_BIO);
    }

    @Test
    @Transactional
    public void getAllBandPruebasByBioIsNullOrNotNull() throws Exception {
        // Initialize the database
        bandPruebaRepository.saveAndFlush(bandPrueba);

        // Get all the bandPruebaList where bio is not null
        defaultBandPruebaShouldBeFound("bio.specified=true");

        // Get all the bandPruebaList where bio is null
        defaultBandPruebaShouldNotBeFound("bio.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBandPruebaShouldBeFound(String filter) throws Exception {
        restBandPruebaMockMvc.perform(get("/api/band-pruebas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bandPrueba.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBandPruebaShouldNotBeFound(String filter) throws Exception {
        restBandPruebaMockMvc.perform(get("/api/band-pruebas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingBandPrueba() throws Exception {
        // Get the bandPrueba
        restBandPruebaMockMvc.perform(get("/api/band-pruebas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBandPrueba() throws Exception {
        // Initialize the database
        bandPruebaService.save(bandPrueba);

        int databaseSizeBeforeUpdate = bandPruebaRepository.findAll().size();

        // Update the bandPrueba
        BandPrueba updatedBandPrueba = bandPruebaRepository.findOne(bandPrueba.getId());
        updatedBandPrueba
            .name(UPDATED_NAME)
            .birthdate(UPDATED_BIRTHDATE)
            .bio(UPDATED_BIO)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE);

        restBandPruebaMockMvc.perform(put("/api/band-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBandPrueba)))
            .andExpect(status().isOk());

        // Validate the BandPrueba in the database
        List<BandPrueba> bandPruebaList = bandPruebaRepository.findAll();
        assertThat(bandPruebaList).hasSize(databaseSizeBeforeUpdate);
        BandPrueba testBandPrueba = bandPruebaList.get(bandPruebaList.size() - 1);
        assertThat(testBandPrueba.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBandPrueba.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testBandPrueba.getBio()).isEqualTo(UPDATED_BIO);
        assertThat(testBandPrueba.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testBandPrueba.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBandPrueba() throws Exception {
        int databaseSizeBeforeUpdate = bandPruebaRepository.findAll().size();

        // Create the BandPrueba

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBandPruebaMockMvc.perform(put("/api/band-pruebas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandPrueba)))
            .andExpect(status().isCreated());

        // Validate the BandPrueba in the database
        List<BandPrueba> bandPruebaList = bandPruebaRepository.findAll();
        assertThat(bandPruebaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBandPrueba() throws Exception {
        // Initialize the database
        bandPruebaService.save(bandPrueba);

        int databaseSizeBeforeDelete = bandPruebaRepository.findAll().size();

        // Get the bandPrueba
        restBandPruebaMockMvc.perform(delete("/api/band-pruebas/{id}", bandPrueba.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BandPrueba> bandPruebaList = bandPruebaRepository.findAll();
        assertThat(bandPruebaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BandPrueba.class);
        BandPrueba bandPrueba1 = new BandPrueba();
        bandPrueba1.setId(1L);
        BandPrueba bandPrueba2 = new BandPrueba();
        bandPrueba2.setId(bandPrueba1.getId());
        assertThat(bandPrueba1).isEqualTo(bandPrueba2);
        bandPrueba2.setId(2L);
        assertThat(bandPrueba1).isNotEqualTo(bandPrueba2);
        bandPrueba1.setId(null);
        assertThat(bandPrueba1).isNotEqualTo(bandPrueba2);
    }
}
