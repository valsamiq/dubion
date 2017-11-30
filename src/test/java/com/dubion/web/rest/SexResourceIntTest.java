package com.dubion.web.rest;

import com.dubion.DubionApp;

import com.dubion.domain.Sex;
import com.dubion.repository.SexRepository;
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
import java.util.List;

import static com.dubion.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SexResource REST controller.
 *
 * @see SexResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubionApp.class)
public class SexResourceIntTest {

    private static final String DEFAULT_ORIENTATION = "AAAAAAAAAA";
    private static final String UPDATED_ORIENTATION = "BBBBBBBBBB";

    @Autowired
    private SexRepository sexRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSexMockMvc;

    private Sex sex;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SexResource sexResource = new SexResource(sexRepository);
        this.restSexMockMvc = MockMvcBuilders.standaloneSetup(sexResource)
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
    public static Sex createEntity(EntityManager em) {
        Sex sex = new Sex()
            .orientation(DEFAULT_ORIENTATION);
        return sex;
    }

    @Before
    public void initTest() {
        sex = createEntity(em);
    }

    @Test
    @Transactional
    public void createSex() throws Exception {
        int databaseSizeBeforeCreate = sexRepository.findAll().size();

        // Create the Sex
        restSexMockMvc.perform(post("/api/sexes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sex)))
            .andExpect(status().isCreated());

        // Validate the Sex in the database
        List<Sex> sexList = sexRepository.findAll();
        assertThat(sexList).hasSize(databaseSizeBeforeCreate + 1);
        Sex testSex = sexList.get(sexList.size() - 1);
        assertThat(testSex.getOrientation()).isEqualTo(DEFAULT_ORIENTATION);
    }

    @Test
    @Transactional
    public void createSexWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sexRepository.findAll().size();

        // Create the Sex with an existing ID
        sex.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSexMockMvc.perform(post("/api/sexes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sex)))
            .andExpect(status().isBadRequest());

        // Validate the Sex in the database
        List<Sex> sexList = sexRepository.findAll();
        assertThat(sexList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSexes() throws Exception {
        // Initialize the database
        sexRepository.saveAndFlush(sex);

        // Get all the sexList
        restSexMockMvc.perform(get("/api/sexes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sex.getId().intValue())))
            .andExpect(jsonPath("$.[*].orientation").value(hasItem(DEFAULT_ORIENTATION.toString())));
    }

    @Test
    @Transactional
    public void getSex() throws Exception {
        // Initialize the database
        sexRepository.saveAndFlush(sex);

        // Get the sex
        restSexMockMvc.perform(get("/api/sexes/{id}", sex.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sex.getId().intValue()))
            .andExpect(jsonPath("$.orientation").value(DEFAULT_ORIENTATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSex() throws Exception {
        // Get the sex
        restSexMockMvc.perform(get("/api/sexes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSex() throws Exception {
        // Initialize the database
        sexRepository.saveAndFlush(sex);
        int databaseSizeBeforeUpdate = sexRepository.findAll().size();

        // Update the sex
        Sex updatedSex = sexRepository.findOne(sex.getId());
        updatedSex
            .orientation(UPDATED_ORIENTATION);

        restSexMockMvc.perform(put("/api/sexes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSex)))
            .andExpect(status().isOk());

        // Validate the Sex in the database
        List<Sex> sexList = sexRepository.findAll();
        assertThat(sexList).hasSize(databaseSizeBeforeUpdate);
        Sex testSex = sexList.get(sexList.size() - 1);
        assertThat(testSex.getOrientation()).isEqualTo(UPDATED_ORIENTATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSex() throws Exception {
        int databaseSizeBeforeUpdate = sexRepository.findAll().size();

        // Create the Sex

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSexMockMvc.perform(put("/api/sexes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sex)))
            .andExpect(status().isCreated());

        // Validate the Sex in the database
        List<Sex> sexList = sexRepository.findAll();
        assertThat(sexList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSex() throws Exception {
        // Initialize the database
        sexRepository.saveAndFlush(sex);
        int databaseSizeBeforeDelete = sexRepository.findAll().size();

        // Get the sex
        restSexMockMvc.perform(delete("/api/sexes/{id}", sex.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sex> sexList = sexRepository.findAll();
        assertThat(sexList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sex.class);
        Sex sex1 = new Sex();
        sex1.setId(1L);
        Sex sex2 = new Sex();
        sex2.setId(sex1.getId());
        assertThat(sex1).isEqualTo(sex2);
        sex2.setId(2L);
        assertThat(sex1).isNotEqualTo(sex2);
        sex1.setId(null);
        assertThat(sex1).isNotEqualTo(sex2);
    }
}
