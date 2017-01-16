package com.voronovich.entity;

/**
 * POJO AdditionalInfoEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class AdditionalInfoEntity {

    private int id;
    private String title;
    private String value;
    private int fk_MainInfo;

    public AdditionalInfoEntity(){}

    public AdditionalInfoEntity(int id, String title, String value, int fk_MainInfo){
        this.id=id;
        this.title=title;
        this.value=value;
        this.fk_MainInfo=fk_MainInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFk_MainInfo() {
        return fk_MainInfo;
    }

    public void setFk_MainInfo(int fk_MainInfo) {
        this.fk_MainInfo = fk_MainInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditionalInfoEntity that = (AdditionalInfoEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (fk_MainInfo != that.fk_MainInfo) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + fk_MainInfo;
        return result;
    }

    @Override
    public String toString() {
        return "AdditionalInfoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", fk_MainInfo=" + fk_MainInfo +
                '}';
    }
}
