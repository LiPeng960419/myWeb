package com.lipeng.domain;

import com.lipeng.common.mybatis.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/13 14:56
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class QueryUser extends PageParam<User> {

    private String name;

    private String password;

}