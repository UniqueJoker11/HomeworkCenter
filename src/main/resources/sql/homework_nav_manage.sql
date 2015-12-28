/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : homework

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-12-27 22:24:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for homework_nav_manage
-- ----------------------------
DROP TABLE IF EXISTS `homework_nav_manage`;
CREATE TABLE `homework_nav_manage` (
  `nav_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `nav_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nav_parent_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nav_createtime` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nav_user` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`nav_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
