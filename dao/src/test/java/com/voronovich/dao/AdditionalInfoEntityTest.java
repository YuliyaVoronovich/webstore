package com.voronovich.dao;

import com.voronovich.entity.AdditionalInfoEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdditionalInfoEntityTest {

    DAO dao = DAO.getDAO();

    @Test
    public void aTest() {

        List<AdditionalInfoEntity> listBefore = dao.getAdditionalInfoEntityDAO().get();
        int before = listBefore.size();
        AdditionalInfoEntity additionalEntityEntity = new AdditionalInfoEntity(0, "test", "test", 1);
        dao.getAdditionalInfoEntityDAO().create(additionalEntityEntity);
        List<AdditionalInfoEntity> listAfter = dao.getAdditionalInfoEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<AdditionalInfoEntity> listBefore = dao.getAdditionalInfoEntityDAO().get();
        int before = listBefore.size();
        AdditionalInfoEntity additionalEntityBefore = listBefore.get(before - 1);
        String a = additionalEntityBefore.getTitle();
        additionalEntityBefore.setTitle("newtest");
        dao.getAdditionalInfoEntityDAO().update(additionalEntityBefore);
        List<AdditionalInfoEntity> listAfter = dao.getAdditionalInfoEntityDAO().get();
        AdditionalInfoEntity additionalEntityAfter = listAfter.get(before - 1);
        String b = additionalEntityAfter.getTitle();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<AdditionalInfoEntity> listBefore = dao.getAdditionalInfoEntityDAO().get();
        int before = listBefore.size();
        AdditionalInfoEntity additionalEntity = listBefore.get(before - 1);
        dao.getAdditionalInfoEntityDAO().delete(additionalEntity);
        List<AdditionalInfoEntity> listAfter = dao.getAdditionalInfoEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }

    @Test
    public void dTest() {
        AdditionalInfoEntity additionalInfoEntity = dao.getAdditionalInfoEntityDAO().get(1);
        String value = additionalInfoEntity.getValue();
        Assert.assertEquals("Not read", "320 ", value);
    }
}
