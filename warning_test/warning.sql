/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : warning

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-03-08 09:28:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_warning_group
-- ----------------------------
DROP TABLE IF EXISTS `t_warning_group`;
CREATE TABLE `t_warning_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '组名称',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '0: 有效，1: 无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='报警组';

-- ----------------------------
-- Records of t_warning_group
-- ----------------------------
INSERT INTO `t_warning_group` VALUES ('1', 'pingan_pay_order_group', '0', '2016-11-17 18:08:09', null);
INSERT INTO `t_warning_group` VALUES ('2', 'sys_exception_group', '0', '2016-11-17 18:08:59', null);

-- ----------------------------
-- Table structure for t_warning_item
-- ----------------------------
DROP TABLE IF EXISTS `t_warning_item`;
CREATE TABLE `t_warning_item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '报警项名称',
  `send_sms` tinyint(1) NOT NULL COMMENT '是否发短信（0：否  1：是）',
  `send_email` tinyint(1) NOT NULL COMMENT '是否发邮件（0：否  1：是）',
  `sms_tpl` varchar(255) DEFAULT NULL COMMENT '短信模板',
  `email_title` varchar(255) DEFAULT NULL COMMENT '邮件标题',
  `email_tpl` varchar(255) DEFAULT NULL COMMENT '邮件模板',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '0: 有效，1: 无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='报警项';

-- ----------------------------
-- Records of t_warning_item
-- ----------------------------
INSERT INTO `t_warning_item` VALUES ('1', 'global_inventory', '1', '0', '%skuName%(id=%skuId%)库存不足, 当前库存为%warning_inventory%。', null, null, '0', '2016-09-18 10:04:56', null);
INSERT INTO `t_warning_item` VALUES ('2', 'sku_inventory', '1', '0', '%skuName%(id=%skuId%)库存不足, 当前库存为%warning_inventory%。', null, null, '0', '2016-09-18 10:04:56', null);
INSERT INTO `t_warning_item` VALUES ('3', 'pingan_pay_order_exception', '1', '0', '订单已支付后被取消或删除，订单号：%orderNo%【平安口袋银行】', null, null, '0', '2016-11-17 18:08:09', null);
INSERT INTO `t_warning_item` VALUES ('4', 'sys_exception', '1', '0', '系统异常，内容:%exception%', null, null, '0', '2016-11-17 18:08:59', '2016-11-22 10:01:44');

-- ----------------------------
-- Table structure for t_warning_item_group
-- ----------------------------
DROP TABLE IF EXISTS `t_warning_item_group`;
CREATE TABLE `t_warning_item_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `warning_item_id` int(11) NOT NULL COMMENT '预警项ID',
  `warning_group_id` int(11) NOT NULL COMMENT '预警组ID',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '0: 有效，1: 无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_item_id_group_id` (`warning_item_id`,`warning_group_id`),
  KEY `idx_warning_item_id` (`warning_item_id`),
  KEY `idx_warning_group_id` (`warning_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='报警项发送给哪些报警组';

-- ----------------------------
-- Records of t_warning_item_group
-- ----------------------------
INSERT INTO `t_warning_item_group` VALUES ('1', '3', '1', '0', '2016-11-17 18:08:10', '2016-11-17 18:08:10');
INSERT INTO `t_warning_item_group` VALUES ('2', '4', '2', '0', '2016-11-17 18:08:59', '2016-11-17 18:08:59');

-- ----------------------------
-- Table structure for t_warning_user
-- ----------------------------
DROP TABLE IF EXISTS `t_warning_user`;
CREATE TABLE `t_warning_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号，用于短信报警',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱，用户邮件报警',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '0: 有效，1: 无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='报警用户';

-- ----------------------------
-- Records of t_warning_user
-- ----------------------------
INSERT INTO `t_warning_user` VALUES ('1', '', '18600405541', '', '0', '2016-11-17 18:08:09', null);
INSERT INTO `t_warning_user` VALUES ('2', '', '18610295134', '', '0', '2016-11-17 18:08:09', null);
INSERT INTO `t_warning_user` VALUES ('3', '', '18500611321', '', '0', '2016-11-17 18:08:59', null);
INSERT INTO `t_warning_user` VALUES ('4', '', '15710054280', '', '0', '2016-11-17 18:08:59', null);

-- ----------------------------
-- Table structure for t_warning_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_warning_user_group`;
CREATE TABLE `t_warning_user_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `warning_user_id` int(11) NOT NULL COMMENT '用户ID',
  `warning_group_id` int(11) NOT NULL COMMENT '组ID',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '0: 有效，1: 无效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_warning_user_id` (`warning_user_id`),
  KEY `idx_warning_group_id` (`warning_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='报警用户和组的关联表';

-- ----------------------------
-- Records of t_warning_user_group
-- ----------------------------
INSERT INTO `t_warning_user_group` VALUES ('1', '1', '1', '0', '2016-11-17 18:08:10', '2016-11-17 18:08:10');
INSERT INTO `t_warning_user_group` VALUES ('2', '2', '1', '0', '2016-11-17 18:08:10', '2016-11-17 18:08:10');
INSERT INTO `t_warning_user_group` VALUES ('3', '3', '2', '0', '2016-11-17 18:08:59', '2016-11-17 18:08:59');
INSERT INTO `t_warning_user_group` VALUES ('4', '4', '2', '0', '2016-11-17 18:08:59', '2016-11-17 18:08:59');

