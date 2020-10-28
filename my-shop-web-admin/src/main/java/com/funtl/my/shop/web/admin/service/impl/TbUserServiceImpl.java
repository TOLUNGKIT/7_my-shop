package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 20:52
 **/
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        // 验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }

        // 通过验证
        else {
            tbUser.setUpdated(new Date());

            // 新增用户
            if(tbUser.getId() == null){
                //密码需要加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
            }

            // 编辑用户
            else{
                update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
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

    /**
     * 用户信息的有效性验证(用 spring validation 后就不需要了)
     * @param tbUser
     */
//    private BaseResult checkTbUser(TbUser tbUser){
//
//        BaseResult baseResult = BaseResult.success();
//
//        //非空验证
//        if(StringUtils.isBlank(tbUser.getEmail())){
//            baseResult = BaseResult.fail("邮箱不能为空，请重新输入" );
//        }
//        else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
//            baseResult = BaseResult.fail("邮箱格式不正确，请重新输入" );
//        }
//        else if (StringUtils.isBlank(tbUser.getPassword())) {
//            baseResult = BaseResult.fail("密码不能为空，请重新输入");
//        }
//        else if (StringUtils.isBlank(tbUser.getUsername())) {
//            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
//        }
//        else if (StringUtils.isBlank(tbUser.getPhone())) {
//            baseResult = BaseResult.fail("手机不能为空，请重新输入");
//        }
//        else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
//            baseResult = BaseResult.fail("手机格式不正确，请重新输入" );
//        }
//        return baseResult;
//    }
}
