package com.tomatobookstore.tomatobookstore.service;

import com.tomatobookstore.tomatobookstore.vo.LoginResultVO;
import com.tomatobookstore.tomatobookstore.vo.MessageVO;
import com.tomatobookstore.tomatobookstore.vo.RetUserVO;
import com.tomatobookstore.tomatobookstore.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RetUserVO getUserDetail(String username) throws Exception;

    MessageVO createUser(UserVO user);

    LoginResultVO login(String username, String password);

    MessageVO update(UserVO user);
}
