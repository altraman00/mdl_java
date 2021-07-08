/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : tools-home

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 02/01/2021 21:37:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` int(11) DEFAULT '0',
  `create_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` varchar(32) NOT NULL DEFAULT '1.0.0',
  `sort_no` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', '张三', 19, '2020-08-24 16:23:23', '2020-08-24 16:23:34', 0, '1.0.0', 0);
COMMIT;

-- ----------------------------
-- Table structure for tb_user_tools_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_tools_category`;
CREATE TABLE `tb_user_tools_category` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL DEFAULT '0' COMMENT '用户id',
  `name` varchar(255) NOT NULL COMMENT '类别名称',
  `create_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` varchar(32) NOT NULL DEFAULT '1.0.0',
  `sort_no` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user_tools_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_tools_category` VALUES ('1', '1', '博客', '2020-08-24 16:23:23', '2021-01-02 16:22:22', 0, '1.0.0', 1);
INSERT INTO `tb_user_tools_category` VALUES ('2', '1', '社区', '2020-08-25 11:41:09', '2021-01-02 16:22:36', 0, '1.0.0', 2);
INSERT INTO `tb_user_tools_category` VALUES ('3', '1', '工具', '2020-12-22 09:53:16', '2021-01-02 16:22:23', 0, '1.0.0', 3);
INSERT INTO `tb_user_tools_category` VALUES ('4', '1', '其他', '2020-12-21 17:13:43', '2021-01-02 16:22:34', 0, '1.0.0', 4);
COMMIT;

-- ----------------------------
-- Table structure for tb_user_tools_category_site
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_tools_category_site`;
CREATE TABLE `tb_user_tools_category_site` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `category_id` char(32) NOT NULL DEFAULT '' COMMENT '用户个人类别id（每个用户的类别不一样）',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '网址标题',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '网址url',
  `img` varchar(255) DEFAULT NULL COMMENT 'logo',
  `intro` varchar(255) NOT NULL DEFAULT '' COMMENT '网址简介',
  `create_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` varchar(32) NOT NULL DEFAULT '1.0.0',
  `sort_no` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user_tools_category_site
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_tools_category_site` VALUES ('1', '1', '1', '博客园', 'https://www.cnblogs.com/', '../static/img/logo_small.gif', '开发者的网上家园', '2021-01-02 17:12:42', '2021-01-02 17:14:03', 0, '1.0.0', 0);
INSERT INTO `tb_user_tools_category_site` VALUES ('2', '1', '1', 'CSDN', 'https://www.csdn.net/', '../static/img/logo_small.gif', '专业IT技术社区', '2021-01-02 17:14:27', '2021-01-02 17:15:29', 0, '1.0.0', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
