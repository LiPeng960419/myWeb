package com.lipeng.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogMessage {

    private Long id;

    private Integer type;

    private String content;

    private String source;

    private Integer status;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer version;

}