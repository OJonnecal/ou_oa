/*
 Navicat Premium Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : ou_oa

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 02/05/2023 22:07:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assignment
-- ----------------------------
DROP TABLE IF EXISTS `assignment`;
CREATE TABLE `assignment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务内容',
  `level` int NULL DEFAULT NULL COMMENT '紧急程度 1 紧急 2 正常 3 不紧急',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务创建时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '任务状态 1（true）已完成， 0（false）未完成',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '任务清单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of assignment
-- ----------------------------
INSERT INTO `assignment` VALUES (1, NULL, '1', '1', 1, '2023-04-24 05:04:44', 0, 1);
INSERT INTO `assignment` VALUES (2, NULL, '1', '1', 1, '2023-04-24 05:07:41', 1, 1);
INSERT INTO `assignment` VALUES (3, NULL, '1', '1', 1, '2023-04-24 05:11:44', 1, 1);
INSERT INTO `assignment` VALUES (4, NULL, '加班', '加班', 1, '2023-04-24 05:12:09', 1, 0);
INSERT INTO `assignment` VALUES (5, NULL, '1', '1', 3, '2023-04-29 12:10:42', 0, 0);

-- ----------------------------
-- Table structure for clock_in
-- ----------------------------
DROP TABLE IF EXISTS `clock_in`;
CREATE TABLE `clock_in`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `type` int NULL DEFAULT NULL COMMENT '打卡类型，上班打卡(1)，下班打卡(2)',
  `time` datetime NULL DEFAULT NULL COMMENT '打卡时间',
  `date` datetime NULL DEFAULT NULL COMMENT '打卡时期',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `clock_in_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打卡表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of clock_in
-- ----------------------------
INSERT INTO `clock_in` VALUES (10, 1, 1, '2023-01-10 15:29:23', '2023-01-10 00:00:00', 0);
INSERT INTO `clock_in` VALUES (11, 1, 2, '2023-01-10 15:29:26', '2023-01-10 00:00:00', 0);
INSERT INTO `clock_in` VALUES (12, 1, 1, '2023-03-19 14:37:33', '2023-03-19 00:00:00', 0);
INSERT INTO `clock_in` VALUES (13, 1, 2, '2023-03-19 14:37:44', '2023-03-19 00:00:00', 0);
INSERT INTO `clock_in` VALUES (14, 1, 1, '2023-04-20 21:51:21', '2023-04-20 00:00:00', 0);
INSERT INTO `clock_in` VALUES (15, 1, 2, '2023-04-20 21:51:23', '2023-04-20 00:00:00', 0);
INSERT INTO `clock_in` VALUES (16, 1, 1, '2023-04-21 15:36:24', '2023-04-21 00:00:00', 0);
INSERT INTO `clock_in` VALUES (17, 8, 2, '2023-04-21 15:36:25', '2023-04-21 00:00:00', 0);

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `remarks` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通讯录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES (1, NULL, '欧家俊', '15915095796', '1', 0);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户手机号',
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户创建时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '欧家俊', '15915095796', 0, '2023-04-21 17:12:54', '');
INSERT INTO `customer` VALUES (6, '1', '1', 0, '2023-04-29 11:04:59', '1');

-- ----------------------------
-- Table structure for expense
-- ----------------------------
DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报销原因',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报销描述',
  `amount` int NULL DEFAULT NULL COMMENT '报销金额',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报销申请时间',
  `approve_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报销审批时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '申请状态 2 未通过，1 通过，0 未审核',
  `user_id` int NULL DEFAULT NULL COMMENT '申请人id',
  `is_deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `expense_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报销申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expense
-- ----------------------------
INSERT INTO `expense` VALUES (1, '1', '1', 1, '2023-04-27 10:54:10', '2023-04-27 10:55:06', 1, 8, 0);
INSERT INTO `expense` VALUES (2, '2', '2', 2, '2023-04-27 10:55:11', '2023-04-27 10:55:12', 2, 8, 0);
INSERT INTO `expense` VALUES (3, '1', '', NULL, '2023-05-02 10:23:30', NULL, 0, 8, 0);

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假原因',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假描述',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '请假开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '请假结束时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态 2 未通过，1 通过，0 未审核',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请时间',
  `approve_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批时间',
  `user_id` int NULL DEFAULT NULL COMMENT '请假人',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `leave_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假申请表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES (19, '4', '4', '2023-04-12 17:00:00', '2023-04-13 18:00:00', 1, '2023-04-27 10:01:37', '2023-04-27 10:12:30', NULL, 1);
INSERT INTO `leave` VALUES (20, '5', '5', '2023-04-12 17:00:00', '2023-04-13 18:00:00', 2, '2023-04-27 10:01:41', '2023-04-27 10:10:52', NULL, 1);
INSERT INTO `leave` VALUES (22, '1', '1', '2023-04-03 18:00:00', '2023-04-04 18:00:00', 0, '2023-04-27 10:14:22', NULL, NULL, 0);
INSERT INTO `leave` VALUES (23, '2', '2', '2023-04-03 18:00:00', '2023-04-04 18:00:00', 0, '2023-04-27 10:14:29', NULL, NULL, 0);
INSERT INTO `leave` VALUES (24, '3', '3', '2023-04-04 16:00:00', '2023-04-05 16:00:00', 0, '2023-04-27 10:18:32', NULL, 9, 0);
INSERT INTO `leave` VALUES (25, '1', '1', NULL, NULL, 1, '2023-04-27 11:04:42', '2023-04-27 17:03:09', 8, 1);
INSERT INTO `leave` VALUES (26, '1', '1', '2023-04-05 16:00:00', '2023-04-06 16:00:00', 2, '2023-04-27 17:01:12', '2023-04-29 11:36:59', 8, 0);
INSERT INTO `leave` VALUES (27, '9', '9', '2023-04-03 16:00:00', '2023-04-04 16:00:00', 0, '2023-04-27 17:03:28', NULL, 9, 0);
INSERT INTO `leave` VALUES (28, '1', '1', '2023-04-04 16:00:00', '2023-04-05 16:00:00', 1, '2023-04-29 11:29:55', '2023-04-29 11:36:54', 8, 0);
INSERT INTO `leave` VALUES (29, '1', '1', '2023-05-02 16:00:00', '2023-05-04 16:00:00', 0, '2023-05-02 10:22:29', NULL, 8, 0);

-- ----------------------------
-- Table structure for meeting_room
-- ----------------------------
DROP TABLE IF EXISTS `meeting_room`;
CREATE TABLE `meeting_room`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议室名称',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议室状态',
  `remarks` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会议室表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of meeting_room
-- ----------------------------
INSERT INTO `meeting_room` VALUES (1, '第一会议室', '空闲', '', 0);
INSERT INTO `meeting_room` VALUES (2, '第二会议室', '使用中', NULL, 0);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `time` datetime NULL DEFAULT NULL COMMENT '公告时间',
  `is_show` tinyint NULL DEFAULT 1 COMMENT '是否展示，0不展示，1展示',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (10, '加班', '加班', '2020-11-03 09:00:00', 1, 0);
INSERT INTO `notice` VALUES (11, '放假通知', '明天全体放假！', '2022-12-19 01:02:00', 1, 0);
INSERT INTO `notice` VALUES (55, '1', '1', '2023-05-01 00:00:00', 1, 1);
INSERT INTO `notice` VALUES (56, '1', '1', '2023-05-01 08:00:00', 1, 1);
INSERT INTO `notice` VALUES (57, '1', '1', '2023-05-01 08:00:00', 1, 1);
INSERT INTO `notice` VALUES (58, '2', '2', '2023-05-01 12:00:40', 1, 1);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目描述',
  `rate` int NULL DEFAULT 0 COMMENT '项目进度',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目负责人',
  `apply_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目申请时间',
  `approve_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目审批时间',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目创建时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态 2 未通过，1 通过，0 未审核',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (5, '项目1', '1', 12, 'ojj', NULL, NULL, NULL, 1, 0);
INSERT INTO `project` VALUES (6, '项目2', '2', 22, 'ojj', NULL, NULL, NULL, 1, 0);
INSERT INTO `project` VALUES (7, '项目3', '3', 33, 'ojj', NULL, NULL, NULL, 1, 0);
INSERT INTO `project` VALUES (12, '6', '6', 11, '6', '2023-04-26 04:41:09', NULL, NULL, 0, 0);
INSERT INTO `project` VALUES (13, '7', '7', 11, '7', '2023-04-26 16:44:12', '2023-04-27 09:58:20', NULL, 2, 0);
INSERT INTO `project` VALUES (15, '1', '1', 0, '欧家俊', '2023-04-27 10:46:47', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for quit
-- ----------------------------
DROP TABLE IF EXISTS `quit`;
CREATE TABLE `quit`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离职原因',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离职描述',
  `quit_time` datetime NULL DEFAULT NULL COMMENT '离职时间',
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离职申请时间',
  `approve_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离职审批时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '申请状态 状态 2 未通过，1 通过，0 未审核',
  `user_id` int NULL DEFAULT NULL COMMENT '离职申请人',
  `is_deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `quit_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '离职申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of quit
-- ----------------------------
INSERT INTO `quit` VALUES (1, '1', '1', '2023-04-28 00:00:00', '2023-04-27 17:20:01', NULL, 0, 8, 0);
INSERT INTO `quit` VALUES (2, '2', '2', '2023-04-29 00:00:00', '2023-04-27 17:23:37', NULL, 0, 9, 0);
INSERT INTO `quit` VALUES (3, '2', '2', '2023-04-30 00:00:00', '2023-04-27 17:23:47', NULL, 0, 9, 0);
INSERT INTO `quit` VALUES (4, '1', '1', '2023-05-30 00:00:00', '2023-05-02 10:24:25', NULL, 0, 8, 0);

-- ----------------------------
-- Table structure for top_contacts
-- ----------------------------
DROP TABLE IF EXISTS `top_contacts`;
CREATE TABLE `top_contacts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '常用联系人表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of top_contacts
-- ----------------------------
INSERT INTO `top_contacts` VALUES (1, '欧家俊', '15915095796', '294557741@qq.com', 0);
INSERT INTO `top_contacts` VALUES (6, '莫子晴', '19854571208', '111', 0);
INSERT INTO `top_contacts` VALUES (11, '1', '1', '', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `permission` int NULL DEFAULT NULL COMMENT '权限 1管理员',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户创建时间',
  `is_deleted` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '1', NULL, NULL, '经理', 1, NULL, 1);
INSERT INTO `user` VALUES (3, 'tony', 'tony', '3', '111', '111', '员工', 2, NULL, 1);
INSERT INTO `user` VALUES (8, '欧家俊', 'ojj', '123456', '15915095796', '294557741@qq.com', '开发人员', 1, '2023-04-24 09:11:06', 0);
INSERT INTO `user` VALUES (9, '莫子晴', 'mzq', '123456', '111', '111', '董事长', 2, '2023-04-25 11:49:41', 0);
INSERT INTO `user` VALUES (10, '管理员', 'admin', 'admin', '1', '1', '管理员', 1, '2023-05-02 09:47:40', 0);

SET FOREIGN_KEY_CHECKS = 1;
