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

 Date: 03/05/2023 16:37:02
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
  `end_time` datetime NULL DEFAULT NULL COMMENT '任务截至时间',
  `complete_time` datetime NULL DEFAULT NULL COMMENT '任务完成时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '任务创建时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '任务状态 1（true）已完成， 0（false）未完成',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '任务清单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of assignment
-- ----------------------------
INSERT INTO `assignment` VALUES (9, NULL, '论文查重', '提交设计论文', 1, '2023-05-10 00:00:00', NULL, '2023-05-03 15:33:07', 0, 0);
INSERT INTO `assignment` VALUES (10, NULL, '论文答辩', '进行论文答辩', 2, '2023-05-18 00:00:00', NULL, '2023-05-03 15:33:33', 0, 0);
INSERT INTO `assignment` VALUES (11, NULL, '提交中期检查', '提交中期检查报告', 2, '2023-03-25 00:00:00', '2023-03-24 15:34:56', '2023-03-03 15:34:36', 1, 0);

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
  INDEX `user_id`(`user_id`) USING BTREE
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
INSERT INTO `contacts` VALUES (2, 8, '张三', '15915012345', '', 0);
INSERT INTO `contacts` VALUES (3, 8, '李四', '15915012346', '', 0);
INSERT INTO `contacts` VALUES (4, 8, '王五', '15915012347', '', 0);
INSERT INTO `contacts` VALUES (5, 8, '林六', '15915012348', '', 0);

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
INSERT INTO `customer` VALUES (1, '欧家俊', '15915095796', 0, '2023-04-21 17:12:54', '资深客户');
INSERT INTO `customer` VALUES (9, '陈三', '15915054321', 0, '2023-05-02 14:56:51', '合作伙伴');
INSERT INTO `customer` VALUES (10, '叶四', '15915054322', 0, '2023-05-03 14:59:01', '潜力客户');
INSERT INTO `customer` VALUES (11, '姚五', '15915054323', 0, '2023-05-04 14:59:28', '老客户');
INSERT INTO `customer` VALUES (12, '余总', '15915054324', 0, '2023-05-05 15:00:00', '老板客户');
INSERT INTO `customer` VALUES (13, '林六', '15915054325', 0, '2023-05-06 15:01:41', '合作伙伴');

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
  `approve_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批人',
  `approve_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报销审批时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '申请状态 2 未通过，1 通过，0 未审核',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请人',
  `is_deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报销申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of expense
-- ----------------------------
INSERT INTO `expense` VALUES (5, '1', '1', 1, '2023-05-03 12:27:55', NULL, NULL, 0, '欧家俊', 0);
INSERT INTO `expense` VALUES (6, '1', '1', 1, '2023-05-03 12:27:55', NULL, NULL, 0, '欧家俊', 0);
INSERT INTO `expense` VALUES (7, '1', '1', 1, '2023-05-03 12:27:59', '欧家俊', '2023-05-03 12:28:40', 2, '欧家俊', 0);
INSERT INTO `expense` VALUES (8, '1', '1', 1, '2023-05-03 12:27:59', NULL, NULL, 0, '欧家俊', 0);
INSERT INTO `expense` VALUES (9, '1', '1', 1, '2023-05-03 12:28:03', '欧家俊', '2023-05-03 12:28:38', 1, '欧家俊', 0);

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
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假申请人',
  `approve_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假申请表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of leave
-- ----------------------------

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
INSERT INTO `meeting_room` VALUES (2, '第二会议室', '使用中', '领导正在开会', 0);
INSERT INTO `meeting_room` VALUES (12, '第三会议室', '维修中', '等待维修灯管', 0);

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
INSERT INTO `notice` VALUES (59, '加班', '今晚加班', '2023-04-25 14:47:16', 1, 0);
INSERT INTO `notice` VALUES (60, '放假通知', '五一放假4.28-5.3', '2023-04-26 14:47:46', 1, 0);

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
  `approve_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人',
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
  `approve_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批人',
  `approve_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离职审批时间',
  `status` tinyint NULL DEFAULT NULL COMMENT '申请状态 状态 2 未通过，1 通过，0 未审核',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '离职申请人',
  `is_deleted` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '离职申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of quit
-- ----------------------------

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
INSERT INTO `top_contacts` VALUES (12, '欧家俊', '15915095796', '294557741@qq.com', 0);
INSERT INTO `top_contacts` VALUES (13, '张三', '15915012345', '1234567890@qq.com', 0);
INSERT INTO `top_contacts` VALUES (14, '李四', '15915012346', '1234567891@qq.com', 0);
INSERT INTO `top_contacts` VALUES (15, '王五', '15915012347', '1234567892@qq.com', 0);

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
INSERT INTO `user` VALUES (8, '欧家俊', 'ojj', '123456', '15915095796', '294557741@qq.com', '开发人员', 1, '2023-04-24 09:11:06', 0);
INSERT INTO `user` VALUES (9, '莫子晴', 'mzq', '123456', '1985457120', '1234567895@qq.com', '董事长', 1, '2023-04-25 11:49:41', 0);
INSERT INTO `user` VALUES (10, '管理员', 'admin', 'admin', '15915098765', '1234567894@qq.com', '管理员', 1, '2023-05-02 09:47:40', 0);
INSERT INTO `user` VALUES (11, '李四', 'ls', '123456', '15915012346', '1234567891@qq.com', '测试工程师', 2, '2023-05-03 15:09:37', 0);
INSERT INTO `user` VALUES (12, '张三', 'zs', '123456', '15915012345', '1234567890@qq.com', '运维工程师', 2, '2023-05-03 15:10:31', 0);
INSERT INTO `user` VALUES (13, '王五', 'wangwu', '123456', '15915012347', '1234567892@qq.com', '前端工程师', 2, '2023-05-03 15:11:00', 0);

SET FOREIGN_KEY_CHECKS = 1;
