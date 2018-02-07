package com.indigorobot.dao;

import com.indigorobot.model.ImageMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageMetaDAOImpl implements ImageMetaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${mysql.imagemeta.table}")
    private String imageMetaTable;

    /*
    public ImageMetaDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    */

    @Override
    public boolean save(ImageMeta imageMeta) {
        String sql = "INSERT INTO " + imageMetaTable +
                " (name, gallery, description)" +
                " VALUES ('...',?,?,?)";
        int result = jdbcTemplate.update(sql,
                imageMeta.getName(),
                imageMeta.getGallery(),
                imageMeta.getDescription());
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
    public boolean update(ImageMeta imageMeta) {
        String sql = "UPDATE " + imageMetaTable +
                " SET name=?, gallery=?, description=?" +
                " WHERE id=?";
        int result = jdbcTemplate.update(sql,
                imageMeta.getName(),
                imageMeta.getGallery(),
                imageMeta.getDescription(),
                imageMeta.getId());
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
    public List<ImageMeta> list() {
        return new ArrayList<>(Arrays.asList(new ImageMeta()));
    }

}
