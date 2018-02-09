package com.indigorobot.dao;

import com.indigorobot.AppConfig;
import com.indigorobot.model.ImageMeta;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ImageMetaDAOImplTest {

    private long imageMetaId = 100L;
    private String imageMetaName = "imageMetaName";
    private String imageMetaDescription = "imageMetaDesc";
    private String imageMetaGallery = "imageMetaGallery";
    private ImageMeta imageMeta;

    //@Value("${mysql.imagemeta.table}")
    private String imageMetaTable = AppConfig.imageMetaTable;

    private String insertSql = "INSERT INTO " + imageMetaTable +
            " (name, gallery, description)" +
            " VALUES (?,?,?)";
    private String getByNameSql = "SELECT" +
            " (name, gallery, description)" +
            " FROM " + imageMetaTable +
            " WHERE name = ?" +
            " ORDER BY id DESC" +
            " LIMIT 1";
    private String getByIdSql = "SELECT" +
            " (name, gallery, description)" +
            " FROM " + imageMetaTable +
            " WHERE id = ?" +
            " ORDER BY id DESC" +
            " LIMIT 1";
    private String updateSql = "UPDATE " + imageMetaTable +
            " SET name=?, gallery=?, description=?" +
            " WHERE id=?";
    private String deleteByIdSql = "DELETE from " + imageMetaTable +
            " WHERE id=?";
    private String deleteByNameSql = "DELETE from " + imageMetaTable +
            " WHERE name=?";
    private String listSql = "SELECT * FROM " + imageMetaTable;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @InjectMocks
    private ImageMetaDAOImpl imageMetaDAO;

    @Before
    public void beforeTest() {
        imageMeta = new ImageMeta();
        imageMeta.setId(imageMetaId);
        imageMeta.setName(imageMetaName);
        imageMeta.setDescription(imageMetaDescription);
        imageMeta.setGallery(imageMetaGallery);
    }

    @After
    public void afterTest() {
    }

    @Test
    public void testSave() {

        // When our mock is called, return
        // what is expected (a single row
        // inserted: 1)
        when(jdbcTemplate.update(
                insertSql,
                imageMetaName,
                imageMetaGallery,
                imageMetaDescription
        )).thenReturn(1);

        // Run the method to be tested and
        // get its result
        boolean result = imageMetaDAO.save(imageMeta);

        // Ensure we got a successful result
        // from the DAO (since the template
        // told the DAO that a row was inserted,
        // see the "when" above)
        assertTrue(result);

        // Ensure the jdbcTemplate was called
        // by the DAO with expected arguments
        verify(jdbcTemplate, times(1)).update(
                insertSql,
                imageMetaName,
                imageMetaGallery,
                imageMetaDescription);

        // Ensure the jdbcTemplate was not
        // further interacted with
        verifyNoMoreInteractions(jdbcTemplate);
    }

    @Test
    public void testGetByName() {

        // When our mock is called, return
        // what is expected (a single object)
        when(jdbcTemplate.queryForObject(
                getByNameSql,
                ImageMeta.class
        )).thenReturn(imageMeta);

        // Run the method to be tested and
        // get its result
        ImageMeta result = imageMetaDAO.getByName(imageMetaName);

        // Ensure we got a successful result
        // from the DAO (since the template
        // told the DAO that an object was
        // retrieved, see the "when" above)
        assertNotNull(result);
        assertTrue(imageMeta.equals(result));

        // Ensure the jdbcTemplate was called
        // by the DAO with expected arguments
        verify(jdbcTemplate, times(1)).queryForObject(
                getByNameSql,
                ImageMeta.class);

        // Ensure the jdbcTemplate was not
        // further interacted with
        verifyNoMoreInteractions(jdbcTemplate);
    }

    @Test
    public void testGetById() {

        // When our mock is called, return
        // what is expected (a single object)
        when(jdbcTemplate.queryForObject(
                getByIdSql,
                ImageMeta.class
        )).thenReturn(imageMeta);

        // Run the method to be tested and
        // get its result
        ImageMeta result = imageMetaDAO.getById(imageMetaId);

        // Ensure we got a successful result
        // from the DAO (since the template
        // told the DAO that an object was
        // retrieved, see the "when" above)
        assertNotNull(result);
        assertTrue(imageMeta.equals(result));

        // Ensure the jdbcTemplate was called
        // by the DAO with expected arguments
        verify(jdbcTemplate, times(1)).queryForObject(
                getByIdSql,
                ImageMeta.class);

        // Ensure the jdbcTemplate was not
        // further interacted with
        verifyNoMoreInteractions(jdbcTemplate);
    }

    @Test
    public void testUpdate() {

        // When our mock is called, return
        // what is expected (a single object)
        when(jdbcTemplate.update(
                updateSql,
                imageMetaName,
                imageMetaGallery,
                imageMetaDescription,
                imageMetaId
        )).thenReturn(1);

        // Run the method to be tested and
        // get its result
        boolean result = imageMetaDAO.update(imageMetaId, imageMeta);

        // Ensure we got a successful result
        // from the DAO (since the template
        // told the DAO that an object was
        // retrieved, see the "when" above)
        assertTrue(result);

        // Ensure the jdbcTemplate was called
        // by the DAO with expected arguments
        verify(jdbcTemplate, times(1)).update(
                updateSql,
                imageMetaName,
                imageMetaGallery,
                imageMetaDescription,
                imageMetaId);

        // Ensure the jdbcTemplate was not
        // further interacted with
        verifyNoMoreInteractions(jdbcTemplate);
    }

    @Test
    public void testDeleteById() {

        // When our mock is called, return
        // what is expected (a single row
        // counted, 1)
        when(jdbcTemplate.update(
                deleteByIdSql,
                imageMetaId
        )).thenReturn(1);

        // Run the method to be tested and
        // get its result
        boolean result = imageMetaDAO.delete(imageMetaId);

        // Ensure we got a successful result
        // from the DAO (since the template
        // told the DAO that a row was
        // deleted, see the "when" above)
        assertTrue(result);

        // Ensure the jdbcTemplate was called
        // by the DAO with expected arguments
        verify(jdbcTemplate, times(1)).update(
                deleteByIdSql,
                imageMetaId);

        // Ensure the jdbcTemplate was not
        // further interacted with
        verifyNoMoreInteractions(jdbcTemplate);
    }

    @Test
    public void testDeleteByName() {

        // When our mock is called, return
        // what is expected (a single row
        // counted, 1)
        when(jdbcTemplate.update(
                deleteByNameSql,
                imageMetaName
        )).thenReturn(1);

        // Run the method to be tested and
        // get its result
        boolean result = imageMetaDAO.delete(imageMetaName);

        // Ensure we got a successful result
        // from the DAO (since the template
        // told the DAO that a row was
        // deleted, see the "when" above)
        assertTrue(result);

        // Ensure the jdbcTemplate was called
        // by the DAO with expected arguments
        verify(jdbcTemplate, times(1)).update(
                deleteByNameSql,
                imageMetaName);

        // Ensure the jdbcTemplate was not
        // further interacted with
        verifyNoMoreInteractions(jdbcTemplate);
    }

    // Need to include the rowmapper appropriately before enabling
    @Ignore
    @Test
    public void testList() {
        ImageMetaRowMapper imageMetaRowMapper = new ImageMetaRowMapper();
        when(jdbcTemplate.query(listSql, any(ImageMetaRowMapper.class)))
        .thenReturn(new ArrayList<>(Arrays.asList(imageMeta, imageMeta)));
        List<ImageMeta> result = imageMetaDAO.list();
        assertNotNull(result);
        verify(jdbcTemplate, times(1)).query(listSql, imageMetaRowMapper);
        verifyNoMoreInteractions(jdbcTemplate);
    }
}

