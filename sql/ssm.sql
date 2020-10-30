/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-08-29 21:37:30
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`       int(10) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(32)   DEFAULT NULL,
    `password` varchar(32)   DEFAULT NULL,
    `tel`      varchar(11)   DEFAULT NULL,
    `email`    varchar(16)   DEFAULT NULL,
    `money`    double(16, 4) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '李鹏', 'lipeng', '15671564665', '921403443@qq.com', '1314520.0000');

-- ----------------------------
-- Table structure for t_log_message
-- ----------------------------
DROP TABLE IF EXISTS `t_log_message`;
CREATE TABLE `t_log_message`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type`        tinyint(4)  NOT NULL DEFAULT '-1' COMMENT '日志消息类型',
    `content`     varchar(64) NOT NULL DEFAULT '' COMMENT '日志消息内容',
    `source`      varchar(32) NOT NULL DEFAULT '' COMMENT '日志消息来源',
    `status`      tinyint(4)  NOT NULL DEFAULT '0' COMMENT '日志消息状态，1有效 0无效 ',
    `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time` datetime             DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `version`     int(11)     NOT NULL DEFAULT '1' COMMENT '操作版本号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_source` (`source`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='日志消息表';

-- ----------------------------
-- Records of t_log_message
-- ----------------------------