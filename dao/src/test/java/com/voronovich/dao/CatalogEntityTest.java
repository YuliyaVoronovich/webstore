package com.voronovich.dao;

import com.voronovich.entity.CatalogEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CatalogEntityTest {

    DAO dao = DAO.getDAO();

    @Test
    public void aTest() {

        List<CatalogEntity> listBefore = dao.getCatalogEntityDAO().get();
        int before = listBefore.size();
        CatalogEntity catalogEntity = new CatalogEntity(0, "test");
        dao.getCatalogEntityDAO().create(catalogEntity);
        List<CatalogEntity> listAfter = dao.getCatalogEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<CatalogEntity> listBefore = dao.getCatalogEntityDAO().get();
        int before = listBefore.size();
        CatalogEntity catalogEntityBefore = listBefore.get(before - 1);
        String a = catalogEntityBefore.getDepartment();
        catalogEntityBefore.setDepartment("newtest");
        dao.getCatalogEntityDAO().update(catalogEntityBefore);
        List<CatalogEntity> listAfter = dao.getCatalogEntityDAO().get();
        CatalogEntity catalogEntityAfter = listAfter.get(before - 1);
        String b = catalogEntityAfter.getDepartment();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<CatalogEntity> listBefore = dao.getCatalogEntityDAO().get();
        int before = listBefore.size();
        CatalogEntity catalogEntity = listBefore.get(before - 1);
        dao.getCatalogEntityDAO().delete(catalogEntity);
        List<CatalogEntity> listAfter = dao.getCatalogEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }

    @Test
    public void fTest() {
        CatalogEntity catalogEntity = dao.getCatalogEntityDAO().get(1);
        String name = catalogEntity.getDepartment();
        Assert.assertEquals("Not read", "friedge", name);
    }
}
