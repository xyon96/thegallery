package com.indigorobot.dao;

import com.indigorobot.model.ImageMeta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMetaRowMapper implements RowMapper<ImageMeta> {

    public ImageMeta mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ImageMeta imageMeta = new ImageMeta();
        imageMeta.setId(resultSet.getInt("id"));
        imageMeta.setName(resultSet.getString("name"));
        imageMeta.setGallery(resultSet.getString("gallery"));
        imageMeta.setDescription(resultSet.getString("description"));
        return imageMeta;
    }

}
