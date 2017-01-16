package com.voronovich.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainInfoViewService {

    @Test
    public void aTest() {
        Service service = Service.getService();
        List<String> list = service.getMainInfoViewService().getMainInfoView(1);
        int x = list.size();
        Assert.assertEquals("Not the same", 34, x);
    }
}
