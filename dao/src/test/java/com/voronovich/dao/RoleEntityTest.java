package com.voronovich.dao;

import com.voronovich.entity.RoleEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RoleEntityTest{

    DAO dao = DAO.getDAO();

    @Test
    public void aTest() {
        List<RoleEntity> listBefore = dao.getRoleEntityDAO().get();
        int before = listBefore.size();
        RoleEntity roleEntity = new RoleEntity(0, "test");
        dao.getRoleEntityDAO().create(roleEntity);
        List<RoleEntity> listAfter = dao.getRoleEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<RoleEntity> listBefore = dao.getRoleEntityDAO().get();
        int before = listBefore.size();
        RoleEntity roleEntityBefore = listBefore.get(before - 1);
        String a = roleEntityBefore.getName();
        roleEntityBefore.setName("newtest");
        dao.getRoleEntityDAO().update(roleEntityBefore);
        List<RoleEntity> listAfter = dao.getRoleEntityDAO().get();
        RoleEntity roleEntityAfter = listAfter.get(before - 1);
        String b = roleEntityAfter.getName();
        Assert.assertNotEquals("Not updated", a, b);
    }

    @Test
    public void cTest() {
        RoleEntity roleEntity = dao.getRoleEntityDAO().get(1);
        String name = roleEntity.getName();
        Assert.assertEquals("Not read", "user", name);
    }

    @Test
    public void dTest() {
        List<RoleEntity> listBefore = dao.getRoleEntityDAO().get();
        int before = listBefore.size();
        RoleEntity roleEntity = listBefore.get(before - 1);
        dao.getRoleEntityDAO().delete(roleEntity);
        List<RoleEntity> listAfter = dao.getRoleEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }
}
