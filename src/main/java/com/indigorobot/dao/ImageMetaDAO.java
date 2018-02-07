package com.indigorobot.dao;

import com.indigorobot.model.ImageMeta;

import java.util.List;

public interface ImageMetaDAO {

    public boolean save(ImageMeta imageMeta);

    public ImageMeta getByName(String imageMetaName);

    public ImageMeta getById(long imageMetaId);

    public boolean update(ImageMeta imageMeta);

    public boolean delete(long id);

    public List<ImageMeta> list();

}
