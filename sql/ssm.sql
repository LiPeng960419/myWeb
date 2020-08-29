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

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `email` varchar(16) DEFAULT NULL,
  `money` double(16,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '李鹏', 'lipeng', '15671564665', '921403443@qq.com', '1314520.0000');
