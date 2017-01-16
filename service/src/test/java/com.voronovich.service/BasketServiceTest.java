package com.voronovich.service;

import com.voronovich.entity.BasketEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BasketServiceTest {

    Service service = Service.getService();

    @Test
    public void aTest() {

        List<BasketEntity> listBefore = service.getBasketService().get();
        int before = listBefore.size();
        BasketEntity basketEntity = new BasketEntity(0, 10, 1);
        service.getBasketService().create(basketEntity);
        List<BasketEntity> listAfter = service.getBasketService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<BasketEntity> listBefore = service.getBasketService().get();
        int before = listBefore.size();
        BasketEntity basketEntityBefore = listBefore.get(before - 1);
        int a = basketEntityBefore.getFk_Users();
        basketEntityBefore.setFk_Users(11);
        service.getBasketService().update(basketEntityBefore);
        List<BasketEntity> listAfter = service.getBasketService().get();
        BasketEntity basketEntityAfter = listAfter.get(before - 1);
        int b = basketEntityAfter.getFk_Users();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<BasketEntity> listBefore = service.getBasketService().get();
        int before = listBefore.size();
        BasketEntity basketEntity = listBefore.get(before - 1);
        service.getBasketService().delete(basketEntity);
        List<BasketEntity> listAfter = service.getBasketService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }
}
