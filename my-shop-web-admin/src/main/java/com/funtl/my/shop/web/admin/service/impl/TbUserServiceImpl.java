package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 20:52
 **/
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public void insert(TbUser tbUser) {
        tbUserDao.insert(tbUser);
    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {

        tbUserDao.updated(tbUser);
    }

    @Override
    public List<TbUser> selectByUsername(String name) {
        return tbUserDao.selectByUsername(name);
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if(tbUser != null){
            // 明文密码加密
            String Md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            //判断加密后的密码和数据库中存放的密码是否匹配，匹配则表示允许登录
            if(Md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }
}
