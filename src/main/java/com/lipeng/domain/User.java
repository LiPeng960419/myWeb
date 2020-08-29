package com.lipeng.domain;

import lombok.Data;

/**
 * @Author: lipeng 910138
 * @Date: 2020/6/5 16:02
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String tel;
    private String email;
    private Double money;
}
