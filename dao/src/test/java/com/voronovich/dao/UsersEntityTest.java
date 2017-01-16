package com.voronovich.dao;

import com.voronovich.entity.UsersEntity;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UsersEntityTest {

    DAO dao = DAO.getDAO();

    @Test
    public void aTest() {

        List<UsersEntity> listBefore = dao.getUsersEntityDAO().get();
        int before = listBefore.size();
        UsersEntity usersEntity = new UsersEntity(0, "test", "test", "test@test.ru", "test", "test", "test", "false",1);
        dao.getUsersEntityDAO().create(usersEntity);
        List<UsersEntity> listAfter = dao.getUsersEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        UsersEntity usersEntity = dao.getUsersEntityDAO().get("test");
        String password = usersEntity.getPassword();
        Assert.assertEquals("Not the same", "test", password);
    }

    @Test
    public void cTest() {
        UsersEntity usersEntity = dao.getUsersEntityDAO().get("test","test");
        String name = usersEntity.getName();
        Assert.assertEquals("Not the same", "test", name);
    }

    @Test
    public void dTest() {
        UsersEntity usersEntity = dao.getUsersEntityDAO().getByEmail("test@test.ru");
        String password = usersEntity.getPassword();
        Assert.assertEquals("Not the same", "test", password);
    }

    @Test
    public void eTest() {
        List<UsersEntity> listBefore = dao.getUsersEntityDAO().get();
        int before = listBefore.size();
        UsersEntity usersEntityBefore = listBefore.get(before - 1);
        String a = usersEntityBefore.getEmail();
        usersEntityBefore.setEmail("new@test.ru");
        dao.getUsersEntityDAO().update(usersEntityBefore);
        List<UsersEntity> listAfter = dao.getUsersEntityDAO().get();
        UsersEntity usersEntityAfter = listAfter.get(before - 1);
        String b = usersEntityAfter.getEmail();
        Assert.assertNotEquals("Not updated", a, b);
    }

    @Test
    public void fTest() {
        UsersEntity usersEntity = dao.getUsersEntityDAO().get(10);
        String name = usersEntity.getName();
        Assert.assertEquals("Not read", "Дмитрий", name);
    }

    @Test
    public void gTest() {
        List<UsersEntity> listBefore = dao.getUsersEntityDAO().get();
        int before = listBefore.size();
        UsersEntity usersEntity = listBefore.get(before - 1);
        dao.getUsersEntityDAO().delete(usersEntity);
        List<UsersEntity> listAfter = dao.getUsersEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }
}
