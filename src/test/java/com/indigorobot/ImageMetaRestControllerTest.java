package com.indigorobot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indigorobot.dao.ImageMetaDAO;
import com.indigorobot.dao.ImageMetaDAOImpl;
import com.indigorobot.dao.ImageMetaRowMapper;
import com.indigorobot.model.ImageMeta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Unit test for ImageMeta Object.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ImageMetaRestControllerTest {

    @Mock
    ImageMetaDAOImpl imageMetaDAO;

    @Autowired
    @InjectMocks
    private ImageMetaRestController imageMetaRestController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void beforeTest() {
        imageMetaRestController = new ImageMetaRestController();
    }

    @After
    public void afterTest() {
    }

    @Test
    public void testListData() throws Exception {

        ImageMeta sampleImageMeta = new ImageMeta();
        ImageMeta sampleImageMetaAlt = new ImageMeta();
        ArrayList<ImageMeta> sampleImageMetaList = new ArrayList<>();

        sampleImageMeta.setId(2);
        sampleImageMeta.setName("sampleImageMeta");
        sampleImageMeta.setGallery("sampleImageMetaGallery");
        sampleImageMeta.setDescription("sampleImageMeta Description");
        sampleImageMetaAlt.setId(3);
        sampleImageMetaAlt.setName("sampleImageMeta alt");
        sampleImageMetaAlt.setGallery("sampleImageMetaGallery alt");
        sampleImageMetaAlt.setDescription("sampleImageMeta Description alt");

        sampleImageMetaList.add(sampleImageMeta);
        sampleImageMetaList.add(sampleImageMetaAlt);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(sampleImageMetaList);

        when(imageMetaDAO.list()).thenReturn(sampleImageMetaList);

        this.mockMvc.perform(get("/imagedata/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testSave() throws Exception {

        ImageMeta sampleImageMeta = new ImageMeta();

        sampleImageMeta.setId(2);
        sampleImageMeta.setName("sampleImageMeta");
        sampleImageMeta.setGallery("sampleImageMetaGallery");
        sampleImageMeta.setDescription("sampleImageMeta Description");

        when(imageMetaDAO.save(sampleImageMeta)).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String submittalJson = objectMapper.writeValueAsString(sampleImageMeta);

        this.mockMvc.perform(post("/imagedata/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(submittalJson))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testDelete() throws Exception {

        ImageMeta sampleImageMeta = new ImageMeta();

        long sampleImageMetaId = 4;
        sampleImageMeta.setId(sampleImageMetaId);
        sampleImageMeta.setName("sampleImageMeta Delete test");
        sampleImageMeta.setGallery("sampleImageMetaGallery Delete test");
        sampleImageMeta.setDescription("sampleImageMeta Description Delete test");

        when(imageMetaDAO.delete(sampleImageMetaId)).thenReturn(true);

        this.mockMvc.perform(delete("/imagedata/" + sampleImageMetaId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testUpdate() throws Exception {

        ImageMeta sampleImageMeta = new ImageMeta();
        ImageMeta sampleImageMetaUpdate = new ImageMeta();

        long sampleImageMetaId = 5;
        sampleImageMeta.setId(sampleImageMetaId);
        sampleImageMeta.setName("sampleImageMeta Update test");
        sampleImageMeta.setGallery("sampleImageMetaGallery Update test");
        sampleImageMeta.setDescription("sampleImageMeta Description Update test");
        //sampleImageMetaUpdate.setName("sampleImageMeta Update test updated");
        //sampleImageMetaUpdate.setGallery("sampleImageMetaGallery Update test updated");
        //sampleImageMetaUpdate.setDescription("sampleImageMeta Description Update test updated");

        when(imageMetaDAO.update(sampleImageMetaId, sampleImageMeta)).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String submittalJson = objectMapper.writeValueAsString(sampleImageMeta);

        this.mockMvc.perform(put("/imagedata/" + sampleImageMetaId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(submittalJson))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}
