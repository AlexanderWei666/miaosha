package com.alexander.miaosha.service;

import com.alexander.miaosha.dao.MiaoshaUserDao;
import com.alexander.miaosha.domain.MiaoshaUser;
import com.alexander.miaosha.exception.GlobalException;
import com.alexander.miaosha.redis.MiaoshaUserKey;
import com.alexander.miaosha.redis.RedisService;
import com.alexander.miaosha.results.CodeMsg;
import com.alexander.miaosha.util.MD5Util;
import com.alexander.miaosha.util.UUIDUtil;
import com.alexander.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static final String COOKI_NAME_TOKEN = "token";
    private static Logger log = LoggerFactory.getLogger(MiaoshaUserService.class);

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(Long id) {
        return miaoshaUserDao.getById(id);
    }


    public String login(HttpServletResponse response, LoginVo loginVo, String token) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        log.info(calcPass);
        log.info(dbPass);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        MiaoshaUser tokenUser = getByToken(response, token);
        if (token != null && user.getId().equals(tokenUser.getId()))
            return token;
        //生成cookies
        String newToken = UUIDUtil.uuid();
        addCookie(response, newToken, user);
        return newToken;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        if (user != null)
            addCookie(response, token, user);
        return user;
    }
}
