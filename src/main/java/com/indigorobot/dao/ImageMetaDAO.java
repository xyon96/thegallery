package com.indigorobot.dao;

import com.indigorobot.model.ImageMeta;

import java.sql.SQLException;
import java.util.List;

public interface ImageMetaDAO {

    public boolean save(ImageMeta imageMeta) throws SQLException;

    public ImageMeta getByName(String imageMetaName);

    public ImageMeta getById(long imageMetaId);

    public boolean update(long id, ImageMeta imageMeta);

    public boolean delete(long id);

    public boolean delete(String name);

    public List<ImageMeta> list();

}
