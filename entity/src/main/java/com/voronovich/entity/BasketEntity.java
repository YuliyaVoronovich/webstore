package com.voronovich.entity;

/**
 * POJO BasketEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class BasketEntity {

    private int id;
    private int fk_Users;
    private int fk_MainInfo;

    public BasketEntity(){}

    public BasketEntity(int id, int fk_Users, int fk_MainInfo){
        this.id=id;
        this.fk_Users=fk_Users;
        this.fk_MainInfo=fk_MainInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_Users() {
        return fk_Users;
    }

    public void setFk_Users(int fk_Users) {
        this.fk_Users = fk_Users;
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

        BasketEntity that = (BasketEntity) o;

        if (id != that.id) return false;
        if (fk_Users != that.fk_Users) return false;
        if (fk_MainInfo != that.fk_MainInfo) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result=id;
        result = 31 * result + fk_Users;
        result = 31 * result + getFk_MainInfo();
        return result;
    }

    @Override
    public String toString() {
        return "BasketEntity{" +
                "id=" + id +
                ", fk_Users=" + fk_Users +
                ", fk_MainInfo=" + fk_MainInfo +
                '}';
    }
}
