/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : minemanage

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-03-08 14:24:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_b_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_b_sms`;
CREATE TABLE `t_b_sms` (
  `id` varchar(32) NOT NULL COMMENT '唯一标识',
  `group_code` varchar(50) DEFAULT NULL COMMENT '集团编码',
  `group_name` varchar(50) DEFAULT NULL COMMENT '集团名称',
  `mine_code` varchar(50) DEFAULT NULL COMMENT '矿井编码',
  `mine_name` varchar(50) DEFAULT NULL COMMENT '矿井名称',
  `type` varchar(10) DEFAULT NULL COMMENT '短信类型',
  `content` varchar(1000) DEFAULT NULL COMMENT '短信内容',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '发往至手机号',
  `request_time` datetime DEFAULT NULL COMMENT '请求发送时间',
  `send_time` datetime DEFAULT NULL COMMENT '实际发送时间',
  `handle_status` varchar(10) DEFAULT '0' COMMENT '0：未发送；1：已发送成功；2：已发送失败',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人,录入人',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人登录名',
  `update_name` varchar(100) DEFAULT NULL COMMENT '更新人名称',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
