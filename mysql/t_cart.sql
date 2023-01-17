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

 Date: 13/01/2023 20:45:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '购物车数据id',
  `uid` int NOT NULL COMMENT '用户id',
  `pid` int NOT NULL COMMENT '商品id',
  `price` bigint DEFAULT NULL COMMENT '加入时商品单价',
  `num` int DEFAULT NULL COMMENT '商品数量',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cart
-- ----------------------------
BEGIN;
INSERT INTO `t_cart` (`cid`, `uid`, `pid`, `price`, `num`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES (8, 4, 10000023, 15, 5, 'xiaofu', '2023-01-12 20:28:31', 'xiaofu', '2023-01-12 20:28:31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
