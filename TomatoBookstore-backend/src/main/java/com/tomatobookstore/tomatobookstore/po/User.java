package com.tomatobookstore.tomatobookstore.po;

import com.tomatobookstore.tomatobookstore.vo.RetUserVO;
import com.tomatobookstore.tomatobookstore.vo.UserVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Basic
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "role", nullable = false)
    private String role;

    @Basic
    @Column(name = "telephone")
    private String telephone;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "location")
    private String location;

    public UserVO toVO() {
        UserVO userVO = new UserVO();
        userVO.setUuid(uuid);
        userVO.setUsername(username);
        userVO.setPassword(password);
        userVO.setName(name);
        userVO.setAvatar(avatar);
        userVO.setRole(role);
        userVO.setTelephone(telephone);
        userVO.setEmail(email);
        userVO.setLocation(location);
        return userVO;
    }
    public RetUserVO toRetVO() {
        RetUserVO retUserVO = new RetUserVO();
        retUserVO.setUsername(username);
        retUserVO.setName(name);
        retUserVO.setAvatar(avatar);
        retUserVO.setRole(role);
        retUserVO.setTelephone(telephone);
        retUserVO.setEmail(email);
        retUserVO.setLocation(location);
        return retUserVO;
    }
}
