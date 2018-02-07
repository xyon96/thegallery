package com.indigorobot.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for ImageMeta Object.
 */
public class ImageMetaTest {

    private ImageMeta imageMeta;
    private String imageMetaName = "TestImage0.jpg";
    private String imageMetaDescription = "Image Meta Description";
    private String imageMetaFilePath = "/images/" + imageMetaName;
    private String imageMetaGallery = "ImageGallery";

    @Before
    public void beforeTest() {
        imageMeta = new ImageMeta();
    }

    @After
    public void afterTest() {
    }

    @Test
    public void testImageMetaProperties() {
        imageMeta.setName(imageMetaName);
        imageMeta.setDescription(imageMetaDescription);
        imageMeta.setGallery(imageMetaGallery);
        assertEquals(imageMetaName, imageMeta.getName());
        assertEquals(imageMetaDescription, imageMeta.getDescription());
        assertEquals(imageMetaGallery, imageMeta.getGallery());
    }

}
