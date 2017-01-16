package com.voronovich.service;

import com.voronovich.entity.MainInfoEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainInfoTest {

    Service service = Service.getService();

    @Test
    public void aTest() {

        List<MainInfoEntity> listBefore = service.getMainInfoService().get();
        int before = listBefore.size();
        MainInfoEntity mainInfoEntity = new MainInfoEntity(0, "test", "test", 1.0,"test", "1.jpg",1);
        service.getMainInfoService().create(mainInfoEntity);
        List<MainInfoEntity> listAfter = service.getMainInfoService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<MainInfoEntity> listBefore = service.getMainInfoService().get();
        int before = listBefore.size();
        MainInfoEntity mainInfoEntityBefore = listBefore.get(before - 1);
        String a = mainInfoEntityBefore.getBrand();
        mainInfoEntityBefore.setBrand("newtest");
        service.getMainInfoService().update(mainInfoEntityBefore);
        List<MainInfoEntity> listAfter = service.getMainInfoService().get();
        MainInfoEntity mainInfoEntityAfter = listAfter.get(before - 1);
        String b = mainInfoEntityAfter.getBrand();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<MainInfoEntity> listBefore = service.getMainInfoService().get();
        int before = listBefore.size();
        MainInfoEntity mainInfoEntity = listBefore.get(before - 1);
        service.getMainInfoService().delete(mainInfoEntity);
        List<MainInfoEntity> listAfter = service.getMainInfoService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }

    @Test
    public void dTest() {
        MainInfoEntity mainInfoEntity = service.getMainInfoService().get(1);
        String model = mainInfoEntity.getModel();
        Assert.assertEquals("Not read", "ХМ-4012-022 ", model);
    }
}
