package com.indigorobot.dao;

import com.indigorobot.AppConfig;
import com.indigorobot.model.ImageMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ImageMetaDAOImpl implements ImageMetaDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Value("${mysql.imagemeta.table}")
    private String imageMetaTable = AppConfig.imageMetaTable;

    /*
    public ImageMetaDAOImpl() {
    }
    */
    /*
    public ImageMetaDAOImpl(DataSource dataSource) {
        //jdbcTemplate = new JdbcTemplate(dataSource);
    }
    */

    @Override
    public boolean save(ImageMeta imageMeta) {
        String sql = "INSERT INTO " + imageMetaTable +
                " (name, gallery, description)" +
                " VALUES (?,?,?)";
        int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    imageMeta.getName(),
                    imageMeta.getGallery(),
                    imageMeta.getDescription());
        } catch (Exception e) {
            logger.error("INSERT FAILED: " + e.getLocalizedMessage());
        }
        return (result > 0);
    }

    @Override
    public ImageMeta getByName(String imageMetaName) {
        ImageMeta imageMeta = new ImageMeta();
        String sql = "SELECT" +
                " (name, gallery, description)" +
                " FROM " + imageMetaTable +
                " WHERE name = ?" +
                " ORDER BY id DESC" +
                " LIMIT 1";
        imageMeta = jdbcTemplate.queryForObject(sql, ImageMeta.class);
        return imageMeta;
    }

    @Override
    public ImageMeta getById(long imageMetaId) {
        ImageMeta imageMeta = new ImageMeta();
        String sql = "SELECT" +
                " (name, gallery, description)" +
                " FROM " + imageMetaTable +
                " WHERE id = ?" +
                " ORDER BY id DESC" +
                " LIMIT 1";
        imageMeta = jdbcTemplate.queryForObject(sql, ImageMeta.class);
        return imageMeta;
    }

    @Override
    public boolean update(long id, ImageMeta imageMeta) {
        String sql = "UPDATE " + imageMetaTable +
                " SET name=?, gallery=?, description=?" +
                " WHERE id=?";
        int result = jdbcTemplate.update(sql,
                imageMeta.getName(),
                imageMeta.getGallery(),
                imageMeta.getDescription(),
                id);
        return (result > 0);
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE from " + imageMetaTable +
                " WHERE id=?";
        int result = jdbcTemplate.update(sql, id);
        return (result > 0);
    }

    @Override
    public boolean delete(String name) {
        String sql = "DELETE from " + imageMetaTable +
                " WHERE name=?";
        int result = jdbcTemplate.update(sql, name);
        return (result > 0);
    }

    @Override
    public List<ImageMeta> list() {
        String sql = "SELECT * FROM " + imageMetaTable;
        List<ImageMeta> imageMetas = jdbcTemplate.query(sql, new ImageMetaRowMapper());
        return imageMetas;
    }

}
