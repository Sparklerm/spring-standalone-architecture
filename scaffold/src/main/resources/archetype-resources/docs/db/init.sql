CREATE
database `boot_db` default CHARACTER SET utf8mb4 collate utf8mb4_unicode_ci;

USE
`boot_db`;

-- 表结构
CREATE TABLE `resource`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
    `url`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源URL',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
    `category_id` bigint                                                        DEFAULT NULL COMMENT '资源分类ID',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='后台资源表';

CREATE TABLE `resource_category`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类名称',
    `sort`        int                                                           DEFAULT NULL COMMENT '排序',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源分类表';

CREATE TABLE `role`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
    `admin_count` int                                                           DEFAULT NULL COMMENT '后台用户数量',
    `status`      int                                                           DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='后台用户角色表';

CREATE TABLE `role_resource_relation`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `role_id`     bigint   DEFAULT NULL COMMENT '角色ID',
    `resource_id` bigint   DEFAULT NULL COMMENT '资源ID',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='后台角色资源关系表';

CREATE TABLE `user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `username`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
    `email`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
    `nick_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
    `note`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `status`      int                                                           DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
    `login_time`  datetime                                                      DEFAULT NULL COMMENT '最后登录时间',
    `create_time` datetime                                                      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                      DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE `user_role_relation`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `user_id`     bigint   DEFAULT NULL COMMENT '用户Id',
    `role_id`     bigint   DEFAULT NULL COMMENT '角色Id',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='后台用户和角色关系表';
-- 初始化数据

INSERT INTO `user`
(id, username, password, email, nick_name, note, status, login_time, create_time, update_time)
VALUES (1, 'admin', '$2a$10$zbnaAd4ZWvfccZaaQKkzuOMlIUnli37jxMCajfbDyk56zkfGS2E7G', 'admin@boot.com', 'Administrator',
        '默认管理员账户 密码 123', 1, NULL, now(), NULL);

INSERT INTO `role`
(id, name, description, admin_count, status, sort, create_time, update_time)
VALUES (1, '系统管理员', '可访问全部资源', 0, 1, 0, now(), NULL);

INSERT INTO user_role_relation
    (user_id, role_id, create_time, update_time)
VALUES (1, 1, now(), NULL);

INSERT INTO resource_category
    (id, name, sort, create_time, update_time)
VALUES (1, '权限资源', 0, now(), NULL);

INSERT INTO resource
    (id, name, url, description, category_id, create_time, update_time)
VALUES (1, '后端资源管理API', '/resource/**', '', 1, now(), NULL);
INSERT INTO resource
    (id, name, url, description, category_id, create_time, update_time)
VALUES (2, '后台角色管理API', '/role/**', '', 1, now(), NULL);
INSERT INTO resource
    (id, name, url, description, category_id, create_time, update_time)
VALUES (3, '后台用户管理API', '/user/**', '', 1, now(), NULL);
INSERT INTO resource
    (id, name, url, description, category_id, create_time, update_time)
VALUES (4, '后台资源分类管理API', '/resourceCategory/**', '', 1, now(), NULL);

INSERT INTO boot_scaffold.role_resource_relation
    (id, role_id, resource_id, create_time, update_time)
VALUES (1, 1, 1, now(), NULL);
INSERT INTO boot_scaffold.role_resource_relation
    (id, role_id, resource_id, create_time, update_time)
VALUES (2, 1, 2, now(), NULL);
INSERT INTO boot_scaffold.role_resource_relation
    (id, role_id, resource_id, create_time, update_time)
VALUES (3, 1, 3, now(), NULL);
INSERT INTO boot_scaffold.role_resource_relation
    (id, role_id, resource_id, create_time, update_time)
VALUES (4, 1, 4, now(), NULL);