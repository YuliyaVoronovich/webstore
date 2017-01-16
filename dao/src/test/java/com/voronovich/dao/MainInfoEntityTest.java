package com.voronovich.dao;

import com.voronovich.entity.MainInfoEntity;
import com.voronovich.entity.RoleEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MainInfoEntityTest {

    DAO dao = DAO.getDAO();

    @Test
    public void aTest() {

        List<MainInfoEntity> listBefore = dao.getMainInfoEntityDAO().get();
        int before = listBefore.size();
        MainInfoEntity mainInfoEntity = new MainInfoEntity(0, "test", "test", 1.0,"test", "1.jpg",1);
        dao.getMainInfoEntityDAO().create(mainInfoEntity);
        List<MainInfoEntity> listAfter = dao.getMainInfoEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<MainInfoEntity> listBefore = dao.getMainInfoEntityDAO().get();
        int before = listBefore.size();
        MainInfoEntity mainInfoEntityBefore = listBefore.get(before - 1);
        String a = mainInfoEntityBefore.getBrand();
        mainInfoEntityBefore.setBrand("newtest");
        dao.getMainInfoEntityDAO().update(mainInfoEntityBefore);
        List<MainInfoEntity> listAfter = dao.getMainInfoEntityDAO().get();
        MainInfoEntity mainInfoEntityAfter = listAfter.get(before - 1);
        String b = mainInfoEntityAfter.getBrand();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<MainInfoEntity> listBefore = dao.getMainInfoEntityDAO().get();
        int before = listBefore.size();
        MainInfoEntity mainInfoEntity = listBefore.get(before - 1);
        dao.getMainInfoEntityDAO().delete(mainInfoEntity);
        List<MainInfoEntity> listAfter = dao.getMainInfoEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }

    @Test
    public void dTest() {
        MainInfoEntity mainInfoEntity = dao.getMainInfoEntityDAO().get(1);
        String model = mainInfoEntity.getModel();
        Assert.assertEquals("Not read", "ХМ-4012-022 ", model);
    }
}
