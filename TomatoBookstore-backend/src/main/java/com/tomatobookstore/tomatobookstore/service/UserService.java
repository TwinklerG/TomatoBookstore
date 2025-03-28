package com.tomatobookstore.tomatobookstore.service;

import com.tomatobookstore.tomatobookstore.vo.LoginResultVO;
import com.tomatobookstore.tomatobookstore.vo.RetUserVO;
import com.tomatobookstore.tomatobookstore.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RetUserVO getUserDetail(String username) throws Exception;

    void createUser(UserVO user);

    LoginResultVO login(String username, String password);

    void update(UserVO user);
}
