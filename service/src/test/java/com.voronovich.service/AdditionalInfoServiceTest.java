package com.voronovich.service;

import com.voronovich.entity.AdditionalInfoEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AdditionalInfoServiceTest {

    Service service = Service.getService();

    @Test
    public void aTest() {

        List<AdditionalInfoEntity> listBefore = service.getAdditionalInfoService().get();
        int before = listBefore.size();
        AdditionalInfoEntity additionalEntityEntity = new AdditionalInfoEntity(0, "test", "test", 1);
        service.getAdditionalInfoService().create(additionalEntityEntity);
        List<AdditionalInfoEntity> listAfter = service.getAdditionalInfoService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<AdditionalInfoEntity> listBefore = service.getAdditionalInfoService().get();
        int before = listBefore.size();
        AdditionalInfoEntity additionalEntityBefore = listBefore.get(before - 1);
        String a = additionalEntityBefore.getTitle();
        additionalEntityBefore.setTitle("newtest");
        service.getAdditionalInfoService().update(additionalEntityBefore);
        List<AdditionalInfoEntity> listAfter = service.getAdditionalInfoService().get();
        AdditionalInfoEntity additionalEntityAfter = listAfter.get(before - 1);
        String b = additionalEntityAfter.getTitle();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<AdditionalInfoEntity> listBefore = service.getAdditionalInfoService().get();
        int before = listBefore.size();
        AdditionalInfoEntity additionalEntity = listBefore.get(before - 1);
        service.getAdditionalInfoService().delete(additionalEntity);
        List<AdditionalInfoEntity> listAfter = service.getAdditionalInfoService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }

    @Test
    public void dTest() {
        AdditionalInfoEntity additionalInfoEntity = service.getAdditionalInfoService().get(1);
        String value = additionalInfoEntity.getValue();
        Assert.assertEquals("Not read", "320 ", value);
    }
}
