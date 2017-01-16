package com.voronovich.entity;

/**
 * POJO MainInfoEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class MainInfoEntity {

    private int id;
    private String brand;
    private String model;
    private double price;
    private String releaseDate;
    private String img;
    private int fk_Catalog;

    public MainInfoEntity() {
    }

    public MainInfoEntity(int id, String brand, String model, double price, String releaseDate, String img, int fk_Catalog) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
        this.img = img;
        this.fk_Catalog = fk_Catalog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getFk_Catalog() {
        return fk_Catalog;
    }

    public void setFk_Catalog(int fk_Catalog) {
        this.fk_Catalog = fk_Catalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainInfoEntity that = (MainInfoEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (fk_Catalog != that.fk_Catalog) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + fk_Catalog;
        return result;
    }

    @Override
    public String toString() {
        return "MainInfoEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", releaseDate='" + releaseDate + '\'' +
                ", picture='" + img + '\'' +
                ", fk_Catalog=" + fk_Catalog +
                '}';
    }
}
