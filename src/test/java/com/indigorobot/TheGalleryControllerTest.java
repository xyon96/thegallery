package com.indigorobot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.*;
import java.util.Scanner;

/**
 * Unit test for ImageMeta Object.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //, classes={TheGalleryController.class, ImageMeta.class})
@AutoConfigureMockMvc
public class TheGalleryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TheGalleryController theGalleryController;

    @Autowired
    private MockMvc mockMvc;

    private String mainPageTemplate = "/templates/mainpage.html";
    private String galleriesTemplate = "/templates/galleries.html";

    @Before
    public void beforeTest() {
        theGalleryController = new TheGalleryController();
    }

    @After
    public void afterTest() {
    }

    @Test
    public void testMainPage() throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream(mainPageTemplate);
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String expectedResponseBody = scanner.hasNext() ? scanner.next() : "";

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponseBody));
    }

    @Test
    public void testGalleryPage() throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream(galleriesTemplate);
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String expectedResponseBody = scanner.hasNext() ? scanner.next() : "";

        this.mockMvc.perform(get("/galleries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponseBody));

    }

}
