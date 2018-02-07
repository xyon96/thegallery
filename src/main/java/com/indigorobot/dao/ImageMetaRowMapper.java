package com.indigorobot.dao;

import com.indigorobot.model.ImageMeta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMetaRowMapper {

    public ImageMeta mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ImageMeta imageMeta = new ImageMeta();
        imageMeta.setId(resultSet.getInt("id"));
        imageMeta.setName(resultSet.getString("name"));
        imageMeta.setGallery(resultSet.getString("gallery"));
        imageMeta.setDescription(resultSet.getString("description"));
        return imageMeta;
    }

}
