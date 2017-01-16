package com.voronovich.dao;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainInfoViewEntityTest {

    @Test
    public void aTest() {
        DAO dao = DAO.getDAO();
        List<String> list = dao.getMainInfoViewEntityDAO().getByID(1);
        int x = list.size();
        Assert.assertEquals("Not the same", 34, x);
    }
}
