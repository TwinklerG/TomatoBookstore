package com.tomatobookstore.tomatobookstore.serviceImpl;

import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.digest.DigestUtil;
import com.tomatobookstore.tomatobookstore.exception.TomatoBookstoreException;
import com.tomatobookstore.tomatobookstore.po.User;
import com.tomatobookstore.tomatobookstore.repository.UserRepository;
import com.tomatobookstore.tomatobookstore.service.UserService;
import com.tomatobookstore.tomatobookstore.util.TokenUtil;
import com.tomatobookstore.tomatobookstore.vo.LoginResultVO;
import com.tomatobookstore.tomatobookstore.vo.MessageVO;
import com.tomatobookstore.tomatobookstore.vo.RetUserVO;
import com.tomatobookstore.tomatobookstore.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public RetUserVO getUserDetail(String username) throws Exception {
        try {
            return userRepository.findByUsername(username).toRetVO();
        } catch (Exception e) {
            throw TomatoBookstoreException.userNotExisted();
        }
    }

    @Override
    public MessageVO createUser(UserVO user) {
        if(!Objects.equals(user.getRole(), "admin") && !Objects.equals(user.getRole(), "user") && !Objects.equals(user.getRole(), "staff"))
            throw TomatoBookstoreException.createFail();
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw TomatoBookstoreException.userExisted();
        }
        if(user.getTelephone() != null && (user.getTelephone().charAt(0) != '1' || user.getTelephone().length() != 11))
            throw TomatoBookstoreException.createFail();
        if(user.getEmail()!=null&&!Validator.isEmail(user.getEmail()))
            throw TomatoBookstoreException.createFail();
        userRepository.save(user.toPO());
        return new MessageVO("创建用户成功");
    }

    @Override
    public LoginResultVO login(String username, String password) {
        User user = userRepository.findByUsername(username);
        String tmp=password+"-=[]"+username;
        if (user != null&&user.getPassword().equals(DigestUtil.sha512Hex(tmp))) {
            return new LoginResultVO("登录成功", tokenUtil.getToken(user));
        }
        throw TomatoBookstoreException.loginFailure();
    }

    @Override
    public MessageVO update(UserVO userVO) {
        try {
            User user=userRepository.findByUsername(userVO.getUsername());
            if(userVO.getName()!=null)
                user.setName(userVO.getName());
            userVO.setPassword(DigestUtil.sha512Hex(userVO.getUsername()+"-=[]"+userVO.getPassword()));
            user.setAvatar(userVO.getAvatar());
            if(userVO.getTelephone() != null && (userVO.getTelephone().charAt(0) != '1' || userVO.getTelephone().length() != 11))
                throw TomatoBookstoreException.updateFailed();
            user.setTelephone(userVO.getTelephone());
            if(userVO.getEmail()!=null&&!Validator.isEmail(userVO.getEmail()))
                throw TomatoBookstoreException.updateFailed();
            user.setEmail(userVO.getEmail());
            user.setLocation(userVO.getLocation());
            userRepository.save(user);
            return new MessageVO("用户信息更新成功");
        } catch (Exception e) {
            throw TomatoBookstoreException.updateFailed();
        }
    }

}
