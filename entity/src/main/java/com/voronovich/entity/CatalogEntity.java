package com.voronovich.entity;

/**
 * POJO CatalogEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CatalogEntity {

    private int id;
    private String department;

    public CatalogEntity(){}

    public CatalogEntity(int id, String department){
        this.id=id;
        this.department=department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatalogEntity that = (CatalogEntity) o;

        if (id != that.id) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CatalogEntity{" +
                "id=" + id +
                ", department='" + department + '\'' +
                '}';
    }
}
