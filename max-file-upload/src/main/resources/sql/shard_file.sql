/*
 Navicat Premium Data Transfer

 Source Server         : localhost_5.7
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : mcloud

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 19/10/2020 18:37:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shard_file
-- ----------------------------
DROP TABLE IF EXISTS `shard_file`;
CREATE TABLE `shard_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `path` varchar(100) NOT NULL COMMENT '相对路径',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(10) DEFAULT NULL COMMENT '后缀',
  `size` int(11) DEFAULT NULL COMMENT '大小|字节B',
  `shard_index` int(11) DEFAULT NULL COMMENT '已上传分片',
  `shard_size` int(11) DEFAULT NULL COMMENT '分片大小|B',
  `shard_total` int(11) DEFAULT NULL COMMENT '分片总数',
  `file_key` varchar(100) DEFAULT NULL COMMENT '文件标识',
  `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
