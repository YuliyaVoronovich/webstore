package com.voronovich.service;

import com.voronovich.entity.UsersEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UsersServiceTest {

    Service service = Service.getService();

    @Test
    public void aTest() {

        List<UsersEntity> listBefore = service.getUserService().get();
        int before = listBefore.size();
        UsersEntity usersEntity = new UsersEntity(0, "test", "test", "test@test.ru", "test", "test", "test", "false",1);
        service.getUserService().create(usersEntity);
        List<UsersEntity> listAfter = service.getUserService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        UsersEntity usersEntity = service.getUserService().get("test");
        String password = usersEntity.getPassword();
        Assert.assertEquals("Not the same", "test", password);
    }

    @Test
    public void cTest() {
        UsersEntity usersEntity = service.getUserService().get("test","test");
        String name = usersEntity.getName();
        Assert.assertEquals("Not the same", "test", name);
    }

    @Test
    public void dTest() {
        UsersEntity usersEntity = service.getUserService().getByEmail("test@test.ru");
        String password = usersEntity.getPassword();
        Assert.assertEquals("Not the same", "test", password);
    }

    @Test
    public void eTest() {
        List<UsersEntity> listBefore = service.getUserService().get();
        int before = listBefore.size();
        UsersEntity usersEntityBefore = listBefore.get(before - 1);
        String a = usersEntityBefore.getEmail();
        usersEntityBefore.setEmail("new@test.ru");
        service.getUserService().update(usersEntityBefore);
        List<UsersEntity> listAfter = service.getUserService().get();
        UsersEntity usersEntityAfter = listAfter.get(before - 1);
        String b = usersEntityAfter.getEmail();
        Assert.assertNotEquals("Not updated", a, b);
    }

    @Test
    public void fTest() {
        List<UsersEntity> listBefore = service.getUserService().get();
        int before = listBefore.size();
        UsersEntity usersEntity = listBefore.get(before - 1);
        service.getUserService().delete(usersEntity);
        List<UsersEntity> listAfter = service.getUserService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }
}
