package com.alexander.miaosha.controller;

import com.alexander.miaosha.domain.User;
import com.alexander.miaosha.redis.RedisService;
import com.alexander.miaosha.redis.UserKey;
import com.alexander.miaosha.results.CodeMsg;
import com.alexander.miaosha.results.Result;
import com.alexander.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "alex");//在前端可以用EL表达式获取${name}
        return "hello";
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("niubi");
    }

    @RequestMapping(value = "/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user =  userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "1", User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<User> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        boolean ret = redisService.set(UserKey.getById,"1", user);
        User user1 = redisService.get(UserKey.getById,"1", User.class);
        return Result.success(user1);
    }
}
