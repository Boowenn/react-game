/*
 Navicat MySQL Data Transfer

 Source Server         : gameshopping
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : game

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 13/01/2023 20:46:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码',
  `salt` char(36) DEFAULT NULL COMMENT '盐值',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `gender` int DEFAULT NULL COMMENT '性别:0-女，1-男',
  `avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `is_delete` int DEFAULT NULL COMMENT '是否删除：0-未删除，1-已删除',
  `created_user` varchar(20) DEFAULT NULL COMMENT '日志-创建人',
  `created_time` datetime DEFAULT NULL COMMENT '日志-创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '日志-最后修改执行人',
  `modified_time` datetime DEFAULT NULL COMMENT '日志-最后修改时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`uid`, `username`, `password`, `salt`, `phone`, `email`, `gender`, `avatar`, `is_delete`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES (1, '678678', '3F019A40074BB42EE66F28EE35EEDD12', '29C35505-8DDA-4E90-9BC3-06C5CF5E4349', NULL, NULL, NULL, NULL, 1, '678678', '2023-01-09 20:45:33', '678678', '2023-01-09 20:45:33');
INSERT INTO `t_user` (`uid`, `username`, `password`, `salt`, `phone`, `email`, `gender`, `avatar`, `is_delete`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES (2, 'panghu', '34CFB0129E73A398B7DAD1D3FB830EF4', '8D4C2B2A-1C96-4EFE-9774-FEDB8E3C0ED0', NULL, NULL, NULL, NULL, 1, 'panghu', '2023-01-09 20:48:13', 'panghu', '2023-01-09 20:48:13');
INSERT INTO `t_user` (`uid`, `username`, `password`, `salt`, `phone`, `email`, `gender`, `avatar`, `is_delete`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES (3, 'daxiong', '0F5AF0078872487DBE71A6505387A883', 'E6C445F6-41A8-45BC-A677-780D68064075', NULL, NULL, NULL, NULL, 1, 'daxiong', '2023-01-09 20:50:39', 'daxiong', '2023-01-09 20:50:39');
INSERT INTO `t_user` (`uid`, `username`, `password`, `salt`, `phone`, `email`, `gender`, `avatar`, `is_delete`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES (4, 'xiaofu', 'C90E2A90E09262FD69E907356AD88B17', '2F788883-AF63-4D92-AC88-F2C9E81A5491', NULL, NULL, NULL, NULL, 0, 'xiaofu', '2023-01-12 20:09:32', 'xiaofu', '2023-01-12 20:09:32');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
