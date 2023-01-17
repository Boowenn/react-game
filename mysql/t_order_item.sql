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

 Date: 13/01/2023 20:45:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单中的商品记录的id',
  `oid` int NOT NULL COMMENT '所归属的订单的id',
  `pid` int NOT NULL COMMENT '商品的id',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `price` bigint DEFAULT NULL COMMENT '商品价格',
  `num` int DEFAULT NULL COMMENT '购买数量',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
