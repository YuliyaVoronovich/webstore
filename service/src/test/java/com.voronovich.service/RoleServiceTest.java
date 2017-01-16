package com.voronovich.service;

import com.voronovich.entity.RoleEntity;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RoleServiceTest {

    Service service = Service.getService();

    @Test
    public void aTest() {

        List<RoleEntity> listBefore = service.getRoleService().get();
        int before = listBefore.size();
        RoleEntity roleEntity = new RoleEntity(0, "test");
        service.getRoleService().create(roleEntity);
        List<RoleEntity> listAfter = service.getRoleService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not created", before + 1, after);
    }

    @Test
    public void bTest() {
        List<RoleEntity> listBefore = service.getRoleService().get();
        int before = listBefore.size();
        RoleEntity roleEntityBefore = listBefore.get(before - 1);
        String a = roleEntityBefore.getName();
        roleEntityBefore.setName("newtest");
        service.getRoleService().update(roleEntityBefore);
        List<RoleEntity> listAfter = service.getRoleService().get();
        RoleEntity roleEntityAfter = listAfter.get(before - 1);
        String b = roleEntityAfter.getName();
        Assert.assertNotEquals("Not updated", a, b);

    }

    @Test
    public void cTest() {
        List<RoleEntity> listBefore = service.getRoleService().get();
        int before = listBefore.size();
        RoleEntity roleEntity = listBefore.get(before - 1);
        service.getRoleService().delete(roleEntity);
        List<RoleEntity> listAfter = service.getRoleService().get();
        int after = listAfter.size();
        Assert.assertEquals("Not deleted", before - 1, after);
    }
}
