/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : homeworkcenter

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-08-07 18:14:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for homework_authority
-- ----------------------------
DROP TABLE IF EXISTS `homework_authority`;
CREATE TABLE `homework_authority` (
  `authority_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限id',
  `parent_authority_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '父级权限Id',
  `authority_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限名称',
  `authoruty_description` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_authority
-- ----------------------------
INSERT INTO `homework_authority` VALUES ('15a16c13-7394-482d-aba8-ae764d346c06', 'b1b91332-e099-4669-8836-360428c26888', '用户管理', '用户管理');
INSERT INTO `homework_authority` VALUES ('226977ef-22c8-40b9-b684-c7eeadb96d2d', '15a16c13-7394-482d-aba8-ae764d346c06', '查看用户', '查看用户信息');
INSERT INTO `homework_authority` VALUES ('941b51da-3509-4de4-a3ee-11046fa18e6d', '15a16c13-7394-482d-aba8-ae764d346c06', '新增用户', '新增用户信息');
INSERT INTO `homework_authority` VALUES ('b1b91332-e099-4669-8836-360428c26888', '0', '系统管理', '允许管理系统用户信息');
INSERT INTO `homework_authority` VALUES ('d1aab7c6-e807-425d-821f-1b7eddf659ee', '15a16c13-7394-482d-aba8-ae764d346c06', '删除用户', '删除用户信息');
INSERT INTO `homework_authority` VALUES ('e76220f5-9518-4cf7-bf9e-b3a5aedb68cf', '15a16c13-7394-482d-aba8-ae764d346c06', '修改用户', '修改用户信息');

-- ----------------------------
-- Table structure for homework_group
-- ----------------------------
DROP TABLE IF EXISTS `homework_group`;
CREATE TABLE `homework_group` (
  `group_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parent_group_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `group_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `group_description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `group_createtime` datetime NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_group
-- ----------------------------

-- ----------------------------
-- Table structure for homework_group_authority
-- ----------------------------
DROP TABLE IF EXISTS `homework_group_authority`;
CREATE TABLE `homework_group_authority` (
  `group_authority_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `group_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `authority_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `group_authority_type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`group_authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_group_authority
-- ----------------------------

-- ----------------------------
-- Table structure for homework_group_role
-- ----------------------------
DROP TABLE IF EXISTS `homework_group_role`;
CREATE TABLE `homework_group_role` (
  `group_role_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `group_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '组id',
  `role_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`group_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_group_role
-- ----------------------------

-- ----------------------------
-- Table structure for homework_log
-- ----------------------------
DROP TABLE IF EXISTS `homework_log`;
CREATE TABLE `homework_log` (
  `log_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `log_operate_type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_operate_content` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作内容',
  `log_operate_user` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作人',
  `log_createtime` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'log创建时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_log
-- ----------------------------

-- ----------------------------
-- Table structure for homework_menu
-- ----------------------------
DROP TABLE IF EXISTS `homework_menu`;
CREATE TABLE `homework_menu` (
  `menu_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `menu_parent_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_icon` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_order` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_createuser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_createtime` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_menu
-- ----------------------------
INSERT INTO `homework_menu` VALUES ('0bc83050-f2fc-4bad-bd67-1d24c4ca72d0', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '资源管理', '用户资源管理', 'fa fa-briefcase', '/admin/user/resourcesmanage.html', '66', 'admin', null);
INSERT INTO `homework_menu` VALUES ('3e917225-1d57-4d52-abaf-c70e49eb591c', '0', '收藏图片', '图片管理', 'glyphicon glyphicon-picture', '/admin/userinfo_manage.html', '2', 'admin', null);
INSERT INTO `homework_menu` VALUES ('3f4fa6c2-d1d0-4778-9634-6db80570fc41', '0', '收藏视频', '增加用户信息', 'glyphicon glyphicon-facetime-video', '/admin/userinfo_add.action', '3', 'admin', null);
INSERT INTO `homework_menu` VALUES ('4ab1b675-73d7-493f-a8bb-90f4fd509a7a', '006bfbf258-e8da-4e82-8a3a-301f7c65c90d', '权限管理', '系统权限管理', 'glyphicon glyphicon-indent-right', null, '65', 'admin', null);
INSERT INTO `homework_menu` VALUES ('573d78f1-428d-4134-8e68-0fe7f9b853b0', 'e3fdccb6-6dbf-4464-847a-dd150b3ecc51', '收藏文章', '', 'glyphicon glyphicon-book', null, '7', 'admin', null);
INSERT INTO `homework_menu` VALUES ('63f66e8e-2db4-4d88-9b71-c8640d9bfae8', '0', '邮件管理', null, 'glyphicon glyphicon-envelope', null, '1', 'admin', null);
INSERT INTO `homework_menu` VALUES ('6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '0', '系统管理', '系统管理节点', 'glyphicon glyphicon-cog', '#', '6', 'admin', '2015-07-15 11:12:32');
INSERT INTO `homework_menu` VALUES ('97a86a1b-141f-4ba3-8339-3d12e1f5a7e1', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '菜单管理', null, 'glyphicon glyphicon-th-list', null, '3', 'admin', null);
INSERT INTO `homework_menu` VALUES ('a85e0b27-ee9b-4965-94f2-6804bf474521', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '用户组管理', '系统用户组管理', 'fa fa-users', '/admin/usermanage.html', '64', 'admin', null);
INSERT INTO `homework_menu` VALUES ('bcbc508c-cae6-472c-a53b-1cf917808970', 'e3fdccb6-6dbf-4464-847a-dd150b3ecc51', '学生作业', null, 'fa fa-file-archive-o', null, '41', 'admin', null);
INSERT INTO `homework_menu` VALUES ('cb1cb36c-16d0-476d-8265-c10dd640097d', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '用户管理', '系统用户管理，包含管理员，教师和学生', 'glyphicon glyphicon-wrench', '/admin/usermanage.html', '61', 'admin', null);
INSERT INTO `homework_menu` VALUES ('d4bb547d-4280-47fc-9f0e-2fd9d6e1316e', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '角色管理', '系统角色', 'fa fa-graduation-cap', null, '62', 'admin', null);
INSERT INTO `homework_menu` VALUES ('df06cf48-8acd-45f4-9647-703ccfcaa8f1', '0', '收藏网页', null, 'glyphicon glyphicon-file', null, '5', 'admin', null);
INSERT INTO `homework_menu` VALUES ('e3fdccb6-6dbf-4464-847a-dd150b3ecc51', '0', '作业管理', null, 'glyphicon glyphicon-tag', null, '4', 'admin', null);
INSERT INTO `homework_menu` VALUES ('ea9b7e86-282f-4ca4-a40f-2f08186452f8', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d', '参数管理', '系统参数管理', 'glyphicon glyphicon-tags', '/admin/parametermanage.html', '63', 'admin', null);

-- ----------------------------
-- Table structure for homework_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `homework_menu_function`;
CREATE TABLE `homework_menu_function` (
  `menu_function_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_function_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_function_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '功能描述',
  `menu_function_action` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '功能地址',
  `menu_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_menu_function
-- ----------------------------
INSERT INTO `homework_menu_function` VALUES ('2a06a17d-fd4c-4da7-bbd3-6cb0150adc71', '增加用户', '增加用户信息（管理员）', '/admin/userinfo/add_userinfo.action', 'cb1cb36c-16d0-476d-8265-c10dd640097d');
INSERT INTO `homework_menu_function` VALUES ('86f2fda5-f4fe-4228-bd6d-1d874013c0ac', '删除用户', '删除用户信息（管理员）', '/admin/userinfo/del_userinfo.action', 'cb1cb36c-16d0-476d-8265-c10dd640097d');
INSERT INTO `homework_menu_function` VALUES ('6974522f-b0e5-4c59-9577-16895fb9a27e', '修改用户', '修改用用户信息', '/admin/userinfo/update_userinfo.action', 'cb1cb36c-16d0-476d-8265-c10dd640097d');
INSERT INTO `homework_menu_function` VALUES ('f8ccc1cf-2487-4243-9764-01c149824de4', '查询用户', '根据过滤器条件查询用户', '/admin/userinfo/search_userinfo.action', 'cb1cb36c-16d0-476d-8265-c10dd640097d');

-- ----------------------------
-- Table structure for homework_organize
-- ----------------------------
DROP TABLE IF EXISTS `homework_organize`;
CREATE TABLE `homework_organize` (
  `organize_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parent_organize_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '组织父级id',
  `organize_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '组织姓名',
  `organize_createtime` varchar(25) COLLATE utf8_unicode_ci NOT NULL COMMENT '组织创建时间',
  `organize_description` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '组织描述',
  PRIMARY KEY (`organize_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_organize
-- ----------------------------

-- ----------------------------
-- Table structure for homework_rightcolumn
-- ----------------------------
DROP TABLE IF EXISTS `homework_rightcolumn`;
CREATE TABLE `homework_rightcolumn` (
  `right_column_id` int(11) NOT NULL AUTO_INCREMENT,
  `right_column_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`right_column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_rightcolumn
-- ----------------------------

-- ----------------------------
-- Table structure for homework_role
-- ----------------------------
DROP TABLE IF EXISTS `homework_role`;
CREATE TABLE `homework_role` (
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `parent_role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '父级角色id',
  `createdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '角色描述',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_role
-- ----------------------------
INSERT INTO `homework_role` VALUES ('1c39fb09-e4f4-4b2f-876a-ee30bb470066', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472', '2015-08-06 11:30“34', '钻石用户', '钻石用户');
INSERT INTO `homework_role` VALUES ('1e310db0-6331-4457-8868-ca6a6b21ae83', '1c39fb09-e4f4-4b2f-876a-ee30bb470066', '2015-08-07 10:44:32', '黄金用户，充值缴费很多', '黄金用户');
INSERT INTO `homework_role` VALUES ('374abc7b-324b-4958-88e0-2a62ffb32691', '9f83dc60-b9bd-438a-907f-02ce376ee356', '2015-08-06 11:32:23', '访客，只能查看内容，没有其他任何权限', '访客');
INSERT INTO `homework_role` VALUES ('9a2a486b-26b8-456c-91c2-b35824a65e88', '1e310db0-6331-4457-8868-ca6a6b21ae83', '2015-08-07 10:34:45', '新用户', '新用户');
INSERT INTO `homework_role` VALUES ('9f83dc60-b9bd-438a-907f-02ce376ee356', '1e310db0-6331-4457-8868-ca6a6b21ae83', '2015-07-15 10:56:34', '普通用户，仅能管理自己的账号，无其他权限', '普通用户');
INSERT INTO `homework_role` VALUES ('c7675d06-ed46-429e-ae2f-aa882fc29bae', '0', '2015-07-15 09:43:23', '超级管理员权限，什么权限都有', '超级管理员');
INSERT INTO `homework_role` VALUES ('d36b7df1-dac0-4ff9-9c2b-a101411f8472', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '2015-07-15 10:55:34', 'VIP用户', 'VIP用户');

-- ----------------------------
-- Table structure for homework_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `homework_role_authority`;
CREATE TABLE `homework_role_authority` (
  `role_authority_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `authority_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `authority_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限类型,0:可访问，1:可授权',
  `is_access` int(2) DEFAULT NULL COMMENT '允许访问（0，不允许，1允许）',
  `is_authorization` int(2) DEFAULT NULL COMMENT '是否允许授权（0，不允许，1允许）',
  PRIMARY KEY (`role_authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_role_authority
-- ----------------------------
INSERT INTO `homework_role_authority` VALUES ('218dd5df-cb9f-44b8-8331-bb0d6db32f8d', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'b1b91332-e099-4669-8836-360428c26888', '管理用户类型', '1', '1');
INSERT INTO `homework_role_authority` VALUES ('228a2863-528e-43f5-9621-69b1ef175745', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '941b51da-3509-4de4-a3ee-11046fa18e6d', '新增用户信息', '1', '1');
INSERT INTO `homework_role_authority` VALUES ('3f384cb5-8038-4b90-b15a-dbbc3b90f0a8', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '226977ef-22c8-40b9-b684-c7eeadb96d2d', '查看用户信息', '1', '1');
INSERT INTO `homework_role_authority` VALUES ('85088d3a-50cb-432f-b003-3a03030ad91a', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'd1aab7c6-e807-425d-821f-1b7eddf659ee', '删除用户信息', '1', '1');
INSERT INTO `homework_role_authority` VALUES ('a131f6c9-1fdb-47c1-a645-1b7b6aef6b82', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472', '226977ef-22c8-40b9-b684-c7eeadb96d2d', '查看用户', '1', '1');
INSERT INTO `homework_role_authority` VALUES ('b9897173-e941-4dd2-925e-32d30296adc6', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '15a16c13-7394-482d-aba8-ae764d346c06', '用户管理', '1', '1');
INSERT INTO `homework_role_authority` VALUES ('fc647831-3f16-4af1-a5dc-4aacbe31b6e6', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'e76220f5-9518-4cf7-bf9e-b3a5aedb68cf', '修改用户信息', '1', '1');

-- ----------------------------
-- Table structure for homework_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `homework_role_menu`;
CREATE TABLE `homework_role_menu` (
  `role_menu_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_role_menu
-- ----------------------------
INSERT INTO `homework_role_menu` VALUES ('14c15187-4784-429d-9b5f-d3328ceca780', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'd4bb547d-4280-47fc-9f0e-2fd9d6e1316e');
INSERT INTO `homework_role_menu` VALUES ('34010ad7-5a8d-4c76-8e1c-aec3117802f2', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'df06cf48-8acd-45f4-9647-703ccfcaa8f1');
INSERT INTO `homework_role_menu` VALUES ('4115cd02-0ba1-4d50-a10f-f18a532695d0', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'cb1cb36c-16d0-476d-8265-c10dd640097d');
INSERT INTO `homework_role_menu` VALUES ('4c827a43-ed67-4321-9aa5-2ccc158e67ba', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '3e917225-1d57-4d52-abaf-c70e49eb591c');
INSERT INTO `homework_role_menu` VALUES ('4cb667c3-6e1f-4e09-a629-96db8c7b1ef3', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472', '63f66e8e-2db4-4d88-9b71-c8640d9bfae8');
INSERT INTO `homework_role_menu` VALUES ('60a704d6-dba9-4bd3-a10a-645165f5fe86', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d');
INSERT INTO `homework_role_menu` VALUES ('76b792a8-a57d-43d6-b8a5-541a6fcebba4', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '0bc83050-f2fc-4bad-bd67-1d24c4ca72d0');
INSERT INTO `homework_role_menu` VALUES ('7ef6ec13-244b-4626-a3fc-5ab1add6c03f', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472', '3e917225-1d57-4d52-abaf-c70e49eb591c');
INSERT INTO `homework_role_menu` VALUES ('95066375-abb0-4dd3-9d05-cf13aee2358b', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'ea9b7e86-282f-4ca4-a40f-2f08186452f8');
INSERT INTO `homework_role_menu` VALUES ('a6a580d0-9d25-4cc6-a810-2d51bc57556f', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '97a86a1b-141f-4ba3-8339-3d12e1f5a7e1');
INSERT INTO `homework_role_menu` VALUES ('a7b7578a-889d-4c71-b0ee-9f52ba3caf50', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '573d78f1-428d-4134-8e68-0fe7f9b853b0');
INSERT INTO `homework_role_menu` VALUES ('bc60761a-7626-47ed-805c-e806de7c30b3', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '4ab1b675-73d7-493f-a8bb-90f4fd509a7a');
INSERT INTO `homework_role_menu` VALUES ('cdf7cead-660f-43f2-bccf-4d34d6e8cfcc', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', '63f66e8e-2db4-4d88-9b71-c8640d9bfae8');
INSERT INTO `homework_role_menu` VALUES ('d1870d5b-dadc-4767-ac5c-413867df4319', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472', '6bfbf258-e8da-4e82-8a3a-301f7c65c90d');
INSERT INTO `homework_role_menu` VALUES ('d8e9dfbc-6f7c-4337-a261-fba65a0efad5', 'c7675d06-ed46-429e-ae2f-aa882fc29bae', 'e3fdccb6-6dbf-4464-847a-dd150b3ecc51');

-- ----------------------------
-- Table structure for homework_role_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `homework_role_menu_function`;
CREATE TABLE `homework_role_menu_function` (
  `role_menu_function_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `function_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_menu_function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_role_menu_function
-- ----------------------------
INSERT INTO `homework_role_menu_function` VALUES ('0dc02d9e-72b2-4bba-a33e-f6fc05f98ee0', '2a06a17d-fd4c-4da7-bbd3-6cb0150adc71', 'cb1cb36c-16d0-476d-8265-c10dd640097d', 'c7675d06-ed46-429e-ae2f-aa882fc29bae');
INSERT INTO `homework_role_menu_function` VALUES ('3634dfb0-908f-4aa2-a9c2-314ebdb23395', '6974522f-b0e5-4c59-9577-16895fb9a27e', 'cb1cb36c-16d0-476d-8265-c10dd640097d', 'c7675d06-ed46-429e-ae2f-aa882fc29bae');

-- ----------------------------
-- Table structure for homework_task
-- ----------------------------
DROP TABLE IF EXISTS `homework_task`;
CREATE TABLE `homework_task` (
  `task_id` int(11) NOT NULL,
  `task_author_id` int(11) DEFAULT NULL,
  `task_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `task_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `task_evaluate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任务评级，星星显示',
  `task_state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任务状态-审核，未审核',
  `task_result` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核结果',
  `task_snapshot` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `task_auditor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核者',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_task
-- ----------------------------

-- ----------------------------
-- Table structure for homework_user
-- ----------------------------
DROP TABLE IF EXISTS `homework_user`;
CREATE TABLE `homework_user` (
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户登录名',
  `user_title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `user_password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_callname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户账号',
  `user_organize_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户组织id',
  `user_email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮件',
  `user_phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户电话',
  `user_createtime` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户登录次数',
  `user_logintime` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user
-- ----------------------------
INSERT INTO `homework_user` VALUES ('02447b02-c243-44f9-bcd6-d66440b5efe6', 'student', null, '123456', 'Student', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');
INSERT INTO `homework_user` VALUES ('196169b4-d40f-407b-abaf-0477ae8b947b', 'user2', null, '111111', 'VIP用户', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');
INSERT INTO `homework_user` VALUES ('247357ca-4759-4143-b3c4-d47a261ba0c7', 'colin', null, 'admin123', 'Administrator', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');
INSERT INTO `homework_user` VALUES ('49abec95-ba26-4628-b89d-df1322bd91a8', 'user1', null, '111111', '访客', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');
INSERT INTO `homework_user` VALUES ('4ac0ea80-b1b0-417d-afb5-ed6bdce8dbc1', 'user3', null, '111111', '钻石用户', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');
INSERT INTO `homework_user` VALUES ('5dbfe4ef-785a-40bd-97e9-7f631c7c3f25', 'user4', null, '1111111', '访客2', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');
INSERT INTO `homework_user` VALUES ('e5c6e23d-28fe-4e75-a12d-d53951732d59', 'teacher', null, '123456', 'Teacher', null, '92934@qq.com', '11223432443', '2015-08-07 11:23:23', '2015-08-07 11:23:23');

-- ----------------------------
-- Table structure for homework_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `homework_user_authority`;
CREATE TABLE `homework_user_authority` (
  `user_authority_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限id',
  `authority_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`user_authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user_authority
-- ----------------------------

-- ----------------------------
-- Table structure for homework_user_group
-- ----------------------------
DROP TABLE IF EXISTS `homework_user_group`;
CREATE TABLE `homework_user_group` (
  `user_group_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for homework_user_role
-- ----------------------------
DROP TABLE IF EXISTS `homework_user_role`;
CREATE TABLE `homework_user_role` (
  `user_role_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user_role
-- ----------------------------
INSERT INTO `homework_user_role` VALUES ('1b8c5403-f30b-419f-965f-51bf42a3f68a', '247357ca-4759-4143-b3c4-d47a261ba0c7', '1e310db0-6331-4457-8868-ca6a6b21ae83');
INSERT INTO `homework_user_role` VALUES ('284bbfa1-2cd3-4a2e-9c09-79f42407a326', '196169b4-d40f-407b-abaf-0477ae8b947b', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472');
INSERT INTO `homework_user_role` VALUES ('8458c135-a670-482c-b907-652485f8a2a1', 'e5c6e23d-28fe-4e75-a12d-d53951732d59', 'd36b7df1-dac0-4ff9-9c2b-a101411f8472');
INSERT INTO `homework_user_role` VALUES ('96c9eba6-3a23-42bb-ae87-f0707bc53290', '247357ca-4759-4143-b3c4-d47a261ba0c7', 'c7675d06-ed46-429e-ae2f-aa882fc29bae');
INSERT INTO `homework_user_role` VALUES ('abd5df11-e59c-4abe-ba3e-869c44dfb6da', '02447b02-c243-44f9-bcd6-d66440b5efe6', '9f83dc60-b9bd-438a-907f-02ce376ee356');
INSERT INTO `homework_user_role` VALUES ('ff7856f7-edef-46cd-b455-ce8577871e2d', '49abec95-ba26-4628-b89d-df1322bd91a8', '374abc7b-324b-4958-88e0-2a62ffb32691');

-- ----------------------------
-- Function structure for getChildLst
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildLst`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildLst`(rootId varchar(50)) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_unicode_ci
BEGIN
       DECLARE sTemp VARCHAR(1000);
       DECLARE sTempChd VARCHAR(1000);
    
       SET sTemp = '$';
       SET sTempChd =rootId;
    
       WHILE sTempChd is not null DO
         SET sTemp = concat(sTemp,',',sTempChd);
        SELECT group_concat(role_id) INTO sTempChd FROM homework_role where FIND_IN_SET(parent_role_id,sTempChd)>0;
      END WHILE;
      RETURN sTemp;
     END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getChildLst_SupplierType
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildLst_SupplierType`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildLst_SupplierType`(codeId varchar(50)) RETURNS varchar(1000) CHARSET utf8 COLLATE utf8_unicode_ci
BEGIN  
DECLARE sTemp VARCHAR(1000);  
DECLARE sTempChd VARCHAR(1000);  
declare rootId varchar (200);  
SET sTemp = '$';  
  
#SET sTempChd =cast(codeId as CHAR);  

select parent_role_id into rootId  from  homework_role  where role_id =sTempChd;  
  
SET sTempChd=rootId;  
  
WHILE sTempChd is not null DO  
SET sTemp = concat(sTemp,',',sTempChd);  
SELECT group_concat(role_id) INTO sTempChd FROM homework_role where FIND_IN_SET(parent_role_id,sTempChd)>0;  
END WHILE;  
  
RETURN sTemp;  
  
END
;;
DELIMITER ;
