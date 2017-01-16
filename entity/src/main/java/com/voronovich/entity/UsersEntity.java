package com.voronovich.entity;

/**
 * POJO UsersEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class UsersEntity {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private String salt;
    private String blackList;
    private int fk_Role;

    public UsersEntity(){}

    public UsersEntity(int id, String name, String surname, String email, String login, String password, String salt, String blackList, int fk_Role){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.login=login;
        this.password=password;
        this.salt=salt;
        this.blackList=blackList;
        this.fk_Role=fk_Role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    public int getFk_Role() {
        return fk_Role;
    }

    public void setFk_Role(int fk_Role) {
        this.fk_Role = fk_Role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (salt != null ? !salt.equals(that.salt) : that.salt != null) return false;
        if (blackList != null ? !blackList.equals(that.blackList) : that.blackList != null) return false;
        if (fk_Role != that.fk_Role) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (blackList != null ? blackList.hashCode() : 0);
        result = 31 * result + fk_Role;
        return result;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", blackList='" + blackList + '\'' +
                ", fk_Role=" + fk_Role +
                '}';
    }
}
