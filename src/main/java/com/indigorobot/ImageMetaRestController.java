package com.indigorobot;

import com.indigorobot.dao.ImageMetaDAO;
import com.indigorobot.dao.ImageMetaDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.indigorobot.model.ImageMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/imagedata")
public class ImageMetaRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImageMetaDAOImpl imageMetaDAO;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<ImageMeta> listData() {
        return imageMetaDAO.list();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public boolean newImageMeta(@RequestBody ImageMeta imageMeta) {
        boolean insertResult = false;
        try {
            insertResult = imageMetaDAO.save(imageMeta);
        } catch (Exception e){
            logger.error("INSERT FAILED: " + e.getLocalizedMessage());
        }
        return insertResult;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public boolean removeImageMetaById(@PathVariable("id") long id) {
        boolean deleteResult = false;
        try {
            deleteResult = imageMetaDAO.delete(id);
        } catch (Exception e) {
            logger.error("DELETE FAILED: " + e.getLocalizedMessage());
        }
        return deleteResult;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public boolean removeImageMetaById(@RequestBody ImageMeta imageMeta,
                                       @PathVariable("id") long id) {
        boolean updateResult = false;
        try {
            updateResult = imageMetaDAO.update(id, imageMeta);
        } catch (Exception e) {
            logger.error("DELETE FAILED: " + e.getLocalizedMessage());
        }
        return updateResult;
    }

}
