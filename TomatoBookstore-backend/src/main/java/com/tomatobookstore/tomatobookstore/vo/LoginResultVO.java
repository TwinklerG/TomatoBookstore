package com.tomatobookstore.tomatobookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResultVO {
    private String msg;
    private String token;

}
