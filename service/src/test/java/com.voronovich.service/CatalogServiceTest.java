package com.voronovich.service;

import com.voronovich.entity.CatalogEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CatalogServiceTest {

    Service service = Service.getService();

    @Test
    public void aTest() {

        List<CatalogEntity> listBefore = service.getCatalogService().get();
        int before = listBefore.size();
        CatalogEntity catalogEntity = new CatalogEntity(0, "test");
        service.getCatalogService().create(catalogEntity);
        List<CatalogEntity> listAfter = service.getCatalogService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<CatalogEntity> listBefore = service.getCatalogService().get();
        int before = listBefore.size();
        CatalogEntity catalogEntityBefore = listBefore.get(before - 1);
        String a = catalogEntityBefore.getDepartment();
        catalogEntityBefore.setDepartment("newtest");
        service.getCatalogService().update(catalogEntityBefore);
        List<CatalogEntity> listAfter = service.getCatalogService().get();
        CatalogEntity catalogEntityAfter = listAfter.get(before - 1);
        String b = catalogEntityAfter.getDepartment();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<CatalogEntity> listBefore = service.getCatalogService().get();
        int before = listBefore.size();
        CatalogEntity catalogEntity = listBefore.get(before - 1);
        service.getCatalogService().delete(catalogEntity);
        List<CatalogEntity> listAfter = service.getCatalogService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }

    @Test
    public void fTest() {
        CatalogEntity catalogEntity = service.getCatalogService().get(1);
        String name = catalogEntity.getDepartment();
        Assert.assertEquals("Not read", "friedge", name);
    }
}
