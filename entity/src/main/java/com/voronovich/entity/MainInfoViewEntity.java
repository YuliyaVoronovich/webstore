package com.voronovich.entity;

public class MainInfoViewEntity {

    private int id;
    private String brand;
    private String model;
    private double price;
    private String releaseDate;
    private String img;
    private int fk_Catalog;
    private String title;
    private String value;

    public MainInfoViewEntity() {
    }

    public MainInfoViewEntity(int id, String brand, String model, double price, String releaseDate, String img, int fk_Catalog, String title, String value) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
        this.img = img;
        this.fk_Catalog = fk_Catalog;
        this.title=title;
        this.value=value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainInfoViewEntity)) return false;

        MainInfoViewEntity that = (MainInfoViewEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (fk_Catalog != that.fk_Catalog) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;

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
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MainInfoViewEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", releaseDate='" + releaseDate + '\'' +
                ", img='" + img + '\'' +
                ", fk_Catalog=" + fk_Catalog +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
