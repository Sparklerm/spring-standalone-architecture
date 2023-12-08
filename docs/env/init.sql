/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL_80
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : boot_scaffold

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 09/12/2023 00:57:48
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

CREATE
database `boot_scaffold` default CHARACTER SET utf8mb4 collate utf8mb4_unicode_ci;

USE
`boot_scaffold`;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源名称',
    `url`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源URL',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
    `category_id` bigint NULL DEFAULT NULL COMMENT '资源分类ID',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '后台资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource`
VALUES (1, '后端资源管理API', '/resource/**', '', 1, '2023-11-21 23:29:48', NULL);
INSERT INTO `resource`
VALUES (2, '后台角色管理API', '/role/**', '', 1, '2023-11-21 23:29:48', NULL);
INSERT INTO `resource`
VALUES (3, '后台用户管理API', '/user/**', '', 1, '2023-11-21 23:29:48', NULL);
INSERT INTO `resource`
VALUES (4, '后台资源分类管理API', '/resourceCategory/**', '', 1, '2023-11-21 23:29:48', NULL);
INSERT INTO `resource`
VALUES (5, '普通用户', '/user/common/**', '普通用户基础API', 1, '2023-11-21 23:29:48', NULL);

-- ----------------------------
-- Table structure for resource_category
-- ----------------------------
DROP TABLE IF EXISTS `resource_category`;
CREATE TABLE `resource_category`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
    `sort`        int NULL DEFAULT NULL COMMENT '排序',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资源分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_category
-- ----------------------------
INSERT INTO `resource_category`
VALUES (1, '权限资源', 0, '2023-11-21 23:27:45', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          bigint  NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
    `admin_count` int NULL DEFAULT NULL COMMENT '后台用户数量',
    `status`      int NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
    `is_default`  tinyint NOT NULL DEFAULT 0 COMMENT '是否为用户注册时的默认角色：0->非默认;1->默认',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, '系统管理员', '可访问全部资源', 0, 1, 0, '2023-11-21 23:31:07', NULL);
INSERT INTO `role`
VALUES (2, '普通用户', '普通用户', NULL, 1, 1, '2023-11-21 23:31:07', NULL);

-- ----------------------------
-- Table structure for role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_relation`;
CREATE TABLE `role_resource_relation`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `role_id`     bigint NULL DEFAULT NULL COMMENT '角色ID',
    `resource_id` bigint NULL DEFAULT NULL COMMENT '资源ID',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '后台角色资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_resource_relation
-- ----------------------------
INSERT INTO `role_resource_relation`
VALUES (1, 1, 1, '2023-11-21 23:33:07', NULL);
INSERT INTO `role_resource_relation`
VALUES (2, 1, 2, '2023-11-22 11:25:51', NULL);
INSERT INTO `role_resource_relation`
VALUES (3, 1, 3, '2023-11-22 11:25:51', NULL);
INSERT INTO `role_resource_relation`
VALUES (4, 1, 4, '2023-11-22 11:25:51', NULL);
INSERT INTO `role_resource_relation`
VALUES (5, 2, 5, '2023-12-09 00:30:35', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `username`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
    `email`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
    `nick_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
    `note`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
    `status`      int NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
    `login_time`  datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', '$2a$10$zbnaAd4ZWvfccZaaQKkzuOMlIUnli37jxMCajfbDyk56zkfGS2E7G', 'admin@boot.com', 'Administrator',
        '默认管理员账户', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `user_id`     bigint NULL DEFAULT NULL COMMENT '用户Id',
    `role_id`     bigint NULL DEFAULT NULL COMMENT '角色Id',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation`
VALUES (1, 1, 1, '2023-11-22 02:25:43', NULL);

SET
FOREIGN_KEY_CHECKS = 1;