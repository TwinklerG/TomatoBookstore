package com.tomatobookstore.tomatobookstore.controller;

import com.tomatobookstore.tomatobookstore.service.UserService;
import com.tomatobookstore.tomatobookstore.vo.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/accounts")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public ResultVO<RetUserVO> getUserDetail(@PathVariable String username) throws Exception {
        return ResultVO.buildSuccess(userService.getUserDetail(username),null);
    }

    @PostMapping("")
    public ResultVO<String> createUser(@RequestBody UserVO userVO) {
        userService.createUser(userVO);
        return ResultVO.buildSuccess("注册成功",null);
    }

    @PostMapping("/login")
    public ResultVO<String> login(HttpServletResponse request, @RequestBody LoginVO loginVO) {
        LoginResultVO result=userService.login(loginVO.getUsername(), loginVO.getPassword());
        Cookie cookie=new Cookie("token",result.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(24*60*60);
        request.addCookie(cookie);
        request.addHeader("token",result.getToken());
        return ResultVO.buildSuccess(result.getToken(),null);
    }

    @PutMapping("")
    public ResultVO<String> update(@RequestBody UserVO userVO) {
        userService.update(userVO);
        return ResultVO.buildSuccess("更新成功",null);
    }
}
