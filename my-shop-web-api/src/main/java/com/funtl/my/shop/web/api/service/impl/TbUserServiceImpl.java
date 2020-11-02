package com.funtl.my.shop.web.api.service.impl;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.api.dao.TbUserDao;
import com.funtl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-11-02 13:10
 **/
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);

        // 如果用户存在
        if(user != null){
            // 将明文密码加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if(password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
