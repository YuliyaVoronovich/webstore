package com.voronovich.dao;

import com.voronovich.entity.BasketEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BasketEntityTest {

    DAO dao = DAO.getDAO();

    @Test
    public void aTest() {

        List<BasketEntity> listBefore = dao.getBasketEntityDAO().get();
        int before = listBefore.size();
        BasketEntity basketEntity = new BasketEntity(0, 10, 1);
        dao.getBasketEntityDAO().create(basketEntity);
        List<BasketEntity> listAfter = dao.getBasketEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<BasketEntity> listBefore = dao.getBasketEntityDAO().get();
        int before = listBefore.size();
        BasketEntity basketEntityBefore = listBefore.get(before - 1);
        int a = basketEntityBefore.getFk_Users();
        basketEntityBefore.setFk_Users(11);
        dao.getBasketEntityDAO().update(basketEntityBefore);
        List<BasketEntity> listAfter = dao.getBasketEntityDAO().get();
        BasketEntity basketEntityAfter = listAfter.get(before - 1);
        int b = basketEntityAfter.getFk_Users();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<BasketEntity> listBefore = dao.getBasketEntityDAO().get();
        int before = listBefore.size();
        BasketEntity basketEntity = listBefore.get(before - 1);
        dao.getBasketEntityDAO().delete(basketEntity);
        List<BasketEntity> listAfter = dao.getBasketEntityDAO().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }
}
