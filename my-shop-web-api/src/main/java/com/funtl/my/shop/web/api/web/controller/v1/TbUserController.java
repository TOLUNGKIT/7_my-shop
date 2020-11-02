package com.funtl.my.shop.web.api.web.controller.v1;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员管理(只提供Json数据服务)
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-11-02 13:14
 **/
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if(user == null){
            return BaseResult.fail("账号或密码错误");
        }

        else {
            return BaseResult.success("成功",user);
        }
    }

}
