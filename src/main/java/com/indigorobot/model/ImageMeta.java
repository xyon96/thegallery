package com.indigorobot.model;

import java.util.Objects;

public class ImageMeta {

    private long id;
    private String name;
    private String gallery;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageMeta imageMeta = (ImageMeta) o;
        return id == imageMeta.id &&
                Objects.equals(name, imageMeta.name) &&
                Objects.equals(gallery, imageMeta.gallery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, gallery);
    }

    @Override
    public String toString() {
        return "ImageMeta{" +
                "id=" + id +
                ", imageName='" + name + '\'' +
                ", imageGallery='" + gallery + '\'' +
                '}';
    }
}
