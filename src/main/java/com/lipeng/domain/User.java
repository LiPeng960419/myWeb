package com.lipeng.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lipeng 910138
 * @Date: 2020/6/5 16:02
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 3960492459365954358L;
    private Integer id;
    private String username;
    private String password;
    private String tel;
    private String email;
    private Double money;
}
