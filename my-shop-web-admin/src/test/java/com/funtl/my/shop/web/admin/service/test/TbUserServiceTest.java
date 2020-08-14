package com.funtl.my.shop.web.admin.service.test;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 21:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        for(TbUser tbUser : tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("TOLUNGKIT");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes()));
        tbUser.setEmail("xxtolungkit@gmail.com");
        tbUser.setPhone("134333333");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.insert(tbUser);
    }

    @Test
    public void testMd5(){
        System.out.println(DigestUtils.md5DigestAsHex("12345".getBytes()));
    }

    @Test
    public void testDelete(){
        tbUserService.delete(38L);
    }

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(35L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testUpdate(){
        TbUser tbUser= tbUserService.getById(37L);
        tbUser.setUsername("ttttt");
        tbUserService.update(tbUser);
    }

    @Test
    public void testSelectByUsername(){
        List<TbUser> tbUsers = tbUserService.selectByUsername("uni");
        for(TbUser tbUser : tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }
}
