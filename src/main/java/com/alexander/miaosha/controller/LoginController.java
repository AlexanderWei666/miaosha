package com.alexander.miaosha.controller;

import com.alexander.miaosha.redis.RedisService;
import com.alexander.miaosha.results.Result;
import com.alexander.miaosha.service.MiaoshaUserService;
import com.alexander.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo,
                                   @CookieValue(name = MiaoshaUserService.COOKI_NAME_TOKEN, required = false) String token) {
        log.info(loginVo.toString());
        //登录
        String newToken = userService.login(response, loginVo, token);
        return Result.success(newToken);
    }
}